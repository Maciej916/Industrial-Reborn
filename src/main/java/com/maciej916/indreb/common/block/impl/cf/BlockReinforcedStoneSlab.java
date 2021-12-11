package com.maciej916.indreb.common.block.impl.cf;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockReinforcedStoneSlab extends SlabBlock {

    public BlockReinforcedStoneSlab() {
        super(Properties.of(Material.STONE, MaterialColor.STONE).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
