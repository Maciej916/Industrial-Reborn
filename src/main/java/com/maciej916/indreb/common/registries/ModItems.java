package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.item.*;
import com.maciej916.indreb.common.item.base.DummyItem;
import com.maciej916.indreb.common.item.base.EnergyStorageItem;
import com.maciej916.indreb.common.item.base.WIPItem;
import com.maciej916.indreb.common.item.impl.FilledTinCan;
import com.maciej916.indreb.common.item.impl.IridiumItem;
import com.maciej916.indreb.common.item.impl.Scrap;
import com.maciej916.indreb.common.item.impl.ScrapBox;
import com.maciej916.indreb.common.item.impl.tools.*;
import com.maciej916.indreb.common.item.impl.treetap.ElectricTreetap;
import com.maciej916.indreb.common.item.impl.treetap.Treetap;
import com.maciej916.indreb.common.item.impl.wrench.ElectricWrench;
import com.maciej916.indreb.common.item.impl.wrench.Wrench;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModItems {

    public static Item RAW_TIN;
    public static Item TIN_INGOT;
    public static Item SILVER_INGOT;
    public static Item BRONZE_INGOT;
    public static Item STEEL_INGOT;
    public static Item MIXED_METAL_INGOT;

    public static Item CRUSHED_TIN;
    public static Item CRUSHED_COPPER;
    public static Item CRUSHED_IRON;
    public static Item CRUSHED_GOLD;

    public static Item COAL_DUST;
    public static Item DIAMOND_DUST;
    public static Item ENERGIUM_DUST;
    public static Item SILVER_DUST;
    public static Item STONE_DUST;
    public static Item DEEPSLATE_DUST;
    public static Item SAWDUST;

    public static Item STICKY_RESIN;
    public static Item RUBBER;

    public static Item ELECTRONIC_CIRCUIT;
    public static Item ADVANCED_CIRCUIT;

    public static Item BATTERY;
    public static Item ADVANCED_BATTERY;
    public static Item ENERGY_CRYSTAL;
    public static Item LAPOTRON_CRYSTAL;

    public static Item FLUID_CELL;

    public static Item IRON_ROD;

    public static Item IRIDIUM_SHARD;
    public static Item IRIDIUM;

    public static Item COPPER_PLATE;
    public static Item TIN_PLATE;
    public static Item IRON_PLATE;
    public static Item GOLD_PLATE;
    public static Item BRONZE_PLATE;
    public static Item STEEL_PLATE;
    public static Item IRIDIUM_PLATE;

    public static Item CARBON_FIBERS;
    public static Item COMBINED_CARBON_FIBERS;
    public static Item CARBON_PLATE;

    public static Item ADVANCED_ALLOY;

    public static Item NIGHTVISION_GOGGLES;
    public static Item RUBBER_BOOTS;

    public static Item BRONZE_HELMET;
    public static Item BRONZE_CHESTPLATE;
    public static Item BRONZE_LEGGINGS;
    public static Item BRONZE_BOOTS;

    public static Item BRONZE_SWORD;
    public static Item BRONZE_PICKAXE;
    public static Item BRONZE_AXE;
    public static Item BRONZE_SHOVEL;
    public static Item BRONZE_HOE;

    public static Item NANO_HELMET;
    public static Item NANO_CHESTPLATE;
    public static Item NANO_LEGGINGS;
    public static Item NANO_BOOTS;
    public static Item NANO_SABER;

    public static Item SMALL_POWER_UNIT;
    public static Item POWER_UNIT;
    public static Item COIL;
    public static Item ELECTRIC_MOTOR;
    public static Item TIN_CAN;
    public static Item FILLED_TIN_CAN;
    public static Item SCRAP;
    public static Item SCRAP_BOX;

    public static Item HAMMER;
    public static Item CUTTER;

    public static Item TREETAP;
    public static Item ELECTRIC_TREETAP;

    public static Item WRENCH;
    public static Item ELECTRIC_WRENCH;

    public static Item CHAINSAW;
    public static Item DIAMOND_CHAINSAW;
    public static Item IRIDIUM_CHAINSAW;

    public static Item MINING_DRILL;
    public static Item DIAMOND_DRILL;
    public static Item IRIDIUM_DRILL;

    public static Item ELECTRIC_HOE;
    public static Item WIND_METER;
    public static Item IE_METER;
    public static Item MULTI_TOOL;

    public static Item FOAM_SPRAYER;
    public static Item PAINTER;
    public static Item PAINTER_WHITE;
    public static Item PAINTER_RED;
    public static Item PAINTER_ORANGE;
    public static Item PAINTER_PINK;
    public static Item PAINTER_YELLOW;
    public static Item PAINTER_LIME;
    public static Item PAINTER_GREEN;
    public static Item PAINTER_LIGHT_BLUE;
    public static Item PAINTER_CYAN;
    public static Item PAINTER_BLUE;
    public static Item PAINTER_MAGENTA;
    public static Item PAINTER_PURPLE;
    public static Item PAINTER_BROWN;
    public static Item PAINTER_GRAY;
    public static Item PAINTER_LIGHT_GRAY;
    public static Item PAINTER_BLACK;

    public static Item BIO_CHAFF;
    public static Item FERTILIZER;
    public static Item HEAT_CONDUCTOR;

    public static Item FOAM_POWDER;
    public static Item REINFORCED_FOAM_POWDER;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        RAW_TIN = registerItem(new ItemBasic(), "raw_tin");
        TIN_INGOT = registerItem(new ItemBasic(), "tin_ingot");
        BRONZE_INGOT = registerItem(new ItemBasic(), "bronze_ingot");
        STEEL_INGOT = registerItem(new ItemBasic(), "steel_ingot");
        MIXED_METAL_INGOT = registerItem(new ItemBasic(), "mixed_metal_ingot");
        SILVER_INGOT = registerItem(new ItemBasic(), "silver_ingot");

        CRUSHED_TIN = registerItem(new ItemBasic(), "crushed_tin");
        CRUSHED_COPPER = registerItem(new ItemBasic(), "crushed_copper");
        CRUSHED_IRON = registerItem(new ItemBasic(), "crushed_iron");
        CRUSHED_GOLD = registerItem(new ItemBasic(), "crushed_gold");

        COAL_DUST = registerItem(new ItemBasic(), "coal_dust");
        DIAMOND_DUST = registerItem(new ItemBasic(), "diamond_dust");
        ENERGIUM_DUST = registerItem(new ItemBasic(), "energium_dust");
        SILVER_DUST = registerItem(new ItemBasic(), "silver_dust");
        STONE_DUST = registerItem(new ItemBasic(), "stone_dust");
        DEEPSLATE_DUST = registerItem(new ItemBasic(), "deepslate_dust");
        SAWDUST = registerItem(new ItemBasic(), "sawdust");

        STICKY_RESIN = registerItem(new ItemBasic(), "sticky_resin");
        RUBBER = registerItem(new ItemBasic(), "rubber");

        ELECTRONIC_CIRCUIT = registerItem(new ItemBasic(), "electronic_circuit");
        ADVANCED_CIRCUIT = registerItem(new ItemBasic(), "advanced_circuit");

        BATTERY = registerItem(new EnergyStorageItem(0, 10000, EnumEnergyType.BOTH, EnergyTier.BASIC), "battery");
        ADVANCED_BATTERY = registerItem(new EnergyStorageItem(0, 40000, EnumEnergyType.BOTH, EnergyTier.STANDARD), "advanced_battery");
        ENERGY_CRYSTAL = registerItem(new EnergyStorageItem(0, 100000, EnumEnergyType.BOTH, EnergyTier.ADVANCED), "energy_crystal");
        LAPOTRON_CRYSTAL = registerItem(new EnergyStorageItem(0, 1000000, EnumEnergyType.BOTH, EnergyTier.SUPER), "lapotron_crystal");

        FLUID_CELL = registerItem(new FluidCell(), "fluid_cell");

        IRON_ROD = registerItem(new ItemBasic(), "iron_rod");

        IRIDIUM_SHARD = registerItem(new IridiumItem(), "iridium_shard");
        IRIDIUM = registerItem(new IridiumItem(), "iridium");

        COPPER_PLATE = registerItem(new ItemBasic(), "copper_plate");
        TIN_PLATE = registerItem(new ItemBasic(), "tin_plate");
        IRON_PLATE = registerItem(new ItemBasic(), "iron_plate");
        GOLD_PLATE = registerItem(new ItemBasic(), "gold_plate");
        BRONZE_PLATE = registerItem(new ItemBasic(), "bronze_plate");
        STEEL_PLATE = registerItem(new ItemBasic(), "steel_plate");
        IRIDIUM_PLATE = registerItem(new IridiumItem(), "iridium_plate");

        CARBON_FIBERS = registerItem(new ItemBasic(), "carbon_fibers");
        COMBINED_CARBON_FIBERS = registerItem(new ItemBasic(), "combined_carbon_fibers");
        CARBON_PLATE = registerItem(new ItemBasic(), "carbon_plate");

        ADVANCED_ALLOY = registerItem(new ItemBasic(), "advanced_alloy");

        NIGHTVISION_GOGGLES = registerItem(new IndRebArmour(ModArmorMaterials.NIGHTVISION, EquipmentSlot.HEAD), "nightvision_goggles");
        RUBBER_BOOTS = registerItem(new IndRebArmour(ModArmorMaterials.RUBBER, EquipmentSlot.FEET), "rubber_boots");

        BRONZE_HELMET = registerItem(new IndRebArmour(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD), "bronze_helmet");
        BRONZE_CHESTPLATE = registerItem(new IndRebArmour(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST), "bronze_chestplate");
        BRONZE_LEGGINGS = registerItem(new IndRebArmour(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS), "bronze_leggings");
        BRONZE_BOOTS = registerItem(new IndRebArmour(ModArmorMaterials.BRONZE, EquipmentSlot.FEET), "bronze_boots");

        BRONZE_SWORD = registerItem(new IndRebSword(ModTiers.BRONZE, 3, -2.4F), "bronze_sword");
        BRONZE_PICKAXE = registerItem(new IndRebPickaxe(ModTiers.BRONZE, 1, -2.8F), "bronze_pickaxe");
        BRONZE_AXE = registerItem(new IndRebAxe(ModTiers.BRONZE, 6.0F, -3.1F), "bronze_axe");
        BRONZE_SHOVEL = registerItem(new IndRebShovel(ModTiers.BRONZE, 1.5F, -3.0F), "bronze_shovel");
        BRONZE_HOE = registerItem(new IndRebHoe(ModTiers.BRONZE, -2, -1.0F), "bronze_hoe");

        NANO_HELMET = registerItem(new ItemNanoArmour(EquipmentSlot.HEAD, 0, 1000000, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "nano_helmet");
        NANO_CHESTPLATE = registerItem(new ItemNanoArmour(EquipmentSlot.CHEST, 0, 1000000, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "nano_chestplate");
        NANO_LEGGINGS = registerItem(new ItemNanoArmour(EquipmentSlot.LEGS, 0, 1000000, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "nano_leggings");
        NANO_BOOTS = registerItem(new ItemNanoArmour(EquipmentSlot.FEET, 0, 1000000, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "nano_boots");
        NANO_SABER = registerItem(new ItemNanosaber(1, -3F, 0, 160000, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "nano_saber");

        SMALL_POWER_UNIT = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "small_power_unit");
        POWER_UNIT = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "power_unit");
        COIL = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "coil");
        ELECTRIC_MOTOR = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "electric_motor");
        TIN_CAN = registerItem(new DummyItem(CreativeModeTab.TAB_FOOD), "tin_can");
        FILLED_TIN_CAN = registerItem(new FilledTinCan(), "filled_tin_can");
        SCRAP = registerItem(new Scrap(), "scrap");
        SCRAP_BOX = registerItem(new ScrapBox(), "scrap_box");

        // have container
        HAMMER = registerItem(new ItemTool(80), "hammer");
        CUTTER = registerItem(new ItemTool(60), "cutter");

        TREETAP = registerItem(new Treetap(20), "treetap");
        ELECTRIC_TREETAP = registerItem(new ElectricTreetap(0, 10000, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "electric_treetap");

        WRENCH = registerItem(new Wrench(120), "wrench");
        ELECTRIC_WRENCH = registerItem(new ElectricWrench(0, 10000, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "electric_wrench");

        CHAINSAW = registerItem(new Chainsaw(Tiers.IRON,6.0F, -3.1F, 0, 30000, 50, 100, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "chainsaw");
        DIAMOND_CHAINSAW = registerItem(new Chainsaw(Tiers.DIAMOND,5.0F, -3.0F, 0, 80000, 70, 120, EnumEnergyType.RECEIVE, EnergyTier.STANDARD), "diamond_chainsaw");
        IRIDIUM_CHAINSAW = registerItem(new Chainsaw(ModTiers.IRIDIUM,5.0F, -3.0F, 0, 300000, 200, 400, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "iridium_chainsaw");

        MINING_DRILL = registerItem(new MiningDrill(Tiers.IRON,1, -2.8F, 0, 30000, 50, 100, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "mining_drill");
        DIAMOND_DRILL = registerItem(new MiningDrill(Tiers.DIAMOND,1, -2.8F, 0, 80000, 70, 120, EnumEnergyType.RECEIVE, EnergyTier.STANDARD), "diamond_drill");
        IRIDIUM_DRILL = registerItem(new MiningDrill(ModTiers.IRIDIUM, 1, -2.8F, 0, 300000, 200, 400, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "iridium_drill");

        ELECTRIC_HOE = registerItem(new ElectricHoe(Tiers.IRON,-2, -1.0F, 0, 10000, 50, 100, 50, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "electric_hoe");
//        WIND_METER = registerItem(new WindMeter(0, 1000, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "wind_meter");
        WIND_METER = registerItem(new WIPItem(), "wind_meter");
//        IE_METER = registerItem(new IEMeter(CreativeModeTab.TAB_TOOLS), "ie_meter");
        IE_METER = registerItem(new WIPItem(), "ie_meter");
        MULTI_TOOL = registerItem(new MultiTool(Tiers.DIAMOND,-3, 0.0F, 0, 300000, 800, 1400, 500, EnumEnergyType.RECEIVE, EnergyTier.ADVANCED), "multi_tool");

        FOAM_SPRAYER = registerItem(new WIPItem(), "foam_sprayer");
        PAINTER = registerItem(new ItemTool(1), "painter");
        PAINTER_WHITE = registerItem(new ItemPainter(MaterialColor.WOOL), "painter_white");
        PAINTER_RED = registerItem(new ItemPainter(MaterialColor.COLOR_RED), "painter_red");
        PAINTER_ORANGE = registerItem(new ItemPainter(MaterialColor.COLOR_ORANGE), "painter_orange");
        PAINTER_PINK = registerItem(new ItemPainter(MaterialColor.COLOR_PINK), "painter_pink");
        PAINTER_YELLOW = registerItem(new ItemPainter(MaterialColor.COLOR_YELLOW), "painter_yellow");
        PAINTER_LIME = registerItem(new ItemPainter(MaterialColor.COLOR_LIGHT_GREEN), "painter_lime");
        PAINTER_GREEN = registerItem(new ItemPainter(MaterialColor.COLOR_GREEN), "painter_green");
        PAINTER_LIGHT_BLUE = registerItem(new ItemPainter(MaterialColor.COLOR_LIGHT_BLUE), "painter_light_blue");
        PAINTER_CYAN = registerItem(new ItemPainter(MaterialColor.COLOR_CYAN), "painter_cyan");
        PAINTER_BLUE = registerItem(new ItemPainter(MaterialColor.COLOR_BLUE), "painter_blue");
        PAINTER_MAGENTA = registerItem(new ItemPainter(MaterialColor.COLOR_MAGENTA), "painter_magenta");
        PAINTER_PURPLE = registerItem(new ItemPainter(MaterialColor.COLOR_PURPLE), "painter_purple");
        PAINTER_BROWN = registerItem(new ItemPainter(MaterialColor.COLOR_BROWN), "painter_brown");
        PAINTER_GRAY = registerItem(new ItemPainter(MaterialColor.COLOR_GRAY), "painter_gray");
        PAINTER_LIGHT_GRAY = registerItem(new ItemPainter(MaterialColor.COLOR_LIGHT_GRAY), "painter_light_gray");
        PAINTER_BLACK = registerItem(new ItemPainter(MaterialColor.COLOR_BLACK), "painter_black");

        BIO_CHAFF = registerItem(new DummyItem(CreativeModeTab.TAB_MATERIALS), "bio_chaff");
        FERTILIZER = registerItem(new DummyItem(CreativeModeTab.TAB_MATERIALS), "fertilizer");
        HEAT_CONDUCTOR = registerItem(new DummyItem(CreativeModeTab.TAB_MATERIALS), "heat_conductor");
        FOAM_POWDER = registerItem(new DummyItem(CreativeModeTab.TAB_MATERIALS), "foam_powder");
        REINFORCED_FOAM_POWDER = registerItem(new DummyItem(CreativeModeTab.TAB_MATERIALS), "reinforced_foam_powder");




    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

}