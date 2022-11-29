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
                .addIngredientExcluded(Items.SNOWBALL,  1)
                .addIngredientExcluded(ModItems.SCRAP,1)
                .addIngredientExcluded(ModItems.BASIC_MACHINE_CASING, 1)
                .addIngredientExcluded(ModItems.ADVANCED_MACHINE_CASING, 1)

                .addIngredientExcluded(ModItemTags.ELECTRIC_ITEMS, 1)
                .addIngredientExcluded(ModItemTags.FORGE_GLASS_PANES, 1)
                .addIngredientExcluded(ModItemTags.FORGE_RODS_ALL_METALS, 1)
                .addIngredientExcluded(ModItemTags.FORGE_RODS_WOODEN, 1)
                .addIngredientExcluded(ModItemTags.BASIC_MACHINE_SIGN, 1)
                .addIngredientExcluded(ModItemTags.GENERATORS, 1)
                .addIngredientExcluded(ModItemTags.MACHINES, 1)
                .addIngredientExcluded(ModItemTags.REACTOR_COMPONENT, 1)
                .addIngredientExcluded(ModItemTags.UPGRADE, 1)
                .addIngredientExcluded(ModItemTags.TRANSFORMERS, 1)
                .addIngredientExcluded(ModItemTags.BATTERY_BOXEX, 1)
                .addIngredientExcluded(ModItemTags.CANNED_FOOD, 1)

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

                .addIngredientExcluded(Items.LEATHER_HELMET, 1)
                .addIngredientExcluded(Items.LEATHER_CHESTPLATE, 1)
                .addIngredientExcluded(Items.LEATHER_LEGGINGS, 1)
                .addIngredientExcluded(Items.LEATHER_BOOTS, 1)

                .addIngredientExcluded(ModItems.BRONZE_HELMET, 1)
                .addIngredientExcluded(ModItems.BRONZE_CHESTPLATE, 1)
                .addIngredientExcluded(ModItems.BRONZE_LEGGINGS, 1)
                .addIngredientExcluded(ModItems.BRONZE_BOOTS, 1)
                .addIngredientExcluded(ModItems.DEBUG_STICK, 1)

                .addIngredientExcluded(Items.IRON_SWORD, 1)
                .addIngredientExcluded(Items.GOLDEN_SWORD, 1)
                .addIngredientExcluded(Items.DIAMOND_SWORD, 1)
                .addIngredientExcluded(ModItems.BRONZE_SWORD, 1)

                .addIngredientExcluded(Items.TURTLE_HELMET, 1)
                .addIngredientExcluded(Items.TNT, 1)

                .addIngredientExcluded(Items.LEATHER_HORSE_ARMOR, 1)
                .addIngredientExcluded(Items.IRON_HORSE_ARMOR, 1)
                .addIngredientExcluded(Items.DIAMOND_HORSE_ARMOR, 1)
                .addIngredientExcluded(Items.GOLDEN_HORSE_ARMOR, 1)

                .addIngredientExcluded(Items.IRON_PICKAXE, 1)
                .addIngredientExcluded(Items.IRON_AXE, 1)
                .addIngredientExcluded(Items.IRON_SHOVEL, 1)
                .addIngredientExcluded(Items.IRON_HOE, 1)

                .addIngredientExcluded(Items.DIAMOND_PICKAXE, 1)
                .addIngredientExcluded(Items.DIAMOND_AXE, 1)
                .addIngredientExcluded(Items.DIAMOND_SHOVEL, 1)
                .addIngredientExcluded(Items.DIAMOND_HOE, 1)

                .addIngredientExcluded(Items.GOLDEN_PICKAXE, 1)
                .addIngredientExcluded(Items.GOLDEN_AXE, 1)
                .addIngredientExcluded(Items.GOLDEN_SHOVEL, 1)
                .addIngredientExcluded(Items.GOLDEN_HOE, 1)

                .addIngredientExcluded(ModItems.BRONZE_PICKAXE, 1)
                .addIngredientExcluded(ModItems.BRONZE_AXE, 1)
                .addIngredientExcluded(ModItems.BRONZE_SHOVEL, 1)
                .addIngredientExcluded(ModItems.BRONZE_HOE, 1)

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

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 4)
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

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 4)
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

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 4)
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

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 4)
                .addIngredientIncluded(ModItems.BRONZE_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_BOOTS.get()))
                .setGroup("recycling/armour")
                .save(consumer,"bronze_boots");

        RecyclingRecipeBuilder.builder(Items.LEATHER, 5)
                .addIngredientIncluded(Items.LEATHER_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HELMET))
                .setGroup("recycling/armour")
                .save(consumer,"leather_helmet");

        RecyclingRecipeBuilder.builder(Items.LEATHER, 8)
                .addIngredientIncluded(Items.LEATHER_CHESTPLATE, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_CHESTPLATE))
                .setGroup("recycling/armour")
                .save(consumer,"leather_chestplate");

        RecyclingRecipeBuilder.builder(Items.LEATHER, 7)
                .addIngredientIncluded(Items.LEATHER_LEGGINGS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_LEGGINGS))
                .setGroup("recycling/armour")
                .save(consumer,"leather_leggings");

        RecyclingRecipeBuilder.builder(Items.LEATHER, 7)
                .addIngredientIncluded(Items.LEATHER_BOOTS, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_BOOTS))
                .setGroup("recycling/armour")
                .save(consumer,"leather_boots");

        RecyclingRecipeBuilder.builder(Items.SCUTE, 5)
                .addIngredientIncluded(Items.TURTLE_HELMET, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TURTLE_HELMET))
                .setGroup("recycling/armour")
                .save(consumer,"turtle_helmet");


        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 2)
                .addIngredientIncluded(Items.IRON_SWORD, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_SWORD))
                .setGroup("recycling/tools")
                .save(consumer,"iron_sword");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 2)
                .addIngredientIncluded(Items.GOLDEN_SWORD, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_SWORD))
                .setGroup("recycling/tools")
                .save(consumer,"golden_sword");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 2)
                .addIngredientIncluded(Items.DIAMOND_SWORD, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_SWORD))
                .setGroup("recycling/tools")
                .save(consumer,"diamond_sword");

        RecyclingRecipeBuilder.builder(Items.GUNPOWDER, 5)
                .addIngredientIncluded(Items.TNT, 1)
                .setChance(75)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TNT))
                .setGroup("recycling/misc")
                .save(consumer,"tnt");

        RecyclingRecipeBuilder.builder(ModItems.SCRAP_BOX, 1)
                .addIngredientIncluded(ModItems.SCRAP, 9)
                .setChance(100)
                .setExperience(2f)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .setGroup("recycling/misc")
                .save(consumer,"scrap_box");

        RecyclingRecipeBuilder.builder(Items.LEATHER, 7)
                .addIngredientIncluded(Items.LEATHER_HORSE_ARMOR, 1)
                .setChance(100)
                .setExperience(5f)
                .addCriterion("leather_horse_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HORSE_ARMOR))
                .setGroup("recycling/horse")
                .save(consumer,"leather_horse_armor");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 7)
                .addIngredientIncluded(Items.IRON_HORSE_ARMOR, 1)
                .setChance(100)
                .setExperience(5f)
                .addCriterion("iron_horse_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_HORSE_ARMOR))
                .setGroup("recycling/horse")
                .save(consumer,"iron_horse_armor");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 7)
                .addIngredientIncluded(Items.DIAMOND_HORSE_ARMOR, 1)
                .setChance(100)
                .setExperience(5f)
                .addCriterion("diamond_horse_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_HORSE_ARMOR))
                .setGroup("recycling/horse")
                .save(consumer,"diamond_horse_armor");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 7)
                .addIngredientIncluded(Items.GOLDEN_HORSE_ARMOR, 1)
                .setChance(100)
                .setExperience(5f)
                .addCriterion("golden_horse_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_HORSE_ARMOR))
                .setGroup("recycling/horse")
                .save(consumer,"golden_horse_armor");

        RecyclingRecipeBuilder.builder(ModItems.EMPTY_CAN, 1)
                .addIngredientIncluded(ModItemTags.CANNED_FOOD, 1)
                .setChance(100)
                .setExperience(1.5f)
                .addCriterion("empty_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .setGroup("recycling")
                .save(consumer,"canned_food");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 3)
                .addIngredientIncluded(Items.IRON_PICKAXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("iron_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_PICKAXE))
                .setGroup("recycling/tools")
                .save(consumer,"iron_pickaxe");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 3)
                .addIngredientIncluded(Items.IRON_AXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("iron_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_AXE))
                .setGroup("recycling/tools")
                .save(consumer,"iron_axe");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 1)
                .addIngredientIncluded(Items.IRON_SHOVEL, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("iron_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_SHOVEL))
                .setGroup("recycling/tools")
                .save(consumer,"iron_shovel");

        RecyclingRecipeBuilder.builder(Items.IRON_INGOT, 2)
                .addIngredientIncluded(Items.IRON_HOE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("iron_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_HOE))
                .setGroup("recycling/tools")
                .save(consumer,"iron_hoe");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 3)
                .addIngredientIncluded(Items.DIAMOND_PICKAXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("diamond_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_PICKAXE))
                .setGroup("recycling/tools")
                .save(consumer,"diamond_pickaxe");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 3)
                .addIngredientIncluded(Items.DIAMOND_AXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("diamond_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_AXE))
                .setGroup("recycling/tools")
                .save(consumer,"diamond_axe");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 1)
                .addIngredientIncluded(Items.DIAMOND_SHOVEL, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("diamond_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_SHOVEL))
                .setGroup("recycling/tools")
                .save(consumer,"diamond_shovel");

        RecyclingRecipeBuilder.builder(Items.DIAMOND, 2)
                .addIngredientIncluded(Items.DIAMOND_HOE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("diamond_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_HOE))
                .setGroup("recycling/tools")
                .save(consumer,"diamond_hoe");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 3)
                .addIngredientIncluded(Items.GOLDEN_PICKAXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("golden_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_PICKAXE))
                .setGroup("recycling/tools")
                .save(consumer,"golden_pickaxe");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 3)
                .addIngredientIncluded(Items.GOLDEN_AXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("golden_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_AXE))
                .setGroup("recycling/tools")
                .save(consumer,"golden_axe");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 1)
                .addIngredientIncluded(Items.GOLDEN_SHOVEL, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("golden_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_SHOVEL))
                .setGroup("recycling/tools")
                .save(consumer,"golden_shovel");

        RecyclingRecipeBuilder.builder(Items.GOLD_INGOT, 2)
                .addIngredientIncluded(Items.GOLDEN_HOE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("golden_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_HOE))
                .setGroup("recycling/tools")
                .save(consumer,"golden_hoe");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 3)
                .addIngredientIncluded(ModItems.BRONZE_PICKAXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("bronze_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_PICKAXE.get()))
                .setGroup("recycling/tools")
                .save(consumer,"bronze_pickaxe");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 3)
                .addIngredientIncluded(ModItems.BRONZE_AXE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("bronze_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_AXE.get()))
                .setGroup("recycling/tools")
                .save(consumer,"bronze_axe");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 1)
                .addIngredientIncluded(ModItems.BRONZE_SHOVEL, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("bronze_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_SHOVEL.get()))
                .setGroup("recycling/tools")
                .save(consumer,"bronze_shovel");

        RecyclingRecipeBuilder.builder(ModItems.BRONZE_INGOT, 2)
                .addIngredientIncluded(ModItems.BRONZE_HOE, 1)
                .setChance(75)
                .setExperience(1f)
                .addCriterion("bronze_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_HOE.get()))
                .setGroup("recycling/tools")
                .save(consumer,"bronze_hoe");


    }
}
