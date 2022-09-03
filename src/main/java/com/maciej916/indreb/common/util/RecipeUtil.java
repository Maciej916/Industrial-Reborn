package com.maciej916.indreb.common.util;

import net.minecraft.core.NonNullList;

import java.util.Arrays;

public class RecipeUtil {

    @SafeVarargs
    public static <T> NonNullList<T> nnListOf(T... toList) {
        NonNullList<T> list = NonNullList.create();
        list.addAll(Arrays.asList(toList));
        return list;
    }

}
