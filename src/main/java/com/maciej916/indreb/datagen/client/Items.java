package com.maciej916.indreb.datagen.client;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
        registerPlates();
        registerDecoration();

        registerDusts();
        registerPurified();
        registerChunk();

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
        registerMisc();
        registerBatteryBox();
        registerCrafting();
        registerCables();
        registerCrop();
        registerUpgrades();
        registerTransformers();
        registerChargePad();
        registerReactor();
        registerExplosive();

//        createGeneratedTexture(ModItems.FLUID_CELL, "fluid_cell");

        createWithBlock(ModItems.BASIC_MACHINE_CASING, "basic_machine_casing");
        createWithBlock(ModItems.ADVANCED_MACHINE_CASING, "advanced_machine_casing");

        createWithBlock(ModItems.REINFORCED_GLASS, "reinforced_glass");
        createWithBlock(ModItems.REINFORCED_STONE, "reinforced_stone");
        createWithBlock(ModItems.REINFORCED_STONE_SLAB, "reinforced_stone_slab");
        createWithBlock(ModItems.REINFORCED_STONE_STAIRS, "reinforced_stone_stairs");

        createWithBlock(ModItems.IRON_SCAFFOLDING, "iron_scaffolding");
        createWithBlock(ModItems.IRON_FENCE, "iron_fence");

        createWithBlock(ModItems.LUMINATOR, "luminator");

        createGeneratedTexture(ModItems.IRIDIUM_SHARD, "resource/iridium_shard");
        createGeneratedTexture(ModItems.IRIDIUM, "resource/iridium");
    }

    protected void registerOres() {
        createWithBlock(ModItems.TIN_ORE, "tin_ore");
        createWithBlock(ModItems.DEEPSLATE_TIN_ORE, "deepslate_tin_ore");
        createWithBlock(ModItems.LEAD_ORE, "lead_ore");
        createWithBlock(ModItems.DEEPSLATE_LEAD_ORE, "deepslate_lead_ore");
        createWithBlock(ModItems.URANIUM_ORE, "uranium_ore");
        createWithBlock(ModItems.DEEPSLATE_URANIUM_ORE, "deepslate_uranium_ore");
        createWithBlock(ModItems.SILVER_ORE, "silver_ore");
        createWithBlock(ModItems.DEEPSLATE_SILVER_ORE, "deepslate_silver_ore");
    }

    protected void registerRaw() {
        createGeneratedTexture(ModItems.RAW_TIN, "raw/tin");
        createGeneratedTexture(ModItems.RAW_LEAD, "raw/lead");
        createGeneratedTexture(ModItems.RAW_URANIUM, "raw/uranium");
        createGeneratedTexture(ModItems.RAW_SILVER, "raw/silver");
    }

    protected void registerIngots() {
        createGeneratedTexture(ModItems.TIN_INGOT, "ingot/tin");
        createGeneratedTexture(ModItems.BRONZE_INGOT, "ingot/bronze");
        createGeneratedTexture(ModItems.MIXED_METAL_INGOT, "ingot/mixed_metal");
        createGeneratedTexture(ModItems.SILVER_INGOT, "ingot/silver");
        createGeneratedTexture(ModItems.STEEL_INGOT, "ingot/steel");
        createGeneratedTexture(ModItems.LEAD_INGOT, "ingot/lead");
        createGeneratedTexture(ModItems.URANIUM_INGOT, "ingot/uranium");
    }

    protected void registerRods() {
        createGeneratedTexture(ModItems.IRON_ROD, "rod/iron");
    }

    protected void registerPlates() {
        createGeneratedTexture(ModItems.BRONZE_PLATE, "plate/bronze");
        createGeneratedTexture(ModItems.GOLD_PLATE, "plate/gold");
        createGeneratedTexture(ModItems.IRON_PLATE, "plate/iron");
        createGeneratedTexture(ModItems.IRIDIUM_PLATE, "plate/iridium");
        createGeneratedTexture(ModItems.TIN_PLATE, "plate/tin");
        createGeneratedTexture(ModItems.STEEL_PLATE, "plate/steel");
        createGeneratedTexture(ModItems.COPPER_PLATE, "plate/copper");
        createGeneratedTexture(ModItems.LEAD_PLATE, "plate/lead");
        createGeneratedTexture(ModItems.LAPIS_LAZULI_PLATE, "plate/lapis_lazuli");
    }

    protected void registerDusts() {
        createGeneratedTexture(ModItems.STONE_DUST, "dust/stone");
        createGeneratedTexture(ModItems.DEEPSLATE_DUST, "dust/deepslate");
        createGeneratedTexture(ModItems.COAL_DUST, "dust/coal");
        createGeneratedTexture(ModItems.DIAMOND_DUST, "dust/diamond");
        createGeneratedTexture(ModItems.ENERGIUM_DUST, "dust/energium");
        createGeneratedTexture(ModItems.SAWDUST, "dust/sawdust");
        createGeneratedTexture(ModItems.SULFUR_DUST, "dust/sulfur");
        createGeneratedTexture(ModItems.MUD_PILE, "dust/mud_pile");
        createGeneratedTexture(ModItems.LAPIS_LAZULI_DUST, "dust/lapis_lazuli");

        createGeneratedTexture(ModItems.TIN_DUST, "dust/tin");
        createGeneratedTexture(ModItems.COPPER_DUST, "dust/copper");
        createGeneratedTexture(ModItems.IRON_DUST, "dust/iron");
        createGeneratedTexture(ModItems.GOLD_DUST, "dust/gold");
        createGeneratedTexture(ModItems.LEAD_DUST, "dust/lead");
        createGeneratedTexture(ModItems.URANIUM_DUST, "dust/uranium");
        createGeneratedTexture(ModItems.SILVER_DUST, "dust/silver");
   }

    protected void registerPurified() {
        createGeneratedTexture(ModItems.PURIFIED_TIN, "purified/tin");
        createGeneratedTexture(ModItems.PURIFIED_COPPER, "purified/copper");
        createGeneratedTexture(ModItems.PURIFIED_IRON, "purified/iron");
        createGeneratedTexture(ModItems.PURIFIED_GOLD, "purified/gold");
        createGeneratedTexture(ModItems.PURIFIED_LEAD, "purified/lead");
        createGeneratedTexture(ModItems.PURIFIED_URANIUM, "purified/uranium");
        createGeneratedTexture(ModItems.PURIFIED_SILVER, "purified/silver");
    }

    protected void registerChunk() {
        createGeneratedTexture(ModItems.TIN_CHUNK, "chunk/tin");
        createGeneratedTexture(ModItems.COPPER_CHUNK, "chunk/copper");
        createGeneratedTexture(ModItems.IRON_CHUNK, "chunk/iron");
        createGeneratedTexture(ModItems.GOLD_CHUNK, "chunk/gold");
        createGeneratedTexture(ModItems.LEAD_CHUNK, "chunk/lead");
        createGeneratedTexture(ModItems.URANIUM_CHUNK, "chunk/uranium");
        createGeneratedTexture(ModItems.SILVER_CHUNK, "chunk/silver");
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
        createWithBlock(ModItems.CONSTRUCTION_FOAM, "construction_foam");
        createWithBlock(ModItems.REINFORCED_CONSTRUCTION_FOAM, "reinforced_construction_foam");

        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_WHITE, "construction_foam_wall_white");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_RED, "construction_foam_wall_red");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_ORANGE, "construction_foam_wall_orange");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_PINK, "construction_foam_wall_pink");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_YELLOW, "construction_foam_wall_yellow");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIME, "construction_foam_wall_lime");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_GREEN, "construction_foam_wall_green");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE, "construction_foam_wall_light_blue");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_CYAN, "construction_foam_wall_cyan");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BLUE, "construction_foam_wall_blue");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_MAGENTA, "construction_foam_wall_magenta");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_PURPLE, "construction_foam_wall_purple");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BROWN, "construction_foam_wall_brown");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_GRAY, "construction_foam_wall_gray");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY, "construction_foam_wall_light_gray");
        createWithBlock(ModItems.CONSTRUCTION_FOAM_WALL_BLACK, "construction_foam_wall_black");
    }

    protected void registerTools() {
        createHandheldTexture(ModItems.BRONZE_AXE, "tool/bronze_axe");
        createHandheldTexture(ModItems.BRONZE_HOE, "tool/bronze_hoe");
        createHandheldTexture(ModItems.BRONZE_PICKAXE, "tool/bronze_pickaxe");
        createHandheldTexture(ModItems.BRONZE_SHOVEL, "tool/bronze_shovel");
        createHandheldTexture(ModItems.BRONZE_SWORD, "tool/bronze_sword");

        createHandheldTexture(ModItems.CUTTER, "tool/cutter");
        createHandheldTexture(ModItems.WRENCH, "tool/wrench");
        createHandheldTexture(ModItems.HAMMER, "tool/hammer");
        createHandheldTexture(ModItems.TREETAP, "tool/treetap");
        createHandheldTexture(ModItems.FOAM_SPRAYER, "tool/foam_sprayer");
        createHandheldTexture(ModItems.DEBUG_STICK, "tool/debug_stick");
        createHandheldTexture(ModItems.TOOL_BOX, "tool/tool_box");
    }

    protected void registerElectricTools() {
        createWithActive(ModItems.NANO_SABER,"tool/electric/nano_saber");
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
        createGeneratedTexture(ModItems.BASIC_TRANSPORTER, "tool/electric/basic_transporter");
        createGeneratedTexture(ModItems.ADVANCED_TRANSPORTER, "tool/electric/advanced_transporter");
    }

    protected void registerBatteries() {
        createWithChargeRatio(ModItems.BATTERY,"battery/battery");
        createWithChargeRatio(ModItems.ADVANCED_BATTERY,"battery/advanced_battery");
        createWithChargeRatio(ModItems.ENERGY_CRYSTAL,"battery/energy_crystal");
        createWithChargeRatio(ModItems.LAPOTRON_CRYSTAL,"battery/lapotron_crystal");

        createWithChargeRatio(ModItems.CHARGING_BATTERY,"battery/charging_battery");
        createWithChargeRatio(ModItems.ADVANCED_CHARGING_BATTERY,"battery/advanced_charging_battery");
        createWithChargeRatio(ModItems.CHARGING_ENERGY_CRYSTAL,"battery/charging_energy_crystal");
        createWithChargeRatio(ModItems.CHARGING_LAPOTRON_CRYSTAL,"battery/charging_lapotron_crystal");
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
        createWithBlock(ModItems.BRONZE_BLOCK, "bronze_block");
        createWithBlock(ModItems.SILVER_BLOCK, "silver_block");
        createWithBlock(ModItems.STEEL_BLOCK, "steel_block");
        createWithBlock(ModItems.TIN_BLOCK, "tin_block");
        createWithBlock(ModItems.LEAD_BLOCK, "lead_block");
    }

    protected void registerRubber() {
        createWithBlock(ModItems.RUBBER_WOOD, "rubber_wood/rubber_wood");
        createWithBlock(ModItems.RUBBER_LOG, "rubber_wood/rubber_log");
        createWithBlock(ModItems.RUBBER_LEAVES, "rubber_wood/rubber_leaves");
        createWithBlock(ModItems.RUBBER_STAIRS, "rubber_wood/rubber_stairs");
        createWithBlock(ModItems.RUBBER_SLAB, "rubber_wood/rubber_slab");
        createWithBlock(ModItems.RUBBER_PLANKS, "rubber_wood/rubber_planks");

//        createWithBlock(ModItems.RUBBER_SHEET, "rubber_sheet");
//        createWithBlock(ModItems.RESIN_SHEET, "resin_sheet");

        createGeneratedBlockTexture(ModItems.RUBBER_SAPLING, "rubber_wood/rubber_sapling");
        createGeneratedTexture(ModItems.STICKY_RESIN, "resource/sticky_resin");
        createGeneratedTexture(ModItems.RUBBER, "resource/rubber");
    }

    protected void registerGenerators() {
        createWithBlock(ModItems.GENERATOR, "generator/generator");
        createWithBlock(ModItems.GEO_GENERATOR, "generator/geo_generator");
        createWithBlock(ModItems.SOLAR_GENERATOR, "generator/solar_generator");
        createWithBlock(ModItems.ADVANCED_SOLAR_GENERATOR, "generator/advanced_solar_generator");
        createWithBlock(ModItems.HYBRID_SOLAR_GENERATOR, "generator/hybrid_solar_generator");
        createWithBlock(ModItems.QUANTUM_SOLAR_GENERATOR, "generator/quantum_solar_generator");
//        createWithBlock(ModItems.CRYSTALLINE_GENERATOR, "generator/crystalline_generator");
        createWithBlock(ModItems.SEMIFLUID_GENERATOR, "semifluid_generator");
        createWithBlock(ModItems.NUCLEAR_REACTOR, "nuclear_reactor");
        createWithBlock(ModItems.REACTOR_CHAMBER, "reactor_chamber");
    }

    protected void registerMachines() {
        createWithBlock(ModItems.IRON_FURNACE, "machines/iron_furnace");
        createWithBlock(ModItems.ELECTRIC_FURNACE, "machines/electric_furnace");

        createWithBlock(ModItems.CRUSHER, "machines/crusher");
        createWithBlock(ModItems.COMPRESSOR, "machines/compressor");
        createWithBlock(ModItems.EXTRACTOR, "machines/extractor");
        createWithBlock(ModItems.SAWMILL, "machines/sawmill");
        createWithBlock(ModItems.EXTRUDER, "machines/extruder");
        createWithBlock(ModItems.CANNING_MACHINE, "canning_machine");
        createWithBlock(ModItems.FLUID_ENRICHER, "fluid_enricher");
        createWithBlock(ModItems.FERMENTER, "fermenter");
        createWithBlock(ModItems.RECYCLER, "recycler");
        createWithBlock(ModItems.ORE_WASHING_PLANT, "ore_washing_plant");
        createWithBlock(ModItems.ALLOY_SMELTER, "alloy_smelter");
        createWithBlock(ModItems.METAL_FORMER, "metal_former");

        createWithBlock(ModItems.MATTER_FABRICATOR, "matter_fabricator");
        createWithBlock(ModItems.THERMAL_CENTRIFUGE, "thermal_centrifuge");

        createWithBlock(ModItems.SCANNER, "scanner");
        createWithBlock(ModItems.REPLICATOR, "replicator");
    }

    protected void registerMisc() {
        createWithBlock(ModItems.PATTERN_STORAGE, "pattern_storage");
        createWithBlock(ModItems.TELEPORT_ANCHOR, "teleport_anchor");
    }

    protected void registerDecoration() {
        createWithBlock(ModItems.YELLOW_STRIPES_BLOCK_LEFT, "yellow_stripes_block_left");
        createWithBlock(ModItems.YELLOW_STRIPES_BLOCK_RIGHT, "yellow_stripes_block_right");
        createWithBlock(ModItems.RADIOACTIVE_HAZARD_SIGN_BLOCK, "radioactive_hazard_sign_block");
        createWithBlock(ModItems.BIO_HAZARD_SIGN_BLOCK, "bio_hazard_sign_block");
        createWithBlock(ModItems.EXPLOSION_HAZARD_SIGN_BLOCK, "explosion_hazard_sign_block");
        createWithBlock(ModItems.FIRE_HAZARD_SIGN_BLOCK, "fire_hazard_sign_block");
        createWithBlock(ModItems.ACID_HAZARD_SIGN_BLOCK, "acid_hazard_sign_block");
        createWithBlock(ModItems.MAGIC_HAZARD_SIGN_BLOCK, "magic_hazard_sign_block");
        createWithBlock(ModItems.FROST_HAZARD_SIGN_BLOCK, "frost_hazard_sign_block");
        createWithBlock(ModItems.NOISE_HAZARD_SIGN_BLOCK, "noise_hazard_sign_block");
    }

    protected void registerBatteryBox() {
        createWithBlock(ModItems.BATTERY_BOX, "battery_box/battery_box");
        createWithBlock(ModItems.CESU, "battery_box/cesu");
        createWithBlock(ModItems.MFE, "battery_box/mfe");
        createWithBlock(ModItems.MFSU, "battery_box/mfsu");
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
        createGeneratedTexture(ModItems.MEMORY_CARD, "crafting/memory_card");
    }

    protected void registerCables() {
        createWithBlock(ModItems.TIN_CABLE, "cable/tin_cable_inventory");
        createWithBlock(ModItems.TIN_CABLE_INSULATED, "cable/tin_cable_insulated_inventory");
        createWithBlock(ModItems.COPPER_CABLE, "cable/copper_cable_inventory");
        createWithBlock(ModItems.COPPER_CABLE_INSULATED, "cable/copper_cable_insulated_inventory");
        createWithBlock(ModItems.GOLD_CABLE, "cable/gold_cable_inventory");
        createWithBlock(ModItems.GOLD_CABLE_INSULATED, "cable/gold_cable_insulated_inventory");
        createWithBlock(ModItems.HV_CABLE, "cable/hv_cable_inventory");
        createWithBlock(ModItems.HV_CABLE_INSULATED, "cable/hv_cable_insulated_inventory");
        createWithBlock(ModItems.GLASS_FIBRE_CABLE, "cable/glass_fibre_cable_inventory");
    }

    protected void registerCrop() {
        createGeneratedTexture(ModItems.FERTILIZER, "crop/fertilizer");
    }

    protected void registerUpgrades() {
//        createGeneratedTexture(ModItems.UPGRADE_BASE, "upgrade/base");
//        createGeneratedTexture(ModItems.ADVANCED_UPGRADE_BASE, "upgrade/advanced_base");
        createGeneratedTexture(ModItems.OVERCLOCKER_UPGRADE, "upgrade/overclocker");
        createGeneratedTexture(ModItems.TRANSFORMER_UPGRADE, "upgrade/transformer");
        createGeneratedTexture(ModItems.ENERGY_STORAGE_UPGRADE, "upgrade/energy_storage");
        createGeneratedTexture(ModItems.REDSTONE_SIGNAL_INVERTER_UPGRADE, "upgrade/redstone_inverter");
        createWithDirections(ModItems.EJECTOR_UPGRADE, "upgrade/ejector");
        createWithDirections(ModItems.PULLING_UPGRADE, "upgrade/pulling");
        createWithDirections(ModItems.FLUID_EJECTOR_UPGRADE, "upgrade/fluid_ejector");
        createWithDirections(ModItems.FLUID_PULLING_UPGRADE, "upgrade/fluid_pulling");
        createWithDirections(ModItems.ADVANCED_EJECTOR_UPGRADE, "upgrade/advanced_ejector");
        createWithDirections(ModItems.ADVANCED_PULLING_UPGRADE, "upgrade/advanced_pulling");
        createWithDirections(ModItems.ADVANCED_FLUID_EJECTOR_UPGRADE, "upgrade/advanced_fluid_ejector");
        createWithDirections(ModItems.ADVANCED_FLUID_PULLING_UPGRADE, "upgrade/advanced_fluid_pulling");
    }

    protected void registerTransformers() {
        createWithBlock(ModItems.LV_TRANSFORMER, "lv_transformer");
        createWithBlock(ModItems.MV_TRANSFORMER, "mv_transformer");
        createWithBlock(ModItems.HV_TRANSFORMER, "hv_transformer");
        createWithBlock(ModItems.EV_TRANSFORMER, "ev_transformer");
    }

    protected void registerChargePad() {
        createWithBlock(ModItems.CHARGE_PAD_BATTERY_BOX, "charge_pad_battery_box");
        createWithBlock(ModItems.CHARGE_PAD_CESU, "charge_pad_cesu");
        createWithBlock(ModItems.CHARGE_PAD_MFE, "charge_pad_mfe");
        createWithBlock(ModItems.CHARGE_PAD_MFSU, "charge_pad_mfsu");
    }

    protected void registerReactor() {
        createGeneratedTexture(ModItems.SMALL_COOLANT_CELL, "reactor/small_coolant_cell");
        createGeneratedTexture(ModItems.MEDIUM_COOLANT_CELL, "reactor/medium_coolant_cell");
        createGeneratedTexture(ModItems.LARGE_COOLANT_CELL, "reactor/large_coolant_cell");

        createGeneratedTexture(ModItems.HEAT_EXCHANGER, "reactor/heat_exchanger");
        createGeneratedTexture(ModItems.HEAT_VENT, "reactor/heat_vent");
        createGeneratedTexture(ModItems.OVERCLOCKED_HEAT_VENT, "reactor/overclocked_heat_vent");
        createGeneratedTexture(ModItems.ADVANCED_HEAT_EXCHANGER, "reactor/advanced_heat_exchanger");
        createGeneratedTexture(ModItems.ADVANCED_HEAT_VENT, "reactor/advanced_heat_vent");
        createGeneratedTexture(ModItems.COMPONENT_HEAT_EXCHANGER, "reactor/component_heat_exchanger");
        createGeneratedTexture(ModItems.COMPONENT_HEAT_VENT, "reactor/component_heat_vent");
        createGeneratedTexture(ModItems.CONTAINMENT_REACTOR_PLATING, "reactor/containment_reactor_plating");
        createGeneratedTexture(ModItems.HEAT_CAPACITY_REACTOR_PLATING, "reactor/heat_capacity_reactor_plating");
        createGeneratedTexture(ModItems.HEATING_CELL, "reactor/heating_cell");
        createGeneratedTexture(ModItems.REACTOR_HEAT_EXCHANGER, "reactor/reactor_heat_exchanger");
        createGeneratedTexture(ModItems.REACTOR_HEAT_VENT, "reactor/reactor_heat_vent");
        createGeneratedTexture(ModItems.REACTOR_PLATING, "reactor/reactor_plating");
        createGeneratedTexture(ModItems.RSH_CONDENSATOR, "reactor/rsh_condensator");
        createGeneratedTexture(ModItems.ZLH_CONDENSATOR, "reactor/zlh_condensator");
        createGeneratedTexture(ModItems.NEUTRON_REFLECTOR, "reactor/neutron_reflector");
        createGeneratedTexture(ModItems.THICK_NEUTRON_REFLECTOR, "reactor/thick_neutron_reflector");
        createGeneratedTexture(ModItems.IRIDIUM_NEUTRON_REFLECTOR, "reactor/iridium_neutron_reflector");

        createGeneratedTexture(ModItems.FUEL_ROD, "reactor/fuel_rod");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM, "reactor/fuel_rod_uranium");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM_DEPLETED, "reactor/fuel_rod_uranium_depleted");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM_DUAL, "reactor/fuel_rod_uranium_dual");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM_DUAL_DEPLETED, "reactor/fuel_rod_uranium_dual_depleted");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM_QUAD, "reactor/fuel_rod_uranium_quad");
        createGeneratedTexture(ModItems.FUEL_ROD_URANIUM_QUAD_DEPLETED, "reactor/fuel_rod_uranium_quad_depleted");

        createGeneratedTexture(ModItems.FUEL_ROD_MOX, "reactor/fuel_rod_mox");
        createGeneratedTexture(ModItems.FUEL_ROD_MOX_DEPLETED, "reactor/fuel_rod_mox_depleted");
        createGeneratedTexture(ModItems.FUEL_ROD_MOX_DUAL, "reactor/fuel_rod_mox_dual");
        createGeneratedTexture(ModItems.FUEL_ROD_MOX_DUAL_DEPLETED, "reactor/fuel_rod_mox_dual_depleted");
        createGeneratedTexture(ModItems.FUEL_ROD_MOX_QUAD, "reactor/fuel_rod_mox_quad");
        createGeneratedTexture(ModItems.FUEL_ROD_MOX_QUAD_DEPLETED, "reactor/fuel_rod_mox_quad_depleted");
    }

    protected void registerExplosive() {
        createWithBlock(ModItems.INDUSTRIAL_TNT, "industrial_tnt");
        createWithBlock(ModItems.NUKE, "nuke");
    }

    private ItemModelBuilder createGeneratedTexture(RegistryObject<Item> item, String path) {
        return singleTexture(item.getId().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createGeneratedBlockTexture(RegistryObject<Item> block, String path) {
        return singleTexture(block.getId().getPath(), new ResourceLocation("item/generated"),"layer0",new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createHandheldTexture(RegistryObject<Item> item, String path) {
        return singleTexture(item.getId().getPath(), new ResourceLocation("item/handheld"),"layer0",new ResourceLocation(IndReb.MODID, "item/" + path));
    }

    private ItemModelBuilder createWithBlock(RegistryObject<Item> block, String path) {
        return withExistingParent(block.getId().getPath(), new ResourceLocation(IndReb.MODID, "block/" + path));
    }

    private ItemModelBuilder createWithActive(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + path)
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 0).model(createTestModel("handheld", path, "")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "active"), 1).model(createTestModel("handheld", path, "_active")).end();
    }

    private ItemModelBuilder createWithChargeRatio(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + path + "_0")
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 0).model(createTestModel("generated", path, "_0")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 1).model(createTestModel("generated", path, "_1")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 2).model(createTestModel("generated", path, "_2")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 3).model(createTestModel("generated", path, "_3")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "charge_ratio"), 4).model(createTestModel("generated", path, "_4")).end();
    }

    private ItemModelBuilder createWithDirections(RegistryObject<Item> item, String path) {
        return getBuilder(item.getId().getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + path)
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), -1).model(createTestModel("generated", path, "")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 0).model(createTestModel("generated", path, "_down")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 1).model(createTestModel("generated", path, "_up")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 2).model(createTestModel("generated", path, "_north")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 3).model(createTestModel("generated", path, "_south")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 4).model(createTestModel("generated", path, "_west")).end()
                .override().predicate(new ResourceLocation(IndReb.MODID, "direction"), 5).model(createTestModel("generated", path, "_east")).end();
    }


    private ItemModelBuilder createTestModel(String type, String path, String suffix) {
        return getBuilder("item/" + path + suffix).parent(getExistingFile(mcLoc("item/" + type)))
                .texture("layer0", "item/" + path + suffix);
    }

}
