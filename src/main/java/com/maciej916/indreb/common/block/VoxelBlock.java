package com.maciej916.indreb.common.block;

import com.google.common.collect.Maps;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class VoxelBlock extends IndRebBlock {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (direction) -> {
        direction.put(Direction.NORTH, NORTH);
        direction.put(Direction.EAST, EAST);
        direction.put(Direction.SOUTH, SOUTH);
        direction.put(Direction.WEST, WEST);
        direction.put(Direction.UP, UP);
        direction.put(Direction.DOWN, DOWN);
    });
    protected final VoxelShape[] shapes;

    public VoxelBlock(Properties properties, float apothem) {
        super(properties);
        this.shapes = this.makeShapes(apothem);
    }

    private VoxelShape[] makeShapes(float apothem) {
        float f = 0.5F - apothem;
        float f1 = 0.5F + apothem;
        VoxelShape voxelshape = box(f * 16.0F, f * 16.0F, f * 16.0F, f1 * 16.0F, f1 * 16.0F, f1 * 16.0F);
        VoxelShape[] avoxelshape = new VoxelShape[Constants.DIRECTIONS.length];

        for(int i = 0; i < Constants.DIRECTIONS.length; ++i) {
            Direction direction = Constants.DIRECTIONS[i];
            avoxelshape[i] = Shapes.create(0.5D + Math.min(-apothem, (double)direction.getStepX() * 0.5D), 0.5D + Math.min(-apothem, (double)direction.getStepY() * 0.5D), 0.5D + Math.min((double)(-apothem), (double)direction.getStepZ() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getStepX() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getStepY() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getStepZ() * 0.5D));
        }

        VoxelShape[] avoxelshape1 = new VoxelShape[64];

        for(int k = 0; k < 64; ++k) {
            VoxelShape voxelshape1 = voxelshape;

            for(int j = 0; j < Constants.DIRECTIONS.length; ++j) {
                if ((k & 1 << j) != 0) {
                    voxelshape1 = Shapes.or(voxelshape1, avoxelshape[j]);
                }
            }

            avoxelshape1[k] = voxelshape1;
        }

        return avoxelshape1;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.shapes[this.getShapeIndex(pState)];
    }

    protected int getShapeIndex(BlockState state) {
        int i = 0;

        for(int j = 0; j < Constants.DIRECTIONS.length; ++j) {
            if (state.getValue(FACING_TO_PROPERTY_MAP.get(Constants.DIRECTIONS[j]))) {
                i |= 1 << j;
            }
        }

        return i;
    }

    protected boolean canConnect(LevelAccessor world, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public BlockState setStateForPlacement(BlockPlaceContext pContext, BlockState blockState) {

        Level world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        for (Direction direction : Constants.DIRECTIONS) {
            boolean valid = canConnect(world, pos.relative(direction), direction);
            blockState = blockState.setValue(FACING_TO_PROPERTY_MAP.get(direction), valid);
        }

        return super.setStateForPlacement(pContext, blockState);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        for (Direction direction : Constants.DIRECTIONS) {
            if (pDirection == direction) {
                boolean valid  = canConnect(pLevel, pNeighborPos, pDirection);
                pState = pState.setValue(FACING_TO_PROPERTY_MAP.get(direction), valid);
            }
        }
        return pState;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(UP, DOWN, NORTH, EAST, WEST, SOUTH);
        super.createBlockStateDefinition(pBuilder);
    }
}
