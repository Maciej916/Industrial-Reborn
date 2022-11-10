package com.maciej916.indreb.common.block.impl.machines.pattern_storage;

import com.maciej916.indreb.common.block.BlockMachine;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockPatternStorage extends BlockMachine implements IStateFacing, IHasMenu {

    public BlockPatternStorage() {
        super(0, 0);
    }

    @Override
    public AbstractContainerMenu getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuPatternStorage(windowId, level, pos, playerInventory, playerEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityPatternStorage(pos, state);
    }

}
