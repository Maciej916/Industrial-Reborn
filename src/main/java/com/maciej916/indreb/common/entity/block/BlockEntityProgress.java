package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.interfaces.entity.IProgress;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BlockEntityProgress implements IProgress, INBTSerializable<CompoundTag> {

    private float progress;
    private float progressMax;

    private boolean changed = false;

    public BlockEntityProgress() {
        this.progress = -1;
        this.progressMax = -1;
    }

    public BlockEntityProgress(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    public BlockEntityProgress(int progress, int progressMax) {
        this.progress = progress;
        this.progressMax = progressMax;
    }

    public void setData(float progress, float progressMax) {
        if (progress != this.progress || progressMax != this.progressMax) this.changed = true;
        this.progress = progress;
        this.progressMax = progressMax;
    }

    public void setProgress(float progress) {
        this.setData(progress, progressMax);
    }

    public void incProgress(float progress) {
        this.setData(this.progress + progress, progressMax);
    }

    public void decProgress(float progress) {
        this.setData(this.progress - progress, progressMax);
    }

    public void setMaxProgress() {
        this.setData(progressMax, progressMax);
    }

    public void setBoth(float progress) {
        this.setData(progress, progress);
    }

    public void setProgressMax(float progressMax) {
        this.setData(progress, progressMax);
    }

    @Override
    public float getProgress() {
        return progress;
    }

    @Override
    public float getProgressMax() {
        return progressMax;
    }

    public boolean changed() {
        return changed;
    }

    public void clearChanged() {
        changed = false;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("progress", progress);
        nbt.putFloat("progressMax", progressMax);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.progress = nbt.getInt("progress");
        this.progressMax = nbt.getInt("progressMax");
    }

    @Override
    public String toString() {
        return "Progress{" +
                "progress=" + progress +
                ", progressMax=" + progressMax +
                '}';
    }
}
