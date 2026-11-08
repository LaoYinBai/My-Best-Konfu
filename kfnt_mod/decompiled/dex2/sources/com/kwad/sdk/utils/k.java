package com.kwad.sdk.utils;

import android.content.Context;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static volatile k b;
    private Context c;
    private a d;

    private k(Context context) {
        this.c = context.getApplicationContext();
    }

    public static k a(@NonNull Context context) {
        if (b == null) {
            synchronized (k.class) {
                if (b == null) {
                    b = new k(context);
                }
            }
        }
        return b;
    }

    private void c() {
        if (!a.get() || this.c == null) {
            return;
        }
        this.c.unregisterReceiver(this.d);
        a.set(false);
    }

    public void a() {
        if (this.c == null || a.get()) {
            return;
        }
        if (this.d == null) {
            this.d = new a();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.c.registerReceiver(this.d, intentFilter);
        a.set(true);
    }

    public void b() {
        c();
    }
}
