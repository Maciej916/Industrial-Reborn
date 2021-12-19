package com.maciej916.indreb.common.fluids;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ConstructionFoam {

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluids/construction_foam_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(IndReb.MODID,"/block/fluids/construction_foam_flow");

    public static FlowingFluid CONSTRUCTION_FOAM;
    public static FlowingFluid FLOWING_CONSTRUCTION_FOAM;
    public static LiquidBlock LIQUID_CONSTRUCTION_FOAM;
//    public static Item BUCKET_CONSTRUCTION_FOAM;

    public static FlowingFluid ConstructionFoamFluid() {
        CONSTRUCTION_FOAM = new ForgeFlowingFluid.Source(properties);
        return CONSTRUCTION_FOAM;
    }

    public static FlowingFluid FlowingConstructionFoamFluid() {
        FLOWING_CONSTRUCTION_FOAM = new ForgeFlowingFluid.Flowing(properties);
        return FLOWING_CONSTRUCTION_FOAM;
    }

    public static LiquidBlock LiquidConstructionFoam() {
        LIQUID_CONSTRUCTION_FOAM = new LiquidBlock(() -> CONSTRUCTION_FOAM, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
        return LIQUID_CONSTRUCTION_FOAM;
    }

//    public static Item BucketConstructionFoam(){
//        BUCKET_CONSTRUCTION_FOAM = new BucketItem(() -> NAPHTHA, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModItemGroups.MAIN_ITEM_GROUP));
//        return BUCKET_CONSTRUCTION_FOAM;
//    }


    public static final ForgeFlowingFluid.Properties properties =
        new ForgeFlowingFluid.Properties(() -> CONSTRUCTION_FOAM, () -> FLOWING_CONSTRUCTION_FOAM, FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE))
//                    .bucket(() -> BUCKET_CONSTRUCTION_FOAM)
        .block(() -> LIQUID_CONSTRUCTION_FOAM);
}
