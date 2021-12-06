package com.maciej916.indreb.common.registries;

import com.maciej916.indreb.common.config.ClientConfig;
import com.maciej916.indreb.common.config.CommonConfig;
import com.maciej916.indreb.common.config.ServerConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public final class Config {

    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

    public static void init() {
        CLIENT_CONFIG = ClientConfig.initClient();
        COMMON_CONFIG = CommonConfig.initCommon();
        SERVER_CONFIG = ServerConfig.initServer();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
    }

}
