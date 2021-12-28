package com.maciej916.indreb.common.util;

import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateAxis;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.block.IStateRubberLog;
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

    public static final EnumProperty<Direction.Axis> axisProperty = BlockStateProperties.AXIS;
    public static final DirectionProperty facingProperty = BlockStateProperties.FACING;
    public static final DirectionProperty horizontalFacingProperty = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty activeProperty = BooleanProperty.create("active");
    public static final BooleanProperty wetProperty = BooleanProperty.create("wet");
    public static final BooleanProperty dryProperty = BooleanProperty.create("dry");
    public static final BooleanProperty waterlogged = BlockStateProperties.WATERLOGGED;

    public static BlockState getDefaultState(Block block, @Nonnull BlockState state) {

        if (block instanceof IStateActive) {
            state = state.setValue(activeProperty, false);
        }

        if (block instanceof IStateAxis) {
            state = state.setValue(axisProperty, Direction.Axis.Y);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            state = state.setValue(waterlogged, false);
        }

        if (block instanceof IStateRubberLog) {
            state = state.setValue(wetProperty, false);
            state = state.setValue(dryProperty, false);
        }

        return state;
    }

    public static BlockState getStateForPlacement(Block block, @Nullable BlockState state, BlockPlaceContext context) {
        if (state == null) {
            return null;
        }

        if (block instanceof IStateFacing blockFacing) {
            Direction newDirection;

            if (blockFacing.getFacingProperty() == horizontalFacingProperty) {
                newDirection = context.getHorizontalDirection().getOpposite();
            } else {
                newDirection = context.getNearestLookingDirection().getOpposite();
            }

            state = blockFacing.setDirection(state, newDirection);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            state = state.setValue(waterlogged, fluidstate.getType() == Fluids.WATER);
        }

        return state;
    }

    public static void fillBlockStateContainer(Block block, StateDefinition.Builder<Block, BlockState> builder) {
        List<Property<?>> properties = new ArrayList<>();

        if (block instanceof IStateFacing) {
            properties.add(((IStateFacing) block).getFacingProperty());
        }

        if (block instanceof IStateActive) {
            properties.add(activeProperty);
        }

        if (block instanceof IStateAxis) {
            properties.add(axisProperty);
        }

        if (block instanceof SimpleWaterloggedBlock) {
            properties.add(waterlogged);
        }

        if (block instanceof IStateRubberLog) {
            properties.add(wetProperty);
            properties.add(dryProperty);
        }

        if (!properties.isEmpty()) {
            builder.add(properties.toArray(new Property[0]));
        }
    }

    public static BlockState rotate(BlockState state, Rotation rotation) {
        Block block = state.getBlock();

        if (block instanceof IStateFacing) {
            IStateFacing blockFacing = (IStateFacing) block;
            return rotate(blockFacing, blockFacing.getFacingProperty(), state, rotation);
        }

        return state;
    }

    public static BlockState mirror(BlockState state, Mirror mirror) {
        Block block = state.getBlock();

        if (block instanceof IStateFacing) {
            IStateFacing blockFacing = (IStateFacing) block;
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
