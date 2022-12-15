package com.maciej916.indreb.common.subscribe.event;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEntityUseItem {

    @SubscribeEvent
    public static void event(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getItem();
        Item item = stack.getItem();
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            if (RadiationHelper.RADIATION_FOOD.containsKey(item)) {
                double value = RadiationHelper.RADIATION_FOOD.get(item);
                player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
                    cap.removePlayerRads(Math.abs(value));
                });
            }
        }
    }
}
