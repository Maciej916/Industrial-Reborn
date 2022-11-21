package com.maciej916.indreb.common.block.impl.explosive.nuke;

import com.maciej916.indreb.common.api.blockentity.IndRebBlockEntity;
import com.maciej916.indreb.common.api.enums.GuiSlotBg;
import com.maciej916.indreb.common.api.enums.InventorySlotType;
import com.maciej916.indreb.common.api.slot.BaseSlot;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockEntityNuke extends IndRebBlockEntity {

    public static HashMap<Supplier<Item>, Float> EXPLOSION_MULTIPLAYER = new HashMap<>() {{
        put(() -> Items.TNT, 4.0F);
        put(ModItems.INDUSTRIAL_TNT, 7f);
//        put(ModItems.NUKE, 10f);
    }};

    public static final int INPUT_SLOT = 0;

    public BlockEntityNuke(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.NUKE.get(), pPos, pBlockState);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuNuke(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    public boolean isBaseStorageItemValid(int slot, @NotNull ItemStack stack) {
        for (Map.Entry<Supplier<Item>, Float> set : EXPLOSION_MULTIPLAYER.entrySet()) {
            if (set.getKey().get() == stack.getItem()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<BaseSlot> addBaseSlots(ArrayList<BaseSlot> slots) {
        slots.add(new BaseSlot(INPUT_SLOT, 80, 35, 79, 34, InventorySlotType.INPUT, GuiSlotBg.NORMAL));
        return super.addBaseSlots(slots);
    }

    public float getExplosionRadius() {
        ItemStack inputStack = getBaseStorage().getStackInSlot(INPUT_SLOT);
        if (!inputStack.isEmpty()) {
            for (Map.Entry<Supplier<Item>, Float> set : EXPLOSION_MULTIPLAYER.entrySet()) {
                if (set.getKey().get() == inputStack.getItem()) {
                    return 10F + ((set.getValue() * 0.5f) * inputStack.getCount());
                }
            }
        }
        return 10f;
    }

}
