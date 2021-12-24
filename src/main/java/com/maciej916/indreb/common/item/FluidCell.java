package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.capabilities.FluidCellHandler;
import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.registries.ModItemGroups;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class FluidCell extends Item {
    private static final String TAG_FLUID = "Fluid";

    public FluidCell() {
        super(new Properties().tab(ModItemGroups.MAIN_ITEM_GROUP).stacksTo(16));
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == ModItemGroups.MAIN_ITEM_GROUP) {
            for (Fluid fluid : ForgeRegistries.FLUIDS) {
                if (fluid != Fluids.EMPTY && fluid.isSource(fluid.defaultFluidState())) {
                    pItems.add(setFluid(new ItemStack(this), fluid));
                }
            }
        }

        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {

        Fluid fluid = getFluid(stack);
        if (fluid != Fluids.EMPTY) {
            list.add(new TextComponent("< " + ServerConfig.fluid_cell_capacity.get() + " mB, ").append(new TranslatableComponent(fluid.getAttributes().getTranslationKey())).append(" >").withStyle(ChatFormatting.GRAY));
        } else {
            list.add(new TranslatableComponent("item.indreb.empty_fluid").withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(stack, level, list, tooltipFlag);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidCellHandler(stack);
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
        } else if (getFluid(stack) != Fluids.EMPTY) {
            return InteractionResultHolder.fail(stack);
        }

        BlockPos pos = hit.getBlockPos();
        Direction direction = hit.getDirection();
        BlockPos pos1 = pos.relative(direction);

        if (level.mayInteract(player, pos) && player.mayUseItemAt(pos1, direction, stack)) {
            BlockState state = level.getBlockState(pos);

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
                    ItemStack itemstack1 = ItemUtils.createFilledResult(stack, player, setFluid(new ItemStack(this), fluid));

                    if (!level.isClientSide()) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, new ItemStack(fluid.getBucket()));
                    }

                    return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
                }
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return getFluid(stack) != Fluids.EMPTY;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        Fluid fluid = getFluid(stack);
        if (fluid != Fluids.EMPTY) {
            return new ItemStack(this);
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack setFluid(ItemStack stack, Fluid fluid) {
        if (fluid == Fluids.EMPTY) {
            CompoundTag nbt = stack.getTag();

            if (nbt != null) {
                nbt.remove(TAG_FLUID);

                if (nbt.isEmpty()) {
                    stack.setTag(null);
                }
            }
        } else {
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putString(TAG_FLUID, Objects.requireNonNull(fluid.getRegistryName()).toString());
        }

        return stack;
    }

    public static Fluid getFluid(ItemStack stack) {
        CompoundTag nbt = stack.getTag();

        if (nbt != null) {
            ResourceLocation location = ResourceLocation.tryParse(nbt.getString(TAG_FLUID));
            if (location != null && ForgeRegistries.FLUIDS.containsKey(location)) {
                Fluid fluid = ForgeRegistries.FLUIDS.getValue(location);
                if (fluid != null) {
                    return fluid;
                }
            }
        }

        return Fluids.EMPTY;
    }
}
