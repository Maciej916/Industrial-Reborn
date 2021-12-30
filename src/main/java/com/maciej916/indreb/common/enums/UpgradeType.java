package com.maciej916.indreb.common.enums;

public enum UpgradeType {
    OVERCLOCKER("overclocker"),
    TRANSFORMER("transformer"),
    ENERGY_STORAGE("energy_storage"),
    REDSTONE_SIGNAL_INVERTER("redstone_signal_inverter"),
    EJECTOR("ejector"),
    PULLING("pulling"),
    FLUID_EJECTOR("fluid_ejector"),
    FLUID_PULLING("fluid_pulling");

    private final String type;

    UpgradeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

