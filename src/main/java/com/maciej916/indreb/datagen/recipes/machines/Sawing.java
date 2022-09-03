package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderSawing;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class Sawing extends RecipeProvider {

    public Sawing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderSawing.builder(ModBlocks.RUBBER_PLANKS, 5)
                .setIngredient(Ingredient.of(ModBlocks.RUBBER_LOG), 1)
                .setBonus(ModItems.STICKY_RESIN, 1,50)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG))
                .setGroup("sawing")
                .save(consumer,"rubber_log_rubber_planks");

        RecipeBuilderSawing.builder(Items.STICK, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("planks"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .setGroup("sawing")
                .save(consumer,"planks_stick");

        RecipeBuilderSawing.builder(ModItems.SAWDUST, 1)
                .setIngredient(Items.STICK, 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .setGroup("sawing")
                .save(consumer,"stick_sawdust");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("oak_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LOG))
                .setGroup("sawing")
                .save(consumer,"oak_log_oak_planks");

        RecipeBuilderSawing.builder(Items.SPRUCE_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("spruce_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_PLANKS))
                .setGroup("sawing")
                .save(consumer,"spruce_log_spruce_planks");

        RecipeBuilderSawing.builder(Items.BIRCH_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("birch_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_PLANKS))
                .setGroup("sawing")
                .save(consumer,"birch_log_birch_planks");

        RecipeBuilderSawing.builder(Items.JUNGLE_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("jungle_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_PLANKS))
                .setGroup("sawing")
                .save(consumer,"jungle_log_jungle_planks");

        RecipeBuilderSawing.builder(Items.ACACIA_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("acacia_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS))
                .setGroup("sawing")
                .save(consumer,"acacia_log_acacia_planks");

        RecipeBuilderSawing.builder(Items.DARK_OAK_PLANKS, 6)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("dark_oak_logs"))), 1)
                .setBonus(ModItems.SAWDUST, 1,25)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS))
                .setGroup("sawing")
                .save(consumer,"dark_oak_log_dark_oak_planks");


        RecipeBuilderSawing.builder(Items.LEATHER, 3)
                .setIngredient(Items.SADDLE, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SADDLE))
                .setGroup("sawing")
                .save(consumer,"saddle_leather");

        RecipeBuilderSawing.builder(Items.LEATHER, 2)
                .setIngredient(Items.LEATHER_HELMET, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HELMET))
                .setGroup("sawing/armour")
                .save(consumer,"leather_helmet_leather");

        RecipeBuilderSawing.builder(Items.LEATHER, 4)
                .setIngredient(Items.LEATHER_CHESTPLATE, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_CHESTPLATE))
                .setGroup("sawing/armour")
                .save(consumer,"leather_chestplate_leather");

        RecipeBuilderSawing.builder(Items.LEATHER, 3)
                .setIngredient(Items.LEATHER_LEGGINGS, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_LEGGINGS))
                .setGroup("sawing/armour")
                .save(consumer,"leather_leggins_leather");

        RecipeBuilderSawing.builder(Items.LEATHER, 2)
                .setIngredient(Items.LEATHER_BOOTS, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_BOOTS))
                .setGroup("sawing/armour")
                .save(consumer,"leather_boots_leather");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 6)
                .setIngredient(Items.BOOKSHELF, 1)
                .setBonus(Items.BOOK, 3, 100)
                .setExperience(0.2F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOOKSHELF))
                .setGroup("sawing")
                .save(consumer,"bookshelf_oak_planks");

        RecipeBuilderSawing.builder(Items.MELON_SLICE, 9)
                .setIngredient(Items.MELON, 1)
                .setBonus(Items.MELON_SEEDS, 1, 50)
                .setExperience(0.1F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON))
                .setGroup("sawing")
                .save(consumer,"melon_melon_slice");

        RecipeBuilderSawing.builder(Items.PUMPKIN_SEEDS, 6)
                .setIngredient(Items.PUMPKIN, 1)
                .setExperience(0.1F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN))
                .setGroup("sawing")
                .save(consumer,"pumpkin_pumpkin_seeds");

        RecipeBuilderSawing.builder(Items.WHEAT, 9)
                .setIngredient(Items.HAY_BLOCK, 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HAY_BLOCK))
                .setGroup("sawing")
                .save(consumer,"hay_block_wheat");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.NOTE_BLOCK, 1)
                .setBonus(Items.REDSTONE, 1, 50)
                .setExperience(0.4F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NOTE_BLOCK))
                .setGroup("sawing")
                .save(consumer,"plank_note_block");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.JUKEBOX, 1)
                .setBonus(Items.DIAMOND, 1, 50)
                .setExperience(0.4F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUKEBOX))
                .setGroup("sawing")
                .save(consumer,"jukebox_oak_planks");

        RecipeBuilderSawing.builder(Items.LEATHER, 3)
                .setIngredient(Items.LEATHER_HORSE_ARMOR, 1)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HORSE_ARMOR))
                .setGroup("sawing")
                .save(consumer,"leather_horse_armor_leather");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 4)
                .setIngredient(Items.CRAFTING_TABLE, 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .setGroup("sawing")
                .save(consumer,"crafting_table_oak_planks");

        RecipeBuilderSawing.builder(Items.OAK_PLANKS, 8)
                .setIngredient(Items.CHEST, 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHEST))
                .setGroup("sawing")
                .save(consumer,"chest_oak_planks");


    }

}
