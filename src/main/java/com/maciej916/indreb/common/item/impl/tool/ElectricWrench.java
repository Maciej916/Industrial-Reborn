package com.maciej916.indreb.common.item.impl.tool;

import com.maciej916.indreb.common.api.enums.EnergyTier;
import com.maciej916.indreb.common.api.enums.EnergyType;
import com.maciej916.indreb.common.api.item.base.BaseElectricItem;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ElectricWrench extends BaseElectricItem {

    public ElectricWrench(int energyStored, int maxEnergy, EnergyTier energyTier) {
        super(new Item.Properties(), energyStored, maxEnergy, EnergyType.RECEIVE, energyTier);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        return WrenchHelper.onWrenchUse(world.getBlockState(pos), world, pos, pContext.getPlayer(), pContext.getClickedFace()) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}
