package com.maciej916.indreb.common.tier;

import com.maciej916.indreb.common.enums.EnergyTier;

public enum TransformerTier {
    BASIC(EnergyTier.BASIC, EnergyTier.STANDARD),
    STANDARD(EnergyTier.STANDARD, EnergyTier.ADVANCED),
    ADVANCED(EnergyTier.ADVANCED, EnergyTier.SUPER),
    SUPER(EnergyTier.SUPER, EnergyTier.ULTRA);

    private final EnergyTier minTier;
    private final EnergyTier maxTier;

    TransformerTier(EnergyTier minTier, EnergyTier maxTier) {
        this.minTier = minTier;
        this.maxTier = maxTier;
    }

    public EnergyTier getMinTier() {
        return minTier;
    }

    public EnergyTier getMaxTier() {
        return maxTier;
    }
}
