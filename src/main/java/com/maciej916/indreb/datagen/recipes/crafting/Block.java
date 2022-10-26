package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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

        ShapedRecipeBuilder.shaped(ModBlocks.BASIC_MACHINE_CASING.get())
                .pattern("iii")
                .pattern("i i")
                .pattern("iii")
                .define('i', ItemTags.create(new ResourceLocation("forge", "plates/iron")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, saveResource("basic_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.ADVANCED_MACHINE_CASING.get())
                .pattern("scs")
                .pattern("aba")
                .pattern("scs")
                .define('s', ItemTags.create(new ResourceLocation("forge", "plates/steel")))
                .define('c', ModItems.CARBON_PLATE.get())
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('b', ModBlocks.BASIC_MACHINE_CASING.get())
                .group(MODID)
                .unlockedBy("steel_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_PLATE.get()))
                .unlockedBy("carbon_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CARBON_PLATE.get()))
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("basic_machine", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("advanced_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_PLANKS.get(), 4)
                .pattern("p  ")
                .define('p', ModBlocks.RUBBER_LOG.get())
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_planks"));


        ShapedRecipeBuilder.shaped(ModBlocks.RESIN_SHEET.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STICKY_RESIN.get())
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STICKY_RESIN.get()))
                .save(consumer, saveResource("resin_sheet"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_SHEET.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RUBBER.get())
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .save(consumer, saveResource("rubber_sheet"));


        ShapedRecipeBuilder.shaped(ModBlocks.TIN_BLOCK.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.create(new ResourceLocation("forge", "ingots/tin")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, saveResource("tin_block"));


        ShapedRecipeBuilder.shaped(ModItems.TIN_INGOT.get(), 9)
                .pattern("x  ")
                .define('x', ItemTags.create(new ResourceLocation("forge", "storage_blocks/tin")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_BLOCK.get()))
                .save(consumer, saveResource("tin_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.create(new ResourceLocation("forge", "ingots/silver")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT.get()))
                .save(consumer, saveResource("silver_block"));


        ShapedRecipeBuilder.shaped(ModItems.SILVER_INGOT.get(), 9)
                .pattern("x  ")
                .define('x', ItemTags.create(new ResourceLocation("forge", "storage_blocks/silver")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SILVER_BLOCK.get()))
                .save(consumer, saveResource("silver_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.BRONZE_BLOCK.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.create(new ResourceLocation("forge", "ingots/bronze")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .save(consumer, saveResource("bronze_block"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_INGOT.get(), 9)
                .pattern("x  ")
                .define('x', ItemTags.create(new ResourceLocation("forge", "storage_blocks/bronze")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BRONZE_BLOCK.get()))
                .save(consumer, saveResource("bronze_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.STEEL_BLOCK.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.create(new ResourceLocation("forge", "ingots/steel")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get()))
                .save(consumer, saveResource("steel_block"));


        ShapedRecipeBuilder.shaped(ModItems.STEEL_INGOT.get(), 9)
                .pattern("x  ")
                .define('x', ItemTags.create(new ResourceLocation("forge", "storage_blocks/steel")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.STEEL_BLOCK.get()))
                .save(consumer, saveResource("steel_ingot"));


        ShapedRecipeBuilder.shaped(ModBlocks.LEAD_BLOCK.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.create(new ResourceLocation("forge", "ingots/lead")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .save(consumer, saveResource("lead_block"));


        ShapedRecipeBuilder.shaped(ModItems.LEAD_INGOT.get(), 9)
                .pattern("x  ")
                .define('x', ItemTags.create(new ResourceLocation("forge", "storage_blocks/lead")))
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.LEAD_BLOCK.get()))
                .save(consumer, saveResource("lead_ingot"));



        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_GLASS.get(), 7)
                .pattern("ggg")
                .pattern("aga")
                .pattern("ggg")
                .define('a', ModItems.ADVANCED_ALLOY.get())
                .define('g', Items.GLASS)
                .group(MODID)
                .unlockedBy("advanced_alloy", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_ALLOY.get()))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("reinforced_glass"));

        ShapedRecipeBuilder.shaped(ModBlocks.LUMINATOR.get(), 8)
                .pattern("pcp")
                .pattern("gug")
                .pattern("ggg")
                .define('p', ItemTags.create(new ResourceLocation("forge", "plates/tin")))
                .define('c', ModBlocks.TIN_CABLE_INSULATED.get())
                .define('u', ModBlocks.TIN_CABLE.get())
                .define('g', Items.GLASS)
                .group(MODID)
                .unlockedBy("tin_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_PLATE.get()))
                .unlockedBy("tin_cable_insulated", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE_INSULATED.get()))
                .unlockedBy("tin_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE.get()))
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
                .save(consumer, saveResource("luminator"));


        ShapedRecipeBuilder.shaped(ModBlocks.IRON_FENCE.get(), 8)
                .pattern("iri")
                .pattern("iri")
                .define('i', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/iron"))))
                .define('r', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "rods/iron"))))
                .group(MODID)
                .unlockedBy("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .save(consumer, saveResource("iron_fence"));


        ShapedRecipeBuilder.shaped(ModBlocks.IRON_SCAFFOLDING.get(), 6)
                .pattern("ppp")
                .pattern("ftf")
                .pattern("ppp")
                .define('p', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "plates/iron"))))
                .define('t', Ingredient.of(ItemTags.create(new ResourceLocation("forge", "rods/iron"))))
                .define('f', ModBlocks.IRON_FENCE.get())
                .group(MODID)
                .unlockedBy("iron_plate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_PLATE.get()))
                .unlockedBy("iron_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_ROD.get()))
                .unlockedBy("iron_fence", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_FENCE.get()))
                .save(consumer, saveResource("iron_scaffolding"));


        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_SLAB.get(), 6)
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID)
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_slab"));


        ShapedRecipeBuilder.shaped(ModBlocks.REINFORCED_STONE_STAIRS.get(), 4)
                .pattern("r  ")
                .pattern("rr ")
                .pattern("rrr")
                .define('r', ModBlocks.REINFORCED_STONE.get())
                .group(MODID)
                .unlockedBy("reinforced_stone", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.REINFORCED_STONE.get()))
                .save(consumer, saveResource("reinforced_stone_stairs"));


    }

}