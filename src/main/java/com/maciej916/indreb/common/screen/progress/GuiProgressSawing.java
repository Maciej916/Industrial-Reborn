package com.maciej916.indreb.common.screen.progress;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

public class GuiProgressSawing extends GuiProgress {
    public GuiProgressSawing(IGuiWrapper wrapper, int leftOffset, int topOffset, IProgress progress) {
        super(wrapper, leftOffset, topOffset, progress, GuiSprite.SAWING, Direction.HORIZONTAL, false);
    }
}
