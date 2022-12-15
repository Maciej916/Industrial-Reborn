package com.maciej916.indreb.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.HashSet;

public class LevelUtil {

    public static HashSet<LevelChunk> getRadiusChunks(Player player, int radius) {
        Level level = player.getLevel();
        BlockPos pos = player.blockPosition();
        LevelChunk chunk = level.getChunkAt(pos);

        HashSet<LevelChunk> chunks = new HashSet<>();
        for (int x = radius * -1; x <= radius; x++) {
            int chunkX = SectionPos.blockToSectionCoord(chunk.getPos().getMiddleBlockX() + (x * 16));
            for (int z = radius * -1; z <= radius; z++) {
                int chunkZ = SectionPos.blockToSectionCoord(chunk.getPos().getMiddleBlockZ() + (z * 16));
                chunks.add(level.getChunk(chunkX, chunkZ));
            }
        }

        return chunks;
    }

}
