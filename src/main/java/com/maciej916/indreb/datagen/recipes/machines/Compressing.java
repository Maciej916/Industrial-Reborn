package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCompressing;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCrushing;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderExtracting;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Compressing extends RecipeProvider {

    public Compressing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderCompressing.builder(Items.IRON_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/iron")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"iron_ingot_iron_block");

        RecipeBuilderCompressing.builder(Items.GOLD_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/gold")), 9)
                .setExperience(0.4F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"gold_ingot_gold_ore");

        RecipeBuilderCompressing.builder(Items.DIAMOND_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:gems/diamond")), 9)
                .setExperience(0.8F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .setGroup("compressing/block")
                .save(consumer,"diamond_diamond_block");

        RecipeBuilderCompressing.builder(Items.EMERALD_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:gems/emerald")), 9)
                .setExperience(0.6F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD))
                .setGroup("compressing/block")
                .save(consumer,"emerald_emerald_block");

        RecipeBuilderCompressing.builder(Items.COPPER_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/copper")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"copper_copper_block");

        RecipeBuilderCompressing.builder(ModBlocks.TIN_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/tin")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"tin_tin_block");

        RecipeBuilderCompressing.builder(ModBlocks.SILVER_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/silver")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"silver_block");

        RecipeBuilderCompressing.builder(ModBlocks.BRONZE_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:alloys/bronze")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"bronze_block");

        RecipeBuilderCompressing.builder(ModBlocks.STEEL_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.bind("forge:ingots/steel")), 9)
                .setExperience(0.3F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"steel_block");

        RecipeBuilderCompressing.builder(Items.REDSTONE_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.REDSTONE), 9)
                .setExperience(0.1F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .setGroup("compressing/block")
                .save(consumer,"redstone_redstone_block");

        RecipeBuilderCompressing.builder(Items.LAPIS_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.LAPIS_LAZULI), 9)
                .setExperience(0.1F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .setGroup("compressing/block")
                .save(consumer,"lapis_lapis_block");

        RecipeBuilderCompressing.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.WATER_BUCKET), 1)
                .setBonus(Items.BUCKET, 1, 100)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WATER_BUCKET))
                .setGroup("compressing/block")
                .save(consumer,"water_bucket_snow_block");

        RecipeBuilderCompressing.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.SNOWBALL), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOWBALL))
                .setGroup("compressing/block")
                .save(consumer,"snowball_snow_block");

        RecipeBuilderCompressing.builder(Items.COAL_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.COAL), 9)
                .setExperience(0.1F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .setGroup("compressing/block")
                .save(consumer,"coal_coal_block");

        RecipeBuilderCompressing.builder(Items.QUARTZ_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.QUARTZ), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .setGroup("compressing/block")
                .save(consumer,"quartz_quartz_block");

        RecipeBuilderCompressing.builder(Items.BONE_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.BONE_MEAL), 9)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .setGroup("compressing/block")
                .save(consumer,"bone_meal_bone_block");

        RecipeBuilderCompressing.builder(Items.HONEY_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.HONEY_BOTTLE), 4)
                .setExperience(0.2F)
                .setBonus(Items.GLASS_BOTTLE, 4, 100)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEY_BOTTLE))
                .setGroup("compressing/block")
                .save(consumer,"honey_bottle_honey_block");

        RecipeBuilderCompressing.builder(Items.HONEYCOMB_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.HONEYCOMB), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                .setGroup("compressing/block")
                .save(consumer,"honeycomb_honey_block");

        RecipeBuilderCompressing.builder(Items.SLIME_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.SLIME_BALL), 9)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
                .setGroup("compressing/block")
                .save(consumer,"slime_ball_slime_block");

        RecipeBuilderCompressing.builder(Items.CLAY, 1)
                .setIngredient(Ingredient.of(Items.CLAY_BALL), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY_BALL))
                .setGroup("compressing/block")
                .save(consumer,"clay_ball_clay");

        RecipeBuilderCompressing.builder(Items.NETHER_BRICKS, 1)
                .setIngredient(Ingredient.of(Items.NETHER_BRICK), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICK))
                .setGroup("compressing/block")
                .save(consumer,"nether_brick_nether_bricks");


        RecipeBuilderCompressing.builder(Items.BRICKS, 1)
                .setIngredient(Ingredient.of(Items.BRICK), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
                .setGroup("compressing/block")
                .save(consumer,"brick_bricks");

        RecipeBuilderCompressing.builder(Items.ICE, 1)
                .setIngredient(Ingredient.of(Items.SNOW_BLOCK), 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOW_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"snow_ice");

        RecipeBuilderCompressing.builder(Items.SANDSTONE, 1)
                .setIngredient(Ingredient.of(Items.SAND), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .setGroup("compressing/block")
                .save(consumer,"sand_sandstone");

        RecipeBuilderCompressing.builder(Items.PACKED_ICE, 1)
                .setIngredient(Ingredient.of(Items.ICE), 2)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ICE))
                .setGroup("compressing/block")
                .save(consumer,"ice_packed_ice");

        RecipeBuilderCompressing.builder(Items.GLOWSTONE, 1)
                .setIngredient(Ingredient.of(Items.GLOWSTONE_DUST), 4)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .setGroup("compressing/block")
                .save(consumer,"glowstone_dust_glowstone");

        RecipeBuilderCompressing.builder(Items.BLAZE_ROD, 1)
                .setIngredient(Ingredient.of(Items.BLAZE_POWDER), 5)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_POWDER))
                .setGroup("compressing/items")
                .save(consumer,"blaze_powder_blaze_rod");

        RecipeBuilderCompressing.builder(Items.NETHER_BRICK, 1)
                .setIngredient(Ingredient.of(Items.NETHERITE_INGOT), 9)
                .setExperience(0.5F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"netherite_ingot_netherite_block");

        RecipeBuilderCompressing.builder(ModItems.ADVANCED_ALLOY, 1)
                .setIngredient(Ingredient.of(ModItems.MIXED_METAL_INGOT), 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .setGroup("compressing/items")
                .save(consumer,"advanced_alloy");

        RecipeBuilderCompressing.builder(ModItems.CARBON_PLATE, 1)
                .setIngredient(Ingredient.of(ModItems.COMBINED_CARBON_FIBERS), 1)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COMBINED_CARBON_FIBERS))
                .setGroup("compressing/items")
                .save(consumer,"carbon_plate");

        RecipeBuilderCompressing.builder(ModItems.ENERGY_CRYSTAL, 1)
                .setIngredient(Ingredient.of(ModItems.ENERGIUM_DUST), 9)
                .setExperience(5.0f)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGIUM_DUST))
                .setGroup("compressing/dust")
                .save(consumer,"energium_dust");

        RecipeBuilderCompressing.builder(Items.STONE, 1)
                .setIngredient(Ingredient.of(ModItems.STONE_DUST), 4)
                .addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .setGroup("compressing/dust")
                .save(consumer,"stone");

        RecipeBuilderCompressing.builder(Items.DEEPSLATE, 1)
                .setIngredient(Ingredient.of(ModItems.DEEPSLATE_DUST), 4)
                .addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE))
                .setGroup("compressing/dust")
                .save(consumer,"deepslate");

        RecipeBuilderCompressing.builder(ModItems.IRIDIUM, 1)
                .setIngredient(Ingredient.of(ModItems.IRIDIUM_SHARD), 9)
                .addCriterion("iridium_shard", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_SHARD))
                .setExperience(1F)
                .setGroup("compressing")
                .save(consumer,"iridium");

        RecipeBuilderCompressing.builder(Items.MOSS_BLOCK,1)
                .setIngredient(Ingredient.of(ModItems.BIO_CHAFF), 1)
                .setGroup("compressing")
                .addCriterion("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF))
                .save(consumer, "moss_block");

        RecipeBuilderCompressing.builder(ModItems.REFINED_URANIUM,1)
                .setIngredient(Ingredient.of(ModItems.URANIUM), 1)
                .setGroup("compressing")
                .setExperience(2F)
                .addCriterion("uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM))
                .save(consumer, "refined_uranium");

    }

}
