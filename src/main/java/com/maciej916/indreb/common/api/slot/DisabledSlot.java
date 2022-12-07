package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;

public class DisabledSlot extends BaseSlot {

    public DisabledSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, GuiSlotBg guiSlotBg) {
        super(slotId, slotX, slotY, slotGuiX, slotGuiY, InventorySlotType.DISABLED, guiSlotBg);
    }

    public DisabledSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, GuiSlotBg guiSlotBg, boolean isEnabled) {
        super(slotId, slotX, slotY, slotGuiX, slotGuiY, InventorySlotType.DISABLED, guiSlotBg, isEnabled);
    }
}
