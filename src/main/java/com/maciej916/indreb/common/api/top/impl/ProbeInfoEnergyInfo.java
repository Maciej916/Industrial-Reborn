package com.maciej916.indreb.common.api.top.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.util.TextComponentUtil;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ProbeInfoEnergyInfo extends BaseOneProbeInfo {

    private final BasicEnergyStorage energyStorage;

    public ProbeInfoEnergyInfo(BasicEnergyStorage energyStorage) {
        this.energyStorage = energyStorage;
    }

    public BasicEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {

        iProbeInfo.text(Component.translatable("top." + IndReb.MODID + ".energy_tier", energyStorage.energyTier().getLang().getTranslationComponent()));

        if (energyStorage.energyType() == EnergyType.EXTRACT) {
            iProbeInfo.text(Component.translatable("ie." + IndReb.MODID + ".last_generated", Component.translatable(TextComponentUtil.getFormattedStorageUnit(energyStorage.lastGenerated()) + " IE/t")));
            iProbeInfo.text(Component.translatable("ie." + IndReb.MODID + ".total_generated", Component.translatable(TextComponentUtil.getFormattedStorageUnit(energyStorage.totalGenerated()) + " IE")));
        }

        if (energyStorage.energyType() == EnergyType.RECEIVE) {
            iProbeInfo.text(Component.translatable("ie." + IndReb.MODID + ".last_consumed", Component.translatable(TextComponentUtil.getFormattedStorageUnit(energyStorage.lastConsumed()) + " IE/t")));
            iProbeInfo.text(Component.translatable("ie." + IndReb.MODID + ".total_consumed", Component.translatable(TextComponentUtil.getFormattedStorageUnit(energyStorage.totalConsumed()) + " IE")));
        }

    }
}
