package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

public class BlockStandardElectricMachine extends BlockElectricMachine {

    public BlockStandardElectricMachine(int lightOn, int lightOff) {
        super(lightOn, lightOff);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.TIER_BASIC.getTranslationKey()).withStyle(ChatFormatting.GREEN)
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.generator_tick_generate.get())).withStyle(ChatFormatting.GREEN)
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.OUTPUT_CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.basic_tier_transfer.get())).withStyle(ChatFormatting.GREEN)
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.generator_energy_capacity.get())).withStyle(ChatFormatting.GREEN)
        ));
    }


}
