package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.ThermalCentrifugingBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ThermalCentrifugingProvider extends RecipeProvider {

    public ThermalCentrifugingProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ThermalCentrifugingBuilder.builder(ModItems.TIN_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_TIN, 3)
                .setTemperature(231)
                .setExperience(0.1F)
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"tin_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.COPPER_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_COPPER, 3)
                .setTemperature(1085)
                .setExperience(0.1F)
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"copper_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.GOLD_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_GOLD, 3)
                .setTemperature(1064)
                .setExperience(0.1F)
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"gold_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.IRON_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_IRON, 3)
                .setTemperature(1538)
                .setExperience(0.1F)
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"iron_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.LEAD_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_LEAD, 3)
                .setTemperature(327)
                .setExperience(0.1F)
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"lead_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.URANIUM_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_URANIUM, 3)
                .setTemperature(1132)
                .setExperience(0.1F)
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"uranium_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.SILVER_CHUNK, 8)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_SILVER, 3)
                .setTemperature(961)
                .setExperience(0.1F)
                .addCriterion("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"silver_chunk");


        // Ore

        ThermalCentrifugingBuilder.builder(ModItems.TIN_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_TIN, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(231)
                .setExperience(0.1F)
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"tin_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.COPPER_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_COPPER, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(1085)
                .setExperience(0.1F)
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"copper_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.GOLD_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_GOLD, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(1064)
                .setExperience(0.1F)
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"gold_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.IRON_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_IRON, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(1538)
                .setExperience(0.1F)
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"iron_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.LEAD_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_LEAD, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(327)
                .setExperience(0.1F)
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"lead_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.URANIUM_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_URANIUM, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(1132)
                .setExperience(0.1F)
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"uranium_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.SILVER_CHUNK, 4)
                .setIngredient(ModItemTags.FORGE_ORE_SILVER, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setTemperature(961)
                .setExperience(0.1F)
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"silver_chunk");

        ThermalCentrifugingBuilder.builder(ModItems.STONE_DUST, 2)
                .setIngredient(Items.COBBLESTONE, 1)
                .setTemperature(80)
                .setDuration(100)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .setGroup("thermal_centrifuging")
                .save(consumer,"stone_dust");

        ThermalCentrifugingBuilder.builder(ModItems.DEEPSLATE_DUST, 2)
                .setIngredient(Items.COBBLED_DEEPSLATE, 1)
                .setTemperature(100)
                .setDuration(100)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLED_DEEPSLATE))
                .setGroup("thermal_centrifuging")
                .save(consumer,"deepslate_dust");

        ThermalCentrifugingBuilder.builder(ModItems.RUBBER, 1)
                .setIngredient(ModItemTags.FORGE_SLIMEBALLS, 1)
                .setTemperature(55)
                .setDuration(150)
                .setExperience(0.5f)
                .addCriterion("slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
                .setGroup("thermal_centrifuging")
                .save(consumer,"rubber");

    }
}
