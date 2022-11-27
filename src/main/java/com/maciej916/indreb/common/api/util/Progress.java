package com.maciej916.indreb.common.api.util;

import com.maciej916.indreb.common.api.blockentity.interfaces.IProgress;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class Progress implements IProgress, INBTSerializable<CompoundTag> {

    private float currentProgress;
    private float progressMax;

    public Progress() {
        this.currentProgress = -1;
        this.progressMax = -1;
    }

    public Progress(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    public Progress(int progress, int progressMax) {
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

    @Override
    public float currentProgress() {
        return currentProgress;
    }

    @Override
    public float getProgressMax() {
        return progressMax;
    }

    @Override
    public boolean isCurrentAboveEqualMax() {
        return currentProgress >= progressMax && progressMax != -1;
    }

    @Override
    public void resetProgress() {
        setBoth(-1);
    }

    @Override
    public void setContainerDataCurrent(int data) {
        this.currentProgress = data / 100f;
    }

    @Override
    public int getContainerDataCurrent() {
        return Math.round(currentProgress * 100);
    }

    @Override
    public void setContainerDataMax(int data) {
        this.progressMax = data / 100f;
    }

    @Override
    public int getContainerDataMax() {
        return Math.round(progressMax * 100);
    }

    @Override
    public void setContainerDataBoth(int current, int max) {
        setData(current / 100f, max / 100f);
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
}
