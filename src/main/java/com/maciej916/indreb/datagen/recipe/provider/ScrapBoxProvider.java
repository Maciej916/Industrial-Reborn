package com.maciej916.indreb.datagen.recipe.provider;

import com.maciej916.indreb.common.item.ModItems;
import com.maciej916.indreb.datagen.recipe.builder.ScrapBoxRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ScrapBoxProvider extends RecipeProvider {

    public ScrapBoxProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ScrapBoxRecipeBuilder.builder(Items.WOODEN_HOE).setWeight(5.0F).save(consumer,"wooden_hoe");
        ScrapBoxRecipeBuilder.builder(Items.WOODEN_AXE).setWeight(5.0F).save(consumer,"wooden_axe");
        ScrapBoxRecipeBuilder.builder(Items.WOODEN_PICKAXE).setWeight(5.0F).save(consumer,"wooden_pickaxe");
        ScrapBoxRecipeBuilder.builder(Items.WOODEN_SHOVEL).setWeight(5.0F).save(consumer,"wooden_shovel");
        ScrapBoxRecipeBuilder.builder(Items.WOODEN_SWORD).setWeight(5.0F).save(consumer,"wooden_sword");
        ScrapBoxRecipeBuilder.builder(Items.COAL).setWeight(0.8F).save(consumer,"coal");
        ScrapBoxRecipeBuilder.builder(Items.REDSTONE).setWeight(0.9F).save(consumer,"redstone");
        ScrapBoxRecipeBuilder.builder(Items.RAW_COPPER).setWeight(0.7F).save(consumer,"raw_copper");
        ScrapBoxRecipeBuilder.builder(Items.RAW_IRON).setWeight(0.5F).save(consumer,"raw_iron");
        ScrapBoxRecipeBuilder.builder(Items.RAW_GOLD).setWeight(0.5F).save(consumer,"raw_gold");
        ScrapBoxRecipeBuilder.builder(Items.DIAMOND).setWeight(0.2F).save(consumer,"diamond");
        ScrapBoxRecipeBuilder.builder(Items.EMERALD).setWeight(0.1F).save(consumer,"emerald");
        ScrapBoxRecipeBuilder.builder(ModItems.RAW_TIN).setWeight(0.7F).save(consumer,"raw_tin");
        ScrapBoxRecipeBuilder.builder(ModItems.RAW_LEAD).setWeight(0.8F).save(consumer,"raw_lead");
        ScrapBoxRecipeBuilder.builder(ModItems.SILVER_INGOT).setWeight(0.7F).save(consumer,"silver_ingot");
        ScrapBoxRecipeBuilder.builder(Items.STICK).setWeight(4.0F).save(consumer,"stick");
        ScrapBoxRecipeBuilder.builder(Items.SOUL_SAND).setWeight(1.0F).save(consumer,"soul_sand");
        ScrapBoxRecipeBuilder.builder(Items.SLIME_BALL).setWeight(0.6F).save(consumer,"slime_ball");
        ScrapBoxRecipeBuilder.builder(Items.OAK_SIGN).setWeight(1.0F).save(consumer,"oak_sign");
        ScrapBoxRecipeBuilder.builder(ModItems.RUBBER).setWeight(0.8F).save(consumer,"rubber");
        ScrapBoxRecipeBuilder.builder(Items.ROTTEN_FLESH).setWeight(2.0F).save(consumer,"rotten_flesh");
        ScrapBoxRecipeBuilder.builder(Items.APPLE).setWeight(2.0F).save(consumer,"apple");
        ScrapBoxRecipeBuilder.builder(Items.GOLDEN_APPLE).setWeight(0.3F).save(consumer,"golden_apple");
        ScrapBoxRecipeBuilder.builder(Items.ENCHANTED_GOLDEN_APPLE).setWeight(0.1F).save(consumer,"enchanted_golden_apple");
        ScrapBoxRecipeBuilder.builder(Items.LEATHER).setWeight(1.0F).save(consumer,"leather");
        ScrapBoxRecipeBuilder.builder(Items.GRAVEL).setWeight(3.0F).save(consumer,"gravel");
        ScrapBoxRecipeBuilder.builder(Items.GRASS_BLOCK).setWeight(3.0F).save(consumer,"grass_block");
        ScrapBoxRecipeBuilder.builder(Items.SAND).setWeight(3.0F).save(consumer,"sand");
        ScrapBoxRecipeBuilder.builder(ModItems.EMPTY_CAN).setWeight(1.5F).save(consumer,"tin_can");
        ScrapBoxRecipeBuilder.builder(Items.CAKE).setWeight(0.5F).save(consumer,"cake");
        ScrapBoxRecipeBuilder.builder(Items.BREAD).setWeight(1.5F).save(consumer,"bread");
        ScrapBoxRecipeBuilder.builder(Items.BONE).setWeight(1.0F).save(consumer,"bone");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_BEEF).setWeight(0.8F).save(consumer,"cooked_beef");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_PORKCHOP).setWeight(0.8F).save(consumer,"cooked_porkchop");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_CHICKEN).setWeight(0.8F).save(consumer,"cooked_chicken");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_COD).setWeight(0.8F).save(consumer,"cooked_cod");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_MUTTON).setWeight(0.8F).save(consumer,"cooked_mutton");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_RABBIT).setWeight(0.8F).save(consumer,"cooked_rabbit");
        ScrapBoxRecipeBuilder.builder(Items.COOKED_SALMON).setWeight(0.8F).save(consumer,"cooked_salmon");
        ScrapBoxRecipeBuilder.builder(Items.GLOWSTONE_DUST).setWeight(0.6F).save(consumer,"glowstone_dust");
        ScrapBoxRecipeBuilder.builder(Items.LAPIS_LAZULI).setWeight(0.6F).save(consumer,"lapis_lazuli");
        ScrapBoxRecipeBuilder.builder(Items.SWEET_BERRIES).setWeight(0.8F).save(consumer,"sweet_berries");
        ScrapBoxRecipeBuilder.builder(Items.GLOW_BERRIES).setWeight(0.8F).save(consumer,"glow_berries");
        ScrapBoxRecipeBuilder.builder(Items.HONEYCOMB).setWeight(0.4F).save(consumer,"honeycomb");
        ScrapBoxRecipeBuilder.builder(Items.HEART_OF_THE_SEA).setWeight(0.0002F).save(consumer,"heart_of_the_sea");
        ScrapBoxRecipeBuilder.builder(Items.TOTEM_OF_UNDYING).setWeight(0.0002F).save(consumer,"totem_of_undying");
        ScrapBoxRecipeBuilder.builder(Items.NETHER_STAR).setWeight(0.001F).save(consumer,"nether_star");
        ScrapBoxRecipeBuilder.builder(Items.AMETHYST_SHARD).setWeight(0.1F).save(consumer,"amethyst_shard");
        ScrapBoxRecipeBuilder.builder(Items.QUARTZ).setWeight(0.2F).save(consumer,"quartz");
        ScrapBoxRecipeBuilder.builder(Items.NETHERITE_SCRAP).setWeight(0.005F).save(consumer,"netherite_scrap");
        ScrapBoxRecipeBuilder.builder(ModItems.IRIDIUM_SHARD).setWeight(0.002F).save(consumer,"iridium_shard");      
        ScrapBoxRecipeBuilder.builder(ModItems.CANNED_FOOD).setWeight(0.1f).save(consumer,"canned_food");
        ScrapBoxRecipeBuilder.builder(ModItems.CANNED_POISON).setWeight(0.2f).save(consumer,"canned_poison");
        ScrapBoxRecipeBuilder.builder(ModItems.CANNED_HUNGER).setWeight(0.2f).save(consumer,"canned_hunger");
        ScrapBoxRecipeBuilder.builder(ModItems.NUKA_COLA).setWeight(0.15f).save(consumer,"nuka_cola");
        ScrapBoxRecipeBuilder.builder(ModItems.SPRUNK).setWeight(0.15f).save(consumer,"sprunk");
        ScrapBoxRecipeBuilder.builder(ModItems.ENERGY_DRINK).setWeight(0.15f).save(consumer,"energy_drink");

    }
}
