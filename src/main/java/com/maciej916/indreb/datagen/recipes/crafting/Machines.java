package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Machines extends RecipeProvider {

    public Machines(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "machines/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FURNACE)
                .pattern("iii")
                .pattern("ifi")
                .pattern("iii")
                .define('f', Blocks.FURNACE)
                .define('i', Items.IRON_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("iron_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.ELECTRIC_FURNACE)
                .pattern(" c ")
                .pattern("rIr")
                .pattern("   ")
                .define('I', ModBlocks.IRON_FURNACE)
                .define('c', ModItems.ELECTRONIC_CIRCUIT)
                .define('r', Items.REDSTONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FURNACE))
                .save(consumer, saveResource("electric_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.CRUSHER)
                .pattern("fff")
                .pattern("cCc")
                .pattern(" x ")
                .define('f', Items.FLINT)
                .define('C', ModBlocks.BASIC_MACHINE_CASING)
                .define('c', Items.COBBLESTONE)
                .define('x', ModItems.ELECTRONIC_CIRCUIT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("crusher"));

        ShapedRecipeBuilder.shaped(ModBlocks.COMPRESSOR)
                .pattern("s s")
                .pattern("sCs")
                .pattern("sxs")
                .define('C', ModBlocks.BASIC_MACHINE_CASING)
                .define('x', ModItems.ELECTRONIC_CIRCUIT)
                .define('s', Items.STONE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("compressor"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRACTOR)
                .pattern("TCT")
                .pattern("TxT")
                .pattern("   ")
                .define('C', ModBlocks.BASIC_MACHINE_CASING)
                .define('x', ModItems.ELECTRONIC_CIRCUIT)
                .define('T', ModItems.TREETAP)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("extractor"));

        ShapedRecipeBuilder.shaped(ModBlocks.SAWMILL)
                .pattern(" A ")
                .pattern("PCP")
                .pattern("PxP")
                .define('A', Items.IRON_AXE)
                .define('C', ModBlocks.BASIC_MACHINE_CASING)
                .define('x', ModItems.ELECTRONIC_CIRCUIT)
                .define('P', ItemTags.bind("minecraft:planks"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("sawmill"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRUDER)
                .pattern(" p ")
                .pattern("cbc")
                .pattern("tet")
                .define('p', Items.PISTON)
                .define('c', ModItems.FLUID_CELL)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .define('t', ItemTags.bind("forge:plates/tin"))
                .group(MODID)
                .unlockedBy("empty_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL))
                .unlockedBy("circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("basic", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("extruder"));


        ShapedRecipeBuilder.shaped(ModBlocks.ALLOY_SMELTER)
                .pattern(" e ")
                .pattern("fbf")
                .pattern(" h ")
                .define('f', ModBlocks.ELECTRIC_FURNACE)
                .define('e', ModItems.ADVANCED_CIRCUIT)
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .define('h', ModItems.HEAT_CONDUCTOR)
                .group(MODID)
                .unlockedBy("electric_furnace", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ELECTRIC_FURNACE))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR))
                .save(consumer, saveResource("alloy_smelter"));


        ShapedRecipeBuilder.shaped(ModBlocks.RECYCLER)
                .pattern(" g ")
                .pattern("dcd")
                .pattern("psp")
                .define('g', Items.GLOWSTONE_DUST)
                .define('d', Items.DIRT)
                .define('s', Items.SAND)
                .define('p', ModItems.IRON_PLATE)
                .define('c', ModBlocks.COMPRESSOR)
                .group(MODID)
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("compressor", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COMPRESSOR))
                .save(consumer, saveResource("recycler"));


        ShapedRecipeBuilder.shaped(ModBlocks.CANNING_MACHINE)
                .pattern("pep")
                .pattern("pbp")
                .pattern("ppp")
                .define('p', ItemTags.bind("forge:plates/tin"))
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .save(consumer, saveResource("canning_machine"));


        ShapedRecipeBuilder.shaped(ModBlocks.FLUID_ENRICHER)
                .pattern("ppp")
                .pattern("cbc")
                .pattern("pep")
                .define('p', ItemTags.bind("forge:plates/tin"))
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .define('c', ModItems.FLUID_CELL)
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL))
                .save(consumer, saveResource("fluid_enricher"));


        ShapedRecipeBuilder.shaped(ModBlocks.FERMENTER)
                .pattern("pcp")
                .pattern("cbc")
                .pattern("php")
                .define('p', ItemTags.bind("forge:plates/tin"))
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .define('c', ModItems.FLUID_CELL)
                .define('h', ModItems.HEAT_CONDUCTOR)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR))
                .save(consumer, saveResource("fermenter"));

    }

}