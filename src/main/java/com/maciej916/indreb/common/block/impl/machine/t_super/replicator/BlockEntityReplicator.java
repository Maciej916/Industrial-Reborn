package com.maciej916.indreb.common.block.impl.machine.t_super.replicator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityFluid;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.api.energy.interfaces.IBlockEntityEnergy;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.fluid.FluidStorage;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.DisabledSlot;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.api.top.impl.ProbeInfoFluidBar;
import com.maciej916.indreb.common.api.util.ProgressFloat;
import com.maciej916.indreb.common.api.util.ProgressInt;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.enums.ReplicatorMode;
import com.maciej916.indreb.common.fluid.impl.Matter;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.network.packet.PacketReplicatorPage;
import com.maciej916.indreb.common.network.packet.PacketReplicatorRun;
import com.maciej916.indreb.common.network.packet.PacketReplicatorStop;
import com.maciej916.indreb.common.sound.ModSounds;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityReplicator extends IndRebBlockEntity implements IBlockEntityEnergy, IHasSound, IBlockEntityFluid {

    public static final int FILL_UP = 0;
    public static final int FILL_DOWN = 1;
    public static final int MEMORY_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    public ProgressFloat progressRecipe = new ProgressFloat();
    public ProgressInt progressFill = new ProgressInt(0, 1);
    protected int currentModeTick = 0;

    protected ArrayList<BlockPos> patternStorages = new ArrayList<>();
    protected ArrayList<ScannerResult> scannerResultMap = new ArrayList<>();

    protected ReplicatorMode mode = ReplicatorMode.WAITING;
    protected ScannerResult result = new ScannerResult();
    protected int currentPattern = 0;

    public FluidStorage firstTank = new FluidStorage(ServerConfig.replicator_matter_capacity.get(), (fluidStack -> fluidStack.getFluid() == Matter.STILL_FLUID)) {
        @Override
        public void updated() {
            setChanged();
            ModNetworking.sendToTrackingChunk(getLevel(), getBlockPos(), new PacketFluidSync(getStoredFluids(), getBlockPos()));
        }
    };

    public BlockEntityReplicator(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.REPLICATOR.get(), pos, blockState);
        createEnergyStorage(0, ServerConfig.replicator_matter_capacity.get(), EnergyType.RECEIVE, EnergyTier.SUPER);

        this.containerData.syncProgressFloat(0, this.progressRecipe);
        this.containerData.syncProgressInt(1, this.progressFill);
        this.containerData.syncInt(2, () -> mode.getId());
        this.containerData.syncInt(3, () -> currentModeTick);
        this.containerData.syncStack(4, () -> result.getResultStack());
        this.containerData.syncInt(5, () -> result.getMatterCost());
        this.containerData.syncInt(6, () -> result.getEnergyCost());
    }

    @Override
    public void tickWork() {
        BlockEntityUtil.fillTank(progressFill, firstTank, getBaseStorage(), FILL_UP, FILL_DOWN);

        if (scannerResultMap.size() == 0 && patternStorages.size() > 0) {
            updatePatterns();
        }

        if (result.getResultStack() != ItemStack.EMPTY) {
            if (isPatternValid()) {
                if (getEnergyStorage().energyStored() > 0 && (mode.getId() == 1 || mode.getId() == 2)) {
                    int matterCost = result.getMatterCost();
                    int energyCost = result.getEnergyCost();
                    int duration = Math.max(matterCost, 1);

                    if (progressRecipe.getCurrentProgress() == -1) {
                        progressRecipe.setData(0, duration);
                    }

                    if (canWork()) {
                        int matterCostTick = Math.round(matterCost / progressRecipe.getProgressMax());
                        if (firstTank.takeFluid(matterCostTick, true) == matterCostTick && getEnergyStorage().energyStored() > 0) {
                            if ((result.getEnergyCost() == 0 || getEnergyStorage().consumeEnergy(energyCost, true) == energyCost) && progressRecipe.getCurrentProgress() <= progressRecipe.getProgressMax()) {
                                firstTank.takeFluid(matterCostTick, false);
                                activeState = true;
                                progressRecipe.incProgress(1);
                                getEnergyStorage().consumeEnergy(energyCost, false);
                                getEnergyStorage().updateConsumed(energyCost);
                            }

                            if (progressRecipe.isCurrentAboveEqualMax()) {
                                StackHandlerHelper.addOutputStack(getBaseStorage(), OUTPUT_SLOT, result.getResultStack());
                                progressRecipe.resetProgress();

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
                progressRecipe.resetProgress();
                updatePatterns();
            }
        }

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuReplicator(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == FILL_UP) {
            IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, ForgeCapabilities.FLUID_HANDLER_ITEM).getValue();
            if (cap != null) {
                return cap.getTanks() > 0 && cap.getFluidInTank(1).getFluid() == Matter.STILL_FLUID;
            }
        }
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(FILL_UP, 8, 19, 7, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new BaseSlot(FILL_DOWN, 8, 50, 7, 49, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new DisabledSlot(MEMORY_SLOT, 68, 24, 67, 34, GuiSlotBg.NORMAL_BLANK));
        slots.add(new OutputSlot(OUTPUT_SLOT, 120, 23, 115, 18, GuiSlotBg.LARGE));

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
        this.result.deserializeNBT(tag.getCompound("result"));
        this.mode = ReplicatorMode.getModeFromId(tag.getInt("mode"));
        this.currentModeTick = tag.getInt("currentModeTick");
        this.currentPattern = tag.getInt("currentPattern");
        this.progressFill.deserializeNBT(tag.getCompound("progressFill"));
        this.firstTank.readFromNBT(tag.getCompound("firstTank"));

        ArrayList<BlockPos> psBlockPos = new ArrayList<>();
        ListTag tagList = tag.getList("patternStorages", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            BlockPos bp = NbtUtils.readBlockPos(itemTags);
            psBlockPos.add(bp);
        }
        this.patternStorages = psBlockPos;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("progressRecipe", this.progressRecipe.serializeNBT());
        tag.put("result", this.result.serializeNBT());
        tag.putInt("mode", mode.getId());
        tag.putInt("currentModeTick", this.currentModeTick);
        tag.putInt("currentPattern", this.currentPattern);
        tag.put("progressFill", this.progressFill.serializeNBT());
        tag.put("firstTank", this.firstTank.writeToNBT(tag.getCompound("firstTank")));

        ListTag psBlockPos = new ListTag();
        for (BlockPos pos: patternStorages) {
            psBlockPos.add(NbtUtils.writeBlockPos(pos));
        }
        tag.put("patternStorages", psBlockPos);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.REPLICATOR.get();
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

    public void updateShowedPattern() {
        if (scannerResultMap.size() == 0) {
            result = new ScannerResult();
        } else {
            result = scannerResultMap.get(this.currentPattern);
        }

        getBaseStorage().setStackInSlot(MEMORY_SLOT, result.getResultStack());
    }

    public int hasScannerResult(ItemStack stack) {
        int i = 0;
        for (ScannerResult result : scannerResultMap) {
            if (result.getResultStack().getItem() == stack.getItem()) {
                return i;
            }
            i++;
        }
        return -1;
    }


    public void getStoredPatterns() {
        ArrayList<ScannerResult> results = new ArrayList<>();
        ArrayList<Item> resultsItem = new ArrayList<>();

        int oldResults = scannerResultMap.size();

        for (BlockPos patternStoragePos: patternStorages) {
            BlockEntity entity = level.getBlockEntity(patternStoragePos);
            if (entity instanceof BlockEntityPatternStorage entityPatternStorage) {
                for (ScannerResult res : entityPatternStorage.getScannerResultMap()) {
                    if (!resultsItem.contains(res.getResultStack().getItem())) {
                        results.add(res);
                        resultsItem.add(res.getResultStack().getItem());
                    }
                }
            }
        }

        this.scannerResultMap = results;

        if (oldResults != results.size()) {
            int has = hasScannerResult(result.getResultStack());

            System.out.println(has);

            if (has == -1) {
                this.progressRecipe.resetProgress();
                this.mode = ReplicatorMode.WAITING;
                this.currentPattern = 0;
            } else {
                this.currentPattern = has;
            }
        }
    }

    public void updatePatterns() {
        getStoredPatterns();
        updateShowedPattern();
        setBlockUpdated();
    }

    public void addPatternStorage(BlockPos pos) {
        if (!patternStorages.contains(pos)) {
            this.patternStorages.add(pos);
            updatePatterns();
        }
    }

    public void removePatternStorage(BlockPos pos) {
        if (patternStorages.contains(pos)) {
            this.patternStorages.remove(pos);
            updatePatterns();
        }
    }

    public Runnable clickPrevPatternClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorPage(getBlockPos(), false));
    }

    public Runnable clickNextPatternClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorPage(getBlockPos(), true));
    }

    public Runnable clientClickStop() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorStop(getBlockPos()));
    }

    public Runnable clientClickRun(boolean single) {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketReplicatorRun(getBlockPos(), single));
    }

    public void changePattern(boolean next) {
        if (scannerResultMap.size() > 0) {
            if (next) {
                this.currentPattern = this.currentPattern + 1 >= this.scannerResultMap.size() ? 0 : this.currentPattern + 1;
            } else {
                this.currentPattern = this.currentPattern - 1 < 0 ? this.scannerResultMap.size() - 1 : this.currentPattern - 1;
            }

            this.progressRecipe.resetProgress();
            this.mode = ReplicatorMode.WAITING;

            updateShowedPattern();
            setBlockUpdated();
        }
    }

    public ReplicatorMode getMode() {
        return mode;
    }

    public void setMode(ReplicatorMode mode) {
        this.mode = mode;
    }

    public int getCurrentModeTick() {
        return currentModeTick;
    }

    public void setCurrentModeTick(int currentModeTick) {
        this.currentModeTick = currentModeTick;
    }

    public ScannerResult getResult() {
        return result;
    }

    public void setResult(ScannerResult result) {
        this.result = result;
    }

    private boolean canWork() {
        final ItemStack outputStack = getBaseStorage().getStackInSlot(OUTPUT_SLOT);

        return outputStack.isEmpty() || (outputStack.getCount() + result.getResultStack().getCount() <= outputStack.getMaxStackSize() && result.getResultStack().getItem() == outputStack.getItem());
    }

    public void stopRun() {
        mode = ReplicatorMode.WAITING;
        setBlockUpdated();
    }

    public void runReplication(boolean single) {
        if (canWork()) {
            mode = single ? ReplicatorMode.SINGLE_RUN : ReplicatorMode.REPEAT_RUN;
            setBlockUpdated();
        }
    }

    private boolean isPatternValid() {
        return scannerResultMap.contains(result);
    }

}
