package com.maciej916.indreb.common.block.impl;

import com.maciej916.indreb.common.api.block.BlockFoam;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.tag.ModTagsItem;
import com.maciej916.indreb.common.util.Constants;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ReinforcedFoamBlock extends BlockFoam {

    public static final Vector3f PARTICLE_COLOR = new Vector3f(Vec3.fromRGB24(0x9b9b9b));

    public ReinforcedFoamBlock() {
        super(BlockBehaviour.Properties.of(Constants.REINFORCED_FOAM_BLOCK).strength(0.25F).sound(SoundType.POWDER_SNOW).dynamicShape(), PARTICLE_COLOR);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        int count = pRandom.nextInt(0, 3);
        if (count == 0) {
            formBlock(pLevel, pPos);
        }

        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    private void formBlock(Level level, BlockPos pos) {
        level.setBlock(pos, ModBlocks.REINFORCED_STONE.get().defaultBlockState(), 2);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (stack.is(ModTagsItem.FORGE_GRAVEL)) {
            if (!level.isClientSide()) {
                if (!player.isCreative()) {
                    stack.shrink(1);
                }

                formBlock(level, pos);
            }
            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

}
