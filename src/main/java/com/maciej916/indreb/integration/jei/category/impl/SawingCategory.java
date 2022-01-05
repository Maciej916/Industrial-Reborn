package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.receipe.impl.SawingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.maciej916.indreb.common.util.Constants.JEI;
import static com.maciej916.indreb.common.util.Constants.PROCESS;

public class SawingCategory extends AbstractRecipeCategory<SawingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.SAWING.getRegistryName();

    protected IDrawableAnimated progress;
    protected IDrawableAnimated energy;

    public SawingCategory(IGuiHelper guiHelper) {
        super(
                SawingRecipe.class,
                UID,
                "sawing",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 0, 165, 114, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.SAWMILL))
        );
    }

    @Override
    public void setIngredients(SawingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getIngredients().stream().map(ingredient -> Arrays.asList(ingredient.getItems())).collect(Collectors.toList()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SawingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.addTooltipCallback(new TooltipCallback(recipe));

        guiItemStacks.init(0, true, 8, 18);
        guiItemStacks.init(1, false, halfX + 7, 5);

        if (recipe.hasBonus()) {
            guiItemStacks.init(2, false, halfX + 7, 28);
            guiItemStacks.set(2, recipe.getBonusItem());
        }

        int i = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            guiItemStacks.set(i++, Arrays.stream(ingredient.getItems())
                    .map(s -> {
                        ItemStack stack = s.copy();
                        stack.setCount(recipe.getIngredientCount());
                        return stack;
                    })
                    .collect(Collectors.toList())
            );
        }

        guiItemStacks.set(1, recipe.getResultItem());

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 68, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(SawingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }
        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private class TooltipCallback implements ITooltipCallback<ItemStack> {
        private final SawingRecipe recipe;

        TooltipCallback(SawingRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<Component> tooltip) {
            if (slotIndex == 2 && recipe.hasBonus()) {
                tooltip.add(new TranslatableComponent(EnumLang.CHANCE.getTranslationKey(), (Math.round(recipe.getBonus().getChance() * 100.0) / 100.0) + "%").withStyle(ChatFormatting.BLUE));
            }
        }
    }
}
