package com.maciej916.indreb.common.screen.slot;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

import static com.maciej916.indreb.common.enums.GuiSlotType.NORMAL;

public class GuiItemSlotNormal extends GuiSlot {

    public GuiItemSlotNormal(IGuiWrapper wrapper, int leftOffset, int topOffset) {
        super(NORMAL, wrapper, leftOffset, topOffset);
    }

}
