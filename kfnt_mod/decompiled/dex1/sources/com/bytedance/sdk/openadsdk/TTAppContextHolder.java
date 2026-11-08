package com.bytedance.sdk.openadsdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.bytedance.sdk.openadsdk.api.b.d;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTAppContextHolder {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile Context f5177a;

    public static Context getContext() {
        if (f5177a == null) {
            setContext(null);
        }
        return f5177a;
    }

    public static synchronized void setContext(Context context) {
        if (f5177a == null) {
            if (context != null) {
                f5177a = context.getApplicationContext();
            } else if (a.a() != null) {
                try {
                    f5177a = a.a();
                    if (f5177a != null) {
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        @SuppressLint({"StaticFieldLeak"})
        private static volatile Application f5178a;

        public static Application a() {
            return f5178a;
        }

        static {
            try {
                Object objB = b();
                f5178a = (Application) objB.getClass().getMethod("getApplication", new Class[0]).invoke(objB, new Object[0]);
                d.c("MyApplication", "application get success");
            } catch (Throwable th) {
                d.b("MyApplication", "application get failed", th);
            }
        }

        private static Object b() {
            try {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]);
                method.setAccessible(true);
                return method.invoke(null, new Object[0]);
            } catch (Throwable th) {
                d.b("MyApplication", "ActivityThread get error, maybe api level <= 4.2.2", th);
                return null;
            }
        }
    }
}
