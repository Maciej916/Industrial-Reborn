package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.receipe.impl.FluidEnrichingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static com.maciej916.indreb.common.util.Constants.*;

public class FluidEnrichingCategory extends AbstractRecipeCategory<FluidEnrichingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.FLUID_ENRICHING.getRegistryName();

    protected IDrawableAnimated progress;
    protected IDrawableAnimated energy;

    public FluidEnrichingCategory(IGuiHelper guiHelper) {
        super(
                FluidEnrichingRecipe.class,
                UID,
                "fluid_enriching",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 165, 152, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.FLUID_ENRICHER))
        );
    }

    @Override
    public void setIngredients(FluidEnrichingRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, new ItemStack(recipe.getIngredient().getItems()[0].getItem(), recipe.getIngredientCount()));
        ingredients.setInput(VanillaTypes.FLUID, recipe.getFluidInput());
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FluidEnrichingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiFluidStacks.init(1, false, 50,12, 8, 29, 8000, false, null);
        guiFluidStacks.init(2, false, 106,12, 8, 29, 8000, false, null);

        guiFluidStacks.set(1, new FluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount()));
        guiFluidStacks.set(2, new FluidStack(recipe.getResult().getFluid(), recipe.getResult().getAmount()));

        guiItemStacks.init(0, true, 7, 18);
        guiItemStacks.set(0, new ItemStack(recipe.getIngredient().getItems()[0].getItem(), recipe.getIngredientCount()));

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(FluidEnrichingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
