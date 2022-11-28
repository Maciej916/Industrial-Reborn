package com.maciej916.indreb.common.api.recipe.lib;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
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

    public static FluidStack fluidFromJson(JsonObject object) {
        FluidStack stack = FluidStack.EMPTY;
        ResourceLocation fluidResource = new ResourceLocation(GsonHelper.getAsString(object, "fluid", "minecraft:empty"));
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidResource);
        if (fluid != null) {
            stack = new FluidStack(fluid, GsonHelper.getAsInt(object, "amount", 1));
        }

        return stack;
    }

    public static JsonObject fluidToJson(FluidStack stack) {
        JsonObject result = new JsonObject();
        result.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(stack.getFluid()).toString());
        if (stack.getAmount() > 1) {
            result.addProperty("amount", stack.getAmount());
        }

        return result;
    }

}
