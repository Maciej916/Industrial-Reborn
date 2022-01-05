package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.UpgradeType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.text.DecimalFormat;
import java.util.List;

public class OverclockerUpgrade extends ItemUpgrade {

    DecimalFormat df = new DecimalFormat("0.00");

    public OverclockerUpgrade() {
        super(UpgradeType.OVERCLOCKER);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        double speed = Math.pow(0.7, stack.getCount()) * 100;
        double energy = Math.pow(1.6, stack.getCount()) * 100;

        components.add(new TranslatableComponent("speed." + IndReb.MODID + ".factor", new TextComponent(df.format(speed) + "%").withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
        components.add(new TranslatableComponent("power." + IndReb.MODID + ".factor", new TextComponent(df.format(energy) + "%").withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }
}
