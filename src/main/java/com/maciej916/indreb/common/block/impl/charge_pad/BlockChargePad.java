package com.maciej916.indreb.common.block.impl.charge_pad;

import com.maciej916.indreb.common.block.IndRebEntityBlock;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.interfaces.block.IHasMenu;
import com.maciej916.indreb.common.interfaces.block.IStateActive;
import com.maciej916.indreb.common.interfaces.block.IStateFacing;
import com.maciej916.indreb.common.tier.ChargePadTier;
import com.maciej916.indreb.common.util.BlockStateHelper;
import com.maciej916.indreb.common.util.TextComponentUtil;
import com.maciej916.indreb.common.util.wrench.WrenchHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockChargePad extends IndRebEntityBlock implements IStateFacing, IHasMenu, IStateActive {

    private final ChargePadTier chargePadTier;

    public BlockChargePad(ChargePadTier chargePadTier, Properties properties) {
        super(properties);
        this.chargePadTier = chargePadTier;
        WrenchHelper.registerAction(this).add(WrenchHelper.rotationAction()).add(WrenchHelper.dropAction());
    }

    public ChargePadTier getChargePadTier() {
        return chargePadTier;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityChargePad(pos, state);
    }

    @Override
    public MenuChargePad getMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new MenuChargePad(windowId, level, pos, playerInventory, playerEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.POWER_TIER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(chargePadTier.getEnergyTier().getLang().getTranslationKey()).withStyle(chargePadTier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.TRANSFER.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(chargePadTier.getEnergyTier().getBasicTransfer())).withStyle(chargePadTier.getEnergyTier().getColor())
        ));

        pTooltip.add(TextComponentUtil.build(
                Component.translatable(EnumLang.CAPACITY.getTranslationKey()).withStyle(ChatFormatting.GRAY),
                Component.translatable(EnumLang.POWER.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(chargePadTier.getEnergyCapacity())).withStyle(chargePadTier.getEnergyTier().getColor())
        ));
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (stateIn.getValue(BlockStateHelper.activeProperty)) {
            RandomSource random = worldIn.getRandom();
            for (int i = 0; i < 20; i++) {
                double x = pos.getX() + 0.1 + random.nextDouble() * 0.9;
                double y = pos.getY() + 1 + 0.3 + random.nextDouble() * 1.4;
                double z = pos.getZ() + 0.1 + random.nextDouble() * 0.9;
                worldIn.addParticle(ParticleTypes.DOLPHIN, x, y, z, 0, 100D, 0);
            }
        }
    }

}
