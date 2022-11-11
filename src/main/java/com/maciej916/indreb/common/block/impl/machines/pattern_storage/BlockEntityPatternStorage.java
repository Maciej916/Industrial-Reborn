package com.maciej916.indreb.common.block.impl.machines.pattern_storage;

import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.capabilities.scan_result.IScannerResult;
import com.maciej916.indreb.common.capabilities.scan_result.ScannerResult;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketPatternStorageAction;
import com.maciej916.indreb.common.network.packet.PacketPatternStoragePage;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.LazyOptionalHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.*;

public class BlockEntityPatternStorage extends IndRebBlockEntity {

    public static final int INPUT_SLOT = 0;
    public static final int MEMORY_SLOT = 1;

    public ArrayList<ScannerResult> scannerResultMap = new ArrayList<>();
    public int currentPattern = 0;
    public int patternsStored = 0;

    public BlockEntityPatternStorage(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PATTERN_STORAGE.get(), pWorldPosition, pBlockState);
    }

    private boolean isValidInput(final ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getItem() == ModItems.MEMORY_CARD.get();
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 138, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 137, 18));
        slots.add(new IndRebSlot(MEMORY_SLOT, 10, 21, InventorySlotType.DISABLED, GuiSlotType.NORMAL_BLANK, 9, 20));

        return super.addInventorySlot(slots);
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == INPUT_SLOT) return isValidInput(stack);
        return false;
    }

    @Override
    public ItemStack insertItemForSlot(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == INPUT_SLOT && !isValidInput(stack)) return stack;
        return super.insertItemForSlot(slot, stack, simulate);
    }

    public boolean hasScannerResult(ItemStack stack) {
        for (ScannerResult result : scannerResultMap) {
            if (result.getResultStack().getItem() == stack.getItem()) return true;
        }
        return false;
    }

    public int getCurrentPattern() {
        return currentPattern;
    }

    public int getPatternsStored() {
        return patternsStored;
    }

    public ArrayList<ScannerResult> getScannerResultMap() {
        return scannerResultMap;
    }

    public void addScannerResult(ScannerResult scannerResult) {
        scannerResultMap.add(scannerResult);
        this.patternsStored = scannerResultMap.size();
        updatePatterns();
        updateBlockState();
    }

    @Override
    public void tickWork(BlockState state) {
        ItemStack patternSlot = getItemStackHandler().getStackInSlot(MEMORY_SLOT);
        if (patternsStored > 0 && patternSlot.isEmpty()) {
            currentPattern = 0;
            getItemStackHandler().setStackInSlot(MEMORY_SLOT, scannerResultMap.get(currentPattern).getResultStack());
            shouldUpdateState = true;
        }
    }

    public Runnable clientClickPrevPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStoragePage(getBlockPos(), false));
    }

    public Runnable clientClickNextPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStoragePage(getBlockPos(), true));
    }

    public Runnable clientClickImportPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStorageAction(getBlockPos(), false));
    }

    public Runnable clientClickExportPattern() {
        return () -> ModNetworking.INSTANCE.sendToServer(new PacketPatternStorageAction(getBlockPos(), true));
    }

    public void changePattern(boolean next) {
        if (scannerResultMap.size() > 0) {
            if (next) {
                this.currentPattern = this.currentPattern + 1 >= this.scannerResultMap.size() ? 0 : this.currentPattern + 1;
            } else {
                this.currentPattern = this.currentPattern - 1 < 0 ? this.scannerResultMap.size() - 1 : this.currentPattern - 1;
            }

            getItemStackHandler().setStackInSlot(MEMORY_SLOT, scannerResultMap.get(this.currentPattern).getResultStack());
            updateBlockState();
        }
    }

    public void exportImportPattern(boolean export) {
        final ItemStack memoryStack = getItemStackHandler().getStackInSlot(INPUT_SLOT);
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

                        if (this.patternsStored == 0) {
                            getItemStackHandler().setStackInSlot(MEMORY_SLOT, ItemStack.EMPTY);
                        } else {
                            getItemStackHandler().setStackInSlot(MEMORY_SLOT, this.scannerResultMap.get(this.currentPattern).getResultStack());
                        }

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
            updateBlockState();
            updatePatterns();
        }
    }

    public void updatePatterns() {
        for (Direction direction : Constants.DIRECTIONS) {
            BlockPos offsetPos = getBlockPos().relative(direction);
            BlockEntity dirTile = getLevel().getBlockEntity(offsetPos);
            if (dirTile instanceof BlockEntityReplicator bePatternStorage) {
                bePatternStorage.updatePatterns();
            }
        }
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
    public CompoundTag save(CompoundTag tag) {

        ListTag nbtTagList = new ListTag();
        for (ScannerResult scannerResult : scannerResultMap) {
            nbtTagList.add(scannerResult.serializeNBT());
        }

        tag.put("Results", nbtTagList);
        tag.putInt("Size", scannerResultMap.size());
        tag.putInt("currentPattern", currentPattern);
        tag.putInt("patternsStored", patternsStored);

        return super.save(tag);
    }
}
