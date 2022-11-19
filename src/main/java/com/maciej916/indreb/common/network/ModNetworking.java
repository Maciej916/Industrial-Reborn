package com.maciej916.indreb.common.network;

import com.maciej916.indreb.common.network.packet.PacketBasicEnergySync;
import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.network.packet.PacketFluidSync;
import com.maciej916.indreb.common.network.packet.PacketParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.maciej916.indreb.IndReb.MODID;

public class ModNetworking {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "networking"), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(nextID(), PacketBasicEnergySync.class, PacketBasicEnergySync::toBytes, PacketBasicEnergySync::new, PacketBasicEnergySync::handle);
        INSTANCE.registerMessage(nextID(), PacketFluidSync.class, PacketFluidSync::toBytes, PacketFluidSync::new, PacketFluidSync::handle);


        INSTANCE.registerMessage(nextID(), PacketExperience.class, PacketExperience::toBytes, PacketExperience::new, PacketExperience::handle);
//        INSTANCE.registerMessage(nextID(), PacketExtruderRecipe.class, PacketExtruderRecipe::toBytes, PacketExtruderRecipe::new, PacketExtruderRecipe::handle);
        INSTANCE.registerMessage(nextID(), PacketParticle.class, PacketParticle::toBytes, PacketParticle::new, PacketParticle::handle);
//        INSTANCE.registerMessage(nextID(), PacketTransformerMode.class, PacketTransformerMode::toBytes, PacketTransformerMode::new, PacketTransformerMode::handle);

//        INSTANCE.registerMessage(nextID(), PacketReqSyncEnergy.class, PacketReqSyncEnergy::toBytes, PacketReqSyncEnergy::new, PacketReqSyncEnergy::handle);
//        INSTANCE.registerMessage(nextID(), PacketSyncEnergy.class, PacketSyncEnergy::toBytes, PacketSyncEnergy::new, PacketSyncEnergy::handle);

//        INSTANCE.registerMessage(nextID(), PacketScannerCleanScan.class, PacketScannerCleanScan::toBytes, PacketScannerCleanScan::new, PacketScannerCleanScan::handle);
//        INSTANCE.registerMessage(nextID(), PacketScannerSaveScan.class, PacketScannerSaveScan::toBytes, PacketScannerSaveScan::new, PacketScannerSaveScan::handle);
//        INSTANCE.registerMessage(nextID(), PacketPatternStoragePage.class, PacketPatternStoragePage::toBytes, PacketPatternStoragePage::new, PacketPatternStoragePage::handle);
//        INSTANCE.registerMessage(nextID(), PacketPatternStorageAction.class, PacketPatternStorageAction::toBytes, PacketPatternStorageAction::new, PacketPatternStorageAction::handle);
//        INSTANCE.registerMessage(nextID(), PacketReplicatorPage.class, PacketReplicatorPage::toBytes, PacketReplicatorPage::new, PacketReplicatorPage::handle);
//        INSTANCE.registerMessage(nextID(), PacketReplicatorStop.class, PacketReplicatorStop::toBytes, PacketReplicatorStop::new, PacketReplicatorStop::handle);
//        INSTANCE.registerMessage(nextID(), PacketReplicatorSingleRun.class, PacketReplicatorSingleRun::toBytes, PacketReplicatorSingleRun::new, PacketReplicatorSingleRun::handle);
//        INSTANCE.registerMessage(nextID(), PacketReplicatorRepeatRun.class, PacketReplicatorRepeatRun::toBytes, PacketReplicatorRepeatRun::new, PacketReplicatorRepeatRun::handle);
//        INSTANCE.registerMessage(nextID(), PacketMetalFormerChangeMode.class, PacketMetalFormerChangeMode::toBytes, PacketMetalFormerChangeMode::new, PacketMetalFormerChangeMode::handle);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(ServerPlayer player, MSG message) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendToTrackingChunk(Level level, BlockPos pos, MSG message) {
        INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(pos)), message);
    }
}