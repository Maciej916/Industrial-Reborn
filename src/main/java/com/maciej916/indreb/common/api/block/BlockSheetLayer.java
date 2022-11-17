package com.maciej916.indreb.common.api.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockSheetLayer extends SnowLayerBlock {

    public BlockSheetLayer(Material material, MaterialColor materialColor, float strength, float speedFactor, SoundType soundType) {
        super(Block.Properties.of(material, materialColor).strength(strength).speedFactor(speedFactor).sound(soundType));
    }
}
