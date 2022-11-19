package com.maciej916.indreb.common.block.impl.generator.solar_panel;

import com.maciej916.indreb.common.api.block.BaseElectricMachineBlock;
import com.maciej916.indreb.common.api.tier.SolarPanelTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockSolarPanel extends BaseElectricMachineBlock {

    private final SolarPanelTier solarTier;
    protected final VoxelShape SHAPE;

    public BlockSolarPanel(SolarPanelTier tier) {
        super(tier.getEnergyTier(), 0, 0);
        this.SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, tier.getHeight(), 16.0D);
        this.solarTier = tier;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySolarPanel(pos, state);
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
    public void appendHoverText(ItemStack pStack, @javax.annotation.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(solarTier.getDayGenerate())).withStyle(getEnergyTier().getColor())
        ));
    }

    public SolarPanelTier getSolarTier() {
        return solarTier;
    }


}
