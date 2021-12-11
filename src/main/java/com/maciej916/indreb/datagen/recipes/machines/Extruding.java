package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderExtruding;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class Extruding extends RecipeProvider {

    public Extruding(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderExtruding.builder(Blocks.COBBLESTONE, 1)
                .setDuration(80)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER))
                .setGroup("extruding")
                .save(consumer,"cobblestone");

        RecipeBuilderExtruding.builder(Blocks.STONE, 1)
                .setDuration(100)
                .setWaterCost(1000)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER))
                .setGroup("extruding")
                .save(consumer,"stone");

        RecipeBuilderExtruding.builder(Blocks.OBSIDIAN, 1)
                .setWaterCost(1000)
                .setLavaCost(1000)
                .setExperience(3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.EXTRUDER))
                .setGroup("extruding")
                .save(consumer,"obsidian");


    }

}
