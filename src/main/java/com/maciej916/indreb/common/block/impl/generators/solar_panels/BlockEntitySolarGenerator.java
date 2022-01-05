package com.maciej916.indreb.common.block.impl.generators.solar_panels;

import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModSounds;
import com.maciej916.indreb.common.tier.SolarGeneratorTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntitySolarGenerator extends IndRebBlockEntity implements IEnergyBlock, ITileSound {

    private final SolarGeneratorTier tier;
    private boolean active = false;
    private int lastAmount = 0;
    public int amount = 0;

    public BlockEntitySolarGenerator(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SOLAR_GENERATOR, pWorldPosition, pBlockState);

        BlockSolarGenerator block = (BlockSolarGenerator) getBlock();
        this.tier = block.getSolarGeneratorTier();
        EnergyTier energyTier =  this.tier.getEnergyTier();

        createEnergyStorage(0, tier.getEnergyCapacity(), EnergyType.EXTRACT, energyTier);
    }

    @Override
    public boolean getActive() {
        return super.getActive();
    }

    @Override
    public void tickOperate(BlockState state) {
        active = false;
        amount = 0;
        getEnergyStorage().updateGenerated(0);

        if (level != null &&level.canSeeSky(getBlockPos())) {
            if (level.isDay() && !level.isThundering()) {
                amount = tier.getDayGenerate();
            }

            if (!level.isDay() || level.isThundering() || level.isRaining()) {
                amount = tier.getNightGenerate();
            }
        }

        if (amount > 0) {
            active = true;
            if (getEnergyStorage().generateEnergy(amount, true) == amount) {
                getEnergyStorage().generateEnergy(amount, false);
                getEnergyStorage().updateGenerated(amount);
            }
        }

        if (amount != lastAmount) {
            lastAmount = amount;
            super.updateBlockState();
        }


        if (this.setActive(active)) {
            super.updateBlockState();
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.active = tag.getBoolean("active");
        this.amount = tag.getInt("amount");
        this.lastAmount = tag.getInt("lastAmount");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("active", active);
        tag.putInt("amount", amount);
        tag.putInt("lastAmount", lastAmount);
        return super.save(tag);
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

    @Override
    public boolean showInGui() {
        return false;
    }
}
