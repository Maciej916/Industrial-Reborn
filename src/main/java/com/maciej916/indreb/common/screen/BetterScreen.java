package com.maciej916.indreb.common.screen;

import com.maciej916.indreb.common.container.IndRebContainer;
import com.maciej916.indreb.common.energy.interfaces.IEnergyBlock;
import com.maciej916.indreb.common.enums.GuiSlotType;
import com.maciej916.indreb.common.interfaces.entity.ICooldown;
import com.maciej916.indreb.common.interfaces.entity.IExpCollector;
import com.maciej916.indreb.common.interfaces.entity.ISupportUpgrades;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarHorizontal;
import com.maciej916.indreb.common.screen.bar.GuiElectricBarVertical;
import com.maciej916.indreb.common.screen.button.GuiExpButton;
import com.maciej916.indreb.common.screen.slot.GuiItemSlot;
import com.maciej916.indreb.common.screen.slot.GuiSlot;
import com.maciej916.indreb.common.screen.widgets.GuiCooldown;
import com.maciej916.indreb.common.screen.widgets.GuiUpgrades;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BetterScreen <T extends IndRebContainer> extends PanelScreen<T> {

    public BetterScreen(T container, Inventory inventory, Component component) {
        super(container, inventory, component);
    }

    @Override
    protected void init() {
        super.init();

        if (getBlockEntity() instanceof ISupportUpgrades) {
            addRenderableOnlyComponent(new GuiUpgrades(this));
            getBlockEntity().getUpgradeHandlers().forEach(s -> addRenderableOnlyComponent(new GuiItemSlot(GuiSlotType.UPGRADE, this, s.x - 1, s.y - 1)));
        }

        if (getBlockEntity() instanceof ICooldown) {
            addRenderableOnlyComponent(new GuiCooldown(this));
        }

        if (getBlockEntity() instanceof IEnergyBlock energyBlock) {
            if (energyBlock.showInGui()) {
                if (energyBlock.showVertical()) {
                    addRenderableOnlyComponent(new GuiElectricBarVertical(this, energyBlock.leftOffsetVertical(), energyBlock.topOffsetVertical(), getBlockEntity().getEnergyStorage(), getBlockEntity()));
                } else {
                    addRenderableOnlyComponent(new GuiElectricBarHorizontal(this, energyBlock.leftOffsetHorizontal(), energyBlock.topOffsetHorizontal(), getBlockEntity().getEnergyStorage()));
                }
            }
        }

        getBlockEntity().getElectricSlot().forEach(s -> addRenderableOnlyComponent(new GuiItemSlot(s.guiSlotType(), this, s.getXPosition() - 1, s.getYPosition() - 1)));

        getBlockEntity().getItem().forEach(s -> {
            if (s.guiSlotType() != null) {
                addRenderableOnlyComponent(new GuiSlot(s.guiSlotType(), this, s.getGuiX(), s.getGuiY()));
            }
        });

        if (getBlockEntity() instanceof IExpCollector expCollector) {
            if (expCollector.hasExpButton()) {
                addRenderableComponent(new GuiExpButton(this, expCollector));
            }
        }
    }
}
