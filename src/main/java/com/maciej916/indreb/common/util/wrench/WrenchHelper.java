package com.maciej916.indreb.common.util.wrench;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.energy.provider.EnergyNetwork;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.interfaces.wrench.IWrenchAction;
import com.maciej916.indreb.common.item.base.ElectricItem;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WrenchHelper {

    protected static Map<Block, WrenchAction> wrenchActions = new HashMap<>();

    public static WrenchAction registerAction(Block block) {
        WrenchAction wrenchAction = new WrenchAction();
        wrenchActions.put(block, wrenchAction);
        return wrenchAction;
    }

    public static Map<Block, WrenchAction> getWrenchActions() {
        return wrenchActions;
    }

    public static boolean hasAction(Block block) {
        return wrenchActions.containsKey(block);
    }

    public static IWrenchAction rotationAction() {
        return (world, pos, blockState, player, clickedFace) -> {
            if (world.isClientSide() || player.isCrouching()) return false;
            BlockState newState = BlockStateHelper.rotate(blockState, world, pos, Rotation.CLOCKWISE_90);
            world.setBlockAndUpdate(pos, newState);
            return true;
        };
    }

    public static IWrenchAction rotationHitAction() {
        return (world, pos, blockState, player, clickedFace) -> {
            if (world.isClientSide() || player.isCrouching()) return false;

            Direction currentFace = blockState.getValue(BlockStateHelper.facingProperty);
            if (currentFace == clickedFace) {
                return false;
            }

            BlockState newState;
            if (player.isShiftKeyDown()) {
                newState = blockState.setValue(BlockStateHelper.facingProperty, clickedFace.getOpposite());
            } else {
                newState = blockState.setValue(BlockStateHelper.facingProperty, clickedFace);
            }

            world.setBlockAndUpdate(pos, newState);

            return true;
        };
    }

    public static IWrenchAction dropAction() {
        return (world, pos, blockState, player, clickedFace) -> {
            if (world.isClientSide() || !player.isCrouching()) return false;
            dismantleBlock(blockState, world, pos);
            return true;
        };
    }

    public static boolean onWrenchUse(BlockState state, Level world, BlockPos pos, Player player, Direction clickedFace) {
        if (!state.isAir() && WrenchHelper.getWrenchActions().containsKey(state.getBlock())) {
            List<IWrenchAction> actions = WrenchHelper.getWrenchActions().get(state.getBlock()).getActions();
            boolean success = false;

            ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
            if (itemStack.getItem() instanceof IElectricItem electricItem) {
                IEnergy energy = electricItem.getEnergy(itemStack);
                if (energy.energyStored() == 0) {
                    return false;
                }
            }

            for (final IWrenchAction action : actions) {
                success = action.perform(world, pos, state, player, clickedFace);
                if (success) break;
            }

            if (success) {
                if (itemStack.getItem() instanceof IElectricItem electricItem) {
                    IEnergy energy = electricItem.getEnergy(itemStack);
                    energy.consumeEnergy(50, false);
                    world.playSound(null, pos, ModSounds.ELECTRIC_WRENCH, SoundSource.NEUTRAL, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
                } else {
                    if (itemStack.getDamageValue() + 1 != itemStack.getMaxDamage()) {
                        world.playSound(null, pos, ModSounds.WRENCH, SoundSource.NEUTRAL, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
                    }
                    itemStack.hurtAndBreak(1, player, (i) -> world.playSound(null, pos, SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F)));
                }
            }

            return success;
        }

        return false;
    }

    public static void dismantleBlock(BlockState state, Level world, BlockPos pos) {
        dismantleBlock(state, world, pos, world.getBlockEntity(pos));
    }

    public static void dismantleBlock(BlockState state, Level world, BlockPos pos, BlockEntity be) {
        if (world instanceof ServerLevel serverLevel) {


            ItemStack drop = new ItemStack(state.getBlock());
            CompoundTag tag = drop.getOrCreateTag();
            if (be instanceof IndRebBlockEntity irb) {
                if (irb.hasEnergy()) {
                    CompoundTag newTag = new CompoundTag();
                    newTag.putString("id", drop.getItem().getRegistryName().toString());
                    newTag.putInt("energy", irb.getEnergyStorage().energyStored());
                    tag.put("BlockEntityTag", newTag);
                }
            }
            Block.popResource(serverLevel, pos, drop);


//            Block.getDrops(state, serverLevel, pos, be).forEach((itemStack) -> {
//                CompoundTag tag = itemStack.getOrCreateTag();
//                if (be instanceof IndRebBlockEntity irb) {
//                    if (irb.hasEnergy()) {
//                        CompoundTag newTag = new CompoundTag();
//                        newTag.putString("id", itemStack.getItem().getRegistryName().toString());
//                        newTag.putInt("energy", irb.getEnergyStorage().energyStored());
//                        tag.put("BlockEntityTag", newTag);
//                    }
//                }
//                Block.popResource(serverLevel, pos, itemStack);
//            });
        }
        world.removeBlock(pos, false);
    }

}
