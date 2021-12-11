package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
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
    private final List<EnergyTier> energyTier;

    public ElectricSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean charging, InventorySlotType inventorySlotType, List<EnergyTier> energyTier) {
        super(itemHandler, index, xPosition, yPosition);
        this.charging = charging;
        this.inventorySlotType = inventorySlotType;
        this.energyTier = energyTier;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {

        if (stack.getItem() instanceof IElectricItem est) {
            if (!energyTier.contains(est.getEnergyTier()) || (est.getEnergyType() == EnumEnergyType.RECEIVE && !charging) || (est.getEnergyType() == EnumEnergyType.EXTRACT && charging)) {
                return false;
            }
        }

        return switch (inventorySlotType) {
            case ELECTRIC -> stack.getItem().getTags().contains(ModTags.ELECTRICS) || stack.getItem().getTags().contains(ModTags.BATTERIES);
            case BATTERY -> stack.getItem().getTags().contains(ModTags.BATTERIES);
            case HELMET -> stack.getItem().getTags().contains(ModTags.HELMET);
            case CHESTPLATE -> stack.getItem().getTags().contains(ModTags.CHESTPLATE);
            case LEGGINGS -> stack.getItem().getTags().contains(ModTags.LEGGINGS);
            case BOOTS -> stack.getItem().getTags().contains(ModTags.BOOTS);
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
