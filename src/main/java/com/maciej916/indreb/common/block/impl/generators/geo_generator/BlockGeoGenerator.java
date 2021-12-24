package com.maciej916.indreb.common.block.impl.generators.geo_generator;

import com.maciej916.indreb.common.block.BlockElectricMachine;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.BlockEntitySemifluidGenerator;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockEntityExtruder;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.fluids.Biogas;
import com.maciej916.indreb.common.interfaces.block.IHasContainer;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
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
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nullable;
import java.util.List;

public class BlockGeoGenerator extends BlockElectricMachine implements IStateFacing, IHasContainer, IStateActive {

    public BlockGeoGenerator() {
        super(EnergyTier.BASIC,12, 0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityGeoGenerator(pos, state);
    }

    public ContainerGeoGenerator getContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ContainerGeoGenerator(windowId, level, pos, playerInventory, playerEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.GENERATE.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.geo_generator_tick_generate.get())).withStyle(getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                new TranslatableComponent(EnumLang.OUTPUT.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.basic_tier_transfer.get())).withStyle(getEnergyTier().getColor()),
                new TextComponent(" "),
                new TranslatableComponent(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                new TranslatableComponent(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(ServerConfig.geo_generator_energy_capacity.get())).withStyle(getEnergyTier().getColor())
        ));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            if (!player.isShiftKeyDown()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof BlockEntityGeoGenerator be) {
                    ItemStack stack = player.getItemInHand(hand);
                    if (!stack.isEmpty()) {
                        IFluidHandlerItem cap = CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
                        if (cap != null) {
                            FluidStack fluid = cap.getFluidInTank(1);
                            if (fluid.getFluid() == Fluids.LAVA) {
                                if (be.fluidStorage.fillFluid(fluid, true) == fluid.getAmount()) {
                                    be.fluidStorage.fillFluid(fluid, false);
                                    if (stack.getItem() instanceof BucketItem) {
                                        player.addItem(new ItemStack(Items.BUCKET));
                                    } else {
                                        player.addItem(new ItemStack(stack.getItem()));
                                    }
                                    stack.shrink(1);
                                    return InteractionResult.PASS;
                                }
                            }
                        }
                    }
                }
            }
        }

        return super.use(state, level, pos, player, hand, trace);
    }
}
