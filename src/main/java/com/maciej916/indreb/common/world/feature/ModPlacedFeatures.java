package com.maciej916.indreb.common.world.feature;

import com.maciej916.indreb.common.config.CommonConfig;
import com.maciej916.indreb.common.registries.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.maciej916.indreb.IndReb.MODID;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MODID);

    public static final RegistryObject<PlacedFeature> ORE_TIN_SMALL_PLACED = PLACED_FEATURES.register("ore_tin_small_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORE_TIN_SMALL.getHolder().get(),
                    commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))
            ));
    public static final RegistryObject<PlacedFeature> ORE_TIN_LARGE_PLACED = PLACED_FEATURES.register("ore_tin_large_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORE_TIN_LARGE.getHolder().get(),
                    commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))
            ));
    public static final RegistryObject<PlacedFeature> ORE_LEAD_PLACED = PLACED_FEATURES.register("ore_lead_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORE_LEAD.getHolder().get(),
                    commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(80)))
            ));
    public static final RegistryObject<PlacedFeature> ORE_URANIUM_PLACED = PLACED_FEATURES.register("ore_uranium_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORE_URANIUM.getHolder().get(),
                    commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)))
            ));

    public static final RegistryObject<PlacedFeature> RUBBER_TREE_CHECKED = PLACED_FEATURES.register("rubber_tree_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.RUBBER_TREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.RUBBER_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> RUBBER_TREE_PLACED = PLACED_FEATURES.register("rubber_tree_placed_standard",
            () -> new PlacedFeature(ModConfiguredFeatures.RUBBER_TREE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.05F, 1))));

    public static final RegistryObject<PlacedFeature> RUBBER_TREE_RICH_PLACED = PLACED_FEATURES.register("rubber_tree_rich_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.RUBBER_TREE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1F, 2))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
