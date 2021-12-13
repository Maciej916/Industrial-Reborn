package com.maciej916.indreb.common.item.impl.tools;

import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnumEnergyType;
import com.maciej916.indreb.common.item.base.ElectricItem;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class MultiTool extends ElectricItem {

    public MultiTool(int energyStored, int maxEnergy, EnumEnergyType energyType, EnergyTier energyTier) {
        super(new Properties(), energyStored, maxEnergy, energyType, energyTier);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        return WrenchHelper.onWrenchUse(world.getBlockState(pos), world, pos, pContext.getPlayer(), pContext.getClickedFace()) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}
