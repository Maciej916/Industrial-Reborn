package com.maciej916.indreb.common.interfaces.entity;

import com.maciej916.indreb.common.enums.UpgradeType;

import java.util.List;

public interface ISupportUpgrades {

    List<UpgradeType> getSupportedUpgrades();

}
