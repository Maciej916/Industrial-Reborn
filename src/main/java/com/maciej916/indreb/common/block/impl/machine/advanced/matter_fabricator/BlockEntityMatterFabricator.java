package com.maciej916.indreb.common.block.impl.machine.advanced.matter_fabricator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasUpgrades;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.*;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.recipe.interfaces.IBaseRecipe;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoFluidBar;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.fluid.impl.Matter;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.MatterAmplifierRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BlockEntityMatterFabricator extends IndRebBlockEntity implements IHasUpgrades, IBlockEntityEnergy, IBlockEntityFluid, IHasSound {

    private final SoundEvent SOUND_EVENT_AMPLIFIED = ModSounds.MATTER_FABRICATOR_AMPLIFIED.get();
    private SoundInstance activeSoundInstance;

    public static final int DRAIN_UP = 0;
    public static final int DRAIN_DOWN = 1;
    public static final int AMPLIFIER_SLOT = 2;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressInt progressDrain = new ProgressInt(0, 1);
    public ProgressInt progressAmplifier = new ProgressInt(0, 0);

    public FluidStorage firstTank = new FluidStorage(ServerConfig.matter_fabricator_matter_capacity.get(), (fluidStack -> fluidStack.getFluid() == Matter.STILL_FLUID)) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityMatterFabricator(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MATTER_FABRICATOR.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.matter_fabricator_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.ADVANCED);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressInt(1, this.progressDrain);
        this.containerData.syncProgressInt(2, this.progressAmplifier);
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.drainTank(progressDrain, firstTank, getBaseStorage(), DRAIN_UP, DRAIN_DOWN);

        ItemStack amplifierStack = getBaseStorage().getStackInSlot(AMPLIFIER_SLOT);

        if (getEnergyStorage().energyStored() > 0 && firstTank.getFluidAmount() + ServerConfig.matter_fabricator_produce_run.get() <= firstTank.getCapacity()) {
            if (progressRecipe.getCurrentProgress() == -1) {
                progressRecipe.setData(0, 1000000);
            }

            if (!amplifierStack.isEmpty() && progressAmplifier.getCurrentProgress() == 0) {
                Optional<MatterAmplifierRecipe> recipe = getRecipe(amplifierStack);
                if (recipe.isPresent()) {
                    progressAmplifier.setBoth(recipe.get().getAmplifiedAmount());
                    amplifierStack.shrink(1);
                }
            }

            int maxEnergyCost = Math.min((int) progressRecipe.getProgressMax() - (int) progressRecipe.getCurrentProgress(), getEnergyStorage().energyStored());

            if (getEnergyStorage().consumeEnergy(maxEnergyCost, true) == maxEnergyCost && progressRecipe.getCurrentProgress() <= progressRecipe.getProgressMax()) {
                activeState = true;
                setChanged();

                int amplifiedAmount = 0;
                if (progressAmplifier.getCurrentProgress() > 0) {
                    int amplified = Math.min(maxEnergyCost, (int) progressAmplifier.getCurrentProgress());
                    amplifiedAmount = amplified * 5;
                    progressAmplifier.decProgress(amplified);

                    if (progressAmplifier.getCurrentProgress() <= 0) {
                        progressAmplifier.setBoth(0);
                    }
                }

                progressRecipe.incProgress(maxEnergyCost + amplifiedAmount);

                getEnergyStorage().consumeEnergy(maxEnergyCost, false);
                getEnergyStorage().updateConsumed(maxEnergyCost);
            }

            if (progressRecipe.isCurrentAboveEqualMax()) {
                firstTank.fillFluid(new FluidStack(Matter.STILL_FLUID, ServerConfig.matter_fabricator_produce_run.get()), false);
                progressRecipe.resetProgress();
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuMatterFabricator(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == AMPLIFIER_SLOT) return getRecipe(stack).isPresent();

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
        slots.add(new BaseSlot(DRAIN_UP, 152, 19, 151, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new OutputSlot(DRAIN_DOWN,  152, 50, 151, 49, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(AMPLIFIER_SLOT, 83, 50, 82, 49, InventorySlotType.INPUT, GuiSlotBg.NORMAL));

        return super.addBaseSlots(slots);
    }

    @Override
    public boolean canReceiveEnergyCustom(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progressRecipe.deserializeNBT(tag.getCompound("progressRecipe"));
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
        this.progressAmplifier.deserializeNBT(tag.getCompound("progressAmplifier"));
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        tag.put("progressAmplifier", this.progressAmplifier.serializeNBT());
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
            Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(getBaseStorage(), (i) -> false, (i, stack) -> getBaseStorage().isItemValid(i, stack))),
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

        if (level.isClientSide()) {
            SoundHandler.stopExtraSound(getBlockPos());
            activeSoundInstance = null;
        }

        for (LazyOptional<?> capability : baseCapabilities) capability.invalidate();
        for (LazyOptional<?> capability : itemCapabilities.values()) capability.invalidate();
    }

    @Override
    public List<UpgradeType> getSupportedUpgrades() {
        return List.of(UpgradeType.TRANSFORMER, UpgradeType.EJECTOR, UpgradeType.PULLING, UpgradeType.REDSTONE_SIGNAL_INVERTER, UpgradeType.FLUID_EJECTOR);
    }

    @Override
    public int getUpgradesSlots() {
        return 2;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.MATTER_FABRICATOR.get();
    }

    @Override
    public List<FluidStack> getStoredFluids() {
        return List.of(firstTank.getFluid());
    }

    @Override
    public void setStoredFluids(List<FluidStack> fluids) {
        this.firstTank.setFluid(fluids.get(0));
    }

    @Override
    public List<BaseOneProbeInfo> addProbeInfo(List<BaseOneProbeInfo> oneProbeInfo) {
        super.addProbeInfo(oneProbeInfo);

        oneProbeInfo.add(new ProbeInfoFluidBar(firstTank));

        return oneProbeInfo;
    }

    @Override
    public void tickClient() {
        super.tickClient();

        if (progressAmplifier.getCurrentProgress() > 0) {
            if (canPlaySound() && !isRemoved()) {
                if (getTickCounter() > 0) {
                    return;
                }
                if (activeSoundInstance == null || !Minecraft.getInstance().getSoundManager().isActive(activeSoundInstance)) {
                    activeSoundInstance = SoundHandler.startExtraSound(SOUND_EVENT_AMPLIFIED, SoundSource.BLOCKS, 1F, getBlockPos());
                }
            } else if (activeSoundInstance != null) {
                SoundHandler.stopExtraSound(getBlockPos());
                activeSoundInstance = null;
            }
        } else {
            if (getBaseStorage().getStackInSlot(AMPLIFIER_SLOT).isEmpty()) {
                SoundHandler.stopExtraSound(getBlockPos());
                activeSoundInstance = null;
            }
        }
    }

    @Override
    public boolean showBarInGui() {
        return false;
    }

    protected Optional<MatterAmplifierRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.MATTER_AMPLIFIER.get(), new SimpleContainer(input), level);
    }

}
