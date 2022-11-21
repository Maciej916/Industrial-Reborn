package com.maciej916.indreb.common.api.tier;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModTagsItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class CustomTiers {

    public static final ForgeTier BRONZE = new ForgeTier(2, 280, 7.0F, 2.0F, 14, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModTagsItem.FORGE_INGOTS_BRONZE));
    public static final ForgeTier IRIDIUM = new ForgeTier(4, 3046, 30.0F, 4.0F, 15, Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.IRIDIUM.get()));


}
