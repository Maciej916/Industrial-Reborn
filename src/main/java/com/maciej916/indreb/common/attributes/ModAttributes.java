package com.maciej916.indreb.common.attributes;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    public static final RegistryObject<Attribute> RADIATION_PROTECTION = ATTRIBUTES.register("radiation_protection", () -> new RangedAttribute("tooltip." + MODID + ".radiation_protection", 0, 0, 10).setSyncable(true));
    public final static UUID RADIATION_PROTECTION_MODIFIER = UUID.randomUUID();


    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

}
