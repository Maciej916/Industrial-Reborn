package com.maciej916.indreb.common.api.tier;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.config.impl.ServerConfig;

public enum SolarPanelTier {
    BASIC(1,EnergyTier.BASIC, "basic", 8.0D),
    ADVANCED(2, EnergyTier.STANDARD, "advanced", 8.0D),
    HYBRID(3, EnergyTier.ADVANCED, "hybrid", 8.0D),
    QUANTUM(4, EnergyTier.SUPER, "quantum", 8.0D);

    private final int tierLevel;
    private final EnergyTier energyTier;
    private final String tier;
    private final double height;

    SolarPanelTier(int tierLevel, EnergyTier energyTier, String tier, double height) {
        this.tierLevel = tierLevel;
        this.energyTier = energyTier;
        this.tier = tier;
        this.height = height;
    }

    public int getTierLevel() {
        return tierLevel;
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
        return switch (energyTier) {
            case BASIC -> ServerConfig.solar_generator_energy_capacity.get();
            case STANDARD -> ServerConfig.advanced_solar_generator_energy_capacity.get();
            case ADVANCED -> ServerConfig.hybrid_solar_generator_energy_capacity.get();
            case SUPER -> ServerConfig.quantum_solar_generator_energy_capacity.get();
            default -> ServerConfig.solar_generator_energy_capacity.get();
        };
    }

    public int getDayGenerate() {
        return switch (energyTier) {
            case BASIC -> ServerConfig.solar_generator_day_tick_generate.get();
            case STANDARD -> ServerConfig.advanced_solar_generator_day_tick_generate.get();
            case ADVANCED -> ServerConfig.hybrid_solar_generator_day_tick_generate.get();
            case SUPER -> ServerConfig.quantum_solar_generator_day_tick_generate.get();
            default -> ServerConfig.solar_generator_day_tick_generate.get();
        };
    }

    public int getNightGenerate() {
        return switch (energyTier) {
            case BASIC -> ServerConfig.solar_generator_night_tick_generate.get();
            case STANDARD -> ServerConfig.advanced_solar_generator_night_tick_generate.get();
            case ADVANCED -> ServerConfig.hybrid_solar_generator_night_tick_generate.get();
            case SUPER -> ServerConfig.quantum_solar_generator_night_tick_generate.get();
            default -> ServerConfig.solar_generator_night_tick_generate.get();
        };
    }
}
