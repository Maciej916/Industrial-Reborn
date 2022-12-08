package com.maciej916.indreb.datagen.loot;

import com.google.common.collect.ImmutableSet;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.blockentity.ModBlockEntities;
import com.maciej916.indreb.common.item.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

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
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    private final DataGenerator generator;
    private final Map<ResourceLocation, LootTable> tables = new HashMap<>();

    public LootTables(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(CachedOutput cache) {

        registerOres();
        registerRawBlock();
        registerMaterial();
        registerCasing();
        registerSheets();
        registerConstructionFoam();
        registerRubberWood();
        registerIron();
        registerCables();
        registerDecoration();
        registerGenerators();
        registerMisc();
        registerExplosives();
        registerSimpleMachines();
        registerEnergyStorage();
        registerChargePad();
        registerTransformer();
        registerBasicMachines();
        registerStandardMachines();
        registerAdvancedMachines();
        registerSuperMachines();

        writeTables(cache);
    }

    private void registerOres() {
        dropOre(ModBlocks.TIN_ORE, ModItems.RAW_TIN);
        dropOre(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAW_TIN);
        dropOre(ModBlocks.LEAD_ORE, ModItems.RAW_LEAD);
        dropOre(ModBlocks.DEEPSLATE_LEAD_ORE, ModItems.RAW_LEAD);
        dropOre(ModBlocks.URANIUM_ORE, ModItems.RAW_URANIUM);
        dropOre(ModBlocks.DEEPSLATE_URANIUM_ORE, ModItems.RAW_URANIUM);
        dropOre(ModBlocks.SILVER_ORE, ModItems.RAW_SILVER);
        dropOre(ModBlocks.DEEPSLATE_SILVER_ORE, ModItems.RAW_SILVER);
    }

    private void registerRawBlock() {
        dropSelf(ModBlocks.RAW_TIN_BLOCK);
        dropSelf(ModBlocks.RAW_LEAD_BLOCK);
        dropSelf(ModBlocks.RAW_URANIUM_BLOCK);
        dropSelf(ModBlocks.RAW_SILVER_BLOCK);
    }

    private void registerMaterial() {
        dropSelf(ModBlocks.TIN_BLOCK);
        dropSelf(ModBlocks.LEAD_BLOCK);
        dropSelf(ModBlocks.URANIUM_BLOCK);
        dropSelf(ModBlocks.SILVER_BLOCK);
        dropSelf(ModBlocks.STEEL_BLOCK);
        dropSelf(ModBlocks.BRONZE_BLOCK);
    }

    private void registerCasing() {
        dropSelf(ModBlocks.BASIC_MACHINE_CASING);
        dropSelf(ModBlocks.ADVANCED_MACHINE_CASING);
    }

    private void registerSheets() {
        dropSelf(ModBlocks.RUBBER_BLOCK);
        dropSelf(ModBlocks.RESIN_BLOCK);
        dropLayers(ModBlocks.RUBBER_SHEET, ModItems.RUBBER, ModBlocks.RUBBER_BLOCK);
        dropLayers(ModBlocks.RESIN_SHEET, ModItems.STICKY_RESIN, ModBlocks.RESIN_BLOCK);
    }

    private void registerConstructionFoam() {
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_WHITE);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_RED);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_ORANGE);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_PINK);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_YELLOW);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_LIME);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_GREEN);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_BLUE);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_CYAN);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_BLUE);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_MAGENTA);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_PURPLE);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_BROWN);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_GRAY);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_LIGHT_GRAY);
        dropSelf(ModBlocks.CONSTRUCTION_FOAM_WALL_BLACK);

        dropSelf(ModBlocks.REINFORCED_GLASS);
        dropSelf(ModBlocks.REINFORCED_STONE);
        dropSelf(ModBlocks.REINFORCED_STONE_SLAB);
        dropSelf(ModBlocks.REINFORCED_STONE_STAIRS);
        dropSelf(ModBlocks.REINFORCED_STONE_BRICKS);
        dropSelf(ModBlocks.SMOOTH_REINFORCED_STONE);
        dropSelf(ModBlocks.REINFORCED_STONE_BRICK_WALL);
        dropDoors(ModBlocks.REINFORCED_DOOR);
    }

    private void registerRubberWood() {
        dropSelf(ModBlocks.RUBBER_LOG);
        dropSelf(ModBlocks.RUBBER_WOOD);
        dropLeaves(ModBlocks.RUBBER_LEAVES, ModBlocks.RUBBER_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES);
        dropSelf(ModBlocks.RUBBER_PLANKS);
        dropSelf(ModBlocks.RUBBER_SAPLING);
        dropSelf(ModBlocks.RUBBER_STAIRS);
        dropSelf(ModBlocks.RUBBER_SLAB);
        dropSelf(ModBlocks.RUBBER_PLATE);
        dropSelf(ModBlocks.RUBBER_GATE);
        dropSelf(ModBlocks.RUBBER_FENCE);
        dropDoors(ModBlocks.RUBBER_DOOR);
        dropSelf(ModBlocks.RUBBER_TRAP_DOOR);
    }

    private void registerIron() {
        dropSelf(ModBlocks.IRON_FENCE);
        dropSelf(ModBlocks.IRON_GATE);
        dropSelf(ModBlocks.IRON_SCAFFOLDING);
    }

    protected void registerCables() {
        dropSelf(ModBlocks.TIN_CABLE);
        dropSelf(ModBlocks.TIN_CABLE_INSULATED);
        dropSelf(ModBlocks.COPPER_CABLE);
        dropSelf(ModBlocks.COPPER_CABLE_INSULATED);
        dropSelf(ModBlocks.GOLD_CABLE);
        dropSelf(ModBlocks.GOLD_CABLE_INSULATED);
        dropSelf(ModBlocks.HV_CABLE);
        dropSelf(ModBlocks.HV_CABLE_INSULATED);
        dropSelf(ModBlocks.GLASS_FIBRE_CABLE);
    }

    private void registerDecoration() {
        dropSelf(ModBlocks.YELLOW_STRIPES_BLOCK_LEFT);
        dropSelf(ModBlocks.YELLOW_STRIPES_BLOCK_RIGHT);
        dropSelf(ModBlocks.RADIOACTIVE_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.BIO_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.EXPLOSION_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.FIRE_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.ACID_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.MAGIC_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.FROST_HAZARD_SIGN_BLOCK);
        dropSelf(ModBlocks.NOISE_HAZARD_SIGN_BLOCK);
    }

    private void registerGenerators() {
        dropBreakable(ModBlocks.GENERATOR, ModBlockEntities.GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.SOLAR_PANEL, ModBlockEntities.SOLAR_PANEL.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.ADVANCED_SOLAR_PANEL, ModBlockEntities.SOLAR_PANEL.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.HYBRID_SOLAR_PANEL, ModBlockEntities.SOLAR_PANEL.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.QUANTUM_SOLAR_PANEL, ModBlockEntities.SOLAR_PANEL.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.GEO_GENERATOR, ModBlockEntities.GEO_GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.SEMIFLUID_GENERATOR, ModBlockEntities.SEMIFLUID_GENERATOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);

        dropBreakable(ModBlocks.NUCLEAR_REACTOR, ModBlockEntities.NUCLEAR_REACTOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropNotBreakable(ModBlocks.REACTOR_CHAMBER, ModBlockEntities.REACTOR_PART.get());
        dropNotBreakable(ModBlocks.REACTOR_CONTROL_ROD, ModBlockEntities.REACTOR_PART.get());
        dropNotBreakable(ModBlocks.REACTOR_FRAME, ModBlockEntities.REACTOR_PART.get());
    }

    private void registerMisc() {
        dropSelf(ModBlocks.PATTERN_STORAGE);
        dropSelf(ModBlocks.TELEPORT_ANCHOR);
    }

    private void registerExplosives() {
        dropSelf(ModBlocks.INDUSTRIAL_TNT);
        dropSelf(ModBlocks.NUKE);
    }

    private void registerSimpleMachines() {
        dropSelf(ModBlocks.IRON_FURNACE);
        dropSelf(ModBlocks.SIMPLE_CRUSHER);
        dropSelf(ModBlocks.SIMPLE_COMPRESSOR);
        dropSelf(ModBlocks.SIMPLE_EXTRACTOR);
    }

    private void registerEnergyStorage() {
        dropNotBreakable(ModBlocks.BATTERY_BOX, ModBlockEntities.BATTERY_BOX.get());
        dropNotBreakable(ModBlocks.CESU, ModBlockEntities.BATTERY_BOX.get());
        dropBreakable(ModBlocks.MFE, ModBlockEntities.BATTERY_BOX.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.MFSU, ModBlockEntities.BATTERY_BOX.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
    }

    private void registerChargePad() {
        dropBreakable(ModBlocks.CHARGE_PAD_BATTERY_BOX, ModBlockEntities.CHARGE_PAD.get(), ModBlocks.BATTERY_BOX, 80);
        dropBreakable(ModBlocks.CHARGE_PAD_CESU, ModBlockEntities.CHARGE_PAD.get(), ModBlocks.CESU, 80);
        dropBreakable(ModBlocks.CHARGE_PAD_MFE, ModBlockEntities.CHARGE_PAD.get(), ModBlocks.MFE, 80);
        dropBreakable(ModBlocks.CHARGE_PAD_MFSU, ModBlockEntities.CHARGE_PAD.get(), ModBlocks.MFSU, 80);
    }

    private void registerTransformer() {
        dropSelf(ModBlocks.LV_TRANSFORMER);
        dropSelf(ModBlocks.MV_TRANSFORMER);
        dropSelf(ModBlocks.HV_TRANSFORMER);
        dropSelf(ModBlocks.EV_TRANSFORMER);
    }

    private void registerBasicMachines() {
        dropBreakable(ModBlocks.ELECTRIC_FURNACE, ModBlockEntities.ELECTRIC_FURNACE.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.CRUSHER, ModBlockEntities.CRUSHER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.COMPRESSOR, ModBlockEntities.COMPRESSOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.EXTRACTOR, ModBlockEntities.EXTRACTOR.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.SAWMILL, ModBlockEntities.SAWMILL.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.EXTRUDER, ModBlockEntities.EXTRUDER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.CANNING_MACHINE, ModBlockEntities.CANNING_MACHINE.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.FLUID_ENRICHER, ModBlockEntities.FLUID_ENRICHER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.RECYCLER, ModBlockEntities.RECYCLER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.METAL_FORMER, ModBlockEntities.METAL_FORMER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
    }

    private void registerStandardMachines() {
        dropBreakable(ModBlocks.ALLOY_SMELTER, ModBlockEntities.ALLOY_SMELTER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.FERMENTER, ModBlockEntities.FERMENTER.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.ORE_WASHING_PLANT, ModBlockEntities.ORE_WASHING_PLANT.get(), ModBlocks.BASIC_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.THERMAL_CENTRIFUGE, ModBlockEntities.THERMAL_CENTRIFUGE.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
    }

    private void registerAdvancedMachines() {
        dropBreakable(ModBlocks.MATTER_FABRICATOR, ModBlockEntities.MATTER_FABRICATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
    }

    private void registerSuperMachines() {
        dropBreakable(ModBlocks.SCANNER, ModBlockEntities.SCANNER.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
        dropBreakable(ModBlocks.REPLICATOR, ModBlockEntities.REPLICATOR.get(), ModBlocks.ADVANCED_MACHINE_CASING, 80);
    }












    private void dropSelf(RegistryObject<Block> block) {
        tables.put(block.get().getLootTable(), createStandardTable(block).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropLeaves(RegistryObject<Block> leaves, RegistryObject<Block> sappling, float... chances) {
        tables.put(leaves.get().getLootTable(), createOakLeavesDrops(leaves, sappling, chances).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropDoors(RegistryObject<Block> block) {
        tables.put(block.get().getLootTable(), createDoorTable(block).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropOre(RegistryObject<Block> block, RegistryObject<Item> item) {
        tables.put(block.get().getLootTable(), createOreDrop(block.get(), item.get()).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropLayers(RegistryObject<Block> block, RegistryObject<Item> item, RegistryObject<Block> fullBlock) {
        tables.put(block.get().getLootTable(),LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS)).add(AlternativesEntry.alternatives(AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(), (p_236238_) -> LootItem.lootTableItem(item.get()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SnowLayerBlock.LAYERS, p_236238_))).apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) p_236238_)))).when(HAS_NO_SILK_TOUCH), AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(), (p_236235_) -> p_236235_ == 8 ? LootItem.lootTableItem(fullBlock.get()) : LootItem.lootTableItem(block.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) p_236235_))).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SnowLayerBlock.LAYERS, p_236235_))))))).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropBreakable(RegistryObject<Block> block, BlockEntityType<?> blockEntityType, RegistryObject<Block> breakTo, int chance) {
        tables.put(block.get().getLootTable(), createBlockEntityTableBreakable(block.getId().getPath(), block.get(), blockEntityType, breakTo.get(), chance).setParamSet(LootContextParamSets.BLOCK).build());
    }

    private void dropNotBreakable(RegistryObject<Block> block, BlockEntityType<?> blockEntityType) {
        tables.put(block.get().getLootTable(), createBlockEntityTable(block.getId().getPath(), block.get(), blockEntityType).setParamSet(LootContextParamSets.BLOCK).build());
    }




    protected LootTable.Builder createStandardTable(RegistryObject<Block> block) {
        LootPool.Builder builder = LootPool.lootPool().name(block.getId().getPath()).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block.get()));
        return LootTable.lootTable().withPool(builder);
    }


    protected static LootTable.Builder createOakLeavesDrops(RegistryObject<Block> pOakLeavesBlock, RegistryObject<Block> pSaplingBlock, float... pChances) {
        return createLeavesDrops(pOakLeavesBlock.get(), pSaplingBlock.get(), pChances).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionCondition(pOakLeavesBlock.get(), LootItem.lootTableItem(Items.APPLE)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
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

    public static LootTable.Builder createDoorTable(RegistryObject<Block> block) {
        return createSinglePropConditionTable(block.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    protected static <T extends Comparable<T> & StringRepresentable> LootTable.Builder createSinglePropConditionTable(Block pBlock, Property<T> pProperty, T pValue) {
        return LootTable.lootTable().withPool(applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pProperty, pValue))))));
    }

    protected static LootTable.Builder createOreDrop(Block block, Item item) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(item).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected static LootTable.Builder createSilkTouchDispatchTable(Block block, LootPoolEntryContainer.Builder<?> builder) {
        return createSelfDropDispatchTable(block, HAS_SILK_TOUCH, builder);
    }

    protected LootTable.Builder createBlockEntityTableBreakable(String name, Block block, BlockEntityType<?> type, Block breaksTo, int chance) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .setWeight(chance)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(SetContainerContents.setContents(type)
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                )
                .add(LootItem.lootTableItem(breaksTo)
                        .setWeight(100 - chance)
//                        .apply(SetContainerContents.setContents(type)
//                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createBlockEntityTable(String name, Block block, BlockEntityType<?> type) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(SetContainerContents.setContents(type)
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    private void writeTables(CachedOutput cache) {
        Path outputFolder = this.generator.getOutputFolder();
        this.tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.saveStable(cache, net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                IndReb.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "Industrial Reborn Loot Tables";
    }
}
