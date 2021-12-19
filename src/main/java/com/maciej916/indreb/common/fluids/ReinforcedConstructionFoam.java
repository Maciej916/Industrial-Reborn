package com.maciej916.indreb.common.fluids;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ReinforcedConstructionFoam {

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluids/reinforced_construction_foam_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluids/reinforced_construction_foam_flow");

    public static FlowingFluid REINFORCED_CONSTRUCTION_FOAM;
    public static FlowingFluid FLOWING_REINFORCED_CONSTRUCTION_FOAM;
    public static LiquidBlock LIQUID_REINFORCED_CONSTRUCTION_FOAM;

    public static FlowingFluid ConstructionFoamFluid() {
        REINFORCED_CONSTRUCTION_FOAM = new ForgeFlowingFluid.Source(properties);
        return REINFORCED_CONSTRUCTION_FOAM;
    }

    public static FlowingFluid FlowingConstructionFoamFluid() {
        FLOWING_REINFORCED_CONSTRUCTION_FOAM = new ForgeFlowingFluid.Flowing(properties);
        return FLOWING_REINFORCED_CONSTRUCTION_FOAM;
    }

    public static LiquidBlock LiquidConstructionFoam() {
        LIQUID_REINFORCED_CONSTRUCTION_FOAM = new LiquidBlock(() -> REINFORCED_CONSTRUCTION_FOAM, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
        return LIQUID_REINFORCED_CONSTRUCTION_FOAM;
    }

    public static final ForgeFlowingFluid.Properties properties =
        new ForgeFlowingFluid.Properties(() -> REINFORCED_CONSTRUCTION_FOAM, () -> FLOWING_REINFORCED_CONSTRUCTION_FOAM, FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE))
        .block(() -> LIQUID_REINFORCED_CONSTRUCTION_FOAM);
}
