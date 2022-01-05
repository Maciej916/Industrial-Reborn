package com.maciej916.indreb.common.subscribers;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.energy.provider.EnergyCore;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        event.addCapability(new ResourceLocation(IndReb.MODID, "energy_core"), new EnergyCore(level));
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.world.isClientSide()) return;
        event.world.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(IEnergyCore::tick);
//        CapabilityUtil.getCapabilityHelper(event.world, ModCapabilities.ENERGY_CORE, null).ifPresent(IEnergyCore::tick);
    }

}
