package com.maciej916.indreb.common.api.interfaces.item;

public interface IArmorProperties {

    default boolean supportsNightVision() {
        return false;
    }
}
