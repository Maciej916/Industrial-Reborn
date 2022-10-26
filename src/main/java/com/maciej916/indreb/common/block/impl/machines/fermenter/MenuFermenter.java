package com.maciej916.indreb.common.block.impl.machines.fermenter;

import com.maciej916.indreb.common.container.IndRebMenu;
import com.maciej916.indreb.common.registries.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MenuFermenter extends IndRebMenu {

    public MenuFermenter(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player);
    }

    public MenuFermenter(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModMenuTypes.FERMENTER.get(), windowId, world, pos, playerInventory, player);

    }

    @Override
    public void init() {
        this.playerInvTop = this.playerInvTop + 22;
        super.init();
    }
}
