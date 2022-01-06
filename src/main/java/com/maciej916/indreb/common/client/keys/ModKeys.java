package com.maciej916.indreb.common.client.keys;

import com.maciej916.indreb.IndReb;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class ModKeys {
    private static final String CATEGORY = "Industrial Reborn";

    public static final KeyMapping NIGHT_VISION_KEY = new KeyMapping("key." + IndReb.MODID + ".night_vision", 67, CATEGORY);

    public static void register() {
        ClientRegistry.registerKeyBinding(NIGHT_VISION_KEY);

        net.minecraftforge.client.settings.KeyConflictContext inGame = net.minecraftforge.client.settings.KeyConflictContext.IN_GAME;
        NIGHT_VISION_KEY.setKeyConflictContext(inGame);
    }
}