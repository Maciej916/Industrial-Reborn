package com.maciej916.indreb.common.subscribe;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.blockentity.interfaces.IBlockEntityChunkSync;
import com.maciej916.indreb.common.capability.ModCapabilities;
import com.maciej916.indreb.common.capability.entity.EntityCapability;
import com.maciej916.indreb.common.capability.player.PlayerCapability;
import com.maciej916.indreb.common.capability.radiation.IHasRadiation;
import com.maciej916.indreb.common.capability.radiation.RadiationCapability;
import com.maciej916.indreb.common.energy.EnergyCore;
import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.ChunkWatchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Map;

@EventBusSubscriber(modid = IndReb.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void levelCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        if (level.isClientSide()) return;
        event.addCapability(new ResourceLocation(IndReb.MODID, "energy_core"), new EnergyCore(level));
    }

    @SubscribeEvent
    public static void levelTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide()) return;
        event.level.getCapability(ModCapabilities.ENERGY_CORE).ifPresent(IEnergyCore::tick);
    }

    @SubscribeEvent
    public static void entityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(IndReb.MODID, "player"), new PlayerCapability());
        }
        if (event.getObject() instanceof LivingEntity) {
            event.addCapability(new ResourceLocation(IndReb.MODID, "entity"), new EntityCapability());
        }
    }


    @SubscribeEvent
    public static void blockEntityCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {

    }

    @SubscribeEvent
    public static void itemStackCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof IHasRadiation hasRadiation) {
            event.addCapability(new ResourceLocation(IndReb.MODID, "radiation"), new RadiationCapability(hasRadiation));
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.getLevel().isClientSide()) {
            player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(iPlayerCapability -> iPlayerCapability.tickServer((ServerPlayer) player));
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(oldCapability -> event.getOriginal().getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(newCapability -> newCapability.clone(oldCapability)));
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(ModCapabilities.PLAYER_CAPABILITY).ifPresent(cap -> cap.death(event.getSource()));
        }
    }

    @SubscribeEvent
    public static void chunkWatch(ChunkWatchEvent.Watch event) {
        Level level = event.getLevel();
        if (!level.isClientSide()) {
            Map<BlockPos, BlockEntity> entityMap = event.getChunk().getBlockEntities();
            for (BlockEntity entity: entityMap.values()) {
                if (entity instanceof IBlockEntityChunkSync blockEntityChunkSync) {
                    blockEntityChunkSync.syncWithChunk(event.getPlayer());
                }
            }
        }
    }
}
