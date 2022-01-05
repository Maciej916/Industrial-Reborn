package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.IndReb;
import net.minecraft.network.chat.TranslatableComponent;

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

    public TranslatableComponent getTranslationComponent() {
        return new TranslatableComponent(key);
    }

}
