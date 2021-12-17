package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.receipe.impl.ScrapBoxRecipe;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.maciej916.indreb.common.util.Constants.JEI;

public class ScrapBoxCategory extends AbstractRecipeCategory<ScrapBoxRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.SCRAP_BOX.getRegistryName();

    public ScrapBoxCategory(IGuiHelper guiHelper) {
        super(
                ScrapBoxRecipe.class,
                UID,
                "scrap_box",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 0, 220, 92, 28),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModItems.SCRAP_BOX))
        );
    }

    @Override
    public void setIngredients(ScrapBoxRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getIngredients().stream().map(ingredient -> Arrays.asList(ingredient.getItems())).collect(Collectors.toList()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ScrapBoxRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 8, 5);
        guiItemStacks.init(1, false, 64, 5);

        guiItemStacks.set(0, new ItemStack(ModItems.SCRAP_BOX));
        guiItemStacks.set(1, recipe.getResultItem());
    }

    @Override
    public void draw(ScrapBoxRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        GuiUtil.renderScaled(poseStack, recipe.getDropChance() + " %", 33, 23, 0.75f, 0x7E7E7E, false);
    }
}
