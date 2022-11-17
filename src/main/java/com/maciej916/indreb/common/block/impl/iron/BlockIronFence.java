package com.maciej916.indreb.common.block.impl.iron;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockIronFence extends FenceBlock {

    public BlockIronFence() {
        super(Properties.of(Material.METAL).strength(3F, 5F).sound(SoundType.METAL));
    }

}
