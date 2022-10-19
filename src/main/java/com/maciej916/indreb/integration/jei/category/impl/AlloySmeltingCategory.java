package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.recipe.impl.AlloySmeltingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Map;

import static com.maciej916.indreb.common.util.Constants.*;

public class AlloySmeltingCategory extends AbstractRecipeCategory<AlloySmeltingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.ALLOY_SMELTING.getRegistryName();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;
    private IDrawableAnimated fire;

    public AlloySmeltingCategory(IGuiHelper guiHelper) {
        super(
                AlloySmeltingRecipe.class,
                UID,
                "alloy_smelting",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 0, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALLOY_SMELTER))
        );
    }

    @Override
    public RecipeType<AlloySmeltingRecipe> getRecipeType() {
        return new RecipeType<>(UID, getRecipeClass());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmeltingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
        this.fire = guiHelper.drawableBuilder(PROCESS, 67, 0, 16,  16).buildAnimated(100, IDrawableAnimated.StartDirection.TOP, true);

        int i = 0;
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredientMap().entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer count = entry.getValue();
            IRecipeSlotBuilder slot = switch (i++) {
                case 0 -> builder.addSlot(RecipeIngredientRole.INPUT, 5, 19);
                case 1 -> builder.addSlot(RecipeIngredientRole.INPUT, 26, 7);
                default -> builder.addSlot(RecipeIngredientRole.INPUT, 47, 19);
            };

            slot.addItemStacks(Arrays.stream(ingredient.getItems()).peek(itemStack -> itemStack.setCount(count)).toList());
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(AlloySmeltingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);
        this.fire.draw(poseStack, halfX - 49, 27);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
