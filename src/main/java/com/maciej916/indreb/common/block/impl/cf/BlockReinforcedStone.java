package com.maciej916.indreb.common.block.impl.cf;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockReinforcedStone extends Block {

    public BlockReinforcedStone() {
        super(Properties.of(Material.STONE, MaterialColor.STONE).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
