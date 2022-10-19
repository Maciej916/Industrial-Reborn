package com.maciej916.indreb.common.interfaces.wrench;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IWrenchAction {

    boolean perform(Level world, BlockPos pos, BlockState blockState, Player player, Direction clickedFace);

}
