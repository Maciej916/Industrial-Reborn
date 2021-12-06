package com.maciej916.indreb.common.screen.slot;

import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

import static com.maciej916.indreb.common.enums.GuiSlotType.LARGE;

public class GuiItemSlotLarge extends GuiSlot {

    public GuiItemSlotLarge(IGuiWrapper wrapper, int leftOffset, int topOffset) {
        super(LARGE, wrapper, leftOffset, topOffset);
    }

}
