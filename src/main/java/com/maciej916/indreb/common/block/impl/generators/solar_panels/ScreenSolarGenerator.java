package com.maciej916.indreb.common.block.impl.generators.solar_panels;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.IndRebScreen;
import com.maciej916.indreb.common.screen.active.GuiSolarActive;
import com.maciej916.indreb.common.screen.text.GuiTextSolar;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSolarGenerator extends IndRebScreen<ContainerSolarGenerator> {

    public ScreenSolarGenerator(ContainerSolarGenerator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        addRenderableOnly(new GuiSolarActive(this, 80, 25, ((BlockEntitySolarGenerator) getBlockEntity()).getActive()));
        addRenderableOnly(new GuiTextSolar(this, 60, 18, 88, 47, (BlockEntitySolarGenerator) getBlockEntity()));
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/solar_generator.png");
    }
}
