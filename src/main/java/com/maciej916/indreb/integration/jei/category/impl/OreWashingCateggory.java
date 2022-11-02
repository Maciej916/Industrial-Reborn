package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.recipe.impl.AlloySmeltingRecipe;
import com.maciej916.indreb.common.recipe.impl.OreWashingRecipe;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModRecipeSerializer;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.maciej916.indreb.common.util.Constants.*;

public class OreWashingCateggory extends AbstractRecipeCategory<OreWashingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.ORE_WASHING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public OreWashingCateggory(IGuiHelper guiHelper) {
        super(
                OreWashingRecipe.class,
                UID,
                "ore_washing",
                guiHelper,
                () -> guiHelper.createDrawable(JEI_LARGE_2, 0, 0, 152, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ORE_WASHING_PLANT.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, OreWashingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 20, 102, 19, 19).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        List<ItemStack> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.addAll(Arrays.stream(ingredient.getItems()).peek(itemStack -> itemStack.setCount(recipe.getIngredientCount())).toList());
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 19).addItemStacks(list);

        builder.addSlot(RecipeIngredientRole.CATALYST, 11, 12).setFluidRenderer(ServerConfig.ore_washing_plant_fluid_capacity.get(), false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount()));

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
                builder.addSlot(RecipeIngredientRole.OUTPUT, 103, (i * 16 + (i == 0 ? 0 : 1)) +  startPos).addItemStack(stack);
                i++;
            }
        }
    }

    @Override
    public void draw(OreWashingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 2, 17);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getPowerCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }
}
