package com.maciej916.indreb.common.block.impl.generators.solar_panels;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasContainer;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.tier.SolarGeneratorTier;
import com.maciej916.indreb.common.util.TextComponentUtil;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSolarGenerator extends BlockElectricMachine implements IHasContainer, IStateActive {

    private SolarGeneratorTier tier;
    protected final VoxelShape SHAPE;

    public BlockSolarGenerator(SolarGeneratorTier tier) {
        super(0, 0);
        SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, tier.getHeight(), 16.0D);
        this.tier = tier;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntitySolarGenerator(pos, state);
    }

    public ContainerSolarGenerator getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ContainerSolarGenerator(windowId, level, pos, playerInventory, playerEntity);
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
        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(tier.getEnergyTier().getLang().getTranslationKey()).withStyle(tier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(tier.getDayGenerate())).withStyle(tier.getEnergyTier().getColor())
        ));
    }

    public SolarGeneratorTier getSolarGeneratorTier() {
        return tier;
    }
}
