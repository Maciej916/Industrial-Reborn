package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.UpgradeType;
import com.maciej916.indreb.common.api.item.base.BaseUpgradeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TransformerUpgrade extends BaseUpgradeItem {

    public TransformerUpgrade() {
        super(UpgradeType.TRANSFORMER);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        components.add(Component.translatable("transformer." + IndReb.MODID + ".increase",Component.literal(String.valueOf(stack.getCount())).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
        components.add(Component.translatable("transformer." + IndReb.MODID + ".max", Component.translatable(EnergyTier.ULTRA.getLang().getTranslationKey())).withStyle(ChatFormatting.RED));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }

}
