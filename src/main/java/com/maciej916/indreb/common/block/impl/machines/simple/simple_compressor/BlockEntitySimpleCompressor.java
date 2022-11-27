package com.maciej916.indreb.common.block.impl.machines.simple.simple_compressor;

import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.blockentity.SimpleMachineBlockEntity;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.CompressingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockEntitySimpleCompressor extends SimpleMachineBlockEntity {

    public BlockEntitySimpleCompressor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_COMPRESSOR.get(), pos, blockState);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSimpleCompressor(this, containerId, playerInventory, player, data);
    }


    @Override
    protected java.util.Optional<CompressingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.COMPRESSING.get(), new SimpleContainer(input), level);
    }

}
