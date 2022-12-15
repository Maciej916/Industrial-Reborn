package com.maciej916.indreb.integration.jei;

import com.google.common.base.Stopwatch;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.screen.WidgetScreen;
import com.maciej916.indreb.common.api.screen.WidgetScreenHandler;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.block.impl.machine.t_advanced.matter_fabricator.ScreenMatterFabricator;
import com.maciej916.indreb.common.block.impl.machine.t_basic.canning_machine.BlockEntityCanningMachine;
import com.maciej916.indreb.common.block.impl.machine.t_basic.canning_machine.MenuCanningMachine;
import com.maciej916.indreb.common.block.impl.machine.t_basic.canning_machine.ScreenCanningMachine;
import com.maciej916.indreb.common.block.impl.machine.t_basic.compressor.BlockEntityCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.compressor.MenuCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.compressor.ScreenCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.crusher.BlockEntityCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.crusher.MenuCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.crusher.ScreenCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.electric_furnace.BlockEntityElectricFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_basic.electric_furnace.MenuElectricFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_basic.electric_furnace.ScreenElectricFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extractor.BlockEntityExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extractor.MenuExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extractor.ScreenExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_basic.extruder.ScreenExtruder;
import com.maciej916.indreb.common.block.impl.machine.t_basic.fluid_enricher.BlockEntityFluidEnricher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.fluid_enricher.MenuFluidEnricher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.fluid_enricher.ScreenFluidEnricher;
import com.maciej916.indreb.common.block.impl.machine.t_basic.metal_former.BlockEntityMetalFormer;
import com.maciej916.indreb.common.block.impl.machine.t_basic.metal_former.MenuMetalFormer;
import com.maciej916.indreb.common.block.impl.machine.t_basic.metal_former.ScreenMetalFormer;
import com.maciej916.indreb.common.block.impl.machine.t_basic.recycler.BlockEntityRecycler;
import com.maciej916.indreb.common.block.impl.machine.t_basic.recycler.MenuRecycler;
import com.maciej916.indreb.common.block.impl.machine.t_basic.recycler.ScreenRecycler;
import com.maciej916.indreb.common.block.impl.machine.t_basic.sawmill.BlockEntitySawmill;
import com.maciej916.indreb.common.block.impl.machine.t_basic.sawmill.MenuSawmill;
import com.maciej916.indreb.common.block.impl.machine.t_basic.sawmill.ScreenSawmill;
import com.maciej916.indreb.common.block.impl.machine.t_simple.iron_furnace.ScreenIronFurnace;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_compressor.BlockEntitySimpleCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_compressor.MenuSimpleCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_compressor.ScreenSimpleCompressor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher.BlockEntitySimpleCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher.MenuSimpleCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher.ScreenSimpleCrusher;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_extractor.BlockEntitySimpleExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_extractor.MenuSimpleExtractor;
import com.maciej916.indreb.common.block.impl.machine.t_simple.simple_extractor.ScreenSimpleextractor;
import com.maciej916.indreb.common.block.impl.machine.t_standard.alloy_smelter.BlockEntityAlloySmelter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.alloy_smelter.MenuAlloySmelter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.alloy_smelter.ScreenAlloySmelter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.fermenter.ScreenFermenter;
import com.maciej916.indreb.common.block.impl.machine.t_standard.ore_washing_plant.BlockEntityOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machine.t_standard.ore_washing_plant.MenuOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machine.t_standard.ore_washing_plant.ScreenOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machine.t_standard.thermal_centrifuge.BlockEntityThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machine.t_standard.thermal_centrifuge.MenuThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machine.t_standard.thermal_centrifuge.ScreenThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.ScreenScanner;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.*;
import com.maciej916.indreb.common.screen.ModMenuTypes;
import com.maciej916.indreb.integration.jei.category.impl.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(IndReb.MODID, IndReb.MODID);
    private static final Logger LOGGER = LogManager.getLogger(IndReb.MODID + " JEI Integration");

    public static final RecipeType<CrushingRecipe> CRUSHING_TYPE = new RecipeType<>(CrushingCategory.UID, CrushingRecipe.class);
    public static final RecipeType<CompressingRecipe> COMPRESSING_TYPE = new RecipeType<>(CompressingCategory.UID, CompressingRecipe.class);
    public static final RecipeType<ExtractingRecipe> EXTRACTING_TYPE = new RecipeType<>(ExtractingCategory.UID, ExtractingRecipe.class);
    public static final RecipeType<SawingRecipe> SAWING_TYPE = new RecipeType<>(SawingCategory.UID, SawingRecipe.class);
    public static final RecipeType<FluidExtrudingRecipe> FLUID_EXTRUDING_TYPE = new RecipeType<>(FluidExtrudingCategory.UID, FluidExtrudingRecipe.class);
    public static final RecipeType<CanningRecipe> CANNING_TYPE = new RecipeType<>(CanningCategory.UID, CanningRecipe.class);
    public static final RecipeType<FluidEnrichingRecipe> FLUID_ENRICHING_TYPE = new RecipeType<>(FluidEnrichingCategory.UID, FluidEnrichingRecipe.class);
    public static final RecipeType<RecyclingRecipe> RECYCLING_TYPE = new RecipeType<>(RecyclingCategory.UID, RecyclingRecipe.class);
    public static final RecipeType<RollingRecipe> ROLLING_TYPE = new RecipeType<>(RollingCategory.UID, RollingRecipe.class);
    public static final RecipeType<CuttingRecipe> CUTTING_TYPE = new RecipeType<>(CuttingCategory.UID, CuttingRecipe.class);
    public static final RecipeType<ExtrudingRecipe> EXTRUDING_TYPE = new RecipeType<>(ExtrudingCategory.UID, ExtrudingRecipe.class);

    public static final RecipeType<AlloySmeltingRecipe> ALLOY_SMELTING_TYPE = new RecipeType<>(AlloySmeltingCategory.UID, AlloySmeltingRecipe.class);
    public static final RecipeType<FermentingRecipe> FERMENTING_TYPE = new RecipeType<>(FermentingCategory.UID, FermentingRecipe.class);
    public static final RecipeType<OreWashingRecipe> ORE_WASHING_TYPE = new RecipeType<>(OreWashingCategory.UID, OreWashingRecipe.class);
    public static final RecipeType<ThermalCentrifugingRecipe> THERMAL_CENTRIFUGING_TYPE = new RecipeType<>(ThermalCentrifugingCategory.UID, ThermalCentrifugingRecipe.class);

    public static final RecipeType<ScanningRecipe> SCANNING_TYPE = new RecipeType<>(ScanningCategory.UID, ScanningRecipe.class);

    public static final RecipeType<ScrapBoxRecipe> SCRAP_BOX_TYPE = new RecipeType<>(ScrapBoxCategory.UID, ScrapBoxRecipe.class);
    public static final RecipeType<MatterAmplifierRecipe> MATTER_AMPLIFIER_TYPE = new RecipeType<>(MatterAmplifierCategory.UID, MatterAmplifierRecipe.class);


    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IRON_FURNACE.get()), RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE.get()), RecipeTypes.SMELTING);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIMPLE_CRUSHER.get()), CRUSHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIMPLE_COMPRESSOR.get()), COMPRESSING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIMPLE_EXTRACTOR.get()), EXTRACTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL.get()), SAWING_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER.get()), CRUSHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR.get()), COMPRESSING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR.get()), EXTRACTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRUDER.get()), FLUID_EXTRUDING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CANNING_MACHINE.get()), CANNING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLUID_ENRICHER.get()), FLUID_ENRICHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RECYCLER.get()), RECYCLING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.METAL_FORMER.get()), ROLLING_TYPE, CUTTING_TYPE, EXTRUDING_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER.get()), ALLOY_SMELTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FERMENTER.get()), FERMENTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.THERMAL_CENTRIFUGE.get()), THERMAL_CENTRIFUGING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ORE_WASHING_PLANT.get()), ORE_WASHING_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SCANNER.get()), SCANNING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.REPLICATOR.get()), SCANNING_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MATTER_FABRICATOR.get()), MATTER_AMPLIFIER_TYPE);

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper ScreenHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new CrushingCategory(ScreenHelper));
        registration.addRecipeCategories(new CompressingCategory(ScreenHelper));
        registration.addRecipeCategories(new ExtractingCategory(ScreenHelper));
        registration.addRecipeCategories(new SawingCategory(ScreenHelper));
        registration.addRecipeCategories(new FluidExtrudingCategory(ScreenHelper));
        registration.addRecipeCategories(new CanningCategory(ScreenHelper));
        registration.addRecipeCategories(new FluidEnrichingCategory(ScreenHelper));
        registration.addRecipeCategories(new RecyclingCategory(ScreenHelper));
        registration.addRecipeCategories(new RollingCategory(ScreenHelper));
        registration.addRecipeCategories(new CuttingCategory(ScreenHelper));
        registration.addRecipeCategories(new ExtrudingCategory(ScreenHelper));

        registration.addRecipeCategories(new AlloySmeltingCategory(ScreenHelper));
        registration.addRecipeCategories(new FermentingCategory(ScreenHelper));
        registration.addRecipeCategories(new OreWashingCategory(ScreenHelper));
        registration.addRecipeCategories(new ThermalCentrifugingCategory(ScreenHelper));

        registration.addRecipeCategories(new ScanningCategory(ScreenHelper));

        registration.addRecipeCategories(new ScrapBoxCategory(ScreenHelper));
        registration.addRecipeCategories(new MatterAmplifierCategory(ScreenHelper));

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
        registration.addRecipes(SAWING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SAWING.get()));
        registration.addRecipes(FLUID_EXTRUDING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.FLUID_EXTRUDING.get()));
        registration.addRecipes(CANNING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CANNING.get()));
        registration.addRecipes(FLUID_ENRICHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.FLUID_ENRICHING.get()));
        registration.addRecipes(RECYCLING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.RECYCLING.get()));
        registration.addRecipes(ROLLING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ROLLING.get()));
        registration.addRecipes(CUTTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CUTTING.get()));
        registration.addRecipes(EXTRUDING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.EXTRUDING.get()));

        registration.addRecipes(ALLOY_SMELTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ALLOY_SMELTING.get()));
        registration.addRecipes(FERMENTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.FERMENTING.get()));
        registration.addRecipes(ORE_WASHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ORE_WASHING.get()));
        registration.addRecipes(THERMAL_CENTRIFUGING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.THERMAL_CENTRIFUGING.get()));

        registration.addRecipes(SCANNING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SCANNING.get()));

        registration.addRecipes(SCRAP_BOX_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SCRAP_BOX.get()));
        registration.addRecipes(MATTER_AMPLIFIER_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.MATTER_AMPLIFIER.get()));

        sw.stop();
        LOGGER.info("Loaded jei recipe integration in {}", sw);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(WidgetScreen.class, new WidgetScreenHandler<>());

        registration.addRecipeClickArea(ScreenIronFurnace.class, 80, 35, 24, 16, RecipeTypes.SMELTING);
        registration.addRecipeClickArea(ScreenElectricFurnace.class, 71, 35, 24, 16, RecipeTypes.SMELTING);

        registration.addRecipeClickArea(ScreenSimpleCrusher.class, 79, 35, 24, 16, CRUSHING_TYPE);
        registration.addRecipeClickArea(ScreenSimpleCompressor.class, 79, 35, 24, 16, COMPRESSING_TYPE);
        registration.addRecipeClickArea(ScreenSimpleextractor.class, 79, 35, 24, 16, EXTRACTING_TYPE);

        registration.addRecipeClickArea(ScreenCrusher.class, 71, 35, 24, 16, CRUSHING_TYPE);
        registration.addRecipeClickArea(ScreenCompressor.class, 71, 35, 24, 16, COMPRESSING_TYPE);
        registration.addRecipeClickArea(ScreenExtractor.class, 71, 35, 24, 16, EXTRACTING_TYPE);
        registration.addRecipeClickArea(ScreenSawmill.class, 71, 35, 24, 16, SAWING_TYPE);
        registration.addRecipeClickArea(ScreenExtruder.class, 78, 35, 24, 16, FLUID_EXTRUDING_TYPE);
        registration.addRecipeClickArea(ScreenCanningMachine.class, 76, 35, 24, 16, CANNING_TYPE);
        registration.addRecipeClickArea(ScreenFluidEnricher.class, 82, 35, 24, 16, FLUID_ENRICHING_TYPE);
        registration.addRecipeClickArea(ScreenRecycler.class, 71, 35, 24, 16, RECYCLING_TYPE);
        registration.addRecipeClickArea(ScreenMetalFormer.class, 71, 34, 24, 18, ROLLING_TYPE, CUTTING_TYPE, EXTRUDING_TYPE);

        registration.addRecipeClickArea(ScreenAlloySmelter.class, 82, 33, 24, 16, ALLOY_SMELTING_TYPE);
        registration.addRecipeClickArea(ScreenFermenter.class, 76, 35, 24, 16, FERMENTING_TYPE);
        registration.addRecipeClickArea(ScreenOreWashingPlant.class, 90, 32, 19, 19, ORE_WASHING_TYPE);
        registration.addRecipeClickArea(ScreenThermalCentrifuge.class, 73, 33, 24, 16, THERMAL_CENTRIFUGING_TYPE);
        registration.addRecipeClickArea(ScreenMatterFabricator.class, 56, 34, 70, 11, MATTER_AMPLIFIER_TYPE);

        registration.addRecipeClickArea(ScreenScanner.class, 7, 15, 61, 9, SCANNING_TYPE);
        registration.addRecipeClickArea(ScreenScanner.class, 51, 15, 18, 9, SCANNING_TYPE);
        registration.addRecipeClickArea(ScreenScanner.class, 7, 24, 18, 26, SCANNING_TYPE);
        registration.addRecipeClickArea(ScreenScanner.class, 51, 24, 18, 26, SCANNING_TYPE);
        registration.addRecipeClickArea(ScreenScanner.class, 7, 50, 61, 9, SCANNING_TYPE);


    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(MenuElectricFurnace.class, ModMenuTypes.ELECTRIC_FURNACE.get(), RecipeTypes.SMELTING, BlockEntityElectricFurnace.INPUT_SLOT, 1, BlockEntityElectricFurnace.OUTPUT_SLOT, 36);

        registration.addRecipeTransferHandler(MenuSimpleCrusher.class, ModMenuTypes.SIMPLE_CRUSHER.get(), CRUSHING_TYPE, 0, 1, BlockEntitySimpleCrusher.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuSimpleCompressor.class, ModMenuTypes.SIMPLE_COMPRESSOR.get(), COMPRESSING_TYPE, 0, 1, BlockEntitySimpleCompressor.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuSimpleExtractor.class, ModMenuTypes.SIMPLE_EXTRACTOR.get(), EXTRACTING_TYPE, 0, 1, BlockEntitySimpleExtractor.BONUS_SLOT, 36);

        registration.addRecipeTransferHandler(MenuCrusher.class, ModMenuTypes.CRUSHER.get(), CRUSHING_TYPE, BlockEntityCrusher.INPUT_SLOT, 1, BlockEntityCrusher.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuCompressor.class, ModMenuTypes.COMPRESSOR.get(), COMPRESSING_TYPE, BlockEntityCompressor.INPUT_SLOT, 1, BlockEntityCompressor.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuExtractor.class, ModMenuTypes.EXTRACTOR.get(), EXTRACTING_TYPE, BlockEntityExtractor.INPUT_SLOT, 1, BlockEntityExtractor.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuSawmill.class, ModMenuTypes.SAWMILL.get(), SAWING_TYPE, BlockEntitySawmill.INPUT_SLOT, 1, BlockEntitySawmill.BONUS_SLOT, 36);
        registration.addRecipeTransferHandler(MenuCanningMachine.class, ModMenuTypes.CANNING_MACHINE.get(), CANNING_TYPE, BlockEntityCanningMachine.INPUT_SLOT_0, 2, BlockEntityCanningMachine.OUTPUT_SLOT, 36);
        registration.addRecipeTransferHandler(MenuFluidEnricher.class, ModMenuTypes.FLUID_ENRICHER.get(), FLUID_ENRICHING_TYPE, BlockEntityFluidEnricher.INPUT_SLOT, 1, BlockEntityFluidEnricher.DRAIN_DOWN, 36);
        registration.addRecipeTransferHandler(MenuRecycler.class, ModMenuTypes.RECYCLER.get(), RECYCLING_TYPE, BlockEntityRecycler.INPUT_SLOT, 1, BlockEntityRecycler.OUTPUT_SLOT, 36);
        registration.addRecipeTransferHandler(MenuMetalFormer.class, ModMenuTypes.METAL_FORMER.get(), ROLLING_TYPE, BlockEntityMetalFormer.INPUT_SLOT, 1, BlockEntityMetalFormer.OUTPUT_SLOT, 36);
        registration.addRecipeTransferHandler(MenuMetalFormer.class, ModMenuTypes.METAL_FORMER.get(), CUTTING_TYPE, BlockEntityMetalFormer.INPUT_SLOT, 1, BlockEntityMetalFormer.OUTPUT_SLOT, 36);
        registration.addRecipeTransferHandler(MenuMetalFormer.class, ModMenuTypes.METAL_FORMER.get(), EXTRUDING_TYPE, BlockEntityMetalFormer.INPUT_SLOT, 1, BlockEntityMetalFormer.OUTPUT_SLOT, 36);

        registration.addRecipeTransferHandler(MenuAlloySmelter.class, ModMenuTypes.ALLOY_SMELTER.get(), ALLOY_SMELTING_TYPE, BlockEntityAlloySmelter.INPUT_SLOT_0, 3, BlockEntityAlloySmelter.OUTPUT_SLOT, 36);
        registration.addRecipeTransferHandler(MenuOreWashingPlant.class, ModMenuTypes.ORE_WASHING_PLANT.get(), ORE_WASHING_TYPE, BlockEntityOreWashingPlant.INPUT_SLOT, 1, BlockEntityOreWashingPlant.OUTPUT_SLOT_2 + 6, 36);
        registration.addRecipeTransferHandler(MenuThermalCentrifuge.class, ModMenuTypes.THERMAL_CENTRIFUGE.get(), THERMAL_CENTRIFUGING_TYPE, BlockEntityThermalCentrifuge.INPUT_SLOT, 1, BlockEntityThermalCentrifuge.OUTPUT_SLOT_2 + 6, 36);

    }

    private static final IIngredientSubtypeInterpreter<ItemStack> INDREB_NBT_INTERPRETER = (stack, context) -> {
        if (context == UidContext.Ingredient && stack.hasTag()) {
            String nbtRepresentation = getEnergyComponent(stack);
            nbtRepresentation = addInterpretation(nbtRepresentation, getFluidComponent(stack));
            return nbtRepresentation;
        }
        return IIngredientSubtypeInterpreter.NONE;
    };


    private static String getEnergyComponent(ItemStack stack) {
        Optional<IEnergyStorage> capability = stack.getCapability(ModCapabilities.ENERGY).resolve();
        if (capability.isPresent()) {
            IEnergyStorage energyHandlerItem = capability.get();
            String component = "";

            if (energyHandlerItem.energyStored() == energyHandlerItem.maxEnergy()) {
                component = addInterpretation(component, "filled");
            } else {
                component = addInterpretation(component, "empty");
            }

            return component;
        }
        return IIngredientSubtypeInterpreter.NONE;
    }

    private static String getFluidComponent(ItemStack stack) {
        Optional<IFluidHandlerItem> cap = FluidUtil.getFluidHandler(stack).resolve();
        if (cap.isPresent()) {
            IFluidHandlerItem handler = cap.get();
            String component = "";
            for (int tank = 0, tanks = handler.getTanks(); tank < tanks; tank++) {
                FluidStack fluidStack = handler.getFluidInTank(tank);
                if (!fluidStack.isEmpty()) {
                    component = addInterpretation(component, ForgeRegistries.FLUIDS.getKey(fluidStack.getFluid()).toString());
                } else if (tanks > 1) {
                    component = addInterpretation(component, "empty");
                }
            }
            return component;
        }
        return IIngredientSubtypeInterpreter.NONE;
    }

    private static String addInterpretation(String nbtRepresentation, String component) {
        return nbtRepresentation.isEmpty() ? component : nbtRepresentation + ":" + component;
    }
    public static void registerItemSubtypes(ISubtypeRegistration registry, Collection<RegistryObject<Item>> itemProviders) {
        for (RegistryObject<Item> itemRegistryObject : itemProviders) {
            Item item = itemRegistryObject.get();
            ItemStack itemStack = new ItemStack(item);
            if (
                    itemStack.getCapability(ModCapabilities.ENERGY).isPresent() ||
                    FluidUtil.getFluidHandler(itemStack).isPresent()
            ) {
                registry.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, item, INDREB_NBT_INTERPRETER);
            }
        }
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registry) {
        registerItemSubtypes(registry, ModItems.ITEMS.getEntries());
    }
}
