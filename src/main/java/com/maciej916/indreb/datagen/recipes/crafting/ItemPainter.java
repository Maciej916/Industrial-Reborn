package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModItems;
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

public class ItemPainter extends RecipeProvider {

    public ItemPainter(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.PAINTER)
                .pattern("www")
                .pattern("  i")
                .pattern(" i ")
                .define('i', ModItems.IRON_ROD)
                .define('w', Items.WHITE_WOOL)
                .group(MODID)
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD))
                .unlockedBy("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .save(consumer, saveResource("painter"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_WHITE)
                .requires(ModItems.PAINTER)
                .requires(Items.BONE_MEAL)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .save(consumer, saveResource("painter_white"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_RED)
                .requires(ModItems.PAINTER)
                .requires(Items.RED_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("red_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_DYE))
                .save(consumer, saveResource("painter_red"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_ORANGE)
                .requires(ModItems.PAINTER)
                .requires(Items.ORANGE_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("orange_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ORANGE_DYE))
                .save(consumer, saveResource("painter_orange"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_PINK)
                .requires(ModItems.PAINTER)
                .requires(Items.PINK_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("pink_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_DYE))
                .save(consumer, saveResource("painter_pink"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_YELLOW)
                .requires(ModItems.PAINTER)
                .requires(Items.YELLOW_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .save(consumer, saveResource("painter_yellow"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_LIME)
                .requires(ModItems.PAINTER)
                .requires(Items.LIME_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("lime_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIME_DYE))
                .save(consumer, saveResource("painter_lime"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_GREEN)
                .requires(ModItems.PAINTER)
                .requires(Items.GREEN_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("green_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GREEN_DYE))
                .save(consumer, saveResource("painter_green"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_LIGHT_BLUE)
                .requires(ModItems.PAINTER)
                .requires(Items.LIGHT_BLUE_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("light_blue_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIGHT_BLUE_DYE))
                .save(consumer, saveResource("painter_light_blue"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_CYAN)
                .requires(ModItems.PAINTER)
                .requires(Items.CYAN_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("cyan_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CYAN_DYE))
                .save(consumer, saveResource("painter_cyan"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_BLUE)
                .requires(ModItems.PAINTER)
                .requires(Items.BLUE_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("blue_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_DYE))
                .save(consumer, saveResource("painter_blue"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_MAGENTA)
                .requires(ModItems.PAINTER)
                .requires(Items.MAGENTA_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("magenta_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGENTA_DYE))
                .save(consumer, saveResource("painter_magenta"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_PURPLE)
                .requires(ModItems.PAINTER)
                .requires(Items.PURPLE_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("purple_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PURPLE_DYE))
                .save(consumer, saveResource("painter_purple"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_BROWN)
                .requires(ModItems.PAINTER)
                .requires(Items.BROWN_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("brown_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BROWN_DYE))
                .save(consumer, saveResource("painter_brown"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_GRAY)
                .requires(ModItems.PAINTER)
                .requires(Items.GRAY_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("gray_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAY_DYE))
                .save(consumer, saveResource("painter_gray"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_LIGHT_GRAY)
                .requires(ModItems.PAINTER)
                .requires(Items.LIGHT_GRAY_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("light_gray_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIGHT_GRAY_DYE))
                .save(consumer, saveResource("painter_light_gray"));


        ShapelessRecipeBuilder.shapeless(ModItems.PAINTER_BLACK)
                .requires(ModItems.PAINTER)
                .requires(Items.BLACK_DYE)
                .group(MODID)
                .unlockedBy("painter", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PAINTER))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("painter_black"));

    }

}