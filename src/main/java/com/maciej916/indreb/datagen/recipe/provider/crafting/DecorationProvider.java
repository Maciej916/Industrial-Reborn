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
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class DecorationProvider extends RecipeProvider {

    public DecorationProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/sign/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModItems.BASIC_MACHINE_CASING.get())
                .requires(ModItemTags.BASIC_MACHINE_SIGN)
                .group(MODID + "/decoration/sign")
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("basic_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT.get())
                .pattern("y  ")
                .pattern(" c ")
                .pattern("  b")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("yellow_stripes_block_left"));

        ShapedRecipeBuilder.shaped(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .pattern("  y")
                .pattern(" c ")
                .pattern("b  ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("yellow_stripes_block_right"));

        ShapedRecipeBuilder.shaped(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .pattern(" yb")
                .pattern(" c ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("radioactive_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.BIO_HAZARD_SIGN_BLOCK.get())
                .pattern(" y ")
                .pattern(" cb")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("bio_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .pattern(" y ")
                .pattern("cb ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("explosion_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.FIRE_HAZARD_SIGN_BLOCK.get())
                .pattern("cy ")
                .pattern(" b ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("fire_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.ACID_HAZARD_SIGN_BLOCK.get())
                .pattern("cy ")
                .pattern("b  ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("acid_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK.get())
                .pattern("bc ")
                .pattern(" y ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("magic_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.FROST_HAZARD_SIGN_BLOCK.get())
                .pattern("bc ")
                .pattern("y  ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("frost_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.NOISE_HAZARD_SIGN_BLOCK.get())
                .pattern("b  ")
                .pattern("yc ")
                .pattern("   ")
                .define('y', ModItemTags.FORGE_DYES_YELLOW)
                .define('b', ModItemTags.FORGE_DYES_BLACK)
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "/decoration/sign")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("noise_hazard_sign_block"));



    }
}