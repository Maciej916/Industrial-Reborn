package com.maciej916.indreb.common.block.impl.cf;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockReinforcedStoneStairs extends StairBlock {

    public BlockReinforcedStoneStairs() {
        super(ModBlocks.REINFORCED_STONE.getBlock().defaultBlockState(), Properties.of(Material.STONE, MaterialColor.STONE).strength(10.0F, 10000000000F).sound(SoundType.STONE));
    }

}
