package com.maciej916.indreb.integration.top;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.top.IHasProbeInfo;
import com.maciej916.indreb.common.api.top.BaseOneProbeInfo;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IndRebTOPProvider implements IProbeInfoProvider {

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(IndReb.MODID, "top");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if (blockEntity != null) {
            if (blockEntity instanceof IHasProbeInfo probeInfo) {
                for (BaseOneProbeInfo infoProvider : probeInfo.getProbeInfo()) {
                    infoProvider.addProbeInfo(probeMode, iProbeInfo, player, level, blockState, iProbeHitData);
                }
            }
        }
    }
}
