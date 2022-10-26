package com.maciej916.indreb.datagen.loottables;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.loot.IridiumShardModifier;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class DungeonLootModifier extends GlobalLootModifierProvider {
    public DungeonLootModifier(DataGenerator gen) {
        super(gen, IndReb.MODID);
    }

    @Override
    protected void start() {
        add("iridium_shard", new IridiumShardModifier(
                        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build() },
                        ModItems.IRIDIUM_SHARD.get(),
                        0.1f
                )
        );
    }
}