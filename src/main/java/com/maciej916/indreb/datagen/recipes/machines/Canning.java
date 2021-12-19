package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderCanning;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class Canning extends RecipeProvider {

    public Canning(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 4)
                .setFirstIngredient(Items.APPLE, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"apple");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 5)
                .setFirstIngredient(Items.BAKED_POTATO, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("baked_potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAKED_POTATO))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"baked_potato");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 1)
                .setFirstIngredient(Items.BEETROOT, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("beetroot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BEETROOT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"beetroot");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 5)
                .setFirstIngredient(Items.BREAD, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("bread", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BREAD))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"bread");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 3)
                .setFirstIngredient(Items.CARROT, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 3)
                .setExperience(0.2F)
                .addCriterion("carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARROT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"carrot");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 3)
                .setFirstIngredient(Items.COOKED_COD, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 3)
                .setExperience(0.2F)
                .addCriterion("cooked_cod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_COD))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_cod");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 6)
                .setFirstIngredient(Items.COOKED_MUTTON, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("cooked_mutton", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_MUTTON))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_mutton");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 8)
                .setFirstIngredient(Items.COOKED_PORKCHOP, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("cooked_porkchop", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_PORKCHOP))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_porkchop");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 5)
                .setFirstIngredient(Items.COOKED_RABBIT, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 5)
                .setExperience(0.2F)
                .addCriterion("cooked_rabbit", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_RABBIT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_rabbit");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 6)
                .setFirstIngredient(Items.COOKED_SALMON, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("cooked_salmon", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_SALMON))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_salmon");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 2)
                .setFirstIngredient(Items.COOKIE, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("cookie", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKIE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cookie");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 1)
                .setFirstIngredient(Items.DRIED_KELP, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("dried_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIED_KELP))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"dried_kelp");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 2)
                .setFirstIngredient(Items.GLOW_BERRIES, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("glow_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOW_BERRIES))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"glow_berries");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 6)
                .setFirstIngredient(Items.GOLDEN_CARROT, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 6)
                .setExperience(0.2F)
                .addCriterion("golden_carrot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_CARROT))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"golden_carrot");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 2)
                .setFirstIngredient(Items.MELON_SLICE, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("melon_slice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MELON_SLICE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"melon_slice");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 1)
                .setFirstIngredient(Items.POTATO, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 1)
                .setExperience(0.2F)
                .addCriterion("potato", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POTATO))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"potato");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 8)
                .setFirstIngredient(Items.PUMPKIN_PIE, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("pumpkin_pie", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUMPKIN_PIE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"pumpkin_pie");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 4)
                .setFirstIngredient(Items.ROTTEN_FLESH, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 4)
                .setExperience(0.2F)
                .addCriterion("rotten_flesh", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"rotten_flesh");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 2)
                .setFirstIngredient(Items.SPIDER_EYE, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("spider_eye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPIDER_EYE))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"spider_eye");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 8)
                .setFirstIngredient(Items.COOKED_BEEF, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 8)
                .setExperience(0.2F)
                .addCriterion("cooked_beef", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COOKED_BEEF))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"cooked_beef");


        RecipeBuilderCanning.builder(ModItems.FILLED_TIN_CAN, 2)
                .setFirstIngredient(Items.SWEET_BERRIES, 1)
                .setSecondIngredient(ModItems.TIN_CAN, 2)
                .setExperience(0.2F)
                .addCriterion("sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SWEET_BERRIES))
                .addCriterion("tin_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_CAN))
                .setGroup("canning")
                .save(consumer,"sweet_berries");


    }
}
