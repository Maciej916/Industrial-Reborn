package com.maciej916.indreb.common.api.tier;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public enum CableTier {
    TIN_CABLE(EnergyTier.BASIC, "tin_cable", false, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    TIN_CABLE_INSULATED(EnergyTier.BASIC, "tin_cable_insulated", true, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    COPPER_CABLE(EnergyTier.STANDARD, "copper_cable", false, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    COPPER_CABLE_INSULATED(EnergyTier.STANDARD, "copper_cable_insulated", true, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    GOLD_CABLE(EnergyTier.ADVANCED, "gold_cable", false, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    GOLD_CABLE_INSULATED(EnergyTier.ADVANCED, "gold_cable_insulated", true, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    HV_CABLE(EnergyTier.SUPER, "hv_cable", false, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    HV_CABLE_INSULATED(EnergyTier.SUPER, "hv_cable_insulated", true, Block.Properties.of(Material.METAL).strength(0.8F, 0.8F).sound(SoundType.WOOL)),
    GLASS_FIBRE_CABLE(EnergyTier.ULTRA, "glass_fibre_cable", true, Block.Properties.of(Material.GLASS).strength(0.8F, 0.8F).sound(SoundType.GLASS));

    private final EnergyTier energyTier;

    private final String tier;
    private final boolean insulated;
    private final Block.Properties properties;

    CableTier(EnergyTier energyTier, String tier, boolean insulated, Block.Properties properties) {
        this.energyTier = energyTier;
        this.tier = tier;
        this.insulated = insulated;
        this.properties = properties;
    }

    public EnergyTier getEnergyTier() {
        return energyTier;
    }

    public static CableTier get(EnergyTier tier) {
        for (CableTier transmitter : Constants.CABLE_TIERS) {
            if (transmitter.getEnergyTier() == tier) {
                return transmitter;
            }
        }
        return TIN_CABLE;
    }

    public String getTier() {
        return tier;
    }

    public boolean isInsulated() {
        return insulated;
    }

    public Block.Properties getProperties() {
        return properties;
    }

}
