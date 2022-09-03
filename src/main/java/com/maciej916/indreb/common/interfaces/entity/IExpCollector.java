package com.maciej916.indreb.common.interfaces.entity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;

public interface IExpCollector {

    default boolean hasExpButton() {
        return true;
    }
    float getExperience(Recipe<?> recipe);
    void collectExp(Player playerEntity);
    Runnable collectExp();
}
