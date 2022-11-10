package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.recipe.impl.CuttingRecipe;
import com.maciej916.indreb.common.recipe.impl.RollingRecipe;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maciej916.indreb.common.util.Constants.*;

public class CuttingCategory extends AbstractRecipeCategory<CuttingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.CUTTING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public CuttingCategory(IGuiHelper guiHelper) {
        super(
                CuttingRecipe.class,
                UID,
                "cutting",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_2, 0, 110, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.METAL_FORMER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CuttingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 213, 24, 18).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).peek(itemStack -> itemStack.setCount(recipe.getIngredientCount())).toList());
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(list);
        builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(CuttingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 18);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private record RecipeSlotTooltipCallback(CuttingRecipe recipe) implements IRecipeSlotTooltipCallback {

        @Override
        public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {

        }
    }
}
