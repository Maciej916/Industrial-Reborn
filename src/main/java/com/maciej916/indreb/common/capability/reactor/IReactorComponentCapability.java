package com.maciej916.indreb.common.capability.reactor;

import com.maciej916.indreb.common.multiblock.reactor.Reactor;

public interface IReactorComponentCapability {

    void addToReactor(Reactor parent, int row, int col, int slotId);
    void removeFromReactor();
    int getSlotId();

    int getMaxDamage();
    int getCurrentDamage();
    int getMaxHeat();
    int getCurrentHeat();

    void setCurrentDamage(int damage);
    void setCurrentHeat(int heat);

    int getDurability();
    int getMaxDurability();

    void preReactorTick();

    int adjustCurrentHeat(int heat);
    void applyDamage(int damage);

    int generateHeat();
    int generateEnergy();

    int dissipate();
    void transfer();

    boolean isNeutronReflector();
    boolean isHeatAcceptor();
    boolean isCoolable();
    boolean isBroken();

    int getRodCount();

    double getExplosionPowerOffset();
    double getExplosionPowerMultiplier();
    int getHullCoolingCapacity();
    int getCurrentOutput();
    boolean needsCoolantInjected();
    void injectCoolant();

}
