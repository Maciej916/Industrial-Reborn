package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Wood extends RecipeProvider {

    public Wood(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "wood/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_STAIRS.get(), 4)
                .pattern("P  ")
                .pattern("PP ")
                .pattern("PPP")
                .define('P', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID)
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("item/rubber_planks_rubber_stairs"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_SLAB.get(), 6)
                .pattern("PPP")
                .define('P', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID)
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_planks_rubber_slab"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_WOOD.get(), 3)
                .pattern("ww ")
                .pattern("ww ")
                .define('w', ModBlocks.RUBBER_LOG.get())
                .group(MODID)
                .unlockedBy("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .save(consumer, saveResource("rubber_wood"));



    }

}