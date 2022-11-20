package com.maciej916.indreb.common.api.energy.interfaces;

public interface IBlockEntityEnergy {

    default boolean showBarInTop() {
        return true;
    }

    default boolean showVertical() {
        return true;
    }

    default boolean showBarInGui() {
        return true;
    }

    default int leftOffsetVertical() {
        return 151;
    }

    default int topOffsetVertical() {
        return 7;
    }

    default int leftOffsetHorizontal() {
        return 90;
    }

    default int topOffsetHorizontal() {
        return 35;
    }

}
