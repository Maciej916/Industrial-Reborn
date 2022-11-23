package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ReactorFuelRodCapability extends ReactorComponentCapability {

    private final int energyMult;
    private final int heatMult;
    private final int rodCount;
    private final boolean moxStyle;

    public ReactorFuelRodCapability(ItemStack stack, int maxDamage, int maxHeat, int energyMult, int heatMult, int rodCount, boolean moxStyle) {
        super(stack, maxDamage, maxHeat);
        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.moxStyle = moxStyle;
    }

    @Override
    public boolean isNeutronReflector() {
        return !isBroken();
    }

    private int countNeutronNeighbors() {
        int neutronNeighbors = 0;
        IReactorComponentCapability component = parent.getComponentAt(row + 1, col);
        if (component != null && component.isNeutronReflector()) {
            neutronNeighbors++;
        }
        component = parent.getComponentAt(row - 1, col);
        if (component != null && component.isNeutronReflector()) {
            neutronNeighbors++;
        }
        component = parent.getComponentAt(row, col - 1);
        if (component != null && component.isNeutronReflector()) {
            neutronNeighbors++;
        }
        component = parent.getComponentAt(row, col + 1);
        if (component != null && component.isNeutronReflector()) {
            neutronNeighbors++;
        }
        return neutronNeighbors;
    }

    protected void handleHeat(final int heat) {
        List<IReactorComponentCapability> heatableNeighbors = new ArrayList<>(4);
        IReactorComponentCapability component = parent.getComponentAt(row + 1, col);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row - 1, col);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row, col - 1);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row, col + 1);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        if (heatableNeighbors.isEmpty()) {
            parent.adjustCurrentHeat(heat);
            currentHullHeating = heat;
        } else {
            currentComponentHeating = heat;
            for (IReactorComponentCapability heatableNeighbor : heatableNeighbors) {
                heatableNeighbor.adjustCurrentHeat(heat / heatableNeighbors.size());
            }
            int remainderHeat = heat % heatableNeighbors.size();
            heatableNeighbors.get(0).adjustCurrentHeat(remainderHeat);
        }
    }

    @Override
    public int generateHeat() {
        int pulses = countNeutronNeighbors() + (rodCount == 1 ? 1 : (rodCount == 2) ? 2 : 3);
        int heat = heatMult * pulses * (pulses + 1);
        if (moxStyle && parent.isFluid() && ((float)parent.getCurrentHeat() / parent.getMaxHeat()) > 0.5) {
            heat *= 2;
        }
        currentHeatGenerated = heat;
        minHeatGenerated = Math.min(minHeatGenerated, heat);
        maxHeatGenerated = Math.max(maxHeatGenerated, heat);
        handleHeat(heat);
        return currentHeatGenerated;
    }

    @Override
    public int generateEnergy() {
        int pulses = countNeutronNeighbors() + (rodCount == 1 ? 1 : (rodCount == 2) ? 2 : 3);
        int energy = energyMult * pulses;

        if (moxStyle) {
            energy *= (1 + 4.0 * parent.getCurrentHeat() / parent.getMaxHeat());
        }
        minEUGenerated = Math.min(minEUGenerated, energy);
        maxEUGenerated = Math.max(maxEUGenerated, energy);
        currentEUGenerated = energy;
        parent.addEUOutput(energy);
        applyDamage(1);
        return energy;
    }

    @Override
    public int getRodCount() {
        return rodCount;
    }

    @Override
    public int getCurrentOutput() {
        if (parent != null) {
            if (parent.isFluid()) {
                return currentHeatGenerated;
            } else {
                return currentEUGenerated;
            }
        }
        return 0;
    }

}
