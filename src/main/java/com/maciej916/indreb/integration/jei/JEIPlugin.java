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
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.screen.GuiHandler;
import com.maciej916.indreb.common.screen.PanelScreen;
import com.maciej916.indreb.common.util.RecipeUtil;
import com.maciej916.indreb.integration.jei.category.impl.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(IndReb.MODID, IndReb.MODID);
    private static final Logger LOGGER = LogManager.getLogger(IndReb.MODID + " JEI Integration");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IRON_FURNACE), VanillaRecipeCategoryUid.FURNACE);

        // custom Screen
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE), VanillaRecipeCategoryUid.FURNACE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER), CrushingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR), CompressingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRACTOR), ExtractingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EXTRUDER), ExtrudingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL), SawingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER), AlloySmeltingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.RECYCLER), RecyclingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CANNING_MACHINE), CanningCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLUID_ENRICHER), FluidEnrichingCategory.UID);
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
        ingredientManager.removeIngredientsAtRuntime(VanillaTypes.ITEM, List.of(ModItems.DEBUG_STICK.getDefaultInstance()));

        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.CRUSHING), CrushingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.COMPRESSING), CompressingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.EXTRACTING), ExtractingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.EXTRUDING), ExtrudingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.SAWING), SawingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.ALLOY_SMELTING), AlloySmeltingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.RECYCLING), RecyclingCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.CANNING), CanningCategory.UID);
        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.FLUID_ENRICHING), FluidEnrichingCategory.UID);

        registration.addRecipes(RecipeUtil.getRecipes(recipeManager, ModRecipeType.SCRAP_BOX), ScrapBoxCategory.UID);

        sw.stop();
        LOGGER.info("Loaded jei recipe integration in {}", sw);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(PanelScreen.class, new GuiHandler());

        registration.addRecipeClickArea(ScreenIronFurnace.class, 80, 35, 24, 16, VanillaRecipeCategoryUid.FURNACE);
        registration.addRecipeClickArea(ScreenElectricFurnace.class, 71, 35, 24, 16, VanillaRecipeCategoryUid.FURNACE);
        registration.addRecipeClickArea(ScreenCrusher.class, 71, 35, 24, 16, CrushingCategory.UID);
        registration.addRecipeClickArea(ScreenCompressor.class, 71, 35, 24, 16, CompressingCategory.UID);
        registration.addRecipeClickArea(ScreenExtractor.class, 71, 35, 24, 16, ExtractingCategory.UID);
        registration.addRecipeClickArea(ScreenExtruder.class, 78, 35, 24, 16, ExtrudingCategory.UID);
        registration.addRecipeClickArea(ScreenSawmill.class, 71, 35, 24, 16, SawingCategory.UID);
        registration.addRecipeClickArea(ScreenAlloySmelter.class, 82, 33, 24, 16, AlloySmeltingCategory.UID);
        registration.addRecipeClickArea(ScreenRecycler.class, 71, 35, 24, 16, RecyclingCategory.UID);
        registration.addRecipeClickArea(ScreenCanningMachine.class, 76, 35, 24, 16, CanningCategory.UID);
        registration.addRecipeClickArea(ScreenFluidEnricher.class, 76, 35, 24, 16, FluidEnrichingCategory.UID);

    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(ContainerCrusher.class, CrushingCategory.UID, BlockEntityCrusher.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerCompressor.class, CompressingCategory.UID, BlockEntityCompressor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerExtractor.class, ExtractingCategory.UID, BlockEntityExtractor.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerSawmill.class, SawingCategory.UID, BlockEntitySawmill.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerAlloySmelter.class, AlloySmeltingCategory.UID, BlockEntityAlloySmelter.INPUT_SLOT_0, 3, 0, 37);
        registration.addRecipeTransferHandler(ContainerRecycler.class, RecyclingCategory.UID, BlockEntityRecycler.INPUT_SLOT, 1, 1, 37);
        registration.addRecipeTransferHandler(ContainerCanningMachine.class, CanningCategory.UID, BlockEntityCanningMachine.INPUT_SLOT_0, 2, 1, 37);
        registration.addRecipeTransferHandler(ContainerFluidEnricher.class, FluidEnrichingCategory.UID, BlockEntityFluidEnricher.INPUT_SLOT, 1, 1, 37);
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

