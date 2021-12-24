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
import com.maciej916.indreb.common.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.maciej916.indreb.common.enums.EnumEnergyType.RECEIVE;

public class BlockEntityFluidEnricher extends IndRebBlockEntity implements IEnergyBlock, IExpCollector, ISupportUpgrades {

    public static final int INPUT_SLOT = 0;
    public static final int BUCKET_UP = 1;
    public static final int BUCKET_DOWN = 2;
    public FluidStorage fluidInputStorage = new FluidStorage(8000);
    public FluidStorage fluidOutputStorage = new FluidStorage(8000);



    public BlockEntityProgress progressDrain = new BlockEntityProgress(0, 1);

    private boolean active = false;
    public BlockEntityProgress progress = new BlockEntityProgress();
//    private ScreenFluidEnricher recipe;



    public BlockEntityFluidEnricher(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.FLUID_ENRICHER, pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.fluid_enricher_energy_capacity.get(), ServerConfig.basic_tier_transfer.get(), 0, RECEIVE);
    }

    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        slots.add(new IndRebSlot(INPUT_SLOT, 13, 35, InventorySlotType.INPUT, GuiSlotType.NORMAL, 12, 34));
        slots.add(new IndRebSlot(BUCKET_UP, 127, 19, InventorySlotType.INPUT, GuiSlotType.NORMAL, 126, 18));
        slots.add(new IndRebSlot(BUCKET_DOWN, 127, 50, InventorySlotType.OUTPUT, GuiSlotType.NORMAL, 126, 49));
        return super.addInventorySlot(slots);
    }

    @Override
    public ArrayList<IElectricSlot> addBatterySlot(ArrayList<IElectricSlot> slots) {
        slots.add(new SlotBattery(0, 152, 62, false, List.of(EnergyTier.STANDARD)));
        return super.addBatterySlot(slots);
    }

    @Override
    public void tickOperate(BlockState state) {


        final ItemStack inputStack = getStackHandler().getStackInSlot(INPUT_SLOT);
        final ItemStack bucketUp = getStackHandler().getStackInSlot(BUCKET_UP);
        final ItemStack bucketDown = getStackHandler().getStackInSlot(BUCKET_DOWN);


        if (fluidOutputStorage.getFluidAmount() >= 1000) {

        }





    }


    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.fluidInputStorage.readFromNBT(tag.getCompound("fluidInputStorage"));
        this.fluidOutputStorage.readFromNBT(tag.getCompound("fluidOutputStorage"));
        this.active = tag.getBoolean("active");
        this.progressDrain.deserializeNBT(tag.getCompound("progressDrain"));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("fluidInputStorage", this.fluidInputStorage.writeToNBT(tag.getCompound("fluidInputStorage")));
        tag.put("fluidOutputStorage", this.fluidOutputStorage.writeToNBT(tag.getCompound("fluidOutputStorage")));
        tag.putBoolean("active", active);
        tag.put("progressDrain", this.progressDrain.serializeNBT());
        return super.save(tag);
    }

}
