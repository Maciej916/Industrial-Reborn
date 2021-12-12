package com.maciej916.indreb.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StaticTagHelper;
import net.minecraft.tags.StaticTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModTags {

    public static final ResourceLocation TREETAPS_RES = new ResourceLocation(MODID, "treetaps");
    public static final ResourceLocation BATTERIES_RES = new ResourceLocation(MODID, "batteries");
    public static final ResourceLocation ELECTRICS_RES = new ResourceLocation(MODID, "electrics");
    public static final ResourceLocation WRENCH_RES = new ResourceLocation(MODID, "wrenches");

    public static final ResourceLocation HELMET_RES = new ResourceLocation(MODID, "helmet");
    public static final ResourceLocation CHESTPLATE_RES = new ResourceLocation(MODID, "chestplate");
    public static final ResourceLocation LEGGINGS_RES = new ResourceLocation(MODID, "leggings");
    public static final ResourceLocation BOOTS_RES = new ResourceLocation(MODID, "boots");

}
