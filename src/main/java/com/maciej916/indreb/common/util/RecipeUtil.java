package com.maciej916.indreb.common.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeUtil {

    public static List<Recipe<?>> getRecipes(RecipeManager manager, RecipeType<?> type) {
        Collection<Recipe<?>> recipes = manager.getRecipes();
        return (List)recipes.stream().filter((iRecipe) -> {
            return iRecipe.getType() == type;
        }).collect(Collectors.toList());
    }

    public static <T> NonNullList<T> nnListOf(T... toList) {
        NonNullList<T> list = NonNullList.create();
        list.addAll(Arrays.asList(toList));
        return list;
    }

}
