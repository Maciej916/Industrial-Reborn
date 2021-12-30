package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.impl.upgrade.ItemUpgrade;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.List;


public class UpgradeSlotHandler extends SlotItemHandler {

    private final List<UpgradeType> acceptedUpgrades;

    public UpgradeSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, List<UpgradeType> acceptedUpgrades) {
        super(itemHandler, index, xPosition, yPosition);
        this.acceptedUpgrades = acceptedUpgrades;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (stack.getItem() instanceof ItemUpgrade itemUpgrade) {
            return acceptedUpgrades.contains(itemUpgrade.getUpgradeType());
        }

        return false;
    }

    public List<UpgradeType> getAcceptedUpgrades() {
        return acceptedUpgrades;
    }
}
