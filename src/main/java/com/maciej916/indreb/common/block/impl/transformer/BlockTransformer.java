package com.maciej916.indreb.common.block.impl.transformer;

import com.maciej916.indreb.common.block.IndRebEntityBlock;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IElectricMachine;
import com.maciej916.indreb.common.interfaces.block.IHasContainer;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.tier.TransformerTier;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockTransformer extends IndRebEntityBlock implements IStateFacing, IHasContainer, IElectricMachine {

    private final TransformerTier transformerTier;

    public BlockTransformer(TransformerTier transformerTier, Properties properties) {
        super(properties);
        this.transformerTier = transformerTier;
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationHitAction()).add(WrenchHelper.dropAction());
    }

    public TransformerTier getTransformerTier() {
        return transformerTier;
    }

    @Nonnull
    @Override
    public DirectionProperty getFacingProperty() {
        return BlockStateHelper.facingProperty;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityTransformer(pos, state);
    }

    public ContainerTransformer getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ContainerTransformer(windowId, level, pos, playerInventory, playerEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(new TranslatableComponent(EnumLang.LOW.getTranslationKey(), new TranslatableComponent(transformerTier.getMinTier().getLang().getTranslationKey()).withStyle(transformerTier.getMinTier().getColor())).withStyle(ChatFormatting.GRAY)));
        pTooltip.add(TextComponentUtil.build(new TranslatableComponent(EnumLang.HIGH.getTranslationKey(), new TranslatableComponent(transformerTier.getMaxTier().getLang().getTranslationKey()).withStyle(transformerTier.getMaxTier().getColor())).withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public EnergyTier getEnergyTier() {
        return transformerTier.getMaxTier();
    }
}
