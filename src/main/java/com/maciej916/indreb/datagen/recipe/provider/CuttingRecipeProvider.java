package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.CuttingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CuttingRecipeProvider extends RecipeProvider {

    public CuttingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        CuttingRecipeBuilder.builder(ModItems.TIN_CABLE, 3)
                .setIngredient(ModItemTags.FORGE_PLATES_TIN, 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, "tin_cable");

        CuttingRecipeBuilder.builder(ModItems.COPPER_CABLE, 2)
                .setIngredient(ModItemTags.FORGE_PLATES_COPPER, 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CABLE.get()))
                .save(consumer, "copper_cable");

        CuttingRecipeBuilder.builder(ModItems.HV_CABLE, 4)
                .setIngredient(ModItemTags.FORGE_PLATES_IRON, 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "hv_cable");

        CuttingRecipeBuilder.builder(ModItems.GOLD_CABLE, 3)
                .setIngredient(ModItemTags.FORGE_PLATES_GOLD, 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .save(consumer, "gold_cable");

        CuttingRecipeBuilder.builder(Items.IRON_BARS, 3)
                .setIngredient(ModItemTags.FORGE_INGOTS_IRON, 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, "iron_bars");

        CuttingRecipeBuilder.builder(Items.CHAIN, 16)
                .setIngredient(Items.IRON_NUGGET, 8)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
                .save(consumer, "chain");


    }
}
