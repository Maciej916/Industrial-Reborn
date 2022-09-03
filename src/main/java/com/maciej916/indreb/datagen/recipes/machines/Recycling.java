package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderRecycling;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class Recycling extends RecipeProvider {

    public Recycling(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderRecycling.builder(ModItems.SCRAP)

                .addExcluded(Ingredient.of(ItemTags.create(new ResourceLocation("indreb", "electrics"))))

                .addExcluded(Ingredient.of(Items.STICK))
                .addExcluded(Ingredient.of(Items.SNOWBALL))
                .addExcluded(Ingredient.of(Items.GLASS_PANE))
                .addExcluded(Ingredient.of(Items.WHITE_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.ORANGE_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.MAGENTA_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.LIGHT_BLUE_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.LIME_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.PINK_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.GRAY_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.LIGHT_GRAY_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.CYAN_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.PURPLE_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.BLUE_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.BROWN_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.GREEN_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.RED_STAINED_GLASS_PANE))
                .addExcluded(Ingredient.of(Items.BLACK_STAINED_GLASS_PANE))

                .setChance(0.15F)
                .addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP))
                .setGroup("recycling")
                .save(consumer,"scrap");



    }

}
