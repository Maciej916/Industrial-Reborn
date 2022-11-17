package com.maciej916.indreb.common.util;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.tier.CableTier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class Constants {

    public static final Direction[] DIRECTIONS = Direction.values();
    public static final CableTier[] CABLE_TIERS = CableTier.values();

    public static final ResourceLocation JEI = new ResourceLocation(IndReb.MODID, "textures/gui/container/jei.png");
    public static final ResourceLocation JEI_2 = new ResourceLocation(IndReb.MODID, "textures/gui/container/jei_2.png");
    public static final ResourceLocation JEI_LARGE = new ResourceLocation(IndReb.MODID, "textures/gui/container/jei_large.png");
    public static final ResourceLocation JEI_LARGE_2 = new ResourceLocation(IndReb.MODID, "textures/gui/container/jei_large2.png");
    public static final ResourceLocation COMMON = new ResourceLocation(IndReb.MODID, "textures/gui/container/common.png");
    public static final ResourceLocation PROCESS = new ResourceLocation(IndReb.MODID, "textures/gui/container/process.png");
    public static final ResourceLocation BUTTONS = new ResourceLocation(IndReb.MODID, "textures/gui/container/buttons.png");


    public static final Material FOAM_BLOCK = (new Material.Builder(MaterialColor.COLOR_LIGHT_GRAY)).nonSolid().noCollider().build();
    public static final Material REINFORCED_FOAM_BLOCK = (new Material.Builder(MaterialColor.COLOR_GRAY)).nonSolid().noCollider().build();
}
