package com.maciej916.indreb.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public class ClientProxy implements IProxy {

    @Override
    public void init() {

    }

    @Override
    public Minecraft getClient() {
        return Minecraft.getInstance();
    }

    @Override
    public ClientLevel getClientLevel() {
        return Minecraft.getInstance().level;
    }

    @Override
    public LocalPlayer getLocalPlayer() {
        return Minecraft.getInstance().player;
    }

}
