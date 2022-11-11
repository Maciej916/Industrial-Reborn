package com.maciej916.indreb.common.interfaces.entity;

import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;

public interface ISlot {

    int getSlotId();
    int getXPosition();
    int getYPosition();
    InventorySlotType getInventorySlotType();
    GuiSlotType guiSlotType();
    void setGuiSlotType(GuiSlotType guiSlotType);
    int getGuiX();
    int getGuiY();
}
