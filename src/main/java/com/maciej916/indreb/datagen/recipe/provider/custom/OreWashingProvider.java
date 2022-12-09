package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.fluid.impl.SulfuricAcid;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.OreWashingBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class OreWashingProvider extends RecipeProvider {

    public OreWashingProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        // RAW

        OreWashingBuilder.builder(ModItems.PURIFIED_TIN, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_TIN, 1)
                .setExperience(0.1F)
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_tin");

        OreWashingBuilder.builder(ModItems.PURIFIED_COPPER, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_COPPER, 1)
                .setExperience(0.1F)
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_copper");

        OreWashingBuilder.builder(ModItems.PURIFIED_GOLD, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_GOLD, 1)
                .setExperience(0.1F)
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_gold");

        OreWashingBuilder.builder(ModItems.PURIFIED_IRON, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_IRON, 1)
                .setExperience(0.1F)
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_iron");

        OreWashingBuilder.builder(ModItems.PURIFIED_LEAD, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_LEAD, 1)
                .setExperience(0.1F)
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_lead");

        OreWashingBuilder.builder(ModItems.PURIFIED_URANIUM, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_URANIUM, 1)
                .setExperience(0.1F)
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_uranium");

        OreWashingBuilder.builder(ModItems.PURIFIED_SILVER, 2)
                .setFluidInput(Fluids.WATER, 500)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_SILVER, 1)
                .setExperience(0.1F)
                .addCriterion("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_silver");


        // ORE

        OreWashingBuilder.builder(ModItems.PURIFIED_TIN, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_TIN, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_tin");

        OreWashingBuilder.builder(ModItems.PURIFIED_COPPER, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_COPPER, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_copper");

        OreWashingBuilder.builder(ModItems.PURIFIED_GOLD, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_GOLD, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_gold");

        OreWashingBuilder.builder(ModItems.PURIFIED_IRON, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_IRON, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_iron");

        OreWashingBuilder.builder(ModItems.PURIFIED_LEAD, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_LEAD, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_lead");

        OreWashingBuilder.builder(ModItems.PURIFIED_URANIUM, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_URANIUM, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_uranium");

        OreWashingBuilder.builder(ModItems.PURIFIED_SILVER, 3)
                .setFluidInput(Fluids.WATER, 750)
                .setIngredient(ModItemTags.FORGE_ORE_SILVER, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 100)
                .setExperience(0.1F)
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_silver");


        // Chunk

        OreWashingBuilder.builder(ModItems.PURIFIED_TIN, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.TIN_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)
                .setExperience(0.1F)
                .addCriterion("tin_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_tin");

        OreWashingBuilder.builder(ModItems.PURIFIED_COPPER, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.COPPER_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)
                .setExperience(0.1F)
                .addCriterion("gold_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_copper");

        OreWashingBuilder.builder(ModItems.PURIFIED_GOLD, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.GOLD_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)

                .setExperience(0.1F)
                .addCriterion("gold_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_gold");

        OreWashingBuilder.builder(ModItems.PURIFIED_IRON, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.IRON_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)
                .setExperience(0.1F)
                .addCriterion("iron_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_iron");

        OreWashingBuilder.builder(ModItems.PURIFIED_LEAD, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.LEAD_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)
                .setExperience(0.1F)
                .addCriterion("lead_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_lead");

        OreWashingBuilder.builder(ModItems.PURIFIED_URANIUM, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.URANIUM_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)

                .setExperience(0.1F)
                .addCriterion("uranium_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_uranium");

        OreWashingBuilder.builder(ModItems.PURIFIED_SILVER, 1)
                .setFluidInput(SulfuricAcid.STILL_FLUID, 250)
                .setIngredient(ModItems.SILVER_CHUNK, 1)
                .addChanceResult(ModItems.MUD_PILE, 1, 100)
                .setExperience(0.1F)
                .addCriterion("silver_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_silver");

    }
}
