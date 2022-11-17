package com.maciej916.indreb.common.block.impl.reinforced;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ReinforcedWall extends WallBlock {

    public ReinforcedWall() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
