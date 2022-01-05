package com.maciej916.indreb.common.block.impl.luminator;

import com.maciej916.indreb.common.block.IndRebEntityBlock;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import javax.annotation.Nullable;

public class BlockLuminator extends IndRebEntityBlock implements IStateActive {

    public BlockLuminator() {
        super(Properties.of(Material.GLASS, MaterialColor.WOOL).lightLevel(state -> state.getValue(BlockStateHelper.activeProperty) ? 16 : 0));
        WrenchHelper.registerAction(this).add(WrenchHelper.dropAction());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityLuminator(pos, state);
    }

}
