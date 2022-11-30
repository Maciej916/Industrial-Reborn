package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.AlloySmeltingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class AlloySmeltingRecipeProvider extends RecipeProvider {

    public AlloySmeltingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        AlloySmeltingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 4)
                .addIngredient(ModItemTags.FORGE_INGOTS_COPPER, 3)
                .addIngredient(ModItemTags.FORGE_INGOTS_TIN, 1)
                .setExperience(0.5F)
                .addCriterion("item_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .addCriterion("item_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .setGroup("alloy_smelting")
                .save(consumer, "bronze_ingot");


        AlloySmeltingRecipeBuilder.builder(ModItems.MIXED_METAL_INGOT, 2)
                .addIngredient(ModItemTags.FORGE_INGOTS_IRON, 3)
                .addIngredient(ModItemTags.FORGE_INGOTS_BRONZE, 3)
                .addIngredient(ModItemTags.FORGE_INGOTS_TIN, 3)
                .setExperience(1.5F)
                .addCriterion("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE.get()))
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .setGroup("alloy_smelting")
                .save(consumer, "mixed_metal_ingot");


        AlloySmeltingRecipeBuilder.builder(ModItems.STEEL_INGOT, 1)
                .addIngredient(ModItemTags.FORGE_INGOTS_IRON, 1)
                .addIngredient(ModItemTags.FORGE_DUSTS_COAL, 3)
                .setExperience(1F)
                .addCriterion("coal_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get()))
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .setGroup("alloy_smelting")
                .save(consumer, "steel_ingot");


    }
}
