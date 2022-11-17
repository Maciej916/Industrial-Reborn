package com.maciej916.indreb.common.api.blockentity.interfaces;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public interface IHasSound {

    SoundEvent getSoundEvent();

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
