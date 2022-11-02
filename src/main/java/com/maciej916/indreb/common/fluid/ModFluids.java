package com.maciej916.indreb.common.fluid;

import com.maciej916.indreb.IndReb;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IndReb.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);
    public static final DeferredRegister<Item> FLUID_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndReb.MODID);
    public static final DeferredRegister<Block> FLUID_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndReb.MODID);


    public static RegistryObject<FlowingFluid> CONSTRUCTION_FOAM = FLUIDS.register("construction_foam", ConstructionFoam::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_CONSTRUCTION_FOAM = FLUIDS.register("flowing_construction_foam", ConstructionFoam::FlowingFluid);
    public static RegistryObject<FluidType> CONSTRUCTION_FOAM_TYPE = FLUID_TYPES.register("construction_foam", ConstructionFoam::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_construction_foam", ConstructionFoam::Block);

    public static RegistryObject<FlowingFluid> REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("reinforced_construction_foam", ReinforcedConstructionFoam::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("flowing_reinforced_construction_foam", ReinforcedConstructionFoam::FlowingFluid);
    public static RegistryObject<FluidType> REINFORCED_CONSTRUCTION_FOAM_TYPE = FLUID_TYPES.register("reinforced_construction_foam", ReinforcedConstructionFoam::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_REINFORCED_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_reinforced_construction_foam", ReinforcedConstructionFoam::Block);

    public static RegistryObject<FlowingFluid> COOLANT = FLUIDS.register("coolant",  Coolant::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_COOLANT = FLUIDS.register("flowing_coolant", Coolant::FlowingFluid);
    public static RegistryObject<FluidType> COOLANT_TYPE = FLUID_TYPES.register("coolant", Coolant::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_COOLANT = FLUID_BLOCKS.register("liquid_coolant", Coolant::Block);

    public static RegistryObject<FlowingFluid> BIOGAS = FLUIDS.register("biogas",  Biogas::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_BIOGAS = FLUIDS.register("flowing_biogas", Biogas::FlowingFluid);
    public static RegistryObject<FluidType> BIOGAS_TYPE = FLUID_TYPES.register("biogas", Biogas::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_BIOGAS = FLUID_BLOCKS.register("liquid_biogas", Biogas::Block);

    public static RegistryObject<FlowingFluid> BIOMASS = FLUIDS.register("biomass",  Biomass::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_BIOMASS = FLUIDS.register("flowing_biomass", Biomass::FlowingFluid);
    public static RegistryObject<FluidType> BIOMASS_TYPE = FLUID_TYPES.register("biomass", Biomass::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_BIOMASS = FLUID_BLOCKS.register("liquid_biomass", Biomass::Block);

    public static RegistryObject<FlowingFluid> MATTER = FLUIDS.register("matter",  Matter::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_MATTER = FLUIDS.register("flowing_matter", Matter::FlowingFluid);
    public static RegistryObject<FluidType> MATTER_TYPE = FLUID_TYPES.register("matter", Matter::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_MATTER = FLUID_BLOCKS.register("liquid_matter", Matter::Block);

    public static RegistryObject<FlowingFluid> SULFURIC_ACID = FLUIDS.register("sulfuric_acid",  SulfuricAcid::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_SULFURIC_ACID = FLUIDS.register("flowing_sulfuric_acid", SulfuricAcid::FlowingFluid);
    public static RegistryObject<FluidType> SULFURIC_ACID_TYPE = FLUID_TYPES.register("sulfuric_acid", SulfuricAcid::FluidType);
    public static RegistryObject<LiquidBlock> LIQUID_SULFURIC_ACID = FLUID_BLOCKS.register("liquid_sulfuric_acid", SulfuricAcid::Block);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        FLUID_TYPES.register(eventBus);
        FLUID_ITEMS.register(eventBus);
        FLUID_BLOCKS.register(eventBus);
    }
}
