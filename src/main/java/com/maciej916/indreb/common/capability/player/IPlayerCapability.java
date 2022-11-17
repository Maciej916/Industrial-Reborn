package com.maciej916.indreb.common.capability.player;

public interface IPlayerCapability {

    boolean getNightVision();
    void setNightVision(boolean enabled);

    void tick();
    void clone(IPlayerCapability capability);
}
