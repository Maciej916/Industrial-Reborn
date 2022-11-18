package com.maciej916.indreb.common.api.slot.handler;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BaseSlotItemHandler extends SlotItemHandler {

    boolean isActive;

    public BaseSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean isActive) {
        super(itemHandler, index, xPosition, yPosition);
        this.isActive = isActive;
    }


    @Override
    public boolean isActive() {
        return isActive;
    }
}
