package com.maciej916.indreb.common.block.impl.battery_box;

import com.maciej916.indreb.common.api.block.IndRebEntityBlock;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.tier.BatteryBoxTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockBatteryBox extends IndRebEntityBlock implements IStateFacing, IElectricMachine {

    private final BatteryBoxTier batteryBoxTier;

    public BlockBatteryBox(BatteryBoxTier batteryBoxTier, Properties properties) {
        super(properties);
        this.batteryBoxTier = batteryBoxTier;
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationHitAction()).add(WrenchHelper.dropAction());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityBatteryBox(pos, state);
    }

    public BatteryBoxTier getBatteryBoxTier() {
        return batteryBoxTier;
    }

    @Nonnull
    @Override
    public DirectionProperty getFacingProperty() {
        return BlockStateHelper.FACING_PROPERTY;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @javax.annotation.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(batteryBoxTier.getEnergyTier().getLang().getTranslationKey()).withStyle(batteryBoxTier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.TRANSFER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(batteryBoxTier.getEnergyTier().getBasicTransfer(), Screen.hasShiftDown())).withStyle(batteryBoxTier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(batteryBoxTier.getEnergyCapacity(), Screen.hasShiftDown())).withStyle(batteryBoxTier.getEnergyTier().getColor())
        ));
    }

    @Override
    public EnergyTier getEnergyTier() {
        return batteryBoxTier.getEnergyTier();
    }

}
