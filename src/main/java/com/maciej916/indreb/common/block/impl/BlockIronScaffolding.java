package com.maciej916.indreb.common.block.impl;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

public class BlockIronScaffolding extends ScaffoldingBlock {

    public BlockIronScaffolding() {
        super(Properties.of(Material.METAL).strength(3F, 5F));
    }
}
