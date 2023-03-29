package com.maciej916.indreb.common.capability.player;

import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.effects.ModEffects;
import com.maciej916.indreb.common.item.impl.GeigerCounter;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketRadiationSync;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCapability implements IPlayerCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IPlayerCapability> playerCap = LazyOptional.of(() -> this);
    private boolean nightVision = false;

    private boolean radsChanged = false;
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
        double newRads = Math.max(0, radiationLevel);
        if (this.playerRads != newRads) {
            radsChanged = true;
        }
        this.radsLevel = newRads;

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
    public void setPlayerRads(double playerRads) {
        double newRads = Math.min(playerRads, RadiationHelper.PLAYER_MAX_RADIATION);
        if (this.playerRads != newRads) {
            radsChanged = true;
        }
        this.playerRads = newRads;
    }

    @Override
    public void addPlayerRads(double radiationLevel) {
        if (radiationImmune == 0 && radiationLevel > 0) {
            setPlayerRads(Math.min(playerRads + radiationLevel, RadiationHelper.PLAYER_MAX_RADIATION));
        }
    }

    @Override
    public void removePlayerRads(double radiationLevel) {
        if (radiationImmune == 0 && radiationLevel > 0) {
            setPlayerRads(Math.max(playerRads - radiationLevel, 0));
        }
    }

    @Override
    public int getRadsProtection() {
        return radiationProtection;
    }

    @Override
    public void setRadsProtection(int newRadsProtection) {
        if (this.radiationProtection != newRadsProtection) {
            radsChanged = true;
        }

        this.radiationProtection = newRadsProtection;
    }

    @Override
    public void setArmourSlots(int newArmourSlots) {
        if (this.armourSlots != newArmourSlots) {
            radsChanged = true;
        }

        this.armourSlots = newArmourSlots;
    }

    @Override
    public int getRadiationImmune() {
        return radiationImmune;
    }

    @Override
    public void setRadiationImmune(int amount) {

        if (radiationImmune != amount) {
            radsChanged = true;
        }

        radiationImmune = amount;
    }

    @Override
    public void tickServer(ServerPlayer player) {
        radsChanged = false;

        if (getRadiationImmune() > 0) {
            setRadiationImmune(getRadiationImmune() - 1);
        } else {
            if (getPlayerRads() > 0) {
                setPlayerRads(Math.max(getPlayerRads() - RadiationHelper.PLAYER_RADIATION_DECAY, 0));
            }
        }

        if (getRadiationImmune() == 0 && getPlayerRads() > 0) {
            double radsPercent = getRadsPercentage();
            int ampl = radsPercent <= 25 ? 0 : radsPercent <= 50 ? 1 : radsPercent <= 75 ? 3 : 4;
            if (radsPercent >= 1) {
                if (!player.hasEffect(ModEffects.RADIATION.get())) {
                    MobEffectInstance instance = new MobEffectInstance(ModEffects.RADIATION.get(), 400, ampl, false, true, true);
                    player.addEffect(instance);
                }
            }
        }

        syncRads(player);
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

    public void syncRads(ServerPlayer player) {
        if (radsChanged) {
            if (player.getLevel().getGameTime() % 20 == 0) {
                boolean geigerFound = false;
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack itemStack = player.getInventory().getItem(i);
                    if (itemStack.getItem() instanceof GeigerCounter) {
                        geigerFound = true;
                        break;
                    }
                }

                if (geigerFound) {
                    ModNetworking.sendToPlayer(player, new PacketRadiationSync(this));
                }
            }
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
