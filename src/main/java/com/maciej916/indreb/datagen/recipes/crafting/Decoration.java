package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModTags;
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

public class Decoration extends RecipeProvider {

    public Decoration(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "block/decoration" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModItems.BASIC_MACHINE_CASING.get())
                .requires(ItemTags.create(ModTags.BASE_MACHINE_SIGN_TAG))
                .group(MODID)
                .group(MODID + "_decoration")
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .save(consumer, saveResource("basic_machine_casing"));

        ShapedRecipeBuilder.shaped(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT.get())
                .pattern("y  ")
                .pattern(" c ")
                .pattern("  b")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("yellow_stripes_block_left"));

        ShapedRecipeBuilder.shaped(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .pattern("  y")
                .pattern(" c ")
                .pattern("b  ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("yellow_stripes_block_right"));

        ShapedRecipeBuilder.shaped(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .pattern(" yb")
                .pattern(" c ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("radioactive_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.BIO_HAZARD_SIGN_BLOCK.get())
                .pattern(" y ")
                .pattern(" cb")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("bio_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .pattern(" y ")
                .pattern("cb ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("explosion_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.FIRE_HAZARD_SIGN_BLOCK.get())
                .pattern("cy ")
                .pattern(" b ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("fire_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.ACID_HAZARD_SIGN_BLOCK.get())
                .pattern("cy ")
                .pattern("b  ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("acid_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK.get())
                .pattern("bc ")
                .pattern(" y ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("magic_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.FROST_HAZARD_SIGN_BLOCK.get())
                .pattern("bc ")
                .pattern("y  ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("frost_hazard_sign_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.NOISE_HAZARD_SIGN_BLOCK.get())
                .pattern("b  ")
                .pattern("yc ")
                .pattern("   ")
                .define('y', ItemTags.create(new ResourceLocation("forge", "dyes/yellow")))
                .define('b', ItemTags.create(new ResourceLocation("forge", "dyes/black")))
                .define('c', ModItems.BASIC_MACHINE_CASING.get())
                .group(MODID + "_decoration")
                .unlockedBy("yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .unlockedBy("basic_machine_casing", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .save(consumer, saveResource("noise_hazard_sign_block"));


    }

}