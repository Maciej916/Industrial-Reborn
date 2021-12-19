package com.maciej916.indreb.common.block.impl.machines.fluid_enricher;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.BlockEntityProgress;
import com.maciej916.indreb.common.entity.block.FluidStorage;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.entity.slot.SlotBattery;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.enums.InventorySlotType;
import com.maciej916.indreb.common.interfaces.entity.IElectricSlot;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.interfaces.entity.ITileSound;
import com.maciej916.indreb.common.network.ModNetworking;
import com.maciej916.indreb.common.network.packet.PacketExtruderRecipe;
import com.maciej916.indreb.common.receipe.impl.ExtrudingRecipe;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModRecipeType;
import com.maciej916.indreb.common.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.maciej916.indreb.common.enums.EnumEnergyType.RECEIVE;

public class BlockEntityFluidEnricher extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT = 0;

    public BlockEntityFluidEnricher(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FLUID_ENRICHER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.fluid_enricher_energy_capacity.get(), ServerConfig.basic_tier_transfer.get(), 0, RECEIVE);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 8, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 7, 34));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false, List.of(EnergyTier.STANDARD)));
        return super.addBatterySlot(slots);
    }

}
