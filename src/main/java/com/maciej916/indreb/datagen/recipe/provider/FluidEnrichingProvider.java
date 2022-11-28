package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.fluid.impl.*;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.FluidEnrichingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class FluidEnrichingProvider extends RecipeProvider {

    public FluidEnrichingProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        FluidEnrichingRecipeBuilder.builder(Biomass.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.BIO_CHAFF, 1)
                .setFluidInput(Fluids.WATER.getSource(), 1000, true)
                .setExperience(1.5F)
                .addCriterion("bio_chaff", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"biomass");

        FluidEnrichingRecipeBuilder.builder(Coolant.STILL_FLUID.getSource(), 1000)
                .setIngredient(Items.LAPIS_LAZULI, 8)
                .setFluidInput(Fluids.WATER, 1000, true)
                .setExperience(2.5F)
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .setGroup("fluid_enriching")
                .save(consumer,"coolant");

        FluidEnrichingRecipeBuilder.builder(ConstructionFoam.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.FOAM_POWDER, 1)
                .setFluidInput(Fluids.WATER, 1000, true)
                .setExperience(3.5F)
                .addCriterion("foam_powder", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FOAM_POWDER.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"construction_foam");

        FluidEnrichingRecipeBuilder.builder(ReinforcedConstructionFoam.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.REINFORCED_FOAM_POWDER, 1)
                .setFluidInput(Fluids.WATER, 1000, true)
                .setExperience(5F)
                .addCriterion("reinforced_foam_powder", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_FOAM_POWDER.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"reinforced_construction_foam");

        FluidEnrichingRecipeBuilder.builder(SulfuricAcid.STILL_FLUID.getSource(), 250)
                .setIngredient(ModItemTags.FORGE_DUSTS_SULFUR, 1)
                .setFluidInput(Fluids.WATER, 250, true)
                .setExperience(1F)
                .addCriterion("sulfur_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SULFUR_DUST.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"sulfuric_acid");


    }
}
