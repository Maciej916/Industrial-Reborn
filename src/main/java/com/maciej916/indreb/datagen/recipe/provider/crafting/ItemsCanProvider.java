package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsCanProvider extends RecipeProvider {

    public ItemsCanProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/items/can/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.NUKA_COLA.get(), 1)
                .pattern("sss")
                .pattern("cdd")
                .define('s', Items.SUGAR)
                .define('c', ModItems.EMPTY_CAN.get())
                .define('d', ModItemTags.FORGE_DYES_BLUE)
                .group(MODID + "/items/can")
                .unlockedBy("sugar", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR))
                .unlockedBy("blue_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_DYE))
                .unlockedBy("empty_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .save(consumer, saveResource("nuka_cola"));

        ShapedRecipeBuilder.shaped(ModItems.SPRUNK.get(), 1)
                .pattern("sss")
                .pattern("cdd")
                .define('s', Items.SUGAR)
                .define('c', ModItems.EMPTY_CAN.get())
                .define('d', ModItemTags.FORGE_DYES_LIME)
                .group(MODID + "/items/can")
                .unlockedBy("sugar", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR))
                .unlockedBy("lime_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIME_DYE))
                .unlockedBy("empty_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .save(consumer, saveResource("sprunk"));

        ShapedRecipeBuilder.shaped(ModItems.ENERGY_DRINK.get(), 1)
                .pattern("sss")
                .pattern("cdd")
                .define('s', Items.SUGAR)
                .define('c', ModItems.EMPTY_CAN.get())
                .define('d', ModItemTags.FORGE_DYES_BLACK)
                .group(MODID + "/items/can")
                .unlockedBy("sugar", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SUGAR))
                .unlockedBy("black_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_DYE))
                .unlockedBy("empty_can", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMPTY_CAN.get()))
                .save(consumer, saveResource("energy_drink"));


    }
}