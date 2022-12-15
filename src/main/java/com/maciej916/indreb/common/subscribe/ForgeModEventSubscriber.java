package com.maciej916.indreb.common.subscribe;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ForgeModEventSubscriber {

    @SubscribeEvent
    public static void onConfigLoading(final ModConfigEvent.Loading configEvent) {
        if (configEvent.getConfig().getType() == ModConfig.Type.SERVER) {
            RadiationHelper.init(false);
        }
    }

    @SubscribeEvent
    public static void onConfigReloading(final ModConfigEvent.Reloading configEvent) {
        if (configEvent.getConfig().getType() == ModConfig.Type.SERVER) {
            RadiationHelper.init(true);
        }
    }

}
