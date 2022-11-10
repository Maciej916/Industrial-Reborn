package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Smelting extends RecipeProvider {

    public Smelting(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "smelting/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.STICKY_RESIN.get()), ModItems.RUBBER.get(), 0.1f, 200)
                .unlockedBy("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .save(consumer, saveResource("rubber"));
        
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_TIN.get()), ModItems.TIN_INGOT.get(), 0.8f, 200)
                .unlockedBy("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_ORE.get()))
                .save(consumer, saveResource("raw_tin"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.TIN_ORE.get()), ModItems.TIN_INGOT.get(), 0.8f, 200)
                .unlockedBy("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_ORE.get()))
                .save(consumer, saveResource("tin_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_TIN_ORE.get()), ModItems.TIN_INGOT.get(), 0.8f, 200)
                .unlockedBy("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_TIN_ORE.get()))
                .save(consumer, saveResource("deepslate_tin_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.COPPER_DUST.get()), Items.COPPER_INGOT, 0.6f, 200)
                .unlockedBy("copper_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_DUST.get()))
                .save(consumer, saveResource("copper_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.TIN_DUST.get()), ModItems.TIN_INGOT.get(), 0.8f, 200)
                .unlockedBy("tin_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_DUST.get()))
                .save(consumer, saveResource("tin_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.GOLD_DUST.get()), Items.GOLD_INGOT, 0.8f, 200)
                .unlockedBy("gold_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_DUST.get()))
                .save(consumer, saveResource("gold_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.IRON_DUST.get()), Items.IRON_INGOT, 0.8f, 200)
                .unlockedBy("iron_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_DUST.get()))
                .save(consumer, saveResource("iron_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.LEAD_ORE.get()), ModItems.LEAD_INGOT.get(), 0.7f, 200)
                .unlockedBy("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEAD_ORE.get()))
                .save(consumer, saveResource("lead_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_LEAD_ORE.get()), ModItems.LEAD_INGOT.get(), 0.7f, 200)
                .unlockedBy("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_LEAD_ORE.get()))
                .save(consumer, saveResource("lead_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_LEAD.get()), ModItems.LEAD_INGOT.get(), 0.7f, 200)
                .unlockedBy("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .save(consumer, saveResource("lead_ingot3"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.LEAD_DUST.get()), ModItems.LEAD_INGOT.get(), 0.7f, 200)
                .unlockedBy("lead_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_DUST.get()))
                .save(consumer, saveResource("lead_ingot4"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_URANIUM_ORE.get()), ModItems.URANIUM_INGOT.get(), 1.5f, 200)
                .unlockedBy("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_URANIUM_ORE.get()))
                .save(consumer, saveResource("uranium"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.URANIUM_DUST.get()), ModItems.URANIUM_INGOT.get(), 1.5f, 200)
                .unlockedBy("uranium_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_DUST.get()))
                .save(consumer, saveResource("uranium2"));

    }

}