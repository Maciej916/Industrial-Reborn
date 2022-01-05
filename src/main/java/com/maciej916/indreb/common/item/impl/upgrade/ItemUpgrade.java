package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.base.BaseItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemUpgrade extends BaseItem {

    private final UpgradeType upgradeType;

    public ItemUpgrade(UpgradeType upgradeType) {
        super(new Properties().stacksTo(16));
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

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_REDSTONE) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

}
