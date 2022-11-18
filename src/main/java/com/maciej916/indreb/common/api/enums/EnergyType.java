package com.maciej916.indreb.common.api.enums;

public enum EnergyType {
    NONE(1, "none"),
    RECEIVE(2, "receive"),
    EXTRACT(3, "extract"),
    BOTH(4, "both"),
    TRANSFORMER(5, "transformer"),
    CABLE(6, "cable");

    private final int id;
    private final String key;

    EnergyType(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public static EnergyType getTierFromId(int id) {
        EnergyType[] types = EnergyType.values();
        for (EnergyType type : types) {
            if (type.getId() == id) return type;
        }
        return NONE;
    }
}
