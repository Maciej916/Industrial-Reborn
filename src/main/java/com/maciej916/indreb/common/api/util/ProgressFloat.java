package com.maciej916.indreb.common.api.util;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ProgressFloat implements IBaseProgress, INBTSerializable<CompoundTag> {

    private float currentProgress;
    private float progressMax;

    public ProgressFloat() {
        this.currentProgress = -1;
        this.progressMax = -1;
    }

    public ProgressFloat(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    public ProgressFloat(float progress, float progressMax) {
        this.currentProgress = progress;
        this.progressMax = progressMax;
    }

    public void setData(float progress, float progressMax) {
        this.currentProgress = progress;
        this.progressMax = progressMax;
    }

    public void setCurrentProgress(float currentProgress) {
        this.setData(currentProgress, progressMax);
    }

    public void incProgress(float progress) {
        this.setData(this.currentProgress + progress, progressMax);
    }

    public void decProgress(float progress) {
        this.setData(this.currentProgress - progress, progressMax);
    }

    public void setMaxProgress() {
        this.setData(progressMax, progressMax);
    }

    public void setBoth(float progress) {
        this.setData(progress, progress);
    }

    public void setProgressMax(float progressMax) {
        this.setData(currentProgress, Math.max(progressMax, 1));
    }

    public boolean isCurrentAboveEqualMax() {
        return currentProgress >= progressMax && progressMax != -1;
    }

    public void resetProgress() {
        setBoth(-1);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("progress", currentProgress);
        nbt.putFloat("progressMax", progressMax);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.currentProgress = nbt.getFloat("progress");
        this.progressMax = nbt.getFloat("progressMax");
    }

    @Override
    public String toString() {
        return "Progress{" +
                "currentProgress=" + currentProgress +
                ", progressMax=" + progressMax +
                '}';
    }

    @Override
    public float getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public float getProgressMax() {
        return progressMax;
    }
}
