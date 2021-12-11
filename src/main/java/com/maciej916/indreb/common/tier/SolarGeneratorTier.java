package com.maciej916.indreb.common.tier;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;

public enum SolarGeneratorTier {
    BASIC(EnergyTier.BASIC, "basic", 8.0D, ServerConfig.solar_generator_energy_capacity.get(), ServerConfig.solar_generator_day_tick_generate.get(), ServerConfig.solar_generator_night_tick_generate.get()),
    ADVANCED(EnergyTier.STANDARD, "advanced", 10.0D, ServerConfig.advanced_solar_generator_energy_capacity.get(), ServerConfig.advanced_solar_generator_day_tick_generate.get(), ServerConfig.advanced_solar_generator_night_tick_generate.get()),
    HYBRID(EnergyTier.ADVANCED, "hybrid", 12.0D, ServerConfig.hybrid_solar_generator_energy_capacity.get(), ServerConfig.hybrid_solar_generator_day_tick_generate.get(), ServerConfig.hybrid_solar_generator_night_tick_generate.get()),
    QUANTUM(EnergyTier.SUPER, "quantum", 14.0D, ServerConfig.quantum_solar_generator_energy_capacity.get(), ServerConfig.quantum_solar_generator_day_tick_generate.get(), ServerConfig.quantum_solar_generator_night_tick_generate.get());

    private final EnergyTier energyTier;
    private final String tier;
    private final double height;
    private final int energyCapacity;
    private final int dayGenerate;
    private final int nightGenerate;

    SolarGeneratorTier(EnergyTier energyTier, String tier, double height, int energyCapacity, int dayGenerate, int nightGenerate) {
        this.energyTier = energyTier;
        this.tier = tier;
        this.height = height;
        this.energyCapacity = energyCapacity;
        this.dayGenerate = dayGenerate;
        this.nightGenerate = nightGenerate;
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public String getTier() {
        return tier;
    }

    public double getHeight() {
        return height;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public int getDayGenerate() {
        return dayGenerate;
    }

    public int getNightGenerate() {
        return nightGenerate;
    }
}
