package com.maciej916.indreb.common.tier;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;

public enum BatteryBoxTier {
    BASIC(EnergyTier.BASIC, 0, ServerConfig.wooden_battery_box_capacity.get()),
    STANDARD(EnergyTier.STANDARD, 0, ServerConfig.cesu_capacity.get()),
    ADVANCED(EnergyTier.ADVANCED, 0, ServerConfig.mfe_capacity.get()),
    SUPER(EnergyTier.SUPER, 0, ServerConfig.mfsu_capacity.get());

    private final EnergyTier energyTier;
    private final int energyStored;
    private final int energyCapacity;


    BatteryBoxTier(EnergyTier energyTier, int energyStored, int energyCapacity) {
        this.energyTier = energyTier;
        this.energyStored = energyStored;
        this.energyCapacity = energyCapacity;
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public int getEnergyStored() {
        return energyStored;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }
}
