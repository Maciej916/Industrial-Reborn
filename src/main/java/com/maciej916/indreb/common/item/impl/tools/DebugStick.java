package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.energy.PacketSyncEnergy;
import com.maciej916.indreb.common.registries.ModCapabilities;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DebugStick extends IEMeter {
    public DebugStick() {
        super();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(iEnergyCore -> {
                ModNetworking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new PacketSyncEnergy(iEnergyCore.getNetworkTag(null)));
            });
        }
        return super.use(level, player, hand);
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {

    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level level, List<Component> components, TooltipFlag flag) {

    }
}
