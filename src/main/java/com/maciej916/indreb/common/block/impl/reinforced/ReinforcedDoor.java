package com.maciej916.indreb.common.block.impl.reinforced;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ReinforcedDoor extends DoorBlock {

    public ReinforcedDoor() {
        super(Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
