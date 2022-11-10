package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderExtruding;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

public class Extruding extends RecipeProvider {

    public Extruding(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderExtruding.builder(ModItems.TIN_CAN.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "plates/tin")), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "tin_can");

        RecipeBuilderExtruding.builder(ModItems.FUEL_ROD.get(), 1)
                .setIngredient(ModItems.FLUID_CELL.get(), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, "fuel_rod");

        RecipeBuilderExtruding.builder(ModItems.TIN_CABLE.get(), 3)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/tin")), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, "tin_cable");

        RecipeBuilderExtruding.builder(ModItems.COPPER_CABLE.get(), 2)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/copper")), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CABLE.get()))
                .save(consumer, "copper_cable");

        RecipeBuilderExtruding.builder(ModItems.HV_CABLE.get(), 4)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/iron")), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "hv_cable");

        RecipeBuilderExtruding.builder(ModItems.GOLD_CABLE.get(), 3)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "ingots/gold")), 1)
                .setExperience(0.5F)
                .setGroup("extruding")
                .addCriterion("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .save(consumer, "gold_cable");

    }
}
