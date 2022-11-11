package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IUpgradeSlot;

public class SlotUpgrade extends IndRebSlot implements IUpgradeSlot {

    public SlotUpgrade(int slotId, int xPosition, int yPosition) {
        super(slotId, xPosition, yPosition, InventorySlotType.UPGRADE, GuiSlotType.UPGRADE, xPosition - 1, yPosition -1);
    }

}
