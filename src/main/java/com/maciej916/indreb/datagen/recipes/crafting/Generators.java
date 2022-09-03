package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
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
                .define('C', ModItems.FLUID_CELL)
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


        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_SOLAR_GENERATOR, 1)
                .pattern("ggg")
                .pattern("asa")
                .pattern("cmc")
                .define('g', ModBlocks.REINFORCED_GLASS)
                .define('a', ModItems.ADVANCED_ALLOY)
                .define('s', ModBlocks.SOLAR_GENERATOR)
                .define('c', ModItems.ADVANCED_CIRCUIT)
                .define('m', ModBlocks.ADVANCED_MACHINE_CASING)
                .group(MODID)
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_GLASS))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .unlockedBy("solar_generator", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SOLAR_GENERATOR))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ADVANCED_MACHINE_CASING))
                .save(consumer, saveResource("advanced_solar_generator"));


        ShapedRecipeBuilder.shaped(ModBlocks.HYBRID_SOLAR_GENERATOR, 1)
                .pattern("ala")
                .pattern("psp")
                .pattern("cec")
                .define('a', ModItems.CARBON_PLATE)
                .define('l', Items.LAPIS_BLOCK)
                .define('s', ModBlocks.ADVANCED_SOLAR_GENERATOR)
                .define('p', ModItems.IRIDIUM_PLATE)
                .define('c', ModItems.ADVANCED_CIRCUIT)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE))
                .unlockedBy("lapis_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_BLOCK))
                .unlockedBy("advanced_solar_generator", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ADVANCED_SOLAR_GENERATOR))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .save(consumer, saveResource("hybrid_solar_generator"));


        ShapedRecipeBuilder.shaped(ModBlocks.SEMIFLUID_GENERATOR)
                .pattern("pcp")
                .pattern("cgc")
                .pattern("pcp")
                .define('p', ModItems.IRON_PLATE)
                .define('c', ModItems.FLUID_CELL)
                .define('g', ModBlocks.GEO_GENERATOR)
                .group(MODID)
                .unlockedBy("geo_generator", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GEO_GENERATOR))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .save(consumer, saveResource("semifluid_generator"));
    }

}