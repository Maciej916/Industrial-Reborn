package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.util.wrench.WrenchHelper;

public class BlockElectricMachine extends BlockMachine {

    public BlockElectricMachine(int lightOn, int lightOff) {
        super(lightOn, lightOff);
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationAction()).add(WrenchHelper.dropAction());
    }

}
