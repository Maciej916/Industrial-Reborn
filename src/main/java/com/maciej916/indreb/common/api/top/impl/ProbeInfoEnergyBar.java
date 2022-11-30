package com.maciej916.indreb.common.api.top.impl;

import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import com.maciej916.indreb.common.util.TextComponentUtil;
import mcjty.theoneprobe.api.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ProbeInfoEnergyBar extends BaseOneProbeInfo {

    private final BasicEnergyStorage energyStorage;

    public ProbeInfoEnergyBar(BasicEnergyStorage energyStorage) {
        this.energyStorage = energyStorage;
    }

    public BasicEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        Color filledColor = new Color(100, 0, 0);
        Color alternateFilledColor = new Color(78, 0, 0);
        Color borderColor = new Color(78, 0, 0);

        IProbeInfo horizontalPane = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        horizontalPane.progress(energyStorage.energyStored(), energyStorage.maxEnergy(), iProbeInfo.defaultProgressStyle()
                .numberFormat(NumberFormat.COMPACT)
                .suffix(" / " + TextComponentUtil.getFormattedStorageUnit(energyStorage.maxEnergy()) + " IE")
                .borderColor(borderColor)
                .filledColor(filledColor)
                .alternateFilledColor(alternateFilledColor));

    }
}
