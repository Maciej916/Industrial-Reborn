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

public class ConstructionFoamProvider extends RecipeProvider {

    public ConstructionFoamProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/construction_foam/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModItems.CONSTRUCTION_FOAM_WALL_WHITE.get(), 1)
                .requires(ModItems.CONSTRUCTION_FOAM.get())
                .requires(Items.SAND, 3)
                .group(MODID + "/construction_foam")
                .unlockedBy("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
                .save(consumer, saveResource("construction_foam_wall_white"));

    }
}