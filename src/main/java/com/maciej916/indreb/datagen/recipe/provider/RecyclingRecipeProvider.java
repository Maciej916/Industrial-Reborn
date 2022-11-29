package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import com.maciej916.indreb.datagen.recipe.builder.RecyclingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RecyclingRecipeProvider extends RecipeProvider {

    public RecyclingRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecyclingRecipeBuilder.builder(ModItems.SCRAP, 1)
                .addIngredientExcluded(ModItemTags.ELECTRIC_ITEMS, 1)
                .addIngredientExcluded(ModItemTags.FORGE_GLASS_PANES, 1)
                .addIngredientExcluded(ModItemTags.FORGE_RODS_ALL_METALS, 1)
                .addIngredientExcluded(ModItemTags.FORGE_RODS_WOODEN, 1)
                .addIngredientExcluded(Items.SNOWBALL,  1)
                .addIngredientExcluded(ModItems.SCRAP,1)

                .addIngredientExcluded(ModItems.BASIC_MACHINE_CASING, 1)
                .addIngredientExcluded(ModItems.ADVANCED_MACHINE_CASING, 1)
                .addIngredientExcluded(ModItemTags.BASIC_MACHINE_SIGN, 1)
                .addIngredientExcluded(ModItemTags.GENERATORS, 1)
                .addIngredientExcluded(ModItemTags.MACHINES, 1)
                .addIngredientExcluded(ModItemTags.REACTOR_COMPONENT, 1)
                .addIngredientExcluded(ModItemTags.UPGRADE, 1)
                .addIngredientExcluded(ModItemTags.TRANSFORMERS, 1)
                .addIngredientExcluded(ModItemTags.BATTERY_BOXEX, 1)

                .addIngredientExcluded(Items.DIAMOND_HELMET, 1)
                .addIngredientExcluded(Items.DIAMOND_CHESTPLATE, 1)
                .addIngredientExcluded(Items.DIAMOND_LEGGINGS, 1)
                .addIngredientExcluded(Items.DIAMOND_BOOTS, 1)

                .addIngredientExcluded(Items.IRON_HELMET, 1)
                .addIngredientExcluded(Items.IRON_CHESTPLATE, 1)
                .addIngredientExcluded(Items.IRON_LEGGINGS, 1)
                .addIngredientExcluded(Items.IRON_BOOTS, 1)

                .addIngredientExcluded(Items.GOLDEN_HELMET, 1)
                .addIngredientExcluded(Items.GOLDEN_CHESTPLATE, 1)
                .addIngredientExcluded(Items.GOLDEN_LEGGINGS, 1)
                .addIngredientExcluded(Items.GOLDEN_BOOTS, 1)

                .addIngredientExcluded(ModItems.BRONZE_HELMET, 1)
                .addIngredientExcluded(ModItems.BRONZE_CHESTPLATE, 1)
                .addIngredientExcluded(ModItems.BRONZE_LEGGINGS, 1)
                .addIngredientExcluded(ModItems.BRONZE_BOOTS, 1)
                .addIngredientExcluded(ModItems.DEBUG_STICK, 1)

                .setChance(15)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .setGroup("recycling")
                .save(consumer,"scrap");


        RecyclingRecipeBuilder.builder(ModItems.SCRAP_METAL, 8)
                .addIngredientIncluded(ModItems.BASIC_MACHINE_CASING, 1)
                .addIngredientIncluded(ModItems.ADVANCED_MACHINE_CASING, 1)
                .addIngredientIncluded(ModItemTags.BASIC_MACHINE_SIGN, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASIC_MACHINE_CASING.get()))
                .setGroup("recycling")
                .save(consumer,"basic_scrap_metal");


        RecyclingRecipeBuilder.builder(Items.DIAMOND, 5)
                .addIngredientIncluded(Items.DIAMOND_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_HELMET))
                .setGroup("recycling/armour")
                .save(consumer,"diamond_helmet");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 8)
                .addIngredientIncluded(Items.DIAMOND_CHESTPLATE, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_CHESTPLATE))
                .setGroup("recycling/armour")
                .save(consumer,"diamond_chestplate");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 7)
                .addIngredientIncluded(Items.DIAMOND_LEGGINGS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_LEGGINGS))
                .setGroup("recycling/armour")
                .save(consumer,"diamond_leggings");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 7)
                .addIngredientIncluded(Items.DIAMOND_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_BOOTS))
                .setGroup("recycling/armour")
                .save(consumer,"diamond_boots");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 5)
                .addIngredientIncluded(Items.IRON_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_HELMET))
                .setGroup("recycling/armour")
                .save(consumer,"iron_helmet");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 8)
                .addIngredientIncluded(Items.IRON_CHESTPLATE, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_CHESTPLATE))
                .setGroup("recycling/armour")
                .save(consumer,"iron_chestplate");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 7)
                .addIngredientIncluded(Items.IRON_LEGGINGS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_LEGGINGS))
                .setGroup("recycling/armour")
                .save(consumer,"iron_leggings");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 7)
                .addIngredientIncluded(Items.IRON_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BOOTS))
                .setGroup("recycling/armour")
                .save(consumer,"iron_boots");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 5)
                .addIngredientIncluded(Items.GOLDEN_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_HELMET))
                .setGroup("recycling/armour")
                .save(consumer,"golden_helmet");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 8)
                .addIngredientIncluded(Items.GOLDEN_CHESTPLATE, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_CHESTPLATE))
                .setGroup("recycling/armour")
                .save(consumer,"golden_chestplate");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 7)
                .addIngredientIncluded(Items.GOLDEN_LEGGINGS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_LEGGINGS))
                .setGroup("recycling/armour")
                .save(consumer,"golden_leggings");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 7)
                .addIngredientIncluded(Items.GOLDEN_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_BOOTS))
                .setGroup("recycling/armour")
                .save(consumer,"golden_boots");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 5)
                .addIngredientIncluded(ModItems.BRONZE_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_HELMET.get()))
                .setGroup("recycling/armour")
                .save(consumer,"bronze_helmet");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 8)
                .addIngredientIncluded(ModItems.BRONZE_CHESTPLATE, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_CHESTPLATE.get()))
                .setGroup("recycling/armour")
                .save(consumer,"bronze_chestplate");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 7)
                .addIngredientIncluded(ModItems.BRONZE_LEGGINGS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_LEGGINGS.get()))
                .setGroup("recycling/armour")
                .save(consumer,"bronze_leggings");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 7)
                .addIngredientIncluded(ModItems.BRONZE_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_BOOTS.get()))
                .setGroup("recycling/armour")
                .save(consumer,"bronze_boots");
    }
}
