package com.maciej916.indreb.common.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.battery_box.ScreenBatteryBox;
import com.maciej916.indreb.common.block.impl.charge_pad.ScreenChargePad;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.ScreenCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.ScreenGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.ScreenGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.ScreenSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.ScreenSolarGenerator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.ScreenAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.ScreenCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.ScreenCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.ScreenCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.ScreenElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.ScreenExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.ScreenExtruder;
import com.maciej916.indreb.common.block.impl.machines.fermenter.ScreenFermenter;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.ScreenFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.ScreenIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.matter_fabricator.ScreenMatterFabricator;
import com.maciej916.indreb.common.block.impl.machines.metal_former.ScreenMetalFormer;
import com.maciej916.indreb.common.block.impl.machines.ore_washing_plant.ScreenOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.ScreenPatternStorage;
import com.maciej916.indreb.common.block.impl.machines.recycler.ScreenRecycler;
import com.maciej916.indreb.common.block.impl.machines.replicator.ScreenReplicator;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ScreenSawmill;
import com.maciej916.indreb.common.block.impl.machines.scanner.ScreenScanner;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.ScreenThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.transformer.ScreenTransformer;
import com.maciej916.indreb.common.client.keys.ModKeys;
import com.maciej916.indreb.common.client.renderer.EnergyInfoRenderer;
import com.maciej916.indreb.common.client.renderer.ITntRenderer;
import com.maciej916.indreb.common.fluid.*;
import com.maciej916.indreb.common.item.base.ElectricItem;
import com.maciej916.indreb.common.item.base.FluidItem;
import com.maciej916.indreb.common.item.impl.MemoryCardItem;
import com.maciej916.indreb.common.item.impl.nano.ItemNanosaber;
import com.maciej916.indreb.common.item.impl.upgrade.ItemDirectionalUpgrade;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModEntityTypes;
import com.maciej916.indreb.common.registries.ModMenuTypes;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
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
		event.register(new FluidItem.Colors(), ModItems.FLUID_CELL.get());
	}

	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {

			MinecraftForge.EVENT_BUS.addListener(EnergyInfoRenderer::render);

			EntityRenderers.register(ModEntityTypes.PRIMED_ITNT.get(), ITntRenderer::new);

			MenuScreens.register(ModMenuTypes.GENERATOR.get(), ScreenGenerator::new);
			MenuScreens.register(ModMenuTypes.SOLAR_GENERATOR.get(), ScreenSolarGenerator::new);
			MenuScreens.register(ModMenuTypes.GEO_GENERATOR.get(), ScreenGeoGenerator::new);
			MenuScreens.register(ModMenuTypes.CRYSTALLINE_GENERATOR.get(), ScreenCrystallineGenerator::new);
			MenuScreens.register(ModMenuTypes.SEMIFLUID_GENERATOR.get(), ScreenSemifluidGenerator::new);

			MenuScreens.register(ModMenuTypes.BATTERY_BOX.get(), ScreenBatteryBox::new);
			MenuScreens.register(ModMenuTypes.TRANSFORMER.get(), ScreenTransformer::new);
			MenuScreens.register(ModMenuTypes.CHARGE_PAD.get(), ScreenChargePad::new);

			MenuScreens.register(ModMenuTypes.IRON_FURNACE.get(), ScreenIronFurnace::new);
			MenuScreens.register(ModMenuTypes.ELECTRIC_FURNACE.get(), ScreenElectricFurnace::new);

			MenuScreens.register(ModMenuTypes.CRUSHER.get(), ScreenCrusher::new);
			MenuScreens.register(ModMenuTypes.COMPRESSOR.get(), ScreenCompressor::new);
			MenuScreens.register(ModMenuTypes.EXTRACTOR.get(), ScreenExtractor::new);
			MenuScreens.register(ModMenuTypes.SAWMILL.get(), ScreenSawmill::new);
			MenuScreens.register(ModMenuTypes.EXTRUDER.get(), ScreenExtruder::new);
			MenuScreens.register(ModMenuTypes.CANNING_MACHINE.get(), ScreenCanningMachine::new);
			MenuScreens.register(ModMenuTypes.FLUID_ENRICHER.get(), ScreenFluidEnricher::new);
			MenuScreens.register(ModMenuTypes.RECYCLER.get(), ScreenRecycler::new);
			MenuScreens.register(ModMenuTypes.ALLOY_SMELTER.get(), ScreenAlloySmelter::new);
			MenuScreens.register(ModMenuTypes.FERMENTER.get(), ScreenFermenter::new);
			MenuScreens.register(ModMenuTypes.ORE_WASHING_PLANT.get(), ScreenOreWashingPlant::new);
			MenuScreens.register(ModMenuTypes.MATTER_FABRICATOR.get(), ScreenMatterFabricator::new);
			MenuScreens.register(ModMenuTypes.THERMAL_CENTRIFUGE.get(), ScreenThermalCentrifuge::new);
			MenuScreens.register(ModMenuTypes.PATTERN_STORAGE.get(), ScreenPatternStorage::new);
			MenuScreens.register(ModMenuTypes.SCANNER.get(), ScreenScanner::new);
			MenuScreens.register(ModMenuTypes.REPLICATOR.get(), ScreenReplicator::new);
			MenuScreens.register(ModMenuTypes.METAL_FORMER.get(), ScreenMetalFormer::new);

			//	TODO Change in model
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.REINFORCED_GLASS.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.RUBBER_SAPLING.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_SCAFFOLDING.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.CONSTRUCTION_FOAM.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.get(), RenderType.cutout());

			ItemProperties.register(ModItems.BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ADVANCED_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ENERGY_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.LAPOTRON_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));

			ItemProperties.register(ModItems.CHARGING_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ADVANCED_CHARGING_BATTERY.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.CHARGING_ENERGY_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.CHARGING_LAPOTRON_CRYSTAL.get(), new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));

			ItemProperties.register(ModItems.NANO_SABER.get(), new ResourceLocation(IndReb.MODID, "active"), (stack, level, living, id) -> ItemNanosaber.isActivated(stack));

			ItemProperties.register(ModItems.EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_FLUID_EJECTOR_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_FLUID_PULLING_UPGRADE.get(), new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));

			ItemBlockRenderTypes.setRenderLayer(Biogas.STILL_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Biogas.FLOWING_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Biomass.STILL_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Biomass.FLOWING_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ConstructionFoam.STILL_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ConstructionFoam.FLOWING_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ReinforcedConstructionFoam.STILL_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ReinforcedConstructionFoam.FLOWING_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Coolant.STILL_FLUID, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Coolant.FLOWING_FLUID, RenderType.translucent());
		});
	}
}
