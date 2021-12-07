package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.BlockOre;
import com.maciej916.indreb.common.block.BlockResource;
import com.maciej916.indreb.common.block.impl.BlockResinSheet;
import com.maciej916.indreb.common.block.impl.battery_box.BlockBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.generators.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.BlockGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_generator.BlockSolarGenerator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockExtruder;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.BlockIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockSawmill;
import com.maciej916.indreb.common.block.impl.rubber_wood.*;
import com.maciej916.indreb.common.generation.RubberTree;
import com.maciej916.indreb.common.tier.BatteryBoxTier;
import com.maciej916.indreb.common.tier.CableTier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    public static Block GENERATOR;
    public static Block SOLAR_GENERATOR;
    public static Block GEO_GENERATOR;

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

    public static Block WOODEN_ENERGY_CONVERTER;


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GENERATOR = registerBlock(new BlockGenerator(), "generator");
        SOLAR_GENERATOR = registerBlock(new BlockSolarGenerator(), "solar_generator");
        GEO_GENERATOR = registerBlock(new BlockGeoGenerator(), "geo_generator");

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

        RUBBER_LOG = registerBlock(new BlockRubberLog(), "rubber_log");
        RUBBER_WOOD = registerBlock(new BlockWood(), "rubber_wood");
        RUBBER_LEAVES = registerBlock(new BlockLeaves(), "rubber_leaves");
        RUBBER_PLANKS = registerBlock(new BlockPlanks(), "rubber_planks");
        RUBBER_SAPLING = registerBlock(new BlockSapling(new RubberTree()), "rubber_sapling");
        RUBBER_STAIRS = registerBlock(new BlockStairs(), "rubber_stairs");
        RUBBER_SLAB = registerBlock(new BlockSlab(), "rubber_slab");

        TIN_ORE = registerBlock(new BlockOre(), "tin_ore");
        DEEPSLATE_TIN_ORE = registerBlock(new BlockOre(), "deepslate_tin_ore");

        TIN_BLOCK = registerBlock(new BlockResource(1f, 3f), "tin_block");
        SILVER_BLOCK = registerBlock(new BlockResource(1f, 3f), "silver_block");
        STEEL_BLOCK = registerBlock(new BlockResource(1f, 3f), "steel_block");
        BRONZE_BLOCK = registerBlock(new BlockResource(1f, 3f), "bronze_block");

        BASIC_MACHINE_CASING = registerBlock(new BlockResource(1f, 3f), "basic_machine_casing");
        ADVANCED_MACHINE_CASING = registerBlock(new BlockResource(1f, 3f), "advanced_machine_casing");
        RESIN_SHEET = registerBlock(new BlockResinSheet(), "resin_sheet");

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
