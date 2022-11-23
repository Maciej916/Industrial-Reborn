package com.maciej916.indreb.common.api.slot.handler;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.blockentity.interfaces.IHasExp;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class OutputSlotItemHandler extends BaseSlotItemHandler {

    private final IndRebBlockEntity entity;

    public OutputSlotItemHandler(IItemHandler itemHandler, IndRebBlockEntity entity, int index, int xPosition, int yPosition, boolean isActive) {
        super(itemHandler, index, xPosition, yPosition, isActive);
        this.entity = entity;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (!player.level.isClientSide() && entity instanceof IHasExp iec) {
            if (!iec.hasExpButton()) {
                iec.collectExpServer((ServerPlayer) player);
            }
        }
        super.onTake(player, stack);
    }

}
