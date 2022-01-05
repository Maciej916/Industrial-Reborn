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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.STICKY_RESIN), ModItems.RUBBER, 0.1f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG))
                .save(consumer, saveResource("rubber"));
        
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_TIN), ModItems.TIN_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_ORE))
                .save(consumer, saveResource("raw_tin"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.TIN_ORE), ModItems.TIN_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_ORE))
                .save(consumer, saveResource("tin_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_TIN_ORE), ModItems.TIN_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_TIN_ORE))
                .save(consumer, saveResource("deepslate_tin_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_COPPER), Items.COPPER_INGOT, 0.6f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_COPPER))
                .save(consumer, saveResource("copper_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_TIN), ModItems.TIN_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_TIN))
                .save(consumer, saveResource("tin_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_GOLD), Items.GOLD_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_GOLD))
                .save(consumer, saveResource("gold_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_IRON), Items.IRON_INGOT, 0.8f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_IRON))
                .save(consumer, saveResource("iron_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.LEAD_ORE), ModItems.LEAD_INGOT, 0.7f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEAD_ORE))
                .save(consumer, saveResource("lead_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_LEAD_ORE), ModItems.LEAD_INGOT, 0.7f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_LEAD_ORE))
                .save(consumer, saveResource("lead_ingot2"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_LEAD), ModItems.LEAD_INGOT, 0.7f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD))
                .save(consumer, saveResource("lead_ingot3"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_LEAD), ModItems.LEAD_INGOT, 0.7f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_LEAD))
                .save(consumer, saveResource("lead_ingot4"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_URANIUM_ORE), ModItems.URANIUM, 1.5f, 200)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DEEPSLATE_URANIUM_ORE))
                .save(consumer, saveResource("uranium"));

    }

}