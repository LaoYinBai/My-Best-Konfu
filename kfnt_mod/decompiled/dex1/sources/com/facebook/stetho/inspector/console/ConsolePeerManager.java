package com.facebook.stetho.inspector.console;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ConsolePeerManager extends ChromePeerManager {
    private static ConsolePeerManager sInstance;

    private ConsolePeerManager() {
    }

    @Nullable
    public static synchronized ConsolePeerManager getInstanceOrNull() {
        return sInstance;
    }

    public static synchronized ConsolePeerManager getOrCreateInstance() {
        if (sInstance == null) {
            sInstance = new ConsolePeerManager();
        }
        return sInstance;
    }
}
