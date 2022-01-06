package com.maciej916.indreb.common.proxy;

import com.maciej916.indreb.common.client.keys.ModKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ModKeys.register();
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
