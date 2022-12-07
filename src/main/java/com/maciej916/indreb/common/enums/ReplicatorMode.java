package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.IndReb;

public enum ReplicatorMode {
    WAITING(0,"waiting", 0xfffb00),
    SINGLE_RUN(1, "single_run", 0x008fd6),
    REPEAT_RUN(2, "repeat_run", 0x008fd6);

    private final int id;
    private final String lang;
    private final int color;

    ReplicatorMode(int id, String lang, int color) {
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

    public static ReplicatorMode getModeFromId(int id) {
        ReplicatorMode[] currencies = ReplicatorMode.values();
        for (ReplicatorMode mode : currencies) {
            if (mode.getId() == id) {
                return mode;
            }
        }
        return WAITING;
    }

    public String getLang() {
        return "gui." + IndReb.MODID + ".replicator." + lang;
    }
}

