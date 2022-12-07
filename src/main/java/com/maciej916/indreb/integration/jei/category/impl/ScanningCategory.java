package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.ScanningRecipe;
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

import java.util.List;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.*;

public class ScanningCategory extends AbstractRecipeCategory<ScanningRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.SCANNING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public ScanningCategory(IGuiHelper guiHelper) {
        super(
                ScanningRecipe.class,
                UID,
                "scanning",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE_2, 0, 55, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SCANNER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ScanningRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 62, 122, 61, 42).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        IngredientCount ingredientCount = recipe.getIngredientCount();
        IngredientCountStack countStack = ingredientCount.getIngredientStack(0);
        List<ItemStack> inputItems = Stream.of(countStack.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack.getCount())).toList();

        builder.addSlot(RecipeIngredientRole.OUTPUT, 23, 19).addItemStacks(inputItems);
    }

    @Override
    public void draw(ScanningRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 76, 5);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, -2, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.replication_cost").getString(), 67, 18, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.matter_cost").getString() + " " + TextComponentUtil.getFormattedStorageUnit(recipe.getMatterCost(), true) + " mB", 67, 25, 0.65f, 0x00a200, false);
        GuiUtil.renderScaled(poseStack, Component.translatable("gui." + IndReb.MODID + ".scanner.energy_cost").getString() + " " + TextComponentUtil.getFormattedStorageUnit(recipe.getEnergyCost(), true) + " IE", 67, 32, 0.65f, 0x00a200, false);

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
