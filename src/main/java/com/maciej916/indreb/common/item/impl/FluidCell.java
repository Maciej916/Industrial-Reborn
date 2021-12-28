package com.maciej916.indreb.common.item.impl;

import com.maciej916.indreb.common.capabilities.FluidHandler;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.item.base.FluidItem;
import com.maciej916.indreb.common.registries.ModItemGroups;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class FluidCell extends FluidItem {

    public FluidCell() {
        super(new Properties().stacksTo(16));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == ModItemGroups.MAIN_ITEM_GROUP || pCategory == CreativeModeTab.TAB_SEARCH) {
            for (Fluid fluid : ForgeRegistries.FLUIDS) {
                if (fluid != Fluids.EMPTY && fluid.isSource(fluid.defaultFluidState())) {
                    ItemStack stack = new ItemStack(this);
                    FluidHandler cap = (FluidHandler) CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
                    if (cap != null) {
                        cap.setFluidStack(new FluidStack(fluid, Math.min(1000, ServerConfig.foam_sprayer_capacity.get())));
                        pItems.add(stack);
                    }
                }
            }
        }

        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(player, level, stack, hit);

        if (ret != null) {
            return ret;
        } else if (hit.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(stack);
        } else if (hit.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(stack);
        } else if (getFluidStack(stack).getFluid() != Fluids.EMPTY) {
            return InteractionResultHolder.fail(stack);
        }

        BlockPos pos = hit.getBlockPos();
        Direction direction = hit.getDirection();
        BlockPos pos1 = pos.relative(direction);

        if (level.mayInteract(player, pos) && player.mayUseItemAt(pos1, direction, stack)) {
            BlockState state = level.getBlockState(pos);

            FluidHandler cap = (FluidHandler) CapabilityUtil.getCapabilityHelper(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
            if (cap != null) {
                if (cap.getFluid().isEmpty()) {
                    if (state.getBlock() instanceof LiquidBlock liquidBlock) {
                        if (state.getValue(BlockStateProperties.LEVEL) == 0) {
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
                        }

                        Fluid fluid = liquidBlock.getFluid();
                        if (fluid != Fluids.EMPTY) {
                            player.awardStat(Stats.ITEM_USED.get(this));
                            SoundEvent soundevent = fluid.getAttributes().getFillSound();

                            if (soundevent == null) {
                                soundevent = fluid.is(FluidTags.LAVA) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL;
                            }

                            player.playSound(soundevent, 1F, 1F);

                            ItemStack newStack = new ItemStack(this);
                            FluidHandler newCap = (FluidHandler) CapabilityUtil.getCapabilityHelper(newStack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).getValue();
                            if (newCap != null) {
                                newCap.setFluidStack(new FluidStack(fluid, Math.min(1000, ServerConfig.foam_sprayer_capacity.get())));
                            }

                            if (!level.isClientSide()) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, new ItemStack(fluid.getBucket()));
                            }

                            return InteractionResultHolder.sidedSuccess(ItemUtils.createFilledResult(stack, player, newStack), level.isClientSide());
                        }
                    }
                }
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidHandler(stack, ServerConfig.fluid_cell_capacity.get());
    }
}
