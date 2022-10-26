package com.maciej916.indreb;

import com.maciej916.indreb.common.config.ModConfig;
import com.maciej916.indreb.common.fluid.ModFluids;
import com.maciej916.indreb.common.loot.ModLootModifiers;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.proxy.ModProxy;
import com.maciej916.indreb.common.registries.*;
import com.maciej916.indreb.common.world.feature.ModConfiguredFeatures;
import com.maciej916.indreb.common.world.feature.ModPlacedFeatures;
import com.maciej916.indreb.datagen.DataGenerators;
import com.maciej916.indreb.integration.top.TOPPlugin;
import mcjty.theoneprobe.TheOneProbe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("indreb")
public class IndReb {
    public static final String MODID = "indreb";
    public static final Logger LOGGER = LogManager.getLogger();

    public IndReb() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModFluids.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipeType.register(modEventBus);
        ModRecipeSerializer.register(modEventBus);

        ModSounds.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModConfig.register();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        ModProxy.init();
        ModNetworking.init();

        if (ModList.get().isLoaded(TheOneProbe.MODID)) {
            TOPPlugin.registerCompatibility();
        }
    }
}
