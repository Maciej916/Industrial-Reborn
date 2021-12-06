package com.maciej916.indreb.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonConfig {
	public static ForgeConfigSpec.BooleanValue worldgen_enabled;

	public static ForgeConfigSpec.BooleanValue worldgen_tin_enabled;
	public static ForgeConfigSpec.IntValue worldgen_tin_min_height;
	public static ForgeConfigSpec.IntValue worldgen_tin_max_height;
	public static ForgeConfigSpec.IntValue worldgen_tin_size;
	public static ForgeConfigSpec.IntValue worldgen_tin_chance;

	public static ForgeConfigSpec.BooleanValue worldgen_rubber_tree_enabled;
	public static ForgeConfigSpec.DoubleValue worldgen_rubber_tree_chance;
	public static ForgeConfigSpec.DoubleValue worldgen_rubber_tree_chance_rich;
	public static ForgeConfigSpec.ConfigValue<List<String>> worldgen_rubber_tree_chance_biomes;
	public static ForgeConfigSpec.ConfigValue<List<String>> worldgen_rubber_tree_chance_biomes_rich;

	public static ForgeConfigSpec initCommon() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("general");

		builder.pop();

		builder.push("worldgen");
		worldgen_enabled = builder.define("enabled", true);

		builder.push("tin");
		worldgen_tin_enabled = builder.define("enabled", true);
		worldgen_tin_min_height = builder.defineInRange("min_height", 0, 0, Integer.MAX_VALUE);
		worldgen_tin_max_height = builder.defineInRange("max_height", 64, 0, Integer.MAX_VALUE);
		worldgen_tin_size = builder.defineInRange("size", 6, 1, Integer.MAX_VALUE);
		worldgen_tin_chance = builder.defineInRange("chance", 2, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("rubber_tree");
		worldgen_rubber_tree_enabled = builder.define("enabled", true);
		worldgen_rubber_tree_chance = builder.defineInRange("chance", 0.2F, 0, 1);
		worldgen_rubber_tree_chance_rich = builder.defineInRange("chance_rich", 0.4F, 0, 1);
		worldgen_rubber_tree_chance_biomes = builder.define("chance_biomes", new ArrayList<>(Arrays.asList("minecraft:forest", "minecraft:birch_forest", "minecraft:flower_forest", "minecraft:wooded_hills")));
		worldgen_rubber_tree_chance_biomes_rich = builder.define("chance_biomes_rich", new ArrayList<>(Arrays.asList("minecraft:swamp", "minecraft:swamp_hills")));
		builder.pop();

		return builder.build();
	}
}
