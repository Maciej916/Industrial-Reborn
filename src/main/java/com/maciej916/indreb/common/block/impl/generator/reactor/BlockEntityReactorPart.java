package com.maciej916.indreb.common.block.impl.generator.reactor;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockEntityNuclearReactor;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockNuclearReactor;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.multiblock.reactor.ReactorPartIndex;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEntityReactorPart extends IndRebBlockEntity {

    public BlockEntityReactorPart(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.REACTOR_PART.get(), pos, blockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.ENERGY) {
            BlockState state = getBlockState();
            if (state.getValue(BlockStateHelper.REACTOR_PART) != ReactorPartIndex.UNFORMED) {
                BlockPos controllerPos = BlockNuclearReactor.getControllerPos(level, getBlockPos());
                if (controllerPos != null) {
                    BlockEntity entity = level.getBlockEntity(controllerPos);
                    if (entity instanceof BlockEntityNuclearReactor entityNuclearReactor) {
                        return entityNuclearReactor.getCapability(cap, side);
                    }
                }
            }
        }

        return LazyOptional.empty();
    }
}
