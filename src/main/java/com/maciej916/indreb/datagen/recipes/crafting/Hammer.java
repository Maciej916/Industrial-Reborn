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

        ShapelessRecipeBuilder.shapeless(ModItems.COPPER_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/copper")))
                .group(MODID)
                .unlockedBy("copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, saveResource("copper_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.TIN_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/tin")))
                .group(MODID)
                .unlockedBy("tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIN_INGOT.get()))
                .save(consumer, new ResourceLocation(MODID, "hammer/tin_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.GOLD_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/gold")))
                .group(MODID)
                .unlockedBy("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/gold_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.IRON_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/iron")))
                .group(MODID)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/iron_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/bronze")))
                .group(MODID)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer, new ResourceLocation(MODID, "hammer/bronze_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.STEEL_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/steel")))
                .group(MODID)
                .unlockedBy("steel_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STEEL_INGOT.get()))
                .save(consumer, new ResourceLocation(MODID, "hammer/steel_plate"));

        ShapelessRecipeBuilder.shapeless(ModItems.LEAD_PLATE.get())
                .requires(ModItems.HAMMER.get())
                .requires(ItemTags.create(new ResourceLocation("forge", "ingots/lead")))
                .group(MODID)
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEAD_PLATE.get()))
                .save(consumer, new ResourceLocation(MODID, "hammer/lead_plate"));




    }

}