package com.maciej916.indreb.common.block.impl.reactor.nuclear_reactor;

import com.maciej916.indreb.common.config.ServerConfig;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import com.maciej916.indreb.common.entity.slot.IndRebSlot;
import com.maciej916.indreb.common.enums.*;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BlockEntityNuclearReactor extends IndRebBlockEntity implements IEnergyBlock {

    protected ArrayList<BlockPos> connectedChambers = new ArrayList<>();

    public BlockEntityNuclearReactor(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.NUCLEAR_REACTOR.get(), pWorldPosition, pBlockState);
        createEnergyStorage(0, ServerConfig.matter_fabricator_energy_capacity.get(), EnergyType.EXTRACT, EnergyTier.ADVANCED);
    }




    @Override
    public void tickWork(BlockState state) {





//        System.out.println("asdasd");
//        System.out.println(getItemHandlers());
//
//
//
//
//
//       System.out.println(
//
//               BlockPos.findClosestMatch(getBlockPos(), 1, 1, (blockPos) -> {
//                   return level.getBlockState(blockPos).is(ModBlocks.REACTOR_CHAMBER.get());
//               }).orElse((BlockPos)null)
//
//       );

    }

    @Override
    public void tickClient(BlockState state) {
        for (int i = 0; i < 54; i++) {
            if (i < (connectedChambers.size() * 2) + 2) {
                getSlots().get(i).setGuiSlotType(GuiSlotType.NORMAL);
                getItemHandlers().get(i).setActive(true);
            } else {
                getSlots().get(i).setGuiSlotType(GuiSlotType.DISABLED);
                getItemHandlers().get(i).setActive(false);
            }
        }
    }
    
    @Override
    public ArrayList<IndRebSlot> addInventorySlot(ArrayList<IndRebSlot> slots) {
        int startX = 7;
        int startY = 17;
        int slotId = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                slots.add(new IndRebSlot(slotId, startX + (i * 18) + 1, startY + (j * 18) + 1, InventorySlotType.INPUT, GuiSlotType.NORMAL, startX + (i * 18), startY + (j * 18)));
                slotId++;
            }
        }
        return super.addInventorySlot(slots);
    }

    @Override
    public boolean isItemValidForSlot(int slot, @NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean canExtractEnergyDir(Direction side) {
        return true;
    }

    @Override
    public void load(CompoundTag tag) {
        ArrayList<BlockPos> cChambers = new ArrayList<>();
        ListTag tagList = tag.getList("cChambers", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            BlockPos bp = NbtUtils.readBlockPos(itemTags);
            cChambers.add(bp);
        }
        this.connectedChambers = cChambers;

        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag cChambers = new ListTag();
        for (BlockPos pos: connectedChambers) {
            cChambers.add(NbtUtils.writeBlockPos(pos));
        }
        tag.put("cChambers", cChambers);
        return super.save(tag);
    }

    @Override
    public boolean showBarInGui() {
        return false;
    }

    @Override
    public void onBreakServer() {
        super.onBreakServer();
    }

    public void linkReactorChamber(BlockPos pos) {
        connectedChambers.add(pos);
        updateBlockState();
    }

    public void unlinkReactorChamber(BlockPos pos) {
        connectedChambers.remove(pos);
        updateBlockState();
    }

    public NonNullList<ItemStack> getBreakChamberItems() {
        NonNullList<ItemStack> stacks = NonNullList.create();

        System.out.println("getBreakChamberItems");

        return stacks;
    }
}
