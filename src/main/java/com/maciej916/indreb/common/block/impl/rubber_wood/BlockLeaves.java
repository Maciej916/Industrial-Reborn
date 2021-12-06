package com.maciej916.indreb.common.block.impl.rubber_wood;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockLeaves extends LeavesBlock {

    public BlockLeaves() {
        super(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());
    }
}
