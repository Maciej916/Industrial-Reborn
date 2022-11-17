package com.maciej916.indreb.common.tag;

import com.maciej916.indreb.IndReb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModTagsBlock {






    public static final TagKey<Block> FORGE_ORE_TIN = blockTag("forge", "ores/tin");
    public static final TagKey<Block> FORGE_ORE_SILVER = blockTag("forge", "ores/silver");
    public static final TagKey<Block> FORGE_ORE_URANIUM = blockTag("forge", "ores/uranium");
    public static final TagKey<Block> FORGE_ORE_LEAD = blockTag("forge", "ores/lead");

    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_TIN = blockTag("forge", "storage_blocks/raw_tin");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_SILVER = blockTag("forge", "storage_blocks/raw_silver");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_URANIUM = blockTag("forge", "storage_blocks/raw_uranium");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_RAW_LEAD = blockTag("forge", "storage_blocks/raw_lead");

    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_TIN = blockTag("forge", "storage_blocks/tin");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_SILVER = blockTag("forge", "storage_blocks/silver");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_URANIUM = blockTag("forge", "storage_blocks/uranium");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_LEAD = blockTag("forge", "storage_blocks/lead");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_STEEL = blockTag("forge", "storage_blocks/steel");
    public static final TagKey<Block> FORGE_STORAGE_BLOCKS_BRONZE = blockTag("forge", "storage_blocks/steel");



    public static ResourceLocation rl(String path) {
        return new ResourceLocation(IndReb.MODID, path);
    }

    public static ResourceLocation rl(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    public static TagKey<Block> blockTag(String path) {
        return blockTag(IndReb.MODID, path);
    }

    public static TagKey<Block> blockTag(String namespace, String path) {
        return BlockTags.create(new ResourceLocation(namespace, path));
    }






}
