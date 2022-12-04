package com.maciej916.indreb.common.block.impl.machine.simple.simple_extractor;

import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.blockentity.SimpleMachineBlockEntity;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.ExtractingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockEntitySimpleExtractor extends SimpleMachineBlockEntity {

    public BlockEntitySimpleExtractor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_EXTRACTOR.get(), pos, blockState);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSimpleExtractor(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    protected java.util.Optional<ExtractingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.EXTRACTING.get(), new SimpleContainer(input), level);
    }

}
