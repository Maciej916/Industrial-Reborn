package com.maciej916.indreb.common.block.impl.generators.solar_panels;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.tier.SolarGeneratorTier;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSolarGenerator extends BlockElectricMachine implements IHasMenu, IStateActive {

    private final SolarGeneratorTier solarTier;
    protected final VoxelShape SHAPE;

    public BlockSolarGenerator(SolarGeneratorTier tier) {
        super(tier.getEnergyTier(),0, 0);
        this.SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, tier.getHeight(), 16.0D);
        this.solarTier = tier;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySolarGenerator(pos, state);
    }

    @Override
    public MenuSolarGenerator getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuSolarGenerator(windowId, level, pos, playerInventory, playerEntity);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(solarTier.getDayGenerate())).withStyle(getEnergyTier().getColor())
        ));
    }

    public SolarGeneratorTier getSolarGeneratorTier() {
        return solarTier;
    }
}
