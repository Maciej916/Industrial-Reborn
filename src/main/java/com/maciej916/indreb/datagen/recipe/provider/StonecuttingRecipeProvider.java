package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class StonecuttingRecipeProvider extends RecipeProvider {

    public StonecuttingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "stonecutting/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModItems.REINFORCED_STONE.get()), ModItems.REINFORCED_STONE_SLAB.get())
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_slab"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModItems.REINFORCED_STONE.get()), ModItems.REINFORCED_STONE_STAIRS.get())
                .unlockedBy("reinforced_stone_stairs", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE_STAIRS.get()))
                .save(consumer, saveResource("reinforced_stone_stairs"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModItems.REINFORCED_STONE.get()), ModItems.REINFORCED_STONE_BRICKS.get())
                .unlockedBy("reinforced_stone_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE_BRICKS.get()))
                .save(consumer, saveResource("reinforced_stone_bricks"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModItems.REINFORCED_STONE.get()), ModItems.SMOOTH_REINFORCED_STONE.get())
                .unlockedBy("smooth_reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMOOTH_REINFORCED_STONE.get()))
                .save(consumer, saveResource("smooth_reinforced_stone"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModItems.REINFORCED_STONE.get()), ModItems.REINFORCED_STONE_BRICK_WALL.get())
                .unlockedBy("reinforced_stone_brick_wall", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE_BRICK_WALL.get()))
                .save(consumer, saveResource("reinforced_stone_brick_wall"));

        
    }
}