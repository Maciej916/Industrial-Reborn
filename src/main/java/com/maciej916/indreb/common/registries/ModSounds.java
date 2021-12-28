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
    public static SoundEvent SEMIFLUID_GENERATOR;

    public static SoundEvent CRUSHER;
    public static SoundEvent COMPRESSOR;
    public static SoundEvent EXTRACTOR;
    public static SoundEvent SAWMILL;
    public static SoundEvent RECYCLER;
    public static SoundEvent CANNING_MACHINE;

    public static SoundEvent TREETAP;
    public static SoundEvent WRENCH;
    public static SoundEvent ELECTRIC_WRENCH;


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        GENERATOR = registerSound("tile.generator");
        GEO_GENERATOR = registerSound("tile.geo_generator");
        SOLAR_GENERATOR = registerSound("tile.solar_generator");
        SEMIFLUID_GENERATOR = registerSound("tile.semifluid_generator");

        CRUSHER = registerSound("tile.crusher");
        COMPRESSOR = registerSound("tile.compressor");
        EXTRACTOR = registerSound("tile.extractor");
        SAWMILL = registerSound("tile.sawmill");
        RECYCLER = registerSound("tile.recycler");
        CANNING_MACHINE = registerSound("tile.canning_machine");


        TREETAP = registerSound("item.treetap");
        WRENCH = registerSound("item.wrench");
        ELECTRIC_WRENCH = registerSound("item.electric_wrench");
    }

    public static SoundEvent registerSound(String name) {
        ResourceLocation shotSoundLocation = new ResourceLocation(MODID, name);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        return soundEvent;
    }

}
