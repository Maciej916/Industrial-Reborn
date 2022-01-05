package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.receipe.impl.FluidEnrichingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.maciej916.indreb.common.enums.EnergyType.RECEIVE;

public class BlockEntityFluidEnricher extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT = 0;
    public static final int DRAIN_BUCKET_UP = 1;
    public static final int DRAIN_BUCKET_DOWN = 2;

    public FluidStorage fluidInputStorage = new FluidStorage(8000);
    public FluidStorage fluidOutputStorage = new FluidStorage(8000);

    private int cachedInput = 0;
    private int cachedOutput = 0;

    public BlockEntityProgress progressDrain = new BlockEntityProgress(0, 1);

    private boolean active = false;
    public BlockEntityProgress progress = new BlockEntityProgress();
    private FluidEnrichingRecipe recipe;
    private ItemStack cachedInputStack = ItemStack.EMPTY;

    public BlockEntityFluidEnricher(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FLUID_ENRICHER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.fluid_enricher_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.BASIC);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 13, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 12, 34));
        slots.add(new IndRebSlot(DRAIN_BUCKET_UP, 127, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 126, 18));
        slots.add(new IndRebSlot(DRAIN_BUCKET_DOWN, 127, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 126, 49));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }

    protected Optional<FluidEnrichingRecipe> getRawRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.FLUID_ENRICHING, new SimpleContainer(input), level);
    }

    protected Optional<FluidEnrichingRecipe> getRecipe(ItemStack input) {
        List<FluidEnrichingRecipe> recipes = level.getRecipeManager().getAllRecipesFor(ModRecipeType.FLUID_ENRICHING);
        for (FluidEnrichingRecipe recipe : recipes) {
            if (recipe.matches(new SimpleContainer(input), level)) {
                if (recipe.getFluidInput().getFluid() == fluidInputStorage.getFluid().getFluid()) {
                    return Optional.of(recipe);
                }
            }
        }

        return Optional.empty();
    }

    protected ItemStack getRecipeResult(ItemStack stack) {
        return this.recipe.assemble(new SimpleContainer(stack));
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return getRawRecipe(stack).isPresent();
    }

    boolean canWork(ItemStack inputStack) {
        return inputStack.getCount() >= recipe.getIngredientCount() && (recipe.getResult().getFluid() == fluidOutputStorage.getFluid().getFluid() || fluidOutputStorage.getFluid().isEmpty()) && fluidOutputStorage.fillFluid(recipe.getResult(), true) == recipe.getResult().getAmount();
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        boolean updateState = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack drainBucketUp = getStackHandler().getStackInSlot(DRAIN_BUCKET_UP);
        final ItemStack drainBucketDown = getStackHandler().getStackInSlot(DRAIN_BUCKET_DOWN);

        if (cachedInput != fluidInputStorage.getFluidAmount()) {
            cachedInput = fluidInputStorage.getFluidAmount();
            updateState = true;
        }

        if (cachedOutput != fluidOutputStorage.getFluidAmount()) {
            cachedOutput = fluidOutputStorage.getFluidAmount();
            updateState = true;
        }

        if (progressDrain.getProgress() == 0) {
            boolean drained = BlockEntityUtil.drainTank(drainBucketUp, drainBucketDown, fluidOutputStorage, getStackHandler(), DRAIN_BUCKET_UP, DRAIN_BUCKET_DOWN);
            if (drained) {
                progressDrain.setProgress(1);
            }
        } else {
            progressDrain.setProgress(0);
        }

        if (progressDrain.changed()) {
            updateState = true;
        }

        if (cachedInputStack.getItem() != inputStack.getItem()) {
            cachedInputStack = inputStack.copy();
            if (inputStack.getItem() != Items.AIR && getRecipe(inputStack).isPresent()) {
                recipe = getRecipe(inputStack).get();
            } else {
                recipe = null;
            }
        }

        if (fluidInputStorage.getFluidAmount() >= 1000) {
            if (recipe != null) {
                if (progress.getProgress() == -1) {
                    progress.setData(0, recipe.getDuration());
                }

                progress.setProgressMax(getSpeedFactor() * recipe.getDuration());
                int energyCost = (int) (recipe.getPowerCost() * getEnergyUsageFactor());

                if (canWork(inputStack)) {
                    if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                        active = true;
                        progress.incProgress(1);
                        getEnergyStorage().consumeEnergy(energyCost, false);
                        getEnergyStorage().updateConsumed(energyCost);
                    }

                    if (progress.getProgress() >= progress.getProgressMax()) {
                        inputStack.shrink(1);
                        getStackHandler().setStackInSlot(INPUT_SLOT, inputStack.copy());
                        fluidInputStorage.drain(recipe.getFluidInput().getAmount(), IFluidHandler.FluidAction.EXECUTE);
                        fluidOutputStorage.fillFluid(recipe.getResult(), false);

                        progress.setBoth(-1);
                    }
                }
            }
        } else {
            progress.setBoth(-1);
        }

        if (progress.changed()) {
            updateState = true;
        }

        this.setActive(active);
        if (updateState) {
            this.updateBlockState();
        }
    }


    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.fluidInputStorage.readFromNBT(tag.getCompound("fluidInputStorage"));
        this.fluidOutputStorage.readFromNBT(tag.getCompound("fluidOutputStorage"));
        this.active = tag.getBoolean("active");
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.progress.deserializeNBT(tag.getCompound("progress"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("fluidInputStorage", this.fluidInputStorage.writeToNBT(tag.getCompound("fluidInputStorage")));
        tag.put("fluidOutputStorage", this.fluidOutputStorage.writeToNBT(tag.getCompound("fluidOutputStorage")));
        tag.putBoolean("active", active);
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("progress", this.progress.serializeNBT());
        return super.save(tag);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public float getExperience(Recipe<?> recipe) {
        return ((FluidEnrichingRecipe) recipe).getExperience();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY || (cap.getFluidInTank(1).getFluid() == fluidOutputStorage.getFluid().getFluid() && cap.getFluidInTank(1).getAmount() < cap.getTankCapacity(1));
            }
        }

        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT && !isValidInput(stack)) {
            return stack;
        }

        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                if (cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY || (cap.getFluidInTank(1).getFluid() == fluidOutputStorage.getFluid().getFluid() && cap.getFluidInTank(1).getAmount() < cap.getTankCapacity(1))) {
                    return null;
                }
            }
            return stack;
        }

        return super.insertItemForSlot(slot, stack, simulate);
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, DRAIN_BUCKET_UP + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), DRAIN_BUCKET_DOWN, DRAIN_BUCKET_DOWN + 1)),
            LazyOptional.of(() -> this.fluidInputStorage),
            LazyOptional.of(() -> this.fluidOutputStorage)
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (side == null) return LazyOptional.empty();

            if (getBlock() instanceof IStateFacing facing) {
                Direction dir = facing.getDirection(getBlockState());
                if (dir.getClockWise() == side) {
                    return capabilities.get(3).cast();
                }
                if (dir.getCounterClockWise() == side) {
                    return capabilities.get(4).cast();
                }
            }

            return LazyOptional.empty();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) return capabilities.get(0).cast();
            return switch (side) {
                case DOWN -> capabilities.get(2).cast();
                case UP, NORTH, SOUTH, WEST, EAST -> capabilities.get(1).cast();
            };
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onBreak() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreak();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.TRANSFORMER, UpgradeType.ENERGY_STORAGE, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_PULLING, UpgradeType.FLUID_EJECTOR);
    }
}
