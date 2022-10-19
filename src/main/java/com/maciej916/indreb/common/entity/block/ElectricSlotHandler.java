package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.item.IElectricItem;
import com.maciej916.indreb.common.registries.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;

import javax.annotation.Nonnull;
import java.util.ArrayList;
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

        List<ResourceLocation> itemTags = ForgeRegistries.ITEMS.tags().getReverseTag(stack.getItem()).map(IReverseTag::getTagKeys)
                .map(tagKeyStream -> tagKeyStream.map(TagKey::location).toList()).orElse(new ArrayList<>());
        return switch (inventorySlotType) {
            case ELECTRIC -> itemTags.contains(ModTags.ELECTRICS_RES) || itemTags.contains(ModTags.BATTERIES_RES);
            case BATTERY -> itemTags.contains(ModTags.BATTERIES_RES);
            case HELMET -> itemTags.contains(ModTags.HELMET_RES);
            case CHESTPLATE -> itemTags.contains(ModTags.CHESTPLATE_RES);
            case LEGGINGS -> itemTags.contains(ModTags.LEGGINGS_RES);
            case BOOTS -> itemTags.contains(ModTags.BOOTS_RES);
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
