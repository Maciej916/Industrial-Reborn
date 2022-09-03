package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.recipe.impl.ExtractingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.*;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maciej916.indreb.common.util.Constants.*;

public class ExtractingCategory extends AbstractRecipeCategory<ExtractingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.EXTRACTING.getRegistryName();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public ExtractingCategory(IGuiHelper guiHelper) {
        super(
                ExtractingRecipe.class,
                UID,
                "extracting",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 0, 110, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EXTRACTOR))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtractingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 51, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).peek(itemStack -> itemStack.setCount(recipe.getIngredientCount())).toList());
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(list);
        builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 6).addItemStack(recipe.getResultItem());
        if (recipe.hasBonus()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 29).addItemStack(recipe.getBonusItem()).addTooltipCallback(new RecipeSlotTooltipCallback(recipe));
        }
    }

    @Override
    public void draw(ExtractingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private record RecipeSlotTooltipCallback(ExtractingRecipe recipe) implements IRecipeSlotTooltipCallback {

        @Override
        public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
            if (recipe.hasBonus()) {
                tooltip.add(new TranslatableComponent(EnumLang.CHANCE.getTranslationKey(), (Math.round(recipe.getBonus().chance() * 100.0) / 100.0) + "%").withStyle(ChatFormatting.BLUE));
            }
        }
    }
}
