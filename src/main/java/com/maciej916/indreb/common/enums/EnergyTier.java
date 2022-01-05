package com.maciej916.indreb.common.enums;

import com.maciej916.indreb.common.config.ServerConfig;
import net.minecraft.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public enum EnergyTier {
    BASIC("Basic", EnumLang.TIER_BASIC, 1, ChatFormatting.GREEN, ServerConfig.basic_tier_transfer.get()),
    STANDARD("Standard", EnumLang.TIER_STANDARD,2, ChatFormatting.YELLOW, ServerConfig.standard_tier_transfer.get()),
    ADVANCED("Advanced", EnumLang.TIER_ADVANCED,3, ChatFormatting.RED, ServerConfig.advanced_tier_transfer.get()),
    SUPER("Super", EnumLang.TIER_SUPER,4, ChatFormatting.BLUE, ServerConfig.super_tier_transfer.get()),
    ULTRA("Ultra", EnumLang.TIER_ULTRA,5, ChatFormatting.GOLD, ServerConfig.ultra_tier_transfer.get()),
    CREATIVE("Creative", EnumLang.TIER_CREATIVE,6, ChatFormatting.GRAY, ServerConfig.creative_tier_transfer.get());

    private final String name;
    private final EnumLang lang;
    private final Integer lvl;
    private final ChatFormatting color;
    private final int basicTransfer;

    EnergyTier(String name, EnumLang lang, Integer lvl, ChatFormatting color, int basicTransfer) {
        this.name = name;
        this.lang = lang;
        this.lvl = lvl;
        this.color = color;
        this.basicTransfer = basicTransfer;
    }

    public Integer getLvl() {
        return lvl;
    }

    public String getName() {
        return name;
    }

    public EnumLang getLang() {
        return lang;
    }

    public ChatFormatting getColor() {
        return color;
    }

    public int getBasicTransfer() {
        return basicTransfer;
    }

    public static EnergyTier getTierFromLvl(int lvl) {
        EnergyTier[] currencies = EnergyTier.values();
        for (EnergyTier enumTier : currencies) {
            if (enumTier.getLvl() == lvl) {
                return enumTier;
            }
        }
        return BASIC;
    }
}
