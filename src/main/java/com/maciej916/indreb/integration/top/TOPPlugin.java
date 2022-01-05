package com.maciej916.indreb.integration.top;

import com.maciej916.indreb.integration.top.provider.TOPEnergyProvider;
import com.maciej916.indreb.integration.top.provider.TOPTierProvider;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TOPPlugin {

    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new TOPEnergyProvider());
        oneProbe.registerProvider(new TOPTierProvider());
    }


}
