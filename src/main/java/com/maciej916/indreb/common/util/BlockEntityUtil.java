package com.maciej916.indreb.common.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;

public class BlockEntityUtil {

    public static void spawnExpOrbs(ServerLevel level, Player player, int entry, float experience) {
        if (experience == 0.0F) {
            entry = 0;
        } else if (experience < 1.0F) {
            int i = (int) Math.floor((float)entry * experience);
            if (i < Math.ceil((float)entry * experience) && Math.random() < (double)((float)entry * experience - (float)i)) {
                ++i;
            }
            entry = i;
        }

        while(entry > 0) {
            int j = ExperienceOrb.getExperienceValue(entry);
            entry -= j;
            level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, j));
        }
    }


}
