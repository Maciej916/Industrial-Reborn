package com.maciej916.indreb.common.interfaces.entity;

import com.maciej916.indreb.common.enums.UpgradeType;

import java.util.HashSet;

public interface ISupportUpgrades {


    HashSet<UpgradeType> getSupportedUpgrades();


}
