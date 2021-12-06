package com.maciej916.indreb.common.block.impl.generators.geo_generator;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.*;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.Config;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maciej916.indreb.common.enums.EnumEnergyType.EXTRACT;

public class BlockEntityGeoGenerator extends IndRebBlockEntity implements ICooldown, IEnergyBlock, ITileSound {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private boolean active = false;
    public FluidStorage lavaStorage = new FluidStorage(ServerConfig.geo_generator_lava_capacity.get());
    public BlockEntityProgress fill = new BlockEntityProgress(0, 1);
    ItemStack inputStackCache = getStackHandler().getStackInSlot(INPUT_SLOT);
    ItemStack outputStackCache = getStackHandler().getStackInSlot(OUTPUT_SLOT);

    public BlockEntityGeoGenerator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GEO_GENERATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.geo_generator_energy_capacity.get(), 0, ServerConfig.basic_tier_transfer.get(), EXTRACT);
        lavaStorage.setFluid(new FluidStack(Fluids.LAVA, 0));
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 48, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 47, 34));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 108, 35, InventorySlotType.OUTPUT, GuiSlotType.LARGE, 103, 30));
        return super.addInventorySlot(slots);
    }

    @Override
    public void tickServer(BlockState state) {
        active = false;
        boolean updateState = false;

        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack outputStack = getStackHandler().getStackInSlot(OUTPUT_SLOT);

        if (!inputStack.isEmpty() && inputStack.getItem() instanceof BucketItem bucketItem && outputStack.isEmpty()) {
            FluidStack lava = new FluidStack(bucketItem.getFluid(), 1000);
            if (this.lavaStorage.fillFluid(lava, true) == 1000) {
                this.lavaStorage.fillFluid(lava, false);
                inputStack.shrink(1);
                getStackHandler().setStackInSlot(OUTPUT_SLOT, new ItemStack(Items.BUCKET));
                fill.setProgress(1);
            }
        } else {
            fill.setProgress(0);
        }

        if (inputStack != inputStackCache) {
            updateState = true;
            inputStackCache = getStackHandler().getStackInSlot(INPUT_SLOT);
        }

        if (outputStack != outputStackCache) {
            updateState = true;
            outputStackCache = getStackHandler().getStackInSlot(OUTPUT_SLOT);
        }

        if (this.getCooldown() == 0) {
            if (getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) == ServerConfig.geo_generator_tick_generate.get() && lavaStorage.takeFluid(1, true) == 1) {
                lavaStorage.takeFluid(1, false);
                getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), false);
                active = true;
                updateState = true;
            }

            if (active && getEnergyStorage().generateEnergy(ServerConfig.geo_generator_tick_generate.get(), true) < ServerConfig.generator_tick_generate.get() && lavaStorage.takeFluid(1, true) == 1) {
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

        super.tickServer(state);
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return !stack.isEmpty() && stack.getItem().equals(Items.LAVA_BUCKET);
        if (slot == OUTPUT_SLOT) return false;
        return super.isItemValidForSlot(slot, stack);
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {

        if (slot == INPUT_SLOT && !stack.getItem().equals(Items.LAVA_BUCKET)) {
            return stack;
        }

        if (slot == OUTPUT_SLOT) return stack;

        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.lavaStorage.readFromNBT(tag.getCompound("lava_storage"));
        this.active = tag.getBoolean("active");
        this.fill.deserializeNBT(tag.getCompound("fill"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("lava_storage", this.lavaStorage.writeToNBT(tag.getCompound("lava_storage")));
        tag.putBoolean("active", active);
        tag.put("fill", this.fill.serializeNBT());
        return super.save(tag);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, true, List.of(EnergyTier.BASIC)));
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
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, INPUT_SLOT + 1)),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1)),
            LazyOptional.of(() -> this.lavaStorage)
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
