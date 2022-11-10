package com.maciej916.indreb.datagen.recipes.advanced_crafting.shaped;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderAdvancedShaped;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.maciej916.indreb.IndReb.MODID;

public class ItemsA extends RecipeProvider {

    public ItemsA(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation saveResource(String name) {
        return new ResourceLocation(MODID, "items_a/" + name);
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {



//        RecipeBuilderAdvancedShaped.shaped(Items.BEDROCK, 1)
//                .pattern("mm ")
//                .pattern("mm ")
//                .define('m', ModItems.LAPIS_LAZULI_DUST.get(), 2)
//                .group(MODID)
//                .unlockedBy("mud_pile", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MUD_PILE.get()))
//                .save(consumer, saveResource("mud"));
//
//        RecipeBuilderAdvancedShaped.shaped(Items.BEDROCK, 1)
//                .pattern("mm ")
//                .pattern("mm ")
//                .define('m', ItemTags.create(new ResourceLocation("forge", "dusts/lapis_lazuli")), 4)
//                .group(MODID)
//                .unlockedBy("mud_pile", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MUD_PILE.get()))
//                .save(consumer, saveResource("mud_2"));

    }

}