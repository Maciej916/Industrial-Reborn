package com.maciej916.indreb.common.block.impl.reinforced;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ReinforcedStoneSlab extends SlabBlock {

    public ReinforcedStoneSlab() {
        super(Properties.of(Material.STONE, MaterialColor.STONE).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
