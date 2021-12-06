package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;

import java.util.List;

public class SlotElectric extends IndRebSlot implements IElectricSlot {

    private final boolean charging;
    private final InventorySlotType inventorySlotType;
    private final List<EnergyTier> energyTier;

    public SlotElectric(int slotId, int xPosition, int yPosition, InventorySlotType inventorySlotType, GuiSlotType guiSlotType, boolean charging, List<EnergyTier> energyTier) {
        super(slotId, xPosition, yPosition, inventorySlotType, guiSlotType, xPosition - 1, yPosition -1);
        this.charging = charging;
        this.inventorySlotType = inventorySlotType;
        this.energyTier = energyTier;
    }

    @Override
    public boolean isCharging() {
        return charging;
    }

    public InventorySlotType getInventorySlotType() {
        return inventorySlotType;
    }

    public List<EnergyTier> getAllowedTiers() {
        return energyTier;
    }
}
