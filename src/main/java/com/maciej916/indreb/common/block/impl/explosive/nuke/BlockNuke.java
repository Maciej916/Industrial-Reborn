package com.maciej916.indreb.common.block.impl.explosive.nuke;

import com.maciej916.indreb.common.entity.PrimedNuke;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockNuke extends TntBlock implements EntityBlock {

    public BlockNuke() {
        super(Properties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS));
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        if (!pLevel.isClientSide) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof BlockEntityNuke nuke) {
                PrimedNuke primedNuke = new PrimedNuke(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pExplosion.getSourceMob(), nuke.getExplosionRadius());
                int i = primedNuke.getFuse();
                primedNuke.setFuse((short)(pLevel.random.nextInt(i / 4) + i / 8));
                pLevel.addFreshEntity(primedNuke);
            }
        }
    }

    private static void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity) {
        if (!pLevel.isClientSide) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof BlockEntityNuke nuke) {
                PrimedNuke primedNuke = new PrimedNuke(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pEntity, nuke.getExplosionRadius());
                pLevel.addFreshEntity(primedNuke);
                pLevel.playSound(null, primedNuke.getX(), primedNuke.getY(), primedNuke.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.gameEvent(pEntity, GameEvent.PRIME_FUSE, pPos);
            }
        }
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {

        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof BlockEntityNuke) {
            MenuProvider menuProvider = (MenuProvider) entity;
            if (!level.isClientSide()) {
                NetworkHooks.openScreen(((ServerPlayer) player), menuProvider, pos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.use(state, level, pos, player, hand, trace);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityNuke(pos, state);
    }

    //    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        ItemStack itemstack = pPlayer.getItemInHand(pHand);
//        if ((!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE) && !itemstack.isEmpty())) {
//            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
//        } else {
//            onCaughtFire(pState, pLevel, pPos, pHit.getDirection(), pPlayer);
//            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
//
//            if (!itemstack.isEmpty()) {
//                Item item = itemstack.getItem();
//                if (!pPlayer.isCreative()) {
//                    if (itemstack.is(Items.FLINT_AND_STEEL)) {
//                        itemstack.hurtAndBreak(1, pPlayer, (p_57425_) -> {
//                            p_57425_.broadcastBreakEvent(pHand);
//                        });
//                    } else {
//                        itemstack.shrink(1);
//                    }
//                }
//
//                pPlayer.awardStat(Stats.ITEM_USED.get(item));
//            }
//
//            return InteractionResult.sidedSuccess(pLevel.isClientSide);
//        }
//    }

}
