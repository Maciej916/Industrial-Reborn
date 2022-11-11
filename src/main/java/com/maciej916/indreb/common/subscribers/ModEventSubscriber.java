package com.maciej916.indreb.common.subscribers;

import com.maciej916.indreb.IndReb;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

//    @SubscribeEvent
//    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
//        final ModConfig config = event.getConfig();
//        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
//            ConfigHelper.bakeClient();
//        } else if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
//            ConfigHelper.bakeCommon();
//        }
//    }


}
