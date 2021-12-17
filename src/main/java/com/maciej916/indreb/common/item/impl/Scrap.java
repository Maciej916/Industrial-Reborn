package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.item.base.BaseItem;
import com.maciej916.indreb.common.receipe.impl.ScrapBoxRecipe;
import com.maciej916.indreb.common.registries.ModRecipeType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Scrap extends BaseItem {
    public Scrap() {
        super(new Properties());
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 200;
    }

}
