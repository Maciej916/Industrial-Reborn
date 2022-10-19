package com.maciej916.indreb.integration.jei;

import com.google.common.base.Stopwatch;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockEntityAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.ContainerAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.ScreenAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockEntityCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.ContainerCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.ScreenCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockEntityCompressor;
import com.maciej916.indreb.common.block.impl.machines.compressor.ContainerCompressor;
import com.maciej916.indreb.common.block.impl.machines.compressor.ScreenCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockEntityCrusher;
import com.maciej916.indreb.common.block.impl.machines.crusher.ContainerCrusher;
import com.maciej916.indreb.common.block.impl.machines.crusher.ScreenCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.ScreenElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockEntityExtractor;
import com.maciej916.indreb.common.block.impl.machines.extractor.ContainerExtractor;
import com.maciej916.indreb.common.block.impl.machines.extractor.ScreenExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.ScreenExtruder;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockEntityFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.ContainerFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.ScreenFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.ScreenIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockEntityRecycler;
import com.maciej916.indreb.common.block.impl.machines.recycler.ContainerRecycler;
import com.maciej916.indreb.common.block.impl.machines.recycler.ScreenRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockEntitySawmill;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ContainerSawmill;
import com.maciej916.indreb.common.block.impl.machines.sawmill.ScreenSawmill;
import com.maciej916.indreb.common.recipe.impl.*;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
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

    public static final RecipeType<ScrapBoxRecipe> SCRAP_BOX_TYPE = new RecipeType<>(ScrapBoxCategory.UID, ScrapBoxRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IRON_FURNACE), RecipeTypes.SMELTING);

        // custom Screen
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE), RecipeTypes.SMELTING);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER), CRUSHING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR), COMPRESSING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR), EXTRACTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRUDER), EXTRUDING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL), SAWING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER), ALLOY_SMELTING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RECYCLER), RECYCLING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CANNING_MACHINE), CANNING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLUID_ENRICHER), FLUID_ENRICHING_TYPE);
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

        registration.addRecipeCategories(new ScrapBoxCategory(ScreenHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Level world = Minecraft.getInstance().level;
        RecipeManager recipeManager = world.getRecipeManager();
        Stopwatch sw = Stopwatch.createStarted();

        IIngredientManager ingredientManager = registration.getIngredientManager();
        ingredientManager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, List.of(ModItems.DEBUG_STICK.getDefaultInstance()));

        registration.addRecipes(CRUSHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CRUSHING.get()));
        registration.addRecipes(COMPRESSING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.COMPRESSING.get()));
        registration.addRecipes(EXTRACTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.EXTRACTING.get()));
        registration.addRecipes(EXTRUDING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.EXTRUDING.get()));
        registration.addRecipes(SAWING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.SAWING.get()));
        registration.addRecipes(ALLOY_SMELTING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.ALLOY_SMELTING.get()));
        registration.addRecipes(RECYCLING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.RECYCLING.get()));
        registration.addRecipes(CANNING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.CANNING.get()));
        registration.addRecipes(FLUID_ENRICHING_TYPE, recipeManager.getAllRecipesFor(ModRecipeType.FLUID_ENRICHING.get()));

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
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(ContainerCrusher.class, CRUSHING_TYPE, BlockEntityCrusher.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerCompressor.class, COMPRESSING_TYPE, BlockEntityCompressor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerExtractor.class, EXTRACTING_TYPE, BlockEntityExtractor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerSawmill.class, SAWING_TYPE, BlockEntitySawmill.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerAlloySmelter.class, ALLOY_SMELTING_TYPE, BlockEntityAlloySmelter.INPUT_SLOT_0, 3, 0, 37);
        registration.addRecipeTransferHandler(ContainerRecycler.class, RECYCLING_TYPE, BlockEntityRecycler.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerCanningMachine.class, CANNING_TYPE, BlockEntityCanningMachine.INPUT_SLOT_0, 2, 1, 37);
        registration.addRecipeTransferHandler(ContainerFluidEnricher.class, FLUID_ENRICHING_TYPE, BlockEntityFluidEnricher.INPUT_SLOT, 1, 1, 37);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(ModItems.NANO_SABER);
        registration.useNbtForSubtypes(ModItems.NANO_HELMET);
        registration.useNbtForSubtypes(ModItems.NANO_CHESTPLATE);
        registration.useNbtForSubtypes(ModItems.NANO_LEGGINGS);
        registration.useNbtForSubtypes(ModItems.NANO_BOOTS);

        registration.useNbtForSubtypes(ModItems.FLUID_CELL);
        registration.useNbtForSubtypes(ModItems.FOAM_SPRAYER);

        registration.useNbtForSubtypes(ModItems.ELECTRIC_HOE);
        registration.useNbtForSubtypes(ModItems.ELECTRIC_WRENCH);
        registration.useNbtForSubtypes(ModItems.ELECTRIC_TREETAP);
        registration.useNbtForSubtypes(ModItems.MULTI_TOOL);

        registration.useNbtForSubtypes(ModItems.MINING_DRILL);
        registration.useNbtForSubtypes(ModItems.DIAMOND_DRILL);
        registration.useNbtForSubtypes(ModItems.IRIDIUM_DRILL);

        registration.useNbtForSubtypes(ModItems.CHAINSAW);
        registration.useNbtForSubtypes(ModItems.DIAMOND_CHAINSAW);
        registration.useNbtForSubtypes(ModItems.IRIDIUM_CHAINSAW);

        registration.useNbtForSubtypes(ModItems.BATTERY);
        registration.useNbtForSubtypes(ModItems.ADVANCED_BATTERY);
        registration.useNbtForSubtypes(ModItems.ENERGY_CRYSTAL);
        registration.useNbtForSubtypes(ModItems.LAPOTRON_CRYSTAL);

        registration.useNbtForSubtypes(ModItems.CHARGING_BATTERY);
        registration.useNbtForSubtypes(ModItems.ADVANCED_CHARGING_BATTERY);
        registration.useNbtForSubtypes(ModItems.CHARGING_ENERGY_CRYSTAL);
        registration.useNbtForSubtypes(ModItems.CHARGING_LAPOTRON_CRYSTAL);
    }
}
