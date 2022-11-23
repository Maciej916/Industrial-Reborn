package com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.screen.IndRebContainerMenu;
import com.maciej916.indreb.common.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class MenuNuclearReactor extends IndRebContainerMenu {

    public MenuNuclearReactor(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this((IndRebBlockEntity) playerInventory.player.level.getBlockEntity(extraData.readBlockPos()), containerId, playerInventory, playerInventory.player, new SimpleContainerData(BlockEntityNuclearReactor.SYNC_DATA_SLOTS));
    }

    public MenuNuclearReactor(IndRebBlockEntity entity, int containerId, Inventory playerInventory, Player player, ContainerData data) {
        super(ModMenuTypes.NUCLEAR_REACTOR.get(), entity, containerId, playerInventory, player, data);
    }

    @Override
    public void init() {
        this.playerInvTop = this.playerInvTop + 71;
        this.playerInvLeft = this.playerInvLeft + 18;
        super.init();
    }
}
