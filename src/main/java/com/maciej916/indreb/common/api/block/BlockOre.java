package com.maciej916.indreb.common.api.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class BlockOre extends IndRebEntityBlock {

    public BlockOre(Material material, float destroyTime, float explosionResistance) {
        super(Block.Properties.of(material).strength(destroyTime, explosionResistance));
    }

}
