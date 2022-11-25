package com.maciej916.indreb.common.api.blockentity.interfaces;

import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.capability.item.BaseItemStackHandler;
import com.maciej916.indreb.common.capability.item.ElectricItemStackHandler;
import com.maciej916.indreb.common.capability.item.UpgradesItemStackHandler;

public interface IIndRebBlockEntity {

    boolean hasMenu();

    boolean hasEnergyStorage();
    boolean hasBaseStorage();
    boolean hasElectricStorage();
    boolean hasUpgradesStorage();

    BasicEnergyStorage getEnergyStorage();

    BaseItemStackHandler getBaseStorage();

    ElectricItemStackHandler getElectricStorage();

    UpgradesItemStackHandler getUpgradesStorage();

    void tick();
    void tickClient();
    void tickServer();
    void tickWork();
    void tickUpgrades();

    void onPlace(boolean isClient);
    void onBreak();

    boolean getActiveStateValue();
    void setChanged();

    void updateState();
    void setActiveState(boolean activeState);
}
