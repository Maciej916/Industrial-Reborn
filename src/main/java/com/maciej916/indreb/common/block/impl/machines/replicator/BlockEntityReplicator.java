package com.maciej916.indreb.common.block.impl.machines.replicator;

import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResult;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.fluid.Matter;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketReplicatorPage;
import com.maciej916.indreb.common.network.packet.PacketReplicatorRepeatRun;
import com.maciej916.indreb.common.network.packet.PacketReplicatorSingleRun;
import com.maciej916.indreb.common.network.packet.PacketReplicatorStop;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.StackHandlerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.minecraft.core.Direction.DOWN;

public class BlockEntityReplicator extends IndRebBlockEntity implements IEnergyBlock, ISupportUpgrades, ITileSound {

    public static final int FILL_BUCKET_UP = 0;
    public static final int FILL_BUCKET_DOWN = 1;
    public static final int MEMORY_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    public BlockEntityProgress progress = new BlockEntityProgress();
    public BlockEntityProgress progressFill = new BlockEntityProgress(0, 1);
    protected ReplicatorMode mode = ReplicatorMode.WAITING;
    protected int currentModeTick = 0;
    protected int currentPattern = 0;
    public FluidStorage matterTank = new FluidStorage(ServerConfig.replicator_matter_capacity.get(), (fluidStack -> fluidStack.getFluid() == Matter.STILL_FLUID));
    protected ArrayList<BlockPos> patternStorageBlockPos = new ArrayList<>();
    protected ArrayList<BlockEntityPatternStorage> patternStorageEntity = new ArrayList<>();
    protected ArrayList<ScannerResult> scannerResultsCache = new ArrayList<>();
    protected ScannerResult result = new ScannerResult();

    public BlockEntityReplicator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.REPLICATOR.get(), pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.replicator_energy_capacity.get(), EnergyType.RECEIVE, EnergyTier.SUPER);

        newUpdateStateTempVar = true;
    }

    @Override
    public void tickWork(BlockState state) {
        final ItemStack memoryStack = getStackHandler().getStackInSlot(MEMORY_SLOT);

        boolean matterTankFilled = fillInternalTank(progressFill, FILL_BUCKET_UP, FILL_BUCKET_DOWN, matterTank);

        if (patternStorageBlockPos.size() != patternStorageEntity.size()) {
            ArrayList<BlockEntityPatternStorage> psBlockPos = new ArrayList<>();
            for (int i = patternStorageBlockPos.size() - 1; i >= 0; i--) {
                BlockPos pos = patternStorageBlockPos.get(i);
                if (level.getBlockEntity(pos) instanceof BlockEntityPatternStorage blockEntityPatternStorage) {
                    psBlockPos.add(blockEntityPatternStorage);
                } else {
                    patternStorageBlockPos.remove(i);
                }
            }

            patternStorageEntity = psBlockPos;
            scannerResultsCache = getStoredPatterns();
        }

        if (memoryStack.isEmpty() && patternStorageBlockPos.size() > 0) {
            setItemAndResult();
            shouldUpdateState = true;
        }

        if (result.getResultStack() != ItemStack.EMPTY) {
            if (isPatternValid()) {
                if (getEnergyStorage().energyStored() > 0 && (mode.getId() == 1 || mode.getId() == 2)) {
                    int matterCost = result.getMatterCost();
                    int energyCost = (int) (result.getEnergyCost() * getEnergyUsageFactor());
                    int duration = Math.max(matterCost, 1);

                    if (progress.getProgress() == -1) {
                        progress.setData(0, duration);
                    }

                    progress.setProgressMax(getSpeedFactor() * duration);

                    if (canWork()) {
                        int matterCostTick = Math.round(matterCost / progress.getProgressMax());
                        if (matterTank.getFluidAmount() >= matterCostTick && getEnergyStorage().energyStored() > 0) {
                            if ((result.getEnergyCost() == 0 || getEnergyStorage().consumeEnergy(energyCost, true) == energyCost) && progress.getProgress() <= progress.getProgressMax()) {
                                matterTank.drain(matterCostTick, IFluidHandler.FluidAction.EXECUTE);
                                activeState = true;
                                progress.incProgress(1);
                                getEnergyStorage().consumeEnergy(energyCost, false);
                                getEnergyStorage().updateConsumed(energyCost);
                            }

                            if (progress.getProgress() >= progress.getProgressMax()) {
                                StackHandlerHelper.incMachineOutputStack(getStackHandler(), OUTPUT_SLOT, result.getResultStack());
                                progress.setBoth(-1);

                                if (mode == ReplicatorMode.SINGLE_RUN) {
                                    mode = ReplicatorMode.WAITING;
                                }
                            }
                        }
                    }
                }
            } else {
                mode = ReplicatorMode.WAITING;
                currentPattern = 0;
                shouldUpdateState = true;
                progress.setBoth(-1);
                setItemAndResult();
            }
        }

        if (progress.changed() || matterTankFilled) {
            shouldUpdateState = true;
        }
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(MEMORY_SLOT, 68, 24, InventorySlotType.DISABLED, GuiSlotType.NORMAL_BLANK, 67, 34));
        slots.add(new IndRebSlot(OUTPUT_SLOT, 120, 23, InventorySlotType.INPUT, GuiSlotType.LARGE, 115, 18));
        slots.add(new IndRebSlot(FILL_BUCKET_UP, 8, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 7, 18));
        slots.add(new IndRebSlot(FILL_BUCKET_DOWN, 8, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 7, 49));

        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false));
        return super.addBatterySlot(slots);
    }

    @Override
    public boolean canReceiveEnergyDir(Direction side) {
        return true;
    }

    @Override
    public int customEnergyReceiveTick() {
        return 12288;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.REPLICATOR.get();
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == FILL_BUCKET_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Matter.STILL_FLUID;
            }
        }

        return false;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progress.deserializeNBT(tag.getCompound("progress"));
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.matterTank.readFromNBT(tag.getCompound("fluidMatterStorage"));

        ArrayList<BlockPos> psBlockPos = new ArrayList<>();
        ListTag tagList = tag.getList("psBlockPos", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            BlockPos bp = NbtUtils.readBlockPos(itemTags);
            psBlockPos.add(bp);
        }
        this.patternStorageBlockPos = psBlockPos;

        this.result.deserializeNBT(tag.getCompound("result"));
        this.mode = ReplicatorMode.getModeFromId(tag.getInt("mode"));
        this.currentModeTick = tag.getInt("currentModeTick");
        this.currentPattern = tag.getInt("currentPattern");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("progress", this.progress.serializeNBT());
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("fluidMatterStorage", this.matterTank.writeToNBT(tag.getCompound("fluidMatterStorage")));

        ListTag psBlockPos = new ListTag();
        for (BlockPos pos: patternStorageBlockPos) {
            psBlockPos.add(NbtUtils.writeBlockPos(pos));
        }
        tag.put("psBlockPos", psBlockPos);

        tag.put("result", this.result.serializeNBT());
        tag.putInt("mode", mode.getId());
        tag.putInt("currentModeTick", this.currentModeTick);
        tag.putInt("currentPattern", this.currentPattern);
        return super.save(tag);
    }

    private final ArrayList<LazyOptional<?>> capabilities = new ArrayList<>(Arrays.asList(
            LazyOptional.of(this::getStackHandler),
            LazyOptional.of(() -> this.matterTank),
            LazyOptional.of(() -> new RangedWrapper(getStackHandler(), OUTPUT_SLOT, OUTPUT_SLOT + 1))
    ));

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) return capabilities.get(0).cast();
            if (side == DOWN) return capabilities.get(2).cast();
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
        return List.of(UpgradeType.OVERCLOCKER, UpgradeType.FLUID_PULLING, UpgradeType.ENERGY_STORAGE, UpgradeType.TRANSFORMER, UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }

    public Runnable clientClickPrevPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorPage(getBlockPos(), false));
    }

    public Runnable clientClickNextPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorPage(getBlockPos(), true));
    }

    public Runnable clientClickStop() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorStop(getBlockPos()));
    }

    public Runnable clientClickSingleRun() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorSingleRun(getBlockPos()));
    }

    public Runnable clientClickRepeatRun() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorRepeatRun(getBlockPos()));
    }

    public void changePattern(boolean next) {
        if (scannerResultsCache.size() > 0) {
            if (next) {
                this.currentPattern = this.currentPattern + 1 >= this.scannerResultsCache.size() ? 0 : this.currentPattern + 1;
            } else {
                this.currentPattern = this.currentPattern - 1 < 0 ? this.scannerResultsCache.size() - 1 : this.currentPattern - 1;
            }

            progress.setBoth(-1);
            mode = ReplicatorMode.WAITING;

            setItemAndResult();
            updateBlockState();
        }
    }

    public void stopRun() {
        mode = ReplicatorMode.WAITING;
        updateBlockState();
    }

    public void singleRun() {
        if (canWork()) {
            mode = ReplicatorMode.SINGLE_RUN;
            updateBlockState();
        }
    }

    public void repeatRun() {
        if (canWork()) {
            mode = ReplicatorMode.REPEAT_RUN;
            updateBlockState();
        }
    }

    public void addPatternStorage(BlockPos pos, BlockEntityPatternStorage blockEntityPatternStorage) {
        if (!Arrays.asList(patternStorageBlockPos).contains(pos)) {
            patternStorageBlockPos.add(pos);
            patternStorageEntity.add(blockEntityPatternStorage);
            updatePatterns();
        }
    }

    public void removePatternStorage(BlockPos pos) {
        int index = patternStorageBlockPos.indexOf(pos);
        if (index != -1) {
            patternStorageBlockPos.remove(index);
            patternStorageEntity.remove(index);
            updatePatterns();
        }
    }

    public void updatePatterns() {
        this.scannerResultsCache = getStoredPatterns();
        updateBlockState();
    }

    private boolean isPatternValid() {
        return scannerResultsCache.contains(result);
    }

    private void setItemAndResult() {
        if (scannerResultsCache.size() > 0) {
            getStackHandler().setStackInSlot(MEMORY_SLOT, scannerResultsCache.get(currentPattern).getResultStack().copy());
            result = scannerResultsCache.get(currentPattern);
        } else {
            getStackHandler().setStackInSlot(MEMORY_SLOT, ItemStack.EMPTY);
            result = new ScannerResult();
        }
    }

    public ReplicatorMode getMode() {
        return mode;
    }

    //    public ItemStack getInputStack() {
//        return getStackHandler().getStackInSlot(MEMORY_SLOT);
//    }

//    public int getCurrentMode() {
//        return currentMode;
//    }

//    public ScannerResult getResult() {
//        return result;
//    }

    public ArrayList<ScannerResult> getStoredPatterns() {
        ArrayList<ScannerResult> results = new ArrayList<>();
        ArrayList<Item> resultsItem = new ArrayList<>();
        for (BlockEntityPatternStorage bePatternStorage: patternStorageEntity) {
            for (ScannerResult sc: bePatternStorage.getScannerResultMap()) {
                if (!resultsItem.contains(sc.getResultStack().getItem())) {
                    results.add(sc);
                    resultsItem.add(sc.getResultStack().getItem());
                }
            }
        }
        return results;
    }


    private boolean canWork() {
        return getStackHandler().getStackInSlot(OUTPUT_SLOT).isEmpty() || (getStackHandler().getStackInSlot(OUTPUT_SLOT).getCount() < getStackHandler().getStackInSlot(OUTPUT_SLOT).getMaxStackSize() &&  getStackHandler().getStackInSlot(OUTPUT_SLOT).getItem() == result.getResultStack().getItem());
    }

}