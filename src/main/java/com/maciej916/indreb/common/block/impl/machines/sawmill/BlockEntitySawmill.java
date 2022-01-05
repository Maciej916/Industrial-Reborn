package com.maciej916.indreb.common.block.impl.machines.sawmill;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.entity.block.BlockEntityStandardMachine;
import com.maciej916.indreb.common.receipe.impl.SawingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class BlockEntitySawmill extends BlockEntityStandardMachine {

    public BlockEntitySawmill(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SAWMILL, pWorldPosition, pBlockState, ServerConfig.sawmill_energy_capacity.get());
    }

    @Override
    protected Optional<SawingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.SAWING, new SimpleContainer(input), level);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.SAWMILL;
    }

}
