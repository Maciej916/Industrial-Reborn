package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCrushing;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class Crushing extends RecipeProvider {

    public Crushing(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        RecipeBuilderCrushing.builder(Items.BLAZE_POWDER,5)
                .setIngredient(Ingredient.of(Items.BLAZE_ROD), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
                .save(consumer, "blaze_rod_blaze_powder");

        RecipeBuilderCrushing.builder(Items.BONE_MEAL,14)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "bones"))), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("bone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE))
                .save(consumer, "bones_bone_meal");

        RecipeBuilderCrushing.builder(Items.CLAY_BALL,2)
                .setIngredient(Ingredient.of(Items.CLAY), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, "clay_clay_ball");

        RecipeBuilderCrushing.builder(Items.REDSTONE,9)
                .setIngredient(Ingredient.of(Items.REDSTONE_BLOCK), 1)
                .setGroup("crushing")
                .addCriterion("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE_BLOCK))
                .save(consumer, "redstone_block_redstone");

        RecipeBuilderCrushing.builder(Items.GUNPOWDER,1)
                .setIngredient(Ingredient.of(Items.FLINT), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("flint", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
                .save(consumer, "flint_gunpowder");

        RecipeBuilderCrushing.builder(Items.FLINT,1)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "gravel"))), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL))
                .save(consumer, "gravel_flint");

        RecipeBuilderCrushing.builder(Items.QUARTZ,4)
                .setIngredient(Ingredient.of(Items.SMOOTH_QUARTZ), 1)
                .setGroup("crushing")
                .addCriterion("smooth_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SMOOTH_QUARTZ))
                .save(consumer, "smooth_quartz_quartz");

        RecipeBuilderCrushing.builder(Items.SUGAR,1)
                .setIngredient(Ingredient.of(Items.SUGAR_CANE), 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("sugar_cane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR_CANE))
                .save(consumer, "sugarcane_sugar");

        RecipeBuilderCrushing.builder(Items.STRING,4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("wool"))), 1)
                .setGroup("crushing")
                .addCriterion("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .save(consumer, "wool_string");

        // Bricks

        RecipeBuilderCrushing.builder(Items.BRICK,4)
                .setIngredient(Ingredient.of(Items.BRICKS), 1)
                .setGroup("crushing/bricks")
                .addCriterion("bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICKS))
                .save(consumer, "bricks_brick");

        // Stones

        RecipeBuilderCrushing.builder(Items.GRAVEL,1)
                .setIngredient(Ingredient.of(Items.COBBLESTONE), 1)
                .setGroup("crushing/stones")
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(consumer, "cobblestone_gravel");

        RecipeBuilderCrushing.builder(Items.GLOWSTONE_DUST,4)
                .setIngredient(Ingredient.of(Items.GLOWSTONE), 1)
                .setGroup("crushing/stones")
                .addCriterion("glowstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE))
                .save(consumer, "glowstone_glowstone_dust");

        RecipeBuilderCrushing.builder(Items.COBBLESTONE,1)
                .setIngredient(Ingredient.of(Items.MOSSY_COBBLESTONE), 1)
                .setBonus(Items.VINE, 1, 80)
                .setGroup("crushing/stones")
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(consumer, "mossy_cobblestone_cobblestone");

        RecipeBuilderCrushing.builder(Items.RED_SAND,2)
                .setIngredient(Ingredient.of(Items.RED_SANDSTONE), 1)
                .setGroup("crushing/stones")
                .addCriterion("red_sandstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SANDSTONE))
                .save(consumer,"red_sandstone_red_sand");

        RecipeBuilderCrushing.builder(Items.SAND,2)
                .setIngredient(Ingredient.of(Items.SANDSTONE), 1)
                .setGroup("crushing/stones")
                .addCriterion("sandstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SANDSTONE))
                .save(consumer, "sandstone_sand");

        RecipeBuilderCrushing.builder(Items.STONE,4)
                .setIngredient(Ingredient.of(Items.STONE_BRICKS), 1)
                .setGroup("crushing/stones")
                .addCriterion("stone_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_BRICKS))
                .save(consumer, "stone_bricks_stone");

        RecipeBuilderCrushing.builder(Items.COBBLESTONE,1)
                .setIngredient(Ingredient.of(Items.STONE), 1)
                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
                .setGroup("crushing/stones")
                .addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .save(consumer, "stone_cobblestone");

        RecipeBuilderCrushing.builder(Items.COBBLED_DEEPSLATE,1)
                .setIngredient(Ingredient.of(Items.DEEPSLATE), 1)
                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
                .setGroup("crushing/stones")
                .addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE))
                .save(consumer, "deepslate_cobbled_deepslate");

        // Ores
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_COPPER.get(),2)
//                .setIngredient(Ingredient.of(Items.COPPER_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(Items.COBBLESTONE, 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE.asItem()))
//                .save(consumer, "copper_ore_crushed_copper");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_TIN.get(),2)
//                .setIngredient(ModItems.TIN_ORE.get(), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
//                .save(consumer, "tin_ore_crushed_tin");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_LEAD.get(),2)
//                .setIngredient(ModItems.LEAD_ORE.get(), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
//                .save(consumer, "lead_ore_crushed_lead");
//
//        RecipeBuilderCrushing.builder(Items.DIAMOND,2)
//                .setIngredient(Items.DIAMOND_ORE, 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
//                .save(consumer,"diamond_ore_diamond");
//
//        RecipeBuilderCrushing.builder(Items.EMERALD,2)
//                .setIngredient(Items.EMERALD_ORE, 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("emerald", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD))
//                .save(consumer, "emerald_ore_emerald");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_GOLD.get(),2)
//                .setIngredient(Items.GOLD_ORE, 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
//                .save(consumer, "gold_ore_crushed_gold");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_IRON.get(),2)
//                .setIngredient(Items.IRON_ORE, 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.STONE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
//                .save(consumer, "iron_ore_crushed_iron");
//
//        // Deepslate Ores
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_COPPER.get(),2)
//                .setIngredient(Ingredient.of(Items.DEEPSLATE_COPPER_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE.asItem()))
//                .save(consumer, "deepslate_copper_ore_crushed_copper");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_TIN.get(),2)
//                .setIngredient(Ingredient.of(ModBlocks.DEEPSLATE_TIN_ORE.get()), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
//                .save(consumer, "deepslate_tin_ore_crushed_tin");
//
//        RecipeBuilderCrushing.builder(Items.DIAMOND,2)
//                .setIngredient(Ingredient.of(Items.DEEPSLATE_DIAMOND_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_diamond_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_DIAMOND_ORE))
//                .save(consumer,"deepslate_diamond_ore_diamond");
//
//        RecipeBuilderCrushing.builder(Items.EMERALD,2)
//                .setIngredient(Ingredient.of(Items.DEEPSLATE_EMERALD_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_emerald_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_EMERALD_ORE))
//                .save(consumer, "deepslate_emerald_ore_emerald");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_GOLD.get(),2)
//                .setIngredient(Ingredient.of(Items.DEEPSLATE_GOLD_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
//                .save(consumer, "deepslate_gold_ore_crushed_gold");
//
//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_IRON.get(),2)
//                .setIngredient(Ingredient.of(Items.DEEPSLATE_IRON_ORE), 1)
//                .setExperience(0.2F)
//                .setBonus(ModItems.DEEPSLATE_DUST.get(), 1, 50)
//                .setGroup("crushing/ores")
//                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
//                .save(consumer, "deepslate_iron_ore_crushed_iron");


        // Flowers

        RecipeBuilderCrushing.builder(Items.MAGENTA_DYE,1)
                .setIngredient(Ingredient.of(Items.ALLIUM), 1)
                .setExperience(0.1F)
                .setBonus(Items.MAGENTA_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("allium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ALLIUM))
                .save(consumer, "allium_magenta_dye");

        RecipeBuilderCrushing.builder(Items.LIGHT_GRAY_DYE,1)
                .setIngredient(Ingredient.of(Items.AZURE_BLUET), 1)
                .setExperience(0.1F)
                .setBonus(Items.LIGHT_GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("azure_bluet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AZURE_BLUET))
                .save(consumer, "azure_bluet_light_gray_dye");

        RecipeBuilderCrushing.builder(Items.LIGHT_BLUE_DYE,1)
                .setIngredient(Ingredient.of(Items.BLUE_ORCHID), 1)
                .setExperience(0.1F)
                .setBonus(Items.LIGHT_BLUE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("blue_orchid", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_ORCHID))
                .save(consumer, "blue_orchid_light_blue_dye");

        RecipeBuilderCrushing.builder(Items.BLUE_DYE,1)
                .setIngredient(Ingredient.of(Items.CORNFLOWER), 1)
                .setExperience(0.1F)
                .setBonus(Items.BLUE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("cornflower", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CORNFLOWER))
                .save(consumer, "cornflower_blue_dye");

        RecipeBuilderCrushing.builder(Items.YELLOW_DYE,1)
                .setIngredient(Ingredient.of(Items.DANDELION), 1)
                .setExperience(0.1F)
                .setBonus(Items.YELLOW_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("dandelion", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DANDELION))
                .save(consumer, "dandelion_yellow_dye");

        RecipeBuilderCrushing.builder(Items.MAGENTA_DYE,2)
                .setIngredient(Ingredient.of(Items.LILAC), 1)
                .setExperience(0.1F)
                .setBonus(Items.MAGENTA_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("lilac", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LILAC))
                .save(consumer, "lilac_magenta_dye");

        RecipeBuilderCrushing.builder(Items.WHITE_DYE,1)
                .setIngredient(Ingredient.of(Items.LILY_OF_THE_VALLEY), 1)
                .setExperience(0.1F)
                .setBonus(Items.WHITE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("lily_of_the_valley", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LILY_OF_THE_VALLEY))
                .save(consumer, "lily_of_the_valley_white_dye");

        RecipeBuilderCrushing.builder(Items.ORANGE_DYE,1)
                .setIngredient(Ingredient.of(Items.ORANGE_TULIP), 1)
                .setExperience(0.1F)
                .setBonus(Items.ORANGE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("orange_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ORANGE_TULIP))
                .save(consumer, "orange_tulip_orange_dye");

        RecipeBuilderCrushing.builder(Items.LIGHT_GRAY_DYE,1)
                .setIngredient(Ingredient.of(Items.OXEYE_DAISY), 1)
                .setExperience(0.1F)
                .setBonus(Items.LIGHT_GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("oxeye_daisy", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OXEYE_DAISY))
                .save(consumer, "oxeye_daisy_light_gray_dye");

        RecipeBuilderCrushing.builder(Items.PINK_DYE,2)
                .setIngredient(Ingredient.of(Items.PEONY), 1)
                .setExperience(0.1F)
                .setBonus(Items.PINK_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("peony", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PEONY))
                .save(consumer, "peony_pink_dye");

        RecipeBuilderCrushing.builder(Items.PINK_DYE,1)
                .setIngredient(Ingredient.of(Items.PINK_TULIP), 1)
                .setExperience(0.1F)
                .setBonus(Items.PINK_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("pink_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_TULIP))
                .save(consumer, "pink_tulip_pink_dye");

        RecipeBuilderCrushing.builder(Items.RED_DYE,1)
                .setIngredient(Ingredient.of(Items.POPPY), 1)
                .setExperience(0.1F)
                .setBonus(Items.RED_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("pink_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_TULIP))
                .save(consumer, "poppy_red_dye");

        RecipeBuilderCrushing.builder(Items.RED_DYE,1)
                .setIngredient(Ingredient.of(Items.RED_TULIP), 1)
                .setExperience(0.1F)
                .setBonus(Items.RED_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("red_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_TULIP))
                .save(consumer, "red_tulip_red_dye");

        RecipeBuilderCrushing.builder(Items.RED_DYE,2)
                .setIngredient(Ingredient.of(Items.ROSE_BUSH), 1)
                .setExperience(0.1F)
                .setBonus(Items.RED_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("rose_bush", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROSE_BUSH))
                .save(consumer, "rose_bush_red_dye");

        RecipeBuilderCrushing.builder(Items.YELLOW_DYE,2)
                .setIngredient(Ingredient.of(Items.SUNFLOWER), 1)
                .setExperience(0.1F)
                .setBonus(Items.YELLOW_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("sunflower", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUNFLOWER))
                .save(consumer, "sunflower_yellow_dye");

        RecipeBuilderCrushing.builder(Items.GRAY_DYE,1)
                .setIngredient(Ingredient.of(Items.WHITE_TULIP), 1)
                .setExperience(0.1F)
                .setBonus(Items.GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("white_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_TULIP))
                .save(consumer, "white_tulip_light_gray_dye");

        RecipeBuilderCrushing.builder(Items.BLACK_DYE,1)
                .setIngredient(Ingredient.of(Items.WITHER_ROSE), 1)
                .setExperience(0.1F)
                .setBonus(Items.BLACK_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("wither_rose", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WITHER_ROSE))
                .save(consumer, "wither_rose_black_dye");

        // Dust

        RecipeBuilderCrushing.builder(ModItems.COAL_DUST.get(),1)
                .setIngredient(Ingredient.of(Items.COAL), 1)
                .setExperience(0.1F)
                .setGroup("crushing/dust")
                .addCriterion("coal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .save(consumer, "coal_coal_dust");

//        RecipeBuilderCrushing.builder(ModItems.CRUSHED_GOLD.get(),3)
//                .setIngredient(Ingredient.of(Items.NETHER_GOLD_ORE), 1)
//                .setExperience(0.4F)
//                .setBonus(Items.NETHERRACK, 1, 75)
//                .setGroup("crushing/ores")
//                .addCriterion("nether_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_GOLD_ORE))
//                .save(consumer, "nether_gold_crushed_gold");

        RecipeBuilderCrushing.builder(Items.NETHERITE_SCRAP,2)
                .setIngredient(Ingredient.of(Items.ANCIENT_DEBRIS), 1)
                .setExperience(2.0F)
                .setGroup("crushing/ores")
                .addCriterion("ANCient_debris", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANCIENT_DEBRIS))
                .save(consumer, "ancient_debris_netherite_scrap");

        RecipeBuilderCrushing.builder(ModItems.DIAMOND_DUST.get(),1)
                .setIngredient(Ingredient.of(Items.DIAMOND), 1)
                .setExperience(2.0F)
                .setGroup("crushing/items")
                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(consumer, "diamond_dust");

        RecipeBuilderCrushing.builder(ModItems.IRIDIUM_SHARD.get(),9)
                .setIngredient(Ingredient.of(ModItems.IRIDIUM.get()), 1)
                .setExperience(1F)
                .setGroup("crushing/items")
                .addCriterion("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM.get()))
                .save(consumer, "iridium_shard");

        RecipeBuilderCrushing.builder(Items.DIRT,1)
                .setIngredient(ModItems.BIO_CHAFF.get(), 1)
                .setGroup("crushing/bio")
                .addCriterion("bio_chaff", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .save(consumer, "dirt");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(ItemTags.create(new ResourceLocation("saplings")), 4)
                .setGroup("crushing/bio")
                .addCriterion("oak_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_SAPLING))
                .save(consumer, "saplings");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(ItemTags.create(new ResourceLocation("leaves")), 8)
                .setGroup("crushing/bio")
                .addCriterion("oak_leaves", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LEAVES))
                .save(consumer, "leaves");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.WHEAT, 8)
                .setGroup("crushing/bio")
                .addCriterion("wheat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
                .save(consumer, "wheat");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.CACTUS, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CACTUS))
                .save(consumer, "cactus");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.SUGAR_CANE, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR_CANE))
                .save(consumer, "sugar_cane");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.CARROT, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARROT))
                .save(consumer, "carrot");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.POTATO, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .save(consumer, "potato");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.MELON_SLICE, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
                .save(consumer, "melon_slice");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.PUMPKIN, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN))
                .save(consumer, "pumpkin");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.WHEAT_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT_SEEDS))
                .save(consumer, "wheat_seeds");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.MELON_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SEEDS))
                .save(consumer, "melon_seeds");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.PUMPKIN_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN_SEEDS))
                .save(consumer, "pumpkin_seeds");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.BEETROOT_SEEDS, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEETROOT_SEEDS))
                .save(consumer, "beetroot_seeds");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.VINE, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.VINE))
                .save(consumer, "vine");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.BAMBOO, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAMBOO))
                .save(consumer, "bamboo");

        RecipeBuilderCrushing.builder(ModItems.BIO_CHAFF.get(),1)
                .setIngredient(Items.KELP, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.KELP))
                .save(consumer, "kelp");


        // RAW

        RecipeBuilderCrushing.builder(ModItems.IRON_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/iron"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer, "iron_dust");

        RecipeBuilderCrushing.builder(ModItems.COPPER_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/copper"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, "copper_dust");

        RecipeBuilderCrushing.builder(ModItems.GOLD_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/gold"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .save(consumer, "gold_dust");

        RecipeBuilderCrushing.builder(ModItems.TIN_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/tin"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .save(consumer, "tin_dust");

        RecipeBuilderCrushing.builder(ModItems.URANIUM_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/uranium"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .save(consumer, "uranium_dust");

        RecipeBuilderCrushing.builder(ModItems.LEAD_DUST.get(),4)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "raw_materials/lead"))), 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .save(consumer, "lead_dust");

        // ORES

        RecipeBuilderCrushing.builder(ModItems.IRON_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/iron"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .save(consumer, "iron_dust");

        RecipeBuilderCrushing.builder(ModItems.COPPER_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/copper"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .save(consumer, "copper_dust");

        RecipeBuilderCrushing.builder(ModItems.GOLD_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/gold"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .save(consumer, "gold_dust");

        RecipeBuilderCrushing.builder(ModItems.TIN_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/tin"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .save(consumer, "tin_dust");

        RecipeBuilderCrushing.builder(ModItems.URANIUM_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/uranium"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .save(consumer, "uranium_dust");

        RecipeBuilderCrushing.builder(ModItems.LEAD_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/lead"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .save(consumer, "lead_dust");

        RecipeBuilderCrushing.builder(ModItems.SILVER_DUST.get(),2)
                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ores/silver"))), 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .save(consumer, "silver_dust");

        // Purified

        RecipeBuilderCrushing.builder(ModItems.IRON_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_IRON.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("iron_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_IRON.get()))
                .save(consumer, "purified_iron");

        RecipeBuilderCrushing.builder(ModItems.COPPER_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_COPPER.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_COPPER.get()))
                .save(consumer, "copper_dust");

        RecipeBuilderCrushing.builder(ModItems.GOLD_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_GOLD.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_gold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_GOLD.get()))
                .save(consumer, "gold_dust");

        RecipeBuilderCrushing.builder(ModItems.TIN_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_TIN.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_TIN.get()))
                .save(consumer, "tin_dust");

        RecipeBuilderCrushing.builder(ModItems.URANIUM_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_URANIUM.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_URANIUM.get()))
                .save(consumer, "uranium_dust");

        RecipeBuilderCrushing.builder(ModItems.LEAD_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_LEAD.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_LEAD.get()))
                .save(consumer, "lead_dust");

        RecipeBuilderCrushing.builder(ModItems.SILVER_DUST.get(),1)
                .setIngredient(Ingredient.of(ModItems.PURIFIED_SILVER.get()), 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_SILVER.get()))
                .save(consumer, "silver_dust");



    RecipeBuilderCrushing.builder(ModItems.LAPIS_LAZULI_DUST.get(),1)
                .setIngredient(ItemTags.create(new ResourceLocation("forge", "gems/lapis")), 1)
                .setExperience(0.4F)
                .setGroup("crushing")
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .save(consumer, "lapis_lazuli_dust");




//        RecipeBuilderCrushing.builder(ModItems.PURIFIED_IRON_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/iron"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_DUST.get()))
//                .save(consumer, "purified_iron_dust");
//
//        RecipeBuilderCrushing.builder(ModItems.PURIFIED_COPPER_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/copper"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COPPER_DUST.get()))
//                .save(consumer, "purified_copper_dust");
//
//        RecipeBuilderCrushing.builder(ModItems.GOLD_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/gold"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLD_DUST.get()))
//                .save(consumer, "gold_dust");
//
//        RecipeBuilderCrushing.builder(ModItems.PURIFIED_TIN_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/tin"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_DUST.get()))
//                .save(consumer, "purified_tin_dust");
//
//        RecipeBuilderCrushing.builder(ModItems.PURIFIED_URANIUM_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/uranium"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_DUST.get()))
//                .save(consumer, "purified_uranium_dust");
//
//        RecipeBuilderCrushing.builder(ModItems.PURIFIED_LEAD_DUST.get(),1)
//                .setIngredient(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "dusts/lead"))), 1)
//                .setExperience(0.2F)
//                .setGroup("crushing/purified")
//                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_DUST.get()))
//                .save(consumer, "purified_lead_dust");


    }

}
