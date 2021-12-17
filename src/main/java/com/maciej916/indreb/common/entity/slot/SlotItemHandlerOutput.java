package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlotItemHandlerOutput extends SlotItemHandler {

    private final IndRebBlockEntity be;

    public SlotItemHandlerOutput(IndRebBlockEntity be, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.be = be;
    }


    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {

        if (!pPlayer.level.isClientSide() && be instanceof IExpCollector iec) {
            if (!iec.hasExpButton()) {
                iec.collectExp(pPlayer);
            }
        }

        super.onTake(pPlayer, pStack);
    }

}
