package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.handler.BaseSlotItemHandler;
import com.maciej916.indreb.common.api.slot.handler.ElectricSlotItemHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IElectricItemStackHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class ElectricItemStackHandler extends ItemStackHandler implements IElectricItemStackHandler {

    private final ArrayList<ElectricSlot> electricSlots;

    public ElectricItemStackHandler(ArrayList<ElectricSlot> slots) {
        super(slots.size());
        this.electricSlots = slots;
    }

    public ArrayList<ElectricSlot> getElectricSlots() {
        return electricSlots;
    }

    public ArrayList<BaseSlotItemHandler> getSlotHandler() {
        ArrayList<BaseSlotItemHandler> slotHandler = new ArrayList<>();
        for (ElectricSlot slot : electricSlots) {
            slotHandler.add(new ElectricSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
        }
        return slotHandler;
    }

    public boolean isSlotCharging(int slotId) {
        return electricSlots.get(slotId).isCharging();
    }

}
