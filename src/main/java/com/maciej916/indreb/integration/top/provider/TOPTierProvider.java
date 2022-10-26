package com.maciej916.indreb.integration.top.provider;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.cable.BlockEntityCable;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.energy.provider.EnergyNetwork;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnergyTier;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TOPTierProvider implements IProbeInfoProvider {

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(IndReb.MODID, "energy_tier");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {
        BlockEntity blockEntity = level.getBlockEntity(iProbeHitData.getPos());
        if (blockEntity instanceof IndRebBlockEntity entity) {
            BasicEnergyStorage energy = entity.getEnergyStorage();
            if (energy != null) {
                iProbeInfo.text(
                        Component.translatable("top." + IndReb.MODID + ".energy_tier", Component.translatable(energy.energyTier().getLang().getTranslationKey()).withStyle(energy.energyTier().getColor())).withStyle(ChatFormatting.DARK_GRAY)
                );
            }
        }

        if (blockEntity instanceof BlockEntityCable cable) {
            EnergyNetwork network = cable.getNetwork();
            iProbeInfo.text(
                    Component.translatable("top." + IndReb.MODID + ".current_voltage", network.getEnergyFlowing() != null ? Component.translatable(network.getEnergyFlowing().getLang().getTranslationKey()).withStyle(network.getEnergyFlowing().getColor()) : Component.literal("-")).withStyle(ChatFormatting.DARK_GRAY)
            );
            iProbeInfo.text(
                    Component.translatable("top." + IndReb.MODID + ".energy_tier", Component.translatable(network.getEnergyTier().getLang().getTranslationKey()).withStyle(network.getEnergyTier().getColor())).withStyle(ChatFormatting.DARK_GRAY)
            );
        }
    }
}
