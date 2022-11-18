package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.interfaces.IElectricSlot;

public class ElectricSlot extends BaseSlot implements IElectricSlot {

    boolean isCharging;

    public ElectricSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, InventorySlotType inventoryType, GuiSlotBg guiType, boolean isEnabled, boolean isCharging) {
        super(slotId, slotX, slotY, slotGuiX, slotGuiY, inventoryType, guiType, isEnabled);
        this.isCharging = isCharging;
    }

    public boolean isCharging() {
        return isCharging;
    }
}
