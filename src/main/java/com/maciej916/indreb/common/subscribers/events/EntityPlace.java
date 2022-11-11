package com.maciej916.indreb.common.subscribers.events;

import com.maciej916.indreb.IndReb;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityPlace {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityPlaceEvent(BlockEvent.EntityPlaceEvent event) {

    }
}
