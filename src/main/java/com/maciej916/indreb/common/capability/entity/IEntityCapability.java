package com.maciej916.indreb.common.capability.entity;

public interface IEntityCapability {

//    boolean isInConsturctionFoam();
//    boolean wasInConsturctionFoam();
//    void setInConstructionFoam(boolean enabled);

    void tick();
    void clone(IEntityCapability capability);

}
