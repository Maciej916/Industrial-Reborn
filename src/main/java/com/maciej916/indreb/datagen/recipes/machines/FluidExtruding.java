package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderFluidExtruding;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class FluidExtruding extends RecipeProvider {

    public FluidExtruding(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderFluidExtruding.builder(Blocks.COBBLESTONE, 1)
                .setDuration(80)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"cobblestone");

        RecipeBuilderFluidExtruding.builder(Blocks.STONE, 1)
                .setDuration(100)
                .setWaterCost(1000)
                .setExperience(0.5F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"stone");

        RecipeBuilderFluidExtruding.builder(Blocks.OBSIDIAN, 1)
                .setWaterCost(1000)
                .setLavaCost(1000)
                .setExperience(3F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"obsidian");

        RecipeBuilderFluidExtruding.builder(Blocks.COBBLED_DEEPSLATE, 1)
                .setDuration(100)
                .setWaterCost(200)
                .setLavaCost(50)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"cobbled_deepslate");

        RecipeBuilderFluidExtruding.builder(Blocks.DEEPSLATE, 1)
                .setDuration(120)
                .setWaterCost(2000)
                .setLavaCost(200)
                .setExperience(0.5F)
                .addCriterion("extruder", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER.get()))
                .setGroup("fluid_extruding")
                .save(consumer,"deepslate");

    }

}
