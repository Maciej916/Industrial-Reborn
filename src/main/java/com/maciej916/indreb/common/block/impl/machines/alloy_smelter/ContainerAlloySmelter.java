package com.maciej916.indreb.common.block.impl.machines.alloy_smelter;

import com.maciej916.indreb.common.container.IndRebContainer;
import com.maciej916.indreb.common.registries.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ContainerAlloySmelter extends IndRebContainer {

    public ContainerAlloySmelter(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player);
    }

    public ContainerAlloySmelter(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.ALLOY_SMELTER, windowId, world, pos, playerInventory, player);

    }
}
