package com.maciej916.indreb.datagen.loottables;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlockEntities;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class ModLootTables extends LootTableProvider {

    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    private final DataGenerator generator;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public ModLootTables(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(CachedOutput cache) {
        Map<ResourceLocation, LootTable> tables = new HashMap<>();

        tables.put(ModBlocks.RUBBER_LOG.get().getLootTable(), createStandardTable("rubber_log", ModBlocks.RUBBER_LOG.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_WOOD.get().getLootTable(), createStandardTable("rubber_wood", ModBlocks.RUBBER_WOOD.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_LEAVES.get().getLootTable(), createOakLeavesDrops(ModBlocks.RUBBER_LEAVES.get(), ModBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SAPLING.get().getLootTable(), createStandardTable("rubber_sapling", ModBlocks.RUBBER_SAPLING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_PLANKS.get().getLootTable(), createStandardTable("rubber_planks", ModBlocks.RUBBER_PLANKS.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_STAIRS.get().getLootTable(), createStandardTable("rubber_stairs", ModBlocks.RUBBER_STAIRS.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SLAB.get().getLootTable(), createStandardTable("rubber_slab", ModBlocks.RUBBER_SLAB.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.TIN_ORE.get().getLootTable(), createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.DEEPSLATE_TIN_ORE.get().getLootTable(), createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.LEAD_ORE.get().getLootTable(), createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.DEEPSLATE_LEAD_ORE.get().getLootTable(), createOreDrop(ModBlocks.DEEPSLATE_LEAD_ORE.get(), ModItems.RAW_LEAD.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.URANIUM_ORE.get().getLootTable(), createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.DEEPSLATE_URANIUM_ORE.get().getLootTable(), createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SILVER_ORE.get().getLootTable(), createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.DEEPSLATE_SILVER_ORE.get().getLootTable(), createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.TIN_BLOCK.get().getLootTable(), createStandardTable("tin_block", ModBlocks.TIN_BLOCK.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SILVER_BLOCK.get().getLootTable(), createStandardTable("silver_block", ModBlocks.SILVER_BLOCK.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.BRONZE_BLOCK.get().getLootTable(), createStandardTable("bronze_block", ModBlocks.BRONZE_BLOCK.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.LEAD_BLOCK.get().getLootTable(), createStandardTable("lead_block", ModBlocks.LEAD_BLOCK.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.BASIC_MACHINE_CASING.get().getLootTable(), createStandardTable("basic_machine_casing", ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ADVANCED_MACHINE_CASING.get().getLootTable(), createStandardTable("advanced_machine_casing", ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.IRON_FURNACE.get().getLootTable(), createBlockEntityTable("iron_furnace", ModBlocks.IRON_FURNACE.get(), ModBlockEntities.IRON_FURNACE.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.COPPER_CABLE.get().getLootTable(), createBlockEntityTable("copper_cable", ModBlocks.COPPER_CABLE.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.COPPER_CABLE_INSULATED.get().getLootTable(), createBlockEntityTable("copper_cable_insulated", ModBlocks.COPPER_CABLE_INSULATED.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.TIN_CABLE.get().getLootTable(), createBlockEntityTable("tin_cable", ModBlocks.TIN_CABLE.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.TIN_CABLE_INSULATED.get().getLootTable(), createBlockEntityTable("tin_cable_insulated", ModBlocks.TIN_CABLE_INSULATED.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GOLD_CABLE.get().getLootTable(), createBlockEntityTable("gold_cable", ModBlocks.GOLD_CABLE.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GOLD_CABLE_INSULATED.get().getLootTable(), createBlockEntityTable("gold_cable_insulated", ModBlocks.GOLD_CABLE_INSULATED.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HV_CABLE.get().getLootTable(), createBlockEntityTable("hv_cable", ModBlocks.HV_CABLE.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HV_CABLE_INSULATED.get().getLootTable(), createBlockEntityTable("hv_cable_insulated", ModBlocks.HV_CABLE_INSULATED.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GLASS_FIBRE_CABLE.get().getLootTable(), createBlockEntityTable("glass_fibre_cable", ModBlocks.GLASS_FIBRE_CABLE.get(), ModBlockEntities.CABLE.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("generator", ModBlocks.GENERATOR.get(), ModBlockEntities.GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GEO_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("geothermal_generator", ModBlocks.GEO_GENERATOR.get(), ModBlockEntities.GEO_GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SOLAR_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("solar_generator", ModBlocks.SOLAR_GENERATOR.get(), ModBlockEntities.SOLAR_GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ADVANCED_SOLAR_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("advanced_solar_generator", ModBlocks.ADVANCED_SOLAR_GENERATOR.get(), ModBlockEntities.SOLAR_GENERATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HYBRID_SOLAR_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("hybrid_solar_generator", ModBlocks.HYBRID_SOLAR_GENERATOR.get(), ModBlockEntities.SOLAR_GENERATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.QUANTUM_SOLAR_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("quantum_solar_generator", ModBlocks.QUANTUM_SOLAR_GENERATOR.get(), ModBlockEntities.SOLAR_GENERATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SEMIFLUID_GENERATOR.get().getLootTable(), createBlockEntityTableBreakable("semifluid_generator", ModBlocks.SEMIFLUID_GENERATOR.get(), ModBlockEntities.SEMIFLUID_GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.ELECTRIC_FURNACE.get().getLootTable(), createBlockEntityTableBreakable("electric_furnace", ModBlocks.ELECTRIC_FURNACE.get(), ModBlockEntities.ELECTRIC_FURNACE.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CRUSHER.get().getLootTable(), createBlockEntityTableBreakable("crusher", ModBlocks.CRUSHER.get(), ModBlockEntities.CRUSHER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.COMPRESSOR.get().getLootTable(), createBlockEntityTableBreakable("compressor", ModBlocks.COMPRESSOR.get(), ModBlockEntities.COMPRESSOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.EXTRACTOR.get().getLootTable(), createBlockEntityTableBreakable("extractor", ModBlocks.EXTRACTOR.get(), ModBlockEntities.EXTRACTOR.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.EXTRUDER.get().getLootTable(), createBlockEntityTableBreakable("extruder", ModBlocks.EXTRUDER.get(), ModBlockEntities.EXTRUDER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SAWMILL.get().getLootTable(), createBlockEntityTableBreakable("sawmill", ModBlocks.SAWMILL.get(), ModBlockEntities.SAWMILL.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CANNING_MACHINE.get().getLootTable(), createBlockEntityTableBreakable("canning_machine", ModBlocks.CANNING_MACHINE.get(), ModBlockEntities.CANNING_MACHINE.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.FLUID_ENRICHER.get().getLootTable(), createBlockEntityTableBreakable("fluid_enricher", ModBlocks.FLUID_ENRICHER.get(), ModBlockEntities.FLUID_ENRICHER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RECYCLER.get().getLootTable(), createBlockEntityTableBreakable("recycler", ModBlocks.RECYCLER.get(), ModBlockEntities.RECYCLER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.FERMENTER.get().getLootTable(), createBlockEntityTableBreakable("fermenter", ModBlocks.FERMENTER.get(), ModBlockEntities.FERMENTER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ALLOY_SMELTER.get().getLootTable(), createBlockEntityTableBreakable("alloy_smelter", ModBlocks.ALLOY_SMELTER.get(), ModBlockEntities.ALLOY_SMELTER.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MATTER_FABRICATOR.get().getLootTable(), createBlockEntityTableBreakable("matter_fabricator", ModBlocks.MATTER_FABRICATOR.get(), ModBlockEntities.MATTER_FABRICATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ORE_WASHING_PLANT.get().getLootTable(), createBlockEntityTableBreakable("ore_washing_plant", ModBlocks.ORE_WASHING_PLANT.get(), ModBlockEntities.ORE_WASHING_PLANT.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.THERMAL_CENTRIFUGE.get().getLootTable(), createBlockEntityTableBreakable("thermal_centrifuge", ModBlocks.THERMAL_CENTRIFUGE.get(), ModBlockEntities.THERMAL_CENTRIFUGE.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.BATTERY_BOX.get().getLootTable(), createBlockEntityTable("battery_box", ModBlocks.BATTERY_BOX.get(), ModBlockEntities.BATTERY_BOX.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CESU.get().getLootTable(), createBlockEntityTable("cesu", ModBlocks.CESU.get(), ModBlockEntities.BATTERY_BOX.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MFE.get().getLootTable(), createBlockEntityTableBreakable("mfe", ModBlocks.MFE.get(), ModBlockEntities.BATTERY_BOX.get(), ModBlocks.BASIC_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MFSU.get().getLootTable(), createBlockEntityTableBreakable("mfsu", ModBlocks.MFSU.get(), ModBlockEntities.BATTERY_BOX.get(), ModBlocks.ADVANCED_MACHINE_CASING.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.RESIN_SHEET.get().getLootTable(), createStandardTable("resin_sheet", ModBlocks.RESIN_SHEET.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SHEET.get().getLootTable(), createStandardTable("rubber_sheet", ModBlocks.RUBBER_SHEET.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.REINFORCED_GLASS.get().getLootTable(), createStandardTable("reinforced_glass", ModBlocks.REINFORCED_GLASS.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE.get().getLootTable(), createStandardTable("reinforced_stone", ModBlocks.REINFORCED_STONE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE_SLAB.get().getLootTable(), createStandardTable("reinforced_stone_slab", ModBlocks.REINFORCED_STONE_SLAB.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE_STAIRS.get().getLootTable(), createStandardTable("reinforced_stone_stairs", ModBlocks.REINFORCED_STONE_STAIRS.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.IRON_SCAFFOLDING.get().getLootTable(), createStandardTable("IRON_SCAFFOLDING", ModBlocks.IRON_SCAFFOLDING.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.IRON_FENCE.get().getLootTable(), createStandardTable("IRON_FENCE", ModBlocks.IRON_FENCE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.LUMINATOR.get().getLootTable(), createStandardTable("luminator", ModBlocks.LUMINATOR.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.CHARGE_PAD_BATTERY_BOX.get().getLootTable(), createBlockEntityTableBreakable("charge_pad_battery_box", ModBlocks.CHARGE_PAD_BATTERY_BOX.get(), ModBlockEntities.CHARGE_PAD.get(), ModBlocks.BATTERY_BOX.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CHARGE_PAD_CESU.get().getLootTable(), createBlockEntityTableBreakable("charge_pad_cesu", ModBlocks.CHARGE_PAD_CESU.get(), ModBlockEntities.CHARGE_PAD.get(), ModBlocks.CESU.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CHARGE_PAD_MFE.get().getLootTable(), createBlockEntityTableBreakable("charge_pad_mfe", ModBlocks.CHARGE_PAD_MFE.get(), ModBlockEntities.CHARGE_PAD.get(), ModBlocks.MFE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CHARGE_PAD_MFSU.get().getLootTable(), createBlockEntityTableBreakable("charge_pad_mfsu", ModBlocks.CHARGE_PAD_MFSU.get(), ModBlockEntities.CHARGE_PAD.get(), ModBlocks.MFSU.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.LV_TRANSFORMER.get().getLootTable(), createStandardTable("lv_transformer", ModBlocks.LV_TRANSFORMER.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MV_TRANSFORMER.get().getLootTable(), createStandardTable("mv_transformer", ModBlocks.MV_TRANSFORMER.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HV_TRANSFORMER.get().getLootTable(), createStandardTable("hv_transformer", ModBlocks.HV_TRANSFORMER.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.EV_TRANSFORMER.get().getLootTable(), createStandardTable("ev_transformer", ModBlocks.EV_TRANSFORMER.get()).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.get().getLootTable(), createStandardTable("construction_foam_wall_white", ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_RED.get().getLootTable(), createStandardTable("construction_foam_wall_red", ModBlocks.CONSTRUCTION_FOAM_WALL_RED.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.get().getLootTable(), createStandardTable("construction_foam_wall_orange", ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.get().getLootTable(), createStandardTable("construction_foam_wall_pink", ModBlocks.CONSTRUCTION_FOAM_WALL_PINK.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.get().getLootTable(), createStandardTable("construction_foam_wall_yellow", ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.get().getLootTable(), createStandardTable("construction_foam_wall_lime", ModBlocks.CONSTRUCTION_FOAM_WALL_LIME.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.get().getLootTable(), createStandardTable("construction_foam_wall_green", ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.get().getLootTable(), createStandardTable("construction_foam_wall_light_blue", ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.get().getLootTable(), createStandardTable("construction_foam_wall_cyan", ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.get().getLootTable(), createStandardTable("construction_foam_wall_blue", ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.get().getLootTable(), createStandardTable("construction_foam_wall_magenta", ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.get().getLootTable(), createStandardTable("construction_foam_wall_purple", ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.get().getLootTable(), createStandardTable("construction_foam_wall_brown", ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.get().getLootTable(), createStandardTable("construction_foam_wall_gray", ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.get().getLootTable(), createStandardTable("construction_foam_wall_light_gray", ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.get().getLootTable(), createStandardTable("construction_foam_wall_black", ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK.get()).setParamSet(LootContextParamSets.BLOCK).build());

        writeTables(cache, tables);
    }

    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createBlockEntityTable(String name, Block block, BlockEntityType<?> type) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
//                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
//                                .copy("inv", "BlockEntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE)
//                                .copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE))
                        .apply(SetContainerContents.setContents(type)
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createBlockEntityTableBreakable(String name, Block block, BlockEntityType<?> type, Block breaksTo) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                                .setWeight(80)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(SetContainerContents.setContents(type)
                                        .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                )
                .add(LootItem.lootTableItem(breaksTo)
                        .setWeight(20)
                        .apply(SetContainerContents.setContents(type)
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }


    protected static LootTable.Builder createOreDrop(Block p_124140_, Item p_124141_) {
        return createSilkTouchDispatchTable(p_124140_, applyExplosionDecay(p_124140_, LootItem.lootTableItem(p_124141_).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected static LootTable.Builder createSilkTouchDispatchTable(Block p_124169_, LootPoolEntryContainer.Builder<?> p_124170_) {
        return createSelfDropDispatchTable(p_124169_, HAS_SILK_TOUCH, p_124170_);
    }


    protected static LootTable.Builder createOakLeavesDrops(Block pOakLeavesBlock, Block pSaplingBlock, float... pChances) {
        return createLeavesDrops(pOakLeavesBlock, pSaplingBlock, pChances).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionCondition(pOakLeavesBlock, LootItem.lootTableItem(Items.APPLE)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    protected static LootTable.Builder createLeavesDrops(Block pLeavesBlock, Block pSaplingBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(pSaplingBlock)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder createSilkTouchOrShearsDispatchTable(Block pBlock, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        return createSelfDropDispatchTable(pBlock, HAS_SHEARS_OR_SILK_TOUCH, pAlternativeEntryBuilder);
    }

    protected static LootTable.Builder createSelfDropDispatchTable(Block pBlock, LootItemCondition.Builder pConditionBuilder, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder).otherwise(pAlternativeEntryBuilder)));
    }

    protected static <T extends FunctionUserBuilder<T>> T applyExplosionDecay(ItemLike pItem, FunctionUserBuilder<T> pFunction) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pFunction.apply(ApplyExplosionDecay.explosionDecay()) : pFunction.unwrap());
    }

    protected static <T extends ConditionUserBuilder<T>> T applyExplosionCondition(ItemLike pItem, ConditionUserBuilder<T> pCondition) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pCondition.when(ExplosionCondition.survivesExplosion()) : pCondition.unwrap());
    }

    private void writeTables(CachedOutput cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.saveStable(cache, LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                IndReb.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }
    @Override
    public String getName() {
        return "Industrial Reborn tables";
    }
}