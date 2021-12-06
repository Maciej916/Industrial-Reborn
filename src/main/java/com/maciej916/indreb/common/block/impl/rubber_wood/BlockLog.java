package com.maciej916.indreb.common.block.impl.rubber_wood;

import com.maciej916.indreb.common.block.IndRebBlock;
import com.maciej916.indreb.common.interfaces.block.IStateAxis;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import javax.annotation.Nullable;

public class BlockLog extends IndRebBlock implements IStateAxis {

    public BlockLog() {
        super(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD).randomTicks());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch((Direction.Axis)state.getValue(BlockStateHelper.axisProperty)) {
                    case X:
                        return state.setValue(BlockStateHelper.axisProperty, Direction.Axis.Z);
                    case Z:
                        return state.setValue(BlockStateHelper.axisProperty, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(BlockStateHelper.axisProperty, pContext.getClickedFace().getAxis());
    }

}