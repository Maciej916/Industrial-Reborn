package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.recipe.impl.ScannerRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
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

public class ScannerCategory extends AbstractRecipeCategory<ScannerRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.SCANNER.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public ScannerCategory(IGuiHelper guiHelper) {
        super(
                ScannerRecipe.class,
                UID,
                "scanner",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE_2, 0, 55, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SCANNER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ScannerRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 62, 122, 61, 42).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).toList());
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 23, 19).addItemStacks(list);
    }

    @Override
    public void draw(ScannerRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 76, 5);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, -2, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.replication_cost").getString(), 67, 18, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + recipe.getMatterCost() + " mB", 67, 25, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedEnergyUnit(recipe.getEnergyCost()) + " IE", 67, 32, 0.65f, 0x00a200, false);

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
