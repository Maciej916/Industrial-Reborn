package com.maciej916.indreb.common.api.recipe.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maciej916.indreb.IndReb;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChanceResult {

    private final List<ChanceResultStack> results = new ArrayList<>();
    private float totalWeight = 0;

    public ChanceResult(ChanceResultStack bonusResult) {
        results.add(bonusResult);
        totalWeight += bonusResult.rawChance();
    }

    public ChanceResult() {}

    public ChanceResult addChanceResult(ItemStack itemStack, float chance) {
        totalWeight += chance;
        results.add(new ChanceResultStack(itemStack, chance));
        return this;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public int getResultsCount() {
        return results.size();
    }

    public List<ChanceResultStack> getResults() {
        return results;
    }

    public ItemStack rollResult() {
        float position = new Random().nextFloat(Math.max(totalWeight, 100));
        if (position >= totalWeight) {
            return ItemStack.EMPTY;
        }

        for (ChanceResultStack result : results) {
            if (position < result.rawChance()) {
                return result.stack();
            }
            position -= result.rawChance();
        }

        IndReb.LOGGER.info("Should never be here, recipe chance result {}", results);

        return ItemStack.EMPTY;
    }

    public JsonArray toJson() {
        JsonArray array = new JsonArray();
        for (ChanceResultStack stack : results) {
            JsonObject object = new JsonObject();
            object.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.stack().getItem()).toString());
            object.addProperty("count", stack.getCount());
            object.addProperty("chance", stack.rawChance());
            array.add(object);
        }

        return array;
    }

    public static ChanceResult fromJson(JsonArray ingredients) {
        ChanceResult chanceResult = new ChanceResult();

        for (int i = 0; i < ingredients.size(); i++) {
            JsonObject object = ingredients.get(i).getAsJsonObject();
            ItemStack itemStack = ShapedRecipe.itemStackFromJson(object);
            itemStack.setCount(GsonHelper.getAsInt(object, "count", 1));
            float chance = GsonHelper.getAsFloat(object, "chance", 100);
            chanceResult.addChanceResult(itemStack, chance);
        }

        return chanceResult;
    }


    public static ChanceResult fromNetwork(FriendlyByteBuf buffer) {
        int mapSize = buffer.readInt();
        ChanceResult chanceRecipeResult = new ChanceResult();
        for (int i = 0; i < mapSize; i++) {
            chanceRecipeResult.addChanceResult(buffer.readItem(), buffer.readFloat());
        }
        return chanceRecipeResult;
    }

    public FriendlyByteBuf toNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(results.size());
        results.forEach((arr) -> {
            buffer.writeItemStack(arr.stack(), false);
            buffer.writeFloat(arr.rawChance());
        });
        return buffer;
    }

    @Override
    public String toString() {
        return "ChanceResult{" +
                "results=" + results +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
