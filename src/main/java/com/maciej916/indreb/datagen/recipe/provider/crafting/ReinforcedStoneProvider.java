package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
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

public class ReinforcedStoneProvider extends RecipeProvider {

    public ReinforcedStoneProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/reinforced_stone/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_SLAB.get(), 6)
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_slab"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_STAIRS.get(), 4)
                .pattern("r  ")
                .pattern("rr ")
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_stairs"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_BRICKS.get(), 4)
                .pattern("rr ")
                .pattern("rr ")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_bricks"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_BRICK_WALL.get(), 6)
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_brick_wall"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_DOOR.get(), 1)
                .pattern("rg ")
                .pattern("rl ")
                .pattern("rr ")
                .define('r', ModItems.REINFORCED_STONE.get())
                .define('g', ModItems.REINFORCED_GLASS.get())
                .define('l', ModItems.ADVANCED_ALLOY.get())
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("reinforced_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_GLASS.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .save(consumer, saveResource("reinforced_door"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_GLASS.get(), 6)
                .pattern("rgr")
                .pattern("aga")
                .pattern("rgr")
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('r', ModItems.REINFORCED_STONE.get())
                .define('g', Items.GLASS)
                .group(MODID + "/reinforced_stone")
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("reinforced_glass"));

        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_GLASS.get(), 6)
                .pattern("rar")
                .pattern("ggg")
                .pattern("rar")
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('r', ModItems.REINFORCED_STONE.get())
                .define('g', Items.GLASS)
                .group(MODID + "/reinforced_stone")
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_STONE.get()))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("reinforced_glass_2"));

        ShapelessRecipeBuilder.shapeless(ModItems.REINFORCED_STONE.get(), 1)
                .requires(ModItems.REINFORCED_CONSTRUCTION_FOAM.get())
                .requires(Items.GRAVEL, 3)
                .group(MODID + "/reinforced_stone")
                .unlockedBy("reinforced_construction_foam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REINFORCED_CONSTRUCTION_FOAM.get()))
                .unlockedBy("gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL))
                .save(consumer, saveResource("reinforced_stone"));

    }
}