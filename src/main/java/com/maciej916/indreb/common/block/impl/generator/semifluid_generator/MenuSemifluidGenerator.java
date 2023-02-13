package com.maciej916.indreb.common.block.impl.generator.semifluid_generator;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.screen.IndRebContainerMenu;
import com.maciej916.indreb.common.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class MenuSemifluidGenerator extends IndRebContainerMenu {

    public MenuSemifluidGenerator(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this((IndRebBlockEntity) playerInventory.player.level.getBlockEntity(extraData.readBlockPos()), containerId, playerInventory, playerInventory.player, new SimpleContainerData(0));
    }

    public MenuSemifluidGenerator(IndRebBlockEntity entity, int containerId, Inventory playerInventory, Player player, ContainerData data) {
        super(ModMenuTypes.SEMIFLUID_GENERATOR.get(), entity, containerId, playerInventory, player, data);
    }
}