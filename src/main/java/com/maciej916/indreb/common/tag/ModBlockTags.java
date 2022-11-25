package com.maciej916.indreb.common.tag;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBlockTags {


    public static final TagKey<Block> REACTOR_PART = creare("reactor_part");



    public static final TagKey<Block> FORGE_ORE_TIN = creare("forge", "ores/tin");
    public static final TagKey<Block> FORGE_ORE_SILVER = creare("forge", "ores/silver");
    public static final TagKey<Block> FORGE_ORE_URANIUM = creare("forge", "ores/uranium");
    public static final TagKey<Block> FORGE_ORE_LEAD = creare("forge", "ores/lead");

    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_TIN = creare("forge", "storage_blocks/raw_tin");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_SILVER = creare("forge", "storage_blocks/raw_silver");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_URANIUM = creare("forge", "storage_blocks/raw_uranium");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_LEAD = creare("forge", "storage_blocks/raw_lead");

    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_TIN = creare("forge", "storage_blocks/tin");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_SILVER = creare("forge", "storage_blocks/silver");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_URANIUM = creare("forge", "storage_blocks/uranium");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_LEAD = creare("forge", "storage_blocks/lead");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_STEEL = creare("forge", "storage_blocks/steel");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_BRONZE = creare("forge", "storage_blocks/steel");





    public static ResourceLocation rl(String path) {
        return new ResourceLocation(IndReb.MODID, path);
    }

    public static ResourceLocation rl(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    public static TagKey<Block> creare(String path) {
        return creare(IndReb.MODID, path);
    }

    public static TagKey<Block> creare(String namespace, String path) {
        return BlockTags.create(new ResourceLocation(namespace, path));
    }






}
