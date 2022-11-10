package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderScanner;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class Scanner extends RecipeProvider {

    public Scanner(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderScanner.builder(Items.STONE).setExperience(5F).setMatterCost(15).addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE)).save(consumer,"stone");
        RecipeBuilderScanner.builder(Items.GRANITE).setExperience(5F).setMatterCost(15).addCriterion("granite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRANITE)).save(consumer,"granite");
        RecipeBuilderScanner.builder(Items.POLISHED_GRANITE).setExperience(5F).setMatterCost(18).addCriterion("polished_granite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_GRANITE)).save(consumer,"polished_granite");
        RecipeBuilderScanner.builder(Items.DIORITE).setExperience(5F).setMatterCost(15).addCriterion("diorite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIORITE)).save(consumer,"diorite");
        RecipeBuilderScanner.builder(Items.POLISHED_DIORITE).setExperience(5F).setMatterCost(18).addCriterion("polished_diorite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_DIORITE)).save(consumer,"polished_diorite");
        RecipeBuilderScanner.builder(Items.ANDESITE).setExperience(5F).setMatterCost(15).addCriterion("andesite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANDESITE)).save(consumer,"andesite");
        RecipeBuilderScanner.builder(Items.POLISHED_ANDESITE).setExperience(5F).setMatterCost(18).addCriterion("polished_andesite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_ANDESITE)).save(consumer,"polished_andesite");
        RecipeBuilderScanner.builder(Items.DEEPSLATE).setExperience(5F).setMatterCost(18).addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE)).save(consumer,"deepslate");
        RecipeBuilderScanner.builder(Items.COBBLED_DEEPSLATE).setExperience(5F).setMatterCost(16).addCriterion("cobbled_deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLED_DEEPSLATE)).save(consumer,"cobbled_deepslate");
        RecipeBuilderScanner.builder(Items.POLISHED_DEEPSLATE).setExperience(5F).setMatterCost(20).addCriterion("polished_deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_DEEPSLATE)).save(consumer,"polished_deepslate");
        RecipeBuilderScanner.builder(Items.CALCITE).setExperience(5F).setMatterCost(15).addCriterion("calcite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CALCITE)).save(consumer,"calcite");
        RecipeBuilderScanner.builder(Items.TUFF).setExperience(5F).setMatterCost(15).addCriterion("tuff", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TUFF)).save(consumer,"tuff");
        RecipeBuilderScanner.builder(Items.DRIPSTONE_BLOCK).setExperience(5F).setMatterCost(16).addCriterion("dripstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIPSTONE_BLOCK)).save(consumer,"dripstone_block");
        RecipeBuilderScanner.builder(Items.GRASS_BLOCK).setExperience(5F).setMatterCost(28).addCriterion("grass_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRASS_BLOCK)).save(consumer,"grass_block");
        RecipeBuilderScanner.builder(Items.DIRT).setExperience(5F).setMatterCost(14).addCriterion("dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT)).save(consumer,"dirt");
        RecipeBuilderScanner.builder(Items.COARSE_DIRT).setExperience(5F).setMatterCost(20).addCriterion("coarse_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COARSE_DIRT)).save(consumer,"coarse_dirt");
        RecipeBuilderScanner.builder(Items.PODZOL).setExperience(5F).setMatterCost(20).addCriterion("podzol", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PODZOL)).save(consumer,"podzol");
        RecipeBuilderScanner.builder(Items.ROOTED_DIRT).setExperience(5F).setMatterCost(20).addCriterion("rooted_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROOTED_DIRT)).save(consumer,"rooted_dirt");
        RecipeBuilderScanner.builder(Items.MUD).setExperience(5F).setMatterCost(22).addCriterion("mud", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD)).save(consumer,"mud");
        RecipeBuilderScanner.builder(Items.CRIMSON_NYLIUM).setExperience(5F).setMatterCost(20).addCriterion("crimson_nylium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_NYLIUM)).save(consumer,"crimson_nylium");
        RecipeBuilderScanner.builder(Items.WARPED_NYLIUM).setExperience(5F).setMatterCost(20).addCriterion("warped_nylium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_NYLIUM)).save(consumer,"warped_nylium");
        RecipeBuilderScanner.builder(Items.COBBLESTONE).setExperience(5F).setMatterCost(1).addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE)).save(consumer,"cobblestone");
//        RecipeBuilderScanner.builder(Items.OAK_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.SPRUCE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.BIRCH_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.JUNGLE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.ACACIA_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.DARK_OAK_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.MANGROVE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.CRIMSON_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.WARPED_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_PLANKS)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.SPRUCE_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_SAPLING)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.BIRCH_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_SAPLING)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.JUNGLE_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_SAPLING)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.ACACIA_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_SAPLING)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.DARK_OAK_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_SAPLING)).save(consumer,"asd");
//        RecipeBuilderScanner.builder(Items.MANGROVE_PROPAGULE).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_PROPAGULE)).save(consumer,"asd");
        RecipeBuilderScanner.builder(Items.BEDROCK).setExperience(5F).setMatterCost(10000).addCriterion("bedrock", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEDROCK)).save(consumer,"bedrock");
        RecipeBuilderScanner.builder(Items.SAND).setExperience(5F).setMatterCost(15).addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND)).save(consumer,"sand");
        RecipeBuilderScanner.builder(Items.RED_SAND).setExperience(5F).setMatterCost(16).addCriterion("red_sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SAND)).save(consumer,"red_sand");
        RecipeBuilderScanner.builder(Items.GRAVEL).setExperience(5F).setMatterCost(52).addCriterion("gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL)).save(consumer,"gravel");
        RecipeBuilderScanner.builder(Items.COAL_ORE).setExperience(5F).setMatterCost(33).addCriterion("coal_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL_ORE)).save(consumer,"coal_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_COAL_ORE).setExperience(33).setMatterCost(33).addCriterion("deepslate_coal_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COAL_ORE)).save(consumer,"deepslate_coal_ore");
        RecipeBuilderScanner.builder(Items.IRON_ORE).setExperience(5F).setMatterCost(72).addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE)).save(consumer,"iron_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_IRON_ORE).setExperience(5F).setMatterCost(72).addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE)).save(consumer,"deepslate_iron_ore");
        RecipeBuilderScanner.builder(Items.COPPER_ORE).setExperience(5F).setMatterCost(131).addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE)).save(consumer,"copper_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_COPPER_ORE).setExperience(5F).setMatterCost(131).addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE)).save(consumer,"deepslate_copper_ore");
        RecipeBuilderScanner.builder(Items.GOLD_ORE).setExperience(5F).setMatterCost(140).addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE)).save(consumer,"gold_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_GOLD_ORE).setExperience(5F).setMatterCost(140).addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE)).save(consumer,"deepslate_gold_ore");
        RecipeBuilderScanner.builder(Items.REDSTONE_ORE).setExperience(5F).setMatterCost(51).addCriterion("redstone_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE_ORE)).save(consumer,"redstone_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_REDSTONE_ORE).setExperience(5F).setMatterCost(51).addCriterion("deepslate_redstone_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_REDSTONE_ORE)).save(consumer,"deepslate_redstone_ore");
        RecipeBuilderScanner.builder(Items.EMERALD_ORE).setExperience(5F).setMatterCost(525).addCriterion("emerald_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD_ORE)).save(consumer,"emerald_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_EMERALD_ORE).setExperience(5F).setMatterCost(525).addCriterion("deepslate_emerald_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_EMERALD_ORE)).save(consumer,"deepslate_emerald_ore");
        RecipeBuilderScanner.builder(Items.LAPIS_ORE).setExperience(5F).setMatterCost(68).addCriterion("lapis_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_ORE)).save(consumer, "lapis_ore");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_LAPIS_ORE).setExperience(5F).setMatterCost(68).addCriterion("deepslate_lapis_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_LAPIS_ORE)).save(consumer,"deepslate_lapis_ore");
        RecipeBuilderScanner.builder(Items.DIAMOND_ORE).setExperience(5F).setMatterCost(389).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_ORE)).save(consumer,"asd");
        RecipeBuilderScanner.builder(Items.DEEPSLATE_DIAMOND_ORE).setExperience(5F).setMatterCost(389).addCriterion("deepslate_diamond_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_DIAMOND_ORE)).save(consumer,"deepslate_diamond_ore");
        RecipeBuilderScanner.builder(Items.NETHER_GOLD_ORE).setExperience(5F).setMatterCost(167).addCriterion("nether_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_GOLD_ORE)).save(consumer,"nether_gold_ore");
        RecipeBuilderScanner.builder(Items.NETHER_QUARTZ_ORE).setExperience(5F).setMatterCost(59).addCriterion("nether_quartz_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_QUARTZ_ORE)).save(consumer,"nether_quartz_ore");
        RecipeBuilderScanner.builder(Items.ANCIENT_DEBRIS).setExperience(5F).setMatterCost(1863).addCriterion("ancient_debris", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANCIENT_DEBRIS)).save(consumer,"ancient_debris");




        RecipeBuilderScanner.builder(ModItems.SCRAP.get()).setExperience(5F).setMatterCost(17).addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get())).save(consumer,"scrap");
        RecipeBuilderScanner.builder(ModItems.COAL_DUST.get()).setExperience(5F).setMatterCost(24).addCriterion("coal_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get())).save(consumer,"coal_dust");
        RecipeBuilderScanner.builder(ModItems.FLUID_CELL.get()).setExperience(5F).setMatterCost(41).addCriterion("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get())).save(consumer,"fluid_cell");




    }
}
