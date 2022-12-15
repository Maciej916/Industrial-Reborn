package com.maciej916.indreb.common.item.impl.reactor;

import com.maciej916.indreb.common.capability.radiation.IHasRadiation;

public class DepletedFuelRodItem extends BaseReactorItem implements IHasRadiation {

    private final double rads;

    public DepletedFuelRodItem(double rads) {
        super(new Properties(), 1, 1);
        this.rads = rads;
    }

    @Override
    public double getAddRads() {
        return rads;
    }

}
