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

        RecipeBuilderRecycling.builder(ModItems.SCRAP.get())

                .addExcluded(Ingredient.of(ItemTags.create(new ResourceLocation("indreb", "electrics"))))
                .addExcluded(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "glass_panes"))))
                .addExcluded(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "rods/all_metals"))))
                .addExcluded(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "rods/wooden"))))

                .addExcluded(Ingredient.of(Items.SNOWBALL))

                .setChance(0.15F)
                .addCriterion("scrap", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRAP.get()))
                .setGroup("recycling")
                .save(consumer,"scrap");



    }

}
