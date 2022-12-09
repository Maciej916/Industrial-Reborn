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

public class GenerattorProvider extends RecipeProvider {

    public GenerattorProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/generator/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.GENERATOR.get())
                .pattern("R")
                .pattern("M")
                .pattern("f")
                .define('f', Blocks.FURNACE)
                .define('M', ModBlocks.BASIC_MACHINE_CASING.get())
                .define('R', ModItems.BATTERY.get())
                  .group(MODID + "/generator")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("generator_one"));

        ShapedRecipeBuilder.shaped(ModBlocks.GENERATOR.get())
                .pattern(" R ")
                .pattern("PPP")
                .pattern(" F ")
                .define('F', ModBlocks.IRON_FURNACE.get())
                .define('P', ModItemTags.FORGE_PLATES_TIN)
                .define('R', ModItems.BATTERY.get())
                  .group(MODID + "/generator")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.FURNACE))
                .save(consumer, saveResource("generator_two"));

        ShapedRecipeBuilder.shaped(ModBlocks.GEO_GENERATOR.get())
                .pattern("gCg")
                .pattern("gCg")
                .pattern("PGP")
                .define('g', Blocks.GLASS)
                .define('C', ModItems.FLUID_CELL.get())
                .define('P', ModItemTags.FORGE_PLATES_TIN)
                .define('G', ModBlocks.GENERATOR.get())
                  .group(MODID + "/generator")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GENERATOR.get()))
                .save(consumer, saveResource("geo_generator"));

        ShapedRecipeBuilder.shaped(ModBlocks.SOLAR_PANEL.get(), 2)
                .pattern("CgC")
                .pattern("glg")
                .pattern("EGE")
                .define('C', ModItemTags.FORGE_DUSTS_COAL)
                .define('l', ModItemTags.FORGE_PLATES_LAPIS)
                .define('g', Items.GLASS)
                .define('E', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('G', ModBlocks.GENERATOR.get())
                  .group(MODID + "/generator")
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GENERATOR.get()))
                .save(consumer, saveResource("solar_panel"));
        
        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_SOLAR_PANEL.get(), 1)
                .pattern("ggg")
                .pattern("asa")
                .pattern("cmc")
                .define('g', ModBlocks.REINFORCED_GLASS.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('s', ModBlocks.SOLAR_PANEL.get())
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('m', ModBlocks.ADVANCED_MACHINE_CASING.get())
                  .group(MODID + "/generator")
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_GLASS.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("solar_panel", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SOLAR_PANEL.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("advanced_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ADVANCED_MACHINE_CASING.get()))
                .save(consumer, saveResource("advanced_solar_panel"));
        
        ShapedRecipeBuilder.shaped(ModBlocks.HYBRID_SOLAR_PANEL.get(), 1)
                .pattern("ala")
                .pattern("psp")
                .pattern("cec")
                .define('a', ModItems.CARBON_PLATE.get())
                .define('l', Items.LAPIS_BLOCK)
                .define('s', ModBlocks.ADVANCED_SOLAR_PANEL.get())
                .define('p', ModItems.IRIDIUM_PLATE.get())
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('e', ModItems.ENERGY_CRYSTAL.get())
                  .group(MODID + "/generator")
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("lapis_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_BLOCK))
                .unlockedBy("advanced_solar_panel", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.ADVANCED_SOLAR_PANEL.get()))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL.get()))
                .save(consumer, saveResource("hybrid_solar_panel"));
        
        ShapedRecipeBuilder.shaped(ModBlocks.SEMIFLUID_GENERATOR.get())
                .pattern("pcp")
                .pattern("cgc")
                .pattern("pcp")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .define('c', ModItems.FLUID_CELL.get())
                .define('g', ModBlocks.GEO_GENERATOR.get())
                  .group(MODID + "/generator")
                .unlockedBy("geo_generator", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GEO_GENERATOR.get()))
                .unlockedBy("fluid_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLUID_CELL.get()))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .save(consumer, saveResource("semifluid_generator"));

        ShapedRecipeBuilder.shaped(ModBlocks.NUCLEAR_REACTOR.get())
                .pattern("aca")
                .pattern("mmm")
                .pattern("aga")
                .define('c', ModItems.ADVANCED_CIRCUIT.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('m', ModItems.REACTOR_CHAMBER.get())
                .define('g', ModItems.GENERATOR.get())
                  .group(MODID + "/generator")
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("reactor_chamber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_CHAMBER.get()))
                .unlockedBy("generator", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GENERATOR.get()))
                .save(consumer, saveResource("nuclear_reactor"));

        ShapedRecipeBuilder.shaped(ModBlocks.REACTOR_CONTROL_ROD.get())
                .pattern("ara")
                .pattern("rcr")
                .pattern("ara")
                .define('c', ModItems.REACTOR_CHAMBER.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('r', ModItems.FUEL_ROD.get())
                  .group(MODID + "/generator")
                .unlockedBy("reactor_chamber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_CHAMBER.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("fuel_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUEL_ROD.get()))
                .save(consumer, saveResource("reactor_control_rod"));

        ShapedRecipeBuilder.shaped(ModBlocks.REACTOR_CHAMBER.get())
                .pattern("ala")
                .pattern("lbl")
                .pattern("ala")
                .define('b', ModItems.BASIC_MACHINE_CASING.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('l', ModItemTags.FORGE_PLATES_LEAD)
                .group(MODID + "/generator")
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("lead_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_PLATE.get()))
                .save(consumer, saveResource("reactor_chamber"));

        ShapedRecipeBuilder.shaped(ModBlocks.REACTOR_FRAME.get())
                .pattern("ala")
                .pattern("lbl")
                .pattern("ala")
                .define('b', ModItems.BASIC_MACHINE_CASING.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('l', ModItemTags.FORGE_PLATES_STEEL)
                .group(MODID + "/generator")
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .save(consumer, saveResource("reactor_frame"));

    }
}