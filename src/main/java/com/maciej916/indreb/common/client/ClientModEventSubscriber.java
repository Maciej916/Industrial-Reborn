package com.maciej916.indreb.common.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.item.base.BaseFluidItem;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.block.impl.generator.generator.ScreenGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.ScreenGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.semifluid_generator.ScreenSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.ScreenSolarPanel;
import com.maciej916.indreb.common.client.keys.ModKeys;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onKeyRegister(RegisterKeyMappingsEvent event) {
		event.register(ModKeys.NIGHT_VISION_KEY);
	}


	@SubscribeEvent
	public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
		event.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) - 1000 : FoliageColor.getEvergreenColor(), ModBlocks.RUBBER_LEAVES.get());
	}

	@SubscribeEvent
	public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
		event.register((stack, tintIndex) -> event.getBlockColors().getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex), ModBlocks.RUBBER_LEAVES.get());
		event.register(new BaseFluidItem.Colors(), ModItems.FLUID_CELL.get());
	}

	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {

//			MinecraftForge.EVENT_BUS.addListener(EnergyInfoRenderer::render);

//			EntityRenderers.register(ModEntityTypes.PRIMED_ITNT.get(), ITntRenderer::new);

			MenuScreens.register(ModMenuTypes.GENERATOR.get(), ScreenGenerator::new);
			MenuScreens.register(ModMenuTypes.SOLAR_PANEL.get(), ScreenSolarPanel::new);
			MenuScreens.register(ModMenuTypes.GEO_GENERATOR.get(), ScreenGeoGenerator::new);
			MenuScreens.register(ModMenuTypes.SEMIFLUID_GENERATOR.get(), ScreenSemifluidGenerator::new);
//
//			MenuScreens.register(ModMenuTypes.BATTERY_BOX.get(), ScreenBatteryBox::new);
//			MenuScreens.register(ModMenuTypes.TRANSFORMER.get(), ScreenTransformer::new);
//			MenuScreens.register(ModMenuTypes.CHARGE_PAD.get(), ScreenChargePad::new);
//
//			MenuScreens.register(ModMenuTypes.IRON_FURNACE.get(), ScreenIronFurnace::new);
//			MenuScreens.register(ModMenuTypes.ELECTRIC_FURNACE.get(), ScreenElectricFurnace::new);
//
//			MenuScreens.register(ModMenuTypes.CRUSHER.get(), ScreenCrusher::new);
//			MenuScreens.register(ModMenuTypes.COMPRESSOR.get(), ScreenCompressor::new);
//			MenuScreens.register(ModMenuTypes.EXTRACTOR.get(), ScreenExtractor::new);
//			MenuScreens.register(ModMenuTypes.SAWMILL.get(), ScreenSawmill::new);
//			MenuScreens.register(ModMenuTypes.EXTRUDER.get(), ScreenExtruder::new);
//			MenuScreens.register(ModMenuTypes.CANNING_MACHINE.get(), ScreenCanningMachine::new);
//			MenuScreens.register(ModMenuTypes.FLUID_ENRICHER.get(), ScreenFluidEnricher::new);
//			MenuScreens.register(ModMenuTypes.RECYCLER.get(), ScreenRecycler::new);
//			MenuScreens.register(ModMenuTypes.ALLOY_SMELTER.get(), ScreenAlloySmelter::new);
//			MenuScreens.register(ModMenuTypes.FERMENTER.get(), ScreenFermenter::new);
//			MenuScreens.register(ModMenuTypes.ORE_WASHING_PLANT.get(), ScreenOreWashingPlant::new);
//			MenuScreens.register(ModMenuTypes.MATTER_FABRICATOR.get(), ScreenMatterFabricator::new);
//			MenuScreens.register(ModMenuTypes.THERMAL_CENTRIFUGE.get(), ScreenThermalCentrifuge::new);
//			MenuScreens.register(ModMenuTypes.PATTERN_STORAGE.get(), ScreenPatternStorage::new);
//			MenuScreens.register(ModMenuTypes.SCANNER.get(), ScreenScanner::new);
//			MenuScreens.register(ModMenuTypes.REPLICATOR.get(), ScreenReplicator::new);
//			MenuScreens.register(ModMenuTypes.METAL_FORMER.get(), ScreenMetalFormer::new);
//
//			MenuScreens.register(ModMenuTypes.NUCLEAR_REACTOR.get(), ScreenNuclearReactor::new);

//
//			ItemProperties.register(ModItems.BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.ADVANCED_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.ENERGY_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.LAPOTRON_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//
//			ItemProperties.register(ModItems.CHARGING_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.ADVANCED_CHARGING_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.CHARGING_ENERGY_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//			ItemProperties.register(ModItems.CHARGING_LAPOTRON_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
//
//			ItemProperties.register(ModItems.NANO_SABER.get(), new ResourceLocation(IndReb.MODID, "active"), (stack, level, living, id) -> ItemNanosaber.isActivated(stack));
//
//			ItemProperties.register(ModItems.EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.ADVANCED_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.ADVANCED_PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.ADVANCED_FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.ADVANCED_FLUID_PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
//			ItemProperties.register(ModItems.ADVANCED_FLUID_PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));

//			ItemBlockRenderTypes.setRenderLayer(Biogas.STILL_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(Biogas.FLOWING_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(Biomass.STILL_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(Biomass.FLOWING_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(ConstructionFoam.STILL_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(ConstructionFoam.FLOWING_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(ReinforcedConstructionFoam.STILL_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(ReinforcedConstructionFoam.FLOWING_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(Coolant.STILL_FLUID, RenderType.translucent());
//			ItemBlockRenderTypes.setRenderLayer(Coolant.FLOWING_FLUID, RenderType.translucent());
		});
	}
}
