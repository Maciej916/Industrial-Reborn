package com.maciej916.indreb.common.item.impl.food;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class CannedChorusFruit extends BaseCanned {

    public static final FoodProperties FOOD = (
            new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .fast()
            .alwaysEat()
            .build();

    public CannedChorusFruit() {
        super(new Properties().food(FOOD));
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (!pLevel.isClientSide) {
            double d0 = pEntityLiving.getX();
            double d1 = pEntityLiving.getY();
            double d2 = pEntityLiving.getZ();

            for(int i = 0; i < 16; ++i) {
                double d3 = pEntityLiving.getX() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                double d4 = Mth.clamp(pEntityLiving.getY() + (double)(pEntityLiving.getRandom().nextInt(16) - 8), (double)pLevel.getMinBuildHeight(), (double)(pLevel.getMinBuildHeight() + ((ServerLevel)pLevel).getLogicalHeight() - 1));
                double d5 = pEntityLiving.getZ() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (pEntityLiving.isPassenger()) {
                    pEntityLiving.stopRiding();
                }

                Vec3 vec3 = pEntityLiving.position();
                pLevel.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(pEntityLiving));
                net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(pEntityLiving, d3, d4, d5);
                if (event.isCanceled()) return itemstack;
                if (pEntityLiving.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    SoundEvent soundevent = pEntityLiving instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    pLevel.playSound((Player)null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pEntityLiving.playSound(soundevent, 1.0F, 1.0F);
                    break;
                }
            }

            if (pEntityLiving instanceof Player) {
                ((Player)pEntityLiving).getCooldowns().addCooldown(this, 20);
            }
        }

        return itemstack;
    }

}
