package com.maciej916.indreb.common.api.blockentity.interfaces;

import net.minecraft.server.level.ServerPlayer;

public interface IBlockEntityChunkSync {

    void syncWithChunk(ServerPlayer player);
}
