package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.item.base.BaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent(EnumLang.WIP.getTranslationKey()).withStyle(ChatFormatting.RED));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
