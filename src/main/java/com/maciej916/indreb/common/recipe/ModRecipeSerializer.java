package com.maciej916.indreb.common.recipe;

import com.maciej916.indreb.common.recipe.impl.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static final RegistryObject<RecipeSerializer<CrushingRecipe>> CRUSHING = SERIALIZERS.register("crushing", () -> CrushingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CompressingRecipe>> COMPRESSING = SERIALIZERS.register("compressing", () -> CompressingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<ExtractingRecipe>> EXTRACTING = SERIALIZERS.register("extracting", () -> ExtractingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<SawingRecipe>> SAWING = SERIALIZERS.register("sawing", () -> SawingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<FluidExtrudingRecipe>> FLUID_EXTRUDING = SERIALIZERS.register("fluid_extruding", () -> FluidExtrudingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CanningRecipe>> CANNING = SERIALIZERS.register("canning", () -> CanningRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<FluidEnrichingRecipe>> FLUID_ENRICHING = SERIALIZERS.register("fluid_enriching", () -> FluidEnrichingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<RecyclingRecipe>> RECYCLING = SERIALIZERS.register("recycling", () -> RecyclingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<RollingRecipe>> ROLLING = SERIALIZERS.register("rolling", () -> RollingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CuttingRecipe>> CUTTING = SERIALIZERS.register("cutting", () -> CuttingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<ExtrudingRecipe>> EXTRUDING = SERIALIZERS.register("extruding", () -> ExtrudingRecipe.SERIALIZER);

    public static final RegistryObject<RecipeSerializer<AlloySmeltingRecipe>> ALLOY_SMELTING = SERIALIZERS.register("alloy_smelting", () -> AlloySmeltingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<FermentingRecipe>> FERMENTING = SERIALIZERS.register("fermenting", () -> FermentingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<OreWashingRecipe>> ORE_WASHING = SERIALIZERS.register("ore_washing", () -> OreWashingRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<ThermalCentrifugingRecipe>> THERMAL_CENTRIFUGING = SERIALIZERS.register("thermal_centrifuging", () -> ThermalCentrifugingRecipe.SERIALIZER);


    public static final RegistryObject<RecipeSerializer<ScrapBoxRecipe>> SCRAP_BOX = SERIALIZERS.register("scrap_box", () -> ScrapBoxRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<MatterAmplifierRecipe>> MATTER_AMPLIFIER = SERIALIZERS.register("matter_amplifier", () -> MatterAmplifierRecipe.SERIALIZER);


//    public static final RegistryObject<RecipeSerializer<ScannerRecipe>> SCANNER = SERIALIZERS.register("scanner", () -> ScannerRecipe.SERIALIZER);

//
//    public static final RegistryObject<RecipeSerializer<AdvancedShapedRecipe>> ADVANCED_SHAPED = SERIALIZERS.register("advanced_shaped", () -> AdvancedShapedRecipe.SERIALIZER);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
