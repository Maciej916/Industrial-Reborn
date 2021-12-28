package com.maciej916.indreb.common.block.impl.cf;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BlockCF extends AbstractGlassBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

    public BlockCF() {
        super(Properties.of(Material.WOOD, MaterialColor.WOOL).strength(0.5F, 3.0F).sound(SoundType.WOOL).randomTicks().noOcclusion());
    }

    public VoxelShape getCollisionShape(BlockState p_54015_, BlockGetter p_54016_, BlockPos p_54017_, CollisionContext p_54018_) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        int count = ThreadLocalRandom.current().nextInt(0, 3);
        if (count == 0) {
            pLevel.setBlock(pPos, ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.getBlock().defaultBlockState(), 2);
        }

        super.tick(pState, pLevel, pPos, pRandom);
    }
}
