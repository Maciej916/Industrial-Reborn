package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ReactorVentCapability extends ReactorComponentCapability {

    private final int selfVent;
    private final int hullDraw;
    private final int sideVent;

    public ReactorVentCapability(ItemStack stack, int maxDamage, int maxHeat, int selfVent, int hullDraw, int sideVent) {
        super(stack, maxDamage, maxHeat);
        this.selfVent = selfVent;
        this.hullDraw = hullDraw;
        this.sideVent = sideVent;
    }

    @Override
    public int dissipate() {
        int deltaHeat = Math.min(hullDraw, parent.getCurrentHeat());
        currentHullCooling = deltaHeat;
        parent.adjustCurrentHeat(-deltaHeat);
        this.adjustCurrentHeat(deltaHeat);
        final int currentDissipation = Math.min(selfVent, getCurrentHeat());
        currentVentCooling = currentDissipation;
        parent.ventHeat(currentDissipation);
        adjustCurrentHeat(-currentDissipation);
        if (sideVent > 0) {
            List<IReactorComponentCapability> coolableNeighbors = new ArrayList<>(4);
            IReactorComponentCapability component = parent.getComponentAt(row - 1, col);
            if (component != null && component.isCoolable()) {
                coolableNeighbors.add(component);
            }
            component = parent.getComponentAt(row, col + 1);
            if (component != null && component.isCoolable()) {
                coolableNeighbors.add(component);
            }
            component = parent.getComponentAt(row + 1, col);
            if (component != null && component.isCoolable()) {
                coolableNeighbors.add(component);
            }
            component = parent.getComponentAt(row, col - 1);
            if (component != null && component.isCoolable()) {
                coolableNeighbors.add(component);
            }
            for (IReactorComponentCapability coolableNeighbor : coolableNeighbors) {
                double rejectedCooling = coolableNeighbor.adjustCurrentHeat(-sideVent);
                double tempDissipatedHeat = sideVent + rejectedCooling;
                parent.ventHeat(tempDissipatedHeat);
                currentVentCooling += tempDissipatedHeat;
            }
        }
        bestVentCooling = Math.max(bestVentCooling, currentVentCooling);
        return currentDissipation;
    }

    @Override
    public double getVentCoolingCapacity() {
        double result = selfVent;
        if (sideVent > 0) {
            IReactorComponentCapability component = parent.getComponentAt(row - 1, col);
            if (component != null && component.isCoolable()) {
                result += sideVent;
            }
            component = parent.getComponentAt(row, col + 1);
            if (component != null && component.isCoolable()) {
                result += sideVent;
            }
            component = parent.getComponentAt(row + 1, col);
            if (component != null && component.isCoolable()) {
                result += sideVent;
            }
            component = parent.getComponentAt(row, col - 1);
            if (component != null && component.isCoolable()) {
                result += sideVent;
            }
        }
        return result;
    }

    @Override
    public int getHullCoolingCapacity() {
        return hullDraw;
    }

    @Override
    public int getCurrentOutput() {
        return currentVentCooling;
    }

}
