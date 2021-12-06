package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.receipe.impl.ExtrudingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.maciej916.indreb.common.util.Constants.*;

public class ExtrudingCategory extends AbstractRecipeCategory<ExtrudingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.EXTRUDING.getRegistryName();

    protected IDrawableAnimated progress;
    protected IDrawableAnimated energy;

    public ExtrudingCategory(IGuiHelper guiHelper) {
        super(
                ExtrudingRecipe.class,
                UID,
                "extruding",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 55, 150, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.EXTRUDER))
        );
    }

    @Override
    public void setIngredients(ExtrudingRecipe recipe, IIngredients ingredients) {
      ingredients.setInputIngredients(recipe.getIngredients());

      List<ItemStack> outputs = new ArrayList<>();

      outputs.add(recipe.getResultItem());

      ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ExtrudingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(2, false, 102, 18);

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 51, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);



        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(ExtrudingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 5, 19);
        this.energy.draw(poseStack, halfX + 59, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
