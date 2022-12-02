package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.fluid.impl.Biogas;
import com.maciej916.indreb.common.fluid.impl.Biomass;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.FermentingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class FermentingRecipeProvider extends RecipeProvider {

    public FermentingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        FermentingRecipeBuilder.builder(Biogas.STILL_FLUID.getSource(), 200, 1400, 1f, ModItems.FERTILIZER.get())
                .setFluidInput(Biomass.STILL_FLUID.getSource(), 1000)
                .setExperience(1.5F)
                .setTickEnergyCost(8)
                .addCriterion("bio_chaff", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .setGroup("fermenting")
                .save(consumer,"biogas");

    }
}
