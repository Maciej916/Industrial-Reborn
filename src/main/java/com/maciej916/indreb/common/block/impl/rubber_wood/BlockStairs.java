package com.maciej916.indreb.common.block.impl.rubber_wood;

import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockStairs extends StairBlock {

    public BlockStairs() {
        super(ModBlocks.RUBBER_PLANKS.defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    }

}
