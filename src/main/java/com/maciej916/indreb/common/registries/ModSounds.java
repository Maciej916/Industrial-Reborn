package com.maciej916.indreb.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maciej916.indreb.IndReb.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModSounds {

    public static SoundEvent GENERATOR;
    public static SoundEvent GEO_GENERATOR;
    public static SoundEvent SOLAR_GENERATOR;

    public static SoundEvent CRUSHER;
    public static SoundEvent COMPRESSOR;
    public static SoundEvent EXTRACTOR;
    public static SoundEvent SAWMILL;

    public static SoundEvent TREETAP;
    public static SoundEvent WRENCH;


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        GENERATOR = registerSound("tile.generator");
        GEO_GENERATOR = registerSound("tile.geo_generator");
        SOLAR_GENERATOR = registerSound("tile.solar_generator");

        CRUSHER = registerSound("tile.crusher");
        COMPRESSOR = registerSound("tile.compressor");
        EXTRACTOR = registerSound("tile.extractor");
        SAWMILL = registerSound("tile.sawmill");


        TREETAP = registerSound("item.treetap");
        WRENCH = registerSound("item.wrench");
    }

    public static SoundEvent registerSound(String name) {
        ResourceLocation shotSoundLocation = new ResourceLocation(MODID, name);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        return soundEvent;
    }

}
