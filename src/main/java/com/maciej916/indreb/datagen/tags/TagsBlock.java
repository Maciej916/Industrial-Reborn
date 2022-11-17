package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.tag.ModTagsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsBlock extends BlockTagsProvider {

    public TagsBlock(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(BlockTags.FENCES)
                .add(ModBlocks.IRON_FENCE.get())
                .add(ModBlocks.RUBBER_FENCE.get())
        ;

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.RUBBER_FENCE.get())
        ;

        tag(BlockTags.LEAVES)
                .add(ModBlocks.RUBBER_LEAVES.get())
        ;

        tag(BlockTags.LOGS)
                .add(ModBlocks.RUBBER_LOG.get())
        ;

        tag(BlockTags.PLANKS)
                .add(ModBlocks.RUBBER_PLANKS.get())
        ;

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.RUBBER_SAPLING.get())
        ;

        tag(BlockTags.WALLS)
                .add(ModBlocks.REINFORCED_STONE_BRICK_WALL.get())
        ;

        tag(BlockTags.DOORS)
                .add(ModBlocks.REINFORCED_DOOR.get())
                .add(ModBlocks.RUBBER_DOOR.get())
        ;

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.RUBBER_DOOR.get())
        ;

        tag(BlockTags.STAIRS)
                .add(ModBlocks.REINFORCED_STONE_STAIRS.get())
                .add(ModBlocks.RUBBER_STAIRS.get())
        ;

        tag(BlockTags.SLABS)
                .add(ModBlocks.REINFORCED_STONE_SLAB.get())
                .add(ModBlocks.RUBBER_SLAB.get())
        ;

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.RUBBER_SLAB.get())
        ;

        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.RUBBER_TRAP_DOOR.get())
        ;

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.RUBBER_TRAP_DOOR.get())
        ;

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.RUBBER_PLATE.get())
        ;

        /* ORES */

        tag(ModTagsBlock.FORGE_ORE_TIN)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
        ;

        tag(ModTagsBlock.FORGE_ORE_LEAD)
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
        ;

        tag(ModTagsBlock.FORGE_ORE_URANIUM)
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
        ;

        tag(ModTagsBlock.FORGE_ORE_SILVER)
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
        ;

        /* RAW STORAGE BLOCKS */

        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_RAW_TIN).add(ModBlocks.RAW_TIN_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_RAW_LEAD).add(ModBlocks.RAW_LEAD_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_RAW_URANIUM).add(ModBlocks.RAW_URANIUM_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_RAW_SILVER).add(ModBlocks.RAW_SILVER_BLOCK.get());

        /* STORAGE BLOCKS */

        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_URANIUM).add(ModBlocks.URANIUM_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_LEAD).add(ModBlocks.LEAD_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(ModTagsBlock.FORGE_STORAGE_BLOCKS_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());



    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
