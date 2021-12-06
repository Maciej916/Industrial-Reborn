package com.maciej916.indreb.common.network;

import com.maciej916.indreb.common.network.packet.PacketExperience;
import com.maciej916.indreb.common.network.packet.PacketExtruderRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

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
    }







}