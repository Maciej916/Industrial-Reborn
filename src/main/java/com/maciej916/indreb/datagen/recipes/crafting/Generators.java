package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Generators extends RecipeProvider {

    public Generators(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "generators/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.GENERATOR)
                .pattern("R")
                .pattern("M")
                .pattern("f")
                .define('f', Blocks.FURNACE)
                .define('M', ModBlocks.BASIC_MACHINE_CASING)
                .define('R', ModItems.BATTERY)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("generator_one"));

        ShapedRecipeBuilder.shaped(ModBlocks.GENERATOR)
                .pattern(" R ")
                .pattern("PPP")
                .pattern(" F ")
                .define('F', ModBlocks.IRON_FURNACE)
                .define('P', ModItems.IRON_PLATE)
                .define('R', ModItems.BATTERY)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("generator_two"));

        ShapedRecipeBuilder.shaped(ModBlocks.GEO_GENERATOR)
                .pattern("gCg")
                .pattern("gCg")
                .pattern("PGP")
                .define('g', Blocks.GLASS)
                .define('C', ModItems.EMPTY_FLUID_CELL)
                .define('P', ModItems.IRON_PLATE)
                .define('G', ModBlocks.GENERATOR)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GENERATOR))
                .save(consumer, saveResource("geo_generator"));

        ShapedRecipeBuilder.shaped(ModBlocks.SOLAR_GENERATOR, 2)
                .pattern("CgC")
                .pattern("glg")
                .pattern("EGE")
                .define('C', ModItems.COAL_DUST)
                .define('l', Items.LAPIS_LAZULI)
                .define('g', Items.GLASS)
                .define('E', ModItems.ELECTRONIC_CIRCUIT)
                .define('G', ModBlocks.GENERATOR)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GENERATOR))
                .save(consumer, saveResource("solar_generator"));

    }

}