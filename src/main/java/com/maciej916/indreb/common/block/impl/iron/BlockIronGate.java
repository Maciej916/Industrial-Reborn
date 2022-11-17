package com.maciej916.indreb.common.block.impl.iron;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockIronGate extends FenceGateBlock {

    public BlockIronGate() {
        super(Properties.of(Material.METAL).strength(3F, 5F).sound(SoundType.METAL));
    }

}
