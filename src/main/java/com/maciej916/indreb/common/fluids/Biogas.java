package com.maciej916.indreb.common.fluids;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class Biogas {

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluid/biogas_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluid/biogas_still");

    public static FlowingFluid STILL_FLUID;
    public static FlowingFluid FLOWING_FLUID;
    public static LiquidBlock LIQUID_BLOCK;

    public static FlowingFluid Fluid() {
        STILL_FLUID = new ForgeFlowingFluid.Source(properties);
        return STILL_FLUID;
    }

    public static FlowingFluid FlowingFluid() {
        FLOWING_FLUID = new ForgeFlowingFluid.Flowing(properties);
        return FLOWING_FLUID;
    }

    public static LiquidBlock Block() {
        LIQUID_BLOCK = new LiquidBlock(() -> STILL_FLUID, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
        return LIQUID_BLOCK;
    }

    public static final ForgeFlowingFluid.Properties properties =
        new ForgeFlowingFluid.Properties(() -> STILL_FLUID, () -> FLOWING_FLUID, FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE).gaseous().temperature(328))
        .block(() -> LIQUID_BLOCK);
}
