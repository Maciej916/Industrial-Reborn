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
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
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
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class LootTables extends LootTableProvider {

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

    public LootTables(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(HashCache cache) {
        Map<ResourceLocation, LootTable> tables = new HashMap<>();

        tables.put(ModBlocks.RUBBER_LOG.getLootTable(), createStandardTable("rubber_log", ModBlocks.RUBBER_LOG).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_WOOD.getLootTable(), createStandardTable("rubber_wood", ModBlocks.RUBBER_WOOD).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_LEAVES.getLootTable(), createOakLeavesDrops(ModBlocks.RUBBER_LEAVES, ModBlocks.RUBBER_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SAPLING.getLootTable(), createStandardTable("rubber_sapling", ModBlocks.RUBBER_SAPLING).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_PLANKS.getLootTable(), createStandardTable("rubber_planks", ModBlocks.RUBBER_PLANKS).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_STAIRS.getLootTable(), createStandardTable("rubber_stairs", ModBlocks.RUBBER_STAIRS).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SLAB.getLootTable(), createStandardTable("rubber_slab", ModBlocks.RUBBER_SLAB).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.TIN_ORE.getLootTable(), createOreDrop(ModBlocks.TIN_ORE, ModItems.RAW_TIN).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.DEEPSLATE_TIN_ORE.getLootTable(), createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAW_TIN).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.TIN_BLOCK.getLootTable(), createStandardTable("tin_block", ModBlocks.TIN_BLOCK).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SILVER_BLOCK.getLootTable(), createStandardTable("silver_block", ModBlocks.SILVER_BLOCK).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.BRONZE_BLOCK.getLootTable(), createStandardTable("bronze_block", ModBlocks.BRONZE_BLOCK).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.BASIC_MACHINE_CASING.getLootTable(), createStandardTable("basic_machine_casing", ModBlocks.BASIC_MACHINE_CASING).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ADVANCED_MACHINE_CASING.getLootTable(), createStandardTable("advanced_machine_casing", ModBlocks.ADVANCED_MACHINE_CASING).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.IRON_FURNACE.getLootTable(), createBlockEntityTable("iron_furnace", ModBlocks.IRON_FURNACE, ModBlockEntities.IRON_FURNACE).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.COPPER_CABLE.getLootTable(), createBlockEntityTable("copper_cable", ModBlocks.COPPER_CABLE, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.COPPER_CABLE_INSULATED.getLootTable(), createBlockEntityTable("copper_cable_insulated", ModBlocks.COPPER_CABLE_INSULATED, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.TIN_CABLE.getLootTable(), createBlockEntityTable("tin_cable", ModBlocks.TIN_CABLE, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.TIN_CABLE_INSULATED.getLootTable(), createBlockEntityTable("tin_cable_insulated", ModBlocks.TIN_CABLE_INSULATED, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GOLD_CABLE.getLootTable(), createBlockEntityTable("gold_cable", ModBlocks.GOLD_CABLE, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GOLD_CABLE_INSULATED.getLootTable(), createBlockEntityTable("gold_cable_insulated", ModBlocks.GOLD_CABLE_INSULATED, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HV_CABLE.getLootTable(), createBlockEntityTable("hv_cable", ModBlocks.HV_CABLE, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HV_CABLE_INSULATED.getLootTable(), createBlockEntityTable("hv_cable_insulated", ModBlocks.HV_CABLE_INSULATED, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GLASS_FIBRE_CABLE.getLootTable(), createBlockEntityTable("glass_fibre_cable", ModBlocks.GLASS_FIBRE_CABLE, ModBlockEntities.CABLE).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.GENERATOR.getLootTable(), createBlockEntityTable("generator", ModBlocks.GENERATOR, ModBlockEntities.GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.GEO_GENERATOR.getLootTable(), createBlockEntityTable("geothermal_generator", ModBlocks.GEO_GENERATOR, ModBlockEntities.GEO_GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SOLAR_GENERATOR.getLootTable(), createBlockEntityTable("solar_generator", ModBlocks.SOLAR_GENERATOR, ModBlockEntities.SOLAR_GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ADVANCED_SOLAR_GENERATOR.getLootTable(), createBlockEntityTable("advanced_solar_generator", ModBlocks.ADVANCED_SOLAR_GENERATOR, ModBlockEntities.SOLAR_GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.HYBRID_SOLAR_GENERATOR.getLootTable(), createBlockEntityTable("hybrid_solar_generator", ModBlocks.HYBRID_SOLAR_GENERATOR, ModBlockEntities.SOLAR_GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.QUANTUM_SOLAR_GENERATOR.getLootTable(), createBlockEntityTable("quantum_solar_generator", ModBlocks.QUANTUM_SOLAR_GENERATOR, ModBlockEntities.SOLAR_GENERATOR).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.ELECTRIC_FURNACE.getLootTable(), createBlockEntityTable("electric_furnace", ModBlocks.ELECTRIC_FURNACE, ModBlockEntities.ELECTRIC_FURNACE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CRUSHER.getLootTable(), createBlockEntityTable("crusher", ModBlocks.CRUSHER, ModBlockEntities.CRUSHER).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.COMPRESSOR.getLootTable(), createBlockEntityTable("compressor", ModBlocks.COMPRESSOR, ModBlockEntities.COMPRESSOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.EXTRACTOR.getLootTable(), createBlockEntityTable("extractor", ModBlocks.EXTRACTOR, ModBlockEntities.EXTRACTOR).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.EXTRUDER.getLootTable(), createBlockEntityTable("extruder", ModBlocks.EXTRUDER, ModBlockEntities.EXTRUDER).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.SAWMILL.getLootTable(), createBlockEntityTable("sawmill", ModBlocks.SAWMILL, ModBlockEntities.SAWMILL).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.ALLOY_SMELTER.getLootTable(), createBlockEntityTable("alloy_smelter", ModBlocks.ALLOY_SMELTER, ModBlockEntities.ALLOY_SMELTER).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.BATTERY_BOX.getLootTable(), createBlockEntityTable("battery_box", ModBlocks.BATTERY_BOX, ModBlockEntities.BATTERY_BOX).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.CESU.getLootTable(), createBlockEntityTable("cesu", ModBlocks.CESU, ModBlockEntities.BATTERY_BOX).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MFE.getLootTable(), createBlockEntityTable("mfe", ModBlocks.MFE, ModBlockEntities.BATTERY_BOX).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.MFSU.getLootTable(), createBlockEntityTable("mfsu", ModBlocks.MFSU, ModBlockEntities.BATTERY_BOX).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.RESIN_SHEET.getLootTable(), createStandardTable("resin_sheet", ModBlocks.RESIN_SHEET).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.RUBBER_SHEET.getLootTable(), createStandardTable("rubber_sheet", ModBlocks.RUBBER_SHEET).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.REINFORCED_GLASS.getLootTable(), createStandardTable("reinforced_glass", ModBlocks.REINFORCED_GLASS).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE.getLootTable(), createStandardTable("reinforced_stone", ModBlocks.REINFORCED_STONE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE_SLAB.getLootTable(), createStandardTable("reinforced_stone_slab", ModBlocks.REINFORCED_STONE_SLAB).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.REINFORCED_STONE_STAIRS.getLootTable(), createStandardTable("reinforced_stone_stairs", ModBlocks.REINFORCED_STONE_STAIRS).setParamSet(LootContextParamSets.BLOCK).build());

        tables.put(ModBlocks.IRON_SCAFFOLDING.getLootTable(), createStandardTable("IRON_SCAFFOLDING", ModBlocks.IRON_SCAFFOLDING).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.IRON_FENCE.getLootTable(), createStandardTable("IRON_FENCE", ModBlocks.IRON_FENCE).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(ModBlocks.LUMINATOR.getLootTable(), createStandardTable("luminator", ModBlocks.LUMINATOR).setParamSet(LootContextParamSets.BLOCK).build());







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
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("inv", "BlockEntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE)
                                .copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE))
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

    protected static <T> T applyExplosionDecay(ItemLike pItem, FunctionUserBuilder<T> pFunction) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pFunction.apply(ApplyExplosionDecay.explosionDecay()) : pFunction.unwrap());
    }

    protected static <T> T applyExplosionCondition(ItemLike pItem, ConditionUserBuilder<T> pCondition) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pCondition.when(ExplosionCondition.survivesExplosion()) : pCondition.unwrap());
    }


    private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable), path);
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