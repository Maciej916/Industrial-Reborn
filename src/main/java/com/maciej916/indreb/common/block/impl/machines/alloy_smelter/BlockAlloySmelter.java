package com.maciej916.indreb.common.block.impl.machines.alloy_smelter;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasContainer;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BlockAlloySmelter extends BlockElectricMachine implements IStateFacing, IHasContainer, IStateActive {

    public BlockAlloySmelter() {
        super(12, 0);
    }

    @Override
    public AbstractContainerMenu getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ContainerAlloySmelter(windowId, level, pos, playerInventory, playerEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityAlloySmelter(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
            new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            new TranslatableComponent(EnumLang.TIER_STANDARD.getTranslationKey()).withStyle(EnergyTier.STANDARD.getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
            new TranslatableComponent(EnumLang.ACCEPT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.standard_tier_transfer.get())).withStyle(EnergyTier.STANDARD.getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
            new TranslatableComponent(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
            new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.alloy_smelter_energy_capacity.get())).withStyle(EnergyTier.STANDARD.getColor())
        ));
    }

}
