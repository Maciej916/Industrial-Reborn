package com.maciej916.indreb;

import com.maciej916.indreb.common.attributes.ModAttributes;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.config.ModConfig;
import com.maciej916.indreb.common.effects.ModEffects;
import com.maciej916.indreb.common.entity.ModEntityTypes;
import com.maciej916.indreb.common.fluid.ModFluids;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.loot.ModLootModifiers;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.proxy.ModProxy;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.screen.ModMenuTypes;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.world.ModConfiguredFeatures;
import com.maciej916.indreb.common.world.ModPlacedFeatures;
import com.maciej916.indreb.integration.top.TOPPlugin;
import com.mojang.logging.LogUtils;
import mcjty.theoneprobe.TheOneProbe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(IndReb.MODID)
public class IndReb {
    public static final String MODID = "indreb";
    public static final Logger LOGGER = LogUtils.getLogger();

    public IndReb() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModFluids.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModEffects.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        ModRecipeType.register(modEventBus);
        ModRecipeSerializer.register(modEventBus);

        ModSounds.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModConfig.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        ModProxy.init();
        ModNetworking.init();

        if (ModList.get().isLoaded(TheOneProbe.MODID)) {
            TOPPlugin.registerCompatibility();
        }
    }

}
