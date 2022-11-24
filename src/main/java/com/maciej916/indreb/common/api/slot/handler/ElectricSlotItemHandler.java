package com.maciej916.indreb.common.api.slot.handler;

import com.maciej916.indreb.common.api.energy.BasicEnergyStorage;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.tag.ModTagsItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ElectricSlotItemHandler extends BaseSlotItemHandler {

    private final boolean isCharging;
    private final InventorySlotType inventorySlotType;
    private final BasicEnergyStorage energyStorage;

    public ElectricSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean isActive, boolean isCharging, InventorySlotType inventorySlotType, BasicEnergyStorage energyStorage) {
        super(itemHandler, index, xPosition, yPosition, isActive);
        this.isCharging = isCharging;
        this.inventorySlotType = inventorySlotType;
        this.energyStorage = energyStorage;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {

        if (energyStorage != null) {
            if (stack.getItem() instanceof IElectricItem est) {
                if (est.getEnergyTier().getLvl() > energyStorage.energyTier().getLvl() || (est.getEnergyType() == EnergyType.RECEIVE && !isCharging) || (est.getEnergyType() == EnergyType.EXTRACT && isCharging)) {
                    return false;
                }
            }
        }

        return switch (inventorySlotType) {
            case ELECTRIC -> stack.is(ModTagsItem.ELECTRIC) || stack.is(ModTagsItem.BATTERY);
            case BATTERY -> stack.is(ModTagsItem.BATTERY);
            case HELMET -> stack.is(ModTagsItem.HELMET);
            case CHESTPLATE -> stack.is(ModTagsItem.CHESTPLATE);
            case LEGGINGS -> stack.is(ModTagsItem.LEGGINGS);
            case BOOTS -> stack.is(ModTagsItem.BOOTS);
            default -> false;
        };
    }

}
