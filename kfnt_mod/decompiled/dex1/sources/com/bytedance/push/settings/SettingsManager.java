package com.bytedance.push.settings;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SettingsManager {
    private static final com.bytedance.push.settings.storage.h sStorageFactory = new com.bytedance.push.settings.storage.h();
    private static final f sLocalSettingsCache = new f(sStorageFactory);
    private static final g mSettingsCache = new g();

    public static <T> T obtain(Context context, Class<T> cls) {
        if (ISettings.class.isAssignableFrom(cls)) {
            return (T) mSettingsCache.a(context, cls);
        }
        if (ILocalSettings.class.isAssignableFrom(cls)) {
            return (T) sLocalSettingsCache.a(context, cls);
        }
        throw new IllegalArgumentException("tClass必须是ISettings或ILocalSettings的子类");
    }

    public static i getStorageFactory() {
        return sStorageFactory;
    }
}
