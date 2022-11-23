package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.item.base.BaseUpgradeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.text.DecimalFormat;
import java.util.List;

public class OverclockerUpgrade extends BaseUpgradeItem {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public OverclockerUpgrade() {
        super(UpgradeType.OVERCLOCKER);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        double speed = Math.pow(0.7, stack.getCount()) * 100;
        double energy = Math.pow(1.6, stack.getCount()) * 100;

        components.add(Component.translatable("speed." + IndReb.MODID + ".factor",Component.literal(df.format(speed) + "%").withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
        components.add(Component.translatable("power." + IndReb.MODID + ".factor",Component.literal(df.format(energy) + "%").withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }

}
