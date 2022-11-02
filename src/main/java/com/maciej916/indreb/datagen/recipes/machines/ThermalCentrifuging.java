package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderOreWashing;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderThermalCentrifuging;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class ThermalCentrifuging extends RecipeProvider {

    public ThermalCentrifuging(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        // RAW

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/tin")), 3)
                .addResult(ModItems.TIN_CHUNK.get(), 8)
                .setTemperature(231)
                .setExperience(0.1F)
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"tin_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/copper")), 3)
                .addResult(ModItems.COPPER_CHUNK.get(), 8)
                .setTemperature(1085)
                .setExperience(0.1F)
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"copper_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/gold")), 3)
                .addResult(ModItems.GOLD_CHUNK.get(), 8)
                .setTemperature(1064)
                .setExperience(0.1F)
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"gold_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/iron")), 3)
                .addResult(ModItems.IRON_CHUNK.get(), 8)
                .setTemperature(1538)
                .setExperience(0.1F)
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"iron_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/lead")), 3)
                .addResult(ModItems.LEAD_CHUNK.get(), 8)
                .setTemperature(327)
                .setExperience(0.1F)
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"lead_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/uranium")), 3)
                .addResult(ModItems.URANIUM_CHUNK.get(), 8)
                .setTemperature(1132)
                .setExperience(0.1F)
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"uranium_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/silver")), 3)
                .addResult(ModItems.SILVER_CHUNK.get(), 8)
                .setTemperature(961)
                .setExperience(0.1F)
                .addCriterion("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .setGroup("thermal_centrifuging/raw")
                .save(consumer,"silver_chunk");


        // Ore

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/tin")), 1)
                .addResult(ModItems.TIN_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(231)
                .setExperience(0.1F)
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"tin_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/copper")), 1)
                .addResult(ModItems.COPPER_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(1085)
                .setExperience(0.1F)
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"copper_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/gold")), 1)
                .addResult(ModItems.GOLD_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(1064)
                .setExperience(0.1F)
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"gold_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/iron")), 1)
                .addResult(ModItems.IRON_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(1538)
                .setExperience(0.1F)
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"iron_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/lead")), 1)
                .addResult(ModItems.LEAD_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(327)
                .setExperience(0.1F)
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"lead_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/uranium")), 1)
                .addResult(ModItems.URANIUM_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(1132)
                .setExperience(0.1F)
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"uranium_chunk");

        RecipeBuilderThermalCentrifuging.builder(ItemTags.create(new ResourceLocation("forge", "ores/silver")), 1)
                .addResult(ModItems.SILVER_CHUNK.get(), 4)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(961)
                .setExperience(0.1F)
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .setGroup("thermal_centrifuging/ores")
                .save(consumer,"silver_chunk");


        RecipeBuilderThermalCentrifuging.builder(Items.COBBLESTONE, 1)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setTemperature(50)
                .setDuration(100)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .setGroup("thermal_centrifuging")
                .save(consumer,"stone_dust");

        RecipeBuilderThermalCentrifuging.builder(Items.COBBLED_DEEPSLATE, 1)
                .addResult(ModItems.DEEPSLATE_DUST.get(), 1)
                .setTemperature(100)
                .setDuration(100)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLED_DEEPSLATE))
                .setGroup("thermal_centrifuging")
                .save(consumer,"deepslate_dust");

    }
}
