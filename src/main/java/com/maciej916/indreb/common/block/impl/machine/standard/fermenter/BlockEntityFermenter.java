package com.maciej916.indreb.common.block.impl.machine.standard.fermenter;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoFluidBar;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.FermentingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockEntityFermenter extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IBlockEntityFluid,IHasSound {

    public static final int FILL_UP = 0;
    public static final int FILL_DOWN = 1;
    public static final int DRAIN_UP = 2;
    public static final int DRAIN_DOWN = 3;
    public static final int WASTE_SLOT = 4;

    protected static List<FermentingRecipe> recipes;
    protected FermentingRecipe recipe;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressFloat progressHeat = new ProgressFloat(0, 100);
    public ProgressInt progressFill = new ProgressInt(0, 1);
    public ProgressInt progressDrain = new ProgressInt(0, 1);
    public ProgressFloat progressWaste = new ProgressFloat();

    public boolean fluidInputChanged = false;

    public FluidStorage firstTank = new FluidStorage(ServerConfig.fluid_enricher_fluid_capacity.get(), (fluidStack -> getRecipeFluids().contains(fluidStack.getFluid()))) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
            fluidInputChanged = true;
        }
    };

    public FluidStorage secondTank = new FluidStorage(ServerConfig.fluid_enricher_fluid_capacity.get(), (fluidStack -> getRecipeFluidsOutput().contains(fluidStack.getFluid()))) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityFermenter(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FERMENTER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.fermenter_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.STANDARD);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressFloat(1, this.progressHeat);
        this.containerData.syncProgressInt(2, this.progressFill);
        this.containerData.syncProgressInt(3, this.progressDrain);
        this.containerData.syncProgressFloat(4, this.progressWaste);
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.fillTank(progressFill, firstTank, getBaseStorage(), FILL_UP, FILL_DOWN);
        BlockEntityUtil.drainTank(progressDrain, secondTank, getBaseStorage(), DRAIN_UP, DRAIN_DOWN);

        ItemStack wasteStack = getBaseStorage().getStackInSlot(WASTE_SLOT);

        if (recipes == null) {
            initRecipes();
        }

        if (fluidInputChanged) {
            FermentingRecipe oldRecipe = recipe;

            recipe = getRecipe().orElse(null);

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
                progressWaste.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            if (progressWaste.getCurrentProgress() == -1) {
                progressWaste.setData(0, recipe.getWasteDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            progressWaste.setProgressMax(getUpgradesDuration(recipe.getWasteDuration()));

            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (
                    getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                    firstTank.takeFluid(recipe.getFluidInput().getAmount(), true) == recipe.getFluidInput().getAmount() &&
                    secondTank.fillFluid(recipe.getFluidOutput(), true) == recipe.getFluidOutput().getAmount() &&
                    (secondTank.getFluid().isEmpty() || secondTank.getFluid().getFluid() == recipe.getFluidOutput().getFluid()) &&
                    ((progressWaste.getCurrentProgress() + recipe.getTickWasteIncrease() >= recipe.getWasteDuration() && wasteStack.getCount() < getBaseStorage().getSlotLimit(WASTE_SLOT)) || progressWaste.getCurrentProgress() + recipe.getTickWasteIncrease() < recipe.getWasteDuration()) &&
                    (wasteStack.isEmpty() || wasteStack.getItem() == recipe.getWasteOutput().getItem()) &&
                    !progressRecipe.isCurrentAboveEqualMax()
            ) {
                activeState = true;
                progressRecipe.incProgress(1 + (progressHeat.getCurrentProgress() / 100f));
                progressWaste.incProgress(recipe.getTickWasteIncrease());
                getEnergyStorage().consumeEnergy(energyCost, false);
            }

            if (progressRecipe.isCurrentAboveEqualMax()) {
                firstTank.takeFluid(recipe.getFluidInput().getAmount(), false);
                secondTank.fillFluid(recipe.getFluidOutput(), false);
                progressRecipe.resetProgress();
                addRecipeUsed(recipe);
            }

            if (progressWaste.isCurrentAboveEqualMax()) {
                StackHandlerHelper.addOutputStack(getBaseStorage(), WASTE_SLOT, recipe.getWasteOutput());
                progressWaste.resetProgress();
            }
        }

        if ((getRedstonePower() > 0 && getEnergyStorage().consumeEnergy(ServerConfig.fermenter_heat_cost.get(), true) >= ServerConfig.fermenter_heat_cost.get() || activeState)) {

            if (getTickCounter() == 20) {
                if (!activeState) {
                    getEnergyStorage().consumeEnergy(ServerConfig.fermenter_heat_cost.get(), false);
                }

                if (progressHeat.getCurrentProgress() + 0.2f < 100) {
                    progressHeat.incProgress(0.2f);
                }
            }

            activeState = true;

        } else {
            if (progressHeat.getCurrentProgress() > 0) {
                if (getTickCounter() == 20) {
                    progressHeat.decProgress(Math.min(progressHeat.getCurrentProgress(), 1));
                }
            }
        }

        fluidInputChanged = false;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuFermenter(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == FILL_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && getRecipeFluids().contains(cap.getFluidInTank(1).getFluid());
            }
        }

        if (slot == DRAIN_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY;
            }
        }
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(FILL_UP, 13, 19, 12, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(FILL_DOWN,  13, 50, 12, 49, GuiSlotBg.NORMAL));

        slots.add(new BaseSlot(DRAIN_UP, 127, 19, 126, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(DRAIN_DOWN,  127, 50, 126, 49, GuiSlotBg.NORMAL));

        slots.add(new OutputSlot(WASTE_SLOT, 127, 73, 126, 72, GuiSlotBg.NORMAL));

        return super.addBaseSlots(slots);
    }

    @Override
    public ArrayList<ElectricSlot> addElectricSlots(ArrayList<ElectricSlot> slots) {
        slots.add(new ElectricSlot(0, 152, 62, InventorySlotType.ELECTRIC, GuiSlotBg.BATTERY, true, false));
        return super.addElectricSlots(slots);
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressWaste.deserializeNBT(tag.getCompound("progressWaste"));
        this.progressRecipe.deserializeNBT(tag.getCompound("progressRecipe"));
        this.progressHeat.deserializeNBT(tag.getCompound("progressHeat"));
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));
        this.secondTank.readFromNBT(tag.getCompound("secondTank"));

        this.fluidInputChanged = true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressWaste", this.progressWaste.serializeNBT());
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("progressHeat", this.progressHeat.serializeNBT());
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("firstTank", this.firstTank.writeToNBT(tag.getCompound("firstTank")));
        tag.put("secondTank", this.secondTank.writeToNBT(tag.getCompound("secondTank")));
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    private final ArrayList<LazyOptional<?>> baseCapabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(() -> this.firstTank),
            LazyOptional.of(() -> this.secondTank)
    ));

    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == WASTE_SLOT, (i, stack) -> false)),
            Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.EAST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.WEST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(),  (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack)))
    );

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            if (side == null) return LazyOptional.empty();

            if (getBlock() instanceof IStateFacing facing) {
                Direction dir = facing.getDirection(getBlockState());
                if (dir.getClockWise() == side) {
                    return baseCapabilities.get(0).cast();
                }
                if (dir.getCounterClockWise() == side) {
                    return baseCapabilities.get(1).cast();
                }
            }

            return LazyOptional.empty();
        }

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) return LazyOptional.empty();

            if (itemCapabilities.containsKey(side)) {
                if (side == Direction.UP || side == Direction.DOWN) return itemCapabilities.get(side).cast(); // UP - DOWN

                Direction localDir = this.getBlockState().getValue(BlockStateHelper.HORIZONTAL_FACING_PROPERTY);
                return switch (localDir) {
                    default -> itemCapabilities.get(side).cast(); // SOUTH
                    case NORTH -> itemCapabilities.get(side.getOpposite()).cast();
                    case WEST -> itemCapabilities.get(side.getCounterClockWise()).cast();
                    case EAST -> itemCapabilities.get(side.getClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<?> capability : baseCapabilities) capability.invalidate();
        for (LazyOptional<?> capability : itemCapabilities.values()) capability.invalidate();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_EJECTOR, UpgradeType.FLUID_PULLING);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.FERMENTER.get();
    }

    @Override
    public List<FluidStack> getStoredFluids() {
        return List.of(firstTank.getFluid(), secondTank.getFluid());
    }

    @Override
    public void setStoredFluids(List<FluidStack> fluids) {
        this.firstTank.setFluid(fluids.get(0));
        this.secondTank.setFluid(fluids.get(1));
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.FERMENTING.get());
    }

    protected Optional<FermentingRecipe> getRecipe() {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (FermentingRecipe recipe : recipes) {
                    if (recipe.getFluidInput().getFluid() == firstTank.getFluid().getFluid()) {
                        return Optional.of(recipe);
                    }
                }
            }
        }

        return Optional.empty();
    }

    protected HashSet<Fluid> getRecipeFluids() {
        HashSet<Fluid> fluids = new HashSet<>();
        if (level != null && recipes != null) {
            for (FermentingRecipe recipe : recipes) {
                fluids.add(recipe.getFluidInput().getFluid());
            }
        }

        return fluids;
    }

    protected HashSet<Fluid> getRecipeFluidsOutput() {
        HashSet<Fluid> fluids = new HashSet<>();
        if (level != null && recipes != null) {
            for (FermentingRecipe recipe : recipes) {
                fluids.add(recipe.getFluidOutput().getFluid());
            }
        }

        return fluids;
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoFluidBar(firstTank));
        oneProbeInfo.add(new ProbeInfoFluidBar(secondTank));

        return oneProbeInfo;
    }
}
