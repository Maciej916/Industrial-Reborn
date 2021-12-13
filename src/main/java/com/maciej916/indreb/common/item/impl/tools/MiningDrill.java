package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.item.base.DiggerElectricItem;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class MiningDrill extends DiggerElectricItem {

    private final int energyCostMine;
    private final int energyCostHurt;

    public MiningDrill(Tier tier, float attackDamage, float attackSpeed, int energyStored, int maxEnergy, int energyCostMine, int energyCostHurt, EnumEnergyType energyType, EnergyTier energyTier) {
        super(tier, attackDamage, attackSpeed, BlockTags.MINEABLE_WITH_PICKAXE, new Properties(), energyStored, maxEnergy, energyType, energyTier);
        this.energyCostMine = energyCostMine;
        this.energyCostHurt = energyCostHurt;
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
}
