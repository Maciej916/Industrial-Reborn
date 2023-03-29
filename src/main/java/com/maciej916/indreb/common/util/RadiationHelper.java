package com.maciej916.indreb.common.util;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;

public class RadiationHelper {

    public static final HashMap<Item, Double> RADIATION_FOOD = new HashMap<>();
    public static final double LOWEST_RADIATION = 0.000000000000001D;
    public static final double PLAYER_MAX_RADIATION = 200D;
    public static final double RADIATION_DECAY = (0.1  * 10e-6);
    public static final double PLAYER_RADIATION_DECAY = 0.0000005D;
    public static final int RADIATION_IMMUNE_TIME = 2400;
    public static DamageSource RADIATION = new DamageSource("radiation");

    public static double calculateRadiationDistance(double radiation, int distance) {
        return Math.max(0, radiation - (RADIATION_DECAY * distance));
    }

    public static String radsPrefix(double rads, boolean rate) {
        String unit = rate ? "Rad/t" : "Rad";
        return UnitHelper.formatNumber(Math.abs(rads), 3, unit);
    }

    public static MutableComponent radsColoredPrefix(double rads, boolean rate) {
        return Component.literal(radsPrefix(rads, rate)).withStyle(getRadiationTextColor(rads));
    }

    public static ChatFormatting getRadiationTextColor(double radiation) {
        return radiation < 0.000000001D ? ChatFormatting.WHITE : radiation < 0.001D ? ChatFormatting.YELLOW : radiation < 0.1D ? ChatFormatting.GOLD : radiation < 1D ? ChatFormatting.RED : ChatFormatting.DARK_RED;
    }

    public static void playGeigerSound(Level level, Player player) {
        player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
            double radsLevel = cap.getRadsLevel();

            int loops =
                    radsLevel < 0.000000001D ? 0 :
                    radsLevel < 0.0001D ? player.getRandom().nextInt(30) <= 2 ? 1 : 0 :
                    radsLevel < 0.001D ? player.getRandom().nextInt(20) <= 2 ? 1 : 0 :
                    radsLevel < 0.1D ? player.getRandom().nextInt(20) <= 2 ? 2 : 1 :
                    radsLevel < 1D ? 2 : 3;

            if (loops == 0) {
                return;
            }

            for (int i = 0; i < loops; i++) {
                if (player.getRandom().nextFloat() <= 0.5f) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.GEIGER.get(), SoundSource.PLAYERS, 1f, Math.min(0.9f + (player.getRandom().nextInt(1) * (1.1f - 0.9f)), 1.1f));
                }
            }
        });
    }

    public static void init(boolean refresh) {
        if (refresh) {
            RADIATION_FOOD.clear();
        }
        try {
            List<? extends String> foodConfig = ServerConfig.radiation_foods.get();
            int loaded = 0;
            for (String food : foodConfig) {
                String[] itemRads = food.split(",");
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemRads[0]));
                if (item != null) {
                    double rads = Double.parseDouble(itemRads[1]);
                    RADIATION_FOOD.put(item, rads);
                    loaded++;
                } else {
                    IndReb.LOGGER.error("Item not found for config value " + food);
                }
            }

            if (loaded > 0) {
                IndReb.LOGGER.debug("Loaded " + loaded + " items for radiation_foods from config");
            }
        } catch (Exception exception) {
            IndReb.LOGGER.error("Exception while loading radiation_foods from config");
        }
    }
}
