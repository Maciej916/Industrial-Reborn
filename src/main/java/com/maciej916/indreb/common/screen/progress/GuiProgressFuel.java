package com.maciej916.indreb.common.screen.progress;

import com.maciej916.indreb.common.enums.GuiSprite;
import com.maciej916.indreb.common.interfaces.entity.IProgress;
import com.maciej916.indreb.common.interfaces.screen.IGuiWrapper;

public class GuiProgressFuel extends GuiProgress {
    public GuiProgressFuel(IGuiWrapper wrapper, int leftOffset, int topOffset, IProgress progress) {
        super(wrapper, leftOffset, topOffset, progress, GuiSprite.SMELTING, Direction.VERTICAL, true);
    }
}
