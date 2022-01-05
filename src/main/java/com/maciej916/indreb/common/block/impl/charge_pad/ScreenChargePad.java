package com.maciej916.indreb.common.block.impl.charge_pad;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.energy.impl.BasicEnergyStorage;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.text.GuiTextElectricProgress;
import com.maciej916.indreb.common.screen.widgets.GuiText;
import com.maciej916.indreb.common.tier.ChargePadTier;
import com.maciej916.indreb.common.util.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScreenChargePad extends BetterScreen<ContainerChargePad> {

    public ScreenChargePad(ContainerChargePad container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BasicEnergyStorage energyStorage = getBlockEntity().getEnergyStorage();
        BlockChargePad block = (BlockChargePad) getBlockEntity().getBlock();
        ChargePadTier chargePadTier = block.getChargePadTier();

        addRenderableOnlyComponent(new GuiTextElectricProgress(this, 50, 10, 90, 24, energyStorage));
        addRenderableOnlyComponent(new GuiText(this, 50, 10, 90, 58, TextComponentUtil.build(
                new TranslatableComponent(EnumLang.TRANSFER.getTranslationKey()),
                new TranslatableComponent(EnumLang.POWER_TICK.getTranslationKey(), TextComponentUtil.getFormattedEnergyUnit(chargePadTier.getEnergyTier().getBasicTransfer()))
        )));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/charge_pad.png");
    }

}
