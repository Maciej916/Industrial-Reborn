package com.maciej916.indreb.common.api.slot;

import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.interfaces.IBaseSlot;

public class BaseSlot implements IBaseSlot {

    private final int slotId;
    private final int slotX;
    private final int slotY;
    private final int slotGuiX;
    private final int slotGuiY;
    private InventorySlotType inventoryType;
    private GuiSlotBg guiSlotBg;
    private boolean isActive;

    public BaseSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, InventorySlotType inventoryType, GuiSlotBg guiSlotBg) {
        this(slotId, slotX, slotY, slotGuiX, slotGuiY, inventoryType, guiSlotBg, true);
    }

    public BaseSlot(int slotId, int slotX, int slotY, int slotGuiX, int slotGuiY, InventorySlotType inventoryType, GuiSlotBg guiType, boolean isEnabled) {
        this.slotId = slotId;
        this.slotX = slotX;
        this.slotY = slotY;
        this.slotGuiX = slotGuiX;
        this.slotGuiY = slotGuiY;
        this.inventoryType = inventoryType;
        this.guiSlotBg = guiType;
        this.isActive = isEnabled;
    }

    @Override
    public int getSlotId() {
        return slotId;
    }

    @Override
    public int getSlotX() {
        return slotX;
    }

    @Override
    public int getSlotY() {
        return slotY;
    }

    @Override
    public int getSlotGuiX() {
        return slotGuiX;
    }

    @Override
    public int getSlotGuiY() {
        return slotGuiY;
    }

    @Override
    public InventorySlotType getInventorySlotType() {
        return inventoryType;
    }

    @Override
    public GuiSlotBg getGuiSlotBg() {
        return guiSlotBg;
    }

    @Override
    public void setGuiSlotBg(GuiSlotBg guiSlotBg) {
        this.guiSlotBg = guiSlotBg;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean enabled) {
        this.isActive = enabled;
    }
}
