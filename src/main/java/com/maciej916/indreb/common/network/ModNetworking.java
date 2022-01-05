package com.maciej916.indreb.common.network;

import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.network.packet.PacketExtruderRecipe;
import com.maciej916.indreb.common.network.packet.PacketParticle;
import com.maciej916.indreb.common.network.packet.PacketTransformerMode;
import com.maciej916.indreb.common.network.packet.energy.PacketReqSyncEnergy;
import com.maciej916.indreb.common.network.packet.energy.PacketSyncEnergy;
import com.maciej916.indreb.common.network.packet.energy.PacketSyncNetwork;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.maciej916.indreb.IndReb.MODID;

public class ModNetworking {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void setup() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "networking"), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(nextID(), PacketExperience.class, PacketExperience::toBytes, PacketExperience::new, PacketExperience::handle);
        INSTANCE.registerMessage(nextID(), PacketExtruderRecipe.class, PacketExtruderRecipe::toBytes, PacketExtruderRecipe::new, PacketExtruderRecipe::handle);
        INSTANCE.registerMessage(nextID(), PacketParticle.class, PacketParticle::toBytes, PacketParticle::new, PacketParticle::handle);
        INSTANCE.registerMessage(nextID(), PacketTransformerMode.class, PacketTransformerMode::toBytes, PacketTransformerMode::new, PacketTransformerMode::handle);

        INSTANCE.registerMessage(nextID(), PacketReqSyncEnergy.class, PacketReqSyncEnergy::toBytes, PacketReqSyncEnergy::new, PacketReqSyncEnergy::handle);
        INSTANCE.registerMessage(nextID(), PacketSyncEnergy.class, PacketSyncEnergy::toBytes, PacketSyncEnergy::new, PacketSyncEnergy::handle);
    }
}