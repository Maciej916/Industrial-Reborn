package com.maciej916.indreb.common.block.impl.rubber_wood;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.Material;

public class BlockSapling extends SaplingBlock {

    public BlockSapling(AbstractTreeGrower tree) {
        super(tree, Block.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).instabreak().sound(SoundType.GRASS));
    }
}
