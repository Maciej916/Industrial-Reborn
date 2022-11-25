package com.maciej916.indreb.common.api.recipe.lib;

import net.minecraft.world.item.crafting.Ingredient;

public record IngredientCountStack(Ingredient ingredient, int count) {

    public Ingredient ingredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }
}

