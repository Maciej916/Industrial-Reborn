package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.interfaces.IElectricSlot;

public class ElectricSlot extends BaseSlot implements IElectricSlot {

    boolean isCharging;

    public ElectricSlot(int slotId, int slotX, int slotY, InventorySlotType inventoryType, GuiSlotBg guiType, boolean isEnabled, boolean isCharging) {
        super(slotId, slotX, slotY, slotX - 1, slotY - 1, inventoryType, guiType, isEnabled);
        this.isCharging = isCharging;
    }

    public boolean isCharging() {
        return isCharging;
    }
}
