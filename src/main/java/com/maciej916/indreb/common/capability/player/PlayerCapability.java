package com.maciej916.indreb.common.capability.player;

import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCapability implements IPlayerCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IPlayerCapability> playerCap = LazyOptional.of(() -> this);
    private boolean nightVision = false;

    private double radsLevel = 0;
    private double playerRads = 0;
    private int radiationImmune = 0;
    private int radiationProtection = 0;
    private int armourSlots = 0;

    public PlayerCapability() {

    }

    @Override
    public boolean getNightVision() {
        return nightVision;
    }

    @Override
    public void setNightVision(boolean enabled) {
        this.nightVision = enabled;
    }

    @Override
    public double getRadsLevel() {
        return radsLevel;
    }

    @Override
    public void setRadsLevel(double radiationLevel) {
        this.radsLevel = Math.max(0, radiationLevel);

        if (radiationLevel > 0) {
            double radsResistance = getRadsResistance();
            double radsToAdd = radsResistance < this.radsLevel ? Math.max(this.radsLevel - radsResistance, 0) : 0;
            addPlayerRads(radsToAdd);
        } else {
            removePlayerRads(radiationLevel);
        }
    }

    @Override
    public double getPlayerRads() {
        return playerRads;
    }

    @Override
    public double getPlayerMaxRads() {
        return RadiationHelper.PLAYER_MAX_RADIATION;
    }

    @Override
    public void setPlayerRads(double playerContamination) {
        this.playerRads = Math.min(playerContamination, RadiationHelper.PLAYER_MAX_RADIATION);
    }

    @Override
    public void addPlayerRads(double radiationLevel) {
        if (radiationImmune == 0 && radiationLevel > 0) {
            playerRads = Math.min(playerRads + radiationLevel, RadiationHelper.PLAYER_MAX_RADIATION);
        }
    }

    @Override
    public void removePlayerRads(double radiationLevel) {
        if (radiationImmune == 0 && radiationLevel > 0) {
            playerRads = Math.max(playerRads - radiationLevel, 0);
        }
    }

    @Override
    public int getRadsProtection() {
        return radiationProtection;
    }

    @Override
    public void setRadsProtection(int radiationProtection) {
        this.radiationProtection = radiationProtection;
    }

    @Override
    public void setArmourSlots(int armourSlots) {
        this.armourSlots = armourSlots;
    }

    @Override
    public int getRadiationImmune() {
        return radiationImmune;
    }

    @Override
    public void tick(Player player) {
        if (radiationImmune > 0) {
            radiationImmune--;
        } else {
            if (playerRads > 0) {
                playerRads = Math.max(playerRads - RadiationHelper.PLAYER_RADIATION_DECAY, 0);
            }
        }


//        if (radiation_player_rads_fatal && !player.isCreative() && !player.isSpectator() && !playerRads.isImmune() && playerRads.isFatal()) {
//            player.attackEntityFrom(DamageSources.FATAL_RADS, Float.MAX_VALUE);
//        }


    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.PLAYER_CAPABILITY) {
            return playerCap.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public void clone(IPlayerCapability capability) {
        this.nightVision = capability.getNightVision();
        this.radsLevel = capability.getRadsLevel();
        this.playerRads = capability.getPlayerRads();
        this.radiationImmune = capability.getRadiationImmune();
        this.radiationProtection = capability.getRadsProtection();
    }

    @Override
    public void death(DamageSource damageSource) {
        if (damageSource == RadiationHelper.RADIATION) {
            radiationImmune = RadiationHelper.RADIATION_IMMUNE_TIME;
        }
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("nightVision", this.nightVision);

        tag.putDouble("radsLevel", this.radsLevel);
        tag.putDouble("playerRads", this.playerRads);
        tag.putInt("radiationImmune", this.radiationImmune);
        tag.putInt("radiationProtection", this.radiationProtection);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
       this.nightVision = nbt.getBoolean("nightVision");

       this.radsLevel = nbt.getDouble("radsLevel");
       this.playerRads = nbt.getDouble("playerRads");
       this.radiationImmune = nbt.getInt("radiationImmune");
       this.radiationProtection = nbt.getInt("radiationProtection");
    }

    public double getRadsResistance() {
        return ((radiationProtection * 5f / 100) * 0.03D) * (armourSlots / 4f);
    }
}
