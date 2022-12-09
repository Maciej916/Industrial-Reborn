package com.maciej916.indreb.datagen.recipe.provider.crafting;

import com.maciej916.indreb.common.block.ModBlocks;
import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.common.tag.ModItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class WoodProvider extends RecipeProvider {

    public WoodProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/wood/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_STAIRS.get(), 4)
                .pattern("p  ")
                .pattern("pp ")
                .pattern("ppp")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_stairs"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_SLAB.get(), 6)
                .pattern("ppp")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_slab"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_WOOD.get(), 3)
                .pattern("ww ")
                .pattern("ww ")
                .define('w', ModBlocks.RUBBER_LOG.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .save(consumer, saveResource("rubber_wood"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_PLATE.get(), 1)
                .pattern("pp ")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_plate"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_DOOR.get(), 1)
                .pattern("pp ")
                .pattern("pp ")
                .pattern("pp ")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_door"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_FENCE.get(), 3)
                .pattern("psp")
                .pattern("psp")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer, saveResource("rubber_fence"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_GATE.get(), 1)
                .pattern("sps")
                .pattern("sps")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .define('s', ModItemTags.FORGE_RODS_WOODEN)
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer, saveResource("rubber_gate"));

        ShapedRecipeBuilder.shaped(ModBlocks.RUBBER_TRAP_DOOR.get(), 2)
                .pattern("ppp")
                .pattern("ppp")
                .define('p', ModBlocks.RUBBER_PLANKS.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_planks", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_PLANKS.get()))
                .save(consumer, saveResource("rubber_trap_door"));

        ShapelessRecipeBuilder.shapeless(ModItems.RUBBER_PLANKS.get(), 4)
                .requires(ModItems.RUBBER_LOG.get())
                .group(MODID + "/wood/rubber")
                .unlockedBy("rubber_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RUBBER_LOG.get()))
                .save(consumer, saveResource("rubber_planks"));



    }
}