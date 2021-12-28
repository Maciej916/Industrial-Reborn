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
import net.minecraft.world.level.Level;
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

        this.init();
    }

    public void init() {
        blockEntity.getItemHandlers().forEach(this::addSlot);
        blockEntity.getBatteryHandlers().forEach(this::addSlot);
        blockEntity.getUpgradeHandlers().forEach(this::addSlot);

        layoutPlayerInventorySlots(playerInvLeft, playerInvTop);
    }

    public IndRebBlockEntity getBlockEntity() {
        return blockEntity;
    }

//    protected void trackProgress(BlockEntityProgress progress) {
//        addDataSlot(new DataSlot() {
//            @Override
//            public int get() {
//                return Math.round(progress.getProgress());
//            }
//
//            @Override
//            public void set(int value) {
//                progress.setProgress(value);
//            }
//        });
//        addDataSlot(new DataSlot() {
//            @Override
//            public int get() {
//                return Math.round(progress.getProgressMax());
//            }
//
//            @Override
//            public void set(int value) {
//                progress.setProgressMax(value);
//            }
//        });
//    }
//
//    private void trackPower() {
//        addDataSlot(new DataSlot() {
//            @Override
//            public int get() {
//                return getEnergy() & 0xffff;
//            }
//
//            @Override
//            public void set(int value) {
//                blockEntity.getCapability(ModCapabilities.ENERGY).ifPresent(h -> {
//                    int energyStored = h.energyStored() & 0xffff0000;
//                    h.setEnergy(energyStored + (value & 0xffff));
//                });
//            }
//        });
//        addDataSlot(new DataSlot() {
//            @Override
//            public int get() {
//                return (getEnergy() >> 16) & 0xffff;
//            }
//
//            @Override
//            public void set(int value) {
//                blockEntity.getCapability(ModCapabilities.ENERGY).ifPresent(h -> {
//                    int energyStored = h.energyStored() & 0x0000ffff;
//                    h.setEnergy(energyStored | (value << 16));
//                });
//            }
//        });
//    }
//
//    public int getEnergy() {
//        return blockEntity.getCapability(ModCapabilities.ENERGY).map(IEnergy::energyStored).orElse(0);
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

        int machineSlots = blockEntity.getItemHandlers().size();
        int batterySlots = machineSlots + blockEntity.getBatteryHandlers().size();
        int upgradeSlots = batterySlots + blockEntity.getUpgradeHandlers().size();
        int totalSlots = blockEntity.getTotalMachineSlotCount() + 37;

        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < upgradeSlots) {
                if (!this.moveItemStackTo(stack, upgradeSlots - 1, totalSlots - 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < totalSlots) {
                if (!this.moveItemStackTo(stack, 0, upgradeSlots, true)) {
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
