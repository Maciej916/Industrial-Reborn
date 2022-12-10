package com.maciej916.indreb.common.block.impl.generators.crystalline_generator;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCrystallineGenerator extends BlockElectricMachine implements IStateFacing, IHasMenu, IStateActive {

    public BlockCrystallineGenerator() {
        super(EnergyTier.STANDARD, 12, 0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityCrystallineGenerator(pos, state);
    }

    @Override
    public MenuCrystallineGenerator getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuCrystallineGenerator(windowId, level, pos, playerInventory, playerEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
            Component.translatable(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.crystalline_generator_tick_generate.get())).withStyle(getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
            Component.translatable(EnumLang.OUTPUT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.standard_tier_transfer.get())).withStyle(getEnergyTier().getColor()),
            Component.literal(" "),
            Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.crystalline_generator_energy_capacity.get())).withStyle(getEnergyTier().getColor())
        ));

        pTooltip.add(Component.translatable(EnumLang.WIP.getTranslationKey()).withStyle(ChatFormatting.RED));

    }
}