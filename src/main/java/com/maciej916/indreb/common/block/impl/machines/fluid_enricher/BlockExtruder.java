package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class BlockExtruder extends BlockElectricMachine implements IStateFacing, IHasContainer, IStateActive {


    public BlockExtruder() {
        super(12, 0);
    }

    @Override
    public AbstractContainerMenu getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ContainerExtruder(windowId, level, pos, playerInventory, playerEntity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityExtruder(pos, state);
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            if (!player.isShiftKeyDown()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof BlockEntityExtruder be) {
                    ItemStack heldStack = player.getItemInHand(hand);
                    if (heldStack.getItem() instanceof BucketItem bi) {
                        Fluid bucketFluid = bi.getFluid();
                        FluidStack fluidStack = new FluidStack(bucketFluid, 1000);

                        Fluid firstFluid = be.waterStorage.getFluid().getFluid();
                        Fluid secondFluid = be.lavaStorage.getFluid().getFluid();

                        if (be.waterStorage.fillFluid(fluidStack, true) == 1000 && bucketFluid == Fluids.WATER && (firstFluid == Fluids.WATER || firstFluid == Fluids.EMPTY)) {
                            be.waterStorage.fillFluid(fluidStack, false);
                            if (!player.isCreative()) {
                                ItemStack stack = new ItemStack(Items.BUCKET);
                                player.setItemInHand(hand, stack);
                            }
                            be.updateBlockState();
                            return InteractionResult.PASS;
                        } else if (be.lavaStorage.fillFluid(fluidStack, true) == 1000 && bucketFluid == Fluids.LAVA && (secondFluid == Fluids.LAVA || secondFluid == Fluids.EMPTY)) {
                            be.lavaStorage.fillFluid(fluidStack, false);
                            if (!player.isCreative()) {
                                ItemStack stack = new ItemStack(Items.BUCKET);
                                player.setItemInHand(hand, stack);
                            }
                            be.updateBlockState();
                            return InteractionResult.PASS;
                        }
                    }

                }
            }
        }

        return super.use(state, level, pos, player, hand, trace);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.TIER_BASIC.getTranslationKey()).withStyle(EnergyTier.BASIC.getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.ACCEPT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.basic_tier_transfer.get())).withStyle(EnergyTier.BASIC.getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.extruder_energy_capacity.get())).withStyle(EnergyTier.BASIC.getColor())
        ));
    }
}
