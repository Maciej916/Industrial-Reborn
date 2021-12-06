package com.maciej916.indreb.common.screen.slot;

import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

public class GuiItemSlot extends GuiSlot {

    public GuiItemSlot(GuiSlotType slotType, IGuiWrapper wrapper, int leftOffset, int topOffset) {
        super(slotType, wrapper, leftOffset, topOffset);
    }

}
