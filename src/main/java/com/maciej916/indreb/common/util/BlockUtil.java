package com.maciej916.indreb.common.util;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BlockUtil {

    public static BlockBehaviour.Properties BLOCK_MACHINE = BlockBehaviour.Properties
            .of(Material.METAL)
            .strength(2F, 3F)
            .sound(SoundType.METAL)
            .lightLevel(state -> state.getValue(BlockStateHelper.activeProperty) ? 14 : 0);

//    public static boolean isBlockLoaded(@Nullable IBlockReader world, @Nonnull BlockPos pos) {
//        if (world == null) return false;
//
//        if (world instanceof IWorldReader) {
//            return ((IWorldReader) world).isBlockLoaded(pos);
//        }
//
//        return true;
//    }
//


}
