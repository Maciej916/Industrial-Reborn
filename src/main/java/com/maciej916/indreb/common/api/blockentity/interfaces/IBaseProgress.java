package com.maciej916.indreb.common.api.blockentity.interfaces;

import java.text.DecimalFormat;

public interface IBaseProgress {

    float currentProgress();
    float getProgressMax();

    /* FORMATTING */

    DecimalFormat df = new DecimalFormat("0.00");

    default String getProgressString() {
        return String.valueOf(currentProgress());
    }
    default String getPercentProgressString() {
        return df.format(getPercentProgress());
    }
    default float getPercentProgress() {
        if (currentProgress() <= 0 || getProgressMax() <= 0) return 0;
        return (currentProgress() / getProgressMax())  * 100;
    }

}
