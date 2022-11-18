package com.maciej916.indreb.common.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.client.keys.impl.NightVisionHandler;
import com.maciej916.indreb.common.proxy.ModProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {

	@SubscribeEvent
	public static void clientKeyInput(InputEvent.Key event) {
		Minecraft minecraft = ModProxy.PROXY.getClient();

		NightVisionHandler.toggleNightVision(minecraft.player);
	}

	@SubscribeEvent
	public static void playerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		NightVisionHandler.checkNightVision(player);
	}

}
