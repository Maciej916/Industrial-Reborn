package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.registries.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsItem extends ItemTagsProvider {

    public TagsItem(DataGenerator generator, BlockTagsProvider blockTagProvider, ExistingFileHelper helper) {
        super(generator, blockTagProvider, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(ModTags.BASE_MACHINE_SIGN)
                .add(ModItems.YELLOW_STRIPES_BLOCK_LEFT.get())
                .add(ModItems.YELLOW_STRIPES_BLOCK_RIGHT.get())
                .add(ModItems.RADIOACTIVE_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.BIO_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.EXPLOSION_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.FIRE_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.ACID_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.MAGIC_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.FROST_HAZARD_SIGN_BLOCK.get())
                .add(ModItems.NOISE_HAZARD_SIGN_BLOCK.get())
        ;


        // PLATES FORGE

        tag(ModTags.FORGE_PLATES_COPPER).add(ModItems.COPPER_PLATE.get());
        tag(ModTags.FORGE_PLATES_TIN).add(ModItems.TIN_PLATE.get());
        tag(ModTags.FORGE_PLATES_IRON).add(ModItems.IRON_PLATE.get());
        tag(ModTags.FORGE_PLATES_LEAD).add(ModItems.LEAD_PLATE.get());
        tag(ModTags.FORGE_PLATES_GOLD).add(ModItems.GOLD_PLATE.get());
        tag(ModTags.FORGE_PLATES_BRONZE).add(ModItems.BRONZE_PLATE.get());
        tag(ModTags.FORGE_PLATES_STEEL).add(ModItems.STEEL_PLATE.get());
        tag(ModTags.FORGE_PLATES_IRIDIUM).add(ModItems.IRIDIUM_PLATE.get());
        tag(ModTags.FORGE_PLATES_LAPIS).add(ModItems.LAPIS_LAZULI_PLATE.get());



    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
