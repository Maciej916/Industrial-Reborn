package com.maciej916.indreb.common.block.impl;

import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.material.Material;

public class BlockIronScaffolding extends ScaffoldingBlock {

    public BlockIronScaffolding() {
        super(Properties.of(Material.METAL).strength(3F, 5F));
    }
}
