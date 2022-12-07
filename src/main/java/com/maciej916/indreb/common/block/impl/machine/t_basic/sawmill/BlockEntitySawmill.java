package com.maciej916.indreb.common.block.impl.machine.t_basic.sawmill;

import com.maciej916.indreb.common.api.blockentity.interfaces.IHasSound;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.blockentity.StandardMachineBlockEntity;
import com.maciej916.indreb.common.config.impl.ServerConfig;
import com.maciej916.indreb.common.recipe.ModRecipeType;
import com.maciej916.indreb.common.recipe.impl.SawingRecipe;
import com.maciej916.indreb.common.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockEntitySawmill extends StandardMachineBlockEntity implements IHasSound {

    public BlockEntitySawmill(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SAWMILL.get(), pos, blockState, ServerConfig.sawmill_energy_capacity.get());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new MenuSawmill(this, containerId, playerInventory, player, new SimpleContainerData(0));
    }

    @Override
    protected java.util.Optional<SawingRecipe> getRecipe(ItemStack input) {
        return level.getRecipeManager().getRecipeFor(ModRecipeType.SAWING.get(), new SimpleContainer(input), level);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return ModSounds.SAWMILL.get();
    }
}
