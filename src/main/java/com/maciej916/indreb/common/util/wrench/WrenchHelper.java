package com.maciej916.indreb.common.util.wrench;

import com.maciej916.indreb.common.api.blockentity.interfaces.IIndRebBlockEntity;
import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
import com.maciej916.indreb.common.api.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.sound.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.registries.ForgeRegistries;

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

            Direction currentFace = blockState.getValue(BlockStateHelper.FACING_PROPERTY);
            if (currentFace == clickedFace) {
                return false;
            }

            BlockState newState;
            if (player.isShiftKeyDown()) {
                newState = blockState.setValue(BlockStateHelper.FACING_PROPERTY, clickedFace.getOpposite());
            } else {
                newState = blockState.setValue(BlockStateHelper.FACING_PROPERTY, clickedFace);
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
                IEnergyStorage energy = electricItem.getEnergyStorage(itemStack);
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
                    IEnergyStorage energy = electricItem.getEnergyStorage(itemStack);
                    energy.consumeEnergy(50, false);
                    world.playSound(null, pos, ModSounds.ELECTRIC_WRENCH.get(), SoundSource.NEUTRAL, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
                } else {
                    if (itemStack.getDamageValue() + 1 != itemStack.getMaxDamage()) {
                        world.playSound(null, pos, ModSounds.WRENCH.get(), SoundSource.NEUTRAL, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
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
            if (be instanceof IIndRebBlockEntity irb) {
                if (irb.hasEnergyStorage()) {
                    CompoundTag newTag = new CompoundTag();
                    newTag.putString("id", ForgeRegistries.ITEMS.getKey(drop.getItem()).toString());
                    newTag.putInt("energy", irb.getEnergyStorage().energyStored());
                    tag.put("BlockEntityTag", newTag);
                }
            }
            Block.popResource(serverLevel, pos, drop);
        }
        world.removeBlock(pos, false);
    }

}
