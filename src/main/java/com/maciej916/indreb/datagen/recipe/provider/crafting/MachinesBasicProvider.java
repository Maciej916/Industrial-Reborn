package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
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

public class MachinesBasicProvider extends RecipeProvider {

    public MachinesBasicProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/machine/basic/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FURNACE.get())
                .pattern("iii")
                .pattern("ifi")
                .pattern("iii")
                .define('f', Blocks.FURNACE)
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .group(MODID + "/machines/basic")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("iron_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.ELECTRIC_FURNACE.get())
                .pattern(" c ")
                .pattern("rIr")
                .pattern("   ")
                .define('I', ModBlocks.IRON_FURNACE.get())
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('r', Items.REDSTONE)
                .group(MODID + "/machines/basic")
                .unlockedBy("iron_furnace", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FURNACE.get()))
                .save(consumer, saveResource("electric_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.CRUSHER.get())
                .pattern("frf")
                .pattern("cCc")
                .pattern(" x ")
                .define('f', Items.FLINT)
                .define('r', ModBlocks.SIMPLE_CRUSHER.get())
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ModItemTags.FORGE_COBBLESTONE)
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("simple_crusher", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SIMPLE_CRUSHER.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("crusher"));

        ShapedRecipeBuilder.shaped(ModBlocks.COMPRESSOR.get())
                .pattern("scs")
                .pattern("sCs")
                .pattern("sxs")
                .define('c', ModBlocks.SIMPLE_COMPRESSOR.get())
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('s', ModItemTags.FORGE_COBBLESTONE)
                .group(MODID + "/machines/basic")
                .unlockedBy("simple_compressor", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SIMPLE_COMPRESSOR.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("compressor"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRACTOR.get())
                .pattern(" e ")
                .pattern("TCT")
                .pattern("TxT")
                .define('e', ModBlocks.SIMPLE_EXTRACTOR.get())
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('T', ModItems.TREETAP.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("simple_extractor", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SIMPLE_EXTRACTOR.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("extractor"));

        ShapedRecipeBuilder.shaped(ModBlocks.SAWMILL.get())
                .pattern(" A ")
                .pattern("PCP")
                .pattern("PxP")
                .define('A', Items.IRON_AXE)
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('P', ItemTags.PLANKS)
                .group(MODID + "/machines/basic")
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("sawmill"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRUDER.get())
                .pattern(" p ")
                .pattern("cbc")
                .pattern("tet")
                .define('p', Items.PISTON)
                .define('c', ModItems.FLUID_CELL.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .group(MODID + "/machines/basic")
                .unlockedBy("empty_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .unlockedBy("circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("basic", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("extruder"));

        ShapedRecipeBuilder.shaped(ModBlocks.RECYCLER.get())
                .pattern(" g ")
                .pattern("dcd")
                .pattern("psp")
                .define('g', Items.GLOWSTONE_DUST)
                .define('d', Items.DIRT)
                .define('s', Items.SAND)
                .define('p', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModBlocks.COMPRESSOR.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("compressor", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COMPRESSOR.get()))
                .save(consumer, saveResource("recycler"));

        ShapedRecipeBuilder.shaped(ModBlocks.CANNING_MACHINE.get())
                .pattern("pep")
                .pattern("pbp")
                .pattern("ppp")
                .define('p', ModItemTags.FORGE_PLATES_TIN)
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("canning_machine"));

        ShapedRecipeBuilder.shaped(ModBlocks.FLUID_ENRICHER.get())
                .pattern("ppp")
                .pattern("cbc")
                .pattern("pep")
                .define('p', ModItemTags.FORGE_PLATES_TIN)
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.FLUID_CELL.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, saveResource("fluid_enricher"));

        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FORMER.get())
                .pattern(" c ")
                .pattern("tbt")
                .pattern("hhh")
                .define('h', ModItems.COIL.get())
                .define('t', ModItems.TOOL_BOX.get())
                .define('b', ModItems.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "/machines/basic")
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("tool_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOOL_BOX.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("metal_former"));


    }
}