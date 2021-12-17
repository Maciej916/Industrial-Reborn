package com.maciej916.indreb.common.item.base;

import com.maciej916.indreb.common.enums.UpgradeType;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UpgradeItem extends BaseItem {

    private final UpgradeType upgradeType;

    public UpgradeItem(int stackTo, UpgradeType upgradeType) {
        super(new Properties().stacksTo(stackTo).setNoRepair());
        this.upgradeType = upgradeType;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_REDSTONE) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }
}
