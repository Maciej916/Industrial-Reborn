package com.maciej916.indreb.common.item.impl.wrench;

import com.maciej916.indreb.common.item.base.ToolItem;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class Wrench extends ToolItem {
    public Wrench(int maxDamage) {
        super(maxDamage);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        return WrenchHelper.onWrenchUse(world.getBlockState(pos), world, pos, pContext.getPlayer(), pContext.getClickedFace()) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}
