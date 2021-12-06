package com.maciej916.indreb.common.interfaces.entity;

public interface IProgress {

    float getProgress();
    float getProgressMax();

    default String getProgressString() {
        return String.valueOf(getProgress());
    }

    default int getPercentProgress() {
        if (getProgress() <= 0 || getProgressMax() <= 0) return 0;
        Double progressPercent = (double) getProgress() / (double) getProgressMax()  * 100;
        return progressPercent.intValue();
    }

}
