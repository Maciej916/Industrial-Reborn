package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.datagen.recipe.builder.FluidExtrudingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class FluidExtrudingRecipeProvider extends RecipeProvider {

    public FluidExtrudingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        FluidExtrudingRecipeBuilder.builder(Blocks.COBBLESTONE, 1)
                .setFirstFluid(Fluids.WATER, 1, false)
                .setSecondFluid(Fluids.LAVA, 1, false)
                .setDuration(80)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"cobblestone");

        FluidExtrudingRecipeBuilder.builder(Blocks.STONE, 1)
                .setFirstFluid(Fluids.WATER, 1000, true)
                .setSecondFluid(Fluids.LAVA, 1, false)
                .setDuration(100)
                .setExperience(0.5F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"stone");

        FluidExtrudingRecipeBuilder.builder(Blocks.OBSIDIAN, 1)
                .setFirstFluid(Fluids.WATER, 1000, true)
                .setSecondFluid(Fluids.LAVA, 1000, true)
                .setExperience(3F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"obsidian");

        FluidExtrudingRecipeBuilder.builder(Blocks.COBBLED_DEEPSLATE, 1)
                .setFirstFluid(Fluids.WATER, 200, true)
                .setSecondFluid(Fluids.LAVA, 50, true)
                .setDuration(100)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"cobbled_deepslate");

        FluidExtrudingRecipeBuilder.builder(Blocks.DEEPSLATE, 1)
                .setFirstFluid(Fluids.WATER, 2000, true)
                .setSecondFluid(Fluids.LAVA, 200, true)
                .setDuration(120)
                .setExperience(0.5F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"deepslate");

    }
}
