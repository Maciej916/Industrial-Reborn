package com.maciej916.indreb.common.interfaces.receipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface IBaseRecipe extends Recipe<Container> {

    float getExperience();
    int getDuration();
    int getPowerCost();
}
