package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.CrushingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CrushingRecipeProvider extends RecipeProvider {

    public CrushingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        CrushingRecipeBuilder.builder(Items.BLAZE_POWDER,3)
                .setIngredient(Items.BLAZE_ROD, 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
                .save(consumer, "blaze_rod_blaze_powder");

        CrushingRecipeBuilder.builder(Items.BONE_MEAL,14)
                .setIngredient(ModItemTags.FORGE_BONES, 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("bone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE))
                .save(consumer, "bones_bone_meal");

        CrushingRecipeBuilder.builder(Items.CLAY_BALL,2)
                .setIngredient(Items.CLAY, 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY))
                .save(consumer, "clay_clay_ball");

        CrushingRecipeBuilder.builder(Items.REDSTONE,9)
                .setIngredient(Items.REDSTONE_BLOCK, 1)
                .setGroup("crushing")
                .addCriterion("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE_BLOCK))
                .save(consumer, "redstone_block_redstone");

        CrushingRecipeBuilder.builder(Items.GUNPOWDER,1)
                .setIngredient(Items.FLINT, 3)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("flint", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
                .save(consumer, "flint_gunpowder");

        CrushingRecipeBuilder.builder(Items.FLINT,1)
                .setIngredient(ModItemTags.FORGE_GRAVEL, 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL))
                .save(consumer, "gravel_flint");

        CrushingRecipeBuilder.builder(Items.QUARTZ,4)
                .setIngredient(Items.SMOOTH_QUARTZ, 1)
                .setGroup("crushing")
                .addCriterion("smooth_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SMOOTH_QUARTZ))
                .save(consumer, "smooth_quartz_quartz");

        CrushingRecipeBuilder.builder(Items.SUGAR,2)
                .setIngredient(Items.SUGAR_CANE, 1)
                .setExperience(0.1F)
                .setGroup("crushing")
                .addCriterion("sugar_cane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR_CANE))
                .save(consumer, "sugarcane_sugar");

        CrushingRecipeBuilder.builder(Items.STRING,4)
                .setIngredient(ItemTags.WOOL, 1)
                .setGroup("crushing")
                .addCriterion("white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .save(consumer, "wool_string");

        
        /* BRICKS */
        
        CrushingRecipeBuilder.builder(Items.BRICK,4)
                .setIngredient(Items.BRICKS, 1)
                .setGroup("crushing/bricks")
                .addCriterion("bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICKS))
                .save(consumer, "bricks_brick");


        /* STONES */

        CrushingRecipeBuilder.builder(Items.GRAVEL,1)
                .setIngredient(Items.COBBLESTONE, 1)
                .setGroup("crushing/stones")
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(consumer, "cobblestone_gravel");

        CrushingRecipeBuilder.builder(Items.GLOWSTONE_DUST,4)
                .setIngredient(Items.GLOWSTONE, 1)
                .setGroup("crushing/stones")
                .addCriterion("glowstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOWSTONE))
                .save(consumer, "glowstone_glowstone_dust");

        CrushingRecipeBuilder.builder(Items.COBBLESTONE,1)
                .setIngredient(Items.MOSSY_COBBLESTONE, 1)
                .addChanceResult(Items.VINE, 1, 80)
                .setGroup("crushing/stones")
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(consumer, "mossy_cobblestone_cobblestone");

        CrushingRecipeBuilder.builder(Items.RED_SAND,2)
                .setIngredient(Items.RED_SANDSTONE, 1)
                .setGroup("crushing/stones")
                .addCriterion("red_sandstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_SANDSTONE))
                .save(consumer,"red_sandstone_red_sand");

        CrushingRecipeBuilder.builder(Items.SAND,2)
                .setIngredient(Items.SANDSTONE, 1)
                .setGroup("crushing/stones")
                .addCriterion("sandstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SANDSTONE))
                .save(consumer, "sandstone_sand");

        CrushingRecipeBuilder.builder(Items.STONE,4)
                .setIngredient(Items.STONE_BRICKS, 1)
                .setGroup("crushing/stones")
                .addCriterion("stone_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_BRICKS))
                .save(consumer, "stone_bricks_stone");

        CrushingRecipeBuilder.builder(Items.COBBLESTONE,1)
                .setIngredient(Items.STONE, 1)
                .addChanceResult(ModItems.STONE_DUST, 1, 25)
                .setGroup("crushing/stones")
                .addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .save(consumer, "stone_cobblestone");

        CrushingRecipeBuilder.builder(Items.COBBLED_DEEPSLATE,1)
                .setIngredient(Items.DEEPSLATE, 1)
                .addChanceResult(ModItems.DEEPSLATE_DUST, 1, 25)
                .setGroup("crushing/stones")
                .addCriterion("deepslate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE))
                .save(consumer, "deepslate_cobbled_deepslate");


        /* FLOWERS */
        
        CrushingRecipeBuilder.builder(Items.MAGENTA_DYE,1)
                .setIngredient(Items.ALLIUM, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.MAGENTA_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("allium", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ALLIUM))
                .save(consumer, "allium_magenta_dye");

        CrushingRecipeBuilder.builder(Items.LIGHT_GRAY_DYE,1)
                .setIngredient(Items.AZURE_BLUET, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.LIGHT_GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("azure_bluet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AZURE_BLUET))
                .save(consumer, "azure_bluet_light_gray_dye");

        CrushingRecipeBuilder.builder(Items.LIGHT_BLUE_DYE,1)
                .setIngredient(Items.BLUE_ORCHID, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.LIGHT_BLUE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("blue_orchid", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_ORCHID))
                .save(consumer, "blue_orchid_light_blue_dye");

        CrushingRecipeBuilder.builder(Items.BLUE_DYE,1)
                .setIngredient(Items.CORNFLOWER, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.BLUE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("cornflower", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CORNFLOWER))
                .save(consumer, "cornflower_blue_dye");

        CrushingRecipeBuilder.builder(Items.YELLOW_DYE,1)
                .setIngredient(Items.DANDELION, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.YELLOW_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("dandelion", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DANDELION))
                .save(consumer, "dandelion_yellow_dye");

        CrushingRecipeBuilder.builder(Items.MAGENTA_DYE,2)
                .setIngredient(Items.LILAC, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.MAGENTA_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("lilac", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LILAC))
                .save(consumer, "lilac_magenta_dye");

        CrushingRecipeBuilder.builder(Items.WHITE_DYE,1)
                .setIngredient(Items.LILY_OF_THE_VALLEY, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.WHITE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("lily_of_the_valley", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LILY_OF_THE_VALLEY))
                .save(consumer, "lily_of_the_valley_white_dye");

        CrushingRecipeBuilder.builder(Items.ORANGE_DYE,1)
                .setIngredient(Items.ORANGE_TULIP, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.ORANGE_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("orange_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ORANGE_TULIP))
                .save(consumer, "orange_tulip_orange_dye");

        CrushingRecipeBuilder.builder(Items.LIGHT_GRAY_DYE,1)
                .setIngredient(Items.OXEYE_DAISY, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.LIGHT_GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("oxeye_daisy", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OXEYE_DAISY))
                .save(consumer, "oxeye_daisy_light_gray_dye");

        CrushingRecipeBuilder.builder(Items.PINK_DYE,2)
                .setIngredient(Items.PEONY, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.PINK_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("peony", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PEONY))
                .save(consumer, "peony_pink_dye");

        CrushingRecipeBuilder.builder(Items.PINK_DYE,1)
                .setIngredient(Items.PINK_TULIP, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.PINK_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("pink_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_TULIP))
                .save(consumer, "pink_tulip_pink_dye");

        CrushingRecipeBuilder.builder(Items.RED_DYE,1)
                .setIngredient(Items.POPPY, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.RED_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("pink_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_TULIP))
                .save(consumer, "poppy_red_dye");

        CrushingRecipeBuilder.builder(Items.RED_DYE,1)
                .setIngredient(Items.RED_TULIP, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.RED_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("red_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_TULIP))
                .save(consumer, "red_tulip_red_dye");

        CrushingRecipeBuilder.builder(Items.RED_DYE,2)
                .setIngredient(Items.ROSE_BUSH, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.RED_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("rose_bush", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROSE_BUSH))
                .save(consumer, "rose_bush_red_dye");

        CrushingRecipeBuilder.builder(Items.YELLOW_DYE,2)
                .setIngredient(Items.SUNFLOWER, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.YELLOW_DYE, 1, 50)
                .setGroup("crushing/flowers")
                .addCriterion("sunflower", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUNFLOWER))
                .save(consumer, "sunflower_yellow_dye");

        CrushingRecipeBuilder.builder(Items.GRAY_DYE,1)
                .setIngredient(Items.WHITE_TULIP, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.GRAY_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("white_tulip", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_TULIP))
                .save(consumer, "white_tulip_light_gray_dye");

        CrushingRecipeBuilder.builder(Items.BLACK_DYE,1)
                .setIngredient(Items.WITHER_ROSE, 1)
                .setExperience(0.1F)
                .addChanceResult(Items.BLACK_DYE, 1, 25)
                .setGroup("crushing/flowers")
                .addCriterion("wither_rose", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WITHER_ROSE))
                .save(consumer, "wither_rose_black_dye");

   
        /* DUST */

        CrushingRecipeBuilder.builder(ModItems.COAL_DUST,1)
                .setIngredient(Items.COAL, 1)
                .setExperience(0.1F)
                .setGroup("crushing/dust")
                .addCriterion("coal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .save(consumer, "coal_coal_dust");


        CrushingRecipeBuilder.builder(Items.NETHERITE_SCRAP,2)
                .setIngredient(Items.ANCIENT_DEBRIS, 1)
                .setExperience(2.0F)
                .setGroup("crushing/ores")
                .addCriterion("ANCient_debris", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ANCIENT_DEBRIS))
                .save(consumer, "ancient_debris_netherite_scrap");


        /* ITEMS */

        CrushingRecipeBuilder.builder(ModItems.DIAMOND_DUST,1)
                .setIngredient(Items.DIAMOND, 1)
                .setExperience(2.0F)
                .setGroup("crushing/items")
                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(consumer, "diamond_dust");

        CrushingRecipeBuilder.builder(ModItems.IRIDIUM_SHARD,9)
                .setIngredient(ModItems.IRIDIUM, 1)
                .setExperience(1F)
                .setGroup("crushing/items")
                .addCriterion("iridium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM.get()))
                .save(consumer, "iridium_shard");


        /* BIO */

        CrushingRecipeBuilder.builder(Items.DIRT,1)
                .setIngredient(ModItems.BIO_CHAFF, 1)
                .setGroup("crushing/bio")
                .addCriterion("bio_chaff", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIO_CHAFF.get()))
                .save(consumer, "dirt");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(ItemTags.SAPLINGS, 4)
                .setGroup("crushing/bio")
                .addCriterion("oak_sapling", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_SAPLING))
                .save(consumer, "saplings");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(ItemTags.LEAVES, 8)
                .setGroup("crushing/bio")
                .addCriterion("oak_leaves", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LEAVES))
                .save(consumer, "leaves");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.WHEAT, 8)
                .setGroup("crushing/bio")
                .addCriterion("wheat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
                .save(consumer, "wheat");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.CACTUS, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CACTUS))
                .save(consumer, "cactus");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.SUGAR_CANE, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR_CANE))
                .save(consumer, "sugar_cane");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.CARROT, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARROT))
                .save(consumer, "carrot");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.POTATO, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .save(consumer, "potato");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.MELON_SLICE, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
                .save(consumer, "melon_slice");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.PUMPKIN, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN))
                .save(consumer, "pumpkin");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.WHEAT_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT_SEEDS))
                .save(consumer, "wheat_seeds");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.MELON_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SEEDS))
                .save(consumer, "melon_seeds");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.PUMPKIN_SEEDS, 16)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN_SEEDS))
                .save(consumer, "pumpkin_seeds");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.BEETROOT_SEEDS, 8)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEETROOT_SEEDS))
                .save(consumer, "beetroot_seeds");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.VINE, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.VINE))
                .save(consumer, "vine");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.BAMBOO, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAMBOO))
                .save(consumer, "bamboo");

        CrushingRecipeBuilder.builder(ModItems.BIO_CHAFF,1)
                .setIngredient(Items.KELP, 32)
                .setGroup("crushing/bio")
                .addCriterion("cactus", InventoryChangeTrigger.TriggerInstance.hasItems(Items.KELP))
                .save(consumer, "kelp");


        /* RAW */

        CrushingRecipeBuilder.builder(ModItems.IRON_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_IRON, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer, "iron_dust");

        CrushingRecipeBuilder.builder(ModItems.COPPER_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_COPPER, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, "copper_dust");

        CrushingRecipeBuilder.builder(ModItems.GOLD_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_GOLD, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
                .save(consumer, "gold_dust");

        CrushingRecipeBuilder.builder(ModItems.TIN_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_TIN, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_TIN.get()))
                .save(consumer, "tin_dust");

        CrushingRecipeBuilder.builder(ModItems.URANIUM_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_URANIUM, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_URANIUM.get()))
                .save(consumer, "uranium_dust");

        CrushingRecipeBuilder.builder(ModItems.LEAD_DUST,4)
                .setIngredient(ModItemTags.FORGE_RAW_MATERIALS_LEAD, 3)
                .setExperience(0.2F)
                .setGroup("crushing/raw")
                .addCriterion("raw_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_LEAD.get()))
                .save(consumer, "lead_dust");


        /* ORES */

        CrushingRecipeBuilder.builder(ModItems.IRON_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_IRON, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_ORE))
                .addCriterion("deepslate_iron_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_IRON_ORE))
                .save(consumer, "iron_dust");

        CrushingRecipeBuilder.builder(ModItems.COPPER_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_COPPER, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_ORE))
                .addCriterion("deepslate_copper_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_COPPER_ORE))
                .save(consumer, "copper_dust");

        CrushingRecipeBuilder.builder(ModItems.GOLD_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_GOLD, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_ORE))
                .addCriterion("deepslate_gold_ore", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DEEPSLATE_GOLD_ORE))
                .save(consumer, "gold_dust");

        CrushingRecipeBuilder.builder(ModItems.TIN_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_TIN, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_ORE.get()))
                .addCriterion("deepslate_tin_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_TIN_ORE.get()))
                .save(consumer, "tin_dust");

        CrushingRecipeBuilder.builder(ModItems.URANIUM_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_URANIUM, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("deepslate_uranium_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_URANIUM_ORE.get()))
                .save(consumer, "uranium_dust");

        CrushingRecipeBuilder.builder(ModItems.LEAD_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_LEAD, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_ORE.get()))
                .addCriterion("deepslate_lead_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_LEAD_ORE.get()))
                .save(consumer, "lead_dust");

        CrushingRecipeBuilder.builder(ModItems.SILVER_DUST,2)
                .setIngredient(ModItemTags.FORGE_ORE_SILVER, 1)
                .setExperience(0.2F)
                .setGroup("crushing/ores")
                .addCriterion("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_ORE.get()))
                .addCriterion("deepslate_silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_SILVER_ORE.get()))
                .save(consumer, "silver_dust");


        /* PURIFIED */

        CrushingRecipeBuilder.builder(ModItems.IRON_DUST,1)
                .setIngredient(ModItems.PURIFIED_IRON, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("iron_chunk", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_IRON.get()))
                .save(consumer, "purified_iron");

        CrushingRecipeBuilder.builder(ModItems.COPPER_DUST,1)
                .setIngredient(ModItems.PURIFIED_COPPER, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_copper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_COPPER.get()))
                .save(consumer, "copper_dust");

        CrushingRecipeBuilder.builder(ModItems.GOLD_DUST,1)
                .setIngredient(ModItems.PURIFIED_GOLD, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_gold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_GOLD.get()))
                .save(consumer, "gold_dust");

        CrushingRecipeBuilder.builder(ModItems.TIN_DUST,1)
                .setIngredient(ModItems.PURIFIED_TIN, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_TIN.get()))
                .save(consumer, "tin_dust");

        CrushingRecipeBuilder.builder(ModItems.URANIUM_DUST,1)
                .setIngredient(ModItems.PURIFIED_URANIUM, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_URANIUM.get()))
                .save(consumer, "uranium_dust");

        CrushingRecipeBuilder.builder(ModItems.LEAD_DUST,1)
                .setIngredient(ModItems.PURIFIED_LEAD, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_lead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_LEAD.get()))
                .save(consumer, "lead_dust");

        CrushingRecipeBuilder.builder(ModItems.SILVER_DUST,1)
                .setIngredient(ModItems.PURIFIED_SILVER, 1)
                .setExperience(0.2F)
                .setGroup("crushing/purified")
                .addCriterion("purified_silver", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PURIFIED_SILVER.get()))
                .save(consumer, "silver_dust");


        /* GEMS */

        CrushingRecipeBuilder.builder(ModItems.LAPIS_LAZULI_DUST,1)
                .setIngredient(ModItemTags.FORGE_GEMS_LAPIS, 1)
                .setExperience(0.4F)
                .setGroup("crushing/gems")
                .addCriterion("lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .save(consumer, "lapis_lazuli_dust");





    }

}
