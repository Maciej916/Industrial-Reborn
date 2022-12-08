package com.maciej916.indreb.datagen.loot;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.loot.impl.SimpleDungeonModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LootModifiers extends GlobalLootModifierProvider {
    public LootModifiers(DataGenerator gen) {
        super(gen, IndReb.MODID);
    }

    @Override
    protected void start() {
        add("iridium_shard", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.IRIDIUM_SHARD.get(),
                        0.1f
                )
        );
        add("canned_food", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.CANNED_FOOD.get(),
                        0.1f
                )
        );
        add("canned_poison", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.CANNED_POISON.get(),
                        0.2f
                )
        );
        add("canned_hunger", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.CANNED_HUNGER.get(),
                        0.2f
                )
        );
        add("nuka_cola", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.NUKA_COLA.get(),
                        0.15f
                )
        );
        add("sprunk", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.SPRUNK.get(),
                        0.15f
                )
        );
        add("energy_drink", new SimpleDungeonModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.ENERGY_DRINK.get(),
                        0.15f
                )
        );
    }
}