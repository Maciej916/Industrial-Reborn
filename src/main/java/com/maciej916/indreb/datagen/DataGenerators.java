package com.maciej916.indreb.datagen;

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
    }
}
