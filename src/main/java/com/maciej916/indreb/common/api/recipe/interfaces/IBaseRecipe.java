package com.maciej916.indreb.common.api.recipe.interfaces;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;

public interface IBaseRecipe {

//    NonNullList<Integer> getIngredientsCount();
    int getDuration();
    int getTickEnergyCost();
    float getExperience();
    IngredientCount getIngredientCount();

    default boolean hasExperience() {
        return getExperience() != -1;
    }

}
