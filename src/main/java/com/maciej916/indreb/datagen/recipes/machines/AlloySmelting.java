package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderAlloySmelting;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class AlloySmelting extends RecipeProvider {

    public AlloySmelting(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderAlloySmelting.builder(ModItems.BRONZE_INGOT, 4)
                .addIngredient(Ingredient.of(Items.COPPER_INGOT), 3)
                .addIngredient(Ingredient.of(ModItems.TIN_INGOT), 1)
                .setExperience(0.5F)
                .addCriterion("item_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .addCriterion("item_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .setGroup("alloy_smelting")
                .build(consumer,"bronze_ingot");


        RecipeBuilderAlloySmelting.builder(ModItems.MIXED_METAL_INGOT, 2)
                .addIngredient(Ingredient.of(ItemTags.bind("forge:ingots/iron")), 3)
                .addIngredient(Ingredient.of(ItemTags.bind("forge:alloys/bronze")), 3)
                .addIngredient(Ingredient.of(ItemTags.bind("forge:ingots/tin")), 3)
                .setExperience(0.5F)
                .addCriterion("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE))
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .setGroup("alloy_smelting")
                .build(consumer,"mixed_metal_ingot");


        RecipeBuilderAlloySmelting.builder(ModItems.STEEL_INGOT, 1)
                .addIngredient(Ingredient.of(ItemTags.bind("forge:ingots/iron")), 1)
                .addIngredient(Ingredient.of(ItemTags.bind("forge:dusts/coal")), 3)
                .setExperience(0.5F)
                .addCriterion("coal_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST))
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .setGroup("alloy_smelting")
                .build(consumer,"steel_ingot");



    }

}
