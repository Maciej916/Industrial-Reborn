package com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityNuclearReactor extends IndRebBlockEntity {

    public static final int SYNC_DATA_SLOTS = 0;
    protected final ContainerData data;

    public BlockEntityNuclearReactor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.NUCLEAR_REACTOR.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {


                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {

                }
            }

            @Override
            public int getCount() {
                return SYNC_DATA_SLOTS;
            }
        };
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuNuclearReactor(this, containerId, playerInventory, player, data);
    }


}
