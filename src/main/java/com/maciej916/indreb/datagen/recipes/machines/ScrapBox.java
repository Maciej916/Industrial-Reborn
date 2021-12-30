package com.maciej916.indreb.datagen.recipes.machines;

import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.datagen.recipes.builder.RecipeBuilderScrapBox;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ScrapBox extends RecipeProvider {

    public ScrapBox(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        RecipeBuilderScrapBox.builder(Items.WOODEN_HOE).setWeight(5.0F).save(consumer,"wooden_hoe");
        RecipeBuilderScrapBox.builder(Items.WOODEN_AXE).setWeight(5.0F).save(consumer,"wooden_axe");
        RecipeBuilderScrapBox.builder(Items.WOODEN_PICKAXE).setWeight(5.0F).save(consumer,"wooden_pickaxe");
        RecipeBuilderScrapBox.builder(Items.WOODEN_SHOVEL).setWeight(5.0F).save(consumer,"wooden_shovel");
        RecipeBuilderScrapBox.builder(Items.WOODEN_SWORD).setWeight(5.0F).save(consumer,"wooden_sword");
        RecipeBuilderScrapBox.builder(Items.COAL).setWeight(0.8F).save(consumer,"coal");
        RecipeBuilderScrapBox.builder(Items.REDSTONE).setWeight(0.9F).save(consumer,"redstone");
        RecipeBuilderScrapBox.builder(Items.RAW_COPPER).setWeight(0.7F).save(consumer,"raw_copper");
        RecipeBuilderScrapBox.builder(Items.RAW_IRON).setWeight(0.5F).save(consumer,"raw_iron");
        RecipeBuilderScrapBox.builder(Items.RAW_GOLD).setWeight(0.5F).save(consumer,"raw_gold");
        RecipeBuilderScrapBox.builder(Items.DIAMOND).setWeight(0.2F).save(consumer,"diamond");
        RecipeBuilderScrapBox.builder(Items.EMERALD).setWeight(0.1F).save(consumer,"emerald");
        RecipeBuilderScrapBox.builder(ModItems.RAW_TIN).setWeight(0.7F).save(consumer,"raw_tin");
        RecipeBuilderScrapBox.builder(ModItems.RAW_LEAD).setWeight(0.8F).save(consumer,"raw_lead");
        RecipeBuilderScrapBox.builder(ModItems.SILVER_INGOT).setWeight(0.7F).save(consumer,"silver_ingot");
        RecipeBuilderScrapBox.builder(Items.STICK).setWeight(4.0F).save(consumer,"stick");
        RecipeBuilderScrapBox.builder(Items.SOUL_SAND).setWeight(1.0F).save(consumer,"soul_sand");
        RecipeBuilderScrapBox.builder(Items.SLIME_BALL).setWeight(0.6F).save(consumer,"slime_ball");
        RecipeBuilderScrapBox.builder(Items.OAK_SIGN).setWeight(1.0F).save(consumer,"oak_sign");
        RecipeBuilderScrapBox.builder(ModItems.RUBBER).setWeight(0.8F).save(consumer,"rubber");
        RecipeBuilderScrapBox.builder(Items.ROTTEN_FLESH).setWeight(2.0F).save(consumer,"rotten_flesh");
        RecipeBuilderScrapBox.builder(Items.APPLE).setWeight(2.0F).save(consumer,"apple");
        RecipeBuilderScrapBox.builder(Items.GOLDEN_APPLE).setWeight(0.3F).save(consumer,"golden_apple");
        RecipeBuilderScrapBox.builder(Items.ENCHANTED_GOLDEN_APPLE).setWeight(0.1F).save(consumer,"enchanted_golden_apple");
        RecipeBuilderScrapBox.builder(Items.LEATHER).setWeight(1.0F).save(consumer,"leather");
        RecipeBuilderScrapBox.builder(Items.GRAVEL).setWeight(3.0F).save(consumer,"gravel");
        RecipeBuilderScrapBox.builder(Items.GRASS_BLOCK).setWeight(3.0F).save(consumer,"grass_block");
        RecipeBuilderScrapBox.builder(Items.SAND).setWeight(3.0F).save(consumer,"sand");
        RecipeBuilderScrapBox.builder(ModItems.TIN_CAN).setWeight(1.5F).save(consumer,"tin_can");
        RecipeBuilderScrapBox.builder(Items.CAKE).setWeight(0.5F).save(consumer,"cake");
        RecipeBuilderScrapBox.builder(Items.BREAD).setWeight(1.5F).save(consumer,"bread");
        RecipeBuilderScrapBox.builder(Items.BONE).setWeight(1.0F).save(consumer,"bone");
        RecipeBuilderScrapBox.builder(Items.COOKED_BEEF).setWeight(0.8F).save(consumer,"cooked_beef");
        RecipeBuilderScrapBox.builder(Items.COOKED_PORKCHOP).setWeight(0.8F).save(consumer,"cooked_porkchop");
        RecipeBuilderScrapBox.builder(Items.COOKED_CHICKEN).setWeight(0.8F).save(consumer,"cooked_chicken");
        RecipeBuilderScrapBox.builder(Items.COOKED_COD).setWeight(0.8F).save(consumer,"cooked_cod");
        RecipeBuilderScrapBox.builder(Items.COOKED_MUTTON).setWeight(0.8F).save(consumer,"cooked_mutton");
        RecipeBuilderScrapBox.builder(Items.COOKED_RABBIT).setWeight(0.8F).save(consumer,"cooked_rabbit");
        RecipeBuilderScrapBox.builder(Items.COOKED_SALMON).setWeight(0.8F).save(consumer,"cooked_salmon");
        RecipeBuilderScrapBox.builder(Items.GLOWSTONE_DUST).setWeight(0.6F).save(consumer,"glowstone_dust");
        RecipeBuilderScrapBox.builder(Items.LAPIS_LAZULI).setWeight(0.6F).save(consumer,"lapis_lazuli");
        RecipeBuilderScrapBox.builder(Items.SWEET_BERRIES).setWeight(0.8F).save(consumer,"sweet_berries");
        RecipeBuilderScrapBox.builder(Items.GLOW_BERRIES).setWeight(0.8F).save(consumer,"glow_berries");
        RecipeBuilderScrapBox.builder(Items.HONEYCOMB).setWeight(0.4F).save(consumer,"honeycomb");
        RecipeBuilderScrapBox.builder(Items.HEART_OF_THE_SEA).setWeight(0.0002F).save(consumer,"heart_of_the_sea");
        RecipeBuilderScrapBox.builder(Items.TOTEM_OF_UNDYING).setWeight(0.0002F).save(consumer,"totem_of_undying");
        RecipeBuilderScrapBox.builder(Items.NETHER_STAR).setWeight(0.001F).save(consumer,"nether_star");
        RecipeBuilderScrapBox.builder(Items.AMETHYST_SHARD).setWeight(0.1F).save(consumer,"amethyst_shard");
        RecipeBuilderScrapBox.builder(Items.QUARTZ).setWeight(0.2F).save(consumer,"quartz");
        RecipeBuilderScrapBox.builder(Items.NETHERITE_SCRAP).setWeight(0.005F).save(consumer,"netherite_scrap");
        RecipeBuilderScrapBox.builder(ModItems.IRIDIUM_SHARD).setWeight(0.002F).save(consumer,"iridium_shard");



    }
}
