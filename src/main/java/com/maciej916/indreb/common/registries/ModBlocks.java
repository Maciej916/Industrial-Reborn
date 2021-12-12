package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.*;
import com.maciej916.indreb.common.block.impl.battery_box.BlockBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.cf.*;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.BlockCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.BlockGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.BlockSolarGenerator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockExtruder;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.BlockIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockSawmill;
import com.maciej916.indreb.common.block.impl.rubber_wood.*;
import com.maciej916.indreb.common.generation.RubberTree;
import com.maciej916.indreb.common.tier.BatteryBoxTier;
import com.maciej916.indreb.common.tier.CableTier;
import com.maciej916.indreb.common.tier.SolarGeneratorTier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    public static Block GENERATOR;
    public static Block SOLAR_GENERATOR;
    public static Block ADVANCED_SOLAR_GENERATOR;
    public static Block HYBRID_SOLAR_GENERATOR;
    public static Block QUANTUM_SOLAR_GENERATOR;
    public static Block GEO_GENERATOR;
    public static Block CRYSTALLINE_GENERATOR;

    public static Block BATTERY_BOX;
    public static Block CESU;
    public static Block MFE;
    public static Block MFSU;

    public static Block IRON_FURNACE;
    public static Block ELECTRIC_FURNACE;

    public static Block CRUSHER;
    public static Block COMPRESSOR;
    public static Block EXTRACTOR;
    public static Block SAWMILL;
    public static Block EXTRUDER;
    public static Block CANNING_MACHINE;
    public static Block FLUID_ENRICHER;

    public static Block ALLOY_SMELTER;

    public static Block COPPER_CABLE;
    public static Block COPPER_CABLE_INSULATED;
    public static Block TIN_CABLE;
    public static Block TIN_CABLE_INSULATED;
    public static Block GOLD_CABLE;
    public static Block GOLD_CABLE_INSULATED;
    public static Block HV_CABLE;
    public static Block HV_CABLE_INSULATED;
    public static Block GLASS_FIBRE_CABLE;

    public static Block RUBBER_LOG;
    public static Block RUBBER_WOOD;
    public static Block RUBBER_LEAVES;
    public static Block RUBBER_SAPLING;
    public static Block RUBBER_PLANKS;
    public static Block RUBBER_STAIRS;
    public static Block RUBBER_SLAB;

    public static Block TIN_ORE;
    public static Block DEEPSLATE_TIN_ORE;

    public static Block TIN_BLOCK;
    public static Block SILVER_BLOCK;
    public static Block STEEL_BLOCK;
    public static Block BRONZE_BLOCK;

    public static Block BASIC_MACHINE_CASING;
    public static Block ADVANCED_MACHINE_CASING;

    public static Block RESIN_SHEET;
    public static Block RUBBER_SHEET;

    public static Block CONSTRUCTION_FOAM;
    public static Block REINFORCED_CONSTRUCTION_FOAM;

    public static Block CONSTRUCTION_FOAM_WALL_WHITE;
    public static Block CONSTRUCTION_FOAM_WALL_RED;
    public static Block CONSTRUCTION_FOAM_WALL_ORANGE;
    public static Block CONSTRUCTION_FOAM_WALL_PINK;
    public static Block CONSTRUCTION_FOAM_WALL_YELLOW;
    public static Block CONSTRUCTION_FOAM_WALL_LIME;
    public static Block CONSTRUCTION_FOAM_WALL_GREEN;
    public static Block CONSTRUCTION_FOAM_WALL_LIGHT_BLUE;
    public static Block CONSTRUCTION_FOAM_WALL_CYAN;
    public static Block CONSTRUCTION_FOAM_WALL_BLUE;
    public static Block CONSTRUCTION_FOAM_WALL_MAGENTA;
    public static Block CONSTRUCTION_FOAM_WALL_PURPLE;
    public static Block CONSTRUCTION_FOAM_WALL_BROWN;
    public static Block CONSTRUCTION_FOAM_WALL_GRAY;
    public static Block CONSTRUCTION_FOAM_WALL_LIGHT_GRAY;
    public static Block CONSTRUCTION_FOAM_WALL_BLACK;

    public static Block REINFORCED_GLASS;
    public static Block REINFORCED_STONE;
    public static Block REINFORCED_STONE_SLAB;
    public static Block REINFORCED_STONE_STAIRS;

    public static Block IRON_SCAFFOLDING;
    public static Block IRON_FENCE;

    public static Block LUMINATOR;


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GENERATOR = registerBlock(new BlockGenerator(), "generator");
        SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.BASIC), "solar_generator");
        ADVANCED_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.ADVANCED), "advanced_solar_generator");
        HYBRID_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.HYBRID), "hybrid_solar_generator");
        QUANTUM_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.QUANTUM), "quantum_solar_generator");
        GEO_GENERATOR = registerBlock(new BlockGeoGenerator(), "geo_generator");
        CRYSTALLINE_GENERATOR = registerBlock(new BlockCrystallineGenerator(), "crystalline_generator");

        BATTERY_BOX = registerBlock(new BlockBatteryBox(BatteryBoxTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD)), "battery_box");
        CESU = registerBlock(new BlockBatteryBox(BatteryBoxTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL)), "cesu");
        MFE = registerBlock(new BlockBatteryBox(BatteryBoxTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL)), "mfe");
        MFSU = registerBlock(new BlockBatteryBox(BatteryBoxTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL)), "mfsu");

        IRON_FURNACE = registerBlock(new BlockIronFurnace(), "iron_furnace");
        ELECTRIC_FURNACE = registerBlock(new BlockElectricFurnace(), "electric_furnace");

        CRUSHER = registerBlock(new BlockCrusher(), "crusher");
        COMPRESSOR = registerBlock(new BlockCompressor(), "compressor");
        EXTRACTOR = registerBlock(new BlockExtractor(), "extractor");
        SAWMILL = registerBlock(new BlockSawmill(), "sawmill");
        EXTRUDER = registerBlock(new BlockExtruder(), "extruder");
        CANNING_MACHINE = registerBlock(new BlockCanningMachine(), "canning_machine");
        FLUID_ENRICHER = registerBlock(new BlockFluidEnricher(), "fluid_enricher");

        ALLOY_SMELTER = registerBlock(new BlockAlloySmelter(), "alloy_smelter");

        TIN_CABLE = registerBlock(new BlockCable(0.127F, CableTier.TIN_CABLE), "tin_cable");
        TIN_CABLE_INSULATED = registerBlock(new BlockCable(0.189F, CableTier.TIN_CABLE_INSULATED), "tin_cable_insulated");
        COPPER_CABLE = registerBlock(new BlockCable(0.127F, CableTier.COPPER_CABLE), "copper_cable");
        COPPER_CABLE_INSULATED = registerBlock(new BlockCable(0.251F, CableTier.COPPER_CABLE_INSULATED), "copper_cable_insulated");
        GOLD_CABLE = registerBlock(new BlockCable(0.127F, CableTier.GOLD_CABLE), "gold_cable");
        GOLD_CABLE_INSULATED = registerBlock(new BlockCable(0.189F, CableTier.GOLD_CABLE_INSULATED), "gold_cable_insulated");
        HV_CABLE = registerBlock(new BlockCable(0.189F, CableTier.HV_CABLE), "hv_cable");
        HV_CABLE_INSULATED = registerBlock(new BlockCable(0.313F, CableTier.HV_CABLE_INSULATED), "hv_cable_insulated");
        GLASS_FIBRE_CABLE = registerBlock(new BlockCable(0.127F, CableTier.GLASS_FIBRE_CABLE), "glass_fibre_cable");

        RUBBER_LOG = registerBlock(new RubberLog(), "rubber_log");
        RUBBER_WOOD = registerBlock(new RubberWood(), "rubber_wood");
        RUBBER_LEAVES = registerBlock(new RubberLeaves(), "rubber_leaves");
        RUBBER_PLANKS = registerBlock(new RybberPlanks(), "rubber_planks");
        RUBBER_SAPLING = registerBlock(new RubberSapling(new RubberTree()), "rubber_sapling");
        RUBBER_STAIRS = registerBlock(new RubberStairs(), "rubber_stairs");
        RUBBER_SLAB = registerBlock(new RubberSlab(), "rubber_slab");

        TIN_ORE = registerBlock(new BlockOre(), "tin_ore");
        DEEPSLATE_TIN_ORE = registerBlock(new BlockOre(), "deepslate_tin_ore");

        TIN_BLOCK = registerBlock(new BlockResource(1f, 3f), "tin_block");
        SILVER_BLOCK = registerBlock(new BlockResource(1f, 3f), "silver_block");
        STEEL_BLOCK = registerBlock(new BlockResource(1f, 3f), "steel_block");
        BRONZE_BLOCK = registerBlock(new BlockResource(1f, 3f), "bronze_block");

        BASIC_MACHINE_CASING = registerBlock(new BlockResource(1f, 3f), "basic_machine_casing");
        ADVANCED_MACHINE_CASING = registerBlock(new BlockResource(1f, 3f), "advanced_machine_casing");
        RESIN_SHEET = registerBlock(new BlockSheet(0.7F), "resin_sheet");
        RUBBER_SHEET = registerBlock(new BlockSheet(1.1F), "rubber_sheet");

        CONSTRUCTION_FOAM = registerBlock(new BlockCF(), "construction_foam");
        REINFORCED_CONSTRUCTION_FOAM = registerBlock(new BlockReinforcedCF(), "reinforced_construction_foam");

        CONSTRUCTION_FOAM_WALL_WHITE = registerBlock(new BlockCFWall(MaterialColor.WOOL), "construction_foam_wall_white");
        CONSTRUCTION_FOAM_WALL_RED = registerBlock(new BlockCFWall(MaterialColor.COLOR_RED), "construction_foam_wall_red");
        CONSTRUCTION_FOAM_WALL_ORANGE = registerBlock(new BlockCFWall(MaterialColor.COLOR_ORANGE), "construction_foam_wall_orange");
        CONSTRUCTION_FOAM_WALL_PINK = registerBlock(new BlockCFWall(MaterialColor.COLOR_PINK), "construction_foam_wall_pink");
        CONSTRUCTION_FOAM_WALL_YELLOW = registerBlock(new BlockCFWall(MaterialColor.COLOR_YELLOW), "construction_foam_wall_yellow");
        CONSTRUCTION_FOAM_WALL_LIME = registerBlock(new BlockCFWall(MaterialColor.COLOR_LIGHT_GREEN), "construction_foam_wall_lime");
        CONSTRUCTION_FOAM_WALL_GREEN = registerBlock(new BlockCFWall(MaterialColor.COLOR_GREEN), "construction_foam_wall_green");
        CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = registerBlock(new BlockCFWall(MaterialColor.COLOR_LIGHT_BLUE), "construction_foam_wall_light_blue");
        CONSTRUCTION_FOAM_WALL_CYAN = registerBlock(new BlockCFWall(MaterialColor.COLOR_CYAN), "construction_foam_wall_cyan");
        CONSTRUCTION_FOAM_WALL_BLUE = registerBlock(new BlockCFWall(MaterialColor.COLOR_BLUE), "construction_foam_wall_blue");
        CONSTRUCTION_FOAM_WALL_MAGENTA = registerBlock(new BlockCFWall(MaterialColor.COLOR_MAGENTA), "construction_foam_wall_magenta");
        CONSTRUCTION_FOAM_WALL_PURPLE = registerBlock(new BlockCFWall(MaterialColor.COLOR_PURPLE), "construction_foam_wall_purple");
        CONSTRUCTION_FOAM_WALL_BROWN = registerBlock(new BlockCFWall(MaterialColor.COLOR_BROWN), "construction_foam_wall_brown");
        CONSTRUCTION_FOAM_WALL_GRAY = registerBlock(new BlockCFWall(MaterialColor.COLOR_GRAY), "construction_foam_wall_gray");
        CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = registerBlock(new BlockCFWall(MaterialColor.COLOR_LIGHT_GRAY), "construction_foam_wall_light_gray");
        CONSTRUCTION_FOAM_WALL_BLACK = registerBlock(new BlockCFWall(MaterialColor.COLOR_BLACK), "construction_foam_wall_black");

        REINFORCED_GLASS = registerBlock(new BlockReinforcedGlass(), "reinforced_glass");
        REINFORCED_STONE = registerBlock(new BlockReinforcedStone(), "reinforced_stone");
        REINFORCED_STONE_SLAB = registerBlock(new BlockReinforcedStoneSlab(), "reinforced_stone_slab");
        REINFORCED_STONE_STAIRS = registerBlock(new BlockReinforcedStoneStairs(), "reinforced_stone_stairs");

        IRON_SCAFFOLDING = registerBlock(new BlockWIP(), "iron_scaffolding");
        IRON_FENCE = registerBlock(new BlockWIP(), "iron_fence");

        LUMINATOR = registerBlock(new BlockWIP(), "luminator");



    }


    public static Block registerBlock(Block block, String name) {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

}
