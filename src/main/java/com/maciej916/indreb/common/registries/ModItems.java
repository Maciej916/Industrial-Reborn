package com.maciej916.indreb.common.registries;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.block.BlockResource;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.base.*;
import com.maciej916.indreb.common.item.block.BlockItemElectric;
import com.maciej916.indreb.common.item.block.ItemScaffolding;
import com.maciej916.indreb.common.item.impl.*;
import com.maciej916.indreb.common.item.impl.armor.NightVisionGoggles;
import com.maciej916.indreb.common.item.impl.bronze.*;
import com.maciej916.indreb.common.item.impl.nano.ItemNanoArmor;
import com.maciej916.indreb.common.item.impl.nano.ItemNanosaber;
import com.maciej916.indreb.common.item.impl.nano.NanoHelmet;
import com.maciej916.indreb.common.item.impl.tools.*;
import com.maciej916.indreb.common.item.impl.treetap.ElectricTreetap;
import com.maciej916.indreb.common.item.impl.treetap.Treetap;
import com.maciej916.indreb.common.item.impl.upgrade.*;
import com.maciej916.indreb.common.item.impl.wrench.ElectricWrench;
import com.maciej916.indreb.common.item.impl.wrench.Wrench;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

    public static final RegistryObject<Item> RUBBER_LOG = fromBlock(ModBlocks.RUBBER_LOG);
    public static final RegistryObject<Item> RUBBER_WOOD = fromBlock(ModBlocks.RUBBER_WOOD);
    public static final RegistryObject<Item> RUBBER_LEAVES = fromBlock(ModBlocks.RUBBER_LEAVES);
    public static final RegistryObject<Item> RUBBER_PLANKS = fromBlock(ModBlocks.RUBBER_PLANKS);
    public static final RegistryObject<Item> RUBBER_SAPLING = fromBlock(ModBlocks.RUBBER_SAPLING);
    public static final RegistryObject<Item> RUBBER_STAIRS = fromBlock(ModBlocks.RUBBER_STAIRS);
    public static final RegistryObject<Item> RUBBER_SLAB = fromBlock(ModBlocks.RUBBER_SLAB);

    public static final RegistryObject<Item> TIN_CABLE = fromBlock(ModBlocks.TIN_CABLE);
    public static final RegistryObject<Item> TIN_CABLE_INSULATED = fromBlock(ModBlocks.TIN_CABLE_INSULATED);
    public static final RegistryObject<Item> COPPER_CABLE = fromBlock(ModBlocks.COPPER_CABLE);
    public static final RegistryObject<Item> COPPER_CABLE_INSULATED = fromBlock(ModBlocks.COPPER_CABLE_INSULATED);
    public static final RegistryObject<Item> GOLD_CABLE = fromBlock(ModBlocks.GOLD_CABLE);
    public static final RegistryObject<Item> GOLD_CABLE_INSULATED = fromBlock(ModBlocks.GOLD_CABLE_INSULATED);
    public static final RegistryObject<Item> HV_CABLE = fromBlock(ModBlocks.HV_CABLE);
    public static final RegistryObject<Item> HV_CABLE_INSULATED = fromBlock(ModBlocks.HV_CABLE_INSULATED);
    public static final RegistryObject<Item> GLASS_FIBRE_CABLE = fromBlock(ModBlocks.GLASS_FIBRE_CABLE);

    public static final RegistryObject<Item> TIN_BLOCK = fromBlock(ModBlocks.TIN_BLOCK);
    public static final RegistryObject<Item> SILVER_BLOCK = fromBlock(ModBlocks.SILVER_BLOCK);
    public static final RegistryObject<Item> STEEL_BLOCK = fromBlock(ModBlocks.STEEL_BLOCK);
    public static final RegistryObject<Item> BRONZE_BLOCK = fromBlock(ModBlocks.BRONZE_BLOCK);
    public static final RegistryObject<Item> LEAD_BLOCK = fromBlock(ModBlocks.LEAD_BLOCK);

    public static final RegistryObject<Item> BASIC_MACHINE_CASING = fromBlock(ModBlocks.BASIC_MACHINE_CASING);
    public static final RegistryObject<Item> ADVANCED_MACHINE_CASING = fromBlock(ModBlocks.ADVANCED_MACHINE_CASING);
    public static final RegistryObject<Item> RESIN_SHEET = fromBlock(ModBlocks.RESIN_SHEET);
    public static final RegistryObject<Item> RUBBER_SHEET = fromBlock(ModBlocks.RUBBER_SHEET);

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

    public static final RegistryObject<Item> IRON_SCAFFOLDING = fromBlockIronScaffolding(ModBlocks.IRON_SCAFFOLDING);
    public static final RegistryObject<Item> IRON_FENCE = fromBlock(ModBlocks.IRON_FENCE);

    public static final RegistryObject<Item> LUMINATOR = fromBlock(ModBlocks.LUMINATOR);

    public static final RegistryObject<Item> GENERATOR = fromBlockElectric(ModBlocks.GENERATOR);
    public static final RegistryObject<Item> SOLAR_GENERATOR = fromBlockElectric(ModBlocks.SOLAR_GENERATOR);
    public static final RegistryObject<Item> ADVANCED_SOLAR_GENERATOR = fromBlockElectric(ModBlocks.ADVANCED_SOLAR_GENERATOR);
    public static final RegistryObject<Item> HYBRID_SOLAR_GENERATOR = fromBlockElectric(ModBlocks.HYBRID_SOLAR_GENERATOR);
    public static final RegistryObject<Item> QUANTUM_SOLAR_GENERATOR = fromBlockElectric(ModBlocks.QUANTUM_SOLAR_GENERATOR);
    public static final RegistryObject<Item> GEO_GENERATOR = fromBlockElectric(ModBlocks.GEO_GENERATOR);
//    public static final RegistryObject<Item> CRYSTALLINE_GENERATOR = fromBlockElectric(ModBlocks.CRYSTALLINE_GENERATOR);
    public static final RegistryObject<Item> SEMIFLUID_GENERATOR = fromBlockElectric(ModBlocks.SEMIFLUID_GENERATOR);

    public static final RegistryObject<Item> IRON_FURNACE = fromBlock(ModBlocks.IRON_FURNACE);
    public static final RegistryObject<Item> ELECTRIC_FURNACE = fromBlockElectric(ModBlocks.ELECTRIC_FURNACE);

    public static final RegistryObject<Item> CRUSHER = fromBlockElectric(ModBlocks.CRUSHER);
    public static final RegistryObject<Item> COMPRESSOR = fromBlockElectric(ModBlocks.COMPRESSOR);
    public static final RegistryObject<Item> EXTRACTOR = fromBlockElectric(ModBlocks.EXTRACTOR);
    public static final RegistryObject<Item> SAWMILL = fromBlockElectric(ModBlocks.SAWMILL);
    public static final RegistryObject<Item> EXTRUDER = fromBlockElectric(ModBlocks.EXTRUDER);
    public static final RegistryObject<Item> CANNING_MACHINE = fromBlockElectric(ModBlocks.CANNING_MACHINE);
    public static final RegistryObject<Item> FLUID_ENRICHER = fromBlockElectric(ModBlocks.FLUID_ENRICHER);
    public static final RegistryObject<Item> RECYCLER = fromBlockElectric(ModBlocks.RECYCLER);
    public static final RegistryObject<Item> FERMENTER = fromBlockElectric(ModBlocks.FERMENTER);
    public static final RegistryObject<Item> ORE_WASHING_PLANT = fromBlock(ModBlocks.ORE_WASHING_PLANT);
    public static final RegistryObject<Item> ALLOY_SMELTER = fromBlockElectric(ModBlocks.ALLOY_SMELTER);
    public static final RegistryObject<Item> METAL_FORMER = fromBlockElectric(ModBlocks.METAL_FORMER);


    public static final RegistryObject<Item> THERMAL_CENTRIFUGE = fromBlock(ModBlocks.THERMAL_CENTRIFUGE);

    public static final RegistryObject<Item> TELEPORT_ANCHOR = fromBlock(ModBlocks.TELEPORT_ANCHOR);
    public static final RegistryObject<Item> REPLICATOR = fromBlock(ModBlocks.REPLICATOR);
    public static final RegistryObject<Item> MATTER_FABRICATOR = fromBlock(ModBlocks.MATTER_FABRICATOR);

    public static final RegistryObject<Item> SCANNER = fromBlock(ModBlocks.SCANNER);

    public static final RegistryObject<Item> PATTERN_STORAGE = fromBlock(ModBlocks.PATTERN_STORAGE);

    public static final RegistryObject<Item> BATTERY_BOX = fromBlockElectric(ModBlocks.BATTERY_BOX);
    public static final RegistryObject<Item> CESU = fromBlockElectric(ModBlocks.CESU);
    public static final RegistryObject<Item> MFE = fromBlockElectric(ModBlocks.MFE);
    public static final RegistryObject<Item> MFSU = fromBlockElectric(ModBlocks.MFSU);

    public static final RegistryObject<Item> LV_TRANSFORMER = fromBlock(ModBlocks.LV_TRANSFORMER);
    public static final RegistryObject<Item> MV_TRANSFORMER = fromBlock(ModBlocks.MV_TRANSFORMER);
    public static final RegistryObject<Item> HV_TRANSFORMER = fromBlock(ModBlocks.HV_TRANSFORMER);
    public static final RegistryObject<Item> EV_TRANSFORMER = fromBlock(ModBlocks.EV_TRANSFORMER);

    public static final RegistryObject<Item> CHARGE_PAD_BATTERY_BOX = fromBlockElectric(ModBlocks.CHARGE_PAD_BATTERY_BOX);
    public static final RegistryObject<Item> CHARGE_PAD_CESU = fromBlockElectric(ModBlocks.CHARGE_PAD_CESU);
    public static final RegistryObject<Item> CHARGE_PAD_MFE = fromBlockElectric(ModBlocks.CHARGE_PAD_MFE);
    public static final RegistryObject<Item> CHARGE_PAD_MFSU = fromBlockElectric(ModBlocks.CHARGE_PAD_MFSU);

    public static final RegistryObject<Item> INDUSTRIAL_TNT = fromBlock(ModBlocks.INDUSTRIAL_TNT);
    public static final RegistryObject<Item> NUKE = fromBlock(ModBlocks.NUKE);




    public static final RegistryObject<Item> RAW_TIN = registerItem("raw_tin", () -> new MaterialItem());
    public static final RegistryObject<Item> RAW_LEAD = registerItem("raw_lead", () -> new MaterialItem());
    public static final RegistryObject<Item> RAW_URANIUM = registerItem("raw_uranium", () -> new MaterialItem());
    public static final RegistryObject<Item> RAW_SILVER = registerItem("raw_silver", () -> new MaterialItem());

    public static final RegistryObject<Item> TIN_INGOT = registerItem("tin_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> BRONZE_INGOT = registerItem("bronze_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> STEEL_INGOT = registerItem("steel_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> MIXED_METAL_INGOT = registerItem("mixed_metal_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> SILVER_INGOT = registerItem("silver_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> LEAD_INGOT = registerItem("lead_ingot", () -> new MaterialItem());
    public static final RegistryObject<Item> URANIUM_INGOT = registerItem("uranium_ingot", () -> new MaterialItem());

    public static final RegistryObject<Item> TIN_DUST = registerItem("tin_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> COPPER_DUST = registerItem("copper_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> IRON_DUST = registerItem("iron_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> GOLD_DUST = registerItem("gold_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> LEAD_DUST = registerItem("lead_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> URANIUM_DUST = registerItem("uranium_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> SILVER_DUST = registerItem("silver_dust", () -> new MaterialItem());

    public static final RegistryObject<Item> PURIFIED_TIN = registerItem("purified_tin", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_COPPER = registerItem("purified_copper", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_IRON = registerItem("purified_iron", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_GOLD = registerItem("purified_gold", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_LEAD = registerItem("purified_lead", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_URANIUM = registerItem("purified_uranium", () -> new MaterialItem());
    public static final RegistryObject<Item> PURIFIED_SILVER = registerItem("purified_silver", () -> new MaterialItem());

    public static final RegistryObject<Item> TIN_CHUNK = registerItem("tin_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> COPPER_CHUNK = registerItem("copper_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> IRON_CHUNK = registerItem("iron_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> GOLD_CHUNK = registerItem("gold_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> LEAD_CHUNK = registerItem("lead_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> URANIUM_CHUNK = registerItem("uranium_chunk", () -> new MaterialItem());
    public static final RegistryObject<Item> SILVER_CHUNK = registerItem("silver_chunk", () -> new MaterialItem());

    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> LAPIS_LAZULI_DUST = registerItem("lapis_lazuli_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> DIAMOND_DUST = registerItem("diamond_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> ENERGIUM_DUST = registerItem("energium_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> STONE_DUST = registerItem("stone_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> DEEPSLATE_DUST = registerItem("deepslate_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> SAWDUST = registerItem("sawdust", () -> new MaterialItem());
    public static final RegistryObject<Item> SULFUR_DUST = registerItem("sulfur_dust", () -> new MaterialItem());
    public static final RegistryObject<Item> MUD_PILE = registerItem("mud_pile", () -> new MaterialItem());

    public static final RegistryObject<Item> STICKY_RESIN = registerItem("sticky_resin", () -> new MaterialItem());
    public static final RegistryObject<Item> RUBBER = registerItem("rubber", () -> new MaterialItem());

    public static final RegistryObject<Item> ELECTRONIC_CIRCUIT = registerItem("electronic_circuit", () -> new MaterialItem());
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = registerItem("advanced_circuit", () -> new MaterialItem());

    public static final RegistryObject<Item> BATTERY = registerItem("battery", () -> new EnergyStorageItem(0, 10000, EnergyType.BOTH, EnergyTier.BASIC));
    public static final RegistryObject<Item> ADVANCED_BATTERY = registerItem("advanced_battery", () -> new EnergyStorageItem(0, 40000, EnergyType.BOTH, EnergyTier.STANDARD));
    public static final RegistryObject<Item> ENERGY_CRYSTAL = registerItem("energy_crystal", () -> new EnergyStorageItem(0, 100000, EnergyType.BOTH, EnergyTier.ADVANCED));
    public static final RegistryObject<Item> LAPOTRON_CRYSTAL = registerItem("lapotron_crystal", () -> new EnergyStorageItem(0, 1000000, EnergyType.BOTH, EnergyTier.SUPER));

    public static final RegistryObject<Item> FLUID_CELL = registerItem("fluid_cell", () -> new FluidCell());

    public static final RegistryObject<Item> IRON_ROD = registerItem("iron_rod", () -> new MaterialItem());

    public static final RegistryObject<Item> IRIDIUM_SHARD = registerItem("iridium_shard", () -> new Iridium());
    public static final RegistryObject<Item> IRIDIUM = registerItem("iridium", () -> new Iridium());

    public static final RegistryObject<Item> COPPER_PLATE = registerItem("copper_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> TIN_PLATE = registerItem("tin_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> IRON_PLATE = registerItem("iron_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> LEAD_PLATE = registerItem("lead_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> GOLD_PLATE = registerItem("gold_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> BRONZE_PLATE = registerItem("bronze_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> STEEL_PLATE = registerItem("steel_plate", () -> new MaterialItem());
    public static final RegistryObject<Item> IRIDIUM_PLATE = registerItem("iridium_plate", () -> new Iridium());
    public static final RegistryObject<Item> LAPIS_LAZULI_PLATE = registerItem("lapis_lazuli_plate", () -> new MaterialItem());

    public static final RegistryObject<Item> CARBON_FIBERS = registerItem("carbon_fibers", () -> new MaterialItem());
    public static final RegistryObject<Item> COMBINED_CARBON_FIBERS = registerItem("combined_carbon_fibers", () -> new MaterialItem());
    public static final RegistryObject<Item> CARBON_PLATE = registerItem("carbon_plate", () -> new MaterialItem());

    public static final RegistryObject<Item> ADVANCED_ALLOY = registerItem("advanced_alloy", () -> new MaterialItem());

    public static final RegistryObject<Item> NIGHTVISION_GOGGLES = registerItem("nightvision_goggles", () -> new NightVisionGoggles());
    public static final RegistryObject<Item> RUBBER_BOOTS = registerItem("rubber_boots", () -> new BaseArmor(ModArmorMaterials.RUBBER, EquipmentSlot.FEET));

    public static final RegistryObject<Item> BRONZE_HELMET = registerItem("bronze_helmet", () -> new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = registerItem("bronze_chestplate", () -> new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = registerItem("bronze_leggings", () -> new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS));
    public static final RegistryObject<Item> BRONZE_BOOTS = registerItem("bronze_boots", () -> new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.FEET));

    public static final RegistryObject<Item> BRONZE_SWORD = registerItem("bronze_sword", () -> new BronzeSword());
    public static final RegistryObject<Item> BRONZE_PICKAXE = registerItem("bronze_pickaxe", () -> new BronzePickaxe());
    public static final RegistryObject<Item> BRONZE_AXE = registerItem("bronze_axe", () -> new BronzeAxe());
    public static final RegistryObject<Item> BRONZE_SHOVEL = registerItem("bronze_shovel", () -> new BronzeShovel());
    public static final RegistryObject<Item> BRONZE_HOE = registerItem("bronze_hoe", () -> new BronzeHoe());

    public static final RegistryObject<Item> NANO_HELMET = registerItem("nano_helmet", () -> new NanoHelmet(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> NANO_CHESTPLATE = registerItem("nano_chestplate", () -> new ItemNanoArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> NANO_LEGGINGS = registerItem("nano_leggings", () -> new ItemNanoArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> NANO_BOOTS = registerItem("nano_boots", () -> new ItemNanoArmor(EquipmentSlot.FEET));
    public static final RegistryObject<Item> NANO_SABER = registerItem("nano_saber", () -> new ItemNanosaber());

    public static final RegistryObject<Item> SMALL_POWER_UNIT = registerItem("small_power_unit", () -> new DummyItem(CreativeModeTab.TAB_REDSTONE));
    public static final RegistryObject<Item> POWER_UNIT = registerItem("power_unit", () -> new DummyItem(CreativeModeTab.TAB_REDSTONE));
    public static final RegistryObject<Item> COIL = registerItem("coil", () -> new DummyItem(CreativeModeTab.TAB_REDSTONE));
    public static final RegistryObject<Item> ELECTRIC_MOTOR = registerItem("electric_motor", () -> new DummyItem(CreativeModeTab.TAB_REDSTONE));
    public static final RegistryObject<Item> TIN_CAN = registerItem("tin_can", () -> new DummyItem(CreativeModeTab.TAB_FOOD));
    public static final RegistryObject<Item> FILLED_TIN_CAN = registerItem("filled_tin_can", () -> new FilledTinCan());
    public static final RegistryObject<Item> SCRAP = registerItem("scrap", () -> new Scrap());
    public static final RegistryObject<Item> SCRAP_BOX = registerItem("scrap_box", () -> new ScrapBox());

    public static final RegistryObject<Item> HAMMER = registerItem("hammer", () -> new ToolItem(80));
    public static final RegistryObject<Item> CUTTER = registerItem("cutter", () -> new ToolItem(60));
    public static final RegistryObject<Item> TREETAP = registerItem("treetap", () -> new Treetap(20));
    public static final RegistryObject<Item> WRENCH = registerItem("wrench", () -> new Wrench(120));
    public static final RegistryObject<Item> TOOL_BOX = registerItem("tool_box", () -> new WIPItem());

    public static final RegistryObject<Item> ELECTRIC_TREETAP = registerItem("electric_treetap", () -> new ElectricTreetap(0, 10000, EnergyType.RECEIVE, EnergyTier.BASIC));
    public static final RegistryObject<Item> ELECTRIC_WRENCH = registerItem("electric_wrench", () -> new ElectricWrench(0, 10000, EnergyType.RECEIVE, EnergyTier.BASIC));

    public static final RegistryObject<Item> CHAINSAW = registerItem("chainsaw", () -> new Chainsaw(Tiers.IRON,6.0F, -3.1F, 0, 30000, 50, 100, EnergyType.RECEIVE, EnergyTier.BASIC));
    public static final RegistryObject<Item> DIAMOND_CHAINSAW = registerItem("diamond_chainsaw", () -> new Chainsaw(Tiers.DIAMOND,5.0F, -3.0F, 0, 80000, 70, 120, EnergyType.RECEIVE, EnergyTier.STANDARD));
    public static final RegistryObject<Item> IRIDIUM_CHAINSAW = registerItem("iridium_chainsaw", () -> new Chainsaw(ModTiers.IRIDIUM,5.0F, -3.0F, 0, 300000, 200, 400, EnergyType.RECEIVE, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> MINING_DRILL = registerItem("mining_drill", () -> new MiningDrill(Tiers.IRON,1, -2.8F, 0, 30000, 50, 100, EnergyType.RECEIVE, EnergyTier.BASIC));
    public static final RegistryObject<Item> DIAMOND_DRILL = registerItem("diamond_drill", () -> new MiningDrill(Tiers.DIAMOND,1, -2.8F, 0, 80000, 70, 120, EnergyType.RECEIVE, EnergyTier.STANDARD));
    public static final RegistryObject<Item> IRIDIUM_DRILL = registerItem("iridium_drill", () -> new MiningDrill(ModTiers.IRIDIUM, 1, -2.8F, 0, 300000, 200, 400, EnergyType.RECEIVE, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> ELECTRIC_HOE = registerItem("electric_hoe", () -> new ElectricHoe(Tiers.IRON,-2, -1.0F, 0, 10000, 50, 100, 50, EnergyType.RECEIVE, EnergyTier.BASIC));
    public static final RegistryObject<Item> WIND_METER = registerItem("wind_meter", () -> new WIPItem());
    public static final RegistryObject<Item> IE_METER = registerItem("ie_meter", () -> new IEMeter());
    public static final RegistryObject<Item> DEBUG_STICK = registerItem("debug_stick", () -> new DebugStick());
    public static final RegistryObject<Item> MULTI_TOOL = registerItem("multi_tool", () -> new MultiTool(Tiers.DIAMOND,-3, 0.0F, 0, 300000, 800, 1400, 500, EnergyType.RECEIVE, EnergyTier.ADVANCED));

    public static final RegistryObject<Item> BASIC_TRANSPORTER = registerItem("basic_transporter", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_TRANSPORTER = registerItem("advanced_transporter", () -> new WIPItem());

    public static final RegistryObject<Item> MEMORY_CARD = registerItem("memory_card", () -> new MemoryCardItem());
    public static final RegistryObject<Item> BIO_CHAFF = registerItem("bio_chaff", () -> new MaterialItem());
    public static final RegistryObject<Item> FERTILIZER = registerItem("fertilizer", () -> new Fertilizer());
    public static final RegistryObject<Item> HEAT_CONDUCTOR = registerItem("heat_conductor", () -> new MaterialItem());
    public static final RegistryObject<Item> FOAM_POWDER = registerItem("foam_powder", () -> new MaterialItem());
    public static final RegistryObject<Item> REINFORCED_FOAM_POWDER = registerItem("reinforced_foam_powder", () -> new MaterialItem());

    public static final RegistryObject<Item> FOAM_SPRAYER = registerItem("foam_sprayer", () -> new FoamSprayer());
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

    public static final RegistryObject<Item> CHARGING_BATTERY = registerItem("charging_battery", () -> new ChargingBattery(40000, EnergyType.BOTH, EnergyTier.BASIC));
    public static final RegistryObject<Item> ADVANCED_CHARGING_BATTERY = registerItem("advanced_charging_battery", () ->new ChargingBattery(400000, EnergyType.BOTH, EnergyTier.STANDARD));
    public static final RegistryObject<Item> CHARGING_ENERGY_CRYSTAL = registerItem("charging_energy_crystal", () -> new ChargingBattery(4000000, EnergyType.BOTH, EnergyTier.ADVANCED));
    public static final RegistryObject<Item> CHARGING_LAPOTRON_CRYSTAL = registerItem("charging_lapotron_crystal", () -> new ChargingBattery(40000000, EnergyType.BOTH, EnergyTier.SUPER));

//    public static final RegistryObject<Item> UPGRADE_BASE = registerItem("upgrade_base", () -> new ItemUpgradeBase());
//    public static final RegistryObject<Item> ADVANCED_UPGRADE_BASE = registerItem("advanced_upgrade_base", () -> new ItemUpgradeBase());
    public static final RegistryObject<Item> OVERCLOCKER_UPGRADE = registerItem("overclocker_upgrade", () -> new OverclockerUpgrade());
    public static final RegistryObject<Item> TRANSFORMER_UPGRADE = registerItem("transformer_upgrade", () -> new TransformerUpgrade());
    public static final RegistryObject<Item> ENERGY_STORAGE_UPGRADE = registerItem("energy_storage_upgrade", () -> new EnergyStorageUpgrade());
    public static final RegistryObject<Item> REDSTONE_SIGNAL_INVERTER_UPGRADE = registerItem("redstone_signal_inverter_upgrade", () -> new RedstoneSignalInverter());
    public static final RegistryObject<Item> EJECTOR_UPGRADE = registerItem("ejector_upgrade", () -> new ItemDirectionalUpgrade(UpgradeType.EJECTOR));
    public static final RegistryObject<Item> PULLING_UPGRADE = registerItem("pulling_upgrade", () -> new ItemDirectionalUpgrade(UpgradeType.PULLING));
    public static final RegistryObject<Item> FLUID_EJECTOR_UPGRADE = registerItem("fluid_ejector_upgrade", () -> new ItemDirectionalUpgrade(UpgradeType.FLUID_EJECTOR));
    public static final RegistryObject<Item> FLUID_PULLING_UPGRADE = registerItem("fluid_pulling_upgrade", () -> new ItemDirectionalUpgrade(UpgradeType.FLUID_PULLING));
    public static final RegistryObject<Item> ADVANCED_EJECTOR_UPGRADE = registerItem("advanced_ejector_upgrade", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_PULLING_UPGRADE = registerItem("advanced_pulling_upgrade", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_FLUID_EJECTOR_UPGRADE = registerItem("advanced_fluid_ejector_upgrade", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_FLUID_PULLING_UPGRADE = registerItem("advanced_fluid_pulling_upgrade", () -> new WIPItem());

    public static final RegistryObject<Item> SMALL_COOLANT_CELL = registerItem("small_coolant_cell", () -> new WIPItem());
    public static final RegistryObject<Item> MEDIUM_COOLANT_CELL = registerItem("medium_coolant_cell", () -> new WIPItem());
    public static final RegistryObject<Item> LARGE_COOLANT_CELL = registerItem("large_coolant_cell", () -> new WIPItem());

    public static final RegistryObject<Item> HEAT_VENT = registerItem("heat_vent", () -> new WIPItem());
    public static final RegistryObject<Item> OVERCLOCKED_HEAT_VENT = registerItem("overclocked_heat_vent", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_HEAT_VENT = registerItem("advanced_heat_vent", () -> new WIPItem());

    public static final RegistryObject<Item> HEAT_EXCHANGER = registerItem("heat_exchanger", () -> new WIPItem());
    public static final RegistryObject<Item> ADVANCED_HEAT_EXCHANGER = registerItem("advanced_heat_exchanger", () -> new WIPItem());
    public static final RegistryObject<Item> COMPONENT_HEAT_EXCHANGER = registerItem("component_heat_exchanger", () -> new WIPItem());
    public static final RegistryObject<Item> COMPONENT_HEAT_VENT = registerItem("component_heat_vent", () -> new WIPItem());
    public static final RegistryObject<Item> CONTAINMENT_REACTOR_PLATING = registerItem("containment_reactor_plating", () -> new WIPItem());
    public static final RegistryObject<Item> HEAT_CAPACITY_REACTOR_PLATING = registerItem("heat_capacity_reactor_plating", () -> new WIPItem());
    public static final RegistryObject<Item> HEATING_CELL = registerItem("heating_cell", () -> new WIPItem());
    public static final RegistryObject<Item> REACTOR_HEAT_EXCHANGER = registerItem("reactor_heat_exchanger", () -> new WIPItem());
    public static final RegistryObject<Item> REACTOR_HEAT_VENT = registerItem("reactor_heat_vent", () -> new WIPItem());
    public static final RegistryObject<Item> REACTOR_PLATING = registerItem("reactor_plating", () -> new WIPItem());
    public static final RegistryObject<Item> RSH_CONDENSATOR = registerItem("rsh_condensator", () -> new WIPItem());
    public static final RegistryObject<Item> ZLH_CONDENSATOR = registerItem("zlh_condensator", () -> new WIPItem());
    public static final RegistryObject<Item> NEUTRON_REFLECTOR = registerItem("neutron_reflector", () -> new WIPItem());
    public static final RegistryObject<Item> THICK_NEUTRON_REFLECTOR = registerItem("thick_neutron_reflector", () -> new WIPItem());
    public static final RegistryObject<Item> IRIDIUM_NEUTRON_REFLECTOR = registerItem("iridium_neutron_reflector", () -> new WIPItem());

//    public static final RegistryObject<Item> TINY_PILE_URANIUM_235 = registerItem("tiny_pile_uranium_235", () -> new WIPItem());
//    public static final RegistryObject<Item> URANIUM_238 = registerItem("uranium_238", () -> new WIPItem());

    public static final RegistryObject<Item> FUEL_ROD = registerItem("fuel_rod", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM = registerItem("fuel_rod_uranium", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM_DEPLETED = registerItem("fuel_rod_uranium_depleted", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM_DUAL = registerItem("fuel_rod_uranium_dual", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM_DUAL_DEPLETED = registerItem("fuel_rod_uranium_dual_depleted", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM_QUAD = registerItem("fuel_rod_uranium_quad", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_URANIUM_QUAD_DEPLETED = registerItem("fuel_rod_uranium_quad_depleted", () -> new WIPItem());

    public static final RegistryObject<Item> FUEL_ROD_MOX = registerItem("fuel_rod_mox", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_MOX_DEPLETED = registerItem("fuel_rod_mox_depleted", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_MOX_DUAL = registerItem("fuel_rod_mox_dual", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_MOX_DUAL_DEPLETED = registerItem("fuel_rod_mox_dual_depleted", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_MOX_QUAD = registerItem("fuel_rod_mox_quad", () -> new WIPItem());
    public static final RegistryObject<Item> FUEL_ROD_MOX_QUAD_DEPLETED = registerItem("fuel_rod_mox_quad_depleted", () -> new WIPItem());

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
       return ITEMS.register(name, item);
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
       return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP)));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockIronScaffolding(RegistryObject<B> block) {
       return ITEMS.register(block.getId().getPath(), () -> new ItemScaffolding(block.get()));
    }

    private static <T extends Block> RegistryObject<Item> fromBlockElectric(RegistryObject<T> block) {
       return ITEMS.register(block.getId().getPath(), () -> new BlockItemElectric(block.get(), new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}