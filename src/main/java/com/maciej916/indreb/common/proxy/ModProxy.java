package com.maciej916.indreb.common.proxy;

import net.minecraftforge.fml.DistExecutor;

public class ModProxy {
    public static final IProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static void init() {
        PROXY.init();
    }

}
