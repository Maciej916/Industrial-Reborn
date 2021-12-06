package com.maciej916.indreb.common.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

import java.util.function.Consumer;

public class GuiUtil {

    public static float getRed(int color) {
        return (color >> 16 & 0xFF) / 255.0F;
    }

    public static float getGreen(int color) {
        return (color >> 8 & 0xFF) / 255.0F;
    }

    public static float getBlue(int color) {
        return (color & 0xFF) / 255.0F;
    }

    public static float getAlpha(int color) {
        return (color >> 24 & 0xFF) / 255.0F;
    }

    public static Font getFont() {
        return Minecraft.getInstance().font;
    }

    public static void drawString(PoseStack poseStack, String text, int x, int y, int color, boolean shadow) {
        if (shadow) {
            getFont().drawShadow(poseStack, text, x, y, color, true);
        } else {
            getFont().draw(poseStack, text, x, y, color);
        }
    }

    public static void prepTextScale(PoseStack poseStack, Consumer<PoseStack> runnable, float x, float y, float scale) {
        float yAdd = 4 - (scale * 8) / 2F;
        poseStack.pushPose();
        poseStack.translate(x, y + yAdd, 0);
        poseStack.scale(scale, scale, scale);
        runnable.accept(poseStack);
        poseStack.popPose();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }


    public static void renderScaled(PoseStack poseStack, String text, int x, int y, float scale, int color, boolean shadow) {
        prepTextScale(poseStack, m -> drawString(m, text, 0, 0, color, shadow), x, y, scale);
    }

}
