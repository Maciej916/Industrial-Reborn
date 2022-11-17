package com.maciej916.indreb.common.util;

import com.maciej916.indreb.common.api.interfaces.block.IStateActive;
import com.maciej916.indreb.common.api.interfaces.block.IStateAxis;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.interfaces.block.IStateRubberLog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockStateHelper {

    public static final EnumProperty<Direction.Axis> AXIS_PROPERTY = BlockStateProperties.AXIS;
    public static final DirectionProperty FACING_PROPERTY = BlockStateProperties.FACING;
    public static final DirectionProperty HORIZONTAL_FACING_PROPERTY = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE_PROPERTY = BooleanProperty.create("active");
    public static final BooleanProperty WET_PROPERTY = BooleanProperty.create("wet");
    public static final BooleanProperty DRY_PROPERTY = BooleanProperty.create("dry");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static BlockState getDefaultState(Block block, @Nonnull BlockState state) {

        if (block instanceof IStateActive) {
            state = state.setValue(ACTIVE_PROPERTY, false);
        }

        if (block instanceof IStateAxis) {
            state = state.setValue(AXIS_PROPERTY, Direction.Axis.Y);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            state = state.setValue(WATERLOGGED, false);
        }

        if (block instanceof IStateRubberLog) {
            state = state.setValue(WET_PROPERTY, false);
            state = state.setValue(DRY_PROPERTY, false);
        }

        return state;
    }

    public static BlockState getStateForPlacement(Block block, @Nullable BlockState state, BlockPlaceContext context) {
        if (state == null) {
            return null;
        }

        if (block instanceof IStateFacing blockFacing) {
            Direction newDirection;

            if (blockFacing.getFacingProperty() == HORIZONTAL_FACING_PROPERTY) {
                newDirection = context.getHorizontalDirection().getOpposite();
            } else {
                newDirection = context.getNearestLookingDirection().getOpposite();
            }

            state = blockFacing.setDirection(state, newDirection);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            state = state.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }

        return state;
    }

    public static void fillBlockStateContainer(Block block, StateDefinition.Builder<Block, BlockState> builder) {
        List<Property<?>> properties = new ArrayList<>();

        if (block instanceof IStateFacing) {
            properties.add(((IStateFacing) block).getFacingProperty());
        }

        if (block instanceof IStateActive) {
            properties.add(ACTIVE_PROPERTY);
        }

        if (block instanceof IStateAxis) {
            properties.add(AXIS_PROPERTY);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            properties.add(WATERLOGGED);
        }

        if (block instanceof IStateRubberLog) {
            properties.add(WET_PROPERTY);
            properties.add(DRY_PROPERTY);
        }

        if (!properties.isEmpty()) {
            builder.add(properties.toArray(new Property[0]));
        }
    }

    public static BlockState rotate(BlockState state, Rotation rotation) {
        Block block = state.getBlock();

        if (block instanceof IStateFacing blockFacing) {
            return rotate(blockFacing, blockFacing.getFacingProperty(), state, rotation);
        }

        return state;
    }

    public static BlockState mirror(BlockState state, Mirror mirror) {
        Block block = state.getBlock();

        if (block instanceof IStateFacing blockFacing) {
            DirectionProperty property = blockFacing.getFacingProperty();
            return rotate(blockFacing, property, state, mirror.getRotation(state.getValue(property)));
        }

        return state;
    }

    private static BlockState rotate(IStateFacing blockFacing, DirectionProperty property, BlockState state, Rotation rotation) {
        return blockFacing.setDirection(state, rotation.rotate(state.getValue(property)));
    }

    public static BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation rotation) {
        return rotate(state, rotation);
    }

}
