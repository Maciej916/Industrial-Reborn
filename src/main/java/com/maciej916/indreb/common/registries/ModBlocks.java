package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.*;
import com.maciej916.indreb.common.block.impl.BlockIronFence;
import com.maciej916.indreb.common.block.impl.BlockIronScaffolding;
import com.maciej916.indreb.common.block.impl.battery_box.BlockBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.cf.*;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.BlockCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.BlockGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.BlockSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.BlockSolarGenerator;
import com.maciej916.indreb.common.block.impl.luminator.BlockLuminator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockExtruder;
import com.maciej916.indreb.common.block.impl.machines.fermenter.BlockFermenter;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.BlockIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockSawmill;
import com.maciej916.indreb.common.block.impl.rubber_wood.*;
import com.maciej916.indreb.common.fluids.ConstructionFoam;
import com.maciej916.indreb.common.generation.RubberTree;
import com.maciej916.indreb.common.item.block.BlockItemElectric;
import com.maciej916.indreb.common.item.block.ItemIronScaffolding;
import com.maciej916.indreb.common.tier.BatteryBoxTier;
import com.maciej916.indreb.common.tier.CableTier;
import com.maciej916.indreb.common.tier.SolarGeneratorTier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    public static BlockItem GENERATOR;
    public static BlockItem SOLAR_GENERATOR;
    public static BlockItem ADVANCED_SOLAR_GENERATOR;
    public static BlockItem HYBRID_SOLAR_GENERATOR;
    public static BlockItem QUANTUM_SOLAR_GENERATOR;
    public static BlockItem GEO_GENERATOR;
    public static BlockItem CRYSTALLINE_GENERATOR;
    public static BlockItem SEMIFLUID_GENERATOR;

    public static BlockItem BATTERY_BOX;
    public static BlockItem CESU;
    public static BlockItem MFE;
    public static BlockItem MFSU;

    public static BlockItem IRON_FURNACE;
    public static BlockItem ELECTRIC_FURNACE;

    public static BlockItem CRUSHER;
    public static BlockItem COMPRESSOR;
    public static BlockItem EXTRACTOR;
    public static BlockItem SAWMILL;
    public static BlockItem EXTRUDER;
    public static BlockItem CANNING_MACHINE;
    public static BlockItem FLUID_ENRICHER;
    public static BlockItem RECYCLER;
    public static BlockItem ALLOY_SMELTER;
    public static BlockItem FERMENTER;

    public static BlockItem COPPER_CABLE;
    public static BlockItem COPPER_CABLE_INSULATED;
    public static BlockItem TIN_CABLE;
    public static BlockItem TIN_CABLE_INSULATED;
    public static BlockItem GOLD_CABLE;
    public static BlockItem GOLD_CABLE_INSULATED;
    public static BlockItem HV_CABLE;
    public static BlockItem HV_CABLE_INSULATED;
    public static BlockItem GLASS_FIBRE_CABLE;

    public static BlockItem RUBBER_LOG;
    public static BlockItem RUBBER_WOOD;
    public static BlockItem RUBBER_LEAVES;
    public static BlockItem RUBBER_SAPLING;
    public static BlockItem RUBBER_PLANKS;
    public static BlockItem RUBBER_STAIRS;
    public static BlockItem RUBBER_SLAB;

    public static BlockItem TIN_ORE;
    public static BlockItem DEEPSLATE_TIN_ORE;
    public static BlockItem LEAD_ORE;
    public static BlockItem DEEPSLATE_LEAD_ORE;
    public static BlockItem DEEPSLATE_URANIUM_ORE;

    public static BlockItem TIN_BLOCK;
    public static BlockItem SILVER_BLOCK;
    public static BlockItem STEEL_BLOCK;
    public static BlockItem BRONZE_BLOCK;

    public static BlockItem BASIC_MACHINE_CASING;
    public static BlockItem ADVANCED_MACHINE_CASING;

    public static BlockItem RESIN_SHEET;
    public static BlockItem RUBBER_SHEET;

    public static BlockItem CONSTRUCTION_FOAM;
    public static BlockItem REINFORCED_CONSTRUCTION_FOAM;

    public static BlockItem CONSTRUCTION_FOAM_WALL_WHITE;
    public static BlockItem CONSTRUCTION_FOAM_WALL_RED;
    public static BlockItem CONSTRUCTION_FOAM_WALL_ORANGE;
    public static BlockItem CONSTRUCTION_FOAM_WALL_PINK;
    public static BlockItem CONSTRUCTION_FOAM_WALL_YELLOW;
    public static BlockItem CONSTRUCTION_FOAM_WALL_LIME;
    public static BlockItem CONSTRUCTION_FOAM_WALL_GREEN;
    public static BlockItem CONSTRUCTION_FOAM_WALL_LIGHT_BLUE;
    public static BlockItem CONSTRUCTION_FOAM_WALL_CYAN;
    public static BlockItem CONSTRUCTION_FOAM_WALL_BLUE;
    public static BlockItem CONSTRUCTION_FOAM_WALL_MAGENTA;
    public static BlockItem CONSTRUCTION_FOAM_WALL_PURPLE;
    public static BlockItem CONSTRUCTION_FOAM_WALL_BROWN;
    public static BlockItem CONSTRUCTION_FOAM_WALL_GRAY;
    public static BlockItem CONSTRUCTION_FOAM_WALL_LIGHT_GRAY;
    public static BlockItem CONSTRUCTION_FOAM_WALL_BLACK;

    public static BlockItem REINFORCED_GLASS;
    public static BlockItem REINFORCED_STONE;
    public static BlockItem REINFORCED_STONE_SLAB;
    public static BlockItem REINFORCED_STONE_STAIRS;

    public static BlockItem IRON_SCAFFOLDING;
    public static BlockItem IRON_FENCE;

    public static BlockItem LUMINATOR;


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GENERATOR = registerBlock(new BlockItemElectric(new BlockGenerator()), "generator");
        SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.BASIC), "solar_generator");
        ADVANCED_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.ADVANCED), "advanced_solar_generator");
        HYBRID_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.HYBRID), "hybrid_solar_generator");
        QUANTUM_SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(SolarGeneratorTier.QUANTUM), "quantum_solar_generator");
        GEO_GENERATOR = registerBlock(new BlockItemElectric(new BlockGeoGenerator()), "geo_generator");
        CRYSTALLINE_GENERATOR = registerBlock(new BlockItemElectric(new BlockCrystallineGenerator()), "crystalline_generator");
        SEMIFLUID_GENERATOR = registerBlock(new BlockItemElectric(new BlockSemifluidGenerator()), "semifluid_generator");

        BATTERY_BOX = registerBlock(new BlockItemElectric(new BlockBatteryBox(BatteryBoxTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD))), "battery_box");
        CESU = registerBlock(new BlockItemElectric(new BlockBatteryBox(BatteryBoxTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL))), "cesu");
        MFE = registerBlock(new BlockItemElectric(new BlockBatteryBox(BatteryBoxTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL))), "mfe");
        MFSU = registerBlock(new BlockItemElectric(new BlockBatteryBox(BatteryBoxTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 3f).sound(SoundType.METAL))), "mfsu");

        IRON_FURNACE = registerBlock(new BlockIronFurnace(), "iron_furnace");
        ELECTRIC_FURNACE = registerBlock(new BlockItemElectric(new BlockElectricFurnace()), "electric_furnace");

        CRUSHER = registerBlock(new BlockItemElectric(new BlockCrusher()), "crusher");
        COMPRESSOR = registerBlock(new BlockItemElectric(new BlockCompressor()), "compressor");
        EXTRACTOR = registerBlock(new BlockItemElectric(new BlockExtractor()), "extractor");
        SAWMILL = registerBlock(new BlockItemElectric(new BlockSawmill()), "sawmill");
        EXTRUDER = registerBlock(new BlockItemElectric(new BlockExtruder()), "extruder");
        CANNING_MACHINE = registerBlock(new BlockItemElectric(new BlockCanningMachine()), "canning_machine");
        FLUID_ENRICHER = registerBlock(new BlockItemElectric(new BlockFluidEnricher()), "fluid_enricher");
        RECYCLER = registerBlock(new BlockItemElectric(new BlockRecycler()), "recycler");
        ALLOY_SMELTER = registerBlock(new BlockItemElectric(new BlockAlloySmelter()), "alloy_smelter");
        FERMENTER = registerBlock(new BlockItemElectric(new BlockFermenter()), "fermenter");

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
        LEAD_ORE = registerBlock(new BlockOre(), "lead_ore");
        DEEPSLATE_LEAD_ORE = registerBlock(new BlockOre(), "deepslate_lead_ore");
        DEEPSLATE_URANIUM_ORE = registerBlock(new BlockOre(), "deepslate_uranium_ore");

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

        IRON_SCAFFOLDING = registerBlock(new ItemIronScaffolding(new BlockIronScaffolding()), "iron_scaffolding");
        IRON_FENCE = registerBlock(new BlockIronFence(), "iron_fence");

        LUMINATOR = registerBlock(new BlockLuminator(), "luminator");

    }



    public static BlockItem registerBlock(BlockItem blockItem, String name) {
        return registerBlock(blockItem.getBlock(), blockItem, name);
    }

    public static BlockItem registerBlock(Block block, String name) {
        return registerBlock(block, new BlockItem(block, new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP)), name);
    }

    public static BlockItem registerBlock(Block block, BlockItem blockItem, String name) {
        block.setRegistryName(name);
        blockItem.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(blockItem);
        return blockItem;
    }
}
