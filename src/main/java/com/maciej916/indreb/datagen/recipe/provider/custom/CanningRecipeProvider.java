package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.CanningRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CanningRecipeProvider extends RecipeProvider {

    public CanningRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 4)
                .setFirstIngredient(Items.APPLE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"apple");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 5)
                .setFirstIngredient(Items.BAKED_POTATO, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"baked_potato");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 1)
                .setFirstIngredient(Items.BEETROOT, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("beetroot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEETROOT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"beetroot");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 5)
                .setFirstIngredient(Items.BREAD, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("bread", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BREAD))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"bread");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 14)
                .setFirstIngredient(Items.CAKE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 14)
                .setExperience(0.2F)
                .addCriterion("cake", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CAKE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cake");

        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 3)
                .setFirstIngredient(Items.CARROT, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 3)
                .setExperience(0.2F)
                .addCriterion("carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARROT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"carrot");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 3)
                .setFirstIngredient(Items.COOKED_COD, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 3)
                .setExperience(0.2F)
                .addCriterion("cooked_cod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_COD))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_cod");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 6)
                .setFirstIngredient(Items.COOKED_MUTTON, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("cooked_mutton", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_MUTTON))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_mutton");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 8)
                .setFirstIngredient(Items.COOKED_PORKCHOP, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("cooked_porkchop", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_PORKCHOP))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_porkchop");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 5)
                .setFirstIngredient(Items.COOKED_RABBIT, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("cooked_rabbit", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_RABBIT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_rabbit");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 6)
                .setFirstIngredient(Items.COOKED_SALMON, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("cooked_salmon", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_SALMON))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_salmon");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 2)
                .setFirstIngredient(Items.COOKIE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("cookie", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKIE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cookie");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 1)
                .setFirstIngredient(Items.DRIED_KELP, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("dried_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIED_KELP))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"dried_kelp");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 2)
                .setFirstIngredient(Items.GLOW_BERRIES, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("glow_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOW_BERRIES))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"glow_berries");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 6)
                .setFirstIngredient(Items.GOLDEN_CARROT, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("golden_carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_CARROT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"golden_carrot");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 2)
                .setFirstIngredient(Items.MELON_SLICE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("melon_slice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"melon_slice");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 1)
                .setFirstIngredient(Items.POTATO, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"potato");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 8)
                .setFirstIngredient(Items.PUMPKIN_PIE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("pumpkin_pie", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN_PIE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"pumpkin_pie");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 8)
                .setFirstIngredient(Items.COOKED_BEEF, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("cooked_beef", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_BEEF))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"cooked_beef");


        CanningRecipeBuilder.builder(ModItems.CANNED_FOOD, 2)
                .setFirstIngredient(Items.SWEET_BERRIES, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SWEET_BERRIES))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"sweet_berries");


        CanningRecipeBuilder.builder(ModItems.CANNED_HUNGER, 2)
                .setFirstIngredient(Items.CHICKEN, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("rotten_flesh", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHICKEN))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"chicken");


        CanningRecipeBuilder.builder(ModItems.CANNED_HUNGER, 4)
                .setFirstIngredient(Items.ROTTEN_FLESH, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("rotten_flesh", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"rotten_flesh");


        CanningRecipeBuilder.builder(ModItems.CANNED_POISON, 2)
                .setFirstIngredient(Items.POISONOUS_POTATO, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("poisonous_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POISONOUS_POTATO))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"poisonous_potato");

        CanningRecipeBuilder.builder(ModItems.CANNED_POISON, 2)
                .setFirstIngredient(Items.SPIDER_EYE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("spider_eye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPIDER_EYE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"spider_eye");


        CanningRecipeBuilder.builder(ModItems.CANNED_CHORUS_FRUIT, 4)
                .setFirstIngredient(Items.CHORUS_FRUIT, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("CHORUS_FRUIT", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHORUS_FRUIT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"chorus_fruit");


        CanningRecipeBuilder.builder(ModItems.CANNED_ENCHANTED_GOLDEN_APPLE, 4)
                .setFirstIngredient(Items.ENCHANTED_GOLDEN_APPLE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("enchanted_golden_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENCHANTED_GOLDEN_APPLE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"enchanted_golden_apple");


        CanningRecipeBuilder.builder(ModItems.CANNED_GOLDEN_APPLE, 4)
                .setFirstIngredient(Items.GOLDEN_APPLE, 1)
                .setSecondIngredient(ModItems.EMPTY_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("golden_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_APPLE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("canning/food")
                .save(consumer,"golden_apple");













        CanningRecipeBuilder.builder(ModItems.FUEL_ROD_URANIUM, 1)
                .setFirstIngredient(ModItems.URANIUM_INGOT, 1)
                .setSecondIngredient(ModItems.FUEL_ROD, 1)
                .setExperience(3.0F)
                .addCriterion("uranium_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_INGOT.get()))
                .addCriterion("fuel_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUEL_ROD.get()))
                .setGroup("canning/uranium")
                .save(consumer,"fuel_rod_uranium");



    }
}
