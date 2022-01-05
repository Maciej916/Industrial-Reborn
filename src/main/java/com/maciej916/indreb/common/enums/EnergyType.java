package com.maciej916.indreb.common.enums;

public enum EnergyType {
    NONE("none"),
    RECEIVE("receive"),
    EXTRACT("extract"),
    BOTH("both"),
    TRANSFORMER("transformer"),
    CABLE("cable");

    private final String key;

    EnergyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
