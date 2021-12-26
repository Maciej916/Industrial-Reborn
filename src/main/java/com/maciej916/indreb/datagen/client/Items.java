package com.maciej916.indreb.datagen.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, IndReb.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerOres();
        registerRaw();
        registerIngots();
        registerRods();
        registerCrushed();
        registerPlates();
        registerDusts();
        registerPainter();
        registerConstructionFoam();
        registerTools();
        registerElectricTools();
        registerBatteries();
        registerArmour();
        registerBlocks();
        registerRubber();
        registerGenerators();
        registerMachines();
        registerBatteryBox();
        registerCrafting();
        registerCables();
        registerCrop();

//        createGeneratedTexture(ModItems.FLUID_CELL, "fluid_cell");

        createWithBlock(ModBlocks.BASIC_MACHINE_CASING.getBlock(), "basic_machine_casing");
        createWithBlock(ModBlocks.ADVANCED_MACHINE_CASING.getBlock(), "advanced_machine_casing");

        createWithBlock(ModBlocks.REINFORCED_GLASS.getBlock(), "reinforced_glass");
        createWithBlock(ModBlocks.REINFORCED_STONE.getBlock(), "reinforced_stone");
        createWithBlock(ModBlocks.REINFORCED_STONE_SLAB.getBlock(), "reinforced_stone_slab");
        createWithBlock(ModBlocks.REINFORCED_STONE_STAIRS.getBlock(), "reinforced_stone_stairs");

        createWithBlock(ModBlocks.IRON_SCAFFOLDING.getBlock(), "iron_scaffolding");
        createWithBlock(ModBlocks.IRON_FENCE.getBlock(), "iron_fence");

        createWithBlock(ModBlocks.LUMINATOR.getBlock(), "luminator");

        createGeneratedTexture(ModItems.IRIDIUM_SHARD, "resource/iridium_shard");
        createGeneratedTexture(ModItems.IRIDIUM, "resource/iridium");
    }

    protected void registerOres() {
        createWithBlock(ModBlocks.TIN_ORE.getBlock(), "tin_ore");
        createWithBlock(ModBlocks.DEEPSLATE_TIN_ORE.getBlock(), "deepslate_tin_ore");
    }

    protected void registerRaw() {
        createGeneratedTexture(ModItems.RAW_TIN, "raw/tin");
    }

    protected void registerIngots() {
        createGeneratedTexture(ModItems.TIN_INGOT, "ingot/tin");
        createGeneratedTexture(ModItems.BRONZE_INGOT, "ingot/bronze");
        createGeneratedTexture(ModItems.MIXED_METAL_INGOT, "ingot/mixed_metal");
        createGeneratedTexture(ModItems.SILVER_INGOT, "ingot/silver");
        createGeneratedTexture(ModItems.STEEL_INGOT, "ingot/steel");
    }

    protected void registerRods() {
        createGeneratedTexture(ModItems.IRON_ROD, "rod/iron");
    }

    protected void registerCrushed() {
        createGeneratedTexture(ModItems.CRUSHED_TIN, "crushed/tin");
        createGeneratedTexture(ModItems.CRUSHED_COPPER, "crushed/copper");
        createGeneratedTexture(ModItems.CRUSHED_GOLD, "crushed/gold");
        createGeneratedTexture(ModItems.CRUSHED_IRON, "crushed/iron");
    }

    protected void registerPlates() {
        createGeneratedTexture(ModItems.BRONZE_PLATE, "plate/bronze");
        createGeneratedTexture(ModItems.GOLD_PLATE, "plate/gold");
        createGeneratedTexture(ModItems.IRON_PLATE, "plate/iron");
        createGeneratedTexture(ModItems.IRIDIUM_PLATE, "plate/iridium");
        createGeneratedTexture(ModItems.TIN_PLATE, "plate/tin");
        createGeneratedTexture(ModItems.STEEL_PLATE, "plate/steel");
        createGeneratedTexture(ModItems.COPPER_PLATE, "plate/copper");
    }

    protected void registerDusts() {
        createGeneratedTexture(ModItems.STONE_DUST, "dust/stone");
        createGeneratedTexture(ModItems.DEEPSLATE_DUST, "dust/deepslate");
        createGeneratedTexture(ModItems.COAL_DUST, "dust/coal");
        createGeneratedTexture(ModItems.DIAMOND_DUST, "dust/diamond");
        createGeneratedTexture(ModItems.ENERGIUM_DUST, "dust/energium");
        createGeneratedTexture(ModItems.SILVER_DUST, "dust/silver");
        createGeneratedTexture(ModItems.SAWDUST, "dust/sawdust");
   }

    protected void registerPainter() {
        createHandheldTexture(ModItems.PAINTER, "tool/painter/painter");
        createHandheldTexture(ModItems.PAINTER_WHITE, "tool/painter/painter_white");
        createHandheldTexture(ModItems.PAINTER_RED, "tool/painter/painter_red");
        createHandheldTexture(ModItems.PAINTER_ORANGE, "tool/painter/painter_orange");
        createHandheldTexture(ModItems.PAINTER_PINK, "tool/painter/painter_pink");
        createHandheldTexture(ModItems.PAINTER_YELLOW, "tool/painter/painter_yellow");
        createHandheldTexture(ModItems.PAINTER_LIME, "tool/painter/painter_lime");
        createHandheldTexture(ModItems.PAINTER_GREEN, "tool/painter/painter_green");
        createHandheldTexture(ModItems.PAINTER_LIGHT_BLUE, "tool/painter/painter_light_blue");
        createHandheldTexture(ModItems.PAINTER_CYAN, "tool/painter/painter_cyan");
        createHandheldTexture(ModItems.PAINTER_BLUE, "tool/painter/painter_blue");
        createHandheldTexture(ModItems.PAINTER_MAGENTA, "tool/painter/painter_magenta");
        createHandheldTexture(ModItems.PAINTER_PURPLE, "tool/painter/painter_purple");
        createHandheldTexture(ModItems.PAINTER_BROWN, "tool/painter/painter_brown");
        createHandheldTexture(ModItems.PAINTER_GRAY, "tool/painter/painter_gray");
        createHandheldTexture(ModItems.PAINTER_LIGHT_GRAY, "tool/painter/painter_light_gray");
        createHandheldTexture(ModItems.PAINTER_BLACK, "tool/painter/painter_black");
    }

    protected void registerConstructionFoam() {
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM.getBlock(), "construction_foam");
        createWithBlock(ModBlocks.REINFORCED_CONSTRUCTION_FOAM.getBlock(), "reinforced_construction_foam");

        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.getBlock(), "construction_foam_wall_white");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.getBlock(), "construction_foam_wall_red");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.getBlock(), "construction_foam_wall_orange");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.getBlock(), "construction_foam_wall_pink");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.getBlock(), "construction_foam_wall_yellow");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.getBlock(), "construction_foam_wall_lime");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.getBlock(), "construction_foam_wall_green");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.getBlock(), "construction_foam_wall_light_blue");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.getBlock(), "construction_foam_wall_cyan");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.getBlock(), "construction_foam_wall_blue");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.getBlock(), "construction_foam_wall_magenta");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.getBlock(), "construction_foam_wall_purple");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.getBlock(), "construction_foam_wall_brown");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.getBlock(), "construction_foam_wall_gray");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getBlock(), "construction_foam_wall_light_gray");
        createWithBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.getBlock(), "construction_foam_wall_black");
    }

    protected void registerTools() {
        createGeneratedTexture(ModItems.BRONZE_AXE, "tool/bronze_axe");
        createGeneratedTexture(ModItems.BRONZE_HOE, "tool/bronze_hoe");
        createGeneratedTexture(ModItems.BRONZE_PICKAXE, "tool/bronze_pickaxe");
        createGeneratedTexture(ModItems.BRONZE_SHOVEL, "tool/bronze_shovel");
        createGeneratedTexture(ModItems.BRONZE_SWORD, "tool/bronze_sword");

        createGeneratedTexture(ModItems.CUTTER, "tool/cutter");
        createGeneratedTexture(ModItems.WRENCH, "tool/wrench");
        createGeneratedTexture(ModItems.HAMMER, "tool/hammer");
        createGeneratedTexture(ModItems.TREETAP, "tool/treetap");
        createGeneratedTexture(ModItems.FOAM_SPRAYER, "tool/foam_sprayer");
    }

    protected void registerElectricTools() {
        createWithActive(ModItems.NANO_SABER,"tool/electric/nano_saber");
        createWithActive(ModItems.QUANTUM_SABER,"tool/electric/quantum_saber");
        createHandheldTexture(ModItems.ELECTRIC_TREETAP, "tool/electric/electric_treetap");
        createHandheldTexture(ModItems.ELECTRIC_WRENCH, "tool/electric/electric_wrench");
        createHandheldTexture(ModItems.CHAINSAW, "tool/electric/chainsaw");
        createHandheldTexture(ModItems.DIAMOND_CHAINSAW, "tool/electric/diamond_chainsaw");
        createHandheldTexture(ModItems.IRIDIUM_CHAINSAW, "tool/electric/iridium_chainsaw");
        createHandheldTexture(ModItems.MINING_DRILL, "tool/electric/mining_drill");
        createHandheldTexture(ModItems.DIAMOND_DRILL, "tool/electric/diamond_drill");
        createHandheldTexture(ModItems.IRIDIUM_DRILL, "tool/electric/iridium_drill");
        createHandheldTexture(ModItems.WIND_METER, "tool/electric/wind_meter");
        createHandheldTexture(ModItems.IE_METER, "tool/electric/ie_meter");
        createHandheldTexture(ModItems.ELECTRIC_HOE, "tool/electric/electric_hoe");
        createHandheldTexture(ModItems.MULTI_TOOL, "tool/electric/multi_tool");
    }

    protected void registerBatteries() {
        createWithChargeRatio(ModItems.BATTERY,"battery/battery");
        createWithChargeRatio(ModItems.ADVANCED_BATTERY,"battery/advanced_battery");
        createWithChargeRatio(ModItems.ENERGY_CRYSTAL,"battery/energy_crystal");
        createWithChargeRatio(ModItems.LAPOTRON_CRYSTAL,"battery/lapotron_crystal");
    }

    protected void registerArmour() {
        createGeneratedTexture(ModItems.BRONZE_HELMET, "armor/bronze_helmet");
        createGeneratedTexture(ModItems.BRONZE_CHESTPLATE, "armor/bronze_chestplate");
        createGeneratedTexture(ModItems.BRONZE_LEGGINGS, "armor/bronze_leggings");
        createGeneratedTexture(ModItems.BRONZE_BOOTS, "armor/bronze_boots");

        createGeneratedTexture(ModItems.NANO_HELMET, "armor/nano_helmet");
        createGeneratedTexture(ModItems.NANO_CHESTPLATE, "armor/nano_chestplate");
        createGeneratedTexture(ModItems.NANO_LEGGINGS, "armor/nano_leggings");
        createGeneratedTexture(ModItems.NANO_BOOTS, "armor/nano_boots");

        createGeneratedTexture(ModItems.NIGHTVISION_GOGGLES, "armor/nightvision_goggles");
        createGeneratedTexture(ModItems.RUBBER_BOOTS, "armor/rubber_boots");
    }

    protected void registerBlocks() {
        createWithBlock(ModBlocks.BRONZE_BLOCK.getBlock(), "bronze_block");
        createWithBlock(ModBlocks.SILVER_BLOCK.getBlock(), "silver_block");
        createWithBlock(ModBlocks.STEEL_BLOCK.getBlock(), "steel_block");
        createWithBlock(ModBlocks.TIN_BLOCK.getBlock(), "tin_block");
    }

    protected void registerRubber() {
        createWithBlock(ModBlocks.RUBBER_WOOD.getBlock(), "rubber_wood/rubber_wood");
        createWithBlock(ModBlocks.RUBBER_LOG.getBlock(), "rubber_wood/rubber_log");
        createWithBlock(ModBlocks.RUBBER_LEAVES.getBlock(), "rubber_wood/rubber_leaves");
        createWithBlock(ModBlocks.RUBBER_STAIRS.getBlock(), "reinforced_stone");
        createWithBlock(ModBlocks.RUBBER_SLAB.getBlock(), "rubber_wood/rubber_slab");
        createWithBlock(ModBlocks.RUBBER_PLANKS.getBlock(), "rubber_wood/rubber_planks");

//        createWithBlock(ModBlocks.RUBBER_SHEET, "rubber_sheet");
//        createWithBlock(ModBlocks.RESIN_SHEET, "resin_sheet");

        createGeneratedBlockTexture(ModBlocks.RUBBER_SAPLING.getBlock(), "rubber_wood/rubber_sapling");
        createGeneratedTexture(ModItems.STICKY_RESIN, "resource/sticky_resin");
        createGeneratedTexture(ModItems.RUBBER, "resource/rubber");
    }

    protected void registerGenerators() {
        createWithBlock(ModBlocks.GENERATOR.getBlock(), "generator/generator");
        createWithBlock(ModBlocks.GEO_GENERATOR.getBlock(), "generator/geo_generator");
        createWithBlock(ModBlocks.SOLAR_GENERATOR.getBlock(), "generator/solar_generator");
        createWithBlock(ModBlocks.ADVANCED_SOLAR_GENERATOR.getBlock(), "generator/advanced_solar_generator");
        createWithBlock(ModBlocks.HYBRID_SOLAR_GENERATOR.getBlock(), "generator/hybrid_solar_generator");
        createWithBlock(ModBlocks.QUANTUM_SOLAR_GENERATOR.getBlock(), "generator/quantum_solar_generator");
//        createWithBlock(ModBlocks.CRYSTALLINE_GENERATOR.getBlock(), "generator/crystalline_generator");
        createWithBlock(ModBlocks.SEMIFLUID_GENERATOR.getBlock(), "semifluid_generator");
    }

    protected void registerMachines() {
        createWithBlock(ModBlocks.IRON_FURNACE.getBlock(), "machines/iron_furnace");
        createWithBlock(ModBlocks.ELECTRIC_FURNACE.getBlock(), "machines/electric_furnace");

        createWithBlock(ModBlocks.CRUSHER.getBlock(), "machines/crusher");
        createWithBlock(ModBlocks.COMPRESSOR.getBlock(), "machines/compressor");
        createWithBlock(ModBlocks.EXTRACTOR.getBlock(), "machines/extractor");
        createWithBlock(ModBlocks.SAWMILL.getBlock(), "machines/sawmill");
        createWithBlock(ModBlocks.EXTRUDER.getBlock(), "machines/extruder");
        createWithBlock(ModBlocks.ALLOY_SMELTER.getBlock(), "machines/alloy_smelter");
        createWithBlock(ModBlocks.CANNING_MACHINE.getBlock(), "canning_machine");
        createWithBlock(ModBlocks.RECYCLER.getBlock(), "recycler");
        createWithBlock(ModBlocks.FLUID_ENRICHER.getBlock(), "fluid_enricher");
        createWithBlock(ModBlocks.FERMENTER.getBlock(), "fermenter");
    }

    protected void registerBatteryBox() {
        createWithBlock(ModBlocks.BATTERY_BOX.getBlock(), "battery_box/battery_box");
        createWithBlock(ModBlocks.CESU.getBlock(), "battery_box/cesu");
        createWithBlock(ModBlocks.MFE.getBlock(), "battery_box/mfe");
        createWithBlock(ModBlocks.MFSU.getBlock(), "battery_box/mfsu");
    }

    protected void registerCrafting() {
        createGeneratedTexture(ModItems.CARBON_FIBERS, "crafting/carbon_fibers");
        createGeneratedTexture(ModItems.COMBINED_CARBON_FIBERS, "crafting/combined_carbon_fibers");
        createGeneratedTexture(ModItems.CARBON_PLATE, "crafting/carbon_plate");
        createGeneratedTexture(ModItems.ADVANCED_ALLOY, "crafting/advanced_alloy");
        createGeneratedTexture(ModItems.ELECTRONIC_CIRCUIT, "crafting/electronic_circuit");
        createGeneratedTexture(ModItems.ADVANCED_CIRCUIT, "crafting/advanced_circuit");
        createGeneratedTexture(ModItems.SMALL_POWER_UNIT, "crafting/small_power_unit");
        createGeneratedTexture(ModItems.POWER_UNIT, "crafting/power_unit");
        createGeneratedTexture(ModItems.COIL, "crafting/coil");
        createGeneratedTexture(ModItems.ELECTRIC_MOTOR, "crafting/electric_motor");
        createGeneratedTexture(ModItems.TIN_CAN, "crafting/tin_can");
        createGeneratedTexture(ModItems.FILLED_TIN_CAN, "crafting/filled_tin_can");
        createGeneratedTexture(ModItems.SCRAP, "crafting/scrap");
        createGeneratedTexture(ModItems.SCRAP_BOX, "crafting/scrap_box");
        createGeneratedTexture(ModItems.BIO_CHAFF, "crafting/bio_chaff");
        createGeneratedTexture(ModItems.FOAM_POWDER, "crafting/foam_powder");
        createGeneratedTexture(ModItems.REINFORCED_FOAM_POWDER, "crafting/reinforced_foam_powder");
        createGeneratedTexture(ModItems.HEAT_CONDUCTOR, "crafting/heat_conductor");
    }

    protected void registerCables() {
        createWithBlock(ModBlocks.TIN_CABLE.getBlock(), "cable/tin_cable_inventory");
        createWithBlock(ModBlocks.TIN_CABLE_INSULATED.getBlock(), "cable/tin_cable_insulated_inventory");
        createWithBlock(ModBlocks.COPPER_CABLE.getBlock(), "cable/copper_cable_inventory");
        createWithBlock(ModBlocks.COPPER_CABLE_INSULATED.getBlock(), "cable/copper_cable_insulated_inventory");
        createWithBlock(ModBlocks.GOLD_CABLE.getBlock(), "cable/gold_cable_inventory");
        createWithBlock(ModBlocks.GOLD_CABLE_INSULATED.getBlock(), "cable/gold_cable_insulated_inventory");
        createWithBlock(ModBlocks.HV_CABLE.getBlock(), "cable/hv_cable_inventory");
        createWithBlock(ModBlocks.HV_CABLE_INSULATED.getBlock(), "cable/hv_cable_insulated_inventory");
        createWithBlock(ModBlocks.GLASS_FIBRE_CABLE.getBlock(), "cable/glass_fibre_cable_inventory");
    }

    protected void registerCrop() {
        createGeneratedTexture(ModItems.FERTILIZER, "crop/fertilizer");
    }

    private ItemModelBuilder createGeneratedTexture(Item item, String path) {
        return singleTexture(item.getRegistryName().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createGeneratedBlockTexture(Block block, String path) {
        return singleTexture(block.getRegistryName().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createHandheldTexture(Item item, String path) {
        return singleTexture(item.getRegistryName().getPath(), new ResourceLocation("item/handheld"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createWithBlock(Block block, String path) {
        return withExistingParent(block.getRegistryName().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createWithActive(Item item, String path) {
        return getBuilder(item.getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + path)
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 0).model(createTestModel("handheld", path, "")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 1).model(createTestModel("handheld", path, "_active")).end();
    }

    private ItemModelBuilder createWithChargeRatio(Item item, String path) {
        return getBuilder(item.getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + path + "_0")
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 0).model(createTestModel("generated", path, "_0")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 1).model(createTestModel("generated", path, "_1")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 2).model(createTestModel("generated", path, "_2")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 3).model(createTestModel("generated", path, "_3")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 4).model(createTestModel("generated", path, "_4")).end();
    }

    private ItemModelBuilder createTestModel(String type, String path, String suffix) {
        return getBuilder("item/" + path + suffix).parent(getExistingFile(mcLoc("item/" + type)))
                .texture("layer0", "item/" + path + suffix);
    }

}
