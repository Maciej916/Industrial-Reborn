package com.maciej916.indreb.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModTags {

    public static final ResourceLocation TREETAPS_RES = new ResourceLocation(MODID, "treetaps");
    public static final ResourceLocation BATTERIES_RES = new ResourceLocation(MODID, "batteries");
    public static final ResourceLocation ELECTRICS_RES = new ResourceLocation(MODID, "electrics");
    public static final ResourceLocation WRENCH_RES = new ResourceLocation(MODID, "wrenches");
//    public static final ResourceLocation UPGRADE_RES = new ResourceLocation(MODID, "upgrades");

    public static final ResourceLocation HELMET_RES = new ResourceLocation(MODID, "helmet");
    public static final ResourceLocation CHESTPLATE_RES = new ResourceLocation(MODID, "chestplate");
    public static final ResourceLocation LEGGINGS_RES = new ResourceLocation(MODID, "leggings");
    public static final ResourceLocation BOOTS_RES = new ResourceLocation(MODID, "boots");



    // MOD
    public static final ResourceLocation BASE_MACHINE_SIGN_TAG = new ResourceLocation(MODID, "base_machine_sign");


    public static final TagKey<Item> BASE_MACHINE_SIGN = ItemTags.create(BASE_MACHINE_SIGN_TAG);


    // FORGE PLATES
    public static final ResourceLocation FORGE_TAG_PLATES_COPPER = new ResourceLocation("forge", "plates/copper");
    public static final ResourceLocation FORGE_TAG_PLATES_TIN = new ResourceLocation("forge", "plates/tin");
    public static final ResourceLocation FORGE_TAG_PLATES_IRON = new ResourceLocation("forge", "plates/iron");
    public static final ResourceLocation FORGE_TAG_PLATES_LEAD = new ResourceLocation("forge", "plates/lead");
    public static final ResourceLocation FORGE_TAG_PLATES_GOLD = new ResourceLocation("forge", "plates/gold");
    public static final ResourceLocation FORGE_TAG_PLATES_BRONZE = new ResourceLocation("forge", "plates/bronze");
    public static final ResourceLocation FORGE_TAG_PLATES_STEEL = new ResourceLocation("forge", "plates/steel");
    public static final ResourceLocation FORGE_TAG_PLATES_IRIDIUM = new ResourceLocation("forge", "plates/iridium");
    public static final ResourceLocation FORGE_TAG_PLATES_LAPIS = new ResourceLocation("forge", "plates/lapis");

    public static final TagKey<Item> FORGE_PLATES_COPPER = ItemTags.create(FORGE_TAG_PLATES_COPPER);
    public static final TagKey<Item> FORGE_PLATES_TIN = ItemTags.create(FORGE_TAG_PLATES_TIN);
    public static final TagKey<Item> FORGE_PLATES_IRON = ItemTags.create(FORGE_TAG_PLATES_IRON);
    public static final TagKey<Item> FORGE_PLATES_LEAD = ItemTags.create(FORGE_TAG_PLATES_LEAD);
    public static final TagKey<Item> FORGE_PLATES_GOLD = ItemTags.create(FORGE_TAG_PLATES_GOLD);
    public static final TagKey<Item> FORGE_PLATES_BRONZE = ItemTags.create(FORGE_TAG_PLATES_BRONZE);
    public static final TagKey<Item> FORGE_PLATES_STEEL = ItemTags.create(FORGE_TAG_PLATES_STEEL);
    public static final TagKey<Item> FORGE_PLATES_IRIDIUM = ItemTags.create(FORGE_TAG_PLATES_IRIDIUM);
    public static final TagKey<Item> FORGE_PLATES_LAPIS = ItemTags.create(FORGE_TAG_PLATES_LAPIS);
}
