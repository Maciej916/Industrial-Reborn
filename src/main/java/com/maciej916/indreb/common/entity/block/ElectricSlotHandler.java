package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.List;


public class ElectricSlotHandler extends SlotItemHandler {

    private final boolean charging;
    private final InventorySlotType inventorySlotType;
    private final IEnergy energyStorage;

    public ElectricSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean charging, InventorySlotType inventorySlotType, IEnergy energyStorage) {
        super(itemHandler, index, xPosition, yPosition);
        this.charging = charging;
        this.inventorySlotType = inventorySlotType;
        this.energyStorage = energyStorage;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (stack.getItem() instanceof IElectricItem est) {
            if (est.getEnergyTier().getLvl() > energyStorage.energyTier().getLvl() || (est.getEnergyType() == EnergyType.RECEIVE && !charging) || (est.getEnergyType() == EnergyType.EXTRACT && charging)) {
                return false;
            }
        }

        return switch (inventorySlotType) {
            case ELECTRIC -> stack.getItem().getTags().contains(ModTags.ELECTRICS_RES) || stack.getItem().getTags().contains(ModTags.BATTERIES_RES);
            case BATTERY -> stack.getItem().getTags().contains(ModTags.BATTERIES_RES);
            case HELMET -> stack.getItem().getTags().contains(ModTags.HELMET_RES);
            case CHESTPLATE -> stack.getItem().getTags().contains(ModTags.CHESTPLATE_RES);
            case LEGGINGS -> stack.getItem().getTags().contains(ModTags.LEGGINGS_RES);
            case BOOTS -> stack.getItem().getTags().contains(ModTags.BOOTS_RES);
            default -> false;
        };
    }

    public boolean isCharging() {
        return charging;
    }

    public InventorySlotType getInventorySlotType() {
        return inventorySlotType;
    }
}
