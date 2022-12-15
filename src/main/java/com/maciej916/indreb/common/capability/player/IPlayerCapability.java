package com.maciej916.indreb.common.capability.player;

import com.maciej916.indreb.common.util.GuiUtil;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;

public interface IPlayerCapability {

    boolean getNightVision();
    void setNightVision(boolean enabled);

    double getRadsLevel();
    void setRadsLevel(double radiationLevel);

    double getPlayerRads();
    double getPlayerMaxRads();
    void setPlayerRads(double radiationLevel);
    void addPlayerRads(double radiationLevel);
    void removePlayerRads(double radiationLevel);

    int getRadsProtection();
    void setRadsProtection(int radiationProtection);
    void setArmourSlots(int armourSlots);

    int getRadiationImmune();
    void setRadiationImmune(int amount);
    default boolean isRadsImmune() {
        return getRadiationImmune() > 0;
    }

    default boolean isTotalRadsNegligible() {
        return getPlayerRads() < RadiationHelper.LOWEST_RADIATION;
    }

    default boolean isRadsFatal() {
        return getPlayerRads() >= getPlayerMaxRads();
    }

    default double getRadsPercentage() {
        return Math.min(100D, 100D * getPlayerRads() / getPlayerMaxRads());
    }

    default String getRadsPercentageString() {
        return GuiUtil.DECIMAL_FORMAT_2.format(getRadsPercentage());
    }

    void tickServer(ServerPlayer player);
    void clone(IPlayerCapability capability);
    void death(DamageSource damageSource);

}
