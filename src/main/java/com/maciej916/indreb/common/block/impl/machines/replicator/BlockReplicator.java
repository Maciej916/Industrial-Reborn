package com.maciej916.indreb.common.block.impl.machines.replicator;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.Constants;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BlockReplicator extends BlockElectricMachine implements IStateFacing, IHasMenu, IStateActive {

    public BlockReplicator() {
        super(EnergyTier.SUPER,0, 0);
    }

    @Override
    public AbstractContainerMenu getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuReplicator(windowId, level, pos, playerInventory, playerEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityReplicator(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.ACCEPT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.super_tier_transfer.get())).withStyle(EnergyTier.SUPER.getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.replicator_energy_capacity.get())).withStyle(EnergyTier.SUPER.getColor())
        ));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide()) return;

        BlockEntityReplicator blockEntityReplicator = (BlockEntityReplicator) level.getBlockEntity(pos);
        if (level.getBlockEntity(fromPos) instanceof BlockEntityPatternStorage blockEntityPatternStorage) {
            blockEntityReplicator.addPatternStorage(fromPos, blockEntityPatternStorage);
        } else {
            blockEntityReplicator.removePatternStorage(fromPos);
        }

        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (level.isClientSide()) return;

        BlockEntityReplicator blockEntityReplicator = (BlockEntityReplicator) level.getBlockEntity(pos);
        for (Direction direction : Constants.DIRECTIONS) {
            BlockPos offsetPos = pos.relative(direction);
            BlockEntity dirTile = level.getBlockEntity(offsetPos);
            if (dirTile instanceof BlockEntityPatternStorage bePatternStorage) {
                blockEntityReplicator.addPatternStorage(offsetPos, bePatternStorage);
            }
        }

        super.onPlace(state, level, pos, oldState, isMoving);
    }
}
