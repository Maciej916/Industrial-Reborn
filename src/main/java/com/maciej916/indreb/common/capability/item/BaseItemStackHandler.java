package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.api.slot.DisabledSlot;
import com.maciej916.indreb.common.api.slot.OutputSlot;
import com.maciej916.indreb.common.api.slot.handler.BaseSlotItemHandler;
import com.maciej916.indreb.common.api.slot.handler.DisabledSlotItemHandler;
import com.maciej916.indreb.common.api.slot.handler.OutputSlotItemHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IBaseItemStackHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class BaseItemStackHandler extends ItemStackHandler implements IBaseItemStackHandler {

    private final IndRebBlockEntity entity;
    private final ArrayList<BaseSlot> baseSlots;

    public BaseItemStackHandler(IndRebBlockEntity entity, ArrayList<BaseSlot> slots) {
        super(slots.size());
        this.entity = entity;
        this.baseSlots = slots;
    }

    public IndRebBlockEntity getEntity() {
        return entity;
    }

    public ArrayList<BaseSlot> getBaseSlots() {
        return baseSlots;
    }

    public ArrayList<BaseSlotItemHandler> getSlotHandler() {
        ArrayList<BaseSlotItemHandler> slotHandler = new ArrayList<>();
        for (BaseSlot slot : baseSlots) {
            if (slot instanceof OutputSlot outputSlot) {
                slotHandler.add(new OutputSlotItemHandler(this, entity, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
            } else if (slot instanceof DisabledSlot disabledSlot) {
                slotHandler.add(new DisabledSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
            } else {
                slotHandler.add(new BaseSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
            }
        }
        return slotHandler;
    }

}
