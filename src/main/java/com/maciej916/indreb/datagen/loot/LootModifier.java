package com.maciej916.indreb.datagen.loot;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.loot.impl.IridiumShardModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LootModifier extends GlobalLootModifierProvider {
    public LootModifier(DataGenerator gen) {
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