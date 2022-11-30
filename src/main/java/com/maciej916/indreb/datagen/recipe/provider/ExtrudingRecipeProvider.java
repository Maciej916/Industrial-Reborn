package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.ExtrudingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ExtrudingRecipeProvider extends RecipeProvider {

    public ExtrudingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ExtrudingRecipeBuilder.builder(ModItems.EMPTY_CAN, 1)
                .setIngredient(ModItemTags.FORGE_PLATES_TIN, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "tin_can");

        ExtrudingRecipeBuilder.builder(ModItems.FUEL_ROD, 1)
                .setIngredient(ModItems.FLUID_CELL, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, "fuel_rod");

        ExtrudingRecipeBuilder.builder(ModItems.TIN_CABLE, 3)
                .setIngredient(ModItemTags.FORGE_INGOTS_TIN, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, "tin_cable");

        ExtrudingRecipeBuilder.builder(ModItems.COPPER_CABLE, 2)
                .setIngredient(ModItemTags.FORGE_INGOTS_COPPER, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CABLE.get()))
                .save(consumer, "copper_cable");

        ExtrudingRecipeBuilder.builder(ModItems.HV_CABLE, 4)
                .setIngredient(ModItemTags.FORGE_INGOTS_IRON, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "hv_cable");

        ExtrudingRecipeBuilder.builder(ModItems.GOLD_CABLE, 3)
                .setIngredient(ModItemTags.FORGE_INGOTS_GOLD, 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .save(consumer, "gold_cable");



    }
}
