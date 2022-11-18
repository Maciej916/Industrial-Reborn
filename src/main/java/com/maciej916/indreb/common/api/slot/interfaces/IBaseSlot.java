package com.maciej916.indreb.common.api.slot.interfaces;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;

public interface IBaseSlot {

    int getSlotId();

    int getSlotX();
    int getSlotY();

    int getSlotGuiX();
    int getSlotGuiY();

    InventorySlotType getInventorySlotType();
    GuiSlotBg getGuiSlotBg();
    void setGuiSlotBg(GuiSlotBg guiSlotBg);

    boolean isActive();
    void setActive(boolean enabled);

}
