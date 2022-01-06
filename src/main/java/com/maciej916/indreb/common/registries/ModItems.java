package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.ModArmorMaterials;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.base.*;
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
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModItems {

    public static Item RAW_TIN;
    public static Item RAW_LEAD;
    public static Item URANIUM;

    public static Item TIN_INGOT;
    public static Item SILVER_INGOT;
    public static Item BRONZE_INGOT;
    public static Item STEEL_INGOT;
    public static Item LEAD_INGOT;
    public static Item REFINED_URANIUM;
    public static Item MIXED_METAL_INGOT;

    public static Item CRUSHED_TIN;
    public static Item CRUSHED_COPPER;
    public static Item CRUSHED_IRON;
    public static Item CRUSHED_GOLD;
    public static Item CRUSHED_LEAD;

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
    public static Item LEAD_PLATE;
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
    public static Item DEBUG_STICK;
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

    public static Item OVERCLOCKER_UPGRADE;
    public static Item TRANSFORMER_UPGRADE;
    public static Item ENERGY_STORAGE_UPGRADE;
    public static Item REDSTONE_SIGNAL_INVERTER_UPGRADE;
    public static Item EJECTOR_UPGRADE;
    public static Item PULLING_UPGRADE;
    public static Item FLUID_EJECTOR_UPGRADE;
    public static Item FLUID_PULLING_UPGRADE;
    public static Item ADVANCED_EJECTOR_UPGRADE;
    public static Item ADVANCED_PULLING_UPGRADE;
    public static Item ADVANCED_FLUID_EJECTOR_UPGRADE;
    public static Item ADVANCED_FLUID_PULLING_UPGRADE;

    public static Item CHARGING_BATTERY;
    public static Item ADVANCED_CHARGING_BATTERY;
    public static Item CHARGING_ENERGY_CRYSTAL;
    public static Item CHARGING_LAPOTRON_CRYSTAL;

    public static Item SMALL_COOLANT_CELL;
    public static Item MEDIUM_COOLANT_CELL;
    public static Item LARGE_COOLANT_CELL;

    public static Item HEAT_EXCHANGER;
    public static Item HEAT_VENT;
    public static Item OVERCLOCKED_HEAT_VENT;
    public static Item ADVANCED_HEAT_EXCHANGER;
    public static Item ADVANCED_HEAT_VENT;
    public static Item COMPONENT_HEAT_EXCHANGER;
    public static Item COMPONENT_HEAT_VENT;
    public static Item CONTAINMENT_REACTOR_PLATING;
    public static Item HEAT_CAPACITY_REACTOR_PLATING;
    public static Item HEATING_CELL;
    public static Item REACTOR_HEAT_EXCHANGER;
    public static Item REACTOR_HEAT_VENT;
    public static Item REACTOR_PLATING;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        RAW_TIN = registerItem(new MaterialItem(), "raw_tin");
        RAW_LEAD = registerItem(new MaterialItem(), "raw_lead");
        URANIUM = registerItem(new MaterialItem(), "uranium");

        TIN_INGOT = registerItem(new MaterialItem(), "tin_ingot");
        BRONZE_INGOT = registerItem(new MaterialItem(), "bronze_ingot");
        STEEL_INGOT = registerItem(new MaterialItem(), "steel_ingot");
        MIXED_METAL_INGOT = registerItem(new MaterialItem(), "mixed_metal_ingot");
        SILVER_INGOT = registerItem(new MaterialItem(), "silver_ingot");
        LEAD_INGOT = registerItem(new MaterialItem(), "lead_ingot");
        REFINED_URANIUM = registerItem(new MaterialItem(), "refined_uranium");

        CRUSHED_TIN = registerItem(new MaterialItem(), "crushed_tin");
        CRUSHED_COPPER = registerItem(new MaterialItem(), "crushed_copper");
        CRUSHED_IRON = registerItem(new MaterialItem(), "crushed_iron");
        CRUSHED_GOLD = registerItem(new MaterialItem(), "crushed_gold");
        CRUSHED_LEAD = registerItem(new MaterialItem(), "crushed_lead");

        COAL_DUST = registerItem(new MaterialItem(), "coal_dust");
        DIAMOND_DUST = registerItem(new MaterialItem(), "diamond_dust");
        ENERGIUM_DUST = registerItem(new MaterialItem(), "energium_dust");
        SILVER_DUST = registerItem(new MaterialItem(), "silver_dust");
        STONE_DUST = registerItem(new MaterialItem(), "stone_dust");
        DEEPSLATE_DUST = registerItem(new MaterialItem(), "deepslate_dust");
        SAWDUST = registerItem(new MaterialItem(), "sawdust");

        STICKY_RESIN = registerItem(new MaterialItem(), "sticky_resin");
        RUBBER = registerItem(new MaterialItem(), "rubber");

        ELECTRONIC_CIRCUIT = registerItem(new MaterialItem(), "electronic_circuit");
        ADVANCED_CIRCUIT = registerItem(new MaterialItem(), "advanced_circuit");

        BATTERY = registerItem(new EnergyStorageItem(0, 10000, EnergyType.BOTH, EnergyTier.BASIC), "battery");
        ADVANCED_BATTERY = registerItem(new EnergyStorageItem(0, 40000, EnergyType.BOTH, EnergyTier.STANDARD), "advanced_battery");
        ENERGY_CRYSTAL = registerItem(new EnergyStorageItem(0, 100000, EnergyType.BOTH, EnergyTier.ADVANCED), "energy_crystal");
        LAPOTRON_CRYSTAL = registerItem(new EnergyStorageItem(0, 1000000, EnergyType.BOTH, EnergyTier.SUPER), "lapotron_crystal");

        FLUID_CELL = registerItem(new FluidCell(), "fluid_cell");

        IRON_ROD = registerItem(new MaterialItem(), "iron_rod");

        IRIDIUM_SHARD = registerItem(new Iridium(), "iridium_shard");
        IRIDIUM = registerItem(new Iridium(), "iridium");

        COPPER_PLATE = registerItem(new MaterialItem(), "copper_plate");
        TIN_PLATE = registerItem(new MaterialItem(), "tin_plate");
        IRON_PLATE = registerItem(new MaterialItem(), "iron_plate");
        LEAD_PLATE = registerItem(new MaterialItem(), "lead_plate");
        GOLD_PLATE = registerItem(new MaterialItem(), "gold_plate");
        BRONZE_PLATE = registerItem(new MaterialItem(), "bronze_plate");
        STEEL_PLATE = registerItem(new MaterialItem(), "steel_plate");
        IRIDIUM_PLATE = registerItem(new Iridium(), "iridium_plate");

        CARBON_FIBERS = registerItem(new MaterialItem(), "carbon_fibers");
        COMBINED_CARBON_FIBERS = registerItem(new MaterialItem(), "combined_carbon_fibers");
        CARBON_PLATE = registerItem(new MaterialItem(), "carbon_plate");

        ADVANCED_ALLOY = registerItem(new MaterialItem(), "advanced_alloy");

        NIGHTVISION_GOGGLES = registerItem(new NightVisionGoggles(), "nightvision_goggles");
        RUBBER_BOOTS = registerItem(new BaseArmor(ModArmorMaterials.RUBBER, EquipmentSlot.FEET), "rubber_boots");

        BRONZE_HELMET = registerItem(new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD), "bronze_helmet");
        BRONZE_CHESTPLATE = registerItem(new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST), "bronze_chestplate");
        BRONZE_LEGGINGS = registerItem(new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS), "bronze_leggings");
        BRONZE_BOOTS = registerItem(new BaseArmor(ModArmorMaterials.BRONZE, EquipmentSlot.FEET), "bronze_boots");

        BRONZE_SWORD = registerItem(new BronzeSword(), "bronze_sword");
        BRONZE_PICKAXE = registerItem(new BronzePickaxe(), "bronze_pickaxe");
        BRONZE_AXE = registerItem(new BronzeAxe(), "bronze_axe");
        BRONZE_SHOVEL = registerItem(new BronzeShovel(), "bronze_shovel");
        BRONZE_HOE = registerItem(new BronzeHoe(), "bronze_hoe");

        NANO_HELMET = registerItem(new NanoHelmet(EquipmentSlot.HEAD), "nano_helmet");
        NANO_CHESTPLATE = registerItem(new ItemNanoArmor(EquipmentSlot.CHEST), "nano_chestplate");
        NANO_LEGGINGS = registerItem(new ItemNanoArmor(EquipmentSlot.LEGS), "nano_leggings");
        NANO_BOOTS = registerItem(new ItemNanoArmor(EquipmentSlot.FEET), "nano_boots");
        NANO_SABER = registerItem(new ItemNanosaber(), "nano_saber");

        SMALL_POWER_UNIT = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "small_power_unit");
        POWER_UNIT = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "power_unit");
        COIL = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "coil");
        ELECTRIC_MOTOR = registerItem(new DummyItem(CreativeModeTab.TAB_REDSTONE), "electric_motor");
        TIN_CAN = registerItem(new DummyItem(CreativeModeTab.TAB_FOOD), "tin_can");
        FILLED_TIN_CAN = registerItem(new FilledTinCan(), "filled_tin_can");
        SCRAP = registerItem(new Scrap(), "scrap");
        SCRAP_BOX = registerItem(new ScrapBox(), "scrap_box");

        // have container
        HAMMER = registerItem(new ToolItem(80), "hammer");
        CUTTER = registerItem(new ToolItem(60), "cutter");

        TREETAP = registerItem(new Treetap(20), "treetap");
        ELECTRIC_TREETAP = registerItem(new ElectricTreetap(0, 10000, EnergyType.RECEIVE, EnergyTier.BASIC), "electric_treetap");

        WRENCH = registerItem(new Wrench(120), "wrench");
        ELECTRIC_WRENCH = registerItem(new ElectricWrench(0, 10000, EnergyType.RECEIVE, EnergyTier.BASIC), "electric_wrench");

        CHAINSAW = registerItem(new Chainsaw(Tiers.IRON,6.0F, -3.1F, 0, 30000, 50, 100, EnergyType.RECEIVE, EnergyTier.BASIC), "chainsaw");
        DIAMOND_CHAINSAW = registerItem(new Chainsaw(Tiers.DIAMOND,5.0F, -3.0F, 0, 80000, 70, 120, EnergyType.RECEIVE, EnergyTier.STANDARD), "diamond_chainsaw");
        IRIDIUM_CHAINSAW = registerItem(new Chainsaw(ModTiers.IRIDIUM,5.0F, -3.0F, 0, 300000, 200, 400, EnergyType.RECEIVE, EnergyTier.ADVANCED), "iridium_chainsaw");

        MINING_DRILL = registerItem(new MiningDrill(Tiers.IRON,1, -2.8F, 0, 30000, 50, 100, EnergyType.RECEIVE, EnergyTier.BASIC), "mining_drill");
        DIAMOND_DRILL = registerItem(new MiningDrill(Tiers.DIAMOND,1, -2.8F, 0, 80000, 70, 120, EnergyType.RECEIVE, EnergyTier.STANDARD), "diamond_drill");
        IRIDIUM_DRILL = registerItem(new MiningDrill(ModTiers.IRIDIUM, 1, -2.8F, 0, 300000, 200, 400, EnergyType.RECEIVE, EnergyTier.ADVANCED), "iridium_drill");

        ELECTRIC_HOE = registerItem(new ElectricHoe(Tiers.IRON,-2, -1.0F, 0, 10000, 50, 100, 50, EnergyType.RECEIVE, EnergyTier.BASIC), "electric_hoe");
//        WIND_METER = registerItem(new WindMeter(0, 1000, EnumEnergyType.RECEIVE, EnergyTier.BASIC), "wind_meter");
        WIND_METER = registerItem(new WIPItem(), "wind_meter");
        IE_METER = registerItem(new IEMeter(), "ie_meter");
        DEBUG_STICK = registerItem(new DebugStick(), "debug_stick");
        MULTI_TOOL = registerItem(new MultiTool(Tiers.DIAMOND,-3, 0.0F, 0, 300000, 800, 1400, 500, EnergyType.RECEIVE, EnergyTier.ADVANCED), "multi_tool");

        FOAM_SPRAYER = registerItem(new FoamSprayer(), "foam_sprayer");
        PAINTER = registerItem(new ToolItem(1), "painter");
        PAINTER_WHITE = registerItem(new Painter(MaterialColor.WOOL), "painter_white");
        PAINTER_RED = registerItem(new Painter(MaterialColor.COLOR_RED), "painter_red");
        PAINTER_ORANGE = registerItem(new Painter(MaterialColor.COLOR_ORANGE), "painter_orange");
        PAINTER_PINK = registerItem(new Painter(MaterialColor.COLOR_PINK), "painter_pink");
        PAINTER_YELLOW = registerItem(new Painter(MaterialColor.COLOR_YELLOW), "painter_yellow");
        PAINTER_LIME = registerItem(new Painter(MaterialColor.COLOR_LIGHT_GREEN), "painter_lime");
        PAINTER_GREEN = registerItem(new Painter(MaterialColor.COLOR_GREEN), "painter_green");
        PAINTER_LIGHT_BLUE = registerItem(new Painter(MaterialColor.COLOR_LIGHT_BLUE), "painter_light_blue");
        PAINTER_CYAN = registerItem(new Painter(MaterialColor.COLOR_CYAN), "painter_cyan");
        PAINTER_BLUE = registerItem(new Painter(MaterialColor.COLOR_BLUE), "painter_blue");
        PAINTER_MAGENTA = registerItem(new Painter(MaterialColor.COLOR_MAGENTA), "painter_magenta");
        PAINTER_PURPLE = registerItem(new Painter(MaterialColor.COLOR_PURPLE), "painter_purple");
        PAINTER_BROWN = registerItem(new Painter(MaterialColor.COLOR_BROWN), "painter_brown");
        PAINTER_GRAY = registerItem(new Painter(MaterialColor.COLOR_GRAY), "painter_gray");
        PAINTER_LIGHT_GRAY = registerItem(new Painter(MaterialColor.COLOR_LIGHT_GRAY), "painter_light_gray");
        PAINTER_BLACK = registerItem(new Painter(MaterialColor.COLOR_BLACK), "painter_black");

        BIO_CHAFF = registerItem(new MaterialItem(), "bio_chaff");
        FERTILIZER = registerItem(new Fertilizer(), "fertilizer");
        HEAT_CONDUCTOR = registerItem(new MaterialItem(), "heat_conductor");
        FOAM_POWDER = registerItem(new MaterialItem(), "foam_powder");
        REINFORCED_FOAM_POWDER = registerItem(new MaterialItem(), "reinforced_foam_powder");

        OVERCLOCKER_UPGRADE = registerItem(new OverclockerUpgrade(), "overclocker_upgrade");
        TRANSFORMER_UPGRADE = registerItem(new TransformerUpgrade(), "transformer_upgrade");
        ENERGY_STORAGE_UPGRADE = registerItem(new EnergyStorageUpgrade(), "energy_storage_upgrade");
        REDSTONE_SIGNAL_INVERTER_UPGRADE = registerItem(new RedstoneSignalInverter(), "redstone_signal_inverter_upgrade");
        EJECTOR_UPGRADE = registerItem(new ItemDirectionalUpgrade(UpgradeType.EJECTOR), "ejector_upgrade");
        PULLING_UPGRADE = registerItem(new ItemDirectionalUpgrade(UpgradeType.PULLING), "pulling_upgrade");
        FLUID_EJECTOR_UPGRADE = registerItem(new ItemDirectionalUpgrade(UpgradeType.FLUID_EJECTOR), "fluid_ejector_upgrade");
        FLUID_PULLING_UPGRADE = registerItem(new ItemDirectionalUpgrade(UpgradeType.FLUID_PULLING), "fluid_pulling_upgrade");
        ADVANCED_EJECTOR_UPGRADE = registerItem(new WIPItem(), "advanced_ejector_upgrade");
        ADVANCED_PULLING_UPGRADE = registerItem(new WIPItem(), "advanced_pulling_upgrade");
        ADVANCED_FLUID_EJECTOR_UPGRADE = registerItem(new WIPItem(), "advanced_fluid_ejector_upgrade");
        ADVANCED_FLUID_PULLING_UPGRADE = registerItem(new WIPItem(), "advanced_fluid_pulling_upgrade");

        CHARGING_BATTERY = registerItem(new ChargingBattery(40000, EnergyType.BOTH, EnergyTier.BASIC), "charging_battery");
        ADVANCED_CHARGING_BATTERY = registerItem(new ChargingBattery(400000, EnergyType.BOTH, EnergyTier.STANDARD), "advanced_charging_battery");
        CHARGING_ENERGY_CRYSTAL = registerItem(new ChargingBattery(4000000, EnergyType.BOTH, EnergyTier.ADVANCED), "charging_energy_crystal");
        CHARGING_LAPOTRON_CRYSTAL = registerItem(new ChargingBattery(40000000, EnergyType.BOTH, EnergyTier.SUPER), "charging_lapotron_crystal");

        SMALL_COOLANT_CELL = registerItem(new WIPItem(), "small_coolant_cell");
        MEDIUM_COOLANT_CELL = registerItem(new WIPItem(), "medium_coolant_cell");
        LARGE_COOLANT_CELL = registerItem(new WIPItem(), "large_coolant_cell");

        HEAT_EXCHANGER = registerItem(new WIPItem(), "heat_exchanger");
        HEAT_VENT = registerItem(new WIPItem(), "heat_vent");
        OVERCLOCKED_HEAT_VENT = registerItem(new WIPItem(), "overclocked_heat_vent");
        ADVANCED_HEAT_EXCHANGER = registerItem(new WIPItem(), "advanced_heat_exchanger");
        ADVANCED_HEAT_VENT = registerItem(new WIPItem(), "advanced_heat_vent");
        COMPONENT_HEAT_EXCHANGER = registerItem(new WIPItem(), "component_heat_exchanger");
        COMPONENT_HEAT_VENT = registerItem(new WIPItem(), "component_heat_vent");
        CONTAINMENT_REACTOR_PLATING = registerItem(new WIPItem(), "containment_reactor_plating");
        HEAT_CAPACITY_REACTOR_PLATING = registerItem(new WIPItem(), "heat_capacity_reactor_plating");
        HEATING_CELL = registerItem(new WIPItem(), "heating_cell");
        REACTOR_HEAT_EXCHANGER = registerItem(new WIPItem(), "reactor_heat_exchanger");
        REACTOR_HEAT_VENT = registerItem(new WIPItem(), "reactor_heat_vent");
        REACTOR_PLATING = registerItem(new WIPItem(), "reactor_plating");
    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

}