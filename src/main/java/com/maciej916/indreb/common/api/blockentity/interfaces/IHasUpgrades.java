package com.maciej916.indreb.common.api.blockentity.interfaces;

import com.maciej916.indreb.common.api.enums.UpgradeType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IHasUpgrades {

    List<UpgradeType> getSupportedUpgrades();
    default int getUpgradesSlots() {
      return 4;
    }
    void onUpgradeStorageContentsChanged(int slot);
    int getUpgradeStorageSlotLimit(int slot);
    boolean isUpgradeStorageItemValid(int slot, @NotNull ItemStack stack);
}
