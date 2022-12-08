package com.maciej916.indreb.common.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.MutableComponent;

import java.text.DecimalFormat;
import java.util.function.Consumer;

public class GuiUtil {

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

    public static void renderScaledCenter(PoseStack poseStack, MutableComponent component, int x, int width, int y, float scale, int color, boolean shadow) {
        int left = (width / 2) + x - (int) (getFont().width(component) * scale) / 2;
        renderScaled(poseStack, component.getString(), left, y, scale, color, shadow);
    }

    public static DecimalFormat DECIMAL_FORMAT_1 = new DecimalFormat("0.0");
    public static DecimalFormat DECIMAL_FORMAT_2 = new DecimalFormat("0.00");
    public static DecimalFormat DECIMAL_FORMAT_3 = new DecimalFormat("0.000");
    public static String DEGREE_SYMBOL = String.valueOf(((char) 0x00B0));

}
