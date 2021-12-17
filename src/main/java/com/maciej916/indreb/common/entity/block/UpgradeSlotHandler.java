package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;


public class UpgradeSlotHandler extends SlotItemHandler {

    private final HashSet<UpgradeType> acceptedUpgrades;

    public UpgradeSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, HashSet<UpgradeType> acceptedUpgrades) {
        super(itemHandler, index, xPosition, yPosition);
        this.acceptedUpgrades = acceptedUpgrades;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {

        return true;
    }

    public HashSet<UpgradeType> getAcceptedUpgrades() {
        return acceptedUpgrades;
    }
}
