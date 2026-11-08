package com.tachikoma.core.utility;

import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.manager.TKModuleManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKEnv {
    public static boolean isDebug() {
        IHostEnvInner iHostEnvInner = (IHostEnvInner) TKModuleManager.getInstance().getHandler(IHostEnvInner.class);
        return iHostEnvInner != null && iHostEnvInner.isDebug();
    }

    @Deprecated
    public static boolean isDebug(TKJSContext tKJSContext) {
        IHostEnvInner iHostEnvInner = (IHostEnvInner) TKModuleManager.getInstance().getHandler(tKJSContext, IHostEnvInner.class);
        return iHostEnvInner != null && iHostEnvInner.isDebug();
    }
}
