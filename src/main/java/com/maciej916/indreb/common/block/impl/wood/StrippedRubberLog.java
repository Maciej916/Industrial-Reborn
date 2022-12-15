package com.maciej916.indreb.common.block.impl.wood;

import com.maciej916.indreb.common.api.block.IndRebBlock;
import com.maciej916.indreb.common.api.interfaces.block.IStateAxis;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

public class StrippedRubberLog extends IndRebBlock implements IStateAxis {

    public StrippedRubberLog() {
        super(Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD).randomTicks());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(BlockStateHelper.AXIS_PROPERTY)) {
                case X -> state.setValue(BlockStateHelper.AXIS_PROPERTY, Direction.Axis.Z);
                case Z -> state.setValue(BlockStateHelper.AXIS_PROPERTY, Direction.Axis.X);
                default -> state;
            };
            default -> state;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(BlockStateHelper.AXIS_PROPERTY, pContext.getClickedFace().getAxis());
    }


}
