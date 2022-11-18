package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.handler.BaseSlotItemHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IBaseItemStackHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class BaseItemStackHandler extends ItemStackHandler implements IBaseItemStackHandler {

    private final ArrayList<BaseSlot> baseSlots;

    public BaseItemStackHandler(ArrayList<BaseSlot> slots) {
        super(slots.size());
        this.baseSlots = slots;
    }

    public ArrayList<BaseSlot> getBaseSlots() {
        return baseSlots;
    }

    public ArrayList<BaseSlotItemHandler> getSlotHandler() {
        ArrayList<BaseSlotItemHandler> slotHandler = new ArrayList<>();
        for (BaseSlot slot : baseSlots) {
            slotHandler.add(new BaseSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
        }
        return slotHandler;
    }

}
