package com.maciej916.indreb.common.api.blockentity.interfaces;

import com.maciej916.indreb.common.api.slot.ElectricSlotItemHandler;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface IHasEnergyStorage {

    void onElectricStorageContentsChanged(int slot);
    int getElectricStorageSlotLimit(int slot);
    boolean isElectricStorageItemValid(int slot, @NotNull ItemStack stack);
    ArrayList<ElectricSlotItemHandler> addElectricSlots(ArrayList<ElectricSlotItemHandler> slots);
}
