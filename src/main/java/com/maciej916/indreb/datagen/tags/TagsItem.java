package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModTagsItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsItem extends ItemTagsProvider {

    public TagsItem(DataGenerator generator, BlockTagsProvider blockTagProvider, ExistingFileHelper helper) {
        super(generator, blockTagProvider, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(ItemTags.LEAVES)
                .add(ModItems.RUBBER_LEAVES.get())
                .replace(false)
        ;

        /* ORES */

        tag(ModTagsItem.FORGE_ORE_TIN)
                .add(ModItems.TIN_ORE.get())
                .add(ModItems.DEEPSLATE_TIN_ORE.get())
        ;

        tag(ModTagsItem.FORGE_ORE_LEAD)
                .add(ModItems.LEAD_ORE.get())
                .add(ModItems.DEEPSLATE_LEAD_ORE.get())
        ;

        tag(ModTagsItem.FORGE_ORE_URANIUM)
                .add(ModItems.URANIUM_ORE.get())
                .add(ModItems.DEEPSLATE_URANIUM_ORE.get())
        ;

        tag(ModTagsItem.FORGE_ORE_SILVER)
                .add(ModItems.SILVER_ORE.get())
                .add(ModItems.DEEPSLATE_SILVER_ORE.get())
        ;

        /* RAW STORAGE BLOCKS */

        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_RAW_TIN).add(ModItems.RAW_TIN_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_RAW_LEAD).add(ModItems.RAW_LEAD_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_RAW_URANIUM).add(ModItems.RAW_URANIUM_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_RAW_SILVER).add(ModItems.RAW_SILVER_BLOCK.get());

        /* STORAGE_BLOCKS */

        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_TIN).add(ModItems.TIN_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_SILVER).add(ModItems.SILVER_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_URANIUM).add(ModItems.URANIUM_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_LEAD).add(ModItems.LEAD_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_STEEL).add(ModItems.STEEL_BLOCK.get());
        tag(ModTagsItem.FORGE_STORAGE_BLOCKS_BRONZE).add(ModItems.BRONZE_BLOCK.get());
        tag(ModTagsItem.FORGE_RAW_MATERIALS_TIN).add(ModItems.RAW_TIN.get());
        tag(ModTagsItem.FORGE_RAW_MATERIALS_SILVER).add(ModItems.RAW_SILVER.get());
        tag(ModTagsItem.FORGE_RAW_MATERIALS_URANIUM).add(ModItems.RAW_URANIUM.get());
        tag(ModTagsItem.FORGE_RAW_MATERIALS_LEAD).add(ModItems.RAW_LEAD.get());

        /* INGOTS */

        tag(ModTagsItem.FORGE_INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_BRONZE).add(ModItems.BRONZE_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        tag(ModTagsItem.FORGE_INGOTS_URANIUM).add(ModItems.URANIUM_INGOT.get());

        /* PLATES */

        tag(ModTagsItem.FORGE_PLATES_COPPER).add(ModItems.COPPER_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_TIN).add(ModItems.TIN_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_IRON).add(ModItems.IRON_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_LEAD).add(ModItems.LEAD_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_GOLD).add(ModItems.GOLD_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_BRONZE).add(ModItems.BRONZE_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_STEEL).add(ModItems.STEEL_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_IRIDIUM).add(ModItems.IRIDIUM_PLATE.get());
        tag(ModTagsItem.FORGE_PLATES_LAPIS).add(ModItems.LAPIS_LAZULI_PLATE.get());

        /* DUSTS */

        tag(ModTagsItem.FORGE_DUSTS_TIN).add(ModItems.TIN_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_COPPER).add(ModItems.COPPER_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_GOLD).add(ModItems.GOLD_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_LEAD).add(ModItems.LEAD_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_COAL).add(ModItems.COAL_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_LAPIS).add(ModItems.LAPIS_LAZULI_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_DIAMOND).add(ModItems.DIAMOND_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_STONE).add(ModItems.STONE_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_DEEPSLATE).add(ModItems.DEEPSLATE_DUST.get());
        tag(ModTagsItem.FORGE_DUSTS_SULFUR).add(ModItems.SULFUR_DUST.get());

        /* RODS */

        tag(ModTagsItem.FORGE_RODS_IRON).add(ModItems.IRON_ROD.get());

        /* WRENCHES */

        tag(ModTagsItem.WRENCHES)
                .add(ModItems.WRENCH.get())
                .add(ModItems.ELECTRIC_WRENCH.get())
                .add(ModItems.MULTI_TOOL.get())
        ;

        /* TREETAPS */

        tag(ModTagsItem.TREETAPS)
                .add(ModItems.TREETAP.get())
                .add(ModItems.ELECTRIC_TREETAP.get())
        ;

        /* HELMET */

        tag(ModTagsItem.HELMET)
                .add(ModItems.NANO_HELMET.get())
                .add(ModItems.BRONZE_HELMET.get())
                .add(ModItems.NIGHTVISION_GOGGLES.get())
        ;

        /* CHESTPLATE */

        tag(ModTagsItem.CHESTPLATE)
                .add(ModItems.NANO_CHESTPLATE.get())
                .add(ModItems.BRONZE_CHESTPLATE.get())
        ;

        /* LEGGINGS */

        tag(ModTagsItem.CHESTPLATE)
                .add(ModItems.NANO_LEGGINGS.get())
                .add(ModItems.BRONZE_LEGGINGS.get())
        ;

        /* BOOTS */

        tag(ModTagsItem.BOOTS)
                .add(ModItems.NANO_BOOTS.get())
                .add(ModItems.BRONZE_BOOTS.get())
                .add(ModItems.RUBBER_BOOTS.get())
        ;


    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
