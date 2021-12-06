package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.config.CommonConfig;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.DecoratedDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ModGeneration {

    public static final List<ConfiguredFeature<?, ?>> OVERWORLD = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> NETHER  = new ArrayList<>();
    public static final List<ConfiguredFeature<?, ?>> END = new ArrayList<>();


    public static void registerOres() {
        if (CommonConfig.worldgen_tin_enabled.get()) {
            ConfiguredFeature<?, ?> tinOre = Feature.ORE
                    .configured(new OreConfiguration(List.of(
                            OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
                                    ModBlocks.TIN_ORE.defaultBlockState()),
                            OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
                                    ModBlocks.TIN_ORE.defaultBlockState())),
                            CommonConfig.worldgen_tin_size.get()))
                    .rangeUniform(VerticalAnchor.aboveBottom(CommonConfig.worldgen_tin_min_height.get()), VerticalAnchor.belowTop(CommonConfig.worldgen_tin_max_height.get())).squared().count(CommonConfig.worldgen_tin_chance.get());
            OVERWORLD.add(register("tin_ore", tinOre));
        }
    }

    public static void registerVegetal(ResourceLocation res, BiomeGenerationSettingsBuilder gen) {
        List<Supplier<ConfiguredFeature<?, ?>>> vege = gen.getFeatures(VEGETAL_DECORATION);

        if (CommonConfig.worldgen_rubber_tree_enabled.get()) {
            String biome = res.toString();
            if (CommonConfig.worldgen_rubber_tree_chance_biomes_rich.get().contains(biome)) {
                vege.add(() -> ModConfiguredFeatures.RUBBER_TREE.decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                        .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, CommonConfig.worldgen_rubber_tree_chance_rich.get().floatValue(), 1)))
                );
            } else if (CommonConfig.worldgen_rubber_tree_chance_biomes.get().contains(biome)) {
                vege.add(() -> ModConfiguredFeatures.RUBBER_TREE.decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
                        .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, CommonConfig.worldgen_rubber_tree_chance.get().floatValue(), 1)))
                );
            }
        }
    }

    private static <Config extends FeatureConfiguration> ConfiguredFeature<Config, ?> register(String name, ConfiguredFeature<Config, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(IndReb.MODID, name), configuredFeature);
    }

    @SubscribeEvent
    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();

        if (CommonConfig.worldgen_enabled.get()) {
            registerOres();
            registerVegetal(event.getName(), gen);

            List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

            switch(event.getCategory()) {
                case NETHER -> NETHER.forEach(ore -> features.add(() -> ore));
                case THEEND -> END.forEach(ore -> features.add(() -> ore));
                default -> OVERWORLD.forEach(ore -> features.add(() -> ore));
            }
        }
    }
}
