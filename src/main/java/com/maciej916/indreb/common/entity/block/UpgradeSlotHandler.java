package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.base.UpgradeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Objects;


public class UpgradeSlotHandler extends SlotItemHandler {

    private final HashSet<UpgradeType> acceptedUpgrades;

    public UpgradeSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, HashSet<UpgradeType> acceptedUpgrades) {
        super(itemHandler, index, xPosition, yPosition);
        this.acceptedUpgrades = acceptedUpgrades;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {



        if (stack.getItem() instanceof UpgradeItem upgradeItem) {
            if (acceptedUpgrades.contains(upgradeItem.getUpgradeType())) {
               return true;
            }
        }

        return false;
    }

    public HashSet<UpgradeType> getAcceptedUpgrades() {
        return acceptedUpgrades;
    }
}
