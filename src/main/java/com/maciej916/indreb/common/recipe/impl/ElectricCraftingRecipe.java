package com.maciej916.indreb.common.recipe.impl;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class ElectricCraftingRecipe extends CustomRecipe {

    public static final SimpleRecipeSerializer<ElectricCraftingRecipe> SERIALIZER = new SimpleRecipeSerializer<> (ElectricCraftingRecipe::new);


    public ElectricCraftingRecipe(ResourceLocation pId) {
        super(pId);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
//        // Simple match first ingredient == first container item
//        return ingredients.getIngredients().get(0).test(container.getItem(0));

        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        return getResultItem().copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
