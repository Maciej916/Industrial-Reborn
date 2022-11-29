package com.maciej916.indreb.common.api.recipe.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientCount {

    private int size;
    private final NonNullList<Ingredient> ingredients;
    private final NonNullList<Integer> ingredientsCount;

    public IngredientCount(int size) {
        this.size = size;
        this.ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
        this.ingredientsCount = NonNullList.withSize(size, 1);
    }

    public IngredientCount() {
        this.size = 0;
        this.ingredients = NonNullList.create();
        this.ingredientsCount = NonNullList.create();
    }

    public void setIngredient(int index, Ingredient ingredient, int count) {
        this.ingredients.set(index, ingredient);
        this.ingredientsCount.set(index, count);
    }

    public void addIngredient(Ingredient ingredient, int count) {
        this.ingredients.add(ingredient);
        this.ingredientsCount.add(count);
        size++;
    }

    public int getSize() {
        return size;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public NonNullList<Integer> getIngredientsCount() {
        return ingredientsCount;
    }

    public IngredientCountStack getIngredientStack(int index) {
        return new IngredientCountStack(this.ingredients.get(index), this.ingredientsCount.get(index));
    }

    public JsonArray toJson() {
        JsonArray array = new JsonArray();
        for (int i = 0; i < ingredients.size(); i++) {
            JsonElement json = ingredients.get(i).toJson();
            if (ingredientsCount.get(i) > 1) {
                json.getAsJsonObject().addProperty("count", ingredientsCount.get(i));
            }
            array.add(json);
        }
        return array;
    }

    public static IngredientCount fromJson(JsonArray ingredients) {
        IngredientCount ingredientCount = new IngredientCount(ingredients.size());

        for (int i = 0; i < ingredients.size(); i++) {
            JsonObject ingredient = ingredients.get(i).getAsJsonObject();
            ingredientCount.ingredients.set(i, Ingredient.fromJson(ingredient));
            ingredientCount.ingredientsCount.set(i, GsonHelper.getAsInt(ingredient, "count", 1));
        }

        return ingredientCount;
    }

    public static IngredientCount fromNetwork(FriendlyByteBuf buffer) {
        IngredientCount ingredientCount = new IngredientCount(buffer.readInt());

        for (int i = 0; i < ingredientCount.size; i++) {
            ingredientCount.ingredients.set(i, Ingredient.fromNetwork(buffer));
            ingredientCount.ingredientsCount.set(i, buffer.readInt());
        }

        return ingredientCount;
    }

    public FriendlyByteBuf toNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(size);

        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).toNetwork(buffer);
            buffer.writeInt(ingredientsCount.get(i));
        }

        return buffer;
    }

    @Override
    public String toString() {
        return "IngredientCount{" +
                "ingredients=" + ingredients +
                ", ingredientsCount=" + ingredientsCount +
                '}';
    }
}
