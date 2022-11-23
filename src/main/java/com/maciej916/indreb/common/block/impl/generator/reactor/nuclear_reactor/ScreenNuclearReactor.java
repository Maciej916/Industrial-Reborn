package com.maciej916.indreb.common.block.impl.generator.reactor.nuclear_reactor;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.button.GuiPlayPauseButtonWidget;
import com.maciej916.indreb.common.block.impl.generator.reactor.screen.GuiReactorOutputWidget;
import com.maciej916.indreb.common.block.impl.generator.reactor.screen.GuiReactorTempVar;
import com.maciej916.indreb.common.enums.EnumLang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenNuclearReactor extends IndRebScreen<MenuNuclearReactor> {

    BlockEntityNuclearReactor entity;

    public ScreenNuclearReactor(MenuNuclearReactor menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityNuclearReactor) getEntity();
        this.imageHeight = 237;
        this.imageWidth = 213;
        this.inventoryLabelY = 143;
        this.inventoryLabelX = 26;
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableWidget(new GuiPlayPauseButtonWidget(this, 100, 129, entity.getReactor(), entity.clickPlayPauseClient(), List.of(EnumLang.REACTOR_PLAY_PAUSE.getTranslationComponent())));
        addRenderableOnlyWidget(new GuiReactorOutputWidget(this, entity));
        addRenderableOnlyWidget(new GuiReactorTempVar(this, entity));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.getReactor().setEnabled(menu.getData().get(0) == 1);
        entity.getReactor().setVentedHeat(menu.getData().get(1));
        entity.getReactor().setCurrentIEOutput(menu.getData().get(2));
        entity.getReactor().setCurrentHeat(menu.getData().get(3));
        entity.getReactor().setMaxHeat(menu.getData().get(4));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/nuclear_reactor.png");
    }
}
