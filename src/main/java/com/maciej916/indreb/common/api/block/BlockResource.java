package com.maciej916.indreb.common.api.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockResource extends IndRebBlock {

    public BlockResource(Material material, float destroyTime, float explosionResistance, SoundType soundType) {
        super(Properties.of(material).strength(destroyTime, explosionResistance).sound(soundType));
    }

    public BlockResource(Material material, MaterialColor materialColor, float destroyTime, float explosionResistance, SoundType soundType) {
        super(Properties.of(material, materialColor).strength(destroyTime, explosionResistance).sound(soundType));
    }

}
