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

        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FURNACE.get())
                .pattern("iii")
                .pattern("ifi")
                .pattern("iii")
                .define('f', Blocks.FURNACE)
                .define('i', ItemTags.create(new ResourceLocation("forge", "ingots/iron")))
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("iron_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.ELECTRIC_FURNACE.get())
                .pattern(" c ")
                .pattern("rIr")
                .pattern("   ")
                .define('I', ModBlocks.IRON_FURNACE.get())
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('r', Items.REDSTONE)
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FURNACE.get()))
                .save(consumer, saveResource("electric_furnace"));

        ShapedRecipeBuilder.shaped(ModBlocks.CRUSHER.get())
                .pattern("fff")
                .pattern("cCc")
                .pattern(" x ")
                .define('f', Items.FLINT)
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ItemTags.create(new ResourceLocation("forge", "cobblestone")))
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("crusher"));

        ShapedRecipeBuilder.shaped(ModBlocks.COMPRESSOR.get())
                .pattern("s s")
                .pattern("sCs")
                .pattern("sxs")
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('s', ItemTags.create(new ResourceLocation("forge", "stone")))
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("compressor"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRACTOR.get())
                .pattern("TCT")
                .pattern("TxT")
                .pattern("   ")
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('T', ModItems.TREETAP.get())
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("extractor"));

        ShapedRecipeBuilder.shaped(ModBlocks.SAWMILL.get())
                .pattern(" A ")
                .pattern("PCP")
                .pattern("PxP")
                .define('A', Items.IRON_AXE)
                .define('C', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('x', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('P', ItemTags.create(new ResourceLocation("planks")))
                .group(MODID + "_machines")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("sawmill"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXTRUDER.get())
                .pattern(" p ")
                .pattern("cbc")
                .pattern("tet")
                .define('p', Items.PISTON)
                .define('c', ModItems.FLUID_CELL.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('t', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .group(MODID + "_machines")
                .unlockedBy("empty_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .unlockedBy("circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("basic", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("extruder"));


        ShapedRecipeBuilder.shaped(ModBlocks.ALLOY_SMELTER.get())
                .pattern(" e ")
                .pattern("fbf")
                .pattern(" h ")
                .define('f', ModBlocks.ELECTRIC_FURNACE.get())
                .define('e', ModItems.ADVANCED_CIRCUIT.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .group(MODID + "_machines")
                .unlockedBy("electric_furnace", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ELECTRIC_FURNACE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .save(consumer, saveResource("alloy_smelter"));


        ShapedRecipeBuilder.shaped(ModBlocks.RECYCLER.get())
                .pattern(" g ")
                .pattern("dcd")
                .pattern("psp")
                .define('g', Items.GLOWSTONE_DUST)
                .define('d', Items.DIRT)
                .define('s', Items.SAND)
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModBlocks.COMPRESSOR.get())
                .group(MODID + "_machines")
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
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "_machines")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("canning_machine"));


        ShapedRecipeBuilder.shaped(ModBlocks.FLUID_ENRICHER.get())
                .pattern("ppp")
                .pattern("cbc")
                .pattern("pep")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.FLUID_CELL.get())
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "_machines")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .save(consumer, saveResource("fluid_enricher"));

        ShapedRecipeBuilder.shaped(ModBlocks.FERMENTER.get())
                .pattern("pcp")
                .pattern("cbc")
                .pattern("php")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.FLUID_CELL.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .group(MODID + "_machines")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .save(consumer, saveResource("fermenter"));

        ShapedRecipeBuilder.shaped(ModBlocks.ORE_WASHING_PLANT.get())
                .pattern("ppp")
                .pattern("cbc")
                .pattern("mem")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('c', Items.BUCKET)
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "_machines")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .unlockedBy("bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("ore_washing_plant"));

        ShapedRecipeBuilder.shaped(ModBlocks.THERMAL_CENTRIFUGE.get())
                .pattern("chc")
                .pattern("pap")
                .pattern("pmp")
                .define('c', ModItems.COIL.get())
                .define('h', ModItems.HEAT_CONDUCTOR.get())
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "_machines")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .unlockedBy("heat_conductor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_CONDUCTOR.get()))
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("thermal_centrifuge"));

        ShapedRecipeBuilder.shaped(ModBlocks.SCANNER.get())
                .pattern("igi")
                .pattern("mlm")
                .pattern("cac")
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('g', ModItems.REINFORCED_GLASS.get())
                .define('l', ModItems.LUMINATOR.get())
                .define('m', ModItems.ELECTRIC_MOTOR.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .define('c', ModItems.ADVANCED_MACHINE_CASING.get())
                .group(MODID + "_machines")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_GLASS.get()))
                .unlockedBy("luminator", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LUMINATOR.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .save(consumer, saveResource("scanner"));

        ShapedRecipeBuilder.shaped(ModBlocks.REPLICATOR.get())
                .pattern("gsg")
                .pattern("ttt")
                .pattern("hmh")
                .define('g', ModItems.REINFORCED_GLASS.get())
                .define('s', ModItems.REINFORCED_STONE.get())
                .define('t', ModItems.TELEPORT_ANCHOR.get())
                .define('h', ModItems.HV_TRANSFORMER.get())
                .define('m', ModItems.MFE.get())
                .group(MODID + "_machines")
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_GLASS.get()))
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("teleport_anchor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TELEPORT_ANCHOR.get()))
                .unlockedBy("hv_transformer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HV_TRANSFORMER.get()))
                .unlockedBy("mfe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MFE.get()))
                .save(consumer, saveResource("replicator"));

        ShapedRecipeBuilder.shaped(ModBlocks.TELEPORT_ANCHOR.get())
                .pattern("ctc")
                .pattern("gag")
                .pattern("cdc")
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('t', ModItems.BASIC_TRANSPORTER.get())
                .define('g', ModItems.GLASS_FIBRE_CABLE.get())
                .define('d', ItemTags.create(new ResourceLocation("forge", "gems/diamond")))
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .group(MODID + "_machines")
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("basic_transporter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_TRANSPORTER.get()))
                .unlockedBy("glass_fibre_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLASS_FIBRE_CABLE.get()))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .save(consumer, saveResource("teleport_anchor"));

        ShapedRecipeBuilder.shaped(ModBlocks.PATTERN_STORAGE.get())
                .pattern("rrr")
                .pattern("mam")
                .pattern("scs")
                .define('r', ModItems.REINFORCED_STONE.get())
                .define('m', ModItems.MEMORY_CARD.get())
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('s', ItemTags.create(new ResourceLocation("forge", "chests/wooden")))
                .group(MODID + "_machines")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("basic_transporter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_TRANSPORTER.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("chest", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHEST))
                .save(consumer, saveResource("pattern_storage"));

        ShapedRecipeBuilder.shaped(ModBlocks.MATTER_FABRICATOR.get())
                .pattern("gcg")
                .pattern("ala")
                .pattern("gcg")
                .define('g', Items.GLOWSTONE_DUST)
                .define('l', ModItems.LAPOTRON_CRYSTAL.get())
                .define('a', ModItems.ADVANCED_MACHINE_CASING.get())
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .group(MODID + "_machines")
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("lapotron_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPOTRON_CRYSTAL.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_MACHINE_CASING.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .save(consumer, saveResource("matter_fabricator"));

        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FORMER.get())
                .pattern(" c ")
                .pattern("tbt")
                .pattern("hhh")
                .define('h', ModItems.COIL.get())
                .define('t', ModItems.TOOL_BOX.get())
                .define('b', ModItems.BASIC_MACHINE_CASING.get())
                .define('c', ModItems.ELECTRONIC_CIRCUIT.get())
                .group(MODID + "_machines")
                .unlockedBy("coil", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COIL.get()))
                .unlockedBy("tool_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOOL_BOX.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .save(consumer, saveResource("metal_former"));

    }

}