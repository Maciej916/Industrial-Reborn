package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.fluids.ConstructionFoam;
import com.maciej916.indreb.common.fluids.ReinforcedConstructionFoam;
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


    public static RegistryObject<FlowingFluid> CONSTRUCTION_FOAM = FLUIDS.register("construction_foam",  ConstructionFoam::ConstructionFoamFluid);
    public static RegistryObject<FlowingFluid> FLOWING_CONSTRUCTION_FOAM = FLUIDS.register("flowing_construction_foam", ConstructionFoam::FlowingConstructionFoamFluid);
    public static RegistryObject<LiquidBlock> LIQUID_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_construction_foam", ConstructionFoam::LiquidConstructionFoam);
    //    public static RegistryObject<Item> BUCKET_CONSTRUCTION_FOAM = FLUID_ITEMS.register("bucket_construction_foam", ConstructionFoam::BucketConstructionFoam);


    public static RegistryObject<FlowingFluid> REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("reinforced_construction_foam",  ReinforcedConstructionFoam::ConstructionFoamFluid);
    public static RegistryObject<FlowingFluid> FLOWING_REINFORCED_CONSTRUCTION_FOAM = FLUIDS.register("flowing_reinforced_construction_foam", ReinforcedConstructionFoam::FlowingConstructionFoamFluid);
    public static RegistryObject<LiquidBlock> LIQUID_REINFORCED_CONSTRUCTION_FOAM = FLUID_BLOCKS.register("liquid_reinforced_construction_foam", ReinforcedConstructionFoam::LiquidConstructionFoam);



}
