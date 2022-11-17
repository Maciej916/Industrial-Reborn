package com.maciej916.indreb.common.proxy;

import com.maciej916.indreb.common.proxy.impl.ClientProxy;
import com.maciej916.indreb.common.proxy.impl.ServerProxy;
import com.maciej916.indreb.common.proxy.interfaces.IProxy;
import net.minecraftforge.fml.DistExecutor;

public class ModProxy {
    public static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static void init() {
        PROXY.init();
    }

}
