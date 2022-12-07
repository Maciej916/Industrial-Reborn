package com.maciej916.indreb.common.block;

import com.google.common.base.Supplier;
import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.block.*;
import com.maciej916.indreb.common.api.tier.*;
import com.maciej916.indreb.common.block.impl.battery_box.BlockBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.charge_pad.BlockChargePad;
import com.maciej916.indreb.common.block.impl.explosive.industrial_tnt.BlockIndustrialTNT;
import com.maciej916.indreb.common.block.impl.explosive.nuke.BlockNuke;
import com.maciej916.indreb.common.block.impl.foam.FoamBlock;
import com.maciej916.indreb.common.block.impl.foam.ReinforcedFoamBlock;
import com.maciej916.indreb.common.block.impl.generator.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.generator.geo_generator.BlockGeoGenerator;
import com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor.BlockNuclearReactor;
import com.maciej916.indreb.common.block.impl.generator.reactor.ractor_frame.BlockReactorFrame;
import com.maciej916.indreb.common.block.impl.generator.reactor.reactor_chamber.BlockReactorChamber;
import com.maciej916.indreb.common.block.impl.generator.reactor.reactor_control_rod.BlockReactorControlRod;
import com.maciej916.indreb.common.block.impl.generator.semifluid_generator.BlockSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generator.solar_panel.BlockSolarPanel;
import com.maciej916.indreb.common.block.impl.iron.BlockIronFence;
import com.maciej916.indreb.common.block.impl.iron.BlockIronGate;
import com.maciej916.indreb.common.block.impl.iron.BlockIronScaffolding;
import com.maciej916.indreb.common.block.impl.machine.t_super.scanner.BlockScanner;
import com.maciej916.indreb.common.block.impl.machine.advanced.matter_fabricator.BlockMatterFabricator;
import com.maciej916.indreb.common.block.impl.machine.basic.metal_former.BlockMetalFormer;
import com.maciej916.indreb.common.block.impl.machine.standard.alloy_smelter.BlockAlloySmelter;
import com.maciej916.indreb.common.block.impl.machine.basic.canning_machine.BlockCanningMachine;
import com.maciej916.indreb.common.block.impl.machine.basic.compressor.BlockCompressor;
import com.maciej916.indreb.common.block.impl.machine.basic.crusher.BlockCrusher;
import com.maciej916.indreb.common.block.impl.machine.basic.electric_furnace.BlockElectricFurnace;
import com.maciej916.indreb.common.block.impl.machine.basic.extractor.BlockExtractor;
import com.maciej916.indreb.common.block.impl.machine.basic.extruder.BlockExtruder;
import com.maciej916.indreb.common.block.impl.machine.basic.fluid_enricher.BlockFluidEnricher;
import com.maciej916.indreb.common.block.impl.machine.basic.recycler.BlockRecycler;
import com.maciej916.indreb.common.block.impl.machine.basic.sawmill.BlockSawmill;
import com.maciej916.indreb.common.block.impl.machine.simple.iron_furnace.BlockIronFurnace;
import com.maciej916.indreb.common.block.impl.machine.simple.simple_compressor.BlockSimpleCompressor;
import com.maciej916.indreb.common.block.impl.machine.simple.simple_crusher.BlockSimpleCrusher;
import com.maciej916.indreb.common.block.impl.machine.simple.simple_extractor.BlockSimpleExtractor;
import com.maciej916.indreb.common.block.impl.machine.standard.fermenter.BlockFermenter;
import com.maciej916.indreb.common.block.impl.machine.standard.ore_washing_plant.BlockOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machine.standard.thermal_centrifuge.BlockThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.misc.luminator.BlockLuminator;
import com.maciej916.indreb.common.block.impl.misc.pattern_storage.BlockPatternStorage;
import com.maciej916.indreb.common.block.impl.reinforced.*;
import com.maciej916.indreb.common.block.impl.transformer.BlockTransformer;
import com.maciej916.indreb.common.block.impl.wood.*;
import com.maciej916.indreb.common.world.rubber_tree.RubberTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndReb.MODID);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new BlockOre(Material.STONE, 3f, 6F));
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", () -> new BlockOre(Material.STONE, 3f, 5F));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", () -> new BlockOre(Material.STONE, 3f, 6F));

    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = registerBlock("raw_uranium_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> RUBBER_LOG = registerBlock("rubber_log", RubberLog::new);
    public static final RegistryObject<Block> RUBBER_WOOD = registerBlock("rubber_wood", RubberWood::new);
    public static final RegistryObject<Block> RUBBER_LEAVES = registerBlock("rubber_leaves", RubberLeaves::new);
    public static final RegistryObject<Block> RUBBER_PLANKS = registerBlock("rubber_planks", RubberPlanks::new);
    public static final RegistryObject<Block> RUBBER_SAPLING = registerBlock("rubber_sapling", () -> new RubberSapling(new RubberTreeGrower()));
    public static final RegistryObject<Block> RUBBER_STAIRS = registerBlock("rubber_stairs", RubberStairs::new);
    public static final RegistryObject<Block> RUBBER_SLAB = registerBlock("rubber_slab", RubberSlab::new);
    public static final RegistryObject<Block> RUBBER_PLATE = registerBlock("rubber_plate", RubberPlate::new);
    public static final RegistryObject<Block> RUBBER_FENCE = registerBlock("rubber_fence", RubberFence::new);
    public static final RegistryObject<Block> RUBBER_GATE = registerBlock("rubber_gate", RubberGate::new);
    public static final RegistryObject<Block> RUBBER_DOOR = registerBlock("rubber_door", RubberDoor::new);
    public static final RegistryObject<Block> RUBBER_TRAP_DOOR = registerBlock("rubber_trap_door", RubberTrapDoor::new);
//    public static final RegistryObject<Block> RUBBER_SIGN = registerBlock("rubber_sign", RubberStandingSign::new);
//    public static final RegistryObject<Block> RUBBER_WALL_SIGN = registerBlock("rubber_wall_sign", RubberWallSign::new);

    public static final RegistryObject<Block> RESIN_SHEET = registerBlock("resin_sheet", () -> new BlockSheetLayer(Material.METAL, MaterialColor.COLOR_ORANGE, 0.5F, 0.7f, SoundType.MUD));
    public static final RegistryObject<Block> RUBBER_SHEET = registerBlock("rubber_sheet", () -> new BlockSheetLayer(Material.METAL, MaterialColor.COLOR_BLACK, 1f, 1.2f, SoundType.WOOL));
    public static final RegistryObject<Block> RESIN_BLOCK = registerBlock("resin_block", () -> new BlockSheet(Material.METAL, MaterialColor.COLOR_ORANGE, 0.5F, 0.7f, SoundType.MUD));
    public static final RegistryObject<Block> RUBBER_BLOCK = registerBlock("rubber_block", () -> new BlockSheet(Material.METAL, MaterialColor.COLOR_BLACK, 1f, 1.2f, SoundType.WOOL));

    public static final RegistryObject<Block> BASIC_MACHINE_CASING = registerBlock("basic_machine_casing", () -> new BlockResource(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY,1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> ADVANCED_MACHINE_CASING = registerBlock("advanced_machine_casing", () -> new BlockResource(Material.METAL, MaterialColor.COLOR_GRAY, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> CONSTRUCTION_FOAM = registerBlock("construction_foam", FoamBlock::new);
    public static final RegistryObject<Block> REINFORCED_CONSTRUCTION_FOAM = registerBlock("reinforced_construction_foam", ReinforcedFoamBlock::new);

    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_WHITE = registerBlock("construction_foam_wall_white", () -> new BlockFoamWall(MaterialColor.WOOL));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_RED = registerBlock("construction_foam_wall_red", () -> new BlockFoamWall(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_ORANGE = registerBlock("construction_foam_wall_orange", () -> new BlockFoamWall(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PINK = registerBlock("construction_foam_wall_pink", () -> new BlockFoamWall(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_YELLOW = registerBlock("construction_foam_wall_yellow", () -> new BlockFoamWall(MaterialColor.COLOR_YELLOW));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIME = registerBlock("construction_foam_wall_lime", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GREEN = registerBlock("construction_foam_wall_green", () -> new BlockFoamWall(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = registerBlock("construction_foam_wall_light_blue", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_CYAN = registerBlock("construction_foam_wall_cyan", () -> new BlockFoamWall(MaterialColor.COLOR_CYAN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLUE = registerBlock("construction_foam_wall_blue", () -> new BlockFoamWall(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_MAGENTA = registerBlock("construction_foam_wall_magenta", () -> new BlockFoamWall(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PURPLE = registerBlock("construction_foam_wall_purple", () -> new BlockFoamWall(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BROWN = registerBlock("construction_foam_wall_brown", () -> new BlockFoamWall(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GRAY = registerBlock("construction_foam_wall_gray", () -> new BlockFoamWall(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = registerBlock("construction_foam_wall_light_gray", () -> new BlockFoamWall(MaterialColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLACK = registerBlock("construction_foam_wall_black", () -> new BlockFoamWall(MaterialColor.COLOR_BLACK));

    public static final RegistryObject<Block> REINFORCED_GLASS = registerBlock("reinforced_glass", ReinforcedGlass::new);
    public static final RegistryObject<Block> REINFORCED_STONE = registerBlock("reinforced_stone", ReinforcedStone::new);
    public static final RegistryObject<Block> REINFORCED_STONE_SLAB = registerBlock("reinforced_stone_slab", ReinforcedStoneSlab::new);
    public static final RegistryObject<Block> REINFORCED_STONE_STAIRS = registerBlock("reinforced_stone_stairs", ReinforcedStoneStairs::new);
    public static final RegistryObject<Block> REINFORCED_STONE_BRICKS = registerBlock("reinforced_stone_bricks", ReinforcedStone::new);
    public static final RegistryObject<Block> SMOOTH_REINFORCED_STONE = registerBlock("smooth_reinforced_stone", ReinforcedStone::new);
    public static final RegistryObject<Block> REINFORCED_STONE_BRICK_WALL = registerBlock("reinforced_stone_brick_wall", ReinforcedWall::new);
    public static final RegistryObject<Block> REINFORCED_DOOR = registerBlock("reinforced_door", ReinforcedDoor::new);

    public static final RegistryObject<Block> IRON_SCAFFOLDING = registerBlock("iron_scaffolding", BlockIronScaffolding::new);
    public static final RegistryObject<Block> IRON_FENCE = registerBlock("iron_fence", BlockIronFence::new);
    public static final RegistryObject<Block> IRON_GATE = registerBlock("iron_gate", BlockIronGate::new);

    public static final RegistryObject<Block> YELLOW_STRIPES_BLOCK_LEFT = registerBlock("yellow_stripes_block_left", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> YELLOW_STRIPES_BLOCK_RIGHT = registerBlock("yellow_stripes_block_right", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> RADIOACTIVE_HAZARD_SIGN_BLOCK = registerBlock("radioactive_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> BIO_HAZARD_SIGN_BLOCK = registerBlock("bio_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> EXPLOSION_HAZARD_SIGN_BLOCK = registerBlock("explosion_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> FIRE_HAZARD_SIGN_BLOCK = registerBlock("fire_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> ACID_HAZARD_SIGN_BLOCK = registerBlock("acid_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> MAGIC_HAZARD_SIGN_BLOCK = registerBlock("magic_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> FROST_HAZARD_SIGN_BLOCK = registerBlock("frost_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));
    public static final RegistryObject<Block> NOISE_HAZARD_SIGN_BLOCK = registerBlock("noise_hazard_sign_block", () -> new BlockResource(Material.METAL, 1f, 3f, SoundType.METAL));

    public static final RegistryObject<Block> INDUSTRIAL_TNT = registerBlock("industrial_tnt", BlockIndustrialTNT::new);
    public static final RegistryObject<Block> NUKE = registerBlock("nuke", BlockNuke::new);

    public static final RegistryObject<Block> LUMINATOR = registerBlock("luminator", BlockLuminator::new);

    public static final RegistryObject<Block> TIN_CABLE = registerBlock("tin_cable", () -> new BlockCable(0.127F, CableTier.TIN_CABLE));
    public static final RegistryObject<Block> TIN_CABLE_INSULATED = registerBlock("tin_cable_insulated", () -> new BlockCable(0.189F, CableTier.TIN_CABLE_INSULATED));
    public static final RegistryObject<Block> COPPER_CABLE = registerBlock("copper_cable", () -> new BlockCable(0.127F, CableTier.COPPER_CABLE));
    public static final RegistryObject<Block> COPPER_CABLE_INSULATED = registerBlock("copper_cable_insulated", () -> new BlockCable(0.251F, CableTier.COPPER_CABLE_INSULATED));
    public static final RegistryObject<Block> GOLD_CABLE = registerBlock("gold_cable", () -> new BlockCable(0.127F, CableTier.GOLD_CABLE));
    public static final RegistryObject<Block> GOLD_CABLE_INSULATED = registerBlock("gold_cable_insulated", () -> new BlockCable(0.189F, CableTier.GOLD_CABLE_INSULATED));
    public static final RegistryObject<Block> HV_CABLE = registerBlock("hv_cable", () -> new BlockCable(0.189F, CableTier.HV_CABLE));
    public static final RegistryObject<Block> HV_CABLE_INSULATED = registerBlock("hv_cable_insulated", () -> new BlockCable(0.313F, CableTier.HV_CABLE_INSULATED));
    public static final RegistryObject<Block> GLASS_FIBRE_CABLE = registerBlock("glass_fibre_cable", () -> new BlockCable(0.127F, CableTier.GLASS_FIBRE_CABLE));

    public static final RegistryObject<Block> IRON_FURNACE = registerBlock("iron_furnace", BlockIronFurnace::new);
    public static final RegistryObject<Block> SIMPLE_CRUSHER = registerBlock("simple_crusher", BlockSimpleCrusher::new);
    public static final RegistryObject<Block> SIMPLE_COMPRESSOR = registerBlock("simple_compressor", BlockSimpleCompressor::new);
    public static final RegistryObject<Block> SIMPLE_EXTRACTOR = registerBlock("simple_extractor", BlockSimpleExtractor::new);

    public static final RegistryObject<Block> GENERATOR = registerBlock("generator", BlockGenerator::new);
    public static final RegistryObject<Block> SOLAR_PANEL = registerBlock("solar_panel", () -> new BlockSolarPanel(SolarPanelTier.BASIC));
    public static final RegistryObject<Block> ADVANCED_SOLAR_PANEL = registerBlock("advanced_solar_panel", () -> new BlockSolarPanel(SolarPanelTier.ADVANCED));
    public static final RegistryObject<Block> HYBRID_SOLAR_PANEL = registerBlock("hybrid_solar_panel", () -> new BlockSolarPanel(SolarPanelTier.HYBRID));
    public static final RegistryObject<Block> QUANTUM_SOLAR_PANEL = registerBlock("quantum_solar_panel", () -> new BlockSolarPanel(SolarPanelTier.QUANTUM));
    public static final RegistryObject<Block> GEO_GENERATOR = registerBlock("geo_generator", BlockGeoGenerator::new);
    public static final RegistryObject<Block> SEMIFLUID_GENERATOR = registerBlock("semifluid_generator", BlockSemifluidGenerator::new);

    public static final RegistryObject<Block> NUCLEAR_REACTOR = registerBlock("nuclear_reactor", BlockNuclearReactor::new);
    public static final RegistryObject<Block> REACTOR_CHAMBER = registerBlock("reactor_chamber", BlockReactorChamber::new);
    public static final RegistryObject<Block> REACTOR_CONTROL_ROD = registerBlock("reactor_control_rod", BlockReactorControlRod::new);
    public static final RegistryObject<Block> REACTOR_FRAME = registerBlock("reactor_frame", BlockReactorFrame::new);

    public static final RegistryObject<Block> ELECTRIC_FURNACE = registerBlock("electric_furnace", BlockElectricFurnace::new);
    public static final RegistryObject<Block> CRUSHER = registerBlock("crusher", BlockCrusher::new);
    public static final RegistryObject<Block> COMPRESSOR = registerBlock("compressor", BlockCompressor::new);
    public static final RegistryObject<Block> EXTRACTOR = registerBlock("extractor", BlockExtractor::new);
    public static final RegistryObject<Block> SAWMILL = registerBlock("sawmill", BlockSawmill::new);
    public static final RegistryObject<Block> EXTRUDER = registerBlock("extruder", BlockExtruder::new);
    public static final RegistryObject<Block> CANNING_MACHINE = registerBlock("canning_machine", BlockCanningMachine::new);
    public static final RegistryObject<Block> FLUID_ENRICHER = registerBlock("fluid_enricher", BlockFluidEnricher::new);
    public static final RegistryObject<Block> RECYCLER = registerBlock("recycler", BlockRecycler::new);
    public static final RegistryObject<Block> METAL_FORMER = registerBlock("metal_former", BlockMetalFormer::new);

    public static final RegistryObject<Block> ALLOY_SMELTER = registerBlock("alloy_smelter", BlockAlloySmelter::new);
    public static final RegistryObject<Block> FERMENTER = registerBlock("fermenter", BlockFermenter::new);
    public static final RegistryObject<Block> ORE_WASHING_PLANT = registerBlock("ore_washing_plant", BlockOreWashingPlant::new);
    public static final RegistryObject<Block> THERMAL_CENTRIFUGE = registerBlock("thermal_centrifuge", BlockThermalCentrifuge::new);

    public static final RegistryObject<Block> MATTER_FABRICATOR = registerBlock("matter_fabricator", BlockMatterFabricator::new);

    public static final RegistryObject<Block> SCANNER = registerBlock("scanner", BlockScanner::new);


    public static final RegistryObject<Block> PATTERN_STORAGE = registerBlock("pattern_storage", BlockPatternStorage::new);




    public static final RegistryObject<Block> BATTERY_BOX = registerBlock("battery_box", () -> new BlockBatteryBox(BatteryBoxTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CESU = registerBlock("cesu", () -> new BlockBatteryBox(BatteryBoxTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MFE = registerBlock("mfe", () -> new BlockBatteryBox(BatteryBoxTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MFSU = registerBlock("mfsu", () -> new BlockBatteryBox(BatteryBoxTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHARGE_PAD_BATTERY_BOX = registerBlock("charge_pad_battery_box", () -> new BlockChargePad(ChargePadTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHARGE_PAD_CESU = registerBlock("charge_pad_cesu", () -> new BlockChargePad(ChargePadTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> CHARGE_PAD_MFE = registerBlock("charge_pad_mfe", () -> new BlockChargePad(ChargePadTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> CHARGE_PAD_MFSU = registerBlock("charge_pad_mfsu", () -> new BlockChargePad(ChargePadTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));

    public static final RegistryObject<Block> LV_TRANSFORMER = registerBlock("lv_transformer", () -> new BlockTransformer(TransformerTier.BASIC, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MV_TRANSFORMER = registerBlock("mv_transformer", () -> new BlockTransformer(TransformerTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> HV_TRANSFORMER = registerBlock("hv_transformer", () -> new BlockTransformer(TransformerTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> EV_TRANSFORMER = registerBlock("ev_transformer", () -> new BlockTransformer(TransformerTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));







    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
