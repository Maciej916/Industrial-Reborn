package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsCropProvider extends RecipeProvider {

    public ItemsCropProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/crop/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModItems.FERTILIZER.get(), 1)
                .requires(ModItems.SCRAP.get())
                .requires(Items.BONE_MEAL)
                .group(MODID + "/items/crop")
                .unlockedBy("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .unlockedBy("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .save(consumer, saveResource("fertilizer"));

        ShapelessRecipeBuilder.shapeless(ModItems.FERTILIZER.get(), 2)
                .requires(ModItems.FERTILIZER.get())
                .requires(Items.BONE_MEAL)
                .group(MODID + "/items/crop")
                .unlockedBy("fertilizer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FERTILIZER.get()))
                .unlockedBy("bone_meal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE_MEAL))
                .save(consumer, saveResource("fertilizer_2"));

    }
}