package com.ss.android.message;

import android.app.Application;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static Application a;
    private static AtomicBoolean b = new AtomicBoolean(false);

    public static void a(Application application) {
        if (b.getAndSet(true)) {
            return;
        }
        a = application;
        if (com.ss.android.message.a.b.c(application)) {
            a.registerActivityLifecycleCallbacks(com.bytedance.common.push.a.a.a());
        }
    }

    public static Application a() {
        return a;
    }

    @Deprecated
    public static int b() {
        return a.getApplicationInfo().targetSdkVersion;
    }
}
