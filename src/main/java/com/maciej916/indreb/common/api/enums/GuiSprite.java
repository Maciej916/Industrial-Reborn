package com.maciej916.indreb.common.api.enums;

public enum GuiSprite {
    ARROW(24,16,0, 0, 24,16,25, 0, 0, 0),
    CRUSHING(24,16,0, 17, 24,16,25, 17, 0, 0),
    COMPRESSING(24,16,0, 34, 24,16,25, 34, 0, 0),
    EXTRACTING(24,16, 0, 51, 24,16,25, 51, 0, 0),
    SAWING(24,16, 0, 68, 24,16,25, 68, 0, 0),
    SMELTING(16,16, 50, 0, 16,16,67, 0, 0, 0),
    SOLAR_SUN(16,16, 50, 17, 16,16,67, 17, 0, 0),
    RECYCLER(24,16,0, 85, 24,16,25, 85, 0, 0),
    ORE_WASHING(19,19,0, 102, 19,19,20, 102, 0, 0),
    SCANNER(61,42,0, 122, 61,42,62, 122, 0, 0),
    EXTRUDING(24,18,0, 175, 24,18,25, 175, 0, 0),
    ROLLING(24,18,0, 194, 24,18,25, 194, 0, 0),
    CUTTING(24,18,0, 213, 24,18,25, 213, 0, 0),

    ELECTRIC_VERTICAL(18,49,0, 0, 10,41,19, 0, 4, 4),
    ELECTRIC_HORIZONTAL(49,18,30, 0, 41,10,30, 19, 4, 4),

    FILL(14,7, 242, 0,14,7, 242, 8, 0, 0),

    LEFT_BUTTON(19,23, 0, 0, 19,23, 0, 24, 0, 0),
    RIGHT_BUTTON(19,23, 0, 0, 19,23, 0, 24, 0, 0),
    SMALL_BUTTON(12,12, 40, 0, 12,12, 40, 13, 0, 0),
    LARGE_BUTTON(20,20, 53, 0, 20,20, 53, 21, 0, 0),

    EXP_ICON(16,16, 0, 48, 0,0,0,0,3, 3),
    FORWARD_ICON(12,12, 40, 26, 0,0,0,0,0, 0),
    BACKWARD_ICON(12,12, 40, 39, 0,0,0,0,0, 0),
    UP_ICON(12,12, 40, 52, 0,0,0,0,0, 0),
    DOWN_ICON(12,12, 40, 65, 0,0,0,0,0, 0),
    TRANSFORMER_ICON(16,16, 0, 65, 0,0,0,0,2, 2),
    INFO_ICON(16,16, 0, 82, 0,0,0,0,3, 3),
    PLAY_ICON(12,12, 40, 130, 0,0,0,0,0, 0),
    PAUSE_ICON(12,12, 40, 143, 0,0,0,0,0, 0),

    FERTILIZER(36,15,80, 0, 28,7,80, 16, 4, 4),
    SCANNER_CLEAR(12,12, 40, 78, 12,12, 40, 91, 0, 0),
    SCANNER_SAVE(24,12, 40, 104, 24,12, 40, 117, 0, 0),
    REPLICATOR_STOP(18,18, 53, 42, 18,18, 72, 42, 0, 0),
    REPLICATOR_SINGLE(18,18, 53, 61, 18,18, 72, 61, 0, 0),
    REPLICATOR_REPEAT(18,18, 53, 80, 18,18, 72, 80, 0, 0),
    REACTOR_TEMPERATURE(87,11,117, 0, 83,7,117, 12, 2, 2)
    ;

    private final int width;
    private final int height;
    private final int offsetLeft;
    private final int offsetTop;

    private final int activeWidth;
    private final int activeHeight;
    private final int activeOffsetLeft;
    private final int activeOffsetTop;

    private final int renderOffsetLeft;
    private final int renderOffsetTop;

    GuiSprite(int width, int height, int offsetLeft, int offsetTop, int activeWidth, int activeHeight, int activeOffsetLeft, int activeOffsetTop, int renderOffsetLeft, int renderOffsetTop) {
        this.width = width;
        this.height = height;
        this.offsetLeft = offsetLeft;
        this.offsetTop = offsetTop;
        this.activeWidth = activeWidth;
        this.activeHeight = activeHeight;
        this.activeOffsetLeft = activeOffsetLeft;
        this.activeOffsetTop = activeOffsetTop;
        this.renderOffsetLeft = renderOffsetLeft;
        this.renderOffsetTop = renderOffsetTop;
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

    public int getActiveWidth() {
        return activeWidth;
    }

    public int getActiveHeight() {
        return activeHeight;
    }

    public int getActiveOffsetLeft() {
        return activeOffsetLeft;
    }

    public int getActiveOffsetTop() {
        return activeOffsetTop;
    }

    public int getRenderOffsetLeft() {
        return renderOffsetLeft;
    }

    public int getRenderOffsetTop() {
        return renderOffsetTop;
    }
}

