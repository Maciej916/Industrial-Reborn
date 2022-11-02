package com.maciej916.indreb.common.enums;

public enum UpgradeType {
    OVERCLOCKER("overclocker_upgrade", EnumLang.OVERCLOCKER_UPGRADE),
    TRANSFORMER("transformer_upgrade", EnumLang.TRANSFORMER_UPGRADE),
    ENERGY_STORAGE("energy_storage_upgrade", EnumLang.ENERGY_STORAGE_UPGRADE),
    REDSTONE_SIGNAL_INVERTER("redstone_signal_inverter_upgrade", EnumLang.REDSTONE_SIGNAL_INVERTER_UPGRADE),
    EJECTOR("ejector_upgrade", EnumLang.EJECTOR_UPGRADE),
    PULLING("pulling_upgrade", EnumLang.PULLING_UPGRADE),
    FLUID_EJECTOR("fluid_ejector_upgrade", EnumLang.FLUID_EJECTOR_UPGRADE),
    FLUID_PULLING("fluid_pulling_upgrade", EnumLang.FLUID_PULLING_UPGRADE);

    private final String type;
    private final EnumLang lang;

    UpgradeType(String type, EnumLang lang) {
        this.type = type;
        this.lang = lang;
    }

    public String getType() {
        return type;
    }

    public EnumLang getLang() {
        return lang;
    }
}

