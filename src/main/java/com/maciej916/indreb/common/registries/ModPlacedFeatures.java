package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static Holder<PlacedFeature> RUBBER_TREE_STANDARD;
    public static Holder<PlacedFeature> RUBBER_TREE_RICH;

    public static Holder<PlacedFeature> ORE_TIN_SMALL;
    public static Holder<PlacedFeature> ORE_TIN_LARGE;
    public static Holder<PlacedFeature> ORE_LEAD;
    public static Holder<PlacedFeature> ORE_URANIUM;

    public static void init() {
        RUBBER_TREE_STANDARD = PlacementUtils.register("rubber_tree_standard", ModConfiguredFeatures.RUBBER_TREE, PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.RUBBER_SAPLING.getBlock().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        RUBBER_TREE_RICH = PlacementUtils.register("rubber_tree_rich", ModConfiguredFeatures.RUBBER_TREE, PlacementUtils.countExtra(0, 0.2F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.RUBBER_SAPLING.getBlock().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

        ORE_TIN_SMALL = PlacementUtils.register("ore_tin_small", ModConfiguredFeatures.ORE_TIN_SMALL, commonOrePlacement(CommonConfig.worldgen_tin_placement.get(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CommonConfig.worldgen_tin_placement_offset.get()), VerticalAnchor.absolute(CommonConfig.worldgen_tin_placement_vertical.get()))));
        ORE_TIN_LARGE = PlacementUtils.register("ore_tin_large", ModConfiguredFeatures.ORE_TIN_LARGE, commonOrePlacement(CommonConfig.worldgen_tin_placement.get(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CommonConfig.worldgen_tin_placement_offset.get()), VerticalAnchor.absolute(CommonConfig.worldgen_tin_placement_vertical.get()))));

        ORE_LEAD = PlacementUtils.register("ore_lead", ModConfiguredFeatures.ORE_LEAD, commonOrePlacement(CommonConfig.worldgen_lead_placement.get(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CommonConfig.worldgen_lead_placement_offset.get()), VerticalAnchor.absolute(CommonConfig.worldgen_lead_placement_vertical.get()))));
        ORE_URANIUM = PlacementUtils.register("ore_uranium", ModConfiguredFeatures.ORE_URANIUM, commonOrePlacement(CommonConfig.worldgen_uranium_placement.get(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CommonConfig.worldgen_uranium_placement_offset.get()), VerticalAnchor.absolute(CommonConfig.worldgen_uranium_placement_vertical.get()))));
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }
}
