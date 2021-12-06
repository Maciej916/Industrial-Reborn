package com.maciej916.indreb.common.interfaces.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface IHasContainer {

    AbstractContainerMenu getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity);

}
