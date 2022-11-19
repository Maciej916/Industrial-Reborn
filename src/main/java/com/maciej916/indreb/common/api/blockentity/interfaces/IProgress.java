package com.maciej916.indreb.common.api.blockentity.interfaces;

public interface IProgress extends IBaseProgress {

    boolean isCurrentAboveEqualMax();
    void resetProgress();

    /* FOR SYNC CONTAINER */

    void setContainerDataCurrent(int data);
    int getContainerDataCurrent();
    void setContainerDataMax(int data);
    int getContainerDataMax();
    void setContainerDataBoth(int current, int max);
}
