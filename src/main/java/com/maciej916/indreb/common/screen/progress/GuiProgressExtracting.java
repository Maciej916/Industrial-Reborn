package com.maciej916.indreb.common.screen.progress;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

public class GuiProgressExtracting extends GuiProgress {
    public GuiProgressExtracting(IGuiWrapper wrapper, int leftOffset, int topOffset, IProgress progress) {
        super(wrapper, leftOffset, topOffset, progress, GuiSprite.EXTRACTING, Direction.HORIZONTAL, false);
    }
}
