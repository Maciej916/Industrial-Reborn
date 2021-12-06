package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.ISlot;

import javax.annotation.Nullable;

public class IndRebSlot implements ISlot {

    private final int slotId;
    private final int xPosition;
    private final int yPosition;
    private final InventorySlotType invSlotType;
    private final GuiSlotType guiSlotType;

    private final int guiX;
    private final int guiY;

    public IndRebSlot(int slotId, int xPosition, int yPosition, InventorySlotType invSlotType, @Nullable GuiSlotType guiSlotType, int guiX, int guiY) {
        this.slotId = slotId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.invSlotType = invSlotType;
        this.guiSlotType = guiSlotType;
        this.guiX = guiX;
        this.guiY = guiY;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public InventorySlotType getInventorySlotType() {
        return invSlotType;
    }

    public GuiSlotType guiSlotType() {
        return guiSlotType;
    }

    public int getGuiX() {
        return guiX;
    }

    public int getGuiY() {
        return guiY;
    }
}
