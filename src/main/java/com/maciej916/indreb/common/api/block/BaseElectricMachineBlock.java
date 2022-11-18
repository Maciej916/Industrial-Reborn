package com.maciej916.indreb.common.api.block;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseElectricMachineBlock extends BaseMachineBlock implements IElectricMachine {

    private final EnergyTier tier;

    public BaseElectricMachineBlock(EnergyTier tier, int lightOn, int lightOff) {
        super(lightOn, lightOff);
        this.tier = tier;
        registerWrenchAction();
    }

    public void registerWrenchAction() {
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationAction()).add(WrenchHelper.dropAction());
    }

    @Override
    public EnergyTier getEnergyTier() {
        return tier;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(tier.getLang().getTranslationKey()).withStyle(tier.getColor())
        ));
    }
}
