package com.maciej916.indreb.common.block.impl.reactor.nuclear_reactor;

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
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BlockNuclearReactor extends BlockElectricMachine implements IStateFacing, IHasMenu, IStateActive {

    public BlockNuclearReactor() {
        super(EnergyTier.SUPER,0, 0);
    }

    @Override
    public AbstractContainerMenu getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuNuclearReactor(windowId, level, pos, playerInventory, playerEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityNuclearReactor(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

//        pTooltip.add(TextComponentUtil.build(
//                Component.translatable(EnumLang.ACCEPT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
//                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.advanced_tier_transfer.get())).withStyle(EnergyTier.ADVANCED.getColor())
//        ));
//
//        pTooltip.add(TextComponentUtil.build(
//                Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
//                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.matter_fabricator_energy_capacity.get())).withStyle(EnergyTier.ADVANCED.getColor())
//        ));
    }
}