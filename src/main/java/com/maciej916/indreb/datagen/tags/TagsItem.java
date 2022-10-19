package com.maciej916.indreb.datagen.tags;

import com.maciej916.indreb.IndReb;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsItem extends ItemTagsProvider {

    public TagsItem(DataGenerator generator, BlockTagsProvider blockTagProvider, ExistingFileHelper helper) {
        super(generator, blockTagProvider, IndReb.MODID, helper);
    }

    @Override
    protected void addTags() {


    }

    @Override
    public String getName() {
        return "Industrial Reborn Tags";
    }

}
