package com.maciej916.indreb.common.api.blockentity.interfaces;

public interface IProgress extends IBaseProgress {

    boolean isCurrentAboveEqualMax();
    void resetProgress();

    /* FOR SYNC CONTAINER */

    void setDataCurrent(int data);
    int getDataCurrent();
    void setDataMax(int data);
    int getDataMax();

}
