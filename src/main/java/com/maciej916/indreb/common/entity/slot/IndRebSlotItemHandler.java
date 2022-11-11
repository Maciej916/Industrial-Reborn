package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class IndRebSlotItemHandler extends SlotItemHandler {

    private final IndRebBlockEntity entity;
    boolean isActive = true;

    public IndRebSlotItemHandler(IndRebBlockEntity be, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.entity = be;
    }

    public IndRebBlockEntity getEntity() {
        return entity;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
