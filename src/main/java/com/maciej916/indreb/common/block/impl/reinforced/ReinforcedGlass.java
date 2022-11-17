package com.maciej916.indreb.common.block.impl.reinforced;

import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ReinforcedGlass extends AbstractGlassBlock {

    public ReinforcedGlass() {
        super(Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).strength(10.0F, 10000000000F).sound(SoundType.GLASS).noOcclusion());
    }

}
