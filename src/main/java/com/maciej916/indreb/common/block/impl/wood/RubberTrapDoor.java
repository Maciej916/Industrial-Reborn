package com.maciej916.indreb.common.block.impl.wood;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RubberTrapDoor extends TrapDoorBlock {

    public RubberTrapDoor() {
        super(Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD));
    }

}
