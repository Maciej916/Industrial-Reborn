package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.item.base.ToolItem;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.util.RadiationHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeigerCounter extends ToolItem {

    public GeigerCounter() {
        super(1);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
                player.sendSystemMessage(
                        TextComponentUtil.build(
                                Component.translatable("tooltip." + IndReb.MODID + ".env_radiation"),
                                Component.literal(" ").withStyle(ChatFormatting.WHITE),
                                RadiationHelper.radsColoredPrefix(cap.getRadsLevel(), true),
                                Component.literal(". ").withStyle(ChatFormatting.WHITE),
                                Component.translatable("tooltip." + IndReb.MODID + ".player_radiation"),
                                Component.literal(" ").withStyle(ChatFormatting.WHITE),
                                RadiationHelper.radsColoredPrefix(cap.getPlayerRads(), false),
                                Component.literal(" [" + cap.getRadsPercentageString() + "%]").withStyle(RadiationHelper.getRadiationTextColor(cap.getPlayerRads()))
                        )
                );
            });
        }

        return super.use(level, player, interactionHand);
    }
}
