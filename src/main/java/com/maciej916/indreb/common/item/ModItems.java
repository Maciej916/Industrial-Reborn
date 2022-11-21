package com.maciej916.indreb.common.item;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.api.blockitem.BlockItemElectric;
import com.maciej916.indreb.common.api.blockitem.IndRebBlockItem;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.item.base.*;
import com.maciej916.indreb.common.api.tier.CustomTiers;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.blockitem.BlockItemIronScaffolding;
import com.maciej916.indreb.common.item.impl.*;
import com.maciej916.indreb.common.item.impl.armor.*;
import com.maciej916.indreb.common.item.impl.tool.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TIN_ORE = fromBlock(ModBlocks.TIN_ORE);
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = fromBlock(ModBlocks.DEEPSLATE_TIN_ORE);
    public static final RegistryObject<Item> LEAD_ORE = fromBlock(ModBlocks.LEAD_ORE);
    public static final RegistryObject<Item> DEEPSLATE_LEAD_ORE = fromBlock(ModBlocks.DEEPSLATE_LEAD_ORE);
    public static final RegistryObject<Item> URANIUM_ORE = fromBlock(ModBlocks.URANIUM_ORE);
    public static final RegistryObject<Item> DEEPSLATE_URANIUM_ORE = fromBlock(ModBlocks.DEEPSLATE_URANIUM_ORE);
    public static final RegistryObject<Item> SILVER_ORE = fromBlock(ModBlocks.SILVER_ORE);
    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE = fromBlock(ModBlocks.DEEPSLATE_SILVER_ORE);

    public static final RegistryObject<Item> RAW_TIN_BLOCK = fromBlock(ModBlocks.RAW_TIN_BLOCK);
    public static final RegistryObject<Item> RAW_LEAD_BLOCK = fromBlock(ModBlocks.RAW_LEAD_BLOCK);
    public static final RegistryObject<Item> RAW_URANIUM_BLOCK = fromBlock(ModBlocks.RAW_URANIUM_BLOCK);
    public static final RegistryObject<Item> RAW_SILVER_BLOCK = fromBlock(ModBlocks.RAW_SILVER_BLOCK);

    public static final RegistryObject<Item> TIN_BLOCK = fromBlock(ModBlocks.TIN_BLOCK);
    public static final RegistryObject<Item> LEAD_BLOCK = fromBlock(ModBlocks.LEAD_BLOCK);
    public static final RegistryObject<Item> URANIUM_BLOCK = fromBlock(ModBlocks.URANIUM_BLOCK);
    public static final RegistryObject<Item> SILVER_BLOCK = fromBlock(ModBlocks.SILVER_BLOCK);
    public static final RegistryObject<Item> STEEL_BLOCK = fromBlock(ModBlocks.STEEL_BLOCK);
    public static final RegistryObject<Item> BRONZE_BLOCK = fromBlock(ModBlocks.BRONZE_BLOCK);

    public static final RegistryObject<Item> RUBBER_LOG = fromBlock(ModBlocks.RUBBER_LOG);
    public static final RegistryObject<Item> RUBBER_WOOD = fromBlock(ModBlocks.RUBBER_WOOD);
    public static final RegistryObject<Item> RUBBER_LEAVES = fromBlock(ModBlocks.RUBBER_LEAVES);
    public static final RegistryObject<Item> RUBBER_PLANKS = fromBlock(ModBlocks.RUBBER_PLANKS);
    public static final RegistryObject<Item> RUBBER_SAPLING = fromBlock(ModBlocks.RUBBER_SAPLING);
    public static final RegistryObject<Item> RUBBER_STAIRS = fromBlock(ModBlocks.RUBBER_STAIRS);
    public static final RegistryObject<Item> RUBBER_SLAB = fromBlock(ModBlocks.RUBBER_SLAB);
    public static final RegistryObject<Item> RUBBER_PLATE = fromBlock(ModBlocks.RUBBER_PLATE);
    public static final RegistryObject<Item> RUBBER_FENCE = fromBlock(ModBlocks.RUBBER_FENCE);
    public static final RegistryObject<Item> RUBBER_GATE = fromBlock(ModBlocks.RUBBER_GATE);
    public static final RegistryObject<Item> RUBBER_DOOR = fromBlock(ModBlocks.RUBBER_DOOR);
    public static final RegistryObject<Item> RUBBER_TRAP_DOOR = fromBlock(ModBlocks.RUBBER_TRAP_DOOR);

    public static final RegistryObject<Item> RESIN_SHEET = fromBlock(ModBlocks.RESIN_SHEET);
    public static final RegistryObject<Item> RUBBER_SHEET = fromBlock(ModBlocks.RUBBER_SHEET);
    public static final RegistryObject<Item> RESIN_BLOCK = fromBlock(ModBlocks.RESIN_BLOCK);
    public static final RegistryObject<Item> RUBBER_BLOCK = fromBlock(ModBlocks.RUBBER_BLOCK);

    public static final RegistryObject<Item> BASIC_MACHINE_CASING = fromBlock(ModBlocks.BASIC_MACHINE_CASING);
    public static final RegistryObject<Item> ADVANCED_MACHINE_CASING = fromBlock(ModBlocks.ADVANCED_MACHINE_CASING);

    public static final RegistryObject<Item> CONSTRUCTION_FOAM = fromBlock(ModBlocks.CONSTRUCTION_FOAM);
    public static final RegistryObject<Item> REINFORCED_CONSTRUCTION_FOAM = fromBlock(ModBlocks.REINFORCED_CONSTRUCTION_FOAM);

    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_WHITE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_RED = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_RED);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_ORANGE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_PINK = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_YELLOW = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIME = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_GREEN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_CYAN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BLUE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_MAGENTA = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_PURPLE = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BROWN = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_GRAY = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY);
    public static final RegistryObject<Item> CONSTRUCTION_FOAM_WALL_BLACK = fromBlock(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK);

    public static final RegistryObject<Item> REINFORCED_GLASS = fromBlock(ModBlocks.REINFORCED_GLASS);
    public static final RegistryObject<Item> REINFORCED_STONE = fromBlock(ModBlocks.REINFORCED_STONE);
    public static final RegistryObject<Item> REINFORCED_STONE_SLAB = fromBlock(ModBlocks.REINFORCED_STONE_SLAB);
    public static final RegistryObject<Item> REINFORCED_STONE_STAIRS = fromBlock(ModBlocks.REINFORCED_STONE_STAIRS);
    public static final RegistryObject<Item> REINFORCED_STONE_BRICKS = fromBlock(ModBlocks.REINFORCED_STONE_BRICKS);
    public static final RegistryObject<Item> SMOOTH_REINFORCED_STONE = fromBlock(ModBlocks.SMOOTH_REINFORCED_STONE);
    public static final RegistryObject<Item> REINFORCED_STONE_BRICK_WALL = fromBlock(ModBlocks.REINFORCED_STONE_BRICK_WALL);
    public static final RegistryObject<Item> REINFORCED_DOOR = fromBlock(ModBlocks.REINFORCED_DOOR);

    public static final RegistryObject<Item> IRON_SCAFFOLDING = fromBlockIronScaffolding(ModBlocks.IRON_SCAFFOLDING);
    public static final RegistryObject<Item> IRON_FENCE = fromBlockIronScaffolding(ModBlocks.IRON_FENCE);
    public static final RegistryObject<Item> IRON_GATE = fromBlockIronScaffolding(ModBlocks.IRON_GATE);

    public static final RegistryObject<Item> YELLOW_STRIPES_BLOCK_LEFT = fromBlock(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT);
    public static final RegistryObject<Item> YELLOW_STRIPES_BLOCK_RIGHT = fromBlock(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT);
    public static final RegistryObject<Item> RADIOACTIVE_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> BIO_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.BIO_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> EXPLOSION_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> FIRE_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.FIRE_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> ACID_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.ACID_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> MAGIC_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> FROST_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.FROST_HAZARD_SIGN_BLOCK);
    public static final RegistryObject<Item> NOISE_HAZARD_SIGN_BLOCK = fromBlock(ModBlocks.NOISE_HAZARD_SIGN_BLOCK);

    public static final RegistryObject<Item> INDUSTRIAL_TNT = fromBlock(ModBlocks.INDUSTRIAL_TNT);
    public static final RegistryObject<Item> NUKE = fromBlock(ModBlocks.NUKE);

    public static final RegistryObject<Item> LUMINATOR = fromBlock(ModBlocks.LUMINATOR);

    public static final RegistryObject<Item> TIN_CABLE = fromBlock(ModBlocks.TIN_CABLE);
    public static final RegistryObject<Item> TIN_CABLE_INSULATED = fromBlock(ModBlocks.TIN_CABLE_INSULATED);
    public static final RegistryObject<Item> COPPER_CABLE = fromBlock(ModBlocks.COPPER_CABLE);
    public static final RegistryObject<Item> COPPER_CABLE_INSULATED = fromBlock(ModBlocks.COPPER_CABLE_INSULATED);
    public static final RegistryObject<Item> GOLD_CABLE = fromBlock(ModBlocks.GOLD_CABLE);
    public static final RegistryObject<Item> GOLD_CABLE_INSULATED = fromBlock(ModBlocks.GOLD_CABLE_INSULATED);
    public static final RegistryObject<Item> HV_CABLE = fromBlock(ModBlocks.HV_CABLE);
    public static final RegistryObject<Item> HV_CABLE_INSULATED = fromBlock(ModBlocks.HV_CABLE_INSULATED);
    public static final RegistryObject<Item> GLASS_FIBRE_CABLE = fromBlock(ModBlocks.GLASS_FIBRE_CABLE);

    public static final RegistryObject<Item> GENERATOR = fromBlockElectric(ModBlocks.GENERATOR);
    public static final RegistryObject<Item> SOLAR_PANEL = fromBlockElectric(ModBlocks.SOLAR_PANEL);
    public static final RegistryObject<Item> ADVANCED_SOLAR_PANEL = fromBlockElectric(ModBlocks.ADVANCED_SOLAR_PANEL);
    public static final RegistryObject<Item> HYBRID_SOLAR_PANEL = fromBlockElectric(ModBlocks.HYBRID_SOLAR_PANEL);
    public static final RegistryObject<Item> QUANTUM_SOLAR_PANEL = fromBlockElectric(ModBlocks.QUANTUM_SOLAR_PANEL);
    public static final RegistryObject<Item> GEO_GENERATOR = fromBlockElectric(ModBlocks.GEO_GENERATOR);
    public static final RegistryObject<Item> SEMIFLUID_GENERATOR = fromBlockElectric(ModBlocks.SEMIFLUID_GENERATOR);








    public static final RegistryObject<Item> RAW_TIN = registerItem("raw_tin", MaterialItem::new);
    public static final RegistryObject<Item> RAW_LEAD = registerItem("raw_lead", MaterialItem::new);
    public static final RegistryObject<Item> RAW_URANIUM = registerItem("raw_uranium", MaterialItem::new);
    public static final RegistryObject<Item> RAW_SILVER = registerItem("raw_silver", MaterialItem::new);

    public static final RegistryObject<Item> TIN_INGOT = registerItem("tin_ingot", MaterialItem::new);
    public static final RegistryObject<Item> BRONZE_INGOT = registerItem("bronze_ingot", MaterialItem::new);
    public static final RegistryObject<Item> STEEL_INGOT = registerItem("steel_ingot", MaterialItem::new);
    public static final RegistryObject<Item> MIXED_METAL_INGOT = registerItem("mixed_metal_ingot", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_INGOT = registerItem("silver_ingot", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_INGOT = registerItem("lead_ingot", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_INGOT = registerItem("uranium_ingot", MaterialItem::new);

    public static final RegistryObject<Item> TIN_DUST = registerItem("tin_dust", MaterialItem::new);
    public static final RegistryObject<Item> COPPER_DUST = registerItem("copper_dust", MaterialItem::new);
    public static final RegistryObject<Item> IRON_DUST = registerItem("iron_dust", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_DUST = registerItem("gold_dust", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_DUST = registerItem("lead_dust", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_DUST = registerItem("uranium_dust", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_DUST = registerItem("silver_dust", MaterialItem::new);
    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust", MaterialItem::new);
    public static final RegistryObject<Item> LAPIS_LAZULI_DUST = registerItem("lapis_lazuli_dust", MaterialItem::new);
    public static final RegistryObject<Item> DIAMOND_DUST = registerItem("diamond_dust", MaterialItem::new);
    public static final RegistryObject<Item> ENERGIUM_DUST = registerItem("energium_dust", MaterialItem::new);
    public static final RegistryObject<Item> STONE_DUST = registerItem("stone_dust", MaterialItem::new);
    public static final RegistryObject<Item> DEEPSLATE_DUST = registerItem("deepslate_dust", MaterialItem::new);
    public static final RegistryObject<Item> SAWDUST = registerItem("sawdust", MaterialItem::new);
    public static final RegistryObject<Item> SULFUR_DUST = registerItem("sulfur_dust", MaterialItem::new);
    public static final RegistryObject<Item> MUD_PILE = registerItem("mud_pile", MaterialItem::new);

    public static final RegistryObject<Item> PURIFIED_TIN = registerItem("purified_tin", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_COPPER = registerItem("purified_copper", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_IRON = registerItem("purified_iron", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_GOLD = registerItem("purified_gold", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_LEAD = registerItem("purified_lead", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_URANIUM = registerItem("purified_uranium", MaterialItem::new);
    public static final RegistryObject<Item> PURIFIED_SILVER = registerItem("purified_silver", MaterialItem::new);

    public static final RegistryObject<Item> TIN_CHUNK = registerItem("tin_chunk", MaterialItem::new);
    public static final RegistryObject<Item> COPPER_CHUNK = registerItem("copper_chunk", MaterialItem::new);
    public static final RegistryObject<Item> IRON_CHUNK = registerItem("iron_chunk", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_CHUNK = registerItem("gold_chunk", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_CHUNK = registerItem("lead_chunk", MaterialItem::new);
    public static final RegistryObject<Item> URANIUM_CHUNK = registerItem("uranium_chunk", MaterialItem::new);
    public static final RegistryObject<Item> SILVER_CHUNK = registerItem("silver_chunk", MaterialItem::new);

    public static final RegistryObject<Item> COPPER_PLATE = registerItem("copper_plate", MaterialItem::new);
    public static final RegistryObject<Item> TIN_PLATE = registerItem("tin_plate", MaterialItem::new);
    public static final RegistryObject<Item> IRON_PLATE = registerItem("iron_plate", MaterialItem::new);
    public static final RegistryObject<Item> LEAD_PLATE = registerItem("lead_plate", MaterialItem::new);
    public static final RegistryObject<Item> GOLD_PLATE = registerItem("gold_plate", MaterialItem::new);
    public static final RegistryObject<Item> BRONZE_PLATE = registerItem("bronze_plate", MaterialItem::new);
    public static final RegistryObject<Item> STEEL_PLATE = registerItem("steel_plate", MaterialItem::new);
    public static final RegistryObject<Item> LAPIS_LAZULI_PLATE = registerItem("lapis_lazuli_plate", MaterialItem::new);
    public static final RegistryObject<Item> ADVANCED_ALLOY = registerItem("advanced_alloy", MaterialItem::new);
    public static final RegistryObject<Item> IRIDIUM_PLATE = registerItem("iridium_plate", MaterialItemRare::new);

    public static final RegistryObject<Item> ELECTRONIC_CIRCUIT = registerItem("electronic_circuit", MaterialItem::new);
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = registerItem("advanced_circuit", MaterialItem::new);
    public static final RegistryObject<Item> CARBON_FIBERS = registerItem("carbon_fibers", MaterialItem::new);
    public static final RegistryObject<Item> COMBINED_CARBON_FIBERS = registerItem("combined_carbon_fibers", MaterialItem::new);
    public static final RegistryObject<Item> CARBON_PLATE = registerItem("carbon_plate", MaterialItem::new);
    public static final RegistryObject<Item> BIO_CHAFF = registerItem("bio_chaff", MaterialItem::new);
    public static final RegistryObject<Item> HEAT_CONDUCTOR = registerItem("heat_conductor", MaterialItem::new);
    public static final RegistryObject<Item> FOAM_POWDER = registerItem("foam_powder", MaterialItem::new);
    public static final RegistryObject<Item> REINFORCED_FOAM_POWDER = registerItem("reinforced_foam_powder", MaterialItem::new);
    public static final RegistryObject<Item> SMALL_POWER_UNIT = registerItem("small_power_unit", RedstoneItem::new);
    public static final RegistryObject<Item> POWER_UNIT = registerItem("power_unit", RedstoneItem::new);
    public static final RegistryObject<Item> COIL = registerItem("coil", RedstoneItem::new);
    public static final RegistryObject<Item> ELECTRIC_MOTOR = registerItem("electric_motor", RedstoneItem::new);
    public static final RegistryObject<Item> SCRAP = registerItem("scrap", Scrap::new);
    public static final RegistryObject<Item> SCRAP_BOX = registerItem("scrap_box", ScrapBox::new);

    public static final RegistryObject<Item> STICKY_RESIN = registerItem("sticky_resin", MaterialItem::new);
    public static final RegistryObject<Item> RUBBER = registerItem("rubber", MaterialItem::new);
    public static final RegistryObject<Item> IRIDIUM_SHARD = registerItem("iridium_shard", MaterialItemRare::new);
    public static final RegistryObject<Item> IRIDIUM = registerItem("iridium", MaterialItemRare::new);

    public static final RegistryObject<Item> IRON_ROD = registerItem("iron_rod", MaterialItem::new);

    public static final RegistryObject<Item> FERTILIZER = registerItem("fertilizer", Fertilizer::new);

    public static final RegistryObject<Item> HAMMER = registerItem("hammer", () -> new Hammer(80));
    public static final RegistryObject<Item> CUTTER = registerItem("cutter", () -> new Cutter(60));
    public static final RegistryObject<Item> TREETAP = registerItem("treetap", () -> new Treetap(20));
    public static final RegistryObject<Item> WRENCH = registerItem("wrench", () -> new Wrench(120));
    public static final RegistryObject<Item> TOOL_BOX = registerItem("tool_box", WIPItem::new);
    public static final RegistryObject<Item> FOAM_SPRAYER = registerItem("foam_sprayer", FoamSprayer::new);

    public static final RegistryObject<Item> ELECTRIC_TREETAP = registerItem("electric_treetap", () -> new ElectricTreetap(0, 10000, EnergyTier.BASIC));
    public static final RegistryObject<Item> ELECTRIC_WRENCH = registerItem("electric_wrench", () -> new ElectricWrench(0, 10000, EnergyTier.BASIC));

    public static final RegistryObject<Item> BASIC_TRANSPORTER = registerItem("basic_transporter", WIPItem::new);
    public static final RegistryObject<Item> ADVANCED_TRANSPORTER = registerItem("advanced_transporter", WIPItem::new);
    public static final RegistryObject<Item> DEBUG_STICK = registerItem("debug_stick", DebugStick::new);

    public static final RegistryObject<Item> CHAINSAW = registerItem("chainsaw", () -> new ElectricChainsaw(Tiers.IRON,6.0F, -3.1F, 0, 30000, 50, 100, EnergyTier.BASIC));
    public static final RegistryObject<Item> DIAMOND_CHAINSAW = registerItem("diamond_chainsaw", () -> new ElectricChainsaw(Tiers.DIAMOND,5.0F, -3.0F, 0, 80000, 70, 120, EnergyTier.STANDARD));
    public static final RegistryObject<Item> IRIDIUM_CHAINSAW = registerItem("iridium_chainsaw", () -> new ElectricChainsaw(CustomTiers.IRIDIUM,5.0F, -3.0F, 0, 300000, 200, 400, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> MINING_DRILL = registerItem("mining_drill", () -> new ElectricDrill(Tiers.IRON,1, -2.8F, 0, 30000, 50, 100, EnergyTier.BASIC));
    public static final RegistryObject<Item> DIAMOND_DRILL = registerItem("diamond_drill", () -> new ElectricDrill(Tiers.DIAMOND,1, -2.8F, 0, 80000, 70, 120, EnergyTier.STANDARD));
    public static final RegistryObject<Item> IRIDIUM_DRILL = registerItem("iridium_drill", () -> new ElectricDrill(CustomTiers.IRIDIUM, 1, -2.8F, 0, 300000, 200, 400, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> ELECTRIC_HOE = registerItem("electric_hoe", () -> new ElectricHoe(Tiers.IRON,-2, -1.0F, 0, 10000, 50, 100, 50, EnergyTier.BASIC));
    public static final RegistryObject<Item> WIND_METER = registerItem("wind_meter", WIPItem::new);
    public static final RegistryObject<Item> IE_METER = registerItem("ie_meter", IEMeter::new);
    public static final RegistryObject<Item> MULTI_TOOL = registerItem("multi_tool", () -> new MultiTool(Tiers.DIAMOND,-3, 0.0F, 0, 300000, 800, 1400, 500, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> NIGHTVISION_GOGGLES = registerItem("nightvision_goggles", NightVisionGoggles::new);
    public static final RegistryObject<Item> RUBBER_BOOTS = registerItem("rubber_boots", RubberBoots::new);

    public static final RegistryObject<Item> BRONZE_HELMET = registerItem("bronze_helmet", () -> new BronzeArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = registerItem("bronze_chestplate", () -> new BronzeArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = registerItem("bronze_leggings", () -> new BronzeArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> BRONZE_BOOTS = registerItem("bronze_boots", () -> new BronzeArmor(EquipmentSlot.FEET));

    public static final RegistryObject<Item> BRONZE_SWORD = registerItem("bronze_sword", BronzeSword::new);
    public static final RegistryObject<Item> BRONZE_PICKAXE = registerItem("bronze_pickaxe", BronzePickaxe::new);
    public static final RegistryObject<Item> BRONZE_AXE = registerItem("bronze_axe", BronzeAxe::new);
    public static final RegistryObject<Item> BRONZE_SHOVEL = registerItem("bronze_shovel", BronzeShovel::new);
    public static final RegistryObject<Item> BRONZE_HOE = registerItem("bronze_hoe", BronzeHoe::new);

    public static final RegistryObject<Item> NANO_HELMET = registerItem("nano_helmet", NanoHelmet::new);
    public static final RegistryObject<Item> NANO_CHESTPLATE = registerItem("nano_chestplate", () -> new NanoArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> NANO_LEGGINGS = registerItem("nano_leggings", () -> new NanoArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> NANO_BOOTS = registerItem("nano_boots", () -> new NanoArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> NANO_SABER = registerItem("nano_saber", Nanosaber::new);

    public static final RegistryObject<Item> PAINTER = registerItem("painter", () -> new ToolItem(1));
    public static final RegistryObject<Item> PAINTER_WHITE = registerItem("painter_white", () -> new Painter(MaterialColor.WOOL));
    public static final RegistryObject<Item> PAINTER_RED = registerItem("painter_red", () -> new Painter(MaterialColor.COLOR_RED));
    public static final RegistryObject<Item> PAINTER_ORANGE = registerItem("painter_orange", () -> new Painter(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Item> PAINTER_PINK = registerItem("painter_pink", () -> new Painter(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Item> PAINTER_YELLOW = registerItem("painter_yellow", () -> new Painter(MaterialColor.COLOR_YELLOW));
    public static final RegistryObject<Item> PAINTER_LIME = registerItem("painter_lime", () -> new Painter(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Item> PAINTER_GREEN = registerItem("painter_green", () -> new Painter(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Item> PAINTER_LIGHT_BLUE = registerItem("painter_light_blue", () -> new Painter(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Item> PAINTER_CYAN = registerItem("painter_cyan", () -> new Painter(MaterialColor.COLOR_CYAN));
    public static final RegistryObject<Item> PAINTER_BLUE = registerItem("painter_blue", () -> new Painter(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Item> PAINTER_MAGENTA = registerItem("painter_magenta", () -> new Painter(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Item> PAINTER_PURPLE = registerItem("painter_purple", () -> new Painter(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Item> PAINTER_BROWN = registerItem("painter_brown", () -> new Painter(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Item> PAINTER_GRAY = registerItem("painter_gray", () -> new Painter(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Item> PAINTER_LIGHT_GRAY = registerItem("painter_light_gray", () -> new Painter(MaterialColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Item> PAINTER_BLACK = registerItem("painter_black", () -> new Painter(MaterialColor.COLOR_BLACK));

    public static final RegistryObject<Item> FLUID_CELL = registerItem("fluid_cell", FluidCell::new);
    public static final RegistryObject<Item> MEMORY_CARD = registerItem("memory_card", MemoryCard::new);

    public static final RegistryObject<Item> TIN_CAN = registerItem("tin_can", FoodItem::new);
    public static final RegistryObject<Item> FILLED_TIN_CAN = registerItem("filled_tin_can", FilledTinCan::new);







    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return fromBlock(block, Rarity.COMMON);
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Rarity rarity) {
        return ITEMS.register(block.getId().getPath(), () -> new IndRebBlockItem(block.get(), rarity));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockIronScaffolding(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemIronScaffolding(block.get()));
    }

    private static <T extends Block> RegistryObject<Item> fromBlockElectric(RegistryObject<T> block) {
        return fromBlockElectric(block, Rarity.COMMON);
    }

    private static <T extends Block> RegistryObject<Item> fromBlockElectric(RegistryObject<T> block, Rarity rarity) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemElectric(block.get(), rarity));
    }


    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
