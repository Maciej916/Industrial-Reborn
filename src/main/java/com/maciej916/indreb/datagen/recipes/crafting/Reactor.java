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

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Reactor extends RecipeProvider {

    public Reactor(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/reactor/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.MEDIUM_COOLANT_CELL)
                .pattern("ttt")
                .pattern("sss")
                .pattern("ttt")
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('s', ModItems.SMALL_COOLANT_CELL)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("small_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_COOLANT_CELL))
                .save(consumer, saveResource("medium_coolant_cell"));

        ShapedRecipeBuilder.shaped(ModItems.LARGE_COOLANT_CELL)
                .pattern("tmt")
                .pattern("ttt")
                .pattern("tmt")
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('m', ModItems.MEDIUM_COOLANT_CELL)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("medium_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MEDIUM_COOLANT_CELL))
                .save(consumer, saveResource("large_coolant_cell"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_EXCHANGER)
                .pattern("cec")
                .pattern("tct")
                .pattern("ctc")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('c', ItemTags.bind("forge:plates/copper"))
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .save(consumer, saveResource("heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_HEAT_EXCHANGER)
                .pattern("geg")
                .pattern("hch")
                .pattern("geg")
                .define('e', ModItems.ELECTRONIC_CIRCUIT)
                .define('h', ModItems.HEAT_EXCHANGER)
                .define('g', ModBlocks.GLASS_FIBRE_CABLE)
                .define('c', ItemTags.bind("forge:plates/copper"))
                .group(MODID)
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER))
                .unlockedBy("GLASs_FIBRE_CABLE", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .save(consumer, saveResource("advanced_heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_VENT)
                .pattern("bib")
                .pattern("iei")
                .pattern("bib")
                .define('b', Items.IRON_BARS)
                .define('i', ItemTags.bind("forge:plates/iron"))
                .define('e', ModItems.ELECTRIC_MOTOR)
                .group(MODID)
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR))
                .save(consumer, saveResource("heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_HEAT_VENT)
                .pattern("bhb")
                .pattern("bdb")
                .pattern("bhb")
                .define('b', Items.IRON_BARS)
                .define('h', ModItems.HEAT_VENT)
                .define('d', Items.DIAMOND)
                .group(MODID)
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(consumer, saveResource("advanced_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.COMPONENT_HEAT_EXCHANGER)
                .pattern(" g ")
                .pattern("ghg")
                .pattern(" g ")
                .define('g', ItemTags.bind("forge:plates/gold"))
                .define('h', ModItems.HEAT_EXCHANGER)
                .group(MODID)
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER))
                .save(consumer, saveResource("component_heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.COMPONENT_HEAT_VENT)
                .pattern("btb")
                .pattern("tht")
                .pattern("btb")
                .define('b', Items.IRON_BARS)
                .define('t', ItemTags.bind("forge:plates/tin"))
                .define('h', ModItems.HEAT_VENT)
                .group(MODID)
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT))
                .save(consumer, saveResource("component_heat_vent"));

        ShapelessRecipeBuilder.shapeless(ModItems.CONTAINMENT_REACTOR_PLATING)
                .requires(ModItems.REACTOR_PLATING)
                .requires(ModItems.ADVANCED_ALLOY, 2)
                .group(MODID)
                .unlockedBy("reactor_plating", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_PLATING))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .save(consumer, saveResource("containment_reactor_plating"));

        ShapelessRecipeBuilder.shapeless(ModItems.REACTOR_PLATING)
                .requires(ItemTags.bind("forge:plates/lead"))
                .requires(ModItems.ADVANCED_ALLOY)
                .group(MODID)
                .unlockedBy("lead_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_PLATE))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .save(consumer, saveResource("reactor_plating"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_CAPACITY_REACTOR_PLATING)
                .pattern("ccc")
                .pattern("crc")
                .pattern("ccc")
                .define('c', ItemTags.bind("forge:plates/copper"))
                .define('r', ModItems.REACTOR_PLATING)
                .group(MODID)
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .unlockedBy("reactor_plating", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_PLATING))
                .save(consumer, saveResource("heat_capacity_reactor_plating"));

        ShapedRecipeBuilder.shaped(ModItems.REACTOR_HEAT_VENT)
                .pattern("ccc")
                .pattern("chc")
                .pattern("ccc")
                .define('c', ItemTags.bind("forge:plates/copper"))
                .define('h', ModItems.HEAT_VENT)
                .group(MODID)
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT))
                .save(consumer, saveResource("reactor_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKED_HEAT_VENT)
                .pattern(" g ")
                .pattern("grg")
                .pattern(" g ")
                .define('g', ItemTags.bind("forge:plates/gold"))
                .define('r', ModItems.REACTOR_HEAT_VENT)
                .group(MODID)
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE))
                .unlockedBy("reactor_heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_HEAT_VENT))
                .save(consumer, saveResource("overclocked_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.REACTOR_HEAT_EXCHANGER)
                .pattern("ccc")
                .pattern("chc")
                .pattern("ccc")
                .define('c', ItemTags.bind("forge:plates/copper"))
                .define('h', ModItems.HEAT_EXCHANGER)
                .group(MODID)
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER))
                .save(consumer, saveResource("reactor_heat_exchanger"));




    }

}