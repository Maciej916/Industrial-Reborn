package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.receipe.impl.CanningRecipe;
import com.maciej916.indreb.common.receipe.impl.ScrapBoxRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.maciej916.indreb.common.util.Constants.*;

public class CanningCategory extends AbstractRecipeCategory<CanningRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.CANNING.getRegistryName();

    protected IDrawableAnimated progress;
    protected IDrawableAnimated energy;

    public CanningCategory(IGuiHelper guiHelper) {
        super(
                CanningRecipe.class,
                UID,
                "canning",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 110, 152, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.CANNING_MACHINE))
        );
    }

    @Override
    public void setIngredients(CanningRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getIngredients().stream().map(ingredient -> Arrays.asList(ingredient.getItems())).collect(Collectors.toList()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CanningRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 7, 18);
        guiItemStacks.init(1, true, 45, 18);
        guiItemStacks.init(2, false, 102, 18);

        guiItemStacks.set(0, new ItemStack(recipe.getFirstIngredient().getItems()[0].getItem(), recipe.getFirstIngredientCount()));
        guiItemStacks.set(1, new ItemStack(recipe.getSecondIngredient().getItems()[0].getItem(), recipe.getSecondIngredientCount()));

        guiItemStacks.set(2, recipe.getResultItem());

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(CanningRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
