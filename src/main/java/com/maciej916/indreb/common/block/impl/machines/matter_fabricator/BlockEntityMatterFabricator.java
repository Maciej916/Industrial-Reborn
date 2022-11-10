package com.maciej916.indreb.common.block.impl.machines.matter_fabricator;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.fluid.Biogas;
import com.maciej916.indreb.common.fluid.Matter;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.recipe.impl.RecyclingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BlockEntityMatterFabricator extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound {

    private SoundEvent soundEventAmplified = ModSounds.MATTER_FABRICATOR_AMPLIFIED.get();
    private SoundInstance activeSoundAmplified;

    public static final int DRAIN_BUCKET_UP = 0;
    public static final int DRAIN_BUCKET_DOWN = 1;
    public static final int AMPLIFIER_SLOT = 2;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress progressDrain = new BlockEntityProgress(0, 1);
    public BlockEntityProgress progressAmplifier = new BlockEntityProgress(0, 0);

    private int cachedOutput = 0;
    private boolean active = false;
    public FluidStorage fluidMatterStorage = new FluidStorage(ServerConfig.matter_fabricator_matter_capacity.get(), (fluidStack -> fluidStack.getFluid() == Matter.STILL_FLUID));

    public BlockEntityMatterFabricator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MATTER_FABRICATOR.get(), pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.matter_fabricator_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.ADVANCED);
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getItem() == ModItems.SCRAP.get() || stack.getItem() == ModItems.SCRAP_BOX.get();
    }

    @Override
    public int customEnergyReceiveTick() {
        return 3072;
    }

    @Override
    public void tickWork(BlockState state) {
        active = false;
        boolean updateState = false;
        getEnergyStorage().updateConsumed(0);

        final ItemStack drainBucketUp = getStackHandler().getStackInSlot(DRAIN_BUCKET_UP);
        final ItemStack drainBucketDown = getStackHandler().getStackInSlot(DRAIN_BUCKET_DOWN);
        final ItemStack amplifierStack = getStackHandler().getStackInSlot(AMPLIFIER_SLOT);

        if (cachedOutput != fluidMatterStorage.getFluidAmount()) {
            cachedOutput = fluidMatterStorage.getFluidAmount();
            updateState = true;
        }

        if (progressDrain.getProgress() == 0) {
            boolean drained = BlockEntityUtil.drainTank(drainBucketUp, drainBucketDown, fluidMatterStorage, getStackHandler(), DRAIN_BUCKET_UP, DRAIN_BUCKET_DOWN);
            if (drained) {
                progressDrain.setProgress(1);
            }
        } else {
            progressDrain.setProgress(0);
        }

        if (progressDrain.changed()) {
            updateState = true;
        }

        if (getEnergyStorage().energyStored() > 0 && fluidMatterStorage.getFluidAmount() + ServerConfig.matter_fabricator_produce_run.get() <= fluidMatterStorage.getCapacity()) {
            if (progress.getProgress() == -1) {
                progress.setData(0, 1000000);
            }

            if (!amplifierStack.isEmpty() && progressAmplifier.getProgress() == 0) {
                progressAmplifier.setBoth(amplifierStack.getItem() == ModItems.SCRAP_BOX.get() ? 45000 : 5000);
                amplifierStack.shrink(1);
            }

            int maxEnergyCost = Math.min((int) progress.getProgressMax() - (int) progress.getProgress(), getEnergyStorage().energyStored());

            if (getEnergyStorage().consumeEnergy(maxEnergyCost, true) == maxEnergyCost && progress.getProgress() <= progress.getProgressMax()) {
                active = true;

                int amplifiedAmount = 0;
                if (progressAmplifier.getProgress() > 0) {
                    int amplified = Math.min(maxEnergyCost, (int) progressAmplifier.getProgress());
                    amplifiedAmount = amplified * 5;
                    progressAmplifier.decProgress(amplified);

                    if (progressAmplifier.getProgress() <= 0) {
                        progressAmplifier.setBoth(0);
                    }
                }

                progress.incProgress(maxEnergyCost + amplifiedAmount);

                getEnergyStorage().consumeEnergy(maxEnergyCost, false);
                getEnergyStorage().updateConsumed(maxEnergyCost);
            }

            if (progress.getProgress() >= progress.getProgressMax()) {
                fluidMatterStorage.fill(new FluidStack(Matter.STILL_FLUID, ServerConfig.matter_fabricator_produce_run.get()), IFluidHandler.FluidAction.EXECUTE);
                progress.setBoth(-1);
            }
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
    public void tickClient(BlockState state) {
        handleAmplifiedSound();
        super.tickClient(state);
    }

    @OnlyIn(Dist.CLIENT)
    private void handleAmplifiedSound() {
        if (progressAmplifier.getProgress() > 0) {
            if (canPlaySound() && !isRemoved()) {
                if (tickCounter > 0) {
                    return;
                }
                if (activeSoundAmplified == null || !Minecraft.getInstance().getSoundManager().isActive(activeSoundAmplified)) {
                    activeSoundAmplified = SoundHandler.startExtraSound(soundEventAmplified, SoundSource.BLOCKS, 1F, getBlockPos());
                }
            } else if (activeSoundAmplified != null) {
                SoundHandler.stopExtraSound(getBlockPos());
                activeSoundAmplified = null;
            }
        } else {
            if (getStackHandler().getStackInSlot(AMPLIFIER_SLOT).isEmpty()) {
                SoundHandler.stopExtraSound(getBlockPos());
                activeSoundAmplified = null;
            }
        }
    }

    @Override
    public void onBreakClient() {
        SoundHandler.stopExtraSound(getBlockPos());
        activeSoundAmplified = null;
        super.onBreakClient();
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(DRAIN_BUCKET_UP, 152, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 151, 18));
        slots.add(new IndRebSlot(DRAIN_BUCKET_DOWN, 152, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 151, 49));
        slots.add(new IndRebSlot(AMPLIFIER_SLOT, 83, 50, InventorySlotType.INPUT, GuiSlotType.NORMAL, 82, 49));

        return super.addInventorySlot(slots);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.MATTER_FABRICATOR.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == AMPLIFIER_SLOT) return isValidInput(stack);

        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY || (cap.getFluidInTank(1).getFluid() == fluidMatterStorage.getFluid().getFluid() && cap.getFluidInTank(1).getAmount() < cap.getTankCapacity(1));
            }
        }

        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == AMPLIFIER_SLOT && !isValidInput(stack)) return stack;

        if (slot == DRAIN_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                if (cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Fluids.EMPTY || (cap.getFluidInTank(1).getFluid() == fluidMatterStorage.getFluid().getFluid() && cap.getFluidInTank(1).getAmount() < cap.getTankCapacity(1))) {
                    return null;
                }
            }
            return stack;
        }

        return super.insertItemForSlot(slot, stack, simulate);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.progressAmplifier.deserializeNBT(tag.getCompound("progressAmplifier"));
        this.fluidMatterStorage.readFromNBT(tag.getCompound("fluidMatterStorage"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.put("progress", this.progress.serializeNBT());
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("progressAmplifier", this.progressAmplifier.serializeNBT());
        tag.put("fluidMatterStorage", this.fluidMatterStorage.writeToNBT(tag.getCompound("fluidMatterStorage")));

        return super.save(tag);
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> this.fluidMatterStorage)
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return capabilities.get(0).cast();
        }

        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return capabilities.get(1).cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onBreakServer() {
        for (LazyOptional<?> capability : capabilities) capability.invalidate();
        super.onBreakServer();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.TRANSFORMER, UpgradeType.FLUID_EJECTOR);
    }

    @Override
    public boolean showInGui() {
        return false;
    }
}
