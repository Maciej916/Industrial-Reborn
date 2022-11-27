package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;

public class BonusSlot extends BaseSlot {

    public BonusSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, GuiSlotBg guiSlotBg) {
        super(slotId, slotX, slotY, slotGuiX, slotGuiY, InventorySlotType.BONUS, guiSlotBg);
    }
}
