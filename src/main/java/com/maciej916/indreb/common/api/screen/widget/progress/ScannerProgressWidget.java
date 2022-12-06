package com.maciej916.indreb.common.api.screen.widget.progress;

import com.maciej916.indreb.common.api.blockentity.interfaces.IBaseProgress;
import com.maciej916.indreb.common.api.enums.GuiSprite;
import com.maciej916.indreb.common.api.interfaces.screen.IGuiHelper;
import com.maciej916.indreb.common.api.screen.widget.BaseProgressWidget;

public class ScannerProgressWidget extends BaseProgressWidget {
    public ScannerProgressWidget(IGuiHelper helper, int x, int y, IBaseProgress progress) {
        super(helper, x, y, progress, GuiSprite.SCANNER, Direction.HORIZONTAL, false);
    }
}
