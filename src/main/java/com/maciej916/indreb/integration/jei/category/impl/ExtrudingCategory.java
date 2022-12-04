package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.ExtrudingRecipe;
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

import java.util.List;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.*;

public class ExtrudingCategory extends AbstractRecipeCategory<ExtrudingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.EXTRUDING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public ExtrudingCategory(IGuiHelper guiHelper) {
        super(
                ExtrudingRecipe.class,
                UID,
                "extruding",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_2, 0, 0, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.METAL_FORMER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtrudingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 26, 175, 24, 18).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        IngredientCount ingredientCount = recipe.getIngredientCount();
        IngredientCountStack countStack = ingredientCount.getIngredientStack(0);
        List<ItemStack> inputItems = Stream.of(countStack.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack.getCount())).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(inputItems);
        builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(ExtrudingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 18);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
