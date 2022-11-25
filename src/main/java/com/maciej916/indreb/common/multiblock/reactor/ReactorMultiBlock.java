package com.maciej916.indreb.common.multiblock.reactor;

import com.maciej916.indreb.common.api.multiblock.IMultiBlockType;
import com.maciej916.indreb.common.api.multiblock.MultiBlockTools;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.tag.ModBlockTags;
import com.maciej916.indreb.common.util.BlockStateHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ReactorMultiBlock implements IMultiBlockType {

    public static ReactorMultiBlock INSTANCE = new ReactorMultiBlock();

    public static void formMultiBlock(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide()) {
            ReactorPartIndex formed = state.getValue(BlockStateHelper.REACTOR_PART);
            if (formed == ReactorPartIndex.UNFORMED) {
                MultiBlockTools.formMultiblock(ReactorMultiBlock.INSTANCE, level, pos);
            }
        }
    }

    public static boolean toggleMultiBlock(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            ReactorPartIndex formed = state.getValue(BlockStateHelper.REACTOR_PART);
            if (formed == ReactorPartIndex.UNFORMED) {
                if (MultiBlockTools.formMultiblock(ReactorMultiBlock.INSTANCE, level, pos)) {
                    player.displayClientMessage(EnumLang.REACTOR_FORMED.getTranslationComponent().withStyle(ChatFormatting.GREEN) ,true);
                } else {
                    player.displayClientMessage(EnumLang.REACTOR_NOT_VALID.getTranslationComponent().withStyle(ChatFormatting.RED) ,true);
                }
                return true;
            } else {
//                if (MultiBlockTools.breakMultiblock(ReactorMultiBlock.INSTANCE, level, pos, state, state)) {
//                    player.displayClientMessage(EnumLang.REACTOR_BROKEN.getTranslationComponent().withStyle(ChatFormatting.RED) ,true);
//                } else {
//                    player.displayClientMessage(EnumLang.REACTOR_FAILED.getTranslationComponent().withStyle(ChatFormatting.RED) ,true);
//                }
            }
        }
        return false;
    }

    @Override
    public Block getControllerBlock() {
        return ModBlocks.NUCLEAR_REACTOR.get();
    }

    @Override
    public @Nullable BlockPos getBottomLowerLeft(Level level, BlockPos pos, BlockState state) {
        if (state.is(ModBlockTags.REACTOR_PART)) {
            ReactorPartIndex index = state.getValue(BlockStateHelper.REACTOR_PART);
            return pos.offset(-index.getDx(), -index.getDy(), -index.getDz());
        } else {
            return null;
        }
    }

    @Override
    public void unformBlock(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof IReactorPart reactorPart) {
            level.setBlockAndUpdate(pos, reactorPart.setState(state, ReactorPartIndex.UNFORMED));
        }
    }

    @Override
    public void formBlock(Level level, BlockPos pos, int dx, int dy, int dz) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof IReactorPart reactorPart) {
            level.setBlockAndUpdate(pos, reactorPart.setState(state, ReactorPartIndex.getIndex(dx, dy, dz)));
        }
    }

    @Override
    public boolean isValidUnformedMultiBlock(Level level, BlockPos pos) {
        int cntSuper = 0;
        for (int dx = 0 ; dx < getWidth() ; dx++) {
            for (int dy = 0 ; dy < getHeight() ; dy++) {
                for (int dz = 0 ; dz < getDepth() ; dz++) {
                    BlockPos p = pos.offset(dx, dy, dz);

                    if (!ReactorPartIndex.getIndex(dx, dy, dz).testPlacement(level, p, false)) {
                        return false;
                    }

                    if (level.getBlockState(p).getBlock() == getControllerBlock()) {
                        cntSuper++;
                    }
                }
            }
        }
        return cntSuper == 1;
    }

    @Override
    public boolean isValidFormedMultiBlock(Level level, BlockPos pos, @Nullable BlockPos skipPos) {
        for (int dx = 0; dx < getWidth(); dx++) {
            for (int dy = 0; dy < getHeight(); dy++) {
                for (int dz = 0; dz < getDepth(); dz++) {
                    BlockPos p = pos.offset(dx, dy, dz);
                    if (!ReactorPartIndex.getIndex(dx, dy, dz).testPlacement(level, p, true) && !p.equals(skipPos)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    public int getDepth() {
        return 3;
    }

}
