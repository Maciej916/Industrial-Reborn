package com.maciej916.indreb.common.block.impl.machines.simple.simple_crusher;

import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.blockentity.SimpleMachineBlockEntity;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.CrushingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BlockEntitySimpleCrusher extends SimpleMachineBlockEntity {

    public BlockEntitySimpleCrusher(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_CRUSHER.get(), pos, blockState);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSimpleCrusher(this, containerId, playerInventory, player, data);
    }


    @Override
    protected Optional<CrushingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.CRUSHING.get(), new SimpleContainer(input), level);
    }

}
