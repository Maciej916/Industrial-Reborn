package com.maciej916.indreb.common.capability;

import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyTransmitter;
import com.maciej916.indreb.common.capability.entity.IEntityCapability;
import com.maciej916.indreb.common.capability.item.interfaces.IBaseItemStackHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IElectricItemStackHandler;
import com.maciej916.indreb.common.capability.item.interfaces.IUpgradesItemStackHandler;
import com.maciej916.indreb.common.capability.player.IPlayerCapability;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCapabilities {

    public static final Capability<IEnergyCore> ENERGY_CORE = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IEnergyStorage> ENERGY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IEnergyTransmitter> ENERGY_TRANSMITTER = CapabilityManager.get(new CapabilityToken<>(){});

    public static final Capability<IBaseItemStackHandler> BASE_ITEM_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IElectricItemStackHandler> ELECTRIC_ITEM_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IUpgradesItemStackHandler> UPGRADES_ITEM_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});

    public static final Capability<IPlayerCapability> PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IEntityCapability> ENTITY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

//    public static final Capability<IScannerResult> SCANNER_RESULT = CapabilityManager.get(new CapabilityToken<>(){});
//    public static final Capability<IReactorComponentCapability> REACTOR_ITEM = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IEnergyCore.class);
        event.register(IEnergyStorage.class);
        event.register(IEnergyTransmitter.class);

        event.register(IBaseItemStackHandler.class);
        event.register(IElectricItemStackHandler.class);
        event.register(IUpgradesItemStackHandler.class);

        event.register(IPlayerCapability.class);
        event.register(IEntityCapability.class);

//        event.register(IScannerResult.class);
//        event.register(IReactorComponentCapability.class);
    }
}
