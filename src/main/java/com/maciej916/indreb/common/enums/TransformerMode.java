package com.maciej916.indreb.common.enums;

public enum TransformerMode {
    STEP_UP(1),
    STEP_DOWN(2);

    private final int mode;

    TransformerMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public static TransformerMode getMode(int i) {
        TransformerMode[] currencies = TransformerMode.values();
        for (TransformerMode mode : currencies) {
            if (mode.getMode() == i) {
                return mode;
            }
        }
        return STEP_UP;
    }
}
