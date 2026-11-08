package com.bytedance.sdk.adnet.game.http.b;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f5033a;

    public static Handler a() {
        if (f5033a == null) {
            f5033a = new Handler(Looper.getMainLooper());
        }
        return f5033a;
    }
}
