package com.maciej916.indreb.common.interfaces.item;

public interface IArmorProperties {

    default boolean supportsNightVision() {
        return false;
    }
}
