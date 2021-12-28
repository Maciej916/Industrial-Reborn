package com.maciej916.indreb.common.block.impl.machines.fermenter;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.fluids.Biogas;
import com.maciej916.indreb.common.fluids.Biomass;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
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

import static com.maciej916.indreb.common.enums.EnumEnergyType.RECEIVE;

public class BlockEntityFermenter extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades {

    public static final int FILL_BUCKET_UP = 0;
    public static final int FILL_BUCKET_DOWN = 2;

    public static final int WASTE_SLOT = 3;

    public static final int DRAIN_BUCKET_UP = 1;
    public static final int DRAIN_BUCKET_DOWN = 4;

    private boolean active = false;
    public FluidStorage fluidInputStorage = new FluidStorage(ServerConfig.fermenter_biomass_capacity.get(), (fluidStack -> fluidStack.getFluid() == Biomass.STILL_FLUID));
    public FluidStorage fluidOutputStorage = new FluidStorage(ServerConfig.fermenter_biogas_capacity.get());
    private int cachedInput = 0;
    private int cachedOutput = 0;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress progressWaste = new BlockEntityProgress();
    public BlockEntityProgress heatLevel = new BlockEntityProgress(0, 100);

    public BlockEntityProgress progressFill = new BlockEntityProgress(0, 1);
    public BlockEntityProgress progressDrain = new BlockEntityProgress(0, 1);

    public BlockEntityFermenter(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FERMENTER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.fermenter_energy_capacity.get(), ServerConfig.standard_tier_transfer.get(), 0, RECEIVE);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(FILL_BUCKET_UP, 13, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 12, 18));
        slots.add(new IndRebSlot(FILL_BUCKET_DOWN, 13, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 12, 49));

        slots.add(new IndRebSlot(WASTE_SLOT, 51, 73, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 50, 72));

        slots.add(new IndRebSlot(DRAIN_BUCKET_UP, 127, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 126, 18));
        slots.add(new IndRebSlot(DRAIN_BUCKET_DOWN, 127, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 126, 49));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false, List.of(EnergyTier.STANDARD)));
        return super.addBatterySlot(slots);
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        boolean updateState = false;

        final ItemStack fillBucketUp = getStackHandler().getStackInSlot(FILL_BUCKET_UP);
        final ItemStack fillBucketDown = getStackHandler().getStackInSlot(FILL_BUCKET_DOWN);

        final ItemStack drainBucketUp = getStackHandler().getStackInSlot(DRAIN_BUCKET_UP);
        final ItemStack drainBucketDown = getStackHandler().getStackInSlot(DRAIN_BUCKET_DOWN);

        final ItemStack wasteStack = getStackHandler().getStackInSlot(WASTE_SLOT);

        if (progressFill.getProgress() == 0) {
            boolean filled = BlockEntityUtil.fillTank(fillBucketUp, fillBucketDown, fluidInputStorage, getStackHandler(), FILL_BUCKET_DOWN);
            if (filled) {
                progressFill.setProgress(1);
            }
        } else {
            progressFill.setProgress(0);
        }

        if (progressFill.changed()) {
            updateState = true;
        }

        if (cachedInput != fluidInputStorage.getFluidAmount()) {
            cachedInput = fluidInputStorage.getFluidAmount();
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

        if (cachedOutput != fluidOutputStorage.getFluidAmount()) {
            cachedOutput = fluidOutputStorage.getFluidAmount();
            updateState = true;
        }

        float progressSpeed = getSpeedFactor() * (1 + (heatLevel.getPercentProgress() / 100f));
        int energyCost = (int) (ServerConfig.fermenter_tick_usage.get() * getEnergyUsageFactor());

        if (fluidInputStorage.getFluidAmount() >= 1000 && fluidOutputStorage.getFluidAmount() + 200 <= fluidOutputStorage.getCapacity() && wasteStack.getCount() < wasteStack.getMaxStackSize()) {
            if (progress.getProgress() == -1) {
                progress.setData(0, 600);
            }

            if (progressWaste.getProgress() == -1) {
                progressWaste.setData(0, 1400);
            }

            if (getEnergyStorage().consumeEnergy(energyCost, true) == energyCost && progress.getProgress() <= progress.getProgressMax()) {
                active = true;
                progress.incProgress(progressSpeed);
                progressWaste.incProgress(progressSpeed);

                getEnergyStorage().consumeEnergy(energyCost, false);
            }

            if (progress.getProgress() >= progress.getProgressMax()) {
                fluidInputStorage.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                fluidOutputStorage.fill(new FluidStack(Biogas.STILL_FLUID, 200), IFluidHandler.FluidAction.EXECUTE);

                progress.setBoth(-1);
            }

            if (progressWaste.getProgress() >= progressWaste.getProgressMax()) {
                if (wasteStack.isEmpty()) {
                    getStackHandler().setStackInSlot(WASTE_SLOT, new ItemStack(ModItems.FERTILIZER));
                } else {
                    wasteStack.grow(1);
                }
                progressWaste.setBoth(-1);
            }
        }

        if (progress.changed()) {
            updateState = true;
        }

        if (progressWaste.changed()) {
            updateState = true;
        }

        if ((getRedstonePower() > 0 && getEnergyStorage().consumeEnergy(ServerConfig.fermenter_heat_cost.get(), true) >= ServerConfig.fermenter_heat_cost.get() || active)) {
            if (heatLevel.getProgress() < 100) {
                if (tickCounter == 20) {
                    heatLevel.incProgress(0.2f);
                    if (!active) {
                        getEnergyStorage().consumeEnergy(ServerConfig.fermenter_heat_cost.get(), false);
                    }
                }
            }
        } else {
            if (heatLevel.getProgress() > 0) {
                if (tickCounter == 20) {
                    heatLevel.decProgress(Math.min(heatLevel.getProgress(), 1));
                }
            }
        }

        if (heatLevel.changed()) {
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
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.active = tag.getBoolean("active");
        this.heatLevel.deserializeNBT(tag.getCompound("heatLevel"));
        this.progressWaste.deserializeNBT(tag.getCompound("progressWaste"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("fluidInputStorage", this.fluidInputStorage.writeToNBT(tag.getCompound("fluidInputStorage")));
        tag.put("fluidOutputStorage", this.fluidOutputStorage.writeToNBT(tag.getCompound("fluidOutputStorage")));
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("progress", this.progress.serializeNBT());
        tag.putBoolean("active", active);
        tag.put("heatLevel", this.heatLevel.serializeNBT());
        tag.put("progressWaste", this.progressWaste.serializeNBT());
        return super.save(tag);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Biomass.STILL_FLUID;
            }
        }

        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY;
            }
        }

        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                if (cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Biomass.STILL_FLUID) {
                    return null;
                }
            }
            return stack;
        }

        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                if (cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY) {
                    return null;
                }
            }
            return stack;
        }

        return super.insertItemForSlot(slot, stack, simulate);
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), FILL_BUCKET_UP, DRAIN_BUCKET_UP + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), FILL_BUCKET_DOWN, DRAIN_BUCKET_DOWN + 1)),
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
                default -> capabilities.get(0).cast();
            };
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onBreak() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreak();
    }
}