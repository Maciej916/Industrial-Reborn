package com.maciej916.indreb.common.block.impl.machines.compressor;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.entity.block.BlockEntityStandardMachine;
import com.maciej916.indreb.common.receipe.impl.CompressingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class BlockEntityCompressor extends BlockEntityStandardMachine {

    public BlockEntityCompressor(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.COMPRESSOR, pWorldPosition, pBlockState, ServerConfig.compressor_energy_capacity.get());
    }

    @Override
    protected Optional<CompressingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.COMPRESSING, new SimpleContainer(input), level);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.COMPRESSOR;
    }

}
