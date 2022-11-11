package com.maciej916.indreb.common.item.block;

import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItemGroups;
import com.maciej916.indreb.common.util.BlockPosUtil;
import com.maciej916.indreb.common.util.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class BlockItemReactorChamber extends IndRebBlockItem {
    public BlockItemReactorChamber(Block pBlock) {
        super(pBlock, new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP), Rarity.UNCOMMON);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext blockPlaceContext, BlockState blockState) {
        BlockPos clickedPos = blockPlaceContext.getClickedPos();
        ArrayList<BlockPos> found = BlockPosUtil.findAllMatch(clickedPos, 1, 1, (blockPos) -> blockPlaceContext.getLevel().getBlockState(blockPos).is(ModBlocks.NUCLEAR_REACTOR.get()));
        if (found.size() == 1) {
            for (Direction direction : Constants.DIRECTIONS) {
                BlockPos relativePos = clickedPos.relative(direction);
                if (blockPlaceContext.getLevel().getBlockState(relativePos).is(ModBlocks.NUCLEAR_REACTOR.get()) || blockPlaceContext.getLevel().getBlockState(relativePos).is(ModBlocks.REACTOR_CHAMBER.get())) {
                    return super.placeBlock(blockPlaceContext, blockState);
                }
            }
        }

        if (blockPlaceContext.getPlayer() != null) {
            if (blockPlaceContext.getLevel().isClientSide()) {
                if (found.size() == 0) {
                    blockPlaceContext.getPlayer().sendSystemMessage(EnumLang.CHAMBER_PLACE_NO_REACTOR.getTranslationComponent().withStyle(ChatFormatting.RED));
                } else if (found.size() == 1) {
                    blockPlaceContext.getPlayer().sendSystemMessage(EnumLang.CHAMBER_PLACE_INVALID.getTranslationComponent().withStyle(ChatFormatting.RED));
                } else {
                    blockPlaceContext.getPlayer().sendSystemMessage(EnumLang.CHAMBER_PLACE_TOO_CLOSE.getTranslationComponent().withStyle(ChatFormatting.RED));
                }
            }
        }

        return false;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.UNCOMMON;
    }
}
