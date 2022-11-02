package com.maciej916.indreb.datagen.recipes.crafting;

import com.maciej916.indreb.common.registries.ModBlocks;
import com.maciej916.indreb.common.registries.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class Cables extends RecipeProvider {

    public Cables(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "cables/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(ModBlocks.COPPER_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.COPPER_CABLE.get())
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("copper_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CABLE.get()))
                .save(consumer, saveResource("copper_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.TIN_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.TIN_CABLE.get())
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("tin_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.TIN_CABLE.get()))
                .save(consumer, saveResource("tin_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.GOLD_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.GOLD_CABLE.get())
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("gold_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CABLE.get()))
                .save(consumer, saveResource("gold_cable_insulated"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.HV_CABLE_INSULATED.get())
                .requires(ModItems.RUBBER.get())
                .requires(ModBlocks.HV_CABLE.get())
                .group(MODID)
                .unlockedBy("rubber", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER.get()))
                .unlockedBy("hv_cable", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HV_CABLE.get()))
                .save(consumer, saveResource("hv_cable_insulated"));


        ShapedRecipeBuilder.shaped(ModBlocks.GLASS_FIBRE_CABLE.get())
                .pattern("ggg")
                .pattern("ese")
                .pattern("ggg")
                .define('g', Items.GLASS)
                .define('e', ModItems.ENERGIUM_DUST.get())
                .define('s', ItemTags.create(new ResourceLocation("forge", "dusts/silver")))
                .group(MODID)
                .unlockedBy("energium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENERGIUM_DUST.get()))
                .unlockedBy("silver_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_DUST.get()))
                .save(consumer, saveResource("glass_fibre_cable"));




    }

}