package com.maciej916.indreb.common.block.impl.transformer;

import com.maciej916.indreb.common.api.block.IndRebEntityBlock;
import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.api.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.api.tier.TransformerTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockTransformer extends IndRebEntityBlock implements IStateFacing, IElectricMachine {

    private final TransformerTier transformerTier;

    public BlockTransformer(TransformerTier transformerTier, Properties properties) {
        super(properties);
        this.transformerTier = transformerTier;
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationHitAction()).add(WrenchHelper.dropAction());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityTransformer(pos, state);
    }

    public TransformerTier getTransformerTier() {
        return transformerTier;
    }

    @Nonnull
    @Override
    public DirectionProperty getFacingProperty() {
        return BlockStateHelper.FACING_PROPERTY;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @javax.annotation.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(Component.translatable(EnumLang.LOW.getTranslationKey(), Component.translatable(transformerTier.getMinTier().getLang().getTranslationKey()).withStyle(transformerTier.getMinTier().getColor())).withStyle(ChatFormatting.GRAY)));
        pTooltip.add(TextComponentUtil.build(Component.translatable(EnumLang.HIGH.getTranslationKey(), Component.translatable(transformerTier.getMaxTier().getLang().getTranslationKey()).withStyle(transformerTier.getMaxTier().getColor())).withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public EnergyTier getEnergyTier() {
        return transformerTier.getMaxTier();
    }

}
