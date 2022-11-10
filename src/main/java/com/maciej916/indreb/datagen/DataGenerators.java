package com.maciej916.indreb.datagen;

import com.maciej916.indreb.datagen.client.BlockStates;
import com.maciej916.indreb.datagen.client.Items;
import com.maciej916.indreb.datagen.loottables.DungeonLootModifier;
import com.maciej916.indreb.datagen.loottables.ModLootTables;
import com.maciej916.indreb.datagen.recipes.advanced_crafting.shaped.ItemsA;
import com.maciej916.indreb.datagen.recipes.crafting.*;
import com.maciej916.indreb.datagen.recipes.machines.*;
import com.maciej916.indreb.datagen.tags.Tags;
import com.maciej916.indreb.datagen.tags.TagsItem;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        // TEST
        generator.addProvider(event.includeServer(), new ItemsA(generator));

        generator.addProvider(event.includeServer(), new Cables(generator));
        generator.addProvider(event.includeServer(), new Cutter(generator));
        generator.addProvider(event.includeServer(), new Hammer(generator));
        generator.addProvider(event.includeServer(), new Generators(generator));
        generator.addProvider(event.includeServer(), new Block(generator));
        generator.addProvider(event.includeServer(), new Blasting(generator));
        generator.addProvider(event.includeServer(), new Smelting(generator));
        generator.addProvider(event.includeServer(), new BatteryBox(generator));
        generator.addProvider(event.includeServer(), new Item(generator));
        generator.addProvider(event.includeServer(), new ItemElectric(generator));
        generator.addProvider(event.includeServer(), new ItemTool(generator));
        generator.addProvider(event.includeServer(), new ItemPainter(generator));
        generator.addProvider(event.includeServer(), new ItemExplosive(generator));
        generator.addProvider(event.includeServer(), new Wood(generator));
        generator.addProvider(event.includeServer(), new Armour(generator));
        generator.addProvider(event.includeServer(), new Machines(generator));
        generator.addProvider(event.includeServer(), new Upgrades(generator));
        generator.addProvider(event.includeServer(), new Reactor(generator));
        generator.addProvider(event.includeServer(), new Transformers(generator));
        generator.addProvider(event.includeServer(), new ChargePads(generator));
        generator.addProvider(event.includeServer(), new Decoration(generator));

        generator.addProvider(event.includeServer(), new Crushing(generator));
        generator.addProvider(event.includeServer(), new Extracting(generator));
        generator.addProvider(event.includeServer(), new Compressing(generator));
        generator.addProvider(event.includeServer(), new Sawing(generator));
        generator.addProvider(event.includeServer(), new FluidExtruding(generator));
        generator.addProvider(event.includeServer(), new AlloySmelting(generator));
        generator.addProvider(event.includeServer(), new Recycling(generator));
        generator.addProvider(event.includeServer(), new ScrapBox(generator));
        generator.addProvider(event.includeServer(), new Canning(generator));
        generator.addProvider(event.includeServer(), new FluidEnriching(generator));
        generator.addProvider(event.includeServer(), new OreWashing(generator));
        generator.addProvider(event.includeServer(), new ThermalCentrifuging(generator));
        generator.addProvider(event.includeServer(), new Scanner(generator));
        generator.addProvider(event.includeServer(), new Rolling(generator));
        generator.addProvider(event.includeServer(), new Cutting(generator));
        generator.addProvider(event.includeServer(), new Extruding(generator));

        generator.addProvider(event.includeServer(), new ModLootTables(generator));

        Tags blockTags = new Tags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new TagsItem(generator, blockTags, event.getExistingFileHelper()));

        generator.addProvider(event.includeClient(), new BlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new Items(generator, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new DungeonLootModifier(generator));
    }
}
