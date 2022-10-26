package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.fluid.Biomass;
import com.maciej916.indreb.common.fluid.ConstructionFoam;
import com.maciej916.indreb.common.fluid.Coolant;
import com.maciej916.indreb.common.fluid.ReinforcedConstructionFoam;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderFluidEnriching;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class FluidEnriching extends RecipeProvider {

    public FluidEnriching(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderFluidEnriching.builder(Biomass.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.BIO_CHAFF.get(), 1)
                .setFluidIngredient(Fluids.WATER, 1000)
                .setExperience(1.5F)
                .addCriterion("bio_chaff", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"biomass");


        RecipeBuilderFluidEnriching.builder(Coolant.STILL_FLUID.getSource(), 1000)
                .setIngredient(Items.LAPIS_LAZULI, 8)
                .setFluidIngredient(Fluids.WATER, 1000)
                .setExperience(2.5F)
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .setGroup("fluid_enriching")
                .save(consumer,"coolant");


        RecipeBuilderFluidEnriching.builder(ConstructionFoam.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.FOAM_POWDER.get(), 1)
                .setFluidIngredient(Fluids.WATER, 1000)
                .setExperience(3.5F)
                .addCriterion("foam_powder", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FOAM_POWDER.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"construction_foam");


        RecipeBuilderFluidEnriching.builder(ReinforcedConstructionFoam.STILL_FLUID.getSource(), 1000)
                .setIngredient(ModItems.REINFORCED_FOAM_POWDER.get(), 1)
                .setFluidIngredient(Fluids.WATER, 1000)
                .setExperience(5F)
                .addCriterion("reinforced_foam_powder", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_FOAM_POWDER.get()))
                .setGroup("fluid_enriching")
                .save(consumer,"reinforced_construction_foam");



    }
}
