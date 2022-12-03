package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.OreWashingRecipe;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.*;

public class OreWashingCategory extends AbstractRecipeCategory<OreWashingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.ORE_WASHING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public OreWashingCategory(IGuiHelper guiHelper) {
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

        builder.addSlot(RecipeIngredientRole.CATALYST, 11, 12).setFluidRenderer(ServerConfig.ore_washing_plant_fluid_capacity.get(), false, 8, 29)
                .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount()));

        IngredientCount ingredientCount = recipe.getIngredientCount();
        IngredientCountStack countStack = ingredientCount.getIngredientStack(0);
        List<ItemStack> inputItems = Stream.of(countStack.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack.getCount())).toList();

        builder.addSlot(RecipeIngredientRole.INPUT, 49, 19).addItemStacks(inputItems);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, recipe.hasBonus() ? 11 : 19).addItemStack(recipe.getResultItem());

        if (recipe.hasBonus()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 28).addItemStacks(recipe.getChanceResultItemStacks()).addTooltipCallback(new RecipeSlotTooltipCallback(recipe));
        }
    }

    @Override
    public void draw(OreWashingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 2, 17);
        this.energy.draw(poseStack, halfX + 58, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private record RecipeSlotTooltipCallback(OreWashingRecipe recipe) implements IRecipeSlotTooltipCallback {

        @Override
        public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
            Optional<ItemStack> displayedStack = recipeSlotView.getDisplayedItemStack();

            if (displayedStack.isPresent()) {
                float chance = recipe.getChanceForStack(displayedStack.get());
                if (chance < 100) {
                    tooltip.add(Component.translatable(EnumLang.CHANCE.getTranslationKey(), Component.literal((Math.round(recipe.getChanceForStack(displayedStack.get()) * 100.0) / 100.0) + "%").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }
}
