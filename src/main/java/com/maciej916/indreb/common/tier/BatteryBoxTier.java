package com.maciej916.indreb.common.tier;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;

public enum BatteryBoxTier {
    BASIC(EnergyTier.BASIC, 0),
    STANDARD(EnergyTier.STANDARD, 0),
    ADVANCED(EnergyTier.ADVANCED, 0),
    SUPER(EnergyTier.SUPER, 0);

    private final EnergyTier energyTier;
    private final int energyStored;


    BatteryBoxTier(EnergyTier energyTier, int energyStored) {
        this.energyTier = energyTier;
        this.energyStored = energyStored;
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public int getEnergyStored() {
        return energyStored;
    }

    public int getEnergyCapacity() {
        return switch (energyTier) {
            case BASIC -> ServerConfig.wooden_battery_box_capacity.get();
            case STANDARD -> ServerConfig.cesu_capacity.get();
            case ADVANCED -> ServerConfig.mfe_capacity.get();
            case SUPER -> ServerConfig.mfsu_capacity.get();
            default -> ServerConfig.wooden_battery_box_capacity.get();
        };
    }
}
