package com.maciej916.indreb.integration.jei;

import com.google.common.base.Stopwatch;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockEntityAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.MenuAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.ScreenAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockEntityCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.MenuCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.ScreenCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockEntityCompressor;
import com.maciej916.indreb.common.block.impl.machines.compressor.MenuCompressor;
import com.maciej916.indreb.common.block.impl.machines.compressor.ScreenCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockEntityCrusher;
import com.maciej916.indreb.common.block.impl.machines.crusher.MenuCrusher;
import com.maciej916.indreb.common.block.impl.machines.crusher.ScreenCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.ScreenElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockEntityExtractor;
import com.maciej916.indreb.common.block.impl.machines.extractor.MenuExtractor;
import com.maciej916.indreb.common.block.impl.machines.extractor.ScreenExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.ScreenExtruder;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockEntityFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.MenuFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.ScreenFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.ScreenIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.ore_washing_plant.MenuOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machines.ore_washing_plant.ScreenOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockEntityRecycler;
import com.maciej916.indreb.common.block.impl.machines.recycler.MenuRecycler;
import com.maciej916.indreb.common.block.impl.machines.recycler.ScreenRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockEntitySawmill;
import com.maciej916.indreb.common.block.impl.machines.sawmill.MenuSawmill;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ScreenSawmill;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.BlockEntityThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.MenuThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.ScreenThermalCentrifuge;
import com.maciej916.indreb.common.recipe.impl.*;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModMenuTypes;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.screen.GuiHandler;
import com.maciej916.indreb.common.screen.PanelScreen;
import com.maciej916.indreb.integration.jei.category.impl.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(IndReb.MODID, IndReb.MODID);
    private static final Logger LOGGER = LogManager.getLogger(IndReb.MODID + " JEI Integration");

    public static final RecipeType<CrushingRecipe> CRUSHING_TYPE = new RecipeType<>(CrushingCategory.UID, CrushingRecipe.class);
    public static final RecipeType<CompressingRecipe> COMPRESSING_TYPE = new RecipeType<>(CompressingCategory.UID, CompressingRecipe.class);
    public static final RecipeType<ExtractingRecipe> EXTRACTING_TYPE = new RecipeType<>(ExtractingCategory.UID, ExtractingRecipe.class);
    public static final RecipeType<ExtrudingRecipe> EXTRUDING_TYPE = new RecipeType<>(ExtrudingCategory.UID, ExtrudingRecipe.class);
    public static final RecipeType<SawingRecipe> SAWING_TYPE = new RecipeType<>(SawingCategory.UID, SawingRecipe.class);
    public static final RecipeType<AlloySmeltingRecipe> ALLOY_SMELTING_TYPE = new RecipeType<>(AlloySmeltingCategory.UID, AlloySmeltingRecipe.class);
    public static final RecipeType<RecyclingRecipe> RECYCLING_TYPE = new RecipeType<>(RecyclingCategory.UID, RecyclingRecipe.class);
    public static final RecipeType<CanningRecipe> CANNING_TYPE = new RecipeType<>(CanningCategory.UID, CanningRecipe.class);
    public static final RecipeType<FluidEnrichingRecipe> FLUID_ENRICHING_TYPE = new RecipeType<>(FluidEnrichingCategory.UID, FluidEnrichingRecipe.class);
    public static final RecipeType<OreWashingRecipe> ORE_WASHING_TYPE = new RecipeType<>(OreWashingCateggory.UID, OreWashingRecipe.class);
    public static final RecipeType<ThermalCentrifugingRecipe> THERMAL_CENTRIFUGING_TYPE = new RecipeType<>(ThermalCentrifugingCategory.UID, ThermalCentrifugingRecipe.class);

    public static final RecipeType<ScrapBoxRecipe> SCRAP_BOX_TYPE = new RecipeType<>(ScrapBoxCategory.UID, ScrapBoxRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IRON_FURNACE.get()), RecipeTypes.SMELTING);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE.get()), RecipeTypes.SMELTING);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER.get()), CRUSHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR.get()), COMPRESSING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR.get()), EXTRACTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRUDER.get()), EXTRUDING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL.get()), SAWING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER.get()), ALLOY_SMELTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RECYCLER.get()), RECYCLING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CANNING_MACHINE.get()), CANNING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLUID_ENRICHER.get()), FLUID_ENRICHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ORE_WASHING_PLANT.get()), ORE_WASHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.THERMAL_CENTRIFUGE.get()), THERMAL_CENTRIFUGING_TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper ScreenHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new CrushingCategory(ScreenHelper));
        registration.addRecipeCategories(new CompressingCategory(ScreenHelper));
        registration.addRecipeCategories(new ExtractingCategory(ScreenHelper));
        registration.addRecipeCategories(new ExtrudingCategory(ScreenHelper));
        registration.addRecipeCategories(new SawingCategory(ScreenHelper));
        registration.addRecipeCategories(new AlloySmeltingCategory(ScreenHelper));
        registration.addRecipeCategories(new RecyclingCategory(ScreenHelper));
        registration.addRecipeCategories(new CanningCategory(ScreenHelper));
        registration.addRecipeCategories(new FluidEnrichingCategory(ScreenHelper));
        registration.addRecipeCategories(new OreWashingCateggory(ScreenHelper));
        registration.addRecipeCategories(new ThermalCentrifugingCategory(ScreenHelper));

        registration.addRecipeCategories(new ScrapBoxCategory(ScreenHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Level world = Minecraft.getInstance().level;
        RecipeManager recipeManager = world.getRecipeManager();
        Stopwatch sw = Stopwatch.createStarted();

        IIngredientManager ingredientManager = registration.getIngredientManager();
        ingredientManager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, List.of(ModItems.DEBUG_STICK.get().getDefaultInstance()));

        registration.addRecipes(CRUSHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CRUSHING.get()));
        registration.addRecipes(COMPRESSING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.COMPRESSING.get()));
        registration.addRecipes(EXTRACTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.EXTRACTING.get()));
        registration.addRecipes(EXTRUDING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.EXTRUDING.get()));
        registration.addRecipes(SAWING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SAWING.get()));
        registration.addRecipes(ALLOY_SMELTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ALLOY_SMELTING.get()));
        registration.addRecipes(RECYCLING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.RECYCLING.get()));
        registration.addRecipes(CANNING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CANNING.get()));
        registration.addRecipes(FLUID_ENRICHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.FLUID_ENRICHING.get()));
        registration.addRecipes(ORE_WASHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ORE_WASHING.get()));
        registration.addRecipes(THERMAL_CENTRIFUGING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.THERMAL_CENTRIFUGING.get()));

        registration.addRecipes(SCRAP_BOX_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SCRAP_BOX.get()));

        sw.stop();
        LOGGER.info("Loaded jei recipe integration in {}", sw);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(PanelScreen.class, new GuiHandler<>());

        registration.addRecipeClickArea(ScreenIronFurnace.class, 80, 35, 24, 16, RecipeTypes.SMELTING);
        registration.addRecipeClickArea(ScreenElectricFurnace.class, 71, 35, 24, 16, RecipeTypes.SMELTING);
        registration.addRecipeClickArea(ScreenCrusher.class, 71, 35, 24, 16, CRUSHING_TYPE);
        registration.addRecipeClickArea(ScreenCompressor.class, 71, 35, 24, 16, COMPRESSING_TYPE);
        registration.addRecipeClickArea(ScreenExtractor.class, 71, 35, 24, 16, EXTRACTING_TYPE);
        registration.addRecipeClickArea(ScreenExtruder.class, 78, 35, 24, 16, EXTRUDING_TYPE);
        registration.addRecipeClickArea(ScreenSawmill.class, 71, 35, 24, 16, SAWING_TYPE);
        registration.addRecipeClickArea(ScreenAlloySmelter.class, 82, 33, 24, 16, ALLOY_SMELTING_TYPE);
        registration.addRecipeClickArea(ScreenRecycler.class, 71, 35, 24, 16, RECYCLING_TYPE);
        registration.addRecipeClickArea(ScreenCanningMachine.class, 76, 35, 24, 16, CANNING_TYPE);
        registration.addRecipeClickArea(ScreenFluidEnricher.class, 76, 35, 24, 16, FLUID_ENRICHING_TYPE);
        registration.addRecipeClickArea(ScreenOreWashingPlant.class, 90, 32, 19, 19, ORE_WASHING_TYPE);
        registration.addRecipeClickArea(ScreenThermalCentrifuge.class, 82, 33, 24, 16, THERMAL_CENTRIFUGING_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(MenuCrusher.class, ModMenuTypes.CRUSHER.get(), CRUSHING_TYPE, BlockEntityCrusher.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuCompressor.class, ModMenuTypes.COMPRESSOR.get(), COMPRESSING_TYPE, BlockEntityCompressor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuExtractor.class, ModMenuTypes.EXTRACTOR.get(), EXTRACTING_TYPE, BlockEntityExtractor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuSawmill.class, ModMenuTypes.SAWMILL.get(), SAWING_TYPE, BlockEntitySawmill.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuAlloySmelter.class, ModMenuTypes.ALLOY_SMELTER.get(), ALLOY_SMELTING_TYPE, BlockEntityAlloySmelter.INPUT_SLOT_0, 3, 0, 37);
        registration.addRecipeTransferHandler(MenuRecycler.class, ModMenuTypes.RECYCLER.get(), RECYCLING_TYPE, BlockEntityRecycler.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuCanningMachine.class, ModMenuTypes.CANNING_MACHINE.get(), CANNING_TYPE, BlockEntityCanningMachine.INPUT_SLOT_0, 2, 1, 37);
        registration.addRecipeTransferHandler(MenuFluidEnricher.class, ModMenuTypes.FLUID_ENRICHER.get(), FLUID_ENRICHING_TYPE, BlockEntityFluidEnricher.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuOreWashingPlant.class, ModMenuTypes.ORE_WASHING_PLANT.get(), ORE_WASHING_TYPE, BlockEntityRecycler.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(MenuThermalCentrifuge.class, ModMenuTypes.THERMAL_CENTRIFUGE.get(), THERMAL_CENTRIFUGING_TYPE, BlockEntityThermalCentrifuge.INPUT_SLOT, 1, 1, 37);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(ModItems.NANO_SABER.get());
        registration.useNbtForSubtypes(ModItems.NANO_HELMET.get());
        registration.useNbtForSubtypes(ModItems.NANO_CHESTPLATE.get());
        registration.useNbtForSubtypes(ModItems.NANO_LEGGINGS.get());
        registration.useNbtForSubtypes(ModItems.NANO_BOOTS.get());

        registration.useNbtForSubtypes(ModItems.FLUID_CELL.get());
        registration.useNbtForSubtypes(ModItems.FOAM_SPRAYER.get());

        registration.useNbtForSubtypes(ModItems.ELECTRIC_HOE.get());
        registration.useNbtForSubtypes(ModItems.ELECTRIC_WRENCH.get());
        registration.useNbtForSubtypes(ModItems.ELECTRIC_TREETAP.get());
        registration.useNbtForSubtypes(ModItems.MULTI_TOOL.get());

        registration.useNbtForSubtypes(ModItems.MINING_DRILL.get());
        registration.useNbtForSubtypes(ModItems.DIAMOND_DRILL.get());
        registration.useNbtForSubtypes(ModItems.IRIDIUM_DRILL.get());

        registration.useNbtForSubtypes(ModItems.CHAINSAW.get());
        registration.useNbtForSubtypes(ModItems.DIAMOND_CHAINSAW.get());
        registration.useNbtForSubtypes(ModItems.IRIDIUM_CHAINSAW.get());

        registration.useNbtForSubtypes(ModItems.BATTERY.get());
        registration.useNbtForSubtypes(ModItems.ADVANCED_BATTERY.get());
        registration.useNbtForSubtypes(ModItems.ENERGY_CRYSTAL.get());
        registration.useNbtForSubtypes(ModItems.LAPOTRON_CRYSTAL.get());

        registration.useNbtForSubtypes(ModItems.CHARGING_BATTERY.get());
        registration.useNbtForSubtypes(ModItems.ADVANCED_CHARGING_BATTERY.get());
        registration.useNbtForSubtypes(ModItems.CHARGING_ENERGY_CRYSTAL.get());
        registration.useNbtForSubtypes(ModItems.CHARGING_LAPOTRON_CRYSTAL.get());
    }
}
