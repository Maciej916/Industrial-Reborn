package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.cable.BlockEntityCable;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.provider.EnergyNetwork;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.item.base.BaseItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IEMeter extends BaseItem {
    public IEMeter() {
        super(new Properties());
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(new TranslatableComponent("ie." + IndReb.MODID + ".desc").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(p_41421_, level, components, flag);
    }

    @Override
    public void fillItemCategory(CreativeModeTab category, NonNullList<ItemStack> items) {
        if (category == CreativeModeTab.TAB_TOOLS) items.add(new ItemStack(this));
        super.fillItemCategory(category, items);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            if (player != null && player.isShiftKeyDown()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof BlockEntityCable cable) {

//                    level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(iEnergyCore -> {
//                        ModNetworking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new PacketSyncEnergy(iEnergyCore.getNetworkTag(pos)));
//                    });

                    EnergyNetwork network = cable.getNetwork();
                    if (network != null) {
                        int generators = 0;
                        int machines = 0;
                        int batteries = 0;
                        for (BlockPos elPos : network.getElectrics()) {
                            BlockEntity entity = level.getBlockEntity(elPos);
                            IEnergy cap = CapabilityUtil.getCapabilityHelper(entity, ModCapabilities.ENERGY, null).getValue();
                            if (cap != null) {
                                generators += cap.energyType() == EnergyType.EXTRACT ? 1 : 0;
                                machines += cap.energyType() == EnergyType.RECEIVE ? 1 : 0;
                                batteries += cap.energyType() == EnergyType.BOTH ? 1 : 0;
                            }
                        }

                        ChatFormatting color = network.getEnergyTier().getColor();
                        player.displayClientMessage(new TextComponent("====================================").withStyle(ChatFormatting.GRAY), false);
                        player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".network_info"), false);
                        player.displayClientMessage(new TextComponent(""), false);
                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".power_tier", new TranslatableComponent(network.getEnergyTier().getLang().getTranslationKey()).withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".transfer", new TranslatableComponent(TextComponentUtil.getFormattedLong(network.getEnergyTier().getBasicTransfer()) + " IE/t").withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".connected_generators", new TextComponent(String.valueOf(generators)).withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".connected_machines", new TextComponent(String.valueOf(machines)).withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".connected_batteries", new TextComponent(String.valueOf(batteries)).withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".stored_capacity", new TextComponent(TextComponentUtil.getFormattedLong(network.energyStored()) + " IE").withStyle(color), new TextComponent(TextComponentUtil.getFormattedLong(network.maxEnergy()) + " IE").withStyle(color)), false);
                        player.displayClientMessage(new TextComponent("====================================").withStyle(ChatFormatting.GRAY), false);

                        return InteractionResult.SUCCESS;
                    }
                }

                if (blockEntity instanceof IndRebBlockEntity indRebBlockEntity) {
                    if (indRebBlockEntity.hasEnergy()) {
                        BasicEnergyStorage energyStorage = indRebBlockEntity.getEnergyStorage();
                        ChatFormatting color = energyStorage.energyTier().getColor();

                        player.displayClientMessage(new TextComponent("====================================").withStyle(ChatFormatting.GRAY), false);
                        player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".machine_info"), false);
                        player.displayClientMessage(new TextComponent(""), false);

                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".power_tier", new TranslatableComponent(energyStorage.energyTier().getLang().getTranslationKey()).withStyle(color)), false);
                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".transfer", new TranslatableComponent(TextComponentUtil.getFormattedLong(energyStorage.energyTier().getBasicTransfer()) + " IE/t").withStyle(color)), false);

                        if (energyStorage.energyType() == EnergyType.EXTRACT) {
                            player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".last_generated", new TranslatableComponent(TextComponentUtil.getFormattedLong(energyStorage.lastGenerated()) + " IE/t").withStyle(color)), false);
                            player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".total_generated", new TranslatableComponent(TextComponentUtil.getFormattedLong(energyStorage.totalGenerated()) + " IE").withStyle(color)), false);
                        }

                        if (energyStorage.energyType() == EnergyType.RECEIVE) {
                            player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".last_consumed", new TranslatableComponent(TextComponentUtil.getFormattedLong(energyStorage.lastConsumed()) + " IE/t").withStyle(color)), false);
                            player.displayClientMessage(new TranslatableComponent("ie." + IndReb.MODID + ".total_consumed", new TranslatableComponent(TextComponentUtil.getFormattedLong(energyStorage.totalConsumed()) + " IE").withStyle(color)), false);
                        }

                        player.displayClientMessage(new TranslatableComponent("tooltip." + IndReb.MODID + ".stored_capacity", new TextComponent(TextComponentUtil.getFormattedLong(energyStorage.energyStored()) + " IE").withStyle(color), new TextComponent(TextComponentUtil.getFormattedLong(energyStorage.maxEnergy()) + " IE").withStyle(color)), false);
                        player.displayClientMessage(new TextComponent("====================================").withStyle(ChatFormatting.GRAY), false);

                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return super.useOn(context);
    }
}
