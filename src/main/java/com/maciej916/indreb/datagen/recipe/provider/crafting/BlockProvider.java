package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class BlockProvider extends RecipeProvider {

    public BlockProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/block/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_BLOCK.get(), 1)
                .pattern("rr ")
                .pattern("rr ")
                .define('r', ModItems.RUBBER.get())
                .group(MODID + "/block/rubber")
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .save(consumer, saveResource("rubber_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_SHEET.get(), 6)
                .pattern("rrr")
                .define('r', ModItems.RUBBER_BLOCK.get())
                .group(MODID + "/block/rubber")
                .unlockedBy("rubber_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER_BLOCK.get()))
                .save(consumer, saveResource("rubber_sheet"));

        ShapedRecipeBuilder.shaped(ModBlocks.RESIN_BLOCK.get(), 1)
                .pattern("rr ")
                .pattern("rr ")
                .define('r', ModItems.STICKY_RESIN.get())
                .group(MODID + "/block/resin")
                .unlockedBy("sticky_resin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN.get()))
                .save(consumer, saveResource("resin_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.RESIN_SHEET.get(), 6)
                .pattern("rrr")
                .define('r', ModItems.RESIN_BLOCK.get())
                .group(MODID + "/block/resin")
                .unlockedBy("resin_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RESIN_BLOCK.get()))
                .save(consumer, saveResource("resin_sheet"));


        ShapedRecipeBuilder.shaped(ModBlocks.RAW_TIN_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_RAW_MATERIALS_TIN)
                .group(MODID + "/block/raw")
                .unlockedBy("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .save(consumer, saveResource("raw/raw_tin_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.RAW_LEAD_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_RAW_MATERIALS_LEAD)
                .group(MODID + "/block/raw")
                .unlockedBy("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .save(consumer, saveResource("raw/raw_lead_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.RAW_URANIUM_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_RAW_MATERIALS_URANIUM)
                .group(MODID + "/block/raw")
                .unlockedBy("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .save(consumer, saveResource("raw/raw_uranium_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.RAW_SILVER_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_RAW_MATERIALS_SILVER)
                .group(MODID + "/block/raw")
                .unlockedBy("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .save(consumer, saveResource("raw/raw_silver_block"));

        ShapelessRecipeBuilder.shapeless(ModItems.RAW_TIN.get(), 9)
                .requires(ModItems.RAW_TIN_BLOCK.get())
                .group(MODID + "/block/raw")
                .unlockedBy("raw_tin_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN_BLOCK.get()))
                .save(consumer, saveResource("raw/raw_tin"));

        ShapelessRecipeBuilder.shapeless(ModItems.RAW_LEAD.get(), 9)
                .requires(ModItems.RAW_LEAD_BLOCK.get())
                .group(MODID + "/block/raw")
                .unlockedBy("raw_tin_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD_BLOCK.get()))
                .save(consumer, saveResource("raw/raw_lead"));

        ShapelessRecipeBuilder.shapeless(ModItems.RAW_URANIUM.get(), 9)
                .requires(ModItems.RAW_URANIUM_BLOCK.get())
                .group(MODID + "/block/raw")
                .unlockedBy("raw_uranium_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM_BLOCK.get()))
                .save(consumer, saveResource("raw/raw_uranium"));

        ShapelessRecipeBuilder.shapeless(ModItems.RAW_SILVER.get(), 9)
                .requires(ModItems.RAW_SILVER_BLOCK.get())
                .group(MODID + "/block/raw")
                .unlockedBy("raw_silver_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER_BLOCK.get()))
                .save(consumer, saveResource("raw/raw_silver"));


        ShapedRecipeBuilder.shaped(ModBlocks.TIN_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_TIN)
                .group(MODID + "/block/storage")
                .unlockedBy("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, saveResource("storage/tin_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.LEAD_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_LEAD)
                .group(MODID + "/block/storage")
                .unlockedBy("lead_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .save(consumer, saveResource("storage/lead_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.URANIUM_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_URANIUM)
                .group(MODID + "/block/storage")
                .unlockedBy("uranium_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_INGOT.get()))
                .save(consumer, saveResource("storage/uranium_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_SILVER)
                .group(MODID + "/block/storage")
                .unlockedBy("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT.get()))
                .save(consumer, saveResource("storage/silver_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.STEEL_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_STEEL)
                .group(MODID + "/block/storage")
                .unlockedBy("steel_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get()))
                .save(consumer, saveResource("storage/steel_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.BRONZE_BLOCK.get(), 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItemTags.FORGE_INGOTS_BRONZE)
                .group(MODID + "/block/storage")
                .unlockedBy("bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("storage/bronze_block"));

        ShapelessRecipeBuilder.shapeless(ModItems.TIN_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_TIN)
                .group(MODID + "/block/storage")
                .unlockedBy("tin_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_BLOCK.get()))
                .save(consumer, saveResource("storage/tin_ingot"));

        ShapelessRecipeBuilder.shapeless(ModItems.LEAD_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_LEAD)
                .group(MODID + "/block/storage")
                .unlockedBy("lead_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_BLOCK.get()))
                .save(consumer, saveResource("storage/lead_ingot"));

        ShapelessRecipeBuilder.shapeless(ModItems.URANIUM_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_URANIUM)
                .group(MODID + "/block/storage")
                .unlockedBy("uranium_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_BLOCK.get()))
                .save(consumer, saveResource("storage/uranium_ingot"));

        ShapelessRecipeBuilder.shapeless(ModItems.SILVER_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_SILVER)
                .group(MODID + "/block/storage")
                .unlockedBy("silver_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_BLOCK.get()))
                .save(consumer, saveResource("storage/silver_ingot"));

        ShapelessRecipeBuilder.shapeless(ModItems.STEEL_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_STEEL)
                .group(MODID + "/block/storage")
                .unlockedBy("steel_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_BLOCK.get()))
                .save(consumer, saveResource("storage/steel_ingot"));

        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_INGOT.get(), 9)
                .requires(ModItemTags.FORGE_STORAGE_BLOCKS_BRONZE)
                .group(MODID + "/block/storage")
                .unlockedBy("bronze_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_BLOCK.get()))
                .save(consumer, saveResource("storage/bronze_ingot"));







    }
}