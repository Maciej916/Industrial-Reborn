package com.maciej916.indreb.common.util.wrench;

import com.maciej916.indreb.common.energy.provider.EnergyNetwork;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateAxis;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.interfaces.wrench.IWrenchAction;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

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

            BlockState newState = blockState.setValue(BlockStateHelper.facingProperty, clickedFace);
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

    public static IWrenchAction energyNetworkInfo() {
        return (world, pos, blockState, player, clickedFace) -> {
            if (world.isClientSide() || player.isCrouching()) return false;
            EnergyNetwork net = CapabilityUtil.getCapabilityHelper(world, ModCapabilities.ENERGY_CORE).getIfPresent(e -> e.getNetworks().getNetwork(pos));
            if (net != null) {
                player.displayClientMessage(new TextComponent(net.toString()), false);
            }
            return false;
        };
    }

    public static boolean onWrenchUse(BlockState state, Level world, BlockPos pos, Player player, Direction clickedFace) {
        if (!state.isAir()) {
            if (WrenchHelper.getWrenchActions().containsKey(state.getBlock())) {
                List<IWrenchAction> actions = WrenchHelper.getWrenchActions().get(state.getBlock()).getActions();
                boolean success = false;
                for (final IWrenchAction action : actions) {
                    success = action.perform(world, pos, state, player, clickedFace);
                    if (success) break;
                }

                if (success) {
                    ItemStack item = player.getItemInHand(player.getUsedItemHand());
                    if (item.getDamageValue() + 1 != item.getMaxDamage()) {
                        world.playSound(null, pos, ModSounds.WRENCH, SoundSource.NEUTRAL, 1F, 0.9F / (new Random().nextFloat() * 0.4F + 0.8F));
                    }
                    item.hurtAndBreak(1, player, (i) -> world.playSound(null, pos, SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F)));
                }

                return success;
            }
        }

        return false;
    }

    public static void dismantleBlock(BlockState state, Level world, BlockPos pos) {
        dismantleBlock(state, world, pos, world.getBlockEntity(pos));
    }

    public static void dismantleBlock(BlockState state, Level world, BlockPos pos, BlockEntity be) {
        Block.dropResources(state, world, pos, be);
        world.removeBlock(pos, false);
    }

}
