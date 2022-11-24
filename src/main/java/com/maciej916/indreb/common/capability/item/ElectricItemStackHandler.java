package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.slot.ElectricSlot;
import com.maciej916.indreb.common.api.slot.handler.BaseSlotItemHandler;
import com.maciej916.indreb.common.api.slot.handler.ElectricSlotItemHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IElectricItemStackHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class ElectricItemStackHandler extends ItemStackHandler implements IElectricItemStackHandler {

    private final BasicEnergyStorage energyStorage;
    private final ArrayList<ElectricSlot> electricSlots;

    public ElectricItemStackHandler(ArrayList<ElectricSlot> slots, BasicEnergyStorage energyStorage) {
        super(slots.size());
        this.electricSlots = slots;
        this.energyStorage = energyStorage;
    }

    public ArrayList<ElectricSlot> getElectricSlots() {
        return electricSlots;
    }

    public ArrayList<BaseSlotItemHandler> getSlotHandler() {
        ArrayList<BaseSlotItemHandler> slotHandler = new ArrayList<>();
        for (ElectricSlot slot : electricSlots) {
            slotHandler.add(new ElectricSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive(), slot.isCharging(), slot.getInventorySlotType(), energyStorage));
        }
        return slotHandler;
    }

    public boolean isSlotCharging(int slotId) {
        return electricSlots.get(slotId).isCharging();
    }

}
