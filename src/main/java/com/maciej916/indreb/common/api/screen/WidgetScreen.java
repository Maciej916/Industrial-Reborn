package com.maciej916.indreb.common.api.screen;

import com.maciej916.indreb.common.api.screen.widget.BaseWidget;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class WidgetScreen <T extends IndRebContainerMenu> extends BaseScreen<T> {

    private final List<BaseWidget> widgets = new ArrayList<>();

    public WidgetScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected void addRenderableWidget(BaseWidget widget) {
        widgets.add(widget);
        super.addRenderableWidget(widget);
    }

    protected void addRenderableOnlyWidget(BaseWidget widget) {
        widgets.add(widget);
        super.addRenderableOnly(widget);
    }

    protected void drawWidgets(boolean draw) {
        for (BaseWidget widget : widgets) widget.visible = draw;
    }

    @Override
    protected void renderTooltip(PoseStack poseStack, int mouseX, int mouseY) {
        super.renderTooltip(poseStack, mouseX, mouseY);
        for (BaseWidget widget : widgets) {
            widget.renderToolTip(this, poseStack, mouseX, mouseY);
        }
    }

    public List<Rect2i> getAreas() {
        List<Rect2i> extraAreas = new ArrayList<>();

        for (BaseWidget widget : widgets) {
            if (widget.addsExtraArea()) {
                extraAreas.add(new Rect2i(widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight()));
            }
        }

        return extraAreas;
    }
}
