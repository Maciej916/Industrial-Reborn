package com.maciej916.indreb.common.entity.block;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.entity.slot.IndRebSlotItemHandler;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
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

public class ElectricSlotHandler extends IndRebSlotItemHandler {

    private final IElectricSlot electricSlot;
    private final IEnergy energyStorage;

    public ElectricSlotHandler(IndRebBlockEntity be, IItemHandler itemHandler, IElectricSlot electricSlot, IEnergy energyStorage) {
        super(be, itemHandler, electricSlot.getSlotId(), electricSlot.getXPosition(), electricSlot.getYPosition());
        this.electricSlot = electricSlot;
        this.energyStorage = energyStorage;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (stack.getItem() instanceof IElectricItem est) {
            if (est.getEnergyTier().getLvl() > energyStorage.energyTier().getLvl() || (est.getEnergyType() == EnergyType.RECEIVE && !electricSlot.isCharging()) || (est.getEnergyType() == EnergyType.EXTRACT && electricSlot.isCharging())) {
                return false;
            }
        }

        List<ResourceLocation> itemTags = ForgeRegistries.ITEMS.tags().getReverseTag(stack.getItem()).map(IReverseTag::getTagKeys)
                .map(tagKeyStream -> tagKeyStream.map(TagKey::location).toList()).orElse(new ArrayList<>());
        return switch (electricSlot.getInventorySlotType()) {
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
        return electricSlot.isCharging();
    }

}
