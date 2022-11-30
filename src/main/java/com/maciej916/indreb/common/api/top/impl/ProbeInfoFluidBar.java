package com.maciej916.indreb.common.api.top.impl;

import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import mcjty.theoneprobe.api.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.IFluidTank;

public class ProbeInfoFluidBar extends BaseOneProbeInfo {

    private final IFluidTank fluidTank;

    public ProbeInfoFluidBar(IFluidTank fluidTank) {
        this.fluidTank = fluidTank;
    }

    public IFluidTank getFluidTank() {
        return fluidTank;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        IProbeInfo horizontalPane = iProbeInfo.horizontal(iProbeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        horizontalPane.tank(fluidTank);
    }
}
