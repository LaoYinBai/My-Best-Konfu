package com.kwad.sdk.splashscreen;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SplashPlayModuleCache {
    private HashMap<String, WeakReference<com.kwad.sdk.splashscreen.b.a>> a;

    private enum Holder {
        INSTANCE;

        private SplashPlayModuleCache mInstance = new SplashPlayModuleCache();

        Holder() {
        }

        SplashPlayModuleCache getInstance() {
            return this.mInstance;
        }
    }

    private SplashPlayModuleCache() {
        this.a = new HashMap<>(1);
    }

    public static SplashPlayModuleCache a() {
        return Holder.INSTANCE.getInstance();
    }

    public com.kwad.sdk.splashscreen.b.a a(String str) {
        WeakReference<com.kwad.sdk.splashscreen.b.a> weakReference = this.a.get(str);
        if (weakReference != null) {
            com.kwad.sdk.splashscreen.b.a aVar = weakReference.get();
            if (aVar != null) {
                return aVar;
            }
            this.a.remove(str);
        }
        return null;
    }

    public void a(String str, com.kwad.sdk.splashscreen.b.a aVar) {
        this.a.put(str, new WeakReference<>(aVar));
    }
}
