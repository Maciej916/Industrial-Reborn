package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.IndReb;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum EnumLang {
    GENERIC("generic", ""),
    WIP("wip", "tooltip"),

    POWER("power", "energy"),
    POWER_TICK("power_tick", "energy"),

    TIER_BASIC("basic", "tier"),
    TIER_STANDARD("standard", "tier"),
    TIER_ADVANCED("advanced", "tier"),
    TIER_SUPER("super", "tier"),
    TIER_ULTRA("ultra", "tier"),
    TIER_CREATIVE("creative", "tier"),

    POWER_TIER("power_tier", "tooltip"),
    ACCEPT_CAPACITY("accept_capacity", "tooltip"),
    GENERATE("generate", "tooltip"),
    OUTPUT_CAPACITY("output_capacity", "tooltip"),
    TRANSFER("transfer", "tooltip"),
    ACCEPT("accept", "tooltip"),
    OUTPUT("output", "tooltip"),
    CAPACITY("capacity", "tooltip"),
    ACCEPT_OUTPUT("accept_output", "tooltip"),
    STORED("stored", "tooltip"),
    STORED_CAPACITY("stored_capacity", "tooltip"),
    CABLE_UNISOLATED("cable_unisolated", "tooltip"),
    LOW("low", "tooltip"),
    HIGH("high", "tooltip"),

    HEAT("heat", "gui"),
    ARMOUR("armour", "gui"),

    SUPPORTED_UPGRADES("supported_upgrades", "gui"),
    OVERCLOCKER_UPGRADE("overclocker_upgrade", "item"),
    TRANSFORMER_UPGRADE("transformer_upgrade", "item"),
    ENERGY_STORAGE_UPGRADE("energy_storage_upgrade", "item"),
    REDSTONE_SIGNAL_INVERTER_UPGRADE("redstone_signal_inverter_upgrade", "item"),
    EJECTOR_UPGRADE("ejector_upgrade", "item"),
    PULLING_UPGRADE("pulling_upgrade", "item"),
    FLUID_EJECTOR_UPGRADE("fluid_ejector_upgrade", "item"),
    FLUID_PULLING_UPGRADE("fluid_pulling_upgrade", "item"),

    CHANCE("chance", "jei");


    private final String key;

    EnumLang(String type, String path) {
        this(path + (!path.equals("") ? "." : "") + IndReb.MODID + "." + type);
    }

    EnumLang(String key) {
        this.key = key;
    }

    public String getTranslationKey() {
        return key;
    }

    public MutableComponent getTranslationComponent() {
        return Component.translatable(key);
    }

}
