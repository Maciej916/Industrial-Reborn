package com.maciej916.indreb.common.block.impl.generators.geo_generator;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class BlockEntityGeoGenerator extends IndRebBlockEntity implements ICooldown, IEnergyBlock, ITileSound {

    public static final int FILL_BUCKET_UP = 0;
    public static final int FILL_BUCKET_DOWN = 1;
    public BlockEntityProgress progressFill = new BlockEntityProgress(0, 1);

    private boolean active = false;
    public FluidStorage fluidStorage = new FluidStorage(ServerConfig.geo_generator_lava_capacity.get(), (fluidStack -> fluidStack.getFluid() == Fluids.LAVA));
    private int cachedLava = 0;

    public BlockEntityGeoGenerator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GEO_GENERATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.geo_generator_energy_capacity.get(), EnergyType.EXTRACT, EnergyTier.BASIC);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(FILL_BUCKET_UP, 61, 20, InventorySlotType.INPUT, GuiSlotType.NORMAL, 60, 19));
        slots.add(new IndRebSlot(FILL_BUCKET_DOWN, 61, 51, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 60, 50));
        return super.addInventorySlot(slots);
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        boolean updateState = false;
        getEnergyStorage().updateGenerated(0);

        final ItemStack fillBucketUp = getStackHandler().getStackInSlot(FILL_BUCKET_UP);
        final ItemStack fillBucketDown = getStackHandler().getStackInSlot(FILL_BUCKET_DOWN);

        if (progressFill.getProgress() == 0) {
            boolean filled = BlockEntityUtil.fillTank(fillBucketUp, fillBucketDown, fluidStorage, getStackHandler(), FILL_BUCKET_DOWN);
            if (filled) {
                progressFill.setProgress(1);
            }
        } else {
            progressFill.setProgress(0);
        }

        if (progressFill.changed()) {
            updateState = true;
        }

        if (cachedLava != fluidStorage.getFluidAmount()) {
            cachedLava = fluidStorage.getFluidAmount();
            updateState = true;
        }

        if (this.getCooldown() == 0) {
            if (getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) == ServerConfig.geo_generator_tick_generate.get() && fluidStorage.takeFluid(1, true) == 1) {
                fluidStorage.takeFluid(1, false);
                getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), false);
                getEnergyStorage().updateGenerated(ServerConfig.geo_generator_tick_generate.get());
                active = true;
                updateState = true;
            }

            if (active && getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) < ServerConfig.geo_generator_tick_generate.get() && fluidStorage.takeFluid(1, true) == 1) {
                this.setCooldown(10);
            }
        }

        if (getActive() != active) {
            this.setActive(active);
            updateState = true;
        }

        if (updateState) {
            this.updateBlockState();
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.LAVA;
            }
        }
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                if (cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.LAVA) {
                    return null;
                }
            }
            return stack;
        }
        if (slot == FILL_BUCKET_DOWN) return stack;

        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.fluidStorage.readFromNBT(tag.getCompound("fluidStorage"));
        this.active = tag.getBoolean("active");
        this.progressFill.deserializeNBT(tag.getCompound("fill"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("fluidStorage", this.fluidStorage.writeToNBT(tag.getCompound("fluidStorage")));
        tag.putBoolean("active", active);
        tag.put("fill", this.progressFill.serializeNBT());
        return super.save(tag);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, true));
        return super.addBatterySlot(slots);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.GEO_GENERATOR;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        return true;
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), FILL_BUCKET_UP, FILL_BUCKET_UP + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), FILL_BUCKET_DOWN, FILL_BUCKET_DOWN + 1)),
            LazyOptional.of(() -> this.fluidStorage)
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return capabilities.get(3).cast();
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
}
