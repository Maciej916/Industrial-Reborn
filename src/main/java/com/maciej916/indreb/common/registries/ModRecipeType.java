package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.recipe.impl.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModRecipeType {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, MODID);

    public static final RegistryObject<RecipeType<CrushingRecipe>> CRUSHING = register("crushing");
    public static final RegistryObject<RecipeType<CompressingRecipe>> COMPRESSING = register("compressing");
    public static final RegistryObject<RecipeType<ExtractingRecipe>> EXTRACTING = register("extracting");
    public static final RegistryObject<RecipeType<SawingRecipe>> SAWING = register("sawing");
    public static final RegistryObject<RecipeType<ExtrudingRecipe>> EXTRUDING = register("extruding");
    public static final RegistryObject<RecipeType<AlloySmeltingRecipe>> ALLOY_SMELTING = register("alloy_smelting");
    public static final RegistryObject<RecipeType<RecyclingRecipe>> RECYCLING = register("recycling");
    public static final RegistryObject<RecipeType<CanningRecipe>> CANNING = register("canning");
    public static final RegistryObject<RecipeType<FluidEnrichingRecipe>> FLUID_ENRICHING = register("fluid_enriching");

    public static final RegistryObject<RecipeType<ScrapBoxRecipe>> SCRAP_BOX = register("scrap_box");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(final String key) {
        return RECIPE_TYPES.register(key, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return key;
            }
        });
    }
}