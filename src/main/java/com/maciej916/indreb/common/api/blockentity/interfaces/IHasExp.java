package com.maciej916.indreb.common.api.blockentity.interfaces;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;

import javax.annotation.Nullable;

public interface IHasExp {

    default boolean hasExpButton() {
        return true;
    }
    float getExperience(Recipe<?> recipe);
    float getStoredExperience();
    void collectExpServer(Player player);
    Runnable collectExpClient();
    void addRecipeUsed(@Nullable Recipe<?> recipe);
}
