package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCutting;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

public class Cutting extends RecipeProvider {

    public Cutting(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderCutting.builder(ModItems.TIN_CABLE.get(), 3)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "plates/tin")), 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .save(consumer, "tin_cable");

        RecipeBuilderCutting.builder(ModItems.COPPER_CABLE.get(), 2)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "plates/copper")), 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CABLE.get()))
                .save(consumer, "copper_cable");

        RecipeBuilderCutting.builder(ModItems.HV_CABLE.get(), 4)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "plates/iron")), 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, "hv_cable");

        RecipeBuilderCutting.builder(ModItems.GOLD_CABLE.get(), 3)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "plates/gold")), 1)
                .setExperience(0.5F)
                .setGroup("cutting")
                .addCriterion("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .save(consumer, "gold_cable");

    }
}
