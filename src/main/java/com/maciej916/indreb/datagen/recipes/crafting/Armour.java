package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Armour extends RecipeProvider {

    public Armour(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HELMET)
                .pattern("bbb")
                .pattern("b b")
                .define('b', ModItems.BRONZE_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_helmet"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_CHESTPLATE)
                .pattern("b b")
                .pattern("bbb")
                .pattern("bbb")
                .define('b', ModItems.BRONZE_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_chestplate"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_LEGGINGS)
                .pattern("bbb")
                .pattern("b b")
                .pattern("b b")
                .define('b', ModItems.BRONZE_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_leggings"));


        ShapedRecipeBuilder.shaped(ModItems.BRONZE_BOOTS)
                .pattern("b b")
                .pattern("b b")
                .define('b', ModItems.BRONZE_INGOT)
                .group(MODID)
                .unlockedBy("item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BRONZE_INGOT))
                .save(consumer, saveResource("bronze_boots"));



    }

}