package com.maciej916.indreb.common.container;

import com.maciej916.indreb.common.entity.block.IndRebBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public abstract class IndRebContainer extends AbstractContainerMenu {

    private final IndRebBlockEntity blockEntity;
    private final Player playerEntity;
    private final IItemHandler playerInventory;
    public int playerInvLeft = 8;
    public int playerInvTop = 84;

    public IndRebContainer(@Nullable MenuType<?> pMenuType, int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(pMenuType, windowId);

        this.blockEntity = (IndRebBlockEntity) world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

//        if (blockEntity != null) {
//            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
//                addSlot(new SlotItemHandler(h, 0, 64, 24));
//            });
//        }


        this.init();
    }

    public void init() {
//        if (blockEntity instanceof IHasSlot) {
//            ((IHasSlot) blockEntity).getItemHandlers().forEach(this::addSlot);
//
//        }
//        if (blockEntity instanceof IEnergyBlock) {
//            ((IEnergyBlock) blockEntity).().forEach(this::addSlot);
//
//        }


        blockEntity.getItemHandlers().forEach(this::addSlot);


        blockEntity.getBatteryHandlers().forEach(this::addSlot);



        layoutPlayerInventorySlots(playerInvLeft, playerInvTop);

    }

    public IndRebBlockEntity getBlockEntity() {
        return blockEntity;
    }

//    public int getEnergy() {
//        return blockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
//    }

    public IItemHandler getPlayerInventory() {
        return playerInventory;
    }

    public Player getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, getBlockEntity().getBlock());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}
