package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.fluids.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModFluids {
    public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IndReb.MODID);
    public static DeferredRegister<Item> FLUID_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndReb.MODID);
    public static DeferredRegister<Block> FLUID_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndReb.MODID);


    public static RegistryObject<FlowingFluid> CONSTRUCTION_FOAM = FLUIDS.register("construction_foam",  ConstructionFoam::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_CONSTRUCTION_FOAM = FLUIDS.register("flowing_construction_foam", ConstructionFoam::FlowingFluid);
    public static RegistryObject<LiquidBlock> LIQUID_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_construction_foam", ConstructionFoam::Block);
    //    public static RegistryObject<Item> BUCKET_CONSTRUCTION_FOAM = FLUID_ITEMS.register("bucket_construction_foam", ConstructionFoam::BucketConstructionFoam);

    public static RegistryObject<FlowingFluid> REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("reinforced_construction_foam",  ReinforcedConstructionFoam::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("flowing_reinforced_construction_foam", ReinforcedConstructionFoam::FlowingFluid);
    public static RegistryObject<LiquidBlock> LIQUID_REINFORCED_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_reinforced_construction_foam", ReinforcedConstructionFoam::Block);

    public static RegistryObject<FlowingFluid> COOLANT = FLUIDS.register("coolant",  Coolant::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_COOLANT = FLUIDS.register("flowing_coolant", Coolant::FlowingFluid);
    public static RegistryObject<LiquidBlock> LIQUID_COOLANT = FLUID_BLOCKS.register("liquid_coolant", Coolant::Block);

    public static RegistryObject<FlowingFluid> BIOGAS = FLUIDS.register("biogas",  Biogas::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_BIOGAS = FLUIDS.register("flowing_biogas", Biogas::FlowingFluid);
    public static RegistryObject<LiquidBlock> LIQUID_BIOGAS = FLUID_BLOCKS.register("liquid_biogas", Biogas::Block);

    public static RegistryObject<FlowingFluid> BIOMASS = FLUIDS.register("biomass",  Biomass::Fluid);
    public static RegistryObject<FlowingFluid> FLOWING_BIOMASS = FLUIDS.register("flowing_biomass", Biomass::FlowingFluid);
    public static RegistryObject<LiquidBlock> LIQUID_BIOMASS = FLUID_BLOCKS.register("liquid_biomass", Biomass::Block);



}
