package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.item.base.BaseUpgradeItem;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EnergyStorageUpgrade extends BaseUpgradeItem {

    public EnergyStorageUpgrade() {
        super(UpgradeType.ENERGY_STORAGE);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag tooltipFlag) {

        int amount = 10000 * stack.getCount();
        components.add(Component.translatable("power." + IndReb.MODID + ".increase",Component.literal(TextComponentUtil.getFormattedStorageUnit(amount)).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, level, components, tooltipFlag);
    }

}
