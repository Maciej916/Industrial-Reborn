package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.ScanningRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ScanningProvider extends RecipeProvider {

    public ScanningProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ScanningRecipeBuilder.builder(Items.STONE).setExperience(5F).setMatterCost(15).addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE)).save(consumer,"stone");
        ScanningRecipeBuilder.builder(Items.GRANITE).setExperience(5F).setMatterCost(15).addCriterion("granite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRANITE)).save(consumer,"granite");
        ScanningRecipeBuilder.builder(Items.POLISHED_GRANITE).setExperience(5F).setMatterCost(18).addCriterion("polished_granite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_GRANITE)).save(consumer,"polished_granite");
        ScanningRecipeBuilder.builder(Items.DIORITE).setExperience(5F).setMatterCost(15).addCriterion("diorite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIORITE)).save(consumer,"diorite");
        ScanningRecipeBuilder.builder(Items.ANDESITE).setExperience(5F).setMatterCost(15).addCriterion("andesite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANDESITE)).save(consumer,"andesite");
        ScanningRecipeBuilder.builder(Items.POLISHED_DIORITE).setExperience(5F).setMatterCost(18).addCriterion("polished_diorite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_DIORITE)).save(consumer,"polished_diorite");
        ScanningRecipeBuilder.builder(Items.POLISHED_ANDESITE).setExperience(5F).setMatterCost(18).addCriterion("polished_andesite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_ANDESITE)).save(consumer,"polished_andesite");
        ScanningRecipeBuilder.builder(Items.DEEPSLATE).setExperience(5F).setMatterCost(18).addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE)).save(consumer,"deepslate");
        ScanningRecipeBuilder.builder(Items.COBBLED_DEEPSLATE).setExperience(5F).setMatterCost(16).addCriterion("cobbled_deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLED_DEEPSLATE)).save(consumer,"cobbled_deepslate");
        ScanningRecipeBuilder.builder(Items.POLISHED_DEEPSLATE).setExperience(5F).setMatterCost(20).addCriterion("polished_deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POLISHED_DEEPSLATE)).save(consumer,"polished_deepslate");
        ScanningRecipeBuilder.builder(Items.CALCITE).setExperience(5F).setMatterCost(15).addCriterion("calcite", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CALCITE)).save(consumer,"calcite");
        ScanningRecipeBuilder.builder(Items.TUFF).setExperience(5F).setMatterCost(15).addCriterion("tuff", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TUFF)).save(consumer,"tuff");
        ScanningRecipeBuilder.builder(Items.DRIPSTONE_BLOCK).setExperience(5F).setMatterCost(16).addCriterion("dripstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIPSTONE_BLOCK)).save(consumer,"dripstone_block");
        ScanningRecipeBuilder.builder(Items.GRASS_BLOCK).setExperience(5F).setMatterCost(28).addCriterion("grass_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRASS_BLOCK)).save(consumer,"grass_block");
        ScanningRecipeBuilder.builder(Items.DIRT).setExperience(5F).setMatterCost(14).addCriterion("dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT)).save(consumer,"dirt");
        ScanningRecipeBuilder.builder(Items.COARSE_DIRT).setExperience(5F).setMatterCost(20).addCriterion("coarse_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COARSE_DIRT)).save(consumer,"coarse_dirt");
        ScanningRecipeBuilder.builder(Items.PODZOL).setExperience(5F).setMatterCost(20).addCriterion("podzol", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PODZOL)).save(consumer,"podzol");
        ScanningRecipeBuilder.builder(Items.ROOTED_DIRT).setExperience(5F).setMatterCost(20).addCriterion("rooted_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROOTED_DIRT)).save(consumer,"rooted_dirt");
        ScanningRecipeBuilder.builder(Items.MUD).setExperience(5F).setMatterCost(22).addCriterion("mud", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD)).save(consumer,"mud");
        ScanningRecipeBuilder.builder(Items.CRIMSON_NYLIUM).setExperience(5F).setMatterCost(20).addCriterion("crimson_nylium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_NYLIUM)).save(consumer,"crimson_nylium");
        ScanningRecipeBuilder.builder(Items.WARPED_NYLIUM).setExperience(5F).setMatterCost(20).addCriterion("warped_nylium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_NYLIUM)).save(consumer,"warped_nylium");
        ScanningRecipeBuilder.builder(Items.COBBLESTONE).setExperience(5F).setMatterCost(1).addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE)).save(consumer,"cobblestone");
//        ScanningRecipeBuilder.builder(Items.OAK_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.SPRUCE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.BIRCH_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.JUNGLE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.ACACIA_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.DARK_OAK_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.MANGROVE_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.CRIMSON_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.WARPED_PLANKS).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_PLANKS)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.SPRUCE_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_SAPLING)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.BIRCH_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_SAPLING)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.JUNGLE_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_SAPLING)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.ACACIA_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_SAPLING)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.DARK_OAK_SAPLING).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_SAPLING)).save(consumer,"asd");
//        ScanningRecipeBuilder.builder(Items.MANGROVE_PROPAGULE).setExperience(5F).setMatterCost(asd).addCriterion("asd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_PROPAGULE)).save(consumer,"asd");
        ScanningRecipeBuilder.builder(Items.BEDROCK).setExperience(5F).setMatterCost(10000).addCriterion("bedrock", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEDROCK)).save(consumer,"bedrock");
        ScanningRecipeBuilder.builder(Items.SAND).setExperience(5F).setMatterCost(15).addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND)).save(consumer,"sand");
        ScanningRecipeBuilder.builder(Items.RED_SAND).setExperience(5F).setMatterCost(16).addCriterion("red_sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SAND)).save(consumer,"red_sand");
        ScanningRecipeBuilder.builder(Items.GRAVEL).setExperience(5F).setMatterCost(52).addCriterion("gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL)).save(consumer,"gravel");

        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_COAL).setExperience(5F).setMatterCost(33).addCriterion("coal_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE)).save(consumer,"coal_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_IRON).setExperience(5F).setMatterCost(72).addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE)).save(consumer,"iron_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_COPPER).setExperience(5F).setMatterCost(131).addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE)).save(consumer,"copper_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_GOLD).setExperience(5F).setMatterCost(140).addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE)).save(consumer,"gold_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_REDSTONE).setExperience(5F).setMatterCost(51).addCriterion("redstone_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE)).save(consumer,"redstone_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_EMERALD).setExperience(5F).setMatterCost(525).addCriterion("emerald_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE)).save(consumer,"emerald_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_LAPIS).setExperience(5F).setMatterCost(68).addCriterion("lapis_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE)).save(consumer, "lapis_ore");
        ScanningRecipeBuilder.builder(ModItemTags.FORGE_ORE_DIAMOND).setExperience(5F).setMatterCost(389).addCriterion("diamond_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE)).save(consumer,"asd");

        ScanningRecipeBuilder.builder(Items.NETHER_GOLD_ORE).setExperience(5F).setMatterCost(167).addCriterion("nether_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_GOLD_ORE)).save(consumer,"nether_gold_ore");
        ScanningRecipeBuilder.builder(Items.NETHER_QUARTZ_ORE).setExperience(5F).setMatterCost(59).addCriterion("nether_quartz_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_QUARTZ_ORE)).save(consumer,"nether_quartz_ore");
        ScanningRecipeBuilder.builder(Items.ANCIENT_DEBRIS).setExperience(5F).setMatterCost(1863).addCriterion("ancient_debris", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANCIENT_DEBRIS)).save(consumer,"ancient_debris");




        ScanningRecipeBuilder.builder(ModItems.SCRAP).setExperience(5F).setMatterCost(17).addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get())).save(consumer,"scrap");
        ScanningRecipeBuilder.builder(ModItems.COAL_DUST).setExperience(5F).setMatterCost(24).addCriterion("coal_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get())).save(consumer,"coal_dust");
        ScanningRecipeBuilder.builder(ModItems.FLUID_CELL).setExperience(5F).setMatterCost(41).addCriterion("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get())).save(consumer,"fluid_cell");
        ScanningRecipeBuilder.builder(ModItems.IRIDIUM_SHARD).setExperience(5F).setMatterCost(2000).addCriterion("IRIDIUM_SHARD", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_SHARD.get())).save(consumer,"iridium_shard");



    }
}
