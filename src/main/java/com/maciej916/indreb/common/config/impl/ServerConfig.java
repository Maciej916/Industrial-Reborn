package com.maciej916.indreb.common.config.impl;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class ServerConfig {

	private static final Predicate<Object> listValidator = (Objects::nonNull);

	public static ForgeConfigSpec.ConfigValue<List<? extends String>> radiation_foods;

	public static ForgeConfigSpec.IntValue basic_tier_transfer;
	public static ForgeConfigSpec.IntValue standard_tier_transfer;
	public static ForgeConfigSpec.IntValue advanced_tier_transfer;
	public static ForgeConfigSpec.IntValue super_tier_transfer;
	public static ForgeConfigSpec.IntValue ultra_tier_transfer;
	public static ForgeConfigSpec.IntValue creative_tier_transfer;

	public static ForgeConfigSpec.IntValue wooden_battery_box_capacity;
	public static ForgeConfigSpec.IntValue cesu_capacity;
	public static ForgeConfigSpec.IntValue mfe_capacity;
	public static ForgeConfigSpec.IntValue mfsu_capacity;

	public static ForgeConfigSpec.IntValue generator_energy_capacity;
	public static ForgeConfigSpec.IntValue generator_tick_generate;

	public static ForgeConfigSpec.IntValue geo_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue geo_generator_lava_capacity;
	public static ForgeConfigSpec.IntValue geo_generator_tick_generate;

	public static ForgeConfigSpec.IntValue semifluid_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue semifluid_generator_fluid_capacity;
	public static ForgeConfigSpec.IntValue semifluid_generator_tick_generate;

	public static ForgeConfigSpec.IntValue solar_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue solar_generator_day_tick_generate;
	public static ForgeConfigSpec.IntValue solar_generator_night_tick_generate;

	public static ForgeConfigSpec.IntValue advanced_solar_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue advanced_solar_generator_day_tick_generate;
	public static ForgeConfigSpec.IntValue advanced_solar_generator_night_tick_generate;

	public static ForgeConfigSpec.IntValue hybrid_solar_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue hybrid_solar_generator_day_tick_generate;
	public static ForgeConfigSpec.IntValue hybrid_solar_generator_night_tick_generate;

	public static ForgeConfigSpec.IntValue quantum_solar_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue quantum_solar_generator_day_tick_generate;
	public static ForgeConfigSpec.IntValue quantum_solar_generator_night_tick_generate;

	public static ForgeConfigSpec.IntValue crystalline_generator_energy_capacity;
	public static ForgeConfigSpec.IntValue crystalline_generator_tick_generate;

	public static ForgeConfigSpec.IntValue electric_furnace_energy_capacity;
	public static ForgeConfigSpec.IntValue electric_furnace_tick_usage;

	public static ForgeConfigSpec.IntValue crusher_energy_capacity;
	public static ForgeConfigSpec.IntValue extractor_energy_capacity;
	public static ForgeConfigSpec.IntValue extruder_energy_capacity;
	public static ForgeConfigSpec.IntValue compressor_energy_capacity;
	public static ForgeConfigSpec.IntValue sawmill_energy_capacity;

	public static ForgeConfigSpec.IntValue alloy_smelter_energy_capacity;
	public static ForgeConfigSpec.IntValue alloy_smelter_heat_cost;

	public static ForgeConfigSpec.IntValue recycler_energy_capacity;
	public static ForgeConfigSpec.IntValue canning_machine_energy_capacity;

	public static ForgeConfigSpec.IntValue fluid_enricher_energy_capacity;
	public static ForgeConfigSpec.IntValue fluid_enricher_fluid_capacity;

	public static ForgeConfigSpec.IntValue fermenter_energy_capacity;
	public static ForgeConfigSpec.IntValue fermenter_fluid_input_capacity;
	public static ForgeConfigSpec.IntValue fermenter_fluid_output_capacity;
	public static ForgeConfigSpec.IntValue fermenter_heat_cost;

	public static ForgeConfigSpec.IntValue fluid_cell_capacity;
	public static ForgeConfigSpec.IntValue foam_sprayer_capacity;

	public static ForgeConfigSpec.IntValue ore_washing_plant_energy_capacity;
	public static ForgeConfigSpec.IntValue ore_washing_plant_fluid_capacity;

	public static ForgeConfigSpec.IntValue matter_fabricator_energy_capacity;
	public static ForgeConfigSpec.IntValue matter_fabricator_matter_capacity;
	public static ForgeConfigSpec.IntValue matter_fabricator_produce_run;

	public static ForgeConfigSpec.IntValue thermal_centrifuge_energy_capacity;
	public static ForgeConfigSpec.IntValue thermal_centrifuge_temp_cost;

	public static ForgeConfigSpec.IntValue scanner_energy_capacity;

	public static ForgeConfigSpec.IntValue replicator_energy_capacity;
	public static ForgeConfigSpec.IntValue replicator_matter_capacity;

	public static ForgeConfigSpec.IntValue metal_former_energy_capacity;

	public static ForgeConfigSpec register(ForgeConfigSpec.Builder builder) {
		builder.push("general");

		builder.pop();

		builder.push("radiation");

//		List<String> list = List.of("minecraft:golden_carrot,-0.390", "minecraft:golden_apple,-12.5", "minecraft:enchanted_golden_apple,-100", "indreb:canned_golden_apple,-3.125", "indreb:canned_enchanted_golden_apple,-25");
//		List<String> list = List.of("minecraft:golden_carrot,-0.00125", "minecraft:golden_apple,-0.4", "minecraft:enchanted_golden_apple,-4", "indreb:canned_golden_apple,-0.1", "indreb:canned_enchanted_golden_apple,-1");
		List<String> list = List.of("minecraft:golden_carrot,-0.0125", "minecraft:golden_apple,-4", "minecraft:enchanted_golden_apple,-40", "indreb:canned_golden_apple,-1", "indreb:canned_enchanted_golden_apple,-10");
		radiation_foods = builder.comment("Foods that can remove or add radiation (double), example modid:item,-rads").defineList("radiation_foods", () -> list, listValidator);
		builder.pop();

		builder.push("tier_transfer");
		basic_tier_transfer = builder.defineInRange("basic_tier_transfer", 32, 1, Integer.MAX_VALUE);
		standard_tier_transfer = builder.defineInRange("standard_tier_transfer", 128, 1, Integer.MAX_VALUE);
		advanced_tier_transfer = builder.defineInRange("advanced_tier_transfer", 512, 1, Integer.MAX_VALUE);
		super_tier_transfer = builder.defineInRange("super_tier_transfer", 2048, 1, Integer.MAX_VALUE);
		ultra_tier_transfer = builder.defineInRange("ultra_tier_transfer", 8192, 1, Integer.MAX_VALUE);
		creative_tier_transfer = builder.defineInRange("creative_tier_transfer", 8192, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("battery_box");
		wooden_battery_box_capacity = builder.defineInRange("wooden_battery_box_capacity", 40000, 1, Integer.MAX_VALUE);
		cesu_capacity = builder.defineInRange("cesu_capacity", 300000, 1, Integer.MAX_VALUE);
		mfe_capacity = builder.defineInRange("mfe_capacity", 4000000, 1, Integer.MAX_VALUE);
		mfsu_capacity = builder.defineInRange("mfsu_capacity", 40000000, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("generator");
		generator_energy_capacity = builder.defineInRange("generator_energy_capacity", 4000, 1, Integer.MAX_VALUE);
		generator_tick_generate = builder.defineInRange("generator_tick_generate", 10, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("geo_generator");
		geo_generator_energy_capacity = builder.defineInRange("geo_generator_energy_capacity", 2400, 1, Integer.MAX_VALUE);
		geo_generator_lava_capacity = builder.defineInRange("geo_generator_lava_capacity", 8000, 1, Integer.MAX_VALUE);
		geo_generator_tick_generate = builder.defineInRange("geo_generator_tick_generate", 20, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("semifluid_generator");
		semifluid_generator_energy_capacity = builder.defineInRange("semifluid_generator_energy_capacity", 8000, 1, Integer.MAX_VALUE);
		semifluid_generator_fluid_capacity = builder.defineInRange("semifluid_generator_fluid_capacity", 8000, 1, Integer.MAX_VALUE);
		semifluid_generator_tick_generate = builder.defineInRange("semifluid_generator_tick_generate", 25, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("solar_generator");
		solar_generator_energy_capacity = builder.defineInRange("solar_generator_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		solar_generator_day_tick_generate = builder.defineInRange("solar_generator_day_tick_generate", 1, 0, Integer.MAX_VALUE);
		solar_generator_night_tick_generate = builder.defineInRange("solar_generator_night_tick_generate", 0, 0, Integer.MAX_VALUE);

		advanced_solar_generator_energy_capacity = builder.defineInRange("advanced_solar_generator_energy_capacity", 2400, 1, Integer.MAX_VALUE);
		advanced_solar_generator_day_tick_generate = builder.defineInRange("advanced_solar_generator_day_tick_generate", 8, 0, Integer.MAX_VALUE);
		advanced_solar_generator_night_tick_generate = builder.defineInRange("advanced_solar_generator_night_tick_generate", 1, 0, Integer.MAX_VALUE);

		hybrid_solar_generator_energy_capacity = builder.defineInRange("hybrid_solar_generator_energy_capacity", 4800, 1, Integer.MAX_VALUE);
		hybrid_solar_generator_day_tick_generate = builder.defineInRange("hybrid_solar_generator_day_tick_generate", 64, 0, Integer.MAX_VALUE);
		hybrid_solar_generator_night_tick_generate = builder.defineInRange("hybrid_solar_generator_night_tick_generate", 8, 0, Integer.MAX_VALUE);

		quantum_solar_generator_energy_capacity = builder.defineInRange("quantum_solar_generator_energy_capacity", 9600, 1, Integer.MAX_VALUE);
		quantum_solar_generator_day_tick_generate = builder.defineInRange("quantum_solar_generator_day_tick_generate", 512, 0, Integer.MAX_VALUE);
		quantum_solar_generator_night_tick_generate = builder.defineInRange("quantum_solar_generator_night_tick_generate", 64, 0, Integer.MAX_VALUE);
		builder.pop();

		builder.push("crystalline_generator");
		crystalline_generator_energy_capacity = builder.defineInRange("crystalline_generator_energy_capacity", 9600, 1, Integer.MAX_VALUE);
		crystalline_generator_tick_generate = builder.defineInRange("crystalline_generator_tick_generate", 30, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("electric_furnace");
		electric_furnace_energy_capacity = builder.defineInRange("electric_furnace_energy_capacity", 4000, 1, Integer.MAX_VALUE);
		electric_furnace_tick_usage = builder.defineInRange("electric_furnace_tick_usage", 12, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("basic_machines");
		crusher_energy_capacity = builder.defineInRange("crusher_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		extractor_energy_capacity = builder.defineInRange("extractor_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		extruder_energy_capacity = builder.defineInRange("extruder_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		compressor_energy_capacity = builder.defineInRange("compressor_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		sawmill_energy_capacity = builder.defineInRange("sawmill_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		recycler_energy_capacity = builder.defineInRange("recycler_energy_capacity", 90, 1, Integer.MAX_VALUE);
		canning_machine_energy_capacity = builder.defineInRange("canning_machine_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("fluid_enricher");
		fluid_enricher_energy_capacity = builder.defineInRange("fluid_enricher_energy_capacity", 1200, 1, Integer.MAX_VALUE);
		fluid_enricher_fluid_capacity = builder.defineInRange("fluid_enricher_fluid_capacity", 8000, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("standard_machines");
		alloy_smelter_energy_capacity = builder.defineInRange("alloy_smelter_energy_capacity", 2400, 1, Integer.MAX_VALUE);
		alloy_smelter_heat_cost = builder.defineInRange("alloy_smelter_energy_heat_cost", 48, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("fermenter");
		fermenter_energy_capacity = builder.defineInRange("fermenter_energy_capacity", 2400, 1, Integer.MAX_VALUE);
		fermenter_fluid_input_capacity = builder.defineInRange("fermenter_fluid_input_capacity", 10000, 1, Integer.MAX_VALUE);
		fermenter_fluid_output_capacity = builder.defineInRange("fermenter_fluid_output_capacity", 4000, 1, Integer.MAX_VALUE);
		fermenter_heat_cost = builder.defineInRange("fermenter_heat_cost", 48, 1, Integer.MAX_VALUE);
		builder.pop();

		builder.push("cell");
		fluid_cell_capacity = builder.defineInRange("fluid_cell_capacity", 1000, 1, Integer.MAX_VALUE);
		foam_sprayer_capacity = builder.defineInRange("foam_sprayer_capacity", 8000, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("ore_washing_plant");
		ore_washing_plant_energy_capacity = builder.defineInRange("ore_washing_plant_energy_capacity", 16000, 1, Integer.MAX_VALUE);
		ore_washing_plant_fluid_capacity = builder.defineInRange("ore_washing_plant_fluid_capacity", 8000, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("matter_fabricator");
		matter_fabricator_energy_capacity = builder.defineInRange("matter_fabricator_energy_capacity", 1000000, 1, Integer.MAX_VALUE);
		matter_fabricator_matter_capacity = builder.defineInRange("matter_fabricator_matter_capacity", 10000, 1, Integer.MAX_VALUE);
		matter_fabricator_produce_run = builder.defineInRange("matter_fabricator_produce_run", 100, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("thermal_centrifuge");
		thermal_centrifuge_energy_capacity = builder.defineInRange("thermal_centrifuge_energy_capacity", 48000, 1, Integer.MAX_VALUE);
		thermal_centrifuge_temp_cost = builder.defineInRange("thermal_centrifuge_temp_cost", 48, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("scanner");
		scanner_energy_capacity = builder.defineInRange("scanner_energy_capacity", 1000000, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("replicator");
		replicator_energy_capacity = builder.defineInRange("replicator_energy_capacity", 1000000, 1, Integer.MAX_VALUE);
		replicator_matter_capacity = builder.defineInRange("replicator_matter_capacity", 100000, 1, Integer.MAX_VALUE);
		builder.pop();


		builder.push("metal_former");
		metal_former_energy_capacity = builder.defineInRange("metal_former_energy_capacity", 4000, 1, Integer.MAX_VALUE);
		builder.pop();

		return builder.build();
	}



}
