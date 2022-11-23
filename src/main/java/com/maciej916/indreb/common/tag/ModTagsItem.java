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

    public static final TagKey<Item> HELMET = itemTag("helmet");
    public static final TagKey<Item> CHESTPLATE = itemTag("chestplate");
    public static final TagKey<Item> LEGGINGS = itemTag("leggings");
    public static final TagKey<Item> BOOTS = itemTag("boots");

    public static final TagKey<Item> REACTOR_COMPONENTS = itemTag("reactor_components");
    public static final TagKey<Item> BASE_MACHINE_SIGN = itemTag("base_machine_sign");
    public static final TagKey<Item> REACTOR_FUSION = itemTag("reactor_fusion");





    public static final TagKey<Item> FORGE_SAND = itemTag("forge", "sand");
    public static final TagKey<Item> FORGE_GRAVEL = itemTag("forge", "gravel");

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

    public static final TagKey<Item> FORGE_PLATES_COPPER = itemTag("forge", "plates/copper");
    public static final TagKey<Item> FORGE_PLATES_TIN = itemTag("forge", "plates/tin");
    public static final TagKey<Item> FORGE_PLATES_IRON = itemTag("forge", "plates/iron");
    public static final TagKey<Item> FORGE_PLATES_LEAD = itemTag("forge", "plates/lead");
    public static final TagKey<Item> FORGE_PLATES_GOLD = itemTag("forge", "plates/gold");
    public static final TagKey<Item> FORGE_PLATES_BRONZE = itemTag("forge", "plates/bronze");
    public static final TagKey<Item> FORGE_PLATES_STEEL = itemTag("forge", "plates/steel");
    public static final TagKey<Item> FORGE_PLATES_IRIDIUM = itemTag("forge", "plates/iridium");
    public static final TagKey<Item> FORGE_PLATES_LAPIS = itemTag("forge", "plates/lapis");

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

    public static final TagKey<Item> FORGE_RODS_IRON = itemTag("forge", "rods/iron");







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
}
