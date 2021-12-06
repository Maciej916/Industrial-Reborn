package com.maciej916.indreb.common.enums;

public enum EnumEnergyType  {
    NONE("none"),
    RECEIVE("receive"),
    EXTRACT("extract"),
    BOTH("both"),
    CABLE("cable");

    private final String key;

    EnumEnergyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
