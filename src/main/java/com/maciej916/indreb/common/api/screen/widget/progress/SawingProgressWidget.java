package com.maciej916.indreb.common.api.screen.widget.progress;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;

public class SawingProgressWidget extends BaseProgressWidget {
    public SawingProgressWidget(IGuiHelper helper, int x, int y, IBaseProgress progress) {
        super(helper, x, y, progress, GuiSprite.SAWING, Direction.HORIZONTAL, false);
    }
}
