package com.maciej916.indreb.common.api.blockentity.interfaces;

import com.maciej916.indreb.common.api.slot.BaseSlotItemHandler;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface IHasBaseStorage {

    void onBaseStorageContentsChanged(int slot);
    int getBaseStorageSlotLimit(int slot);
    boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack);
    ArrayList<BaseSlotItemHandler> addBaseSlots(ArrayList<BaseSlotItemHandler> slots);
}
