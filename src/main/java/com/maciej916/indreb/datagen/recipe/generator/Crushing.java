package com.maciej916.indreb.datagen.recipe.generator;

import com.maciej916.indreb.datagen.recipe.builder.RecipeBuilderCrushing;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class Crushing extends RecipeProvider {

    public Crushing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        RecipeBuilderCrushing.builder(Items.BLAZE_POWDER,5)
                .setIngredient(Items.BLAZE_ROD, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.BLAZE_ROD, 1, 10)
                .setGroup("crushing")
                .addCriterion("blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
                .save(consumer, "blaze_rod_blaze_powder");

    }

}
