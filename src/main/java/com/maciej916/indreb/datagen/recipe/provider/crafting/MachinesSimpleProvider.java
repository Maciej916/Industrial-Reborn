package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
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

public class MachinesSimpleProvider extends RecipeProvider {

    public MachinesSimpleProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/machine/simple/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.SIMPLE_CRUSHER.get())
                .pattern("iii")
                .pattern("fCf")
                .pattern("xxx")
                .define('f', Items.FLINT)
                .define('C', Items.FURNACE)
                .define('x', Items.SMOOTH_STONE)
                .define('i', ModItemTags.FORGE_INGOTS_IRON)
                .group(MODID + "/machines/simple")
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer, saveResource("simple_crusher"));

        ShapedRecipeBuilder.shaped(ModBlocks.SIMPLE_COMPRESSOR.get())
                .pattern("iii")
                .pattern("sCs")
                .pattern("xxx")
                .define('C', Items.FURNACE)
                .define('x', Items.SMOOTH_STONE)
                .define('s', ModItemTags.FORGE_COBBLESTONE)
                .define('i', ModItemTags.FORGE_INGOTS_IRON)
                .group(MODID + "/machines/simple")
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer, saveResource("simple_compressor"));

        ShapedRecipeBuilder.shaped(ModBlocks.SIMPLE_EXTRACTOR.get())
                .pattern("iii")
                .pattern("TCT")
                .pattern("xxx")
                .define('C', Items.FURNACE)
                .define('x', Items.SMOOTH_STONE)
                .define('T', ModItems.TREETAP.get())
                .define('i', ModItemTags.FORGE_INGOTS_IRON)
                .group(MODID + "/machines/simple")
                .unlockedBy("furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer, saveResource("simple_extractor"));

    }
}