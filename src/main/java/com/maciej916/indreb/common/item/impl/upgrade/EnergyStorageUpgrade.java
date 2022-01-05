package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.UpgradeType;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EnergyStorageUpgrade extends ItemUpgrade {

    public EnergyStorageUpgrade() {
        super(UpgradeType.ENERGY_STORAGE);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        int amount = 10000 * stack.getCount();
        components.add(new TranslatableComponent("power." + IndReb.MODID + ".increase", new TextComponent(TextComponentUtil.getFormattedEnergyUnit(amount)).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }
}
