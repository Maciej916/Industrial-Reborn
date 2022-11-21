package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.item.base.BaseElectricDiggerItem;
import com.maciej916.indreb.common.api.tier.CustomTiers;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class ElectricDrill extends BaseElectricDiggerItem {

    private final int energyCostMine;
    private final int energyCostHurt;

    public ElectricDrill(Tier tier, float attackDamage, float attackSpeed, int energyStored, int maxEnergy, int energyCostMine, int energyCostHurt, EnergyTier energyTier) {
        super(tier, attackDamage, attackSpeed, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties(), energyStored, maxEnergy, EnergyType.RECEIVE, energyTier);
        this.energyCostMine = energyCostMine;
        this.energyCostHurt = energyCostHurt;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return getTier() == CustomTiers.IRIDIUM ? Rarity.RARE : Rarity.COMMON;
    }

    @Override
    public int getHurtEnergyCost() {
        return energyCostMine;
    }

    @Override
    public int getMineCost() {
        return energyCostHurt;
    }


    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.MINEABLE_WITH_PICKAXE).contains(blockState.getBlock()) || ForgeRegistries.BLOCKS.tags().getTag(BlockTags.MINEABLE_WITH_SHOVEL).contains(blockState.getBlock())) && getEnergyStorage(itemStack).consumeEnergy(getMineCost(), true) > 1 ? this.speed : 1.0F;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeModeTab.TAB_TOOLS) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }

    @Override
    @Deprecated // FORGE: Use stack sensitive variant below
    public boolean isCorrectToolForDrops(BlockState blockState) {
        if (net.minecraftforge.common.TierSortingRegistry.isTierSorted(getTier())) {
            return net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), blockState) && (blockState.is(BlockTags.MINEABLE_WITH_PICKAXE) || blockState.is(BlockTags.MINEABLE_WITH_SHOVEL));
        }
        int i = this.getTier().getLevel();
        if (i < 3 && blockState.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (i < 2 && blockState.is(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        } else {
            return (i >= 1 || !blockState.is(BlockTags.NEEDS_STONE_TOOL)) && (blockState.is(BlockTags.MINEABLE_WITH_PICKAXE) || blockState.is(BlockTags.MINEABLE_WITH_SHOVEL));
        }
    }

    // FORGE START
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state);
    }
}
