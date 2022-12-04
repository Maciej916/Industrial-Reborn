package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.FluidExtrudingRecipe;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.maciej916.indreb.common.util.Constants.*;

public class FluidExtrudingCategory extends AbstractRecipeCategory<FluidExtrudingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.FLUID_EXTRUDING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public FluidExtrudingCategory(IGuiHelper guiHelper) {
        super(
                FluidExtrudingRecipe.class,
                UID,
                "fluid_extruding",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 55, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EXTRUDER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidExtrudingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 26, 51, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        builder.addSlot(RecipeIngredientRole.CATALYST, 11, 12)
                .setFluidRenderer(8000, false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFirstFluid());

        builder.addSlot(RecipeIngredientRole.CATALYST, 52, 12)
                .setFluidRenderer(8000, false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getSecondFluid());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(FluidExtrudingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
