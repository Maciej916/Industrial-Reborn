package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderExtracting;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class Extracting extends RecipeProvider {

    public Extracting(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderExtracting.builder(ModItems.RUBBER.get(), 3)
                .setIngredient(Ingredient.of(ModItems.STICKY_RESIN.get()), 1)
                .setExperience(0.2F)
                .addCriterion("sticky_resin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN.get()))
                .setGroup("extracting")
                .save(consumer,"sticky_resin_rubber");

        RecipeBuilderExtracting.builder(ModItems.RUBBER.get(), 1)
                .setIngredient(Ingredient.of(ModBlocks.RUBBER_SAPLING.get()), 1)
                .addCriterion("rubber_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_SAPLING.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_sapling_rubber");

        RecipeBuilderExtracting.builder(ModItems.RUBBER.get(), 1)
                .setIngredient(Ingredient.of(ModBlocks.RUBBER_LOG.get()), 1)
                .addCriterion("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_wood_rubber");

        RecipeBuilderExtracting.builder(Items.WHITE_WOOL, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("wool"))), 1)
                .addCriterion("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .setGroup("extracting")
                .save(consumer,"any_wool_white_wool");

        RecipeBuilderExtracting.builder(Items.CLAY_BALL, 4)
                .setIngredient(Ingredient.of(Items.CLAY), 1)
                .addCriterion("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .setGroup("extracting")
                .save(consumer,"clay_clay_ball");

        RecipeBuilderExtracting.builder(Items.SNOWBALL, 4)
                .setIngredient(Ingredient.of(Items.SNOW_BLOCK), 1)
                .addCriterion("SNow_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOW_BLOCK))
                .setGroup("extracting")
                .save(consumer,"snow_block_snowball");

        RecipeBuilderExtracting.builder(Items.NETHER_BRICK, 4)
                .setIngredient(Ingredient.of(Items.NETHER_BRICKS), 1)
                .setExperience(0.1F)
                .addCriterion("nether_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICKS))
                .setGroup("extracting")
                .save(consumer,"nether_bricks_nether_brick");

        RecipeBuilderExtracting.builder(Items.BRICK, 4)
                .setIngredient(Ingredient.of(Items.BRICKS), 1)
                .setExperience(0.1F)
                .addCriterion("bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICKS))
                .setGroup("extracting")
                .save(consumer,"bricks_brick");

        RecipeBuilderExtracting.builder(Items.OAK_PLANKS, 1)
                .setIngredient(Ingredient.of(ModBlocks.RUBBER_PLANKS.get()), 1)
                .setBonus(ModItems.RUBBER.get(), 1, 10)
                .addCriterion("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .setGroup("extracting")
                .save(consumer,"rubber_planks_oak_planks");

        RecipeBuilderExtracting.builder(Items.DIRT, 1)
                .setIngredient(Ingredient.of(Items.MUD), 1)
                .addCriterion("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD))
                .setGroup("extracting")
                .save(consumer,"mud_dirt");

        RecipeBuilderExtracting.builder(ModItems.SULFUR_DUST.get(), 1)
                .setIngredient(Ingredient.of(Items.GUNPOWDER), 1)
                .addCriterion("gunpowder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GUNPOWDER))
                .setGroup("extracting")
                .save(consumer,"sulfur_dust");


    }

}
