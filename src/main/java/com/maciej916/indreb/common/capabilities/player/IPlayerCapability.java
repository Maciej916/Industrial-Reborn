package com.maciej916.indreb.common.capabilities.player;

public interface IPlayerCapability {

    boolean getNightVision();
    void setNightVision(boolean enabled);

    void clone(IPlayerCapability capability);

}
