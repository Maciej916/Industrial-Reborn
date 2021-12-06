package com.maciej916.indreb.common.receipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeChanceResult {

    private List<ChanceResult> results = new ArrayList<>();
    private int totalWeight = 0;

    public RecipeChanceResult(ChanceResult bonusResult) {
        results.add(bonusResult);
        totalWeight += bonusResult.getChance();
    }

    public RecipeChanceResult() {
        for (ChanceResult element : results) {
            totalWeight += element.getChance();
        }
    }

    public RecipeChanceResult addChanceResult(ItemStack itemStack, float chance) {
        totalWeight += chance;
        results.add(new ChanceResult(itemStack, chance));
        return this;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getResultsCount() {
        return results.size();
    }

    public List<ChanceResult> getResults() {
        return results;
    }

    public ItemStack rollResult() {
        int position = new Random().nextInt(Math.max(totalWeight, 100));
        if (position >= totalWeight) {
            return ItemStack.EMPTY;
        }

        for (ChanceResult result : results) {
            if (position < result.getChance()) {
                return result.getStack().copy();
            }
            position -= result.getChance();
        }

        throw new IllegalStateException("Should never get here!");
    }

    public static RecipeChanceResult read(FriendlyByteBuf buffer) {
        int mapSize = buffer.readInt();
        RecipeChanceResult chanceRecipeResult = new RecipeChanceResult();
        for (int i = 0; i < mapSize; i++) {
            ItemStack stack = buffer.readItem();
            float chance = buffer.readFloat();
            chanceRecipeResult.addChanceResult(stack, chance);
        }
        return chanceRecipeResult;
    }

    public static FriendlyByteBuf write(FriendlyByteBuf buffer, RecipeChanceResult chanceResult) {
        buffer.writeInt(chanceResult.getResultsCount());
        chanceResult.getResults().forEach((arr) -> {
            buffer.writeItemStack(arr.getStack(), false);
            buffer.writeFloat(arr.getChance());
        });
        return buffer;
    }

}
