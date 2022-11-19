package com.maciej916.indreb.common.block.impl;

import com.maciej916.indreb.common.api.block.BlockFoam;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.util.Constants;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class FoamBlock extends BlockFoam {

    public static final Vector3f PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(0xe4e4e4));

    public FoamBlock() {
        super(BlockBehaviour.Properties.of(Constants.FOAM_BLOCK).strength(0.25F).sound(SoundType.POWDER_SNOW).dynamicShape(), PARTICLE_COLOR);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        int count = pRandom.nextInt(0, 3);
        if (count == 0) {
            pLevel.setBlock(pPos, ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.get().defaultBlockState(), 2);
        }

        super.randomTick(pState, pLevel, pPos, pRandom);
    }
}