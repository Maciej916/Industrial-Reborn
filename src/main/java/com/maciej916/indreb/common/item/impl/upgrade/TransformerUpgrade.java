package com.maciej916.indreb.common.item.impl.upgrade;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.UpgradeType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TransformerUpgrade extends ItemUpgrade {

    public TransformerUpgrade() {
        super(UpgradeType.TRANSFORMER);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> components, TooltipFlag tooltipFlag) {

        components.add(new TranslatableComponent("transformer." + IndReb.MODID + ".increase", new TextComponent(String.valueOf(stack.getCount())).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
        components.add(new TranslatableComponent("transformer." + IndReb.MODID + ".max", new TranslatableComponent(EnergyTier.ULTRA.getLang().getTranslationKey())).withStyle(ChatFormatting.RED));

        super.appendHoverText(stack, p_41422_, components, tooltipFlag);
    }
}
