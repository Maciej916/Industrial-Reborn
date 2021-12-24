package com.maciej916.indreb.common.interfaces.entity;

import java.text.DecimalFormat;

public interface IProgress {

    DecimalFormat df = new DecimalFormat("0.00");

    float getProgress();
    float getProgressMax();

    default String getProgressString() {
        return String.valueOf(getProgress());
    }

    default float getPercentProgress() {
        if (getProgress() <= 0 || getProgressMax() <= 0) return 0;
        return (getProgress() / getProgressMax())  * 100;
    }

    default String getPercentProgressString() {
        return df.format(getPercentProgress());
    }

}
