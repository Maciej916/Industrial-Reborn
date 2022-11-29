package com.maciej916.indreb.integration.jei.category.impl;

import com.maciej916.indreb.common.api.recipe.lib.IngredientCount;
import com.maciej916.indreb.common.api.recipe.lib.IngredientCountStack;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.recipe.ModRecipeSerializer;
import com.maciej916.indreb.common.recipe.impl.RecyclingRecipe;
import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.integration.jei.category.AbstractRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.maciej916.indreb.common.util.Constants.JEI;
import static com.maciej916.indreb.common.util.Constants.PROCESS;

public class RecyclingCategory extends AbstractRecipeCategory<RecyclingRecipe> {

    public static final ResourceLocation UID = ModRecipeSerializer.RECYCLING.getId();

    private IDrawableAnimated progress;
    private IDrawableAnimated energy;

    public RecyclingCategory(IGuiHelper guiHelper) {
        super(
                RecyclingRecipe.class,
                UID,
                "recycling",
                guiHelper,
                () -> guiHelper.createDrawable(JEI, 117, 0, 114, 54),
                () -> guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.RECYCLER.get()))
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecyclingRecipe recipe, IFocusGroup focuses) {
        this.progress = guiHelper.drawableBuilder(PROCESS, 25, 85, 24, 16).buildAnimated(recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
        this.energy = guiHelper.drawableBuilder(JEI, 249, 0, 7, 37).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);

        // TODO: Overkill?
        IngredientCount ingredientCount = recipe.getIngredientCount();
        List<ItemStack> inputItems = new ArrayList<>();
        if (ingredientCount.getIngredientStack(0).ingredient() == Ingredient.EMPTY) {
            List<Item> itemList = new ArrayList<>(ForgeRegistries.ITEMS.getValues().stream().toList());
            IngredientCount ingredientCountExcluded = recipe.getIngredientsExcluded();
            for (int i = 0; i < ingredientCountExcluded.getSize(); i++) {
                IngredientCountStack countStack1 = ingredientCountExcluded.getIngredientStack(i);
                List<ItemStack> icStack = Stream.of(countStack1.ingredient().getItems()).toList();
                for (ItemStack stack : icStack) {
                    itemList.remove(stack.getItem());
                }
            }
            inputItems = itemList.stream().map(ItemStack::new).toList();
        } else {
            for (int i = 0; i < ingredientCount.getSize(); i++) {
                IngredientCountStack countStack1 = ingredientCount.getIngredientStack(i);
                List<ItemStack> icStack = Stream.of(countStack1.ingredient().getItems()).peek(itemStack -> itemStack.setCount(countStack1.getCount())).toList();
                for (ItemStack stack : icStack) {
                    if (!inputItems.contains(stack)) {
                        inputItems.add(stack);
                    }
                }
            }
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 19).addItemStacks(inputItems);

        int resultSize = recipe.getResultItem().getCount();
        List<ItemStack> resultItems = new ArrayList<>();
        if (resultSize > 1) {

            for (int i = resultSize; i >= 1; i--) {
                ItemStack newStack = recipe.getResultItem();
                newStack.setCount(i);
                resultItems.add(newStack);
            }

//            for (int i = 1; i <= resultSize; i++) {
//                ItemStack newStack = recipe.getResultItem();
//                newStack.setCount(i);
//                resultItems.add(newStack);
//            }
        } else {
            resultItems.add(recipe.getResultItem());
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, halfX + 8, 19).addItemStacks(resultItems).addTooltipCallback(new RecipeSlotTooltipCallback(recipe));
    }

    @Override
    public void draw(RecyclingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.progress.draw(poseStack, halfX - 24, 19);
        this.energy.draw(poseStack, halfX + 39, 7);

        if (recipe.getExperience() > 0) {
            GuiUtil.renderScaled(poseStack, recipe.getExperience() + " XP", 0, 0, 0.75f, 0x7E7E7E, false);
        }

        GuiUtil.renderScaled(poseStack, recipe.getTickEnergyCost() + " IE/T", 0, 48, 0.75f, 0x7E7E7E, false);
    }

    private record RecipeSlotTooltipCallback(RecyclingRecipe recipe) implements IRecipeSlotTooltipCallback {

        @Override
        public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
            Optional<ItemStack> displayedStack = recipeSlotView.getDisplayedItemStack();
            displayedStack.ifPresent(stack -> tooltip.add(Component.translatable(EnumLang.CHANCE.getTranslationKey(), Component.literal((Math.round(recipe.getChance() * 100.0) / 100.0) + "%").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY)));
        }
    }
}
