package com.maciej916.indreb.common.multiblock.reactor;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.blockentity.interfaces.IGetEnabled;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.reactor.IReactorComponentCapability;
import com.maciej916.indreb.common.util.CapabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class Reactor implements INBTSerializable<CompoundTag>, IBaseProgress, IGetEnabled {

    private final IReactorComponentCapability[][] grid = new IReactorComponentCapability[6][9];

    private boolean enabled = false;

    private int currentIEOutput = 0;
    private int currentHeat = 0;
    private int maxHeat = 10000;
    private int ventedHeat = 0;
    private boolean fluid = false;

    public Reactor() {

    }

    public IReactorComponentCapability getComponentAt(final int row, final int column) {
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[row].length) {
            return grid[row][column];
        }
        return null;
    }

    public void setComponentAt(int row, int column, IReactorComponentCapability component, int slotId) {
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[row].length) {
            if (grid[row][column] != null) {
                grid[row][column].removeFromReactor();
            }
            grid[row][column] = component;
            if (component != null) {
                component.addToReactor(this, row, column, slotId);
            }
        }
    }

    /**
     * Checks whether the reactor is to simulate a fluid-style reactor, rather than a direct EU-output reactor.
     * @return true if this was set to be a fluid-style reactor, false if this was set to be direct EU-output reactor.
     */
    public boolean isFluid() {
        return fluid;
    }

    /**
     * Sets whether the reactor is to simulate a fluid-style reactor, rather than a direct EU-output reactor.
     * @param fluid true if this is to be a fluid-style reactor, false if this is to be direct EU-output reactor.
     */
    public void setFluid(final boolean fluid) {
        this.fluid = fluid;
    }


    /**
     * @return the amount of EU output in the reactor tick just simulated.
     */
    public int getCurrentIEOutput() {
        return currentIEOutput;
    }

    public void setCurrentIEOutput(int currentIEOutput) {
        this.currentIEOutput = currentIEOutput;
    }

    /**
     * @return the current heat level of the reactor.
     */
    public int getCurrentHeat() {
        return currentHeat;
    }

    /**
     * @return the maximum heat of the reactor.
     */
    public int getMaxHeat() {
        return maxHeat;
    }

    public void setMaxHeat(int maxHeat) {
        this.maxHeat = maxHeat;
    }

    /**
     * Adjust the maximum heat
     * @param adjustment the adjustment amount (negative values decrease the max heat).
     */
    public void adjustMaxHeat(final double adjustment) {
        maxHeat += adjustment;
    }

    /**
     * Set the current heat of the reactor.  Mainly to be used for simulating a pre-heated reactor, or for resetting to 0 for a new simulation.
     * @param currentHeat the heat to set
     */
    public void setCurrentHeat(final int currentHeat) {
        this.currentHeat = currentHeat;
    }

    /**
     * Adjusts the reactor's current heat by a specified amount
     * @param adjustment the adjustment amount.
     */
    public void adjustCurrentHeat(final int adjustment) {
        currentHeat += adjustment;
        if (currentHeat < 0) {
            currentHeat = 0;
        }
    }

    /**
     * add some EU output.
     * @param amount the amount of EU to output over 1 reactor tick (20 game ticks).
     */
    public void addEUOutput(final int amount) {
        currentIEOutput += amount;
    }

    /**
     * clears the EU output (presumably to start simulating a new reactor tick).
     */
    public void clearIEOutput() {
        currentIEOutput = 0;
    }

    /**
     * @return the amount of heat vented this reactor tick.
     */
    public int getVentedHeat() {
        return ventedHeat;
    }

    public void setVentedHeat(int ventedHeat) {
        this.ventedHeat = ventedHeat;
    }

    /**
     * Adds to the amount of heat vented this reactor tick, in case it is a new-style reactor with a pressure vessel and outputting heat to fluid instead of EU.
     * @param amount the amount to add.
     */
    public void ventHeat(final double amount) {
        ventedHeat += amount;
    }

    /**
     * Clears the amount of vented heat, in case a new reactor tick is starting.
     */
    public void clearVentedHeat() {
        ventedHeat = 0;
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("currentIEOutput", this.currentIEOutput);
        tag.putInt("currentHeat", this.currentHeat);
        tag.putInt("maxHeat", this.maxHeat);
        tag.putInt("ventedHeat", this.ventedHeat);
        tag.putBoolean("fluid", this.fluid);
        tag.putBoolean("enabled", this.enabled);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.currentIEOutput = nbt.getInt("currentIEOutput");
        this.currentHeat = nbt.getInt("currentHeat");
        this.maxHeat = nbt.getInt("maxHeat");
        this.ventedHeat = nbt.getInt("ventedHeat");
        this.fluid = nbt.getBoolean("fluid");
        this.enabled = nbt.getBoolean("enabled");
    }

    @Override
    public float getCurrentProgress() {
        return currentHeat;
    }

    @Override
    public float getProgressMax() {
        return maxHeat;
    }

    @Override
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void explodeReactor(ServerLevel level, BlockPos pos, int totalRodCount) {
        float explosionPower = totalRodCount * 5f;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                IReactorComponentCapability component = getComponentAt(row, col);
                if (component != null) {
                    explosionPower = explosionPower * (float) component.getExplosionPowerMultiplier();
                }
            }
        }

        List<BlockPos> exPos = new ArrayList<>();

        for (int x = -2; x <= 2; x = x+2) {
            for (int y = -2; y <= 2; y = y+2) {
                for (int z = -2; z <= 2; z = z+2) {
                    BlockPos newPos = pos.offset(x, y, z);
                    exPos.add(newPos);
                }
            }
        }

        for (BlockPos expPos: exPos) {
            level.explode(null, expPos.getX() + 0.5D, expPos.getY() + 0.5D, expPos.getZ() + 0.5D, explosionPower / totalRodCount, false, Explosion.BlockInteraction.DESTROY);
        }
    }

    public void initComponents(ItemStackHandler stackHandler) {
        int slotId = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                ItemStack stack = stackHandler.getStackInSlot(slotId);
                IReactorComponentCapability cap = CapabilityUtil.getCapabilityHelper(stack, ModCapabilities.REACTOR_ITEM).getValue();
                if (cap != null) {
                    setComponentAt(row, col, cap, slotId);
                }
                slotId++;
            }
        }
    }

}
