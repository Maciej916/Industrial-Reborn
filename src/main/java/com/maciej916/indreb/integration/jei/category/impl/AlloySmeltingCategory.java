package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.AlloySmeltingRecipe;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.*;

public class AlloySmeltingCategory extends AbstractRecipeCategory<AlloySmeltingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.ALLOY_SMELTING.getId();

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
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALLOY_SMELTER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmeltingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 26, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
        this.fire = guiHelper.drawableBuilder(PROCESS, 67, 0, 16,  16).buildAnimated(100, IDrawableAnimated.StartDirection.BOTTOM, false);

        IngredientCount ingredientCount = recipe.getIngredientCount();
        for (int i = 0; i < ingredientCount.getSize(); i++) {
            IngredientCountStack countStack = ingredientCount.getIngredientStack(i);
            IRecipeSlotBuilder slot = switch (i) {
                case 0 -> builder.addSlot(RecipeIngredientRole.INPUT, 5, 19);
                case 1 -> builder.addSlot(RecipeIngredientRole.INPUT, 26, 7);
                default -> builder.addSlot(RecipeIngredientRole.INPUT, 47, 19);
            };

            List<ItemStack> items = Stream.of(countStack.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack.getCount())).toList();
            slot.addItemStacks(items);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 19).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(AlloySmeltingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);
        this.fire.draw(poseStack, halfX - 50, 27);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
