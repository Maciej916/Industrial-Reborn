package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.MatterAmplifierRecipe;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

import static com.maciej916.indreb.common.util.Constants.JEI_2;

public class MatterAmplifierCategory extends AbstractRecipeCategory<MatterAmplifierRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.MATTER_AMPLIFIER.getId();

    public MatterAmplifierCategory(IGuiHelper guiHelper) {
        super(
                MatterAmplifierRecipe.class,
                UID,
                "matter_amplifier",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_2, 0, 220, 116, 28),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.SCRAP.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MatterAmplifierRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 6).addItemStacks(Arrays.stream(recipe.getIngredients().get(0).getItems()).toList());
    }

    @Override
    public void draw(MatterAmplifierRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".text_amplifier").getString() + " " + recipe.getAmplifiedAmount(), 34, 10, 0.75f, 0x7E7E7E, false);
    }
}
