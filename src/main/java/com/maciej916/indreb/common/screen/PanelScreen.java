package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.common.container.IndRebMenu;
import com.maciej916.indreb.common.screen.button.GuiExpButton;
import com.maciej916.indreb.common.screen.widgets.GuiElement;
import com.maciej916.indreb.common.screen.widgets.GuiUpgrades;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class PanelScreen <T extends IndRebMenu> extends BaseScreen<T> {

    private final List<AbstractWidget> component;

    public PanelScreen(T container, Inventory inventory, Component component) {
        super(container, inventory, component);
        this.component = new ArrayList<>();
    }

    protected void clearComponent() {
        component.clear();
    }

    protected void addRenderableComponent(AbstractWidget widget) {
        component.add(widget);
        addRenderableWidget(widget);
    }

    protected void addRenderableOnlyComponent(AbstractWidget widget) {
        component.add(widget);
        addRenderableOnly(widget);
    }

    protected void drawComponents(boolean draw) {
        for (AbstractWidget widget : component) widget.visible = draw;
    }

    @Override
    protected void renderTooltip(PoseStack poseStack, int mouseX, int mouseY) {
        super.renderTooltip(poseStack, mouseX, mouseY);
        for (AbstractWidget widget : component) {
            if (widget instanceof GuiElement guiElement) {
                guiElement.renderWidgetToolTip(this, poseStack, mouseX, mouseY);
            }
        }
    }

    public List<Rect2i> getAreas() {
        List<Rect2i> extraAreas = new ArrayList<>();

        for (AbstractWidget widget : component) {
            if (widget instanceof GuiUpgrades guiUpgrades) {
                extraAreas.add(new Rect2i(guiUpgrades.x, guiUpgrades.y, guiUpgrades.getWidth(), guiUpgrades.getHeight()));
            }

            if (widget instanceof GuiExpButton guiExpButton) {
                extraAreas.add(new Rect2i(guiExpButton.x, guiExpButton.y, guiExpButton.getWidth(), guiExpButton.getHeight()));
            }

        }

        return extraAreas;
    }
}
