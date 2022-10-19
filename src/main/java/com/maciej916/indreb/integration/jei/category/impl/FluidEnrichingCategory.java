package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.recipe.impl.FluidEnrichingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
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
import net.minecraftforge.fluids.FluidStack;

import static com.maciej916.indreb.common.util.Constants.*;

public class FluidEnrichingCategory extends AbstractRecipeCategory<FluidEnrichingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.FLUID_ENRICHING.getRegistryName();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public FluidEnrichingCategory(IGuiHelper guiHelper) {
        super(
                FluidEnrichingRecipe.class,
                UID,
                "fluid_enriching",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 165, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FLUID_ENRICHER))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidEnrichingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        builder.addSlot(RecipeIngredientRole.INPUT, 8, 19).addItemStack(new ItemStack(recipe.getIngredient().getItems()[0].getItem(), recipe.getIngredientCount()));
        builder.addSlot(RecipeIngredientRole.CATALYST, 50, 12).setFluidRenderer(8000, false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 12).setFluidRenderer(8000, false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(recipe.getResult().getFluid(), recipe.getResult().getAmount()));
    }

    @Override
    public void draw(FluidEnrichingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
