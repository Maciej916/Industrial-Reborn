package com.maciej916.indreb.common.block.impl.machine.t_standard.ore_washing_plant;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
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
import com.maciej916.indreb.common.recipe.impl.OreWashingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
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

public class BlockEntityOreWashingPlant extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IBlockEntityFluid, IHasSound {

    public static final int FILL_UP = 0;
    public static final int FILL_DOWN = 1;
    public static final int INPUT_SLOT = 2;
    public static final int OUTPUT_SLOT_1 = 3;
    public static final int OUTPUT_SLOT_2 = 4;

    protected static List<OreWashingRecipe> recipes;
    protected OreWashingRecipe recipe;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressInt progressFill = new ProgressInt(0, 1);

    private boolean rolledChance = false;
    private ItemStack chanceResult;

    public FluidStorage firstTank = new FluidStorage(ServerConfig.fluid_enricher_fluid_capacity.get(), (fluidStack -> getRecipeFluids().contains(fluidStack.getFluid()))) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityOreWashingPlant(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ORE_WASHING_PLANT.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.ore_washing_plant_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.STANDARD);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressInt(1, this.progressFill);
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.fillTank(progressFill, firstTank, getBaseStorage(), FILL_UP, FILL_DOWN);

        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        ItemStack outputStack1 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_1);
        ItemStack outputStack2 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_2);

        if (recipes == null) {
            initRecipes();
        }

        if (baseSlotsChangedForTick.contains(INPUT_SLOT) || (recipe == null && !inputStack.isEmpty())) {
            OreWashingRecipe oldRecipe = recipe;

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

            if (!rolledChance) {
                chanceResult = recipe.rollChanceResult();
                rolledChance = true;
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(recipe.getTickEnergyCost());

            if (canWork()) {
                if (
                        getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                        (outputStack1.isEmpty() || (recipe.getResultItem().getCount() + outputStack1.getCount() <= outputStack1.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack1.getItem())) &&
                        firstTank.takeFluid(recipe.getFluidInput().getAmount(), true) == recipe.getFluidInput().getAmount() &&
                        !progressRecipe.isCurrentAboveEqualMax()
                ) {
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    activeState = true;
                    progressRecipe.incProgress(1);
                }

                if (progressRecipe.isCurrentAboveEqualMax()) {
                    if (outputStack2.isEmpty() || chanceResult.isEmpty() || (chanceResult.getCount() + outputStack2.getCount() <= outputStack2.getMaxStackSize() && chanceResult.getItem() == outputStack2.getItem())) {
                        StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT_1, recipe.getResultItem());
                        firstTank.takeFluid(recipe.getFluidInput().getAmount(), false);
                        StackHandlerHelper.shrinkStack(getBaseStorage(), INPUT_SLOT, recipe.getIngredientCount().getIngredientsCount().get(0));
                        progressRecipe.resetProgress();
                        addRecipeUsed(recipe);
                        rolledChance = false;

                        if (!chanceResult.isEmpty()) {
                            StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT_2, chanceResult);
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuOreWashingPlant(this, containerId, playerInventory, player, new SimpleContainerData(0));
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

        if (slot == FILL_DOWN) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY;
            }
        }
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(FILL_UP, 8, 19, 7, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(FILL_DOWN,  8, 50, 7, 49, GuiSlotBg.NORMAL));

        slots.add(new BaseSlot(INPUT_SLOT, 65, 33, 64, 32, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT_1,  122, 25, 121, 24, GuiSlotBg.NORMAL_BLANK));
        slots.add(new OutputSlot(OUTPUT_SLOT_2, 122, 44, 121, 43, GuiSlotBg.NORMAL_BLANK));

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
        this.progressRecipe.deserializeNBT(tag.getCompound("progressRecipe"));
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("firstTank", this.firstTank.writeToNBT(tag.getCompound("firstTank")));
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((IBaseRecipe) recipe).getExperience();
    }

    private final ArrayList<LazyOptional<?>> baseCapabilities = new ArrayList<>(List.of(
            LazyOptional.of(() -> this.firstTank)
    ));


    private final Map<Direction, LazyOptional<WrappedHandler>> itemCapabilities = Map.of(
            Direction.UP, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == OUTPUT_SLOT_1 || i == OUTPUT_SLOT_2, (i, stack) -> false)),
            Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.EAST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
            Direction.WEST, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(),  (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack)))
    );

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            if (side == null) return LazyOptional.empty();
            return baseCapabilities.get(0).cast();
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
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_PULLING);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.ORE_WASHING_PLANT.get();
    }

    @Override
    public List<FluidStack> getStoredFluids() {
        return List.of(firstTank.getFluid());
    }

    @Override
    public void setStoredFluids(List<FluidStack> fluids) {
        this.firstTank.setFluid(fluids.get(0));
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.ORE_WASHING.get());
    }

    protected Optional<OreWashingRecipe> getRawRecipe(ItemStack input) {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (OreWashingRecipe recipe : recipes) {
                    if (recipe.matches(new SimpleContainer(input), level)) {
                        if (recipe.getFluidInput().getFluid() == firstTank.getFluid().getFluid() || firstTank.getFluid().isEmpty()) {
                            return Optional.of(recipe);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }

    protected Optional<OreWashingRecipe> getRecipe(ItemStack input) {
        if (level != null) {
            if (recipes == null) initRecipes();
            if (recipes != null) {
                for (OreWashingRecipe recipe : recipes) {
                    if (recipe.matches(new SimpleContainer(input), level)) {
                        if (recipe.getFluidInput().getFluid() == firstTank.getFluid().getFluid()) {
                            return Optional.of(recipe);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }

    protected HashSet<Fluid> getRecipeFluids() {
        HashSet<Fluid> fluids = new HashSet<>();
        if (level != null && recipes != null) {
            for (OreWashingRecipe recipe : recipes) {
                fluids.add(recipe.getFluidInput().getFluid());
            }
        }

        return fluids;
    }

    private boolean canWork() {
        final ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack1 = getBaseStorage().getStackInSlot(OUTPUT_SLOT_1);

        return !inputStack.isEmpty() && (outputStack1.isEmpty() || (outputStack1.getCount() + recipe.getResultItem().getCount() <= outputStack1.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack1.getItem()));
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoFluidBar(firstTank));

        return oneProbeInfo;
    }


}
