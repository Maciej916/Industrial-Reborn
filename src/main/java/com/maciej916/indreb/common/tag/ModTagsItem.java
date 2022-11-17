package com.maciej916.indreb.common.tag;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.maciej916.indreb.IndReb.MODID;

public final class ModTagsItem {

    public static final TagKey<Item> TREETAPS = itemTag("treetaps");
    public static final TagKey<Item> ELECTRICS = itemTag("electrics");
    public static final TagKey<Item> BATTERIES = itemTag("batteries");
    public static final TagKey<Item> WRENCHES = itemTag("wrenches");
    public static final TagKey<Item> UPGRADES = itemTag("upgrades");










    public static final TagKey<Item> FORGE_ORE_TIN = itemTag("forge", "ores/tin");
    public static final TagKey<Item> FORGE_ORE_SILVER = itemTag("forge", "ores/silver");
    public static final TagKey<Item> FORGE_ORE_URANIUM = itemTag("forge", "ores/uranium");
    public static final TagKey<Item> FORGE_ORE_LEAD = itemTag("forge", "ores/lead");

    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_RAW_TIN = itemTag("forge", "storage_blocks/raw_tin");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_RAW_SILVER = itemTag("forge", "storage_blocks/raw_silver");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_RAW_URANIUM = itemTag("forge", "storage_blocks/raw_uranium");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_RAW_LEAD = itemTag("forge", "storage_blocks/raw_lead");

    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_TIN = itemTag("forge", "storage_blocks/tin");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_SILVER = itemTag("forge", "storage_blocks/silver");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_URANIUM = itemTag("forge", "storage_blocks/uranium");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_LEAD = itemTag("forge", "storage_blocks/lead");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_STEEL = itemTag("forge", "storage_blocks/steel");
    public static final TagKey<Item> FORGE_STORAGE_BLOCKS_BRONZE = itemTag("forge", "storage_blocks/steel");

    public static final TagKey<Item> FORGE_RAW_MATERIALS_TIN = itemTag("forge", "raw_materials/tin");
    public static final TagKey<Item> FORGE_RAW_MATERIALS_SILVER = itemTag("forge", "raw_materials/silver");
    public static final TagKey<Item> FORGE_RAW_MATERIALS_URANIUM = itemTag("forge", "raw_materials/uranium");
    public static final TagKey<Item> FORGE_RAW_MATERIALS_LEAD = itemTag("forge", "raw_materials/lead");

    public static final TagKey<Item> FORGE_INGOTS_TIN = itemTag("forge", "ingots/tin");
    public static final TagKey<Item> FORGE_INGOTS_BRONZE = itemTag("forge", "ingots/bronze");
    public static final TagKey<Item> FORGE_INGOTS_STEEL = itemTag("forge", "ingots/steel");
    public static final TagKey<Item> FORGE_INGOTS_SILVER = itemTag("forge", "ingots/silver");
    public static final TagKey<Item> FORGE_INGOTS_LEAD = itemTag("forge", "ingots/lead");
    public static final TagKey<Item> FORGE_INGOTS_URANIUM = itemTag("forge", "ingots/uranium");

    public static final TagKey<Item> FORGE_PLATES_COPPER = itemTag("forge", "ingots/copper");
    public static final TagKey<Item> FORGE_PLATES_TIN = itemTag("forge", "ingots/tin");
    public static final TagKey<Item> FORGE_PLATES_IRON = itemTag("forge", "ingots/iron");
    public static final TagKey<Item> FORGE_PLATES_LEAD = itemTag("forge", "ingots/lead");
    public static final TagKey<Item> FORGE_PLATES_GOLD = itemTag("forge", "ingots/gold");
    public static final TagKey<Item> FORGE_PLATES_BRONZE = itemTag("forge", "ingots/bronze");
    public static final TagKey<Item> FORGE_PLATES_STEEL = itemTag("forge", "ingots/steel");
    public static final TagKey<Item> FORGE_PLATES_IRIDIUM = itemTag("forge", "ingots/iridium");
    public static final TagKey<Item> FORGE_PLATES_LAPIS = itemTag("forge", "ingots/lapis");

    public static final TagKey<Item> FORGE_DUSTS_TIN = itemTag("forge", "dusts/tin");
    public static final TagKey<Item> FORGE_DUSTS_COPPER = itemTag("forge", "dusts/copper");
    public static final TagKey<Item> FORGE_DUSTS_GOLD = itemTag("forge", "dusts/gold");
    public static final TagKey<Item> FORGE_DUSTS_LEAD = itemTag("forge", "dusts/lead");
    public static final TagKey<Item> FORGE_DUSTS_COAL = itemTag("forge", "dusts/coal");
    public static final TagKey<Item> FORGE_DUSTS_LAPIS = itemTag("forge", "dusts/lapis");
    public static final TagKey<Item> FORGE_DUSTS_DIAMOND = itemTag("forge", "dusts/diamond");
    public static final TagKey<Item> FORGE_DUSTS_STONE = itemTag("forge", "dusts/stone");
    public static final TagKey<Item> FORGE_DUSTS_DEEPSLATE = itemTag("forge", "dusts/deepslate");
    public static final TagKey<Item> FORGE_DUSTS_SULFUR = itemTag("forge", "dusts/sulfur");







    public static ResourceLocation rl(String path) {
        return new ResourceLocation(IndReb.MODID, path);
    }

    public static ResourceLocation rl(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    public static TagKey<Item> itemTag(String path) {
        return itemTag(MODID, path);
    }

    public static TagKey<Item> itemTag(String namespace, String path) {
        return ItemTags.create(new ResourceLocation(namespace, path));
    }








    public static final ResourceLocation TREETAPS_RES = new ResourceLocation(MODID, "treetaps");
    public static final ResourceLocation BATTERIES_RES = new ResourceLocation(MODID, "batteries");
    public static final ResourceLocation ELECTRICS_RES = new ResourceLocation(MODID, "electrics");
    public static final ResourceLocation WRENCH_RES = new ResourceLocation(MODID, "wrenches");
    public static final ResourceLocation UPGRADE_RES = new ResourceLocation(MODID, "upgrades");

    public static final ResourceLocation HELMET_RES = new ResourceLocation(MODID, "helmet");
    public static final ResourceLocation CHESTPLATE_RES = new ResourceLocation(MODID, "chestplate");
    public static final ResourceLocation LEGGINGS_RES = new ResourceLocation(MODID, "leggings");
    public static final ResourceLocation BOOTS_RES = new ResourceLocation(MODID, "boots");


    // MOD
    public static final ResourceLocation BASE_MACHINE_SIGN_TAG = new ResourceLocation(MODID, "base_machine_sign");
    public static final ResourceLocation REACTOR_COMPONENTS_TAG = new ResourceLocation(MODID, "reactor_components");
    public static final ResourceLocation REACTOR_FUSION_TAG = new ResourceLocation(MODID, "reactor_fusion");


    public static final TagKey<Item> BASE_MACHINE_SIGN = ItemTags.create(BASE_MACHINE_SIGN_TAG);
    public static final TagKey<Item> REACTOR_COMPONENTS = ItemTags.create(REACTOR_COMPONENTS_TAG);
    public static final TagKey<Item> REACTOR_FUSION = ItemTags.create(REACTOR_FUSION_TAG);




}
