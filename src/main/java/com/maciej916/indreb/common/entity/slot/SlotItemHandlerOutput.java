package com.maciej916.indreb.common.entity.slot;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlotItemHandlerOutput extends IndRebSlotItemHandler {

    public SlotItemHandlerOutput(IndRebBlockEntity entity, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(entity, itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (!player.level.isClientSide() && getEntity() instanceof IExpCollector iec) {
            if (!iec.hasExpButton()) {
                iec.collectExp(player);
            }
        }
        super.onTake(player, stack);
    }

}
