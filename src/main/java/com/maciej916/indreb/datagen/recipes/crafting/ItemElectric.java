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

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemElectric extends RecipeProvider {

    public ItemElectric(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/electric/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.BATTERY)
                .pattern(" C ")
                .pattern("TrT")
                .pattern("TrT")
                .define('C', ModBlocks.COPPER_CABLE_INSULATED)
                .define('r', Items.REDSTONE)
                .define('T', ItemTags.bind("forge:plates/tin"))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("battery"));

        ShapedRecipeBuilder.shaped(ModItems.ADVANCED_BATTERY)
                .pattern("iri")
                .pattern("pbp")
                .pattern("iri")
                .define('i', ModBlocks.COPPER_CABLE_INSULATED)
                .define('r', Items.REDSTONE)
                .define('b', ModItems.BATTERY)
                .define('p', ItemTags.bind("forge:plates/copper"))
                .group(MODID)
                .unlockedBy("battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BATTERY))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE_INSULATED))
                .save(consumer, saveResource("advanced_battery"));

        ShapedRecipeBuilder.shaped(ModItems.LAPOTRON_CRYSTAL)
                .pattern("lal")
                .pattern("lel")
                .pattern("lal")
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('l', Items.LAPIS_LAZULI)
                .define('a', ModItems.ADVANCED_CIRCUIT)
                .group(MODID)
                .unlockedBy("energy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .unlockedBy("circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .save(consumer, saveResource("lapotron_crystal"));

        ShapedRecipeBuilder.shaped(ModItems.NANO_SABER)
                .pattern("ga ")
                .pattern("ga ")
                .pattern("cec")
                .define('c', ModItems.CARBON_PLATE)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('g', Items.GLOWSTONE_DUST)
                .define('a', ModItems.ADVANCED_ALLOY)
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .unlockedBy("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .save(consumer, saveResource("nano_saber"));


        ShapedRecipeBuilder.shaped(ModItems.MINING_DRILL)
                .pattern(" i ")
                .pattern("iii")
                .pattern("ipi")
                .define('i', ModItems.IRON_PLATE)
                .define('p', ModItems.POWER_UNIT)
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POWER_UNIT))
                .save(consumer, saveResource("mining_drill"));

        ShapedRecipeBuilder.shaped(ModItems.DIAMOND_DRILL)
                .pattern(" d ")
                .pattern("dcd")
                .pattern(" a ")
                .define('c', ModItems.MINING_DRILL)
                .define('d', ItemTags.bind("forge:gems/diamond"))
                .define('a', ModItems.ADVANCED_BATTERY)
                .group(MODID)
                .unlockedBy("mining_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MINING_DRILL))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY))
                .save(consumer, saveResource("diamond_drill"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_DRILL)
                .pattern(" i ")
                .pattern("idi")
                .pattern(" e ")
                .define('d', ModItems.DIAMOND_DRILL)
                .define('i', ModItems.IRIDIUM_PLATE)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .group(MODID)
                .unlockedBy("diamond_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DRILL))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .save(consumer, saveResource("iridium_drill"));

        ShapedRecipeBuilder.shaped(ModItems.CHAINSAW)
                .pattern(" ii")
                .pattern("iii")
                .pattern("pi ")
                .define('i', ModItems.IRON_PLATE)
                .define('p', ModItems.POWER_UNIT)
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POWER_UNIT))
                .save(consumer, saveResource("chainsaw"));

        ShapedRecipeBuilder.shaped(ModItems.DIAMOND_CHAINSAW)
                .pattern(" dd")
                .pattern(" cd")
                .pattern("a  ")
                .define('c', ModItems.CHAINSAW)
                .define('d', ItemTags.bind("forge:gems/diamond"))
                .define('a', ModItems.ADVANCED_BATTERY)
                .group(MODID)
                .unlockedBy("chainsaw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHAINSAW))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .unlockedBy("advanced_battery", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_BATTERY))
                .save(consumer, saveResource("diamond_chainsaw"));

        ShapedRecipeBuilder.shaped(ModItems.IRIDIUM_CHAINSAW)
                .pattern(" ii")
                .pattern(" ci")
                .pattern("e  ")
                .define('c', ModItems.DIAMOND_CHAINSAW)
                .define('i', ModItems.IRIDIUM_PLATE)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .group(MODID)
                .unlockedBy("diamond_chainsaw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_CHAINSAW))
                .unlockedBy("iridium_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_PLATE))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .save(consumer, saveResource("iridium_chainsaw"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_HOE)
                .pattern("ii ")
                .pattern(" i ")
                .pattern(" s ")
                .define('i', ItemTags.bind("forge:plates/iron"))
                .define('s', ModItems.SMALL_POWER_UNIT)
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("small_power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_POWER_UNIT))
                .save(consumer, saveResource("electric_hoe"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_TREETAP)
                .pattern("ts ")
                .define('t', ModItems.TREETAP)
                .define('s', ModItems.SMALL_POWER_UNIT)
                .group(MODID)
                .unlockedBy("treetap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TREETAP))
                .unlockedBy("small_power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_POWER_UNIT))
                .save(consumer, saveResource("electric_treetap"));

        ShapedRecipeBuilder.shaped(ModItems.ELECTRIC_WRENCH)
                .pattern("w ")
                .pattern("s ")
                .define('w', ModItems.WRENCH)
                .define('s', ModItems.SMALL_POWER_UNIT)
                .group(MODID)
                .unlockedBy("wrench", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WRENCH))
                .unlockedBy("small_power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_POWER_UNIT))
                .save(consumer, saveResource("electric_wrench"));

        ShapedRecipeBuilder.shaped(ModItems.WIND_METER)
                .pattern(" t ")
                .pattern("tbt")
                .pattern(" ts")
                .define('t', ModItems.TIN_PLATE)
                .define('b', ModItems.BRONZE_PLATE)
                .define('s', ModItems.SMALL_POWER_UNIT)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("bronze_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PLATE))
                .unlockedBy("small_power_unit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMALL_POWER_UNIT))
                .save(consumer, saveResource("wind_meter"));

        ShapedRecipeBuilder.shaped(ModItems.MULTI_TOOL)
                .pattern("ctc")
                .pattern("aea")
                .pattern("wih")
                .define('c', ModItems.CARBON_PLATE)
                .define('t', ModItems.ELECTRIC_TREETAP)
                .define('a', ModItems.ADVANCED_ALLOY)
                .define('e', ModItems.ENERGY_CRYSTAL)
                .define('w', ModItems.ELECTRIC_WRENCH)
                .define('i', ModItems.ADVANCED_CIRCUIT)
                .define('h', ModItems.ELECTRIC_HOE)
                .group(MODID)
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE))
                .unlockedBy("electric_treetap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_TREETAP))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .unlockedBy("energy_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGY_CRYSTAL))
                .unlockedBy("electric_wrench", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_WRENCH))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT))
                .unlockedBy("electric_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELECTRIC_HOE))
                .save(consumer, saveResource("multi_tool"));




    }

}