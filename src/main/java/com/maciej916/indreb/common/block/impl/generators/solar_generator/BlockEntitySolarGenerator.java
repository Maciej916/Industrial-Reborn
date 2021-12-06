package com.maciej916.indreb.common.block.impl.generators.solar_generator;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.Config;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;

import static com.maciej916.indreb.common.enums.EnumEnergyType.EXTRACT;

public class BlockEntitySolarGenerator extends IndRebBlockEntity implements IEnergyBlock, ITileSound {

    private boolean active = false;
    public boolean changedState = false;

    public BlockEntitySolarGenerator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SOLAR_GENERATOR, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.solar_generator_energy_capacity.get(), 0, ServerConfig.basic_tier_transfer.get(), EXTRACT);
    }

    @Override
    public boolean getActive() {
        return super.getActive();
    }

    @Override
    public void tickServer(BlockState state) {
        changedState = false;

        if (level.canSeeSky(getBlockPos()) && level.isDay()) {
            int generate = level.isThundering() ? ServerConfig.solar_generator_tick_generate.get() / 3 : level.isRaining() ? ServerConfig.solar_generator_tick_generate.get() / 2 : ServerConfig.solar_generator_tick_generate.get();
            if (getEnergyStorage().generateEnergy(generate, true) == generate) {
                getEnergyStorage().generateEnergy(generate, false);
            }

            active = true;
        } else {
            active = false;
        }

        super.tickServer(state);

        if (this.setActive(active)) {
            changedState = true;
            super.updateBlockState();
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        return super.save(tag);
    }

    @Override
    public boolean showInGui() {
        return false;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.SOLAR_GENERATOR;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        if (side == Direction.DOWN) return true;
        return super.canExtractEnergyDir(side);
    }
}
