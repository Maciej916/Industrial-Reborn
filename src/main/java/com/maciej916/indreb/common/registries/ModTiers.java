package com.maciej916.indreb.common.registries;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {

    public static final ForgeTier BRONZE = new ForgeTier(2, 280, 7.0F, 2.0F, 14, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT));
    public static final ForgeTier IRIDIUM = new ForgeTier(4, 3046, 14.0F, 4.0F, 15, Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.IRIDIUM));


}
