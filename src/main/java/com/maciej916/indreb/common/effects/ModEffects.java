package com.maciej916.indreb.common.effects;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.effects.impl.RadiationEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

    public static final RegistryObject<RadiationEffect> RADIATION = registerEffect("radiation", () -> (new RadiationEffect(MobEffectCategory.HARMFUL, 5797459)));

    private static <T extends MobEffect> RegistryObject<T> registerEffect(String name, Supplier<T> block) {
        return MOB_EFFECTS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
