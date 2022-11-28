package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.CanningRecipe;
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

public class CanningCategory extends AbstractRecipeCategory<CanningRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.CANNING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public CanningCategory(IGuiHelper guiHelper) {
        super(
                CanningRecipe.class,
                UID,
                "canning",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 110, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CANNING_MACHINE.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CanningRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        IngredientCount ingredientCount = recipe.getIngredientCount();
        IngredientCountStack firstIngredient = ingredientCount.getIngredientStack(0);
        IngredientCountStack secondIngredient = ingredientCount.getIngredientStack(1);

        List<ItemStack> firstItems = Stream.of(firstIngredient.ingredient().getItems()).peek(itemStack -> itemStack.setCount(firstIngredient.getCount())).toList();
        List<ItemStack> secondItems = Stream.of(secondIngredient.ingredient().getItems()).peek(itemStack -> itemStack.setCount(secondIngredient.getCount())).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 8, 19).addItemStacks(firstItems);
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 19).addItemStacks(secondItems);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(CanningRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
