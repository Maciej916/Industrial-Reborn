package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.receipe.impl.ExtrudingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
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
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 55, 152, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.EXTRUDER))
        );
    }

    @Override
    public void setIngredients(ExtrudingRecipe recipe, IIngredients ingredients) {
        ArrayList<FluidStack> fluidInputList = new ArrayList<>();
        FluidStack water = new FluidStack(Fluids.WATER, 1000);
        FluidStack lava = new FluidStack(Fluids.LAVA, 1000);
        fluidInputList.add(water);
        fluidInputList.add(lava);
        ingredients.setInputs(VanillaTypes.FLUID, fluidInputList);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ExtrudingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(0, false, 11, 12, 8, 29, 8000, false, null);
        guiFluidStacks.init(1, false, 52,12, 8, 29, 8000, false, null);

        guiFluidStacks.set(0, new FluidStack(Fluids.WATER, Math.max(recipe.getWaterCost(), 1)));
        guiFluidStacks.set(1, new FluidStack(Fluids.LAVA, Math.max(recipe.getLavaCost(), 1)));

        guiItemStacks.init(2, false, 102, 18);
        guiItemStacks.set(ingredients);

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 51, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(ExtrudingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
