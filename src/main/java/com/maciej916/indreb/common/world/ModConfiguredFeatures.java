package com.maciej916.indreb.common.world;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.world.rubber_tree.RubberFoliagePlacer;
import com.maciej916.indreb.common.world.rubber_tree.RubberTreeBlockStateProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.maciej916.indreb.IndReb.MODID;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_TIN_TARGET_LIST = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_LEAD_TARGET_LIST = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_URANIUM_TARGET_LIST = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.URANIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_SILVER_TARGET_LIST = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TIN_SMALL = CONFIGURED_FEATURES.register("tin_ore_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_TIN_TARGET_LIST.get(), 8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_TIN_LARGE = CONFIGURED_FEATURES.register("ore_tin_large", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_TIN_TARGET_LIST.get(), 10)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_LEAD = CONFIGURED_FEATURES.register("ore_lead", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_LEAD_TARGET_LIST.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_URANIUM = CONFIGURED_FEATURES.register("ore_uranium", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_URANIUM_TARGET_LIST.get(), 2, 0.5f)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_SILVER = CONFIGURED_FEATURES.register("ore_silver", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_SILVER_TARGET_LIST.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBBER_TREE =
            CONFIGURED_FEATURES.register("rubber_tree", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            new RubberTreeBlockStateProvider(ModBlocks.RUBBER_LOG.get().defaultBlockState()),
                            new StraightTrunkPlacer(4, 2, 0),
                            SimpleStateProvider.simple(ModBlocks.RUBBER_LEAVES.get().defaultBlockState()),
                            new RubberFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                            new TwoLayersFeatureSize(1, 0, 1)
                    ).ignoreVines().build()));


    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBBER_TREE_SPAWN =
            CONFIGURED_FEATURES.register("rubber_tree_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.RUBBER_TREE_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.RUBBER_TREE_CHECKED.getHolder().get())));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
