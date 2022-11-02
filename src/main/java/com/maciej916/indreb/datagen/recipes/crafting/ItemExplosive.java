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
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemExplosive extends RecipeProvider {

    public ItemExplosive(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "item/explosive/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        ShapedRecipeBuilder.shaped(ModItems.INDUSTRIAL_TNT.get(), 2)
                .pattern("fff")
                .pattern("ttt")
                .pattern("fff")
                .define('f', Items.FLINT)
                .define('t', Items.TNT)
                .group(MODID+ "_explosive")
                .unlockedBy("flint", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TNT))
                .save(consumer, saveResource("industrial_tnt_1"));

        ShapedRecipeBuilder.shaped(ModItems.INDUSTRIAL_TNT.get(), 2)
                .pattern("ftf")
                .pattern("ftf")
                .pattern("ftf")
                .define('f', Items.FLINT)
                .define('t', Items.TNT)
                .group(MODID+ "_explosive")
                .unlockedBy("flint", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TNT))
                .save(consumer, saveResource("industrial_tnt_2"));


    }

}