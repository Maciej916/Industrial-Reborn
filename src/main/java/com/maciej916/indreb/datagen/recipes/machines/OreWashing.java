package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.fluid.SulfuricAcid;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderAlloySmelting;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCanning;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderOreWashing;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

public class OreWashing extends RecipeProvider {

    public OreWashing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        // RAW

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/tin")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_TIN.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_tin");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/copper")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_COPPER.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_copper");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/gold")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_GOLD.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_gold");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/iron")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_IRON.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_iron");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/lead")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_LEAD.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_lead");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/uranium")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_URANIUM.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_uranium");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "raw_materials/silver")), 1)
                .setFluidIngredient(Fluids.WATER, 500)
                .addResult(ModItems.PURIFIED_SILVER.get(), 2)
                .setExperience(0.1F)
                .addCriterion("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .setGroup("ore_washing/raw")
                .save(consumer,"purified_silver");


        // ORE

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/tin")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_TIN.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_tin");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/copper")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_COPPER.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_copper");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/gold")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_GOLD.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_gold");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/iron")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_IRON.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_iron");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/lead")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_LEAD.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_lead");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/uranium")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_URANIUM.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_uranium");

        RecipeBuilderOreWashing.builder(ItemTags.create(new ResourceLocation("forge", "ores/silver")), 1)
                .setFluidIngredient(Fluids.WATER, 750)
                .addResult(ModItems.PURIFIED_SILVER.get(), 3)
                .addResult(ModItems.STONE_DUST.get(), 1)
                .setExperience(0.1F)
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .setGroup("ore_washing/ores")
                .save(consumer,"purified_silver");


        // Chunk

        RecipeBuilderOreWashing.builder(ModItems.TIN_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_TIN.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("tin_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_tin");

        RecipeBuilderOreWashing.builder(ModItems.COPPER_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_COPPER.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("gold_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_copper");

        RecipeBuilderOreWashing.builder(ModItems.GOLD_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_GOLD.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("gold_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_gold");

        RecipeBuilderOreWashing.builder(ModItems.IRON_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_IRON.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("iron_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_iron");

        RecipeBuilderOreWashing.builder(ModItems.LEAD_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_LEAD.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("lead_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_lead");

        RecipeBuilderOreWashing.builder(ModItems.URANIUM_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_URANIUM.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("uranium_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_uranium");

        RecipeBuilderOreWashing.builder(ModItems.SILVER_CHUNK.get(), 1)
                .setFluidIngredient(SulfuricAcid.STILL_FLUID, 250)
                .addResult(ModItems.PURIFIED_SILVER.get(), 1)
                .addResult(ModItems.MUD_PILE.get(), 1)
                .setExperience(0.1F)
                .addCriterion("silver_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_CHUNK.get()))
                .setGroup("ore_washing/chunk")
                .save(consumer,"purified_silver");
    }
}
