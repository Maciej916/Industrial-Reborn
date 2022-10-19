package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.enums.EnumLang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.List;

public class BlockWIP extends Block {

    public BlockWIP() {
        super(Properties.of(Material.SAND, MaterialColor.COLOR_BROWN));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(new TranslatableComponent(EnumLang.WIP.getTranslationKey()).withStyle(ChatFormatting.RED));

        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
