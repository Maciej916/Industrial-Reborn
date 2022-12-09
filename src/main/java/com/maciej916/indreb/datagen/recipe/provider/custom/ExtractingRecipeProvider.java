package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.ExtractingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ExtractingRecipeProvider extends RecipeProvider {

    public ExtractingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ExtractingRecipeBuilder.builder(ModItems.RUBBER, 3)
                .setIngredient(ModItems.STICKY_RESIN, 1)
                .setExperience(0.2F)
                .addCriterion("sticky_resin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN.get()))
                .setGroup("extracting")
                .save(consumer,"sticky_resin_rubber");

        ExtractingRecipeBuilder.builder(ModItems.RUBBER, 1)
                .setIngredient(ModItems.RUBBER_SAPLING, 1)
                .addCriterion("rubber_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_SAPLING.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_sapling_rubber");

        ExtractingRecipeBuilder.builder(ModItems.RUBBER, 1)
                .setIngredient(ModItems.RUBBER_LOG, 1)
                .addCriterion("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_wood_rubber");

        ExtractingRecipeBuilder.builder(Items.WHITE_WOOL, 1)
                .setIngredient(ItemTags.WOOL, 1)
                .addCriterion("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .setGroup("extracting")
                .save(consumer,"any_wool_white_wool");

        ExtractingRecipeBuilder.builder(Items.CLAY_BALL, 4)
                .setIngredient(Items.CLAY, 1)
                .addCriterion("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .setGroup("extracting")
                .save(consumer,"clay_clay_ball");

        ExtractingRecipeBuilder.builder(Items.SNOWBALL, 4)
                .setIngredient(Items.SNOW_BLOCK, 1)
                .addCriterion("SNow_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOW_BLOCK))
                .setGroup("extracting")
                .save(consumer,"snow_block_snowball");

        ExtractingRecipeBuilder.builder(Items.NETHER_BRICK, 4)
                .setIngredient(Items.NETHER_BRICKS, 1)
                .setExperience(0.1F)
                .addCriterion("nether_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICKS))
                .setGroup("extracting")
                .save(consumer,"nether_bricks_nether_brick");

        ExtractingRecipeBuilder.builder(Items.BRICK, 4)
                .setIngredient(Items.BRICKS, 1)
                .setExperience(0.1F)
                .addCriterion("bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICKS))
                .setGroup("extracting")
                .save(consumer,"bricks_brick");

        ExtractingRecipeBuilder.builder(Items.OAK_PLANKS, 1)
                .setIngredient(ModItems.RUBBER_PLANKS, 1)
                .addChanceResult(ModItems.RUBBER, 1, 10)
                .addCriterion("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_planks_oak_planks");

        ExtractingRecipeBuilder.builder(Items.DIRT, 1)
                .setIngredient(Items.MUD, 1)
                .addCriterion("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD))
                .setGroup("extracting")
                .save(consumer,"mud_dirt");

        ExtractingRecipeBuilder.builder(ModItems.SULFUR_DUST, 1)
                .setIngredient(Items.GUNPOWDER, 1)
                .addCriterion("gunpowder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GUNPOWDER))
                .setGroup("extracting")
                .save(consumer,"sulfur_dust");


    }

}
