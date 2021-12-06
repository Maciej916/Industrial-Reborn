package com.maciej916.indreb.common.interfaces.receipe;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.Map;

public interface IRecipeMultiInput extends IBaseRecipe {

    Map<Ingredient, Integer> getIngredientMap();
}
