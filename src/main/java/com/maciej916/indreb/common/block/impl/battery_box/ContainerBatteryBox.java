package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.common.container.IndRebContainer;
import com.maciej916.indreb.common.registries.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ContainerBatteryBox extends IndRebContainer {

    public ContainerBatteryBox(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player);
    }

    public ContainerBatteryBox(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.BATTERY_BOX, windowId, world, pos, playerInventory, player);

    }

    @Override
    public void init() {
        this.playerInvTop = this.playerInvTop + 32;
        super.init();
    }
}
