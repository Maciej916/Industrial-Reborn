package com.maciej916.indreb.datagen.recipe.provider.custom;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.MatterAmplifierBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class MatterAmplifierProvider extends RecipeProvider {

    public MatterAmplifierProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        MatterAmplifierBuilder.builder(10000).setIngredient(ModItems.SCRAP_METAL).addCriterion("scrap_metal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP_METAL.get())).save(consumer,"scrap_metal");
        MatterAmplifierBuilder.builder(5000).setIngredient(ModItems.SCRAP).addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get())).save(consumer,"scrap");
        MatterAmplifierBuilder.builder(50000).setIngredient(ModItems.SCRAP_BOX).addCriterion("scrap_box", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP_BOX.get())).save(consumer,"scrap_box");
        MatterAmplifierBuilder.builder(150000).setIngredient(Items.NETHERITE_SCRAP).addCriterion("netherite_scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SCRAP)).save(consumer,"netherite_scrap");


    }
}
