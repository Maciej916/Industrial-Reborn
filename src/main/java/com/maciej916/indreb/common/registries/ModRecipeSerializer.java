package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.receipe.impl.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maciej916.indreb.IndReb.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModRecipeSerializer {

    public static RecipeSerializer<CrushingRecipe> CRUSHING;
    public static RecipeSerializer<CompressingRecipe> COMPRESSING;
    public static RecipeSerializer<ExtractingRecipe> EXTRACTING;
    public static RecipeSerializer<SawingRecipe> SAWING;
    public static RecipeSerializer<ExtrudingRecipe> EXTRUDING;
    public static RecipeSerializer<AlloySmeltingRecipe> ALLOY_SMELTING;
    public static RecipeSerializer<RecyclingRecipe> RECYCLING;

    public static RecipeSerializer<ScrapBoxRecipe> SCRAP_BOX;

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {

        CRUSHING = registerRecipeSerializer("crushing", CrushingRecipe.SERIALIZER);
        COMPRESSING = registerRecipeSerializer("compressing", CompressingRecipe.SERIALIZER);
        EXTRACTING = registerRecipeSerializer("extracting", ExtractingRecipe.SERIALIZER);
        SAWING = registerRecipeSerializer("sawing", SawingRecipe.SERIALIZER);
        EXTRUDING = registerRecipeSerializer("extruding", ExtrudingRecipe.SERIALIZER);
        ALLOY_SMELTING = registerRecipeSerializer("alloy_smelting", AlloySmeltingRecipe.SERIALIZER);
        RECYCLING = registerRecipeSerializer("recycling", RecyclingRecipe.SERIALIZER);

        SCRAP_BOX = registerRecipeSerializer("scrap_box", ScrapBoxRecipe.SERIALIZER);
    }

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerRecipeSerializer(String name, S recipeSerializer) {
        recipeSerializer.setRegistryName(new ResourceLocation(MODID, name));
        ForgeRegistries.RECIPE_SERIALIZERS.register(recipeSerializer);
        return recipeSerializer;
    }

}
