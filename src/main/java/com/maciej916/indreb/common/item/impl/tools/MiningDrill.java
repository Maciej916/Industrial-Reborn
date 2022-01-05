package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;
import com.maciej916.indreb.common.item.base.DiggerElectricItem;
import com.maciej916.indreb.common.registries.ModTiers;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class MiningDrill extends DiggerElectricItem {

    private final int energyCostMine;
    private final int energyCostHurt;

    public MiningDrill(Tier tier, float attackDamage, float attackSpeed, int energyStored, int maxEnergy, int energyCostMine, int energyCostHurt, EnergyType energyType, EnergyTier energyTier) {
        super(tier, attackDamage, attackSpeed, BlockTags.MINEABLE_WITH_PICKAXE, new Properties(), energyStored, maxEnergy, energyType, energyTier);
        this.energyCostMine = energyCostMine;
        this.energyCostHurt = energyCostHurt;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return getTier() == ModTiers.IRIDIUM ? Rarity.RARE : Rarity.COMMON;
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
        return (BlockTags.MINEABLE_WITH_PICKAXE.contains(blockState.getBlock()) || BlockTags.MINEABLE_WITH_SHOVEL.contains(blockState.getBlock())) && getEnergy(itemStack).consumeEnergy(getMineCost(), true) > 1 ? this.speed : 1.0F;
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
            return i < 1 && blockState.is(BlockTags.NEEDS_STONE_TOOL) ? false : (blockState.is(BlockTags.MINEABLE_WITH_PICKAXE) || blockState.is(BlockTags.MINEABLE_WITH_SHOVEL));
        }
    }

    // FORGE START
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state);
    }
}
