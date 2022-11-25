package com.maciej916.indreb.common.block.impl.transformer;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.enums.TransformerMode;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.text.SimpleTextWidget;
import com.maciej916.indreb.common.screen.widget.button.TransformerModeButtonWidget;
import com.maciej916.indreb.common.screen.widget.text.TransformerInfoTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenTransformer extends IndRebScreen<MenuTransformer> {

    BlockEntityTransformer entity;

    public ScreenTransformer(MenuTransformer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityTransformer) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new SimpleTextWidget(this, 8, 30, 88, 5, Component.translatable("gui."+ IndReb.MODID + ".input"), 0.8f, 4210752, false));
        addRenderableOnlyWidget(new SimpleTextWidget(this, 8, 46, 88, 5, Component.translatable("gui."+ IndReb.MODID + ".output"), 0.8f, 4210752, false));

        addUssableWidget(new TransformerModeButtonWidget(this, 140, 32, entity.changeModeClient()));
        addRenderableOnlyWidget(new TransformerInfoTextWidget(this, 48, 28, entity));
    }

    @Override
    public void updateData() {
        super.updateData();
        entity.setTransformerMode(TransformerMode.getModeFromId(menu.getData().get(0)));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/transformer.png");
    }
}
