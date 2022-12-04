package com.maciej916.indreb.common.subscribe.event;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebContainerMenu;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerContainer {

    @SubscribeEvent
    public static void openContainer(PlayerContainerEvent.Open event) {
       if (event.getContainer() instanceof IndRebContainerMenu indRebContainerMenu) {
           indRebContainerMenu.addContainerListener(event.getEntity());
       }
    }

    @SubscribeEvent
    public static void closeContainer(PlayerContainerEvent.Close event) {
        if (event.getContainer() instanceof IndRebContainerMenu indRebContainerMenu) {
            indRebContainerMenu.removeContainerListener(event.getEntity());
        }
    }
}
