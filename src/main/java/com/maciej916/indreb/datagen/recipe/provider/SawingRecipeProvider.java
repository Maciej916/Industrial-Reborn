package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.SawingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class SawingRecipeProvider extends RecipeProvider {

    public SawingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        SawingRecipeBuilder.builder(ModItems.RUBBER_PLANKS, 5)
                .setIngredient(ModItems.RUBBER_LOG, 1)
                .addChanceResult(ModItems.STICKY_RESIN, 1,50)
                .setExperience(0.2F)
                .addCriterion("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .setGroup("sawing")
                .save(consumer,"rubber_log_rubber_planks");

        SawingRecipeBuilder.builder(Items.STICK, 6)
                .setIngredient(ItemTags.PLANKS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .addCriterion("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .setGroup("sawing")
                .save(consumer,"planks_stick");

        SawingRecipeBuilder.builder(ModItems.SAWDUST, 1)
                .setIngredient(Items.STICK, 1)
                .addCriterion("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .setGroup("sawing")
                .save(consumer,"stick_sawdust");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 6)
                .setIngredient(ItemTags.OAK_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("oak_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LOG))
                .setGroup("sawing")
                .save(consumer,"oak_log_oak_planks");

        SawingRecipeBuilder.builder(Items.SPRUCE_PLANKS, 6)
                .setIngredient(ItemTags.SPRUCE_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("spruce_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_PLANKS))
                .setGroup("sawing")
                .save(consumer,"spruce_log_spruce_planks");

        SawingRecipeBuilder.builder(Items.BIRCH_PLANKS, 6)
                .setIngredient(ItemTags.BIRCH_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("birch_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_PLANKS))
                .setGroup("sawing")
                .save(consumer,"birch_log_birch_planks");

        SawingRecipeBuilder.builder(Items.JUNGLE_PLANKS, 6)
                .setIngredient(ItemTags.JUNGLE_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("jungle_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_PLANKS))
                .setGroup("sawing")
                .save(consumer,"jungle_log_jungle_planks");

        SawingRecipeBuilder.builder(Items.ACACIA_PLANKS, 6)
                .setIngredient(ItemTags.ACACIA_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("acacia_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS))
                .setGroup("sawing")
                .save(consumer,"acacia_log_acacia_planks");

        SawingRecipeBuilder.builder(Items.DARK_OAK_PLANKS, 6)
                .setIngredient(ItemTags.DARK_OAK_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("dark_oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS))
                .setGroup("sawing")
                .save(consumer,"dark_oak_log_dark_oak_planks");

        SawingRecipeBuilder.builder(Items.MANGROVE_PLANKS, 6)
                .setIngredient(ItemTags.MANGROVE_LOGS, 1)
                .addChanceResult(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("mangrove_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_PLANKS))
                .setGroup("sawing")
                .save(consumer,"mangrove_log_mangrove_planks");

        SawingRecipeBuilder.builder(Items.CRIMSON_PLANKS, 6)
                .setIngredient(Items.CRIMSON_STEM, 1)
                .addChanceResult(Items.NETHERRACK, 1,15)
                .setExperience(0.4F)
                .addCriterion("crimson_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_PLANKS))
                .setGroup("sawing")
                .save(consumer,"crimson_stem_crimson_planks");

        SawingRecipeBuilder.builder(Items.WARPED_PLANKS, 6)
                .setIngredient(Items.WARPED_STEM, 1)
                .addChanceResult(Items.NETHERRACK, 1,15)
                .setExperience(0.4F)
                .addCriterion("warped_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_PLANKS))
                .setGroup("sawing")
                .save(consumer,"warped_stem_warped_planks");

        SawingRecipeBuilder.builder(Items.LEATHER, 3)
                .setIngredient(Items.SADDLE, 1)
                .setExperience(0.5F)
                .addCriterion("saddle", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SADDLE))
                .setGroup("sawing")
                .save(consumer,"saddle_leather");

        SawingRecipeBuilder.builder(Items.LEATHER, 2)
                .setIngredient(Items.LEATHER_HELMET, 1)
                .setExperience(0.5F)
                .addCriterion("leather_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HELMET))
                .setGroup("sawing/armour")
                .save(consumer,"leather_helmet_leather");

        SawingRecipeBuilder.builder(Items.LEATHER, 4)
                .setIngredient(Items.LEATHER_CHESTPLATE, 1)
                .setExperience(0.5F)
                .addCriterion("leather_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_CHESTPLATE))
                .setGroup("sawing/armour")
                .save(consumer,"leather_chestplate_leather");

        SawingRecipeBuilder.builder(Items.LEATHER, 3)
                .setIngredient(Items.LEATHER_LEGGINGS, 1)
                .setExperience(0.5F)
                .addCriterion("leather_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_LEGGINGS))
                .setGroup("sawing/armour")
                .save(consumer,"leather_leggins_leather");

        SawingRecipeBuilder.builder(Items.LEATHER, 2)
                .setIngredient(Items.LEATHER_BOOTS, 1)
                .setExperience(0.5F)
                .addCriterion("leather_boots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_BOOTS))
                .setGroup("sawing/armour")
                .save(consumer,"leather_boots_leather");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 6)
                .setIngredient(Items.BOOKSHELF, 1)
                .addChanceResult(Items.BOOK, 3, 100)
                .setExperience(0.2F)
                .addCriterion("bookshelf", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOOKSHELF))
                .setGroup("sawing")
                .save(consumer,"bookshelf_oak_planks");

        SawingRecipeBuilder.builder(Items.MELON_SLICE, 9)
                .setIngredient(Items.MELON, 1)
                .addChanceResult(Items.MELON_SEEDS, 1, 50)
                .setExperience(0.1F)
                .addCriterion("melon", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON))
                .setGroup("sawing")
                .save(consumer,"melon_melon_slice");

        SawingRecipeBuilder.builder(Items.PUMPKIN_SEEDS, 6)
                .setIngredient(Items.PUMPKIN, 1)
                .setExperience(0.1F)
                .addCriterion("pumpkin", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN))
                .setGroup("sawing")
                .save(consumer,"pumpkin_pumpkin_seeds");

        SawingRecipeBuilder.builder(Items.WHEAT, 9)
                .setIngredient(Items.HAY_BLOCK, 1)
                .addCriterion("hay_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HAY_BLOCK))
                .setGroup("sawing")
                .save(consumer,"hay_block_wheat");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.NOTE_BLOCK, 1)
                .addChanceResult(Items.REDSTONE, 1, 50)
                .setExperience(0.4F)
                .addCriterion("note_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NOTE_BLOCK))
                .setGroup("sawing")
                .save(consumer,"plank_note_block");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.JUKEBOX, 1)
                .addChanceResult(Items.DIAMOND, 1, 50)
                .setExperience(0.4F)
                .addCriterion("jukebox", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUKEBOX))
                .setGroup("sawing")
                .save(consumer,"jukebox_oak_planks");

        SawingRecipeBuilder.builder(Items.LEATHER, 3)
                .setIngredient(Items.LEATHER_HORSE_ARMOR, 1)
                .setExperience(0.5F)
                .addCriterion("leather_horse_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HORSE_ARMOR))
                .setGroup("sawing")
                .save(consumer,"leather_horse_armor_leather");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 4)
                .setIngredient(Items.CRAFTING_TABLE, 1)
                .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .setGroup("sawing")
                .save(consumer,"crafting_table_oak_planks");

        SawingRecipeBuilder.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.CHEST, 1)
                .addCriterion("chest", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHEST))
                .setGroup("sawing")
                .save(consumer,"chest_oak_planks");


    }
}
