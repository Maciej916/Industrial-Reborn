package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.IndReb;

public enum ScannerMode {
    NO_POWER(0,"no_power", 0xd60000),
    IDLE(1, "idle", 0xfffb00),
    NO_STORAGE(2, "no_storage", 0xd60000),
    PROGRESS(3,"progress", 0x008fd6),
    RESULT(4,"result", 0x00a200),
    ALREADY_RECORDED(5,"already_recorded", 0xd60000),
    TRANSFER_NO_STORAGE(6,"transfer_no_storage", 0xd60000);

    private final int id;
    private final String lang;
    private final int color;

    ScannerMode(int id, String lang, int color) {
        this.id = id;
        this.lang = lang;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public static ScannerMode getModeFromId(int id) {
        ScannerMode[] currencies = ScannerMode.values();
        for (ScannerMode mode : currencies) {
            if (mode.getId() == id) {
                return mode;
            }
        }
        return IDLE;
    }

    public String getLang() {
        return "gui." + IndReb.MODID + ".scanner." + lang;
    }
}

