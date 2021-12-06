package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;

import java.util.List;

public class SlotBattery extends SlotElectric {


    public SlotBattery(int slotId, int xPosition, int yPosition, boolean charging, List<EnergyTier> energyTier) {
        super(slotId, xPosition, yPosition, InventorySlotType.BATTERY, GuiSlotType.BATTERY, charging, energyTier);
    }

}
