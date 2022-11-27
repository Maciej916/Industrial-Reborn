package com.maciej916.indreb.common.block.impl.machines.basic.extractor;

import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.blockentity.StandardMachineBlockEntity;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.ExtractingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockEntityExtractor extends StandardMachineBlockEntity implements IHasSound {

    public BlockEntityExtractor(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.EXTRACTOR.get(), pos, blockState, ServerConfig.extractor_energy_capacity.get());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuExtractor(this, containerId, playerInventory, player, data);
    }

    @Override
    protected java.util.Optional<ExtractingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.EXTRACTING.get(), new SimpleContainer(input), level);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.EXTRACTOR.get();
    }
}
