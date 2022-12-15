package com.maciej916.indreb.common.capability.radiation;

public interface IHasRadiation {

    default double getAddRads() {
        return 0;
    };

    default double getRemoveRads() {
        return 0;
    };

    default int getRadiationProtection() {
        return 0;
    };

}
