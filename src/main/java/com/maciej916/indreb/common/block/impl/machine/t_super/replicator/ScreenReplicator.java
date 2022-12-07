package com.maciej916.indreb.common.block.impl.machine.t_super.replicator;

import com.maciej916.indreb.IndReb;
import com.maciej916.indreb.common.api.screen.IndRebScreen;
import com.maciej916.indreb.common.api.screen.widget.bar.GuiFluidStorageVerticalWidget;
import com.maciej916.indreb.common.api.screen.widget.button.GuiBackwardButton;
import com.maciej916.indreb.common.api.screen.widget.button.GuiForwardButton;
import com.maciej916.indreb.common.api.screen.widget.progress.GuiProgressFillWidget;
import com.maciej916.indreb.common.capability.scanner.ScannerResult;
import com.maciej916.indreb.common.enums.EnumLang;
import com.maciej916.indreb.common.enums.ReplicatorMode;
import com.maciej916.indreb.common.screen.widget.button.ReplicatorRepeatRunButtonWidget;
import com.maciej916.indreb.common.screen.widget.button.ReplicatorSingleRunButtonWidget;
import com.maciej916.indreb.common.screen.widget.button.ReplicatorStopButtonWidget;
import com.maciej916.indreb.common.screen.widget.text.ReplicatorModeTextWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ScreenReplicator extends IndRebScreen<MenuReplicator> {

    BlockEntityReplicator entity;

    public ScreenReplicator(MenuReplicator menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.entity = (BlockEntityReplicator) getEntity();
    }

    @Override
    public void initElements() {
        super.initElements();

        addRenderableOnlyWidget(new GuiProgressFillWidget(this, 9, 39, entity.progressFill));
        addRenderableOnlyWidget(new GuiFluidStorageVerticalWidget(this, 27, 18, entity.firstTank));
        addRenderableOnlyWidget(new ReplicatorModeTextWidget(this, entity));

        addUssableWidget(new GuiBackwardButton(this, 51, 26, entity.clickPrevPatternClient(), List.of(EnumLang.LAST_PATTERN.getTranslationComponent())));
        addUssableWidget(new GuiForwardButton(this, 89, 26, entity.clickNextPatternClient(), List.of(EnumLang.NEXT_PATTERN.getTranslationComponent())));

        addUssableWidget(new ReplicatorStopButtonWidget(this, 86, 61, entity.clientClickStop()));
        addUssableWidget(new ReplicatorSingleRunButtonWidget(this, 105, 61, entity.clientClickRun(true)));
        addUssableWidget(new ReplicatorRepeatRunButtonWidget(this,124, 61, entity.clientClickRun(false)));
    }

    @Override
    public void updateData() {
        super.updateData();
        menu.getContainerData().updateProgressFloatData(0, entity.progressRecipe);
        menu.getContainerData().updateProgressIntData(1, entity.progressFill);
        entity.setMode(ReplicatorMode.getModeFromId(menu.getContainerData().getIntData(2)));
        entity.setCurrentModeTick(menu.getContainerData().getIntData(3));
        if (entity.getResult().getResultStack().getItem() != menu.getContainerData().getItemStackData(4).getItem()) {
            entity.setResult(new ScannerResult(menu.getContainerData().getItemStackData(4), menu.getContainerData().getIntData(5), menu.getContainerData().getIntData(6)));
        }
    }

    @Override
    public ResourceLocation getGuiLocation() {
        return new ResourceLocation(IndReb.MODID, "textures/gui/container/replicator.png");
    }
}
