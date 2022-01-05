package com.maciej916.indreb.common.block.impl.machines.crusher;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.entity.block.BlockEntityStandardMachine;
import com.maciej916.indreb.common.receipe.impl.CrushingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class BlockEntityCrusher extends BlockEntityStandardMachine {

    public BlockEntityCrusher(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CRUSHER, pWorldPosition, pBlockState, ServerConfig.crusher_energy_capacity.get());
    }

    @Override
    protected Optional<CrushingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.CRUSHING, new SimpleContainer(input), level);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.CRUSHER;
    }

}
