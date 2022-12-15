package com.maciej916.indreb.common.capability.reactor;

import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.multiblock.reactor.Reactor;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReactorComponentCapability implements IReactorComponentCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IReactorComponentCapability> capReactorComponent = LazyOptional.of(() -> this);

    protected Reactor parent = null;
    protected int slotID = -1;
    protected int row = -10;
    protected int col = -10;

    private ItemStack stack;
    private double initialHeat = 0;

    protected int currentDamage = 0;
    protected int maxDamage;
    protected int currentHeat = 0;
    protected int maxHeat;

    // Stats
    protected int maxReachedHeat = 0;
    protected int currentEUGenerated = 0;
    protected int minIEGenerated = Integer.MAX_VALUE;
    protected int maxIEGenerated = 0;
    protected int currentHeatGenerated = 0;
    protected int minHeatGenerated = Integer.MAX_VALUE;
    protected int maxHeatGenerated = 0;
    protected int currentHullHeating = 0;
    protected int currentComponentHeating = 0;
    protected int currentHullCooling = 0;
    protected int currentVentCooling = 0;
    protected int bestVentCooling = 0;
    protected int currentCellCooling = 0;
    protected int bestCellCooling = 0;
    protected int currentCondensatorCooling = 0;
    protected int bestCondensatorCooling = 0;
    protected double explosionPowerMultiplier = 1;

    public ReactorComponentCapability(ItemStack stack, int maxDamage, int maxHeat) {
        this.stack = stack;
        this.maxDamage = maxDamage;
        this.maxHeat = maxHeat;
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }

    /**
     * Adds this component to a new reactor, and applies changes to the reactor when adding this component if appropriate, such as for reactor plating.
     * @param parent the reactor to add this component to.
     * @param row the row this component will be in.
     * @param col the column this component will be in.
     */
    public void addToReactor(Reactor parent, int row, int col, int slotId) {
        // call removeFromReactor first, in case it had previously been added to a different reactor (unlikely)
        removeFromReactor();
        this.parent = parent;
        this.row = row;
        this.col = col;
        this.slotID = slotId;
    }

    /**
     * Removes this component from its reactor (if any), and applies changes to the reactor when removing this component if appropriate, such as for reactor plating.
     */
    public void removeFromReactor() {
        parent = null;
        this.row = -10;
        this.col = -10;
    }

    @Override
    public int getSlotId() {
        return slotID;
    }

    public int getCurrentDamage() { return currentDamage; }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getCurrentHeat() { return currentHeat; }

    @Override
    public void setCurrentDamage(int damage) {
        this.currentDamage = damage;
    }

    @Override
    public void setCurrentHeat(int heat) {
        this.currentHeat = heat;
    }

    public int getMaxHeat() {
        return maxHeat;
    }

    public int getDurability () {
        return Math.max(currentDamage, currentHeat);
    }

    public int getMaxDurability () {
        return maxDamage > 1 ? maxDamage : maxHeat > 1 ? maxHeat : 0;
    }

    /**
     * Prepare for a new reactor tick.
     */
    public void preReactorTick() {
        currentHullHeating = 0;
        currentComponentHeating = 0;
        currentHullCooling = 0;
        currentVentCooling = 0;
        currentCellCooling = 0;
        currentCondensatorCooling = 0;
        currentEUGenerated = 0;
        currentHeatGenerated = 0;
    }

    public int getMaxReachedHeat() { return maxReachedHeat; }
    public int getCurrentEUGenerated() { return currentEUGenerated; }
    public int getMinIEGenerated() { return minIEGenerated; }
    public int getMaxIEGenerated() { return maxIEGenerated; }
    public int getCurrentHeatGenerated() { return currentHeatGenerated; }
    public int getMinHeatGenerated() { return minHeatGenerated; }
    public int getMaxHeatGenerated() { return maxHeatGenerated; }
    public int getCurrentHullHeating() { return currentHullHeating; }
    public int getCurrentComponentHeating() { return currentComponentHeating; }
    public int getCurrentHullCooling() { return currentHullCooling; }
    public int getCurrentVentCooling() { return currentVentCooling; }
    public int getBestVentCooling() { return bestVentCooling; }
    public int getCurrentCellCooling() { return currentCellCooling; }
    public int getBestCellCooling() { return bestCellCooling; }
    public int getCurrentCondensatorCooling() { return currentCondensatorCooling; }
    public int getBestCondensatorCooling() { return bestCondensatorCooling; }

    /**
     * Adjusts the component heat up or down
     * @param heat the amount of heat to adjust by (positive to add heat, negative to remove heat).
     * @return the amount of heat adjustment refused. (e.g. due to going below minimum heat, breaking due to excessive heat, or attempting to remove heat from a condensator)
     */
    public int adjustCurrentHeat(int heat) {
        if (isHeatAcceptor()) {
            int result = 0;
            int tempHeat = getCurrentHeat();
            tempHeat += heat;
            if (tempHeat > getMaxHeat()) {
                result = getMaxHeat() - tempHeat + 1;
                tempHeat = getMaxHeat();
            } else if (tempHeat < 0.0) {
                result = tempHeat;
                tempHeat = 0;
            }
            currentHeat = tempHeat;
            maxReachedHeat = Math.max(maxReachedHeat, currentHeat);
            return result;
        }
        return heat;
    }

    /**
     * Applies damage to the component, as opposed to heat.  Mainly used for
     * fuel rods and neutron reflectors that lose durability as the reactor runs,
     * but can't recover it via cooling.
     * @param damage the damage to apply (only used if positive).
     */
    public final void applyDamage(int damage) {
        // maxDamage of 1 is treated as meaning the component doesn't accept damage (though it might accept heat instead)
        // if someone actually writes a mod with such a flimsy component, I might have to rethink this.
        if (maxDamage > 1 && damage > 0) {
            currentDamage += damage;
        }
    }

    /**
     * Generate heat if appropriate for component type, and spread to reactor or adjacent cells.
     * @return the amount of heat generated by this component.
     */
    public int generateHeat() {
        return 0;
    }

    /**
     * Generate energy if appropriate for component type.
     * @return the number of IE generated by this component during the current reactor tick.
     */
    public int generateEnergy() {
        return 0;
    }


    /**
     * Dissipate (aka vent) heat if appropriate for component type.
     * @return the amount of heat successfully vented during the current reactor tick.
     */
    public int dissipate() {
        return 0;
    }

    /**
     * Transfer heat between component, neighbors, and/or reactor, if appropriate for component type.
     */
    public void transfer() {
        // do nothing by default.
    }

    /**
     * Checks if this component can accept heat. (e.g. from adjacent fuel rods, or from an exchanger)
     * @return true if this component can accept heat, false otherwise.
     */
    public boolean isHeatAcceptor() {
        // maxHeat of 1 means this component never accepts heat (though it might take damage instead)
        return maxHeat > 1 && !isBroken();
    }

    /**
     * Determines if this component can be cooled down, such as by a component heat vent.
     * @return true if this component can be cooled down, false otherwise.
     */
    public boolean isCoolable() {
        return maxHeat > 1 && !(this instanceof ReactorCondensatorCapability);
    }

    /**
     * Checks if this component acts as a neutron reflector, and boosts performance of adjacent fuel rods,
     * either by being a "neutron reflector" item or by being a fuel rod.
     * @return true if this component reflects neutrons, false otherwise.
     */
    public boolean isNeutronReflector() {
        return false;
    }


    /**
     * Determines if this component is broken in the current tick of the simulation
     * @return true if the component has broken either from damage (e.g. neutron reflectors, fuel rods) or from heat (e.g. heat vents, coolant cells), false otherwise.
     */
    public boolean isBroken() {
        return currentHeat >= getMaxHeat() || currentDamage >= getMaxDamage();
    }

    /**
     * The number of fuel rods in this component (0 for non-fuel-rod components).
     * @return The number of fuel rods in this component, or 0 if this component has no fuel rods.
     */
    public int getRodCount() {
        return 0;
    }


    /**
     * Gets a value added in the formula for calculating explosion power.
     * @return the additive value for explosion power caused by this component,
     * or 0 if this component doesn't affect the addition part of the explosion calculation.
     */
    public double getExplosionPowerOffset() {
        if (!isBroken()) {
            if (getRodCount() == 0 && isNeutronReflector()) {
                return -1;
            }
            return 2 * getRodCount(); // all known fuel rods (including those from GT) use this formula, and non-rod components return 0 for getRodCount
        }
        return 0;
    }

    /**
     * Gets a value multiplied in the formula for calculating explosion power.
     * @return the multiplier value for explosion power caused by this component,
     * or 1 if this component doesn't affect the multiplication part of the explosion calculation.
     */
    public double getExplosionPowerMultiplier() {
        return explosionPowerMultiplier;
    }

    /**
     * Finds the theoretical maximum venting of this component, regardless of
     * whether this venting is from itself, directly from the reactor, or from
     * adjacent components.
     * @return the capacity of this component to vent heat.
     */
    public double getVentCoolingCapacity() {
        return 0;
    }

    /**
     * Finds the theoretical maximum hull cooling of this component.
     * @return the capacity of this component to remove heat from the reactor hull.
     */
    public int getHullCoolingCapacity() {
        return 0;
    }

    /**
     * Gets the current "output" of this component, presumably for writing to
     * CSV data.  What this "output" means may vary by component type or reactor type.
     * @return the output of this component for the current reactor tick.
     */
    public int getCurrentOutput() {
        return 0;
    }

    /**
     * Determines if this component needs input from a Reactor Coolant Injector.
     * Simply returns false for non-condensator items.
     * @return true if this is a condensator that has absorbed enough heat to require the appropriate item added to repair it, false otherwise.
     */
    public boolean needsCoolantInjected() {
        return false;
    }

    /**
     * Simulates having a coolant item added by a Reactor Coolant Injector.
     */
    public void injectCoolant() {
        // do nothing by default.
    }


















    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.REACTOR_ITEM) {
            return capReactorComponent.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("currentDamage", this.currentDamage);
        tag.putInt("currentHeat", this.currentHeat);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
       this.currentDamage = nbt.getInt("currentDamage");
       this.currentHeat = nbt.getInt("currentHeat");
    }




























    public double getInitialHeat() {
        return initialHeat;
    }

    public void setInitialHeat(final double value) {
        if (this.isHeatAcceptor() && value >= 0 && value < this.maxHeat) {
            initialHeat = value;
        }
    }
    private int automationThreshold = 9000;

    public int getAutomationThreshold() {
        return automationThreshold;
    }

    public void setAutomationThreshold(final int value) {
        if (maxHeat > 1 || maxDamage > 1) {
            automationThreshold = value;
        }
    }
    private int reactorPause = 0;

    public int getReactorPause() {
        return reactorPause;
    }

    public void setReactorPause(final int value) {
        if (maxHeat > 1 || maxDamage > 1) {
            reactorPause = value;
        }
    }







}
