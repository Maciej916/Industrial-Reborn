package com.maciej916.indreb.common.interfaces.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public interface ITileSound {

    SoundEvent getSoundEvent();

    default boolean hasSound() {
        return true;
    }

    default float getInitialVolume() {
        return 1.0F;
    }

    default float getVolume() {
        return 1.0F;
    }

    default SoundSource getSoundCategory() {
        return SoundSource.BLOCKS;
    }
}
