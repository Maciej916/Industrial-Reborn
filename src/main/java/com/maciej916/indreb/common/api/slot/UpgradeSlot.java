package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.interfaces.IUpgradeSlot;

public class UpgradeSlot extends BaseSlot implements IUpgradeSlot {

    public UpgradeSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, InventorySlotType inventoryType, GuiSlotBg guiType, boolean isEnabled) {
        super(slotId, slotX, slotY, slotGuiX, slotGuiY, inventoryType, guiType, isEnabled);

    }

}
