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
import com.maciej916.indreb.common.block.impl.machines.recycler.BlockEntityRecycler;
import com.maciej916.indreb.common.block.impl.machines.sawmill.BlockEntitySawmill;
import com.maciej916.indreb.common.block.impl.transformer.BlockEntityTransformer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlockEntities {

    public static BlockEntityType<BlockEntityGenerator> GENERATOR;
    public static BlockEntityType<BlockEntitySolarGenerator> SOLAR_GENERATOR;
    public static BlockEntityType<BlockEntityGeoGenerator> GEO_GENERATOR;
    public static BlockEntityType<BlockEntityCrystallineGenerator> CRYSTALLINE_GENERATOR;
    public static BlockEntityType<BlockEntitySemifluidGenerator> SEMIFLUID_GENERATOR;

    public static BlockEntityType<BlockEntityIronFurnace> IRON_FURNACE;
    public static BlockEntityType<BlockEntityElectricFurnace> ELECTRIC_FURNACE;

    public static BlockEntityType<BlockEntityCrusher> CRUSHER;
    public static BlockEntityType<BlockEntityCompressor> COMPRESSOR;
    public static BlockEntityType<BlockEntityExtractor> EXTRACTOR;
    public static BlockEntityType<BlockEntitySawmill> SAWMILL;
    public static BlockEntityType<BlockEntityExtruder> EXTRUDER;
    public static BlockEntityType<BlockEntityCanningMachine> CANNING_MACHINE;
    public static BlockEntityType<BlockEntityFluidEnricher> FLUID_ENRICHER;
    public static BlockEntityType<BlockEntityRecycler> RECYCLER;
    public static BlockEntityType<BlockEntityFermenter> FERMENTER;

    public static BlockEntityType<BlockEntityAlloySmelter> ALLOY_SMELTER;

    public static BlockEntityType<BlockEntityCable> CABLE;
    public static BlockEntityType<BlockEntityBatteryBox> BATTERY_BOX;
    public static BlockEntityType<BlockEntityLuminator> LUMINATOR;
    public static BlockEntityType<BlockEntityTransformer> TRANSFORMER;
    public static BlockEntityType<BlockEntityChargePad> CHARGE_PAD;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event) {

        GENERATOR = registerTileEntity("generator", BlockEntityType.Builder.of(BlockEntityGenerator::new, ModBlocks.GENERATOR.getBlock()).build(null));
        SOLAR_GENERATOR = registerTileEntity("solar_generator", BlockEntityType.Builder.of(BlockEntitySolarGenerator::new, ModBlocks.SOLAR_GENERATOR.getBlock(), ModBlocks.ADVANCED_SOLAR_GENERATOR.getBlock(), ModBlocks.HYBRID_SOLAR_GENERATOR.getBlock(), ModBlocks.QUANTUM_SOLAR_GENERATOR.getBlock()).build(null));
        GEO_GENERATOR = registerTileEntity("geo_generator", BlockEntityType.Builder.of(BlockEntityGeoGenerator::new, ModBlocks.GEO_GENERATOR.getBlock()).build(null));
        CRYSTALLINE_GENERATOR = registerTileEntity("crystalline_generator", BlockEntityType.Builder.of(BlockEntityCrystallineGenerator::new, ModBlocks.CRYSTALLINE_GENERATOR.getBlock()).build(null));
        SEMIFLUID_GENERATOR = registerTileEntity("semifluid_generator", BlockEntityType.Builder.of(BlockEntitySemifluidGenerator::new, ModBlocks.SEMIFLUID_GENERATOR.getBlock()).build(null));

        IRON_FURNACE = registerTileEntity("iron_furnace", BlockEntityType.Builder.of(BlockEntityIronFurnace::new, ModBlocks.IRON_FURNACE.getBlock()).build(null));
        ELECTRIC_FURNACE = registerTileEntity("electric_furnace", BlockEntityType.Builder.of(BlockEntityElectricFurnace::new, ModBlocks.ELECTRIC_FURNACE.getBlock()).build(null));

        CRUSHER = registerTileEntity("crusher", BlockEntityType.Builder.of(BlockEntityCrusher::new, ModBlocks.CRUSHER.getBlock()).build(null));
        COMPRESSOR = registerTileEntity("compressor", BlockEntityType.Builder.of(BlockEntityCompressor::new, ModBlocks.COMPRESSOR.getBlock()).build(null));
        EXTRACTOR = registerTileEntity("extractor", BlockEntityType.Builder.of(BlockEntityExtractor::new, ModBlocks.EXTRACTOR.getBlock()).build(null));
        SAWMILL = registerTileEntity("sawmill", BlockEntityType.Builder.of(BlockEntitySawmill::new, ModBlocks.SAWMILL.getBlock()).build(null));
        EXTRUDER = registerTileEntity("extruder", BlockEntityType.Builder.of(BlockEntityExtruder::new, ModBlocks.EXTRUDER.getBlock()).build(null));
        CANNING_MACHINE = registerTileEntity("canning_machine", BlockEntityType.Builder.of(BlockEntityCanningMachine::new, ModBlocks.CANNING_MACHINE.getBlock()).build(null));
        FLUID_ENRICHER = registerTileEntity("fluid_enricher", BlockEntityType.Builder.of(BlockEntityFluidEnricher::new, ModBlocks.FLUID_ENRICHER.getBlock()).build(null));
        RECYCLER = registerTileEntity("recycler", BlockEntityType.Builder.of(BlockEntityRecycler::new, ModBlocks.RECYCLER.getBlock()).build(null));
        ALLOY_SMELTER = registerTileEntity("alloy_smelter", BlockEntityType.Builder.of(BlockEntityAlloySmelter::new, ModBlocks.ALLOY_SMELTER.getBlock()).build(null));
        FERMENTER = registerTileEntity("fermenter", BlockEntityType.Builder.of(BlockEntityFermenter::new, ModBlocks.FERMENTER.getBlock()).build(null));

        CABLE = registerTileEntity("cable", BlockEntityType.Builder.of(BlockEntityCable::new, ModBlocks.TIN_CABLE.getBlock(), ModBlocks.TIN_CABLE_INSULATED.getBlock(), ModBlocks.COPPER_CABLE.getBlock(), ModBlocks.COPPER_CABLE_INSULATED.getBlock()).build(null));
        BATTERY_BOX = registerTileEntity("battery_box", BlockEntityType.Builder.of(BlockEntityBatteryBox::new, ModBlocks.BATTERY_BOX.getBlock(), ModBlocks.CESU.getBlock(), ModBlocks.MFE.getBlock(), ModBlocks.MFSU.getBlock()).build(null));
        LUMINATOR = registerTileEntity("luminator", BlockEntityType.Builder.of(BlockEntityLuminator::new, ModBlocks.LUMINATOR.getBlock()).build(null));
        TRANSFORMER = registerTileEntity("transformer", BlockEntityType.Builder.of(BlockEntityTransformer::new, ModBlocks.LV_TRANSFORMER.getBlock(), ModBlocks.MV_TRANSFORMER.getBlock(), ModBlocks.HV_TRANSFORMER.getBlock(), ModBlocks.EV_TRANSFORMER.getBlock()).build(null));
        CHARGE_PAD = registerTileEntity("charge_pad", BlockEntityType.Builder.of(BlockEntityChargePad::new, ModBlocks.CHARGE_PAD_BATTERY_BOX.getBlock(), ModBlocks.CHARGE_PAD_CESU.getBlock(), ModBlocks.CHARGE_PAD_MFE.getBlock(), ModBlocks.CHARGE_PAD_MFSU.getBlock()).build(null));



    }

    private static <T extends BlockEntity> BlockEntityType<T> registerTileEntity(String name, BlockEntityType<T> tileEntityType) {
        tileEntityType.setRegistryName(name);
        ForgeRegistries.BLOCK_ENTITIES.register(tileEntityType);
        return tileEntityType;
    }

}
