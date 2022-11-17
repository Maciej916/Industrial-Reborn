package com.maciej916.indreb.common.block.impl.reinforced;

import com.maciej916.indreb.common.block.ModBlocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ReinforcedStoneStairs extends StairBlock {

    public ReinforcedStoneStairs() {
        super(() -> ModBlocks.REINFORCED_STONE.get().defaultBlockState(), Properties.of(Material.STONE, MaterialColor.STONE).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
