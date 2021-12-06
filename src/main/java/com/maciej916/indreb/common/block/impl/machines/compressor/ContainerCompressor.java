package com.maciej916.indreb.common.block.impl.machines.compressor;

import com.maciej916.indreb.common.container.IndRebContainer;
import com.maciej916.indreb.common.registries.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ContainerCompressor extends IndRebContainer {

    public ContainerCompressor(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player);
    }

    public ContainerCompressor(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.COMPRESSOR, windowId, world, pos, playerInventory, player);

    }
}
