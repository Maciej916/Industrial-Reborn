package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.config.CommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ModGeneration {

    public static final HashSet<PlacedFeature> OVERWORLD_ORES = new HashSet<>();
    public static final HashSet<PlacedFeature> NETHER_ORES  = new HashSet<>();
    public static final HashSet<PlacedFeature> END_ORES = new HashSet<>();

    public static void init() {
        ModConfiguredFeatures.init();
        ModPlacedFeatures.init();
    }

    public static void registerOverworldOres() {
        if (CommonConfig.worldgen_tin_enabled.get()) {
            OVERWORLD_ORES.add(ModPlacedFeatures.ORE_TIN_SMALL);
//            OVERWORLD_ORES.add(ModPlacedFeatures.ORE_TIN_LARGE);
        }
        if (CommonConfig.worldgen_lead_enabled.get()) {
            OVERWORLD_ORES.add(ModPlacedFeatures.ORE_LEAD);
        }
        if (CommonConfig.worldgen_uranium_enabled.get()) {
            OVERWORLD_ORES.add(ModPlacedFeatures.ORE_URANIUM);
        }
    }

    public static void registerNetherOres() {


    }

    public static void registerEndOres() {


    }

    public static void addVegetal(ResourceLocation res, BiomeGenerationSettingsBuilder gen) {
        List<Supplier<PlacedFeature>> vege = gen.getFeatures(VEGETAL_DECORATION);
        if (CommonConfig.worldgen_rubber_tree_enabled.get()) {
            String biome = res.toString();
            if (CommonConfig.worldgen_rubber_tree_chance_biomes_rich.get().contains(biome)) {
                vege.add(() -> ModPlacedFeatures.RUBBER_TREE_RICH);
            } else if (CommonConfig.worldgen_rubber_tree_chance_biomes.get().contains(biome)) {
                vege.add(() -> ModPlacedFeatures.RUBBER_TREE_STANDARD);
            }
        }
    }

    @SubscribeEvent
    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();

        if (!CommonConfig.worldgen_enabled.get()) {
            return;
        }

        addVegetal(event.getName(), gen);

        registerNetherOres();
        registerEndOres();
        registerOverworldOres();

        List<Supplier<PlacedFeature>> features = gen.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);


        switch(event.getCategory()) {
            case NETHER -> NETHER_ORES.forEach(ore -> features.add(() -> ore));
            case THEEND -> END_ORES.forEach(ore -> features.add(() -> ore));
            default -> OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
        }

    }
}

