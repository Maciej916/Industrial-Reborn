package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.UpgradeType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class RedstoneSignalInverter extends ItemUpgrade {

    public RedstoneSignalInverter() {
        super(UpgradeType.REDSTONE_SIGNAL_INVERTER);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        components.add(new TranslatableComponent("redstone." + IndReb.MODID + ".inverter").withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }
}
