package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.common.api.enums.GuiSprite;

public enum MetalFormerMode {
    CUTTING(1, "cutting", GuiSprite.CUTTING),
    ROLLING(2, "rolling", GuiSprite.ROLLING),
    EXTRUDING(3,"extruding", GuiSprite.EXTRUDING);

    private final int id;
    private final String type;
    private final GuiSprite sprite;

    MetalFormerMode(int id, String type, GuiSprite sprite) {
        this.id = id;
        this.type = type;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static MetalFormerMode getModeFromId(int id) {
        MetalFormerMode[] currencies = MetalFormerMode.values();
        for (MetalFormerMode mode : currencies) {
            if (mode.getId() == id) {
                return mode;
            }
        }
        return CUTTING;
    }

    public GuiSprite getSprite() {
        return sprite;
    }
}

