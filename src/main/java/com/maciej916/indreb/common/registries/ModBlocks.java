package com.maciej916.indreb.common.registries;

import com.google.common.base.Supplier;
import com.maciej916.indreb.common.block.BlockOre;
import com.maciej916.indreb.common.block.BlockResource;
import com.maciej916.indreb.common.block.BlockSheet;
import com.maciej916.indreb.common.block.BlockWIP;
import com.maciej916.indreb.common.block.impl.BlockIronFence;
import com.maciej916.indreb.common.block.impl.BlockIronScaffolding;
import com.maciej916.indreb.common.block.impl.battery_box.BlockBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockCable;
import com.maciej916.indreb.common.block.impl.cf.*;
import com.maciej916.indreb.common.block.impl.charge_pad.BlockChargePad;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.BlockCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.BlockGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.BlockGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.BlockSemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.BlockSolarGenerator;
import com.maciej916.indreb.common.block.impl.luminator.BlockLuminator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockExtruder;
import com.maciej916.indreb.common.block.impl.machines.fermenter.BlockFermenter;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.BlockIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockSawmill;
import com.maciej916.indreb.common.block.impl.rubber_wood.*;
import com.maciej916.indreb.common.block.impl.transformer.BlockTransformer;
import com.maciej916.indreb.common.world.feature.tree.RubberTreeGrower;
import com.maciej916.indreb.common.item.block.BlockItemElectric;
import com.maciej916.indreb.common.item.block.ItemScaffolding;
import com.maciej916.indreb.common.tier.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new BlockOre());
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new BlockOre());
    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore", () -> new BlockOre());
    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", () -> new BlockOre());
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new BlockOre());

    public static final RegistryObject<Block> RUBBER_LOG = registerBlock("rubber_log", () -> new RubberLog());
    public static final RegistryObject<Block> RUBBER_WOOD = registerBlock("rubber_wood", () -> new RubberWood());
    public static final RegistryObject<Block> RUBBER_LEAVES = registerBlock("rubber_leaves", () -> new RubberLeaves());
    public static final RegistryObject<Block> RUBBER_PLANKS = registerBlock("rubber_planks", () -> new RubberPlanks());
    public static final RegistryObject<Block> RUBBER_SAPLING = registerBlock("rubber_sapling", () -> new RubberSapling(new RubberTreeGrower()));
    public static final RegistryObject<Block> RUBBER_STAIRS = registerBlock("rubber_stairs", () -> new RubberStairs());
    public static final RegistryObject<Block> RUBBER_SLAB = registerBlock("rubber_slab", () -> new RubberSlab());

    public static final RegistryObject<Block> TIN_CABLE = registerBlock("tin_cable", () -> new BlockCable(0.127F, CableTier.TIN_CABLE));
    public static final RegistryObject<Block> TIN_CABLE_INSULATED = registerBlock("tin_cable_insulated", () -> new BlockCable(0.189F, CableTier.TIN_CABLE_INSULATED));
    public static final RegistryObject<Block> COPPER_CABLE = registerBlock("copper_cable", () -> new BlockCable(0.127F, CableTier.COPPER_CABLE));
    public static final RegistryObject<Block> COPPER_CABLE_INSULATED = registerBlock("copper_cable_insulated", () -> new BlockCable(0.251F, CableTier.COPPER_CABLE_INSULATED));
    public static final RegistryObject<Block> GOLD_CABLE = registerBlock("gold_cable", () -> new BlockCable(0.127F, CableTier.GOLD_CABLE));
    public static final RegistryObject<Block> GOLD_CABLE_INSULATED = registerBlock("gold_cable_insulated", () -> new BlockCable(0.189F, CableTier.GOLD_CABLE_INSULATED));
    public static final RegistryObject<Block> HV_CABLE = registerBlock("hv_cable", () -> new BlockCable(0.189F, CableTier.HV_CABLE));
    public static final RegistryObject<Block> HV_CABLE_INSULATED = registerBlock("hv_cable_insulated", () -> new BlockCable(0.313F, CableTier.HV_CABLE_INSULATED));
    public static final RegistryObject<Block> GLASS_FIBRE_CABLE = registerBlock("glass_fibre_cable", () -> new BlockCable(0.127F, CableTier.GLASS_FIBRE_CABLE));

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block", () -> new BlockResource(1f, 3f));

    public static final RegistryObject<Block> BASIC_MACHINE_CASING = registerBlock("basic_machine_casing", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> ADVANCED_MACHINE_CASING = registerBlock("advanced_machine_casing", () -> new BlockResource(1f, 3f));
    public static final RegistryObject<Block> RESIN_SHEET = registerBlock("resin_sheet", () -> new BlockSheet(0.7F));
    public static final RegistryObject<Block> RUBBER_SHEET = registerBlock("rubber_sheet", () -> new BlockSheet(0.7F));

    public static final RegistryObject<Block> CONSTRUCTION_FOAM = registerBlock("construction_foam", () -> new BlockCF());
    public static final RegistryObject<Block> REINFORCED_CONSTRUCTION_FOAM = registerBlock("reinforced_construction_foam", () -> new BlockReinforcedCF());

    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_WHITE = registerBlock("construction_foam_wall_white", () -> new BlockCFWall(MaterialColor.WOOL));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_RED = registerBlock("construction_foam_wall_red", () -> new BlockCFWall(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_ORANGE = registerBlock("construction_foam_wall_orange", () -> new BlockCFWall(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PINK = registerBlock("construction_foam_wall_pink", () -> new BlockCFWall(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_YELLOW = registerBlock("construction_foam_wall_yellow", () -> new BlockCFWall(MaterialColor.COLOR_YELLOW));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIME = registerBlock("construction_foam_wall_lime", () -> new BlockCFWall(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GREEN = registerBlock("construction_foam_wall_green", () -> new BlockCFWall(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_BLUE = registerBlock("construction_foam_wall_light_blue", () -> new BlockCFWall(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_CYAN = registerBlock("construction_foam_wall_cyan", () -> new BlockCFWall(MaterialColor.COLOR_CYAN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLUE = registerBlock("construction_foam_wall_blue", () -> new BlockCFWall(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_MAGENTA = registerBlock("construction_foam_wall_magenta", () -> new BlockCFWall(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_PURPLE = registerBlock("construction_foam_wall_purple", () -> new BlockCFWall(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BROWN = registerBlock("construction_foam_wall_brown", () -> new BlockCFWall(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_GRAY = registerBlock("construction_foam_wall_gray", () -> new BlockCFWall(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_LIGHT_GRAY = registerBlock("construction_foam_wall_light_gray", () -> new BlockCFWall(MaterialColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> CONSTRUCTION_FOAM_WALL_BLACK = registerBlock("construction_foam_wall_black", () -> new BlockCFWall(MaterialColor.COLOR_BLACK));

    public static final RegistryObject<Block> REINFORCED_GLASS = registerBlock("reinforced_glass", () -> new BlockReinforcedGlass());
    public static final RegistryObject<Block> REINFORCED_STONE = registerBlock("reinforced_stone", () -> new BlockReinforcedStone());
    public static final RegistryObject<Block> REINFORCED_STONE_SLAB = registerBlock("reinforced_stone_slab", () -> new BlockReinforcedStoneSlab());
    public static final RegistryObject<Block> REINFORCED_STONE_STAIRS = registerBlock("reinforced_stone_stairs", () -> new BlockReinforcedStoneStairs());

    public static final RegistryObject<Block> IRON_SCAFFOLDING = registerBlock("iron_scaffolding", () -> new BlockIronScaffolding());
    public static final RegistryObject<Block> IRON_FENCE = registerBlock("iron_fence", () -> new BlockIronFence());

    public static final RegistryObject<Block> LUMINATOR = registerBlock("luminator", () -> new BlockLuminator());

    public static final RegistryObject<Block> GENERATOR = registerBlock("generator", () -> new BlockGenerator());
    public static final RegistryObject<Block> SOLAR_GENERATOR = registerBlock("solar_generator", () -> new BlockSolarGenerator(SolarGeneratorTier.BASIC));
    public static final RegistryObject<Block> ADVANCED_SOLAR_GENERATOR = registerBlock("advanced_solar_generator", () -> new BlockSolarGenerator(SolarGeneratorTier.ADVANCED));
    public static final RegistryObject<Block> HYBRID_SOLAR_GENERATOR = registerBlock("hybrid_solar_generator", () -> new BlockSolarGenerator(SolarGeneratorTier.HYBRID));
    public static final RegistryObject<Block> QUANTUM_SOLAR_GENERATOR = registerBlock("quantum_solar_generator", () -> new BlockSolarGenerator(SolarGeneratorTier.QUANTUM));
    public static final RegistryObject<Block> GEO_GENERATOR = registerBlock("geo_generator", () -> new BlockGeoGenerator());
    public static final RegistryObject<Block> CRYSTALLINE_GENERATOR = registerBlock("crystalline_generator", () -> new BlockCrystallineGenerator());
    public static final RegistryObject<Block> SEMIFLUID_GENERATOR = registerBlock("semifluid_generator", () -> new BlockSemifluidGenerator());

    public static final RegistryObject<Block> BATTERY_BOX = registerBlock("battery_box", () -> new BlockBatteryBox(BatteryBoxTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CESU = registerBlock("cesu", () -> new BlockBatteryBox(BatteryBoxTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MFE = registerBlock("mfe", () -> new BlockBatteryBox(BatteryBoxTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MFSU = registerBlock("mfsu", () -> new BlockBatteryBox(BatteryBoxTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));

    public static final RegistryObject<Block> LV_TRANSFORMER = registerBlock("lv_transformer", () -> new BlockTransformer(TransformerTier.BASIC, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MV_TRANSFORMER = registerBlock("mv_transformer", () -> new BlockTransformer(TransformerTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> HV_TRANSFORMER = registerBlock("hv_transformer", () -> new BlockTransformer(TransformerTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> EV_TRANSFORMER = registerBlock("ev_transformer", () -> new BlockTransformer(TransformerTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHARGE_PAD_BATTERY_BOX = registerBlock("charge_pad_battery_box", () -> new BlockChargePad(ChargePadTier.BASIC, BlockBehaviour.Properties.of(Material.WOOD).strength(1f, 3f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHARGE_PAD_CESU = registerBlock("charge_pad_cesu", () -> new BlockChargePad(ChargePadTier.STANDARD, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> CHARGE_PAD_MFE = registerBlock("charge_pad_mfe", () -> new BlockChargePad(ChargePadTier.ADVANCED, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> CHARGE_PAD_MFSU = registerBlock("charge_pad_mfsu", () -> new BlockChargePad(ChargePadTier.SUPER, BlockBehaviour.Properties.of(Material.METAL).strength(5f, 3f).sound(SoundType.METAL)));

    public static final RegistryObject<Block> IRON_FURNACE = registerBlock("iron_furnace", () -> new BlockIronFurnace());
    public static final RegistryObject<Block> ELECTRIC_FURNACE = registerBlock("electric_furnace", () -> new BlockElectricFurnace());

    public static final RegistryObject<Block> CRUSHER = registerBlock("crusher", () -> new BlockCrusher());
    public static final RegistryObject<Block> COMPRESSOR = registerBlock("compressor", () -> new BlockCompressor());
    public static final RegistryObject<Block> EXTRACTOR = registerBlock("extractor", () -> new BlockExtractor());
    public static final RegistryObject<Block> SAWMILL = registerBlock("sawmill", () -> new BlockSawmill());
    public static final RegistryObject<Block> EXTRUDER = registerBlock("extruder", () -> new BlockExtruder());
    public static final RegistryObject<Block> CANNING_MACHINE = registerBlock("canning_machine", () -> new BlockCanningMachine());
    public static final RegistryObject<Block> FLUID_ENRICHER = registerBlock("fluid_enricher", () -> new BlockFluidEnricher());
    public static final RegistryObject<Block> RECYCLER = registerBlock("recycler", () -> new BlockRecycler());
    public static final RegistryObject<Block> ALLOY_SMELTER = registerBlock("alloy_smelter", () -> new BlockAlloySmelter());
    public static final RegistryObject<Block> FERMENTER = registerBlock("fermenter", () -> new BlockFermenter());

    public static final RegistryObject<Block> INDUSTRIAL_TNT = registerBlock("industrial_tnt", () -> new BlockWIP());
    public static final RegistryObject<Block> NUKE = registerBlock("nuke", () -> new BlockWIP());


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
