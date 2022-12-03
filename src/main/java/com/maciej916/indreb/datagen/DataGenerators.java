package com.maciej916.indreb.datagen;

import com.maciej916.indreb.datagen.loot.LootModifier;
import com.maciej916.indreb.datagen.recipe.provider.*;
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

        generator.addProvider(event.includeServer(), new LootModifier(generator));



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
    }
}
