package com.maciej916.indreb.common.proxy;

import net.minecraftforge.fml.DistExecutor;

public class ModProxy {
    public static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new); // unsafeRunForDist?

    public static void init() {
        PROXY.init();
    }

}
