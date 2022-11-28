package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.FluidEnrichingRecipe;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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

public class FluidEnrichingCategory extends AbstractRecipeCategory<FluidEnrichingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.FLUID_ENRICHING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public FluidEnrichingCategory(IGuiHelper guiHelper) {
        super(
                FluidEnrichingRecipe.class,
                UID,
                "fluid_enriching",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE, 0, 165, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FLUID_ENRICHER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidEnrichingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 0, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        IngredientCount ingredientCount = recipe.getIngredientCount();
        IngredientCountStack countStack = ingredientCount.getIngredientStack(0);
        List<ItemStack> inputItems = Stream.of(countStack.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack.getCount())).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 8, 19).addItemStacks(inputItems);

        builder.addSlot(RecipeIngredientRole.CATALYST, 50, 12)
                .setFluidRenderer(ServerConfig.fluid_enricher_fluid_capacity.get(), false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 12)
                .setFluidRenderer(ServerConfig.fluid_enricher_fluid_capacity.get(), false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidOutput());
    }

    @Override
    public void draw(FluidEnrichingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 6, 19);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
