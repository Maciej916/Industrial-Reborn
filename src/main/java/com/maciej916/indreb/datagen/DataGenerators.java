package com.maciej916.indreb.datagen;

import com.maciej916.indreb.datagen.loot.LootModifiers;
import com.maciej916.indreb.datagen.loot.LootTables;
import com.maciej916.indreb.datagen.recipe.provider.BlastingRecipeProvider;
import com.maciej916.indreb.datagen.recipe.provider.SmeltingRecipeProvider;
import com.maciej916.indreb.datagen.recipe.provider.StonecuttingRecipeProvider;
import com.maciej916.indreb.datagen.recipe.provider.crafting.*;
import com.maciej916.indreb.datagen.recipe.provider.custom.*;
import com.maciej916.indreb.datagen.tags.TagsBlock;
import com.maciej916.indreb.datagen.tags.TagsItem;
import com.maciej916.indreb.datagen.texture.BlockTextures;
import com.maciej916.indreb.datagen.texture.ItemTextures;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new BlockTextures(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ItemTextures(generator, event.getExistingFileHelper()));

        TagsBlock blockTags = new TagsBlock(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new TagsItem(generator, blockTags, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new LootModifiers(generator));

        /* RECIPES */

        generator.addProvider(event.includeServer(), new CompressingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new CrushingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new ExtractingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SawingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new FluidExtrudingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new CanningRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new FluidEnrichingProvider(generator));
        generator.addProvider(event.includeServer(), new RecyclingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new CuttingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new RollingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new ExtrudingRecipeProvider(generator));

        generator.addProvider(event.includeServer(), new AlloySmeltingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new FermentingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new OreWashingProvider(generator));
        generator.addProvider(event.includeServer(), new ThermalCentrifugingProvider(generator));

        generator.addProvider(event.includeServer(), new ScanningProvider(generator));

        generator.addProvider(event.includeServer(), new ScrapBoxProvider(generator));
        generator.addProvider(event.includeServer(), new MatterAmplifierProvider(generator));

        generator.addProvider(event.includeServer(), new SmeltingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new BlastingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new StonecuttingRecipeProvider(generator));

        generator.addProvider(event.includeServer(), new ReinforcedStoneProvider(generator));
        generator.addProvider(event.includeServer(), new ConstructionFoamProvider(generator));
        generator.addProvider(event.includeServer(), new BlockProvider(generator));
        generator.addProvider(event.includeServer(), new CasingProvider(generator));
        generator.addProvider(event.includeServer(), new IronProvider(generator));
        generator.addProvider(event.includeServer(), new WoodProvider(generator));
        generator.addProvider(event.includeServer(), new DecorationProvider(generator));
        generator.addProvider(event.includeServer(), new ExplosiveProvider(generator));
        generator.addProvider(event.includeServer(), new CableProvider(generator));
        generator.addProvider(event.includeServer(), new MiscProvider(generator));
        generator.addProvider(event.includeServer(), new EnergyStorageProvider(generator));
        generator.addProvider(event.includeServer(), new GeneratorProvider(generator));
        generator.addProvider(event.includeServer(), new MachinesSimpleProvider(generator));
        generator.addProvider(event.includeServer(), new MachinesBasicProvider(generator));
        generator.addProvider(event.includeServer(), new MachinesStandardProvider(generator));
        generator.addProvider(event.includeServer(), new MachinesAdvancedProvider(generator));
        generator.addProvider(event.includeServer(), new MachinesSuperProvider(generator));

        generator.addProvider(event.includeServer(), new ItemsReactorProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsUpgradeProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsCanProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsToolProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsPainterProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsElectricProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsCropProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsBasicProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsCircuitProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsStorageProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsHammerProvider(generator));
        generator.addProvider(event.includeServer(), new ItemsArmourProvider(generator));


        /* LOOT TABLES */

        generator.addProvider(event.includeServer(), new LootTables(generator));

    }
}
