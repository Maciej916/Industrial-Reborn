package com.maciej916.indreb.common.block.impl.wood;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RubberPlate extends PressurePlateBlock {

    public RubberPlate() {
        super(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOD));
    }

}
