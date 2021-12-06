package com.maciej916.indreb.common.generation;

import com.maciej916.indreb.common.registries.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class RubberTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.RUBBER_TREE;
    }
}
