package com.maciej916.indreb.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public class ServerProxy implements IProxy {

    @Override
    public void init() {

    }

    @Override
    public Minecraft getClient() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public ClientLevel getClientLevel() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public LocalPlayer getLocalPlayer() {
        throw new IllegalStateException("Only run this on the client!");
    }

}