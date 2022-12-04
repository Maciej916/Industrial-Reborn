package com.maciej916.indreb.common.api.blockentity.interfaces;

import java.text.DecimalFormat;

public interface IBaseProgress {

    float getCurrentProgress();
    float getProgressMax();

    /* FORMATTING */

    DecimalFormat df = new DecimalFormat("0.00");

    default String getProgressString() {
        return String.valueOf(getCurrentProgress());
    }

    default String getCurrentProgressString() {
        return df.format(getCurrentProgress());
    }

    default String getPercentProgressString() {
        return df.format(getPercentProgress());
    }

    default float getPercentProgress() {
        if (getCurrentProgress() <= 0 || getProgressMax() <= 0) return 0;
        return (getCurrentProgress() / getProgressMax())  * 100;
    }

}
