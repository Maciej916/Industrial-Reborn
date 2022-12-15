package com.maciej916.indreb.common.subscribe.event;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import com.maciej916.indreb.common.util.LevelUtil;
import com.maciej916.indreb.common.util.RadiationHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = IndReb.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTickRadiation {

    @SubscribeEvent
    public static void event(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (!event.player.getLevel().isClientSide()) {
            ServerLevel level = (ServerLevel) event.player.getLevel();
            player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> {
                double radiationAdd = 0;
                double radiationRemove = 0;
                int radiationProtection = 0;

                List<Entity> entities = level.getEntities(null, player.getBoundingBox().inflate(40));
                for (Entity entity: entities) {
                    if (entity instanceof ItemEntity itemEntity) {
                        ItemStack stack = itemEntity.getItem();
                        if (stack.getItem() instanceof IHasRadiation hasRadiation) {
                            int distance = entity.blockPosition().distManhattan(player.getOnPos());
                            radiationAdd += RadiationHelper.calculateRadiationDistance(hasRadiation.getAddRads() * stack.getCount(), distance);
                            radiationRemove += RadiationHelper.calculateRadiationDistance(hasRadiation.getRemoveRads() * stack.getCount(), distance);
                        }
                    }
                }

                HashSet<LevelChunk> chunks = LevelUtil.getRadiusChunks(player, 3);
                for (LevelChunk chunk: chunks) {
                    Map<BlockPos, BlockEntity> entityMap = chunk.getBlockEntities();
                    for (BlockEntity entity: entityMap.values()) {
                        if (entity instanceof IHasRadiation hasRadiation) {
                            int distance = entity.getBlockPos().distManhattan(player.getOnPos());
                            radiationAdd += RadiationHelper.calculateRadiationDistance(hasRadiation.getAddRads(), distance);
                            radiationRemove += RadiationHelper.calculateRadiationDistance(hasRadiation.getRemoveRads(), distance);
                        }
                    }
                }

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (stack.getItem() instanceof IHasRadiation hasRadiation) {
                        radiationAdd += hasRadiation.getAddRads() * stack.getCount();
                        radiationRemove += hasRadiation.getRemoveRads() * stack.getCount();
                    }
                }

                int armourSlots = 0;
                for (ItemStack stack: player.getArmorSlots()) {
                    if (stack.getItem() instanceof IHasRadiation hasRadiation) {
                        int radsProt = hasRadiation.getRadiationProtection();;
                        radiationProtection += radsProt;
                        if (radsProt > 0) {
                            armourSlots++;
                        }
                    }
                }

                cap.setRadsProtection(radiationProtection);
                cap.setArmourSlots(armourSlots);
                cap.setRadsLevel(radiationAdd - radiationRemove);
            });
        }
    }

}
