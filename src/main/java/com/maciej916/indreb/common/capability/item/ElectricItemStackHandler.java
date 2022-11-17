package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.slot.ElectricSlotItemHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class ElectricItemStackHandler extends ItemStackHandler implements IElectricItemStackHandler {

    private final ArrayList<ElectricSlotItemHandler> slots;

    public ElectricItemStackHandler(ArrayList<ElectricSlotItemHandler> slots) {
        this.slots = slots;
        this.stacks = NonNullList.withSize(slots.size(), ItemStack.EMPTY);
    }

    public boolean isSlotCharging(int slotId) {
        return false;
    }
}
