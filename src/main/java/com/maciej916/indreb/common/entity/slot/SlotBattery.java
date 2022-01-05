package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;

public class SlotBattery extends SlotElectric {

    public SlotBattery(int slotId, int xPosition, int yPosition, boolean charging) {
        super(slotId, xPosition, yPosition, InventorySlotType.BATTERY, GuiSlotType.BATTERY, charging);
    }

}
