package com.maciej916.indreb.common.api.blockentity.interfaces;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.Recipe;

import javax.annotation.Nullable;

public interface IHasExp {

    default boolean hasExpButton() {
        return true;
    }
    float getExperience(Recipe<?> recipe);

    void collectExpServer(ServerPlayer player);
    Runnable collectExpClient();
    void addRecipeUsed(@Nullable Recipe<?> recipe);

    float getStoredExperience();
    void setStoredExperience(float storedExperience);
}
