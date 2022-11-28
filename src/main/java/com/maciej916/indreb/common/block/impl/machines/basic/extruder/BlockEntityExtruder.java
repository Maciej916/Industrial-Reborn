package com.maciej916.indreb.common.block.impl.machines.basic.extruder;

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
import com.maciej916.indreb.common.api.slot.DisabledSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.util.Progress;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketExtruderRecipe;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.FluidExtrudingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import com.maciej916.indreb.common.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockEntityExtruder extends IndRebBlockEntity implements IHasExp, IHasUpgrades, IBlockEntityEnergy, IBlockEntityFluid, IHasSound {

    public static final int SYNC_DATA_SLOTS = 2;
    protected final ContainerData data;

    public static final int RECIPE_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected static List<FluidExtrudingRecipe> recipes;

    protected FluidExtrudingRecipe recipe;
    protected int recipeIndex = 0;
    public Progress progressRecipe = new Progress();

    public FluidStorage firstTank = new FluidStorage(ServerConfig.geo_generator_lava_capacity.get(), (fluidStack -> recipe != null && fluidStack.getFluid() == recipe.getFirstFluid().getFluid())) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public FluidStorage secondTank = new FluidStorage(ServerConfig.geo_generator_lava_capacity.get(), (fluidStack -> recipe != null && fluidStack.getFluid() == recipe.getSecondFluid().getFluid())) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityExtruder(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.EXTRUDER.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.extruder_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockEntityExtruder.this.progressRecipe.getContainerDataCurrent();
                    case 1 -> BlockEntityExtruder.this.progressRecipe.getContainerDataMax();

                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockEntityExtruder.this.progressRecipe.setContainerDataCurrent(value);
                    case 1 -> BlockEntityExtruder.this.progressRecipe.setContainerDataMax(value);
                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public void tickWork() {

        if (recipes == null) {
            initRecipes();
        }

        if (recipe == null && recipes != null) {
            setRecipe(recipeIndex);
        }

        if (recipe != null) {
            if (progressRecipe.currentProgress() == -1) {
                progressRecipe.setData(0, recipe.getDuration());
            }

            progressRecipe.setProgressMax(getUpgradesDuration(recipe.getDuration()));
            int energyCost = getUpgradesEnergyCost(ServerConfig.electric_furnace_tick_usage.get());

            if (canWork()) {
                if (
                    getEnergyStorage().consumeEnergy(energyCost, true) == energyCost &&
                    firstTank.takeFluid(recipe.getFirstFluid().getAmount(), true) == recipe.getFirstFluid().getAmount() &&
                    secondTank.takeFluid(recipe.getSecondFluid().getAmount(), true) == recipe.getSecondFluid().getAmount() &&
                    !progressRecipe.isCurrentAboveEqualMax()
                ) {
                    activeState = true;
                    progressRecipe.incProgress(1);
                    getEnergyStorage().consumeEnergy(energyCost, false);
                    getEnergyStorage().updateConsumed(energyCost);
                }

                if (progressRecipe.isCurrentAboveEqualMax()) {
                    StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, recipe.getResultItem());
                    progressRecipe.resetProgress();
                    if (recipe.isConsumeFist()) firstTank.takeFluid(recipe.getFirstFluid().getAmount(), false);
                    if (recipe.isConsumeSecond()) secondTank.takeFluid(recipe.getSecondFluid().getAmount(), false);
                    addRecipeUsed(recipe);
                }
            }

        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuExtruder(this, containerId, playerInventory, player, data);
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new DisabledSlot(RECIPE_SLOT, 80, 59, 79, 58, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(OUTPUT_SLOT, 121, 35, 116, 30, GuiSlotBg.LARGE));
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
        this.recipeIndex = tag.getInt("recipeIndex");
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));
        this.secondTank.readFromNBT(tag.getCompound("secondTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.putInt("recipeIndex", recipeIndex);
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
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> i == OUTPUT_SLOT, (i, stack) -> false)),
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
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_PULLING);
    }

    public void initRecipes() {
        recipes = Objects.requireNonNull(getLevel()).getRecipeManager().getAllRecipesFor(ModRecipeType.FLUID_EXTRUDING.get());
    }

    public void setRecipe(int index) {
        if (level != null) {
            this.recipe = Objects.requireNonNullElseGet(recipes, () -> level.getRecipeManager().getAllRecipesFor(ModRecipeType.FLUID_EXTRUDING.get())).get(index);
            getBaseStorage().setStackInSlot(RECIPE_SLOT, this.recipe.getResultItem());
            progressRecipe.setBoth(-1);
        }
    }

    public Runnable changeRecipeClient(boolean next) {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketExtruderRecipe(getBlockPos(), next));
    }

    public void changeRecipeServer(boolean next) {
        if (recipes != null) {
            int newIndex = recipeIndex + (next ? 1 : -1);
            if (newIndex > recipes.size() - 1) newIndex = 0;
            if (newIndex < 0) newIndex = recipes.size() - 1;

            setRecipe(newIndex);
            this.recipeIndex = newIndex;
            this.progressRecipe.setBoth(-1);
        }
    }

    private boolean canWork() {
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);
        return outputStack.isEmpty() || (outputStack.getCount() + recipe.getResultItem().getCount() <= outputStack.getMaxStackSize() && recipe.getResultItem().getItem() == outputStack.getItem());
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


    // NEED SOUND
    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.EXTRACTOR.get();
    }
}
