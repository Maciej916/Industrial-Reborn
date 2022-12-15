package com.maciej916.indreb.common.block.impl.machine.t_simple.simple_crusher;

import com.maciej916.indreb.common.api.block.BaseMachineBlock;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class BlockSimpleCrusher extends BaseMachineBlock {

    public BlockSimpleCrusher() {
        super(12, 0);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySimpleCrusher(pos, state);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
        if (stateIn.getValue(BlockStateHelper.ACTIVE_PROPERTY)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 1.05d;
            double d2 = (double)pos.getZ() + 0.5D;
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

}
