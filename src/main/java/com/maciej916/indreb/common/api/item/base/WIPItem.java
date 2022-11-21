package com.maciej916.indreb.common.api.item.base;

import com.maciej916.indreb.common.enums.EnumLang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class WIPItem extends BaseItem {

    public WIPItem() {
        super(null, new Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable(EnumLang.WIP.getTranslationKey()).withStyle(ChatFormatting.DARK_GRAY));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.create("wip", ChatFormatting.DARK_RED);
    }

}
