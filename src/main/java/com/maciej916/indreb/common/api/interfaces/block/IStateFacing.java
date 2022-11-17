package com.maciej916.indreb.common.api.interfaces.block;

import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface IStateFacing {

    default Direction getDirection(BlockState state) {
        return state.getValue(getFacingProperty());
    }

    default BlockState setDirection(@Nonnull BlockState state, Direction newDirection) {
        return supportsDirection(newDirection) ? state.setValue(getFacingProperty(), newDirection) : state;
    }

    @Nonnull
    default DirectionProperty getFacingProperty() {
        return BlockStateHelper.HORIZONTAL_FACING_PROPERTY;
    }

    default Collection<Direction> getSupportedDirections() {
        return getFacingProperty().getPossibleValues();
    }

    default boolean supportsDirection(Direction direction) {
        return getSupportedDirections().contains(direction);
    }

}
