package com.maciej916.indreb.common.util.wrench;

import com.maciej916.indreb.common.interfaces.wrench.IWrenchAction;

import java.util.ArrayList;
import java.util.List;

public class WrenchAction {

    private final List<IWrenchAction> actions = new ArrayList<>();

    public List<IWrenchAction> getActions() {
        return actions;
    }

    public WrenchAction add(IWrenchAction wrenchAction) {
        actions.add(wrenchAction);
        return this;
    }

}
