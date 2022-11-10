package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderRolling;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class Rolling extends RecipeProvider {

    public Rolling(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderRolling.builder(ModItems.COPPER_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/copper")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, "copper_plate");

        RecipeBuilderRolling.builder(ModItems.GOLD_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/gold")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(consumer, "gold_plate");

        RecipeBuilderRolling.builder(ModItems.IRON_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/iron")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, "iron_plate");

        RecipeBuilderRolling.builder(ModItems.TIN_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/tin")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, "tin_plate");

        RecipeBuilderRolling.builder(ModItems.LEAD_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/lead")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("lead_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .save(consumer, "lead_plate");

        RecipeBuilderRolling.builder(ModItems.BRONZE_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/bronze")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, "bronze_plate");

        RecipeBuilderRolling.builder(ModItems.STEEL_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/steel")), 1)
                .setExperience(0.5F)
                .setGroup("rolling")
                .addCriterion("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .save(consumer, "steel_plate");

    }
}
