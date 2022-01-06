package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.capabilities.player.IPlayerCapability;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.energy.interfaces.IEnergyTransmitter;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCapabilities {

    public static final Capability<IEnergyCore> ENERGY_CORE = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IEnergy> ENERGY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IEnergyTransmitter> ENERGY_TRANSMITTER = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IPlayerCapability> PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IEnergyCore.class);
        event.register(IEnergy.class);
        event.register(IEnergyTransmitter.class);
        event.register(IPlayerCapability.class);
    }
}
