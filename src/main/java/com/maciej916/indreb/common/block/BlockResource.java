package com.maciej916.indreb.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class BlockResource extends Block {

    public BlockResource(float hardnessIn, float resistanceIn) {
        super(Block.Properties.of(Material.METAL).strength(hardnessIn, resistanceIn));
    }

}
