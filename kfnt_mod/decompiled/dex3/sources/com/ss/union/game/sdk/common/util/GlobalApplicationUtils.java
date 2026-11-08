package com.ss.union.game.sdk.common.util;

import android.app.Application;
import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlobalApplicationUtils {
    private static Application a;

    public static void init(Context context) {
        a = (Application) context.getApplicationContext();
    }

    public static Context getContext() {
        return a;
    }

    public static Application getApplication() {
        return a;
    }
}
