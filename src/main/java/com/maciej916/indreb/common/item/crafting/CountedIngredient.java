package com.maciej916.indreb.common.item.crafting;

import com.google.gson.JsonElement;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;

public class CountedIngredient {

    protected Ingredient ingredient;
    protected int count;

    public static final CountedIngredient EMPTY = new CountedIngredient(Ingredient.EMPTY, 0);

    public CountedIngredient(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }

    public JsonElement toJson() {
        JsonElement json = ingredient.toJson();
        json.getAsJsonObject().addProperty("count", count);

        return json;
    }

    public static CountedIngredient fromNetwork(FriendlyByteBuf buffer) {
        CountedIngredient countedIngredient = new CountedIngredient(Ingredient.EMPTY, 1);

        countedIngredient.ingredient = Ingredient.fromNetwork(buffer);
        countedIngredient.count = buffer.readInt();

        return  countedIngredient;
    }

    public void toNetwork(FriendlyByteBuf buffer) {
        ingredient.toNetwork(buffer);
        buffer.writeInt(count);
    }

}
