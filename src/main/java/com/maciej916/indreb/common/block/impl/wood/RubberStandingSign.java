package com.maciej916.indreb.common.block.impl.wood;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RubberStandingSign extends StandingSignBlock {

    public RubberStandingSign() {
        super(Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodType.BIRCH);
    }

}
