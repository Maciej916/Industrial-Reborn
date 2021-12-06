package com.maciej916.indreb.common.tier;

import com.maciej916.indreb.common.enums.EnergyTier;

public enum SolarGeneratorTier {
    BASIC(EnergyTier.BASIC, "tin_cable"),
    ADVANCED(EnergyTier.BASIC, "tin_cable_insulated"),
    HYBRID(EnergyTier.STANDARD, "copper_cable"),
    QUANTUM(EnergyTier.SUPER, "copper_cable_insulated");

    private final EnergyTier energyTier;
    private final String tier;

    SolarGeneratorTier(EnergyTier energyTier, String tier) {
        this.energyTier = energyTier;
        this.tier = tier;
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public String getTier() {
        return tier;
    }

}
