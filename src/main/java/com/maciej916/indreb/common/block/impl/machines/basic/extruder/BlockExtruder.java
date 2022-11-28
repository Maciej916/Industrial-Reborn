package com.maciej916.indreb.common.block.impl.machines.basic.extruder;

import com.maciej916.indreb.common.api.block.BaseElectricMachineBlock;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.BlockEntityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockExtruder extends BaseElectricMachineBlock {

    public BlockExtruder() {
        super(EnergyTier.BASIC,0, 0);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityExtruder(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @javax.annotation.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.ACCEPT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(ServerConfig.basic_tier_transfer.get(), Screen.hasShiftDown())).withStyle(getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedStorageUnit(ServerConfig.electric_furnace_energy_capacity.get(), Screen.hasShiftDown())).withStyle(getEnergyTier().getColor())
        ));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            if (!player.isShiftKeyDown()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof BlockEntityExtruder entityExtruder) {
                    if (BlockEntityUtil.useFillTank(player, hand, entityExtruder.firstTank)) {
                        return InteractionResult.PASS;
                    }

                    if (BlockEntityUtil.useFillTank(player, hand, entityExtruder.secondTank)) {
                        return InteractionResult.PASS;
                    }
                }
            }
        }
        return super.use(state, level, pos, player, hand, trace);
    }
}
