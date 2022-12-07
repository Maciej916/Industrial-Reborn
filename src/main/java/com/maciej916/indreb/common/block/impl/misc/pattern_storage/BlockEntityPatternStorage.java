package com.maciej916.indreb.common.block.impl.misc.pattern_storage;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.DisabledSlot;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.scanner.IScannerResult;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketPatternStorageAction;
import com.maciej916.indreb.common.network.packet.PacketPatternStoragePage;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BlockEntityPatternStorage extends IndRebBlockEntity {

    public static final int INPUT_SLOT = 0;
    public static final int MEMORY_SLOT = 1;

    public ArrayList<ScannerResult> scannerResultMap = new ArrayList<>();
    protected ScannerResult result = new ScannerResult();
    public int currentPattern = 0;
    public int patternsStored = 0;

    public BlockEntityPatternStorage(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PATTERN_STORAGE.get(), pos, blockState);

        this.containerData.syncStack(0, () ->  result.getResultStack());
        this.containerData.syncInt(1, () -> result.getMatterCost());
        this.containerData.syncInt(2, () -> result.getEnergyCost());
        this.containerData.syncInt(3, () -> currentPattern);
        this.containerData.syncInt(4, () -> patternsStored);
    }

    @Override
    public void tickWork() {
        if (scannerResultMap.size() > 0 && result.getResultStack() == ItemStack.EMPTY) {
            updateShowedPattern();
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuPatternStorage(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == INPUT_SLOT) return stack.getItem() == ModItems.MEMORY_CARD.get();
        return false;
    }

    @Override
    public int getBaseStorageSlotLimit(int slot) {
        return 1;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 138, 19, 137, 18, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        slots.add(new DisabledSlot(MEMORY_SLOT, 10, 21, 9, 20, GuiSlotBg.NORMAL_BLANK));

        return super.addBaseSlots(slots);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        ArrayList<ScannerResult> resultMap = new ArrayList<>();
        ListTag tagList = tag.getList("Results", Tag.TAG_COMPOUND);
        for (int i = 0; i < tag.getInt("Size"); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            ScannerResult scannerResult = new ScannerResult();
            scannerResult.deserializeNBT(itemTags);
            resultMap.add(scannerResult);
        }
        this.scannerResultMap = resultMap;
        this.currentPattern = tag.getInt("currentPattern");
        this.patternsStored = tag.getInt("patternsStored");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        ListTag nbtTagList = new ListTag();
        for (ScannerResult scannerResult : scannerResultMap) {
            nbtTagList.add(scannerResult.serializeNBT());
        }

        tag.put("Results", nbtTagList);
        tag.putInt("Size", scannerResultMap.size());
        tag.putInt("currentPattern", currentPattern);
        tag.putInt("patternsStored", patternsStored);
    }

    public ScannerResult getResult() {
        return result;
    }

    public void addScannerResult(ScannerResult scannerResult) {
        scannerResultMap.add(scannerResult);
        this.patternsStored = scannerResultMap.size();
        updatePatterns();
        setBlockUpdated();
        updateShowedPattern();
    }

    public int getPatternsStored() {
        return patternsStored;
    }

    public void setPatternsStored(int patternsStored) {
        this.patternsStored = patternsStored;
    }

    public int getCurrentPattern() {
        return currentPattern;
    }

    public void setCurrentPattern(int currentPattern) {
        this.currentPattern = currentPattern;
    }

    public void setResult(ScannerResult result) {
        this.result = result;
    }

    public Runnable clickPrevPatternClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStoragePage(getBlockPos(), false));
    }

    public Runnable clickNextPatternClient() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStoragePage(getBlockPos(), true));
    }

    public Runnable clientClickImportPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStorageAction(getBlockPos(), false));
    }

    public Runnable clientClickExportPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStorageAction(getBlockPos(), true));
    }

    public void updateShowedPattern() {
        if (scannerResultMap.size() == 0) {
            result = new ScannerResult();
        } else {
            result = scannerResultMap.get(this.currentPattern);
        }

        getBaseStorage().setStackInSlot(MEMORY_SLOT, result.getResultStack());
    }

    public boolean hasScannerResult(ItemStack stack) {
        for (ScannerResult result : scannerResultMap) {
            if (result.getResultStack().getItem() == stack.getItem()) return true;
        }
        return false;
    }

    public void changePattern(boolean next) {
        if (scannerResultMap.size() > 0) {
            if (next) {
                this.currentPattern = this.currentPattern + 1 >= this.scannerResultMap.size() ? 0 : this.currentPattern + 1;
            } else {
                this.currentPattern = this.currentPattern - 1 < 0 ? this.scannerResultMap.size() - 1 : this.currentPattern - 1;
            }

            updateShowedPattern();
            setBlockUpdated();
        }
    }

    public void exportImportPattern(boolean export) {
        final ItemStack memoryStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        boolean success = false;
        if (!memoryStack.isEmpty()) {
            LazyOptionalHelper<IScannerResult> cap = CapabilityUtil.getCapabilityHelper(memoryStack, ModCapabilities.SCANNER_RESULT);

            if (cap.isPresent()) {
                if (export) {
                    if (cap.getValue().getResult().getResultStack() == ItemStack.EMPTY) {
                        cap.getValue().setResult(scannerResultMap.get(currentPattern));
                        scannerResultMap.remove(currentPattern);

                        this.patternsStored = scannerResultMap.size();
                        this.currentPattern = this.patternsStored == 0 ? 0 : this.currentPattern >= this.patternsStored ? this.patternsStored - 1 : this.currentPattern;

                        success = true;
                    }
                } else {
                    if (cap.getValue().getResult().getResultStack() != ItemStack.EMPTY) {
                        if (!hasScannerResult(cap.getValue().getResult().getResultStack())) {
                            scannerResultMap.add(cap.getValue().getResult());
                            this.patternsStored = scannerResultMap.size();
                            cap.getValue().setResult(new ScannerResult());
                            success = true;
                        }
                    }
                }
            }
        }

        if (success) {
            updateShowedPattern();
            setBlockUpdated();
            updatePatterns();
        }
    }

    public void updatePatterns() {
        for (Direction direction : Constants.DIRECTIONS) {
            BlockPos offsetPos = getBlockPos().relative(direction);
            BlockEntity dirTile = getLevel().getBlockEntity(offsetPos);
//            if (dirTile instanceof BlockEntityReplicator bePatternStorage) {
//                bePatternStorage.updatePatterns();
//            }
        }
    }

}
