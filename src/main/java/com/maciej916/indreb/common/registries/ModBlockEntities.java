package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.block.impl.battery_box.BlockEntityBatteryBox;
import com.maciej916.indreb.common.block.impl.cable.BlockEntityCable;
import com.maciej916.indreb.common.block.impl.charge_pad.BlockEntityChargePad;
import com.maciej916.indreb.common.block.impl.generators.crystalline_generator.BlockEntityCrystallineGenerator;
import com.maciej916.indreb.common.block.impl.generators.generator.BlockEntityGenerator;
import com.maciej916.indreb.common.block.impl.generators.geo_generator.BlockEntityGeoGenerator;
import com.maciej916.indreb.common.block.impl.generators.semifluid_generator.BlockEntitySemifluidGenerator;
import com.maciej916.indreb.common.block.impl.generators.solar_panels.BlockEntitySolarGenerator;
import com.maciej916.indreb.common.block.impl.luminator.BlockEntityLuminator;
import com.maciej916.indreb.common.block.impl.machines.alloy_smelter.BlockEntityAlloySmelter;
import com.maciej916.indreb.common.block.impl.machines.canning_machine.BlockEntityCanningMachine;
import com.maciej916.indreb.common.block.impl.machines.compressor.BlockEntityCompressor;
import com.maciej916.indreb.common.block.impl.machines.crusher.BlockEntityCrusher;
import com.maciej916.indreb.common.block.impl.machines.electric_furnace.BlockEntityElectricFurnace;
import com.maciej916.indreb.common.block.impl.machines.extractor.BlockEntityExtractor;
import com.maciej916.indreb.common.block.impl.machines.extruder.BlockEntityExtruder;
import com.maciej916.indreb.common.block.impl.machines.fermenter.BlockEntityFermenter;
import com.maciej916.indreb.common.block.impl.machines.fluid_enricher.BlockEntityFluidEnricher;
import com.maciej916.indreb.common.block.impl.machines.iron_furnace.BlockEntityIronFurnace;
import com.maciej916.indreb.common.block.impl.machines.matter_fabricator.BlockEntityMatterFabricator;
import com.maciej916.indreb.common.block.impl.machines.metal_former.BlockEntityMeralFormer;
import com.maciej916.indreb.common.block.impl.machines.ore_washing_plant.BlockEntityOreWashingPlant;
import com.maciej916.indreb.common.block.impl.machines.pattern_storage.BlockEntityPatternStorage;
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockEntityRecycler;
import com.maciej916.indreb.common.block.impl.machines.replicator.BlockEntityReplicator;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockEntitySawmill;
import com.maciej916.indreb.common.block.impl.machines.scanner.BlockEntityScanner;
import com.maciej916.indreb.common.block.impl.machines.thermal_centrifuge.BlockEntityThermalCentrifuge;
import com.maciej916.indreb.common.block.impl.transformer.BlockEntityTransformer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<BlockEntityGenerator>> GENERATOR = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(BlockEntityGenerator::new, ModBlocks.GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityGenerator>> SOLAR_GENERATOR = BLOCK_ENTITIES.register("solar_generator", () -> BlockEntityType.Builder.of(BlockEntityGenerator::new, ModBlocks.SOLAR_GENERATOR.get(), ModBlocks.ADVANCED_SOLAR_GENERATOR.get(), ModBlocks.HYBRID_SOLAR_GENERATOR.get(), ModBlocks.QUANTUM_SOLAR_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityGeoGenerator>> GEO_GENERATOR = BLOCK_ENTITIES.register("geo_generator", () -> BlockEntityType.Builder.of(BlockEntityGeoGenerator::new, ModBlocks.GEO_GENERATOR.get()).build(null));
//    public static final RegistryObject<BlockEntityType<BlockEntityCrystallineGenerator>> CRYSTALLINE_GENERATOR = BLOCK_ENTITIES.register("crystalline_generator", () -> BlockEntityType.Builder.of(BlockEntityCrystallineGenerator::new, ModBlocks.CRYSTALLINE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySemifluidGenerator>> SEMIFLUID_GENERATOR = BLOCK_ENTITIES.register("semifluid_generator", () -> BlockEntityType.Builder.of(BlockEntitySemifluidGenerator::new, ModBlocks.SEMIFLUID_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityCable>> CABLE = BLOCK_ENTITIES.register("cable", () -> BlockEntityType.Builder.of(BlockEntityCable::new, ModBlocks.TIN_CABLE.get(), ModBlocks.TIN_CABLE_INSULATED.get(), ModBlocks.COPPER_CABLE.get(), ModBlocks.COPPER_CABLE_INSULATED.get(), ModBlocks.HV_CABLE.get(), ModBlocks.HV_CABLE_INSULATED.get(), ModBlocks.GOLD_CABLE.get(), ModBlocks.GLASS_FIBRE_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityBatteryBox>> BATTERY_BOX = BLOCK_ENTITIES.register("battery_box", () -> BlockEntityType.Builder.of(BlockEntityBatteryBox::new, ModBlocks.BATTERY_BOX.get(), ModBlocks.CESU.get(), ModBlocks.MFE.get(), ModBlocks.MFSU.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityLuminator>> LUMINATOR = BLOCK_ENTITIES.register("luminator", () -> BlockEntityType.Builder.of(BlockEntityLuminator::new, ModBlocks.LUMINATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityTransformer>> TRANSFORMER = BLOCK_ENTITIES.register("transformer", () -> BlockEntityType.Builder.of(BlockEntityTransformer::new, ModBlocks.LV_TRANSFORMER.get(), ModBlocks.MV_TRANSFORMER.get(), ModBlocks.HV_TRANSFORMER.get(), ModBlocks.EV_TRANSFORMER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityChargePad>> CHARGE_PAD = BLOCK_ENTITIES.register("charge_pad", () -> BlockEntityType.Builder.of(BlockEntityChargePad::new, ModBlocks.CHARGE_PAD_BATTERY_BOX.get(), ModBlocks.CHARGE_PAD_CESU.get(), ModBlocks.CHARGE_PAD_MFE.get(), ModBlocks.CHARGE_PAD_MFSU.get()).build(null));


    public static final RegistryObject<BlockEntityType<BlockEntityIronFurnace>> IRON_FURNACE = BLOCK_ENTITIES.register("iron_furnace", () -> BlockEntityType.Builder.of(BlockEntityIronFurnace::new, ModBlocks.IRON_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityElectricFurnace>> ELECTRIC_FURNACE = BLOCK_ENTITIES.register("electric_furnace", () -> BlockEntityType.Builder.of(BlockEntityElectricFurnace::new, ModBlocks.ELECTRIC_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityCrusher>> CRUSHER = BLOCK_ENTITIES.register("crusher", () -> BlockEntityType.Builder.of(BlockEntityCrusher::new, ModBlocks.CRUSHER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityCompressor>> COMPRESSOR = BLOCK_ENTITIES.register("compressor", () -> BlockEntityType.Builder.of(BlockEntityCompressor::new, ModBlocks.COMPRESSOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityExtractor>> EXTRACTOR = BLOCK_ENTITIES.register("extractor", () -> BlockEntityType.Builder.of(BlockEntityExtractor::new, ModBlocks.EXTRACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntitySawmill>> SAWMILL = BLOCK_ENTITIES.register("sawmill", () -> BlockEntityType.Builder.of(BlockEntitySawmill::new, ModBlocks.SAWMILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityExtruder>> EXTRUDER = BLOCK_ENTITIES.register("extruder", () -> BlockEntityType.Builder.of(BlockEntityExtruder::new, ModBlocks.EXTRUDER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityCanningMachine>> CANNING_MACHINE = BLOCK_ENTITIES.register("canning_machine", () -> BlockEntityType.Builder.of(BlockEntityCanningMachine::new, ModBlocks.CANNING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityFluidEnricher>> FLUID_ENRICHER = BLOCK_ENTITIES.register("fluid_enricher", () -> BlockEntityType.Builder.of(BlockEntityFluidEnricher::new, ModBlocks.FLUID_ENRICHER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityRecycler>> RECYCLER = BLOCK_ENTITIES.register("recycler", () -> BlockEntityType.Builder.of(BlockEntityRecycler::new, ModBlocks.RECYCLER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityAlloySmelter>> ALLOY_SMELTER = BLOCK_ENTITIES.register("alloy_smelter", () -> BlockEntityType.Builder.of(BlockEntityAlloySmelter::new, ModBlocks.ALLOY_SMELTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityFermenter>> FERMENTER = BLOCK_ENTITIES.register("fermenter", () -> BlockEntityType.Builder.of(BlockEntityFermenter::new, ModBlocks.FERMENTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityOreWashingPlant>> ORE_WASHING_PLANT = BLOCK_ENTITIES.register("ore_washing_plant", () -> BlockEntityType.Builder.of(BlockEntityOreWashingPlant::new, ModBlocks.ORE_WASHING_PLANT.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityMatterFabricator>> MATTER_FABRICATOR = BLOCK_ENTITIES.register("matter_fabricator", () -> BlockEntityType.Builder.of(BlockEntityMatterFabricator::new, ModBlocks.MATTER_FABRICATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityThermalCentrifuge>> THERMAL_CENTRIFUGE = BLOCK_ENTITIES.register("thermal_centrifuge", () -> BlockEntityType.Builder.of(BlockEntityThermalCentrifuge::new, ModBlocks.THERMAL_CENTRIFUGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityPatternStorage>> PATTERN_STORAGE = BLOCK_ENTITIES.register("pattern_storage", () -> BlockEntityType.Builder.of(BlockEntityPatternStorage::new, ModBlocks.PATTERN_STORAGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityScanner>> SCANNER = BLOCK_ENTITIES.register("scanner", () -> BlockEntityType.Builder.of(BlockEntityScanner::new, ModBlocks.SCANNER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityReplicator>> REPLICATOR = BLOCK_ENTITIES.register("replicator", () -> BlockEntityType.Builder.of(BlockEntityReplicator::new, ModBlocks.REPLICATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityMeralFormer>> METAL_FORMER = BLOCK_ENTITIES.register("metal_former", () -> BlockEntityType.Builder.of(BlockEntityMeralFormer::new, ModBlocks.METAL_FORMER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
