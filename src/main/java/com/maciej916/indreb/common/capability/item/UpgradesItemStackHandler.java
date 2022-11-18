package com.maciej916.indreb.common.capability.item;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.UpgradeSlot;
import com.maciej916.indreb.common.api.slot.handler.BaseSlotItemHandler;
import com.maciej916.indreb.common.api.slot.handler.UpgradeSlotItemHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IUpgradesItemStackHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class UpgradesItemStackHandler extends ItemStackHandler implements IUpgradesItemStackHandler {

    private final ArrayList<UpgradeSlot> upgradeSlots;

    public UpgradesItemStackHandler(int size) {
        super(size);
        ArrayList<UpgradeSlot> slots = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            slots.add(new UpgradeSlot(i, 178, 9 + (i * 18), 177, 8 + (i * 18), InventorySlotType.UPGRADE, GuiSlotBg.UPGRADE, true));
        }
        this.upgradeSlots = slots;
    }

    public ArrayList<UpgradeSlot> getUpgradeSlots() {
        return upgradeSlots;
    }

    public ArrayList<BaseSlotItemHandler> getSlotHandler() {
        ArrayList<BaseSlotItemHandler> slotHandler = new ArrayList<>();
        for (UpgradeSlot slot : upgradeSlots) {
            slotHandler.add(new UpgradeSlotItemHandler(this, slot.getSlotId(), slot.getSlotX(), slot.getSlotY(), slot.isActive()));
        }
        return slotHandler;
    }
}
