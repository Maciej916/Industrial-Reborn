package com.maciej916.indreb.common.block.impl.machines.replicator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.block.impl.machines.replicator.screen.GuiReplicatorMode;
import com.maciej916.indreb.common.block.impl.machines.replicator.screen.GuiReplicatorRepeatRun;
import com.maciej916.indreb.common.block.impl.machines.replicator.screen.GuiReplicatorSingleRun;
import com.maciej916.indreb.common.block.impl.machines.replicator.screen.GuiReplicatorStop;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.screen.BetterScreen;
import com.maciej916.indreb.common.screen.bar.GuiFluidBarVertical;
import com.maciej916.indreb.common.screen.button.GuiBackwardButton;
import com.maciej916.indreb.common.screen.button.GuiForwardButton;
import com.maciej916.indreb.common.screen.progress.GuiProgressFill;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenReplicator extends BetterScreen<MenuReplicator> {

    public ScreenReplicator(MenuReplicator container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void init() {
        super.init();

        BlockEntityReplicator be = (BlockEntityReplicator) getBlockEntity();

        addRenderableOnlyComponent(new GuiFluidBarVertical(this, 27, 18, be.matterTank));
        addRenderableOnlyComponent(new GuiProgressFill(this, 9, 39, be.progressFill));

        addRenderableComponent(new GuiBackwardButton(this, 51, 26, be.clientClickPrevPattern(), List.of(EnumLang.LAST_PATTERN.getTranslationComponent())));
        addRenderableComponent(new GuiForwardButton(this, 89, 26, be.clientClickNextPattern(), List.of(EnumLang.NEXT_PATTERN.getTranslationComponent())));

        addRenderableComponent(new GuiReplicatorStop(this, be, be.clientClickStop()));
        addRenderableComponent(new GuiReplicatorSingleRun(this, be, be.clientClickSingleRun()));
        addRenderableComponent(new GuiReplicatorRepeatRun(this, be, be.clientClickRepeatRun()));
        addRenderableOnlyComponent(new GuiReplicatorMode(this, be));

        drawComponents(true);
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/replicator.png");
    }
}
