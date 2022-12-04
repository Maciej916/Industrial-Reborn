package com.maciej916.indreb.common.block.impl.machine.basic.fluid_enricher;

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
import com.maciej916.indreb.common.recipe.impl.FluidEnrichingRecipe;
import com.maciej916.indreb.common.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
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

public class BlockEntityFluidEnricher extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IBlockEntityFluid, IHasSound {

    public static final int INPUT_SLOT = 0;
    public static final int FILL_UP = 1;
    public static final int FILL_DOWN = 2;
    public static final int DRAIN_UP = 3;
    public static final int DRAIN_DOWN = 4;

    protected static List<FluidEnrichingRecipe> recipes;
    protected FluidEnrichingRecipe recipe;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressInt progressFill = new ProgressInt(0, 1);
    public ProgressInt progressDrain = new ProgressInt(0, 1);

    public FluidStorage firstTank = new FluidStorage(ServerConfig.fluid_enricher_fluid_capacity.get(), (fluidStack -> getRecipeFluids().contains(fluidStack.getFluid()))) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public FluidStorage secondTank = new FluidStorage(ServerConfig.fluid_enricher_fluid_capacity.get(), (fluidStack -> getRecipeFluidsOutput().contains(fluidStack.getFluid()))) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityFluidEnricher(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FLUID_ENRICHER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.fluid_enricher_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressInt(1, this.progressFill);
        this.containerData.syncProgressInt(2, this.progressDrain);
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.fillTank(progressFill, firstTank, getBaseStorage(), FILL_UP, FILL_DOWN);
        BlockEntityUtil.drainTank(progressDrain, secondTank, getBaseStorage(), DRAIN_UP, DRAIN_DOWN);

        if (recipes == null) {
            initRecipes();
        }

        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);

        if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
            FluidEnrichingRecipe oldRecipe = recipe;

            if (inputStack.isEmpty()) {
                recipe = null;
            } else {
                recipe = getRecipe(inputStack).orElse(null);
            }

            if (oldRecipe != recipe && oldRecipe != null) {
                progressRecipe.resetProgress();
            }
        }

        if (recipe != null) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                if (
                    getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                    firstTank.takeFluid(recipe.getFluidInput().getAmount(), true) == recipe.getFluidInput().getAmount() &&
                    secondTank.fillFluid(recipe.getFluidOutput(), true) == recipe.getFluidOutput().getAmount() &&
                    (secondTank.getFluid().isEmpty() || secondTank.getFluid().getFluid() == recipe.getFluidOutput().getFluid()) &&
                    !progressRecipe.isCurrentAboveEqualMax()
                ) {
                    activeState = true;
                    progressRecipe.incProgress(1);
                    getEnergyStorage().consumeEnergy(energyCost, false);
                }

                if (progressRecipe.isCurrentAboveEqualMax()) {
                    StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, recipe.getIngredientCount().getIngredientsCount().get(0));
                    firstTank.takeFluid(recipe.getFluidInput().getAmount(), false);
                    secondTank.fillFluid(recipe.getFluidOutput(), false);
                    progressRecipe.resetProgress();
                    addRecipeUsed(recipe);
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuFluidEnricher(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return getRawRecipe(stack).isPresent();

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
        slots.add(new BaseSlot(INPUT_SLOT, 8, 35, 7, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));

        slots.add(new BaseSlot(FILL_UP, 44, 19, 43, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(FILL_DOWN,  44, 50, 43, 49, GuiSlotBg.NORMAL));

        slots.add(new BaseSlot(DRAIN_UP, 128, 19, 127, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(DRAIN_DOWN,  128, 50, 127, 49, GuiSlotBg.NORMAL));

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
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.progressRecipe.deserializeNBT(tag.getCompound("progressRecipe"));
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));
        this.secondTank.readFromNBT(tag.getCompound("secondTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
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
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> false)),
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
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_PULLING, UpgradeType.FLUID_EJECTOR);
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

    // TODO: ADD SOUND
    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.FLUID_ENRICHING.get());
    }

    protected Optional<FluidEnrichingRecipe> getRawRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.FLUID_ENRICHING.get(), new SimpleContainer(input), level);
    }

    protected Optional<FluidEnrichingRecipe> getRecipe(ItemStack input) {
        List<FluidEnrichingRecipe> recipes = level.getRecipeManager().getAllRecipesFor(ModRecipeType.FLUID_ENRICHING.get());
        for (FluidEnrichingRecipe recipe : recipes) {
            if (recipe.matches(new SimpleContainer(input), level)) {
                if (recipe.getFluidInput().getFluid() == firstTank.getFluid().getFluid()) {
                    return Optional.of(recipe);
                }
            }
        }

        return Optional.empty();
    }

    protected HashSet<Fluid> getRecipeFluids() {
        HashSet<Fluid> fluids = new HashSet<>();
        if (level != null && recipes != null) {
            for (FluidEnrichingRecipe recipe : recipes) {
                fluids.add(recipe.getFluidInput().getFluid());
            }
        }

        return fluids;
    }

    protected HashSet<Fluid> getRecipeFluidsOutput() {
        HashSet<Fluid> fluids = new HashSet<>();
        if (level != null && recipes != null) {
            for (FluidEnrichingRecipe recipe : recipes) {
                fluids.add(recipe.getFluidOutput().getFluid());
            }
        }

        return fluids;
    }

    private boolean canWork() {
        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        return !inputStack.isEmpty();
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoFluidBar(firstTank));
        oneProbeInfo.add(new ProbeInfoFluidBar(secondTank));

        return oneProbeInfo;
    }

}
