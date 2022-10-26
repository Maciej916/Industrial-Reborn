package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.recipe.impl.RecyclingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maciej916.indreb.common.util.Constants.JEI;
import static com.maciej916.indreb.common.util.Constants.PROCESS;

public class RecyclingCategory extends AbstractRecipeCategory<RecyclingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.RECYCLING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public RecyclingCategory(IGuiHelper guiHelper) {
        super(
                RecyclingRecipe.class,
                UID,
                "recycling",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 117, 0, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.RECYCLER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecyclingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 85, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).toList());
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(list);
        builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 19).addItemStack(recipe.getResultItem()).addTooltipCallback(new RecipeSlotTooltipCallback(recipe));
    }

    @Override
    public void draw(RecyclingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private record RecipeSlotTooltipCallback(RecyclingRecipe recipe) implements IRecipeSlotTooltipCallback {

        @Override
        public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
            tooltip.add(Component.translatable(EnumLang.CHANCE.getTranslationKey(),Component.literal((Math.round(recipe.getChance() * 100.0) / 100.0) + "%").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
