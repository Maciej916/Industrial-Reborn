package com.maciej916.indreb.common.enums;

public enum GuiSlotType {
    NORMAL(18,18, 84, 27),
    LARGE(26,26, 84, 0),
    BATTERY(18,18, 103, 27),
    HELMET(18,18, 122, 27),
    CHESTPLATE(18,18, 141, 27),
    LEGGINGS(18,18, 160, 27),
    BOOTS(18,18, 179, 27);

    private final int width;
    private final int height;
    private final int offsetLeft;
    private final int offsetTop;

    GuiSlotType(int width, int height, int offsetLeft, int offsetTop) {
        this.width = width;
        this.height = height;
        this.offsetLeft = offsetLeft;
        this.offsetTop = offsetTop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public int getOffsetTop() {
        return offsetTop;
    }
}

