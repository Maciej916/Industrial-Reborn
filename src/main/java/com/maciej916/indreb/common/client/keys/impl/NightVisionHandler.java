package com.maciej916.indreb.common.client.keys.impl;

import com.maciej916.indreb.common.api.interfaces.item.IArmorProperties;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.player.IPlayerCapability;
import com.maciej916.indreb.common.client.keys.ModKeys;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class NightVisionHandler {

    public static void toggleNightVision(Player player) {
        if (ModKeys.NIGHT_VISION_KEY.consumeClick()) {
            boolean found = false;
            for (ItemStack stack : player.getArmorSlots()) {
                if (stack.getItem() instanceof IArmorProperties armorProperties) {
                    if (armorProperties.supportsNightVision()) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> toggle(cap, player, !cap.getNightVision()));
            }
        }
    }

    public static void checkNightVision(Player player) {
        player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
            boolean found = false;
            for (ItemStack stack : player.getArmorSlots()) {
                if (stack.getItem() instanceof IArmorProperties armorProperties) {
                    if (armorProperties.supportsNightVision()) {
                        found = true;
                        break;
                    }
                }
            }

            if (!found && cap.getNightVision()) {
                toggle(cap, player, false);
            }
        });
    }

    public static void toggle(IPlayerCapability cap, Player player, boolean enable) {
        if (enable) {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.NIGHT_VISION, 1000000, 100, false, false);
            effect.setNoCounter(true);
            player.playSound(ModSounds.NIGHT_VISION.get(), 1F, 0.8F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
            player.addEffect(effect);
        } else {
            player.removeEffect(MobEffects.NIGHT_VISION);
        }

        cap.setNightVision(enable);
    }

}
