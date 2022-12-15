package com.maciej916.indreb.common.block.impl.wood;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class StrippedRubberWood extends RotatedPillarBlock {

    public StrippedRubberWood() {
        super(Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD));
    }

}
