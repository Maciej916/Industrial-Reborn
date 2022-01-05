package com.maciej916.indreb.common.block.impl.generators.crystalline_generator;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class BlockEntityCrystallineGenerator extends IndRebBlockEntity implements ICooldown, IEnergyBlock, ITileSound {

    public static final int INPUT_SLOT = 0;

    private boolean active = false;
    public BlockEntityProgress progressBurn = new BlockEntityProgress();

    public BlockEntityCrystallineGenerator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CRYSTALLINE_GENERATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.generator_energy_capacity.get(), EnergyType.EXTRACT, EnergyTier.BASIC);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 80, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 79, 34));
        return super.addInventorySlot(slots);
    }


    @Override
    public void tickOperate(BlockState state) {
        progressBurn.clearChanged();
        active = false;

        if (this.getCooldown() == 0) {
            final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);

            if (getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), true) == ServerConfig.generator_tick_generate.get()) {
                if (progressBurn.getProgress() > 0) {
                    active = true;
                    progressBurn.decProgress(1);
                    getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), false);
                } else {
                    active = false;
                    progressBurn.setBoth(-1);

                    if (!inputStack.isEmpty()) {
                        final int burnTime = ForgeHooks.getBurnTime(inputStack, RecipeType.SMELTING);
                        if (burnTime > 0) {
                            progressBurn.setBoth(burnTime);
                            inputStack.shrink(1);
                            active = true;
                        }
                    }
                }
            }

            if (active && getEnergyStorage().generateEnergy(ServerConfig.generator_tick_generate.get(), true) < ServerConfig.generator_tick_generate.get() && progressBurn.getProgress() > 0) {
                this.setCooldown(10);
            }
        }

        this.setActive(active);

        if (progressBurn.changed()) {
            super.updateBlockState();
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0 && !stack.getItem().equals(Items.LAVA_BUCKET);
        return super.isItemValidForSlot(slot, stack);
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {

        if (slot == INPUT_SLOT &&  ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0 || stack.getItem().equals(Items.LAVA_BUCKET)) {
            return stack;
        }

        return super.insertItemForSlot(slot, stack, simulate);
    }


    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressBurn.deserializeNBT(tag.getCompound("progress"));
        this.active = tag.getBoolean("active");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("progress", this.progressBurn.serializeNBT());
        tag.putBoolean("active", active);
        return super.save(tag);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, true));
        return super.addBatterySlot(slots);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.GENERATOR;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        return true;
    }

    ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), INPUT_SLOT, INPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return capabilities.get(0).cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void onBreak() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreak();
    }
}
