package com.maciej916.indreb;

import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.registries.Config;
import com.maciej916.indreb.common.registries.ModGeneration;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("indreb")
public class IndReb
{
    public static final String MODID = "indreb";
    public static final Logger LOGGER = LogManager.getLogger();

    public IndReb() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        Config.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        ModNetworking.setup();
        ModGeneration.init();
    }
}
