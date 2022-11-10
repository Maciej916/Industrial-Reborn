package com.maciej916.indreb.common.enums;

public enum TransformerMode {
    STEP_UP(1),
    STEP_DOWN(2);

    private final int mode;

    TransformerMode(int mode) {
        this.mode = mode;
    }

    public int getModeFromId() {
        return mode;
    }

    public static TransformerMode getModeFromId(int i) {
        TransformerMode[] currencies = TransformerMode.values();
        for (TransformerMode mode : currencies) {
            if (mode.getModeFromId() == i) {
                return mode;
            }
        }
        return STEP_UP;
    }
}
