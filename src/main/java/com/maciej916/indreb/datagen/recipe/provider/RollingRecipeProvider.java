package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.RollingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RollingRecipeProvider extends RecipeProvider {

    public RollingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RollingRecipeBuilder.builder(ModItems.COPPER_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_COPPER, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, "copper_plate");

        RollingRecipeBuilder.builder(ModItems.GOLD_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_GOLD, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(consumer, "gold_plate");

        RollingRecipeBuilder.builder(ModItems.IRON_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_IRON, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, "iron_plate");

        RollingRecipeBuilder.builder(ModItems.TIN_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_TIN, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, "tin_plate");

        RollingRecipeBuilder.builder(ModItems.LEAD_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_LEAD, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("lead_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .save(consumer, "lead_plate");

        RollingRecipeBuilder.builder(ModItems.BRONZE_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_BRONZE, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, "bronze_plate");

        RollingRecipeBuilder.builder(ModItems.STEEL_PLATE, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_STEEL, 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .save(consumer, "steel_plate");

    }
}
