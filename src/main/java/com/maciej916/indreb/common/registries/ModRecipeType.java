package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.receipe.impl.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModRecipeType {

	public static RecipeType<CrushingRecipe> CRUSHING = register("crushing");
	public static RecipeType<CompressingRecipe> COMPRESSING = register("compressing");
	public static RecipeType<ExtractingRecipe> EXTRACTING = register("extracting");
	public static RecipeType<SawingRecipe> SAWING = register("sawing");
	public static RecipeType<ExtrudingRecipe> EXTRUDING = register("extruding");
	public static RecipeType<AlloySmeltingRecipe> ALLOY_SMELTING = register("alloy_smelting");
	public static RecipeType<RecyclingRecipe> RECYCLING = register("recycling");

	public static RecipeType<ScrapBoxRecipe> SCRAP_BOX = register("scrap_box");

	private static <T extends Recipe<?>> RecipeType<T> register(final String key) {
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MODID, key), new RecipeType<T>() {
			@Override
			public String toString() {
				return key;
			}
		});
	}
}