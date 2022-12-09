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

public class ExplosiveProvider extends RecipeProvider {

    public ExplosiveProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "crafting/explosive/" + name);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ModItems.INDUSTRIAL_TNT.get(), 2)
                .pattern("fff")
                .pattern("ttt")
                .pattern("fff")
                .define('f', ModItemTags.FORGE_DUSTS_SULFUR)
                .define('t', Items.TNT)
                .group(MODID + "/explosive")
                .unlockedBy("sulfur_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TNT))
                .save(consumer, saveResource("industrial_tnt"));

        ShapedRecipeBuilder.shaped(ModItems.INDUSTRIAL_TNT.get(), 2)
                .pattern("ftf")
                .pattern("ftf")
                .pattern("ftf")
                .define('f', ModItemTags.FORGE_DUSTS_SULFUR)
                .define('t', Items.TNT)
                .group(MODID + "/explosive")
                .unlockedBy("sulfur_dust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SULFUR_DUST.get()))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TNT))
                .save(consumer, saveResource("industrial_tnt_2"));

        ShapedRecipeBuilder.shaped(ModItems.NUKE.get(), 1)
                .pattern("tat")
                .pattern("ntn")
                .pattern("tat")
                .define('t', ModItems.INDUSTRIAL_TNT.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .define('n', ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .group(MODID + "/explosive")
                .unlockedBy("industrial_tnt", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INDUSTRIAL_TNT.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("iridium_neutron_reflector", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get()))
                .save(consumer, saveResource("nuke"));

        ShapedRecipeBuilder.shaped(ModItems.NUKE.get(), 1)
                .pattern("tnt")
                .pattern("ata")
                .pattern("tnt")
                .define('t', ModItems.INDUSTRIAL_TNT.get())
                .define('a', ModItems.ADVANCED_CIRCUIT.get())
                .define('n', ModItems.IRIDIUM_NEUTRON_REFLECTOR.get())
                .group(MODID + "/explosive")
                .unlockedBy("industrial_tnt", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INDUSTRIAL_TNT.get()))
                .unlockedBy("advanced_circuit", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ADVANCED_CIRCUIT.get()))
                .unlockedBy("iridium_neutron_reflector", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRIDIUM_NEUTRON_REFLECTOR.get()))
                .save(consumer, saveResource("nuke_2"));



    }
}