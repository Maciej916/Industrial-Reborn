package com.maciej916.indreb.common.api.top.impl;

import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ProbeInfoSimpleText extends BaseOneProbeInfo {

    private final MutableComponent component;

    public ProbeInfoSimpleText(MutableComponent component) {
        this.component = component;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        iProbeInfo.text(component);
    }
}
