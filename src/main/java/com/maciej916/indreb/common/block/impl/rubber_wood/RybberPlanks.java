package com.maciej916.indreb.common.block.impl.rubber_wood;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RybberPlanks extends Block {

    public RybberPlanks() {
        super(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
    }

}
