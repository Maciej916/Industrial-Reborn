package com.maciej916.indreb.common.enums;

public enum UpgradeType {
    INPUT("input"),
    DISABLED("disabled");

    private final String type;

    UpgradeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

