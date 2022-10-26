package com.maciej916.indreb.common.client.keys;

import com.maciej916.indreb.IndReb;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    private static final String CATEGORY = "Industrial Reborn";

    public static final KeyMapping NIGHT_VISION_KEY = new KeyMapping("key." + IndReb.MODID + ".night_vision", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY);

}