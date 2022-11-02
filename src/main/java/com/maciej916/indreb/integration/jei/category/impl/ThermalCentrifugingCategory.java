package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.recipe.impl.ThermalCentrifugingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maciej916.indreb.common.util.Constants.JEI;
import static com.maciej916.indreb.common.util.Constants.PROCESS;

public class ThermalCentrifugingCategory extends AbstractRecipeCategory<ThermalCentrifugingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.THERMAL_CENTRIFUGING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public ThermalCentrifugingCategory(IGuiHelper guiHelper) {
        super(
                ThermalCentrifugingRecipe.class,
                UID,
                "thermal_centrifuging",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 117, 55, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.THERMAL_CENTRIFUGE.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ThermalCentrifugingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).peek(itemStack -> itemStack.setCount(recipe.getIngredientCount())).toList());
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(list);

        int resultSize = 0;
        for(ItemStack stack : recipe.getResults()) {
            resultSize += !stack.isEmpty() ? 1 : 0;
        }

        int startPos;
        if (resultSize == 2) {
            startPos = 11;
        } else {
            startPos = 19;
        }

        int i = 0;
        for(ItemStack stack : recipe.getResults()) {
            if (!stack.isEmpty()) {
                builder.addSlot(RecipeIngredientRole.OUTPUT, 65, (i * 16 + (i == 0 ? 0 : 1)) +  startPos).addItemStack(stack);
                i++;
            }
        }
    }

    @Override
    public void draw(ThermalCentrifugingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTemperature() + "C", 3, 39, 0.75f, 0xb31313, false);
        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
