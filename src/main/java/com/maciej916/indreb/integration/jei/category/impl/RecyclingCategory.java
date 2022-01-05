package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.receipe.impl.RecyclingRecipe;
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
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.JEI;
import static com.maciej916.indreb.common.util.Constants.PROCESS;

public class RecyclingCategory extends AbstractRecipeCategory<RecyclingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.RECYCLING.getRegistryName();

    protected IDrawableAnimated progress;
    protected IDrawableAnimated energy;

    public RecyclingCategory(IGuiHelper guiHelper) {
        super(
                RecyclingRecipe.class,
                UID,
                "recycling",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 117, 0, 114, 54),
                () -> guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.RECYCLER))
        );
    }

    @Override
    public void setIngredients(RecyclingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getIngredients().stream().map(ingredient -> Arrays.asList(ingredient.getItems())).collect(Collectors.toList()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RecyclingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.addTooltipCallback(new TooltipCallback(recipe));

        guiItemStacks.init(0, true, 8, 18);
        guiItemStacks.init(1, false, halfX + 7, 18);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).collect(Collectors.toList()));
        }

        guiItemStacks.set(0, list);
        guiItemStacks.set(1, recipe.getResultItem());

        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 85, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(RecyclingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private class TooltipCallback implements ITooltipCallback<ItemStack> {
        private final RecyclingRecipe recipe;

        TooltipCallback(RecyclingRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<Component> tooltip) {
            if (slotIndex == 1) {
                tooltip.add(new TranslatableComponent(EnumLang.CHANCE.getTranslationKey(), new TextComponent((Math.round(recipe.getChance() * 100.0) / 100.0) + "%").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}
