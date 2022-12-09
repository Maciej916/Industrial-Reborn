package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsReactorProvider extends RecipeProvider {

    public ItemsReactorProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/reactor/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.MEDIUM_COOLANT_CELL.get())
                .pattern("ttt")
                .pattern("sss")
                .pattern("ttt")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('s', ModItems.SMALL_COOLANT_CELL.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("small_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_COOLANT_CELL.get()))
                .save(consumer, saveResource("medium_coolant_cell"));

        ShapedRecipeBuilder.shaped(ModItems.LARGE_COOLANT_CELL.get())
                .pattern("tmt")
                .pattern("ttt")
                .pattern("tmt")
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('m', ModItems.MEDIUM_COOLANT_CELL.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("medium_coolant_cell", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MEDIUM_COOLANT_CELL.get()))
                .save(consumer, saveResource("large_coolant_cell"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_EXCHANGER.get())
                .pattern("cec")
                .pattern("tct")
                .pattern("ctc")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .group(MODID + "/items/reactor")
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_HEAT_EXCHANGER.get())
                .pattern("geg")
                .pattern("hch")
                .pattern("geg")
                .define('e', ModItems.ELECTRONIC_CIRCUIT.get())
                .define('h', ModItems.HEAT_EXCHANGER.get())
                .define('g', ModBlocks.GLASS_FIBRE_CABLE.get())
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .group(MODID + "/items/reactor")
                .unlockedBy("electronic_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRONIC_CIRCUIT.get()))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER.get()))
                .unlockedBy("GLASs_FIBRE_CABLE", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLASS_FIBRE_CABLE.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("advanced_heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_VENT.get())
                .pattern("bib")
                .pattern("iei")
                .pattern("bib")
                .define('b', Items.IRON_BARS)
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('e', ModItems.ELECTRIC_MOTOR.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("electric_motor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_MOTOR.get()))
                .save(consumer, saveResource("heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_HEAT_VENT.get())
                .pattern("bhb")
                .pattern("bdb")
                .pattern("bhb")
                .define('b', Items.IRON_BARS)
                .define('h', ModItems.HEAT_VENT.get())
                .define('d', Items.DIAMOND)
                .group(MODID + "/items/reactor")
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT.get()))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(consumer, saveResource("advanced_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.COMPONENT_HEAT_EXCHANGER.get())
                .pattern(" g ")
                .pattern("ghg")
                .pattern(" g ")
                .define('g', ModItemTags.FORGE_PLATES_GOLD)
                .define('h', ModItems.HEAT_EXCHANGER.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER.get()))
                .save(consumer, saveResource("component_heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.COMPONENT_HEAT_VENT.get())
                .pattern("btb")
                .pattern("tht")
                .pattern("btb")
                .define('b', Items.IRON_BARS)
                .define('t', ModItemTags.FORGE_PLATES_TIN)
                .define('h', ModItems.HEAT_VENT.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("iron_bars", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BARS))
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT.get()))
                .save(consumer, saveResource("component_heat_vent"));

        ShapelessRecipeBuilder.shapeless(ModItems.CONTAINMENT_REACTOR_PLATING.get())
                .requires(ModItems.REACTOR_PLATING.get())
                .requires(ModItems.ADVANCED_ALLOY.get(), 2)
                .group(MODID + "/items/reactor")
                .unlockedBy("reactor_plating", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_PLATING.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .save(consumer, saveResource("containment_reactor_plating"));

        ShapelessRecipeBuilder.shapeless(ModItems.REACTOR_PLATING.get())
                .requires(ModItemTags.FORGE_PLATES_LEAD)
                .requires(ModItems.ADVANCED_ALLOY.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("lead_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_PLATE.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .save(consumer, saveResource("reactor_plating"));

        ShapedRecipeBuilder.shaped(ModItems.HEAT_CAPACITY_REACTOR_PLATING.get())
                .pattern("ccc")
                .pattern("crc")
                .pattern("ccc")
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('r', ModItems.REACTOR_PLATING.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("reactor_plating", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_PLATING.get()))
                .save(consumer, saveResource("heat_capacity_reactor_plating"));

        ShapedRecipeBuilder.shaped(ModItems.REACTOR_HEAT_VENT.get())
                .pattern("ccc")
                .pattern("chc")
                .pattern("ccc")
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('h', ModItems.HEAT_VENT.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_VENT.get()))
                .save(consumer, saveResource("reactor_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.OVERCLOCKED_HEAT_VENT.get())
                .pattern(" g ")
                .pattern("grg")
                .pattern(" g ")
                .define('g', ModItemTags.FORGE_PLATES_GOLD)
                .define('r', ModItems.REACTOR_HEAT_VENT.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("gold_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_PLATE.get()))
                .unlockedBy("reactor_heat_vent", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REACTOR_HEAT_VENT.get()))
                .save(consumer, saveResource("overclocked_heat_vent"));

        ShapedRecipeBuilder.shaped(ModItems.REACTOR_HEAT_EXCHANGER.get())
                .pattern("ccc")
                .pattern("chc")
                .pattern("ccc")
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('h', ModItems.HEAT_EXCHANGER.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER.get()))
                .save(consumer, saveResource("reactor_heat_exchanger"));

        ShapedRecipeBuilder.shaped(ModItems.NEUTRON_REFLECTOR.get())
                .pattern("tct")
                .pattern("cbc")
                .pattern("tct")
                .define('b', ModItemTags.FORGE_PLATES_COPPER)
                .define('t', ModItemTags.FORGE_DUSTS_TIN)
                .define('c', ModItemTags.FORGE_DUSTS_COAL)
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("tin_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_DUST.get()))
                .unlockedBy("heat_exchanger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEAT_EXCHANGER.get()))
                .save(consumer, saveResource("neutron_reflector"));

        ShapedRecipeBuilder.shaped(ModItems.THICK_NEUTRON_REFLECTOR.get())
                .pattern("bnb")
                .pattern("nbn")
                .pattern("bnb")
                .define('b', ModItemTags.FORGE_PLATES_COPPER)
                .define('n', ModItems.NEUTRON_REFLECTOR.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("neutron_reflector", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NEUTRON_REFLECTOR.get()))
                .save(consumer, saveResource("thick_neutron_reflector"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .pattern("ncn")
                .pattern("nin")
                .pattern("ncn")
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('i', ModItemTags.FORGE_PLATES_IRIDIUM)
                .define('n', ModItems.NEUTRON_REFLECTOR.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE.get()))
                .unlockedBy("neutron_reflector", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NEUTRON_REFLECTOR.get()))
                .save(consumer, saveResource("iridium_neutron_reflector"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .pattern("nnn")
                .pattern("cic")
                .pattern("nnn")
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('i', ModItemTags.FORGE_PLATES_IRIDIUM)
                .define('n', ModItems.NEUTRON_REFLECTOR.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE.get()))
                .unlockedBy("neutron_reflector", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NEUTRON_REFLECTOR.get()))
                .save(consumer, saveResource("iridium_neutron_reflector_2"));

        ShapedRecipeBuilder.shaped(ModItems.FUEL_ROD_URANIUM_DUAL.get())
                .pattern("rir")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('r', ModItems.FUEL_ROD_URANIUM.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("fuel_rod_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUEL_ROD_URANIUM.get()))
                .save(consumer, saveResource("fuel_rod_uranium_dual"));

        ShapedRecipeBuilder.shaped(ModItems.FUEL_ROD_URANIUM_QUAD.get())
                .pattern(" d ")
                .pattern("cic")
                .pattern(" d ")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('d', ModItems.FUEL_ROD_URANIUM_DUAL.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("fuel_rod_uranium_dual", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUEL_ROD_URANIUM_DUAL.get()))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("fuel_rod_uranium_quad"));

        ShapedRecipeBuilder.shaped(ModItems.FUEL_ROD_URANIUM_QUAD.get())
                .pattern("rir")
                .pattern("cic")
                .pattern("rir")
                .define('i', ModItemTags.FORGE_PLATES_IRON)
                .define('c', ModItemTags.FORGE_PLATES_COPPER)
                .define('r', ModItems.FUEL_ROD_URANIUM.get())
                .group(MODID + "/items/reactor")
                .unlockedBy("fuel_rod_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUEL_ROD_URANIUM.get()))
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("copper_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_PLATE.get()))
                .save(consumer, saveResource("fuel_rod_uranium_quad_2"));


    }
}