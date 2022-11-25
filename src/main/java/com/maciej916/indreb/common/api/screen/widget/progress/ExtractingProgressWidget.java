package com.maciej916.indreb.common.api.screen.widget.progress;

import com.maciej916.indreb.common.api.blockentity.interfaces.IProgress;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;

public class ExtractingProgressWidget extends BaseProgressWidget {
    public ExtractingProgressWidget(IGuiHelper helper, int x, int y, IProgress progress) {
        super(helper, x, y, progress, GuiSprite.EXTRACTING, Direction.HORIZONTAL, false);
    }
}
