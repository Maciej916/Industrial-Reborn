package com.maciej916.indreb.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class BlockOre extends Block {

    public BlockOre() {
        super(Block.Properties.of(Material.STONE).strength(3F, 5F));
    }

}
