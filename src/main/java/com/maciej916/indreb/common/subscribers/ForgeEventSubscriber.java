package com.maciej916.indreb.common.subscribers;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.capabilities.player.PlayerCapability;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.energy.provider.EnergyCore;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void levelCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        event.addCapability(new ResourceLocation(IndReb.MODID, "energy_core"), new EnergyCore(level));
    }

    @SubscribeEvent
    public static void levelTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide()) return;
        event.level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(IEnergyCore::tick);
    }

    @SubscribeEvent
    public static void entityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(IndReb.MODID, "player"), new PlayerCapability());
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(oldCapability -> event.getOriginal().getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(newCapability -> newCapability.clone(oldCapability)));
    }
}
