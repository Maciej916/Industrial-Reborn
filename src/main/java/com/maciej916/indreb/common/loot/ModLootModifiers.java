package com.maciej916.indreb.common.loot;

import com.maciej916.indreb.common.loot.impl.SimpleDungeonModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SIMPLE_DUNGEON = LOOT_MODIFIER_SERIALIZERS.register("simple_dungeon", SimpleDungeonModifier.CODEC);


    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
