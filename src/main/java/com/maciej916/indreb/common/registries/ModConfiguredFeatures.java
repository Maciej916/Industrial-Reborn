package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.generation.RubberFoliagePlacer;
import com.maciej916.indreb.common.generation.RubberTreeBlockStateProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<TreeConfiguration, ?> RUBBER_TREE = register("rubber_tree", Feature.TREE.configured((
            new TreeConfiguration.TreeConfigurationBuilder(
                    new RubberTreeBlockStateProvider(ModBlocks.RUBBER_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(4, 2, 0),
                    new SimpleStateProvider(ModBlocks.RUBBER_LEAVES.defaultBlockState()),
                    new SimpleStateProvider(ModBlocks.RUBBER_SAPLING.defaultBlockState()),
                    new RubberFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 0, 1))).ignoreVines()
            .build()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}
