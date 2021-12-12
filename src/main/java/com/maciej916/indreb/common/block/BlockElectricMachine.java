package com.maciej916.indreb.common.block;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

public class BlockElectricMachine extends BlockMachine implements IElectricMachine {

    private final EnergyTier tier;

    public BlockElectricMachine(EnergyTier tier, int lightOn, int lightOff) {
        super(lightOn, lightOff);
        this.tier = tier;
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationAction()).add(WrenchHelper.dropAction());
    }

    @Override
    public EnergyTier getEnergyTier() {
        return tier;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(tier.getLang().getTranslationKey()).withStyle(tier.getColor())
        ));
    }
}
