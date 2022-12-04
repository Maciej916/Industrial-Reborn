package com.maciej916.indreb.common.api.util;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.util.GuiUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ProgressInt implements IBaseProgress, INBTSerializable<CompoundTag> {

    private int currentProgress;
    private int progressMax;

    public ProgressInt() {
        this.currentProgress = -1;
        this.progressMax = -1;
    }

    public ProgressInt(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    public ProgressInt(int progress, int progressMax) {
        this.currentProgress = progress;
        this.progressMax = progressMax;
    }

    public void setData(int progress, int progressMax) {
        this.currentProgress = progress;
        this.progressMax = progressMax;
    }

    public void setCurrentProgress(int currentProgress) {
        this.setData(currentProgress, progressMax);
    }

    public void incProgress(int progress) {
        this.setData(this.currentProgress + progress, progressMax);
    }

    public void decProgress(int progress) {
        this.setData(this.currentProgress - progress, progressMax);
    }

    public void setMaxProgress() {
        this.setData(progressMax, progressMax);
    }

    public void setBoth(int progress) {
        this.setData(progress, progress);
    }

    public void setProgressMax(int progressMax) {
        this.setData(currentProgress, Math.max(progressMax, 1));
    }

    @Override
    public float getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public float getProgressMax() {
        return progressMax;
    }

    public int getCurrentProgressInt() {
        return currentProgress;
    }

    public int getProgressMaxInt() {
        return progressMax;
    }

    public boolean isCurrentAboveEqualMax() {
        return currentProgress >= progressMax && progressMax != -1;
    }

    public void resetProgress() {
        setBoth(-1);
    }

    public String getCurrentProgressString() {
        return String.valueOf(getCurrentProgress());
    }

    public String getPercentProgressString() {
        return GuiUtil.DECIMAL_FORMAT_2.format(getPercentProgress());
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("progress", currentProgress);
        nbt.putInt("progressMax", progressMax);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.currentProgress = nbt.getInt("progress");
        this.progressMax = nbt.getInt("progressMax");
    }

    @Override
    public String toString() {
        return "Progress{" +
                "currentProgress=" + currentProgress +
                ", progressMax=" + progressMax +
                '}';
    }
}
