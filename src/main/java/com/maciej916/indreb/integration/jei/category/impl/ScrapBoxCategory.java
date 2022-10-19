package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.recipe.impl.ScrapBoxRecipe;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

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
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.SCRAP_BOX))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ScrapBoxRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 6).addItemStack(new ItemStack(ModItems.SCRAP_BOX));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 65, 6).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(ScrapBoxRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        GuiUtil.renderScaled(poseStack, recipe.getDropChance() + " %", 33, 23, 0.75f, 0x7E7E7E, false);
    }
}
