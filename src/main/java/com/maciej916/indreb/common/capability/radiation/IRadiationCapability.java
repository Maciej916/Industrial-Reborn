package com.maciej916.indreb.common.capability.radiation;

public interface IRadiationCapability {

    double getAddRads();
    double getRemoveRads();
    void clone(IRadiationCapability capability);

}
