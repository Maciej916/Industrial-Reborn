package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.api.enums.UpgradeType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class BaseUpgradeItem extends BaseItem {

    private final UpgradeType upgradeType;

    public BaseUpgradeItem(UpgradeType upgradeType) {
        super(CreativeModeTab.TAB_REDSTONE, new Properties().stacksTo(16));
        this.upgradeType = upgradeType;
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

}
