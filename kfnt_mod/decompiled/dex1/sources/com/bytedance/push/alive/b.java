package com.bytedance.push.alive;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f4733a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f4734b;

    /* renamed from: c, reason: collision with root package name */
    private final com.bytedance.common.push.utility.a.c f4735c = com.ss.android.message.d.a().c();

    public static b a(Context context) {
        if (f4733a == null) {
            synchronized (b.class) {
                if (f4733a == null) {
                    f4733a = new b(context);
                }
            }
        }
        return f4733a;
    }

    private b(Context context) {
        this.f4734b = context.getApplicationContext();
    }

    public boolean a(String str) throws PackageManager.NameNotFoundException {
        c cVarD = d();
        if (cVarD != null) {
            return cVarD.a(this.f4734b, str);
        }
        return true;
    }

    public void a() {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.alive.b.1
            @Override // java.lang.Runnable
            public void run() {
                c cVarD = b.this.d();
                if (cVarD != null) {
                    cVarD.a(b.this.f4734b, b.this.f4735c);
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        } else {
            runnable.run();
        }
    }

    public void b() {
        c cVarD = d();
        if (cVarD != null) {
            cVarD.a(this.f4734b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized c d() {
        Class<?> cls;
        try {
            cls = Class.forName("com.a.b.AKeeperImpl");
        } catch (Throwable th) {
            throw th;
        }
        return (c) cls.getMethod("inst", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean c() {
        c cVarD = d();
        if (cVarD != null) {
            return cVarD.b();
        }
        return true;
    }
}
