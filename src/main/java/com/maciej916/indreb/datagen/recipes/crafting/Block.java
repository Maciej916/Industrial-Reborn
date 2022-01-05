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
import net.minecraft.world.item.crafting.Ingredient;

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
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STICKY_RESIN)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN))
                .save(consumer, saveResource("resin_sheet"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_SHEET, 3)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RUBBER)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER))
                .save(consumer, saveResource("rubber_sheet"));


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


        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_GLASS, 7)
                .pattern("ggg")
                .pattern("aga")
                .pattern("ggg")
                .define('a', ModItems.ADVANCED_ALLOY)
                .define('g', Items.GLASS)
                .group(MODID)
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("reinforced_glass"));

        ShapedRecipeBuilder.shaped(ModBlocks.LUMINATOR, 8)
                .pattern("pcp")
                .pattern("gug")
                .pattern("ggg")
                .define('p', ItemTags.bind("forge:plates/tin"))
                .define('c', ModBlocks.TIN_CABLE_INSULATED)
                .define('u', ModBlocks.TIN_CABLE)
                .define('g', Items.GLASS)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE))
                .unlockedBy("tin_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE_INSULATED))
                .unlockedBy("tin_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("luminator"));


        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FENCE, 8)
                .pattern("iri")
                .pattern("iri")
                .define('i', Ingredient.of(ItemTags.bind("forge:ingots/iron")))
                .define('r', ModItems.IRON_ROD)
                .group(MODID)
                .unlockedBy("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD))
                .save(consumer, saveResource("iron_fence"));


        ShapedRecipeBuilder.shaped(ModBlocks.IRON_SCAFFOLDING, 6)
                .pattern("ppp")
                .pattern("ftf")
                .pattern("ppp")
                .define('p', ModItems.IRON_PLATE)
                .define('t', ModItems.IRON_ROD)
                .define('f', ModBlocks.IRON_FENCE)
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD))
                .unlockedBy("iron_fence", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FENCE))
                .save(consumer, saveResource("iron_scaffolding"));


        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_SLAB, 6)
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE)
                .group(MODID)
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE))
                .save(consumer, saveResource("reinforced_stone_slab"));


        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_STAIRS, 4)
                .pattern("r  ")
                .pattern("rr ")
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE)
                .group(MODID)
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE))
                .save(consumer, saveResource("reinforced_stone_stairs"));


    }

}