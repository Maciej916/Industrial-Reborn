package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.config.CommonConfig;
import com.maciej916.indreb.common.generation.RubberFoliagePlacer;
import com.maciej916.indreb.common.generation.RubberTreeBlockStateProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> ORE_TIN_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.getBlock().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.getBlock().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> ORE_LEAD_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.getBlock().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.getBlock().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> ORE_URANIUM_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_URANIUM_ORE.getBlock().defaultBlockState()));

    public static ConfiguredFeature<TreeConfiguration, ?> RUBBER_TREE;
    public static ConfiguredFeature<OreConfiguration, ?> ORE_TIN_SMALL;
    public static ConfiguredFeature<OreConfiguration, ?> ORE_TIN_LARGE;
    public static ConfiguredFeature<OreConfiguration, ?> ORE_LEAD;
    public static ConfiguredFeature<OreConfiguration, ?> ORE_URANIUM;

    public static void init() {
        RUBBER_TREE = register("rubber_tree", Feature.TREE.configured((
                        new TreeConfiguration.TreeConfigurationBuilder(
                                new RubberTreeBlockStateProvider(ModBlocks.RUBBER_LOG.getBlock().defaultBlockState()),
                                new StraightTrunkPlacer(4, 2, 0),
                                SimpleStateProvider.simple(ModBlocks.RUBBER_LEAVES.getBlock().defaultBlockState()),
                                new RubberFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                                new TwoLayersFeatureSize(1, 0, 1)
                        )
                ).ignoreVines().build()
        ));

        ORE_TIN_SMALL = register("ore_tin_small", Feature.ORE.configured(new OreConfiguration(ORE_TIN_TARGET_LIST, CommonConfig.worldgen_tin_size.get())));
        ORE_TIN_LARGE = register("ore_tin_large", Feature.ORE.configured(new OreConfiguration(ORE_TIN_TARGET_LIST, CommonConfig.worldgen_tin_size_large.get())));

        ORE_LEAD = register("ore_lead", Feature.ORE.configured(new OreConfiguration(ORE_LEAD_TARGET_LIST, CommonConfig.worldgen_lead_size.get())));
        ORE_URANIUM = register("ore_uranium", Feature.ORE.configured(new OreConfiguration(ORE_URANIUM_TARGET_LIST, CommonConfig.worldgen_uranium_size.get(), CommonConfig.worldgen_uranium_discard_air_exposure.get().floatValue())));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
