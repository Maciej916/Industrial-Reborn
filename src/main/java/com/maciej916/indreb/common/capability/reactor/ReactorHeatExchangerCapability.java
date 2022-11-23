package com.maciej916.indreb.common.capability.reactor;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ReactorHeatExchangerCapability extends ReactorComponentCapability {

    private final int switchSide;
    private final int switchReactor;

    public ReactorHeatExchangerCapability(ItemStack stack, int maxDamage, int maxHeat, int switchSide, int switchReactor) {
        super(stack, maxDamage, maxHeat);
        this.switchSide = switchSide;
        this.switchReactor = switchReactor;
    }

    @Override
    public void transfer() {
        List<IReactorComponentCapability> heatableNeighbors = new ArrayList<>(4);
        IReactorComponentCapability component = parent.getComponentAt(row, col - 1);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row, col + 1);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row - 1, col);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        component = parent.getComponentAt(row + 1, col);
        if (component != null && component.isHeatAcceptor()) {
            heatableNeighbors.add(component);
        }
        // Code adapted from decompiled IC2 code, class ItemReactorHeatSwitch, with permission from Thunderdark.
        int myHeat = 0;
        if (switchSide > 0) {
            for (IReactorComponentCapability heatableNeighbor : heatableNeighbors) {
                int mymed = getCurrentHeat() * 100 / getMaxHeat();
                int heatablemed = heatableNeighbor.getCurrentHeat() * 100 / heatableNeighbor.getMaxHeat();

                int add = (int) (heatableNeighbor.getMaxHeat() / 100.0 * (heatablemed + mymed / 2.0));
                if (add > switchSide) {
                    add = switchSide;
                }
                if (heatablemed + mymed / 2.0 < 1.0) {
                    add = switchSide / 2;
                }
                if (heatablemed + mymed / 2.0 < 0.75) {
                    add = switchSide / 4;
                }
                if (heatablemed + mymed / 2.0 < 0.5) {
                    add = switchSide / 8;
                }
                if (heatablemed + mymed / 2.0 < 0.25) {
                    add = 1;
                }
                if (Math.round(heatablemed * 10.0) / 10.0 > Math.round(mymed * 10.0) / 10.0) {
                    add -= 2 * add;
                } else if (Math.round(heatablemed * 10.0) / 10.0 == Math.round(mymed * 10.0) / 10.0) {
                    add = 0;
                }
                myHeat -= add;
                if (add > 0) {
                    currentComponentHeating += add;
                }
                add = heatableNeighbor.adjustCurrentHeat(add);
                myHeat += add;
            }
        }
        if (switchReactor > 0) {
            int mymed = getCurrentHeat() * 100 / getMaxHeat();
            int Reactormed = parent.getCurrentHeat() * 100 / parent.getMaxHeat();

            int add = (int) Math.round(parent.getMaxHeat() / 100.0 * (Reactormed + mymed / 2.0));
            if (add > switchReactor) {
                add = switchReactor;
            }
            if (Reactormed + mymed / 2.0 < 1.0) {
                add = switchSide / 2;
            }
            if (Reactormed + mymed / 2.0 < 0.75) {
                add = switchSide / 4;
            }
            if (Reactormed + mymed / 2.0 < 0.5) {
                add = switchSide / 8;
            }
            if (Reactormed + mymed / 2.0 < 0.25) {
                add = 1;
            }
            if (Math.round(Reactormed * 10.0) / 10.0 > Math.round(mymed * 10.0) / 10.0) {
                add -= 2 * add;
            } else if (Math.round(Reactormed * 10.0) / 10.0 == Math.round(mymed * 10.0) / 10.0) {
                add = 0;
            }
            myHeat -= add;
            parent.adjustCurrentHeat(add);
            if (add > 0) {
                currentHullHeating = add;
            } else {
                currentHullCooling = -add;
            }
        }
        adjustCurrentHeat(myHeat);
    }

    @Override
    public int getHullCoolingCapacity() {
        return switchReactor;
    }

}
