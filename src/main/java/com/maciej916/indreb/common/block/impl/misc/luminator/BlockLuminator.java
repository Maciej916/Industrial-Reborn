package com.maciej916.indreb.common.block.impl.misc.luminator;

import com.maciej916.indreb.common.api.block.IndRebEntityBlock;
import com.maciej916.indreb.common.api.interfaces.block.IStateActive;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLuminator extends IndRebEntityBlock implements IStateFacing, IStateActive, SimpleWaterloggedBlock {

    public VoxelShape DOWN = Shapes.box(0, 0.8, 0, 1, 1, 1);
    public VoxelShape UP = Shapes.box(0, 0, 0, 1, 0.2, 1);
    public VoxelShape NORTH = Shapes.box(0, 0, 0.8, 1, 1, 1);
    public VoxelShape SOUTH = Shapes.box(0, 0, 0, 1, 1, 0.2);
    public VoxelShape WEST = Shapes.box(0.8,0,0,1,1,1);
    public VoxelShape EAST = Shapes.box(0,0,0,0.2,1,1);

    public BlockLuminator() {
        super(Properties.of(Material.GLASS, MaterialColor.QUARTZ).noCollission().dynamicShape().lightLevel(state -> state.getValue(BlockStateHelper.ACTIVE_PROPERTY) ? 16 : 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction facing = state.getValue(BlockStateHelper.FACING_PROPERTY);
        return switch (facing.get3DDataValue()) {
            case 0 -> DOWN;
            default -> UP;
            case 2 -> NORTH;
            case 3 -> SOUTH;
            case 4 -> WEST;
            case 5 -> EAST;
        };
    }

    @NotNull
    @Override
    public DirectionProperty getFacingProperty() {
        return BlockStateHelper.FACING_PROPERTY;
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateHelper.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityLuminator(pos, state);
    }
}
