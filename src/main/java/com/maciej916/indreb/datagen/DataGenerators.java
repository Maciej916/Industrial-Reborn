package com.maciej916.indreb.datagen;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.datagen.client.BlockStates;
import com.maciej916.indreb.datagen.client.Items;
import com.maciej916.indreb.datagen.loot.DungeonLootEnhancerModifier;
import com.maciej916.indreb.datagen.loottables.LootTables;
import com.maciej916.indreb.datagen.recipes.crafting.*;
import com.maciej916.indreb.datagen.recipes.machines.*;
import com.maciej916.indreb.datagen.tags.Tags;
import com.maciej916.indreb.datagen.tags.TagsItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, IndReb.MODID);
    public static final RegistryObject<DungeonLootEnhancerModifier.Serializer> DUNGEON_LOOT = GLM.register("dungeon_loot", DungeonLootEnhancerModifier.Serializer::new);

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        if (event.includeServer()) {
            generator.addProvider(new Cables(generator));
            generator.addProvider(new Cutter(generator));
            generator.addProvider(new Hammer(generator));
            generator.addProvider(new Generators(generator));
            generator.addProvider(new Block(generator));
            generator.addProvider(new Blasting(generator));
            generator.addProvider(new Smelting(generator));
            generator.addProvider(new BatteryBox(generator));
            generator.addProvider(new Item(generator));
            generator.addProvider(new ItemElectric(generator));
            generator.addProvider(new ItemTool(generator));
            generator.addProvider(new ItemPainter(generator));
            generator.addProvider(new Wood(generator));
            generator.addProvider(new Armour(generator));
            generator.addProvider(new Machines(generator));
            generator.addProvider(new Upgrades(generator));
            generator.addProvider(new Reactor(generator));
            generator.addProvider(new Transformers(generator));
            generator.addProvider(new ChargePads(generator));

            generator.addProvider(new Crushing(generator));
            generator.addProvider(new Extracting(generator));
            generator.addProvider(new Compressing(generator));
            generator.addProvider(new Sawing(generator));
            generator.addProvider(new Extruding(generator));
            generator.addProvider(new AlloySmelting(generator));
            generator.addProvider(new Recycling(generator));
            generator.addProvider(new ScrapBox(generator));
            generator.addProvider(new Canning(generator));
            generator.addProvider(new FluidEnriching(generator));


            generator.addProvider(new LootTables(generator));

            Tags blockTags = new Tags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new TagsItem(generator, blockTags, event.getExistingFileHelper()));
        }

        if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new Items(generator, event.getExistingFileHelper()));
        }

        generator.addProvider(new LootModifier(generator));
    }

    private static class LootModifier extends GlobalLootModifierProvider {
        public LootModifier(DataGenerator gen) {
            super(gen, IndReb.MODID);
        }

        @Override
        protected void start() {
            add("dungeon_loot", DUNGEON_LOOT.get(), new DungeonLootEnhancerModifier(
                    new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() })
            );
        }
    }
}
