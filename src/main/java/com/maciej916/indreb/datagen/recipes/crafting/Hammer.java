package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Hammer extends RecipeProvider {

    public Hammer(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "hammer/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModItems.COPPER_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:ingots/copper"))
                .group(MODID)
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, saveResource("copper_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.TIN_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:ingots/tin"))
                .group(MODID)
                .unlockedBy("tin", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/tin_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.GOLD_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:ingots/gold"))
                .group(MODID)
                .unlockedBy("gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/gold_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.IRON_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:ingots/iron"))
                .group(MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/iron_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:alloys/bronze"))
                .group(MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/bronze_plate"));


        ShapelessRecipeBuilder.shapeless(ModItems.STEEL_PLATE)
                .requires(ModItems.HAMMER)
                .requires(ItemTags.bind("forge:ingots/steel"))
                .group(MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/steel_plate"));




    }

}