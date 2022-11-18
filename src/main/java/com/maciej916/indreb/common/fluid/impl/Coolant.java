package com.maciej916.indreb.common.fluid.impl;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.fluid.BaseFluidType;
import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class Coolant {

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluid/coolant/coolant_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluid/coolant/coolant_flow");
    public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluid/coolant/coolant_overlay");

    public static FlowingFluid STILL_FLUID;
    public static FlowingFluid FLOWING_FLUID;
    public static LiquidBlock LIQUID_BLOCK;
    public static FluidType FLUID_TYPE;

    public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> FLUID_TYPE, () -> STILL_FLUID, () -> FLOWING_FLUID).slopeFindDistance(2).levelDecreasePerBlock(2).block(() -> LIQUID_BLOCK);

    public static FlowingFluid Fluid() {
        STILL_FLUID = new ForgeFlowingFluid.Source(PROPERTIES);
        return STILL_FLUID;
    }

    public static FlowingFluid FlowingFluid() {
        FLOWING_FLUID = new ForgeFlowingFluid.Flowing(PROPERTIES);
        return FLOWING_FLUID;
    }

    public static LiquidBlock Block() {
        LIQUID_BLOCK = new LiquidBlock(() -> STILL_FLUID, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable());
        return LIQUID_BLOCK;
    }

    public static FluidType FluidType() {
        FLUID_TYPE = new BaseFluidType(
                STILL_TEXTURE,
                FLOWING_TEXTURE,
                OVERLAY_TEXTURE,
                0XA100FFFF,
                new Vector3f(0f, 1.0f, 1.0f),
                FluidType.Properties.create().density(997).viscosity(1000)
        );
        return FLUID_TYPE;
    }
}
