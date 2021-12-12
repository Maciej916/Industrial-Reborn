package com.maciej916.indreb.common.interfaces.block;

import com.maciej916.indreb.common.enums.EnergyTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;

public interface IElectricMachine {

    EnergyTier getEnergyTier();

}
