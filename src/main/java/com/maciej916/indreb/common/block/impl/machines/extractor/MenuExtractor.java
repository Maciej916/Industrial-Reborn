package com.maciej916.indreb.common.block.impl.machines.extractor;

import com.maciej916.indreb.common.container.IndRebMenu;
import com.maciej916.indreb.common.registries.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MenuExtractor extends IndRebMenu {

    public MenuExtractor(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player);
    }

    public MenuExtractor(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModMenuTypes.EXTRACTOR.get(), windowId, world, pos, playerInventory, player);

    }
}
