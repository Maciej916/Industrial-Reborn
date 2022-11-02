package com.maciej916.indreb.common.block.impl;

import com.maciej916.indreb.common.entity.PrimedITnt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class BlockIndustrialTNT extends TntBlock {

    public BlockIndustrialTNT() {
        super(BlockBehaviour.Properties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS));
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        if (!pLevel.isClientSide) {
            PrimedITnt primedtnt = new PrimedITnt(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pExplosion.getSourceMob());
            int i = primedtnt.getFuse();
            primedtnt.setFuse((short)(pLevel.random.nextInt(i / 4) + i / 8));
            pLevel.addFreshEntity(primedtnt);
        }
    }

    private static void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity) {
        if (!pLevel.isClientSide) {
            PrimedITnt primedtnt = new PrimedITnt(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pEntity);
            pLevel.addFreshEntity(primedtnt);
            pLevel.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pEntity, GameEvent.PRIME_FUSE, pPos);
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if ((!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE) && !itemstack.isEmpty())) {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        } else {
            onCaughtFire(pState, pLevel, pPos, pHit.getDirection(), pPlayer);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);

            if (!itemstack.isEmpty()) {
                Item item = itemstack.getItem();
                if (!pPlayer.isCreative()) {
                    if (itemstack.is(Items.FLINT_AND_STEEL)) {
                        itemstack.hurtAndBreak(1, pPlayer, (p_57425_) -> {
                            p_57425_.broadcastBreakEvent(pHand);
                        });
                    } else {
                        itemstack.shrink(1);
                    }
                }

                pPlayer.awardStat(Stats.ITEM_USED.get(item));
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

}
