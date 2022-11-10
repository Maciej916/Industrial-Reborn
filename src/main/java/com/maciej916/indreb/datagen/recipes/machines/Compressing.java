package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCompressing;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class Compressing extends RecipeProvider {

    public Compressing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderCompressing.builder(Items.IRON_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/iron"))), 9)
                .setExperience(0.3F)
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"iron_ingot_iron_block");

        RecipeBuilderCompressing.builder(Items.GOLD_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/gold"))), 9)
                .setExperience(0.4F)
                .addCriterion("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"gold_ingot_gold_ore");

        RecipeBuilderCompressing.builder(Items.DIAMOND_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "gems/diamond"))), 9)
                .setExperience(0.8F)
                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .setGroup("compressing/block")
                .save(consumer,"diamond_diamond_block");

        RecipeBuilderCompressing.builder(Items.EMERALD_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "gems/emerald"))), 9)
                .setExperience(0.6F)
                .addCriterion("emerald", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD))
                .setGroup("compressing/block")
                .save(consumer,"emerald_emerald_block");

        RecipeBuilderCompressing.builder(Items.COPPER_BLOCK, 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/copper"))), 9)
                .setExperience(0.3F)
                .addCriterion("copper_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"copper_copper_block");

        RecipeBuilderCompressing.builder(ModBlocks.TIN_BLOCK.get(), 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/tin"))), 9)
                .setExperience(0.3F)
                .addCriterion("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"tin_tin_block");

        RecipeBuilderCompressing.builder(ModBlocks.SILVER_BLOCK.get(), 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/silver"))), 9)
                .setExperience(0.3F)
                .addCriterion("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"silver_block");

        RecipeBuilderCompressing.builder(ModBlocks.BRONZE_BLOCK.get(), 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/bronze"))), 9)
                .setExperience(0.3F)
                .addCriterion("bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"bronze_block");

        RecipeBuilderCompressing.builder(ModBlocks.STEEL_BLOCK.get(), 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/steel"))), 9)
                .setExperience(0.3F)
                .addCriterion("steel_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"steel_block");

        RecipeBuilderCompressing.builder(ModBlocks.LEAD_BLOCK.get(), 1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/lead"))), 9)
                .setExperience(0.3F)
                .addCriterion("lead_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"lead_block");

        RecipeBuilderCompressing.builder(Items.REDSTONE_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.REDSTONE), 9)
                .setExperience(0.1F)
                .addCriterion("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .setGroup("compressing/block")
                .save(consumer,"redstone_redstone_block");

        RecipeBuilderCompressing.builder(Items.LAPIS_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.LAPIS_LAZULI), 9)
                .setExperience(0.1F)
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .setGroup("compressing/block")
                .save(consumer,"lapis_lapis_block");

        RecipeBuilderCompressing.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.WATER_BUCKET), 1)
                .setBonus(Items.BUCKET, 1, 100)
                .addCriterion("water_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WATER_BUCKET))
                .setGroup("compressing/block")
                .save(consumer,"water_bucket_snow_block");

        RecipeBuilderCompressing.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.SNOWBALL), 4)
                .addCriterion("snowball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOWBALL))
                .setGroup("compressing/block")
                .save(consumer,"snowball_snow_block");

        RecipeBuilderCompressing.builder(Items.COAL_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.COAL), 9)
                .setExperience(0.1F)
                .addCriterion("coal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .setGroup("compressing/block")
                .save(consumer,"coal_coal_block");

        RecipeBuilderCompressing.builder(Items.QUARTZ_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.QUARTZ), 4)
                .addCriterion("quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .setGroup("compressing/block")
                .save(consumer,"quartz_quartz_block");

        RecipeBuilderCompressing.builder(Items.BONE_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.BONE_MEAL), 9)
                .addCriterion("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .setGroup("compressing/block")
                .save(consumer,"bone_meal_bone_block");

        RecipeBuilderCompressing.builder(Items.HONEY_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.HONEY_BOTTLE), 4)
                .setExperience(0.2F)
                .setBonus(Items.GLASS_BOTTLE, 4, 100)
                .addCriterion("honey_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEY_BOTTLE))
                .setGroup("compressing/block")
                .save(consumer,"honey_bottle_honey_block");

        RecipeBuilderCompressing.builder(Items.HONEYCOMB_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.HONEYCOMB), 4)
                .addCriterion("honeycomb", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                .setGroup("compressing/block")
                .save(consumer,"honeycomb_honey_block");

        RecipeBuilderCompressing.builder(Items.SLIME_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.SLIME_BALL), 9)
                .addCriterion("slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
                .setGroup("compressing/block")
                .save(consumer,"slime_ball_slime_block");

        RecipeBuilderCompressing.builder(Items.CLAY, 1)
                .setIngredient(Ingredient.of(Items.CLAY_BALL), 4)
                .addCriterion("clay_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY_BALL))
                .setGroup("compressing/block")
                .save(consumer,"clay_ball_clay");

        RecipeBuilderCompressing.builder(Items.NETHER_BRICKS, 1)
                .setIngredient(Ingredient.of(Items.NETHER_BRICK), 4)
                .addCriterion("nether_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICK))
                .setGroup("compressing/block")
                .save(consumer,"nether_brick_nether_bricks");


        RecipeBuilderCompressing.builder(Items.BRICKS, 1)
                .setIngredient(Ingredient.of(Items.BRICK), 4)
                .addCriterion("brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
                .setGroup("compressing/block")
                .save(consumer,"brick_bricks");

        RecipeBuilderCompressing.builder(Items.ICE, 1)
                .setIngredient(Ingredient.of(Items.SNOW_BLOCK), 1)
                .addCriterion("snow_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOW_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"snow_ice");

        RecipeBuilderCompressing.builder(Items.SANDSTONE, 1)
                .setIngredient(Ingredient.of(Items.SAND), 4)
                .addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .setGroup("compressing/block")
                .save(consumer,"sand_sandstone");

        RecipeBuilderCompressing.builder(Items.PACKED_ICE, 1)
                .setIngredient(Ingredient.of(Items.ICE), 2)
                .addCriterion("ice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ICE))
                .setGroup("compressing/block")
                .save(consumer,"ice_packed_ice");

        RecipeBuilderCompressing.builder(Items.GLOWSTONE, 1)
                .setIngredient(Ingredient.of(Items.GLOWSTONE_DUST), 4)
                .addCriterion("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .setGroup("compressing/block")
                .save(consumer,"glowstone_dust_glowstone");

        RecipeBuilderCompressing.builder(Items.BLAZE_ROD, 1)
                .setIngredient(Ingredient.of(Items.BLAZE_POWDER), 5)
                .addCriterion("blaze_powder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_POWDER))
                .setGroup("compressing/items")
                .save(consumer,"blaze_powder_blaze_rod");

        RecipeBuilderCompressing.builder(Blocks.NETHERITE_BLOCK, 1)
                .setIngredient(Ingredient.of(Items.NETHERITE_INGOT), 9)
                .setExperience(0.5F)
                .addCriterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"netherite_ingot_netherite_block");

        RecipeBuilderCompressing.builder(ModItems.ADVANCED_ALLOY.get(), 1)
                .setIngredient(Ingredient.of(ModItems.MIXED_METAL_INGOT.get()), 1)
                .addCriterion("mixed_metal_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MIXED_METAL_INGOT.get()))
                .setGroup("compressing/items")
                .save(consumer,"advanced_alloy");

        RecipeBuilderCompressing.builder(ModItems.CARBON_PLATE.get(), 1)
                .setIngredient(Ingredient.of(ModItems.COMBINED_CARBON_FIBERS.get()), 1)
                .addCriterion("combined_carbon_fibers", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COMBINED_CARBON_FIBERS.get()))
                .setGroup("compressing/items")
                .save(consumer,"carbon_plate");

        RecipeBuilderCompressing.builder(ModItems.ENERGY_CRYSTAL.get(), 1)
                .setIngredient(Ingredient.of(ModItems.ENERGIUM_DUST.get()), 9)
                .setExperience(5.0f)
                .addCriterion("energium_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGIUM_DUST.get()))
                .setGroup("compressing/dust")
                .save(consumer,"energium_dust");

        RecipeBuilderCompressing.builder(Items.STONE, 1)
                .setIngredient(Ingredient.of(ModItems.STONE_DUST.get()), 4)
                .addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .setGroup("compressing/dust")
                .save(consumer,"stone");

        RecipeBuilderCompressing.builder(Items.DEEPSLATE, 1)
                .setIngredient(Ingredient.of(ModItems.DEEPSLATE_DUST.get()), 4)
                .addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE))
                .setGroup("compressing/dust")
                .save(consumer,"deepslate");

        RecipeBuilderCompressing.builder(ModItems.IRIDIUM.get(), 1)
                .setIngredient(Ingredient.of(ModItems.IRIDIUM_SHARD.get()), 9)
                .addCriterion("iridium_shard", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_SHARD.get()))
                .setExperience(1F)
                .setGroup("compressing")
                .save(consumer,"iridium");

        RecipeBuilderCompressing.builder(Items.MOSS_BLOCK,1)
                .setIngredient(Ingredient.of(ModItems.BIO_CHAFF.get()), 1)
                .setGroup("compressing")
                .addCriterion("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .save(consumer, "moss_block");

        RecipeBuilderCompressing.builder(ModItems.URANIUM_INGOT.get(),1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/uranium"))), 1)
                .setGroup("compressing")
                .setExperience(2F)
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .save(consumer, "uranium_ingot");

        RecipeBuilderCompressing.builder(Items.STONE,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/stone"))), 4)
                .setGroup("compressing/block")
                .setExperience(1F)
                .addCriterion("stone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STONE_DUST.get()))
                .save(consumer, "stone");

        RecipeBuilderCompressing.builder(Items.DEEPSLATE,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/deepslate"))), 4)
                .setGroup("compressing/block")
                .setExperience(1F)
                .addCriterion("deepslate_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_DUST.get()))
                .save(consumer, "deepslate");

        RecipeBuilderCompressing.builder(Items.PACKED_MUD,1)
                .setIngredient(Ingredient.of(Items.MUD), 1)
                .setGroup("compressing/block")
                .addCriterion("mud", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD))
                .save(consumer, "packed_mud");

        RecipeBuilderCompressing.builder(Items.AMETHYST_BLOCK,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "gems/amethyst"))), 4)
                .setGroup("compressing/block")
                .addCriterion("amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(consumer, "amethyst_block");

        RecipeBuilderCompressing.builder(Blocks.RAW_COPPER_BLOCK,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/copper"))), 9)
                .setGroup("compressing/block")
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, "raw_copper_block");

        RecipeBuilderCompressing.builder(Blocks.RAW_GOLD_BLOCK,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/gold"))), 9)
                .setGroup("compressing/block")
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .save(consumer, "raw_gold_block");

        RecipeBuilderCompressing.builder(Blocks.RAW_IRON_BLOCK,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/iron"))), 9)
                .setGroup("compressing/block")
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer, "raw_iron_block");

        RecipeBuilderCompressing.builder(Items.RED_SANDSTONE, 1)
                .setIngredient(Ingredient.of(Items.RED_SAND), 4)
                .addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SAND))
                .setGroup("compressing/block")
                .save(consumer,"red_sandstone");

        RecipeBuilderCompressing.builder(ModItems.LAPIS_LAZULI_PLATE.get(), 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "dusts/lapis")), 1)
                .addCriterion("lapis_lazuli_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPIS_LAZULI_DUST.get()))
                .setGroup("compressing/plate")
                .save(consumer,"lapis_lazuli_plate");
    }

}
