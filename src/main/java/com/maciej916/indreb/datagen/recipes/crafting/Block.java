package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Block extends RecipeProvider {

    public Block(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "block/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.BASIC_MACHINE_CASING)
                .pattern("iii")
                .pattern("i i")
                .pattern("iii")
                .define('i', ModItems.IRON_PLATE)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("basic_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_MACHINE_CASING)
                .pattern("scs")
                .pattern("aba")
                .pattern("scs")
                .define('s', ModItems.STEEL_PLATE)
                .define('c', ModItems.CARBON_PLATE)
                .define('a', ModItems.ADVANCED_ALLOY)
                .define('b', ModBlocks.BASIC_MACHINE_CASING)
                .group(MODID)
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .unlockedBy("basic_machine", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING))
                .save(consumer, saveResource("advanced_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_PLANKS, 4)
                .pattern("p  ")
                .define('p', ModBlocks.RUBBER_LOG)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS))
                .save(consumer, saveResource("rubber_planks"));


        ShapedRecipeBuilder.shaped(ModBlocks.RESIN_SHEET, 3)
                .pattern("   ")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STICKY_RESIN)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN))
                .save(consumer, saveResource("resin_sheet"));


        ShapedRecipeBuilder.shaped(ModBlocks.TIN_BLOCK)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.TIN_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .save(consumer, saveResource("tin_block"));


        ShapedRecipeBuilder.shaped(ModItems.TIN_INGOT, 9)
                .pattern("x  ")
                .define('x', ModBlocks.TIN_BLOCK)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_BLOCK))
                .save(consumer, saveResource("tin_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.SILVER_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT))
                .save(consumer, saveResource("silver_block"));


        ShapedRecipeBuilder.shaped(ModItems.SILVER_INGOT, 9)
                .pattern("x  ")
                .define('x', ModBlocks.SILVER_BLOCK)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SILVER_BLOCK))
                .save(consumer, saveResource("silver_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.BRONZE_BLOCK)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.BRONZE_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_block"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_INGOT, 9)
                .pattern("x  ")
                .define('x', ModBlocks.BRONZE_BLOCK)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BRONZE_BLOCK))
                .save(consumer, saveResource("bronze_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.STEEL_BLOCK)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.STEEL_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT))
                .save(consumer, saveResource("steel_block"));


        ShapedRecipeBuilder.shaped(ModItems.STEEL_INGOT, 9)
                .pattern("x  ")
                .define('x', ModBlocks.STEEL_BLOCK)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.STEEL_BLOCK))
                .save(consumer, saveResource("steel_ingot"));



    }

}