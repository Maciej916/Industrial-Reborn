package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.CompressingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class CompressingRecipeProvider extends RecipeProvider {

    public CompressingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        CompressingRecipeBuilder.builder(Items.IRON_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_IRON, 9)
                .setExperience(0.3F)
                .addCriterion("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"iron_ingot_iron_block");

        CompressingRecipeBuilder.builder(Items.GOLD_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_GOLD, 9)
                .setExperience(0.4F)
                .addCriterion("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"gold_ingot_gold_ore");

        CompressingRecipeBuilder.builder(Items.DIAMOND_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_GEMS_DIAMOND, 9)
                .setExperience(0.8F)
                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .setGroup("compressing/block")
                .save(consumer,"diamond_diamond_block");

        CompressingRecipeBuilder.builder(Items.EMERALD_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_GEMS_EMERALD, 9)
                .setExperience(0.6F)
                .addCriterion("emerald", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD))
                .setGroup("compressing/block")
                .save(consumer,"emerald_emerald_block");

        CompressingRecipeBuilder.builder(Items.COPPER_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_COPPER, 9)
                .setExperience(0.3F)
                .addCriterion("copper_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"copper_copper_block");

        CompressingRecipeBuilder.builder(ModItems.TIN_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_TIN, 9)
                .setExperience(0.3F)
                .addCriterion("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"tin_tin_block");

        CompressingRecipeBuilder.builder(ModItems.SILVER_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_SILVER, 9)
                .setExperience(0.3F)
                .addCriterion("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"silver_block");

        CompressingRecipeBuilder.builder(ModItems.BRONZE_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_BRONZE, 9)
                .setExperience(0.3F)
                .addCriterion("bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"bronze_block");

        CompressingRecipeBuilder.builder(ModItems.URANIUM_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_URANIUM, 9)
                .setExperience(0.3F)
                .addCriterion("uranium_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"uranium_block");

        CompressingRecipeBuilder.builder(ModItems.STEEL_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_STEEL, 9)
                .setExperience(0.3F)
                .addCriterion("steel_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"steel_block");

        CompressingRecipeBuilder.builder(ModItems.LEAD_BLOCK, 1)
                .setIngredient(ModItemTags.FORGE_INGOTS_LEAD, 9)
                .setExperience(0.3F)
                .addCriterion("lead_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_INGOT.get()))
                .setGroup("compressing/block")
                .save(consumer,"lead_block");

        CompressingRecipeBuilder.builder(Items.REDSTONE_BLOCK, 1)
                .setIngredient(Items.REDSTONE, 9)
                .setExperience(0.1F)
                .addCriterion("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .setGroup("compressing/block")
                .save(consumer,"redstone_redstone_block");

        CompressingRecipeBuilder.builder(Items.LAPIS_BLOCK, 1)
                .setIngredient(Items.LAPIS_LAZULI, 9)
                .setExperience(0.1F)
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .setGroup("compressing/block")
                .save(consumer,"lapis_lapis_block");

        CompressingRecipeBuilder.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Items.WATER_BUCKET, 1)
                .addChanceResult(Items.BUCKET, 1, 100)
                .addCriterion("water_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WATER_BUCKET))
                .setGroup("compressing/block")
                .save(consumer,"water_bucket_snow_block");

        CompressingRecipeBuilder.builder(Items.SNOW_BLOCK, 1)
                .setIngredient(Items.SNOWBALL, 4)
                .addCriterion("snowball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOWBALL))
                .setGroup("compressing/block")
                .save(consumer,"snowball_snow_block");

        CompressingRecipeBuilder.builder(Items.COAL_BLOCK, 1)
                .setIngredient(Items.COAL, 9)
                .setExperience(0.1F)
                .addCriterion("coal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .setGroup("compressing/block")
                .save(consumer,"coal_coal_block");

        CompressingRecipeBuilder.builder(Items.QUARTZ_BLOCK, 1)
                .setIngredient(Items.QUARTZ, 4)
                .addCriterion("quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .setGroup("compressing/block")
                .save(consumer,"quartz_quartz_block");

        CompressingRecipeBuilder.builder(Items.BONE_BLOCK, 1)
                .setIngredient(Items.BONE_MEAL, 9)
                .addCriterion("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .setGroup("compressing/block")
                .save(consumer,"bone_meal_bone_block");

        CompressingRecipeBuilder.builder(Items.HONEY_BLOCK, 1)
                .setIngredient(Items.HONEY_BOTTLE, 4)
                .setExperience(0.2F)
                .addChanceResult(Items.GLASS_BOTTLE, 4, 100)
                .addCriterion("honey_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEY_BOTTLE))
                .setGroup("compressing/block")
                .save(consumer,"honey_bottle_honey_block");

        CompressingRecipeBuilder.builder(Items.HONEYCOMB_BLOCK, 1)
                .setIngredient(Items.HONEYCOMB, 4)
                .addCriterion("honeycomb", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                .setGroup("compressing/block")
                .save(consumer,"honeycomb_honey_block");

        CompressingRecipeBuilder.builder(Items.SLIME_BLOCK, 1)
                .setIngredient(Items.SLIME_BALL, 9)
                .addCriterion("slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
                .setGroup("compressing/block")
                .save(consumer,"slime_ball_slime_block");

        CompressingRecipeBuilder.builder(Items.CLAY, 1)
                .setIngredient(Items.CLAY_BALL, 4)
                .addCriterion("clay_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY_BALL))
                .setGroup("compressing/block")
                .save(consumer,"clay_ball_clay");

        CompressingRecipeBuilder.builder(Items.NETHER_BRICKS, 1)
                .setIngredient(Items.NETHER_BRICK, 4)
                .addCriterion("nether_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICK))
                .setGroup("compressing/block")
                .save(consumer,"nether_brick_nether_bricks");

        CompressingRecipeBuilder.builder(Items.BRICKS, 1)
                .setIngredient(Items.BRICK, 4)
                .addCriterion("brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
                .setGroup("compressing/block")
                .save(consumer,"brick_bricks");

        CompressingRecipeBuilder.builder(Items.ICE, 1)
                .setIngredient(Items.SNOW_BLOCK, 1)
                .addCriterion("snow_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SNOW_BLOCK))
                .setGroup("compressing/block")
                .save(consumer,"snow_ice");

        CompressingRecipeBuilder.builder(Items.SANDSTONE, 1)
                .setIngredient(Items.SAND, 4)
                .addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .setGroup("compressing/block")
                .save(consumer,"sand_sandstone");

        CompressingRecipeBuilder.builder(Items.PACKED_ICE, 1)
                .setIngredient(Items.ICE, 2)
                .addCriterion("ice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ICE))
                .setGroup("compressing/block")
                .save(consumer,"ice_packed_ice");

        CompressingRecipeBuilder.builder(Items.GLOWSTONE, 1)
                .setIngredient(Items.GLOWSTONE_DUST, 4)
                .addCriterion("glowstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE_DUST))
                .setGroup("compressing/block")
                .save(consumer,"glowstone_dust_glowstone");

        CompressingRecipeBuilder.builder(Items.BLAZE_ROD, 1)
                .setIngredient(Items.BLAZE_POWDER, 3)
                .addCriterion("blaze_powder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_POWDER))
                .setGroup("compressing/items")
                .save(consumer,"blaze_powder_blaze_rod");

        CompressingRecipeBuilder.builder(Blocks.NETHERITE_BLOCK, 1)
                .setIngredient(Items.NETHERITE_INGOT, 9)
                .setExperience(0.5F)
                .addCriterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .setGroup("compressing/block")
                .save(consumer,"netherite_ingot_netherite_block");

        CompressingRecipeBuilder.builder(ModItems.ADVANCED_ALLOY, 1)
                .setIngredient(ModItems.MIXED_METAL_INGOT, 1)
                .addCriterion("mixed_metal_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MIXED_METAL_INGOT.get()))
                .setGroup("compressing/items")
                .save(consumer,"advanced_alloy");

        CompressingRecipeBuilder.builder(ModItems.CARBON_PLATE, 1)
                .setIngredient(ModItems.COMBINED_CARBON_FIBERS, 1)
                .addCriterion("combined_carbon_fibers", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COMBINED_CARBON_FIBERS.get()))
                .setGroup("compressing/items")
                .save(consumer,"carbon_plate");

        CompressingRecipeBuilder.builder(ModItems.ENERGY_CRYSTAL, 1)
                .setIngredient(ModItems.ENERGIUM_DUST, 9)
                .setExperience(5.0f)
                .addCriterion("energium_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGIUM_DUST.get()))
                .setGroup("compressing/dust")
                .save(consumer,"energium_dust");

        CompressingRecipeBuilder.builder(ModItems.IRIDIUM, 1)
                .setIngredient(ModItems.IRIDIUM_SHARD, 9)
                .addCriterion("iridium_shard", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_SHARD.get()))
                .setExperience(1F)
                .setGroup("compressing")
                .save(consumer,"iridium");

        CompressingRecipeBuilder.builder(Items.MOSS_BLOCK,1)
                .setIngredient(ModItems.BIO_CHAFF, 1)
                .setGroup("compressing")
                .addCriterion("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .save(consumer, "moss_block");

        CompressingRecipeBuilder.builder(Items.STONE,1)
                .setIngredient(ModItemTags.FORGE_DUSTS_STONE, 4)
                .setGroup("compressing/block")
                .setExperience(1F)
                .addCriterion("stone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STONE_DUST.get()))
                .save(consumer, "stone");

        CompressingRecipeBuilder.builder(Items.DEEPSLATE,1)
                .setIngredient(ModItemTags.FORGE_DUSTS_DEEPSLATE, 4)
                .setGroup("compressing/block")
                .setExperience(1F)
                .addCriterion("deepslate_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_DUST.get()))
                .save(consumer, "deepslate");

        CompressingRecipeBuilder.builder(Items.PACKED_MUD,1)
                .setIngredient(Items.MUD, 1)
                .setGroup("compressing/block")
                .addCriterion("mud", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUD))
                .save(consumer, "packed_mud");

        CompressingRecipeBuilder.builder(Items.AMETHYST_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_GEMS_AMETHYST, 4)
                .setGroup("compressing/block")
                .addCriterion("amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(consumer, "amethyst_block");


        CompressingRecipeBuilder.builder(Blocks.RAW_COPPER_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_COPPER, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, "raw_copper_block");

        CompressingRecipeBuilder.builder(Blocks.RAW_GOLD_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_GOLD, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .save(consumer, "raw_gold_block");

        CompressingRecipeBuilder.builder(Blocks.RAW_IRON_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_IRON, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer, "raw_iron_block");

        CompressingRecipeBuilder.builder(ModItems.RAW_LEAD_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_LEAD, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .save(consumer, "raw_lead_block");

        CompressingRecipeBuilder.builder(ModItems.RAW_SILVER_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_SILVER, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_SILVER.get()))
                .save(consumer, "raw_silver_block");

        CompressingRecipeBuilder.builder(ModItems.RAW_TIN_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_TIN, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .save(consumer, "raw_tin_block");

        CompressingRecipeBuilder.builder(ModItems.RAW_URANIUM_BLOCK,1)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_URANIUM, 9)
                .setGroup("compressing/raw")
                .addCriterion("raw_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .save(consumer, "raw_uranium_block");

        CompressingRecipeBuilder.builder(Items.RED_SANDSTONE, 1)
                .setIngredient(Items.RED_SAND, 4)
                .addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SAND))
                .setGroup("compressing/block")
                .save(consumer,"red_sandstone");

        CompressingRecipeBuilder.builder(ModItems.LAPIS_LAZULI_PLATE, 1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "dusts/lapis")), 9)
                .addCriterion("lapis_lazuli_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LAPIS_LAZULI_DUST.get()))
                .setGroup("compressing/plate")
                .save(consumer,"lapis_lazuli_plate");
        

    }

}
