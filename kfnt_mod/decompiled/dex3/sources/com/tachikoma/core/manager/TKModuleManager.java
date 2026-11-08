package com.tachikoma.core.manager;

import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.api.IModuleInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.module.TKBuilder;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKModuleManager {
    private final HashMap<Class<?>, Object> handlerMap = new HashMap<>();

    private static class Holder {
        private static final TKModuleManager INSTANCE = new TKModuleManager();

        private Holder() {
        }
    }

    public static TKModuleManager getInstance() {
        return Holder.INSTANCE;
    }

    public void clear() {
        this.handlerMap.clear();
    }

    public <T> T getHandler(TKJSContext tKJSContext, Class<T> cls) {
        if (cls == null || tKJSContext == null) {
            return null;
        }
        return (T) getHandler(cls);
    }

    public <T> T getHandler(Class<T> cls) {
        T t;
        if (cls == null || this.handlerMap == null || (t = (T) this.handlerMap.get(cls)) == null || !cls.isInstance(t)) {
            return null;
        }
        return t;
    }

    public void register(IModuleInner iModuleInner) {
        TKBuilder tKBuilder = new TKBuilder();
        iModuleInner.applyOptions(tKBuilder);
        this.handlerMap.clear();
        this.handlerMap.putAll(tKBuilder.build());
        IHostEnvInner iHostEnvInner = (IHostEnvInner) getHandler(IHostEnvInner.class);
        if (iHostEnvInner != null) {
            Logger.setShowLog(iHostEnvInner.isShowLog());
        }
    }
}
