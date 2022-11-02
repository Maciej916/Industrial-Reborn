package com.maciej916.indreb.common.util;

import com.maciej916.indreb.IndReb;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = IndReb.MODID)
public class SoundHandler {

    private static final Long2ObjectMap<SoundInstance> soundMap = new Long2ObjectOpenHashMap<>();
    private static final Long2ObjectMap<SoundInstance> soundMapExtra = new Long2ObjectOpenHashMap<>();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTilePlaySound(PlaySoundEvent event) {
        SoundInstance resultSound = event.getSound();
        if (resultSound == null) {
            return;
        }

        ResourceLocation soundLoc = event.getSound().getLocation();
        if (!soundLoc.getNamespace().startsWith(IndReb.MODID)) {
            return;
        }

        if (event.getName().startsWith("tile.")) {
            BlockPos pos = new BlockPos(resultSound.getX() - 0.5, resultSound.getY() - 0.5, resultSound.getZ() - 0.5);
            soundMap.put(pos.asLong(), resultSound);
        }

        if (event.getName().startsWith("extra.")) {
            BlockPos pos = new BlockPos(resultSound.getX() - 0.5, resultSound.getY() - 0.5, resultSound.getZ() - 0.5);
            soundMapExtra.put(pos.asLong(), resultSound);
        }
    }

    public static void playSound(SoundInstance sound) {
        Minecraft.getInstance().getSoundManager().play(sound);
    }

    public static void playSound(SoundEvent sound) {
        playSound(SimpleSoundInstance.forUI(sound, 1, 1F));
    }

    public static SoundInstance startTileSound(Long2ObjectMap<SoundInstance> map, SoundEvent soundEvent, SoundSource category, float volume, BlockPos pos) {
        SoundInstance s = map.get(pos.asLong());

        if (s == null || !Minecraft.getInstance().getSoundManager().isActive(s)) {
            s = new TileTickableSound(soundEvent, category, pos, volume);
            playSound(s);
            s = map.get(pos.asLong());
        }

        return s;
    }

    public static SoundInstance startTileSound(SoundEvent soundEvent, SoundSource category, float volume, BlockPos pos) {
        return startTileSound(soundMap, soundEvent, category, volume, pos);
    }

    public static SoundInstance startExtraSound(SoundEvent soundEvent, SoundSource category, float volume, BlockPos pos) {
        return startTileSound(soundMapExtra, soundEvent, category, volume, pos);
    }

    public static void stopTileSound(Long2ObjectMap<SoundInstance> map, BlockPos pos) {
        long posKey = pos.asLong();
        SoundInstance s = map.get(posKey);
        if (s != null) {
            Minecraft.getInstance().getSoundManager().stop(s);
            map.remove(posKey);
        }
    }

    public static void stopTileSound(BlockPos pos) {
        stopTileSound(soundMap, pos);
    }

    public static void stopExtraSound(BlockPos pos) {
        stopTileSound(soundMapExtra, pos);
    }

    private static class TileTickableSound extends AbstractTickableSoundInstance {

        private final float originalVolume;
        private final int checkInterval = 20 + ThreadLocalRandom.current().nextInt(20);

        TileTickableSound(SoundEvent soundEvent, SoundSource category, BlockPos pos, float volume) {
            super(soundEvent, category, SoundInstance.createUnseededRandom());

            this.x = pos.getX() + 0.5F;
            this.y = pos.getY() + 0.5F;
            this.z = pos.getZ() + 0.5F;

            this.originalVolume = volume * 1;
            this.volume = this.originalVolume * getTileVolumeFactor();
            this.looping = true;
            this.delay = 0;
        }

        @Override
        public boolean isStopped() {
            return false;
        }

        @Override
        public void tick() {
            assert Minecraft.getInstance().level != null;
            if (Minecraft.getInstance().level.getGameTime() % checkInterval == 0) {
                volume = originalVolume;
//                SoundInstance s = ForgeHooksClient.playSound(soundEngine, this);


//                PlaySoundEvent e = new PlaySoundEvent(manager, sound);


//                Minecraft.getInstance().getSoundManager().play(this);

//                if (s == this) {
//                    volume = originalVolume * getTileVolumeFactor();
//                } else if (s == null) {
//                    stop();
//                } else {
//                    volume = s.getVolume() * getTileVolumeFactor();
//                }
            }
        }

        private float getTileVolumeFactor() {
            return 1.0F;
        }
    }
}
