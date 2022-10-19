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
import com.maciej916.indreb.common.block.impl.machines.recycler.ScreenRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ScreenSawmill;
import com.maciej916.indreb.common.block.impl.transformer.ScreenTransformer;
import com.maciej916.indreb.common.client.renderer.EnergyInfoRenderer;
import com.maciej916.indreb.common.item.base.ElectricItem;
import com.maciej916.indreb.common.item.impl.nano.ItemNanosaber;
import com.maciej916.indreb.common.item.impl.upgrade.ItemDirectionalUpgrade;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModContainers;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {

			MinecraftForge.EVENT_BUS.addListener(EnergyInfoRenderer::render);

			MenuScreens.register(ModContainers.GENERATOR, ScreenGenerator::new);
			MenuScreens.register(ModContainers.SOLAR_GENERATOR, ScreenSolarGenerator::new);
			MenuScreens.register(ModContainers.GEO_GENERATOR, ScreenGeoGenerator::new);
			MenuScreens.register(ModContainers.CRYSTALLINE_GENERATOR, ScreenCrystallineGenerator::new);
			MenuScreens.register(ModContainers.SEMIFLUID_GENERATOR, ScreenSemifluidGenerator::new);

			MenuScreens.register(ModContainers.BATTERY_BOX, ScreenBatteryBox::new);
			MenuScreens.register(ModContainers.TRANSFORMER, ScreenTransformer::new);
			MenuScreens.register(ModContainers.CHARGE_PAD, ScreenChargePad::new);

			MenuScreens.register(ModContainers.IRON_FURNACE, ScreenIronFurnace::new);
			MenuScreens.register(ModContainers.ELECTRIC_FURNACE, ScreenElectricFurnace::new);

			MenuScreens.register(ModContainers.CRUSHER, ScreenCrusher::new);
			MenuScreens.register(ModContainers.COMPRESSOR, ScreenCompressor::new);
			MenuScreens.register(ModContainers.EXTRACTOR, ScreenExtractor::new);
			MenuScreens.register(ModContainers.SAWMILL, ScreenSawmill::new);
			MenuScreens.register(ModContainers.EXTRUDER, ScreenExtruder::new);
			MenuScreens.register(ModContainers.CANNING_MACHINE, ScreenCanningMachine::new);
			MenuScreens.register(ModContainers.FLUID_ENRICHER, ScreenFluidEnricher::new);
			MenuScreens.register(ModContainers.RECYCLER, ScreenRecycler::new);
			MenuScreens.register(ModContainers.ALLOY_SMELTER, ScreenAlloySmelter::new);
			MenuScreens.register(ModContainers.FERMENTER, ScreenFermenter::new);


			ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS_FIBRE_CABLE.getBlock(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.RUBBER_SAPLING.getBlock(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.CONSTRUCTION_FOAM.getBlock(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getBlock(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.REINFORCED_GLASS.getBlock(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_SCAFFOLDING.getBlock(), RenderType.cutout());

			BlockColors blockColors = Minecraft.getInstance().getBlockColors();
			blockColors.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) - 1000: FoliageColor.getEvergreenColor(), ModBlocks.RUBBER_LEAVES.getBlock());
			ItemColors itemColors = Minecraft.getInstance().getItemColors();
			itemColors.register((stack, tintIndex) -> {
				BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
				return blockColors.getColor(BlockState, null, null, tintIndex);
			}, ModBlocks.RUBBER_LEAVES);



			ItemProperties.register(ModItems.BATTERY, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ADVANCED_BATTERY, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ENERGY_CRYSTAL, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.LAPOTRON_CRYSTAL, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));

			ItemProperties.register(ModItems.CHARGING_BATTERY, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.ADVANCED_CHARGING_BATTERY, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.CHARGING_ENERGY_CRYSTAL, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));
			ItemProperties.register(ModItems.CHARGING_LAPOTRON_CRYSTAL, new ResourceLocation(IndReb.MODID, "charge_ratio"), (stack, level, living, id) -> ElectricItem.getChargeRatioModel(stack));

			ItemProperties.register(ModItems.NANO_SABER, new ResourceLocation(IndReb.MODID, "active"), (stack, level, living, id) -> ItemNanosaber.isActivated(stack));

			ItemProperties.register(ModItems.EJECTOR_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.PULLING_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.FLUID_EJECTOR_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_EJECTOR_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_PULLING_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_FLUID_EJECTOR_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
			ItemProperties.register(ModItems.ADVANCED_FLUID_PULLING_UPGRADE, new ResourceLocation(IndReb.MODID, "direction"), (stack, level, living, id) -> ItemDirectionalUpgrade.getDirection(stack));
		});
	}
}
