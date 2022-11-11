package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.common.container.IndRebMenu;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarHorizontal;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarVertical;
import com.maciej916.indreb.common.screen.button.GuiExpButton;
import com.maciej916.indreb.common.screen.button.GuiInfoButton;
import com.maciej916.indreb.common.screen.slot.GuiSlotElement;
import com.maciej916.indreb.common.screen.widgets.GuiCooldown;
import com.maciej916.indreb.common.screen.widgets.GuiUpgrades;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BetterScreen <T extends IndRebMenu> extends PanelScreen<T> {

    public BetterScreen(T container, Inventory inventory, Component component) {
        super(container, inventory, component);
    }

    @Override
    protected void init() {
        super.init();

        if (getBlockEntity() instanceof ISupportUpgrades) {
            addRenderableOnlyComponent(new GuiUpgrades(this));
            getBlockEntity().getUpgradeSlot().forEach(sl -> addRenderableOnlyComponent(new GuiSlotElement(this, sl)));
        }

        if (getBlockEntity() instanceof ICooldown) {
            addRenderableOnlyComponent(new GuiCooldown(this));
        }

        if (getBlockEntity() instanceof IEnergyBlock energyBlock) {
            if (energyBlock.showBarInGui()) {
                if (energyBlock.showVertical()) {
                    addRenderableOnlyComponent(new GuiElectricBarVertical(this, energyBlock.leftOffsetVertical(), energyBlock.topOffsetVertical(), getBlockEntity().getEnergyStorage(), getBlockEntity()));
                } else {
                    addRenderableOnlyComponent(new GuiElectricBarHorizontal(this, energyBlock.leftOffsetHorizontal(), energyBlock.topOffsetHorizontal(), getBlockEntity().getEnergyStorage()));
                }
            }
        }

        getBlockEntity().getElectricSlot().forEach(sl -> addRenderableOnlyComponent(new GuiSlotElement(this, sl)));
        getBlockEntity().getSlots().forEach(sl -> addRenderableOnlyComponent(new GuiSlotElement(this, sl)));

        // Left side
        int topLeftOffset = 5;

        if (getBlockEntity() instanceof ISupportUpgrades supportUpgrades) {
            if (supportUpgrades.hasUpgrades()) {
                addRenderableComponent(new GuiInfoButton(this, supportUpgrades, topLeftOffset));
                topLeftOffset += 24;
            }
        }

        if (getBlockEntity() instanceof IExpCollector expCollector) {
            if (expCollector.hasExpButton()) {
                addRenderableComponent(new GuiExpButton(this, expCollector, topLeftOffset));
                topLeftOffset += 24;
            }
        }
    }
}
