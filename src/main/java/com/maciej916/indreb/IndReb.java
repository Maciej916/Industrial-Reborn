package com.maciej916.indreb;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.config.ModConfig;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.proxy.ModProxy;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.world.ModConfiguredFeatures;
import com.maciej916.indreb.common.world.ModPlacedFeatures;
import com.mojang.logging.LogUtils;
import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(IndReb.MODID)
public class IndReb {
    public static final String MODID = "indreb";
    private static final Logger LOGGER = LogUtils.getLogger();

    public IndReb() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
//        ModFluids.register(modEventBus);

//        ModBlockEntities.register(modEventBus);
//        ModEntityTypes.register(modEventBus);
//        ModMenuTypes.register(modEventBus);

//        ModRecipeType.register(modEventBus);
//        ModRecipeSerializer.register(modEventBus);

        ModSounds.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

//        ModLootModifiers.register(modEventBus);

        ModConfig.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        ModProxy.init();
        ModNetworking.init();

        if (ModList.get().isLoaded(TheOneProbe.MODID)) {
//            TOPPlugin.registerCompatibility();
        }
    }
}
