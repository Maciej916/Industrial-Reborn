package com.maciej916.indreb.common.api.recipe.lib;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class ResultItem {

    public static JsonObject stackToJson(ItemStack stack) {
        JsonObject result = new JsonObject();
        result.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
        if (stack.getCount() > 1) {
            result.addProperty("count", stack.getCount());
        }
        return result;
    }

}
