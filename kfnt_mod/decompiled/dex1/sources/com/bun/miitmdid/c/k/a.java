package com.bun.miitmdid.c.k;

import android.content.Context;
import androidx.annotation.Keep;
import java.lang.reflect.Method;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private static Object f3315a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private static Class<?> f3316b;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private static Method f3317c;

    @Keep
    private static Method d;

    @Keep
    private static Method e;

    static {
        try {
            f3316b = Class.forName("com.android.id.impl.IdProviderImpl");
            f3315a = f3316b.newInstance();
        } catch (Exception e2) {
            com.bun.lib.a.a("IdentifierManager", "reflect exception!", e2);
        }
        try {
            if (f3316b != null) {
                f3317c = f3316b.getMethod("getOAID", Context.class);
            }
        } catch (Exception e3) {
            com.bun.lib.a.a("IdentifierManager", "reflect exception!", e3);
        }
        try {
            if (f3316b != null) {
                d = f3316b.getMethod("getVAID", Context.class);
            }
        } catch (Exception e4) {
            com.bun.lib.a.a("IdentifierManager", "reflect exception!", e4);
        }
        try {
            if (f3316b != null) {
                e = f3316b.getMethod("getAAID", Context.class);
            }
        } catch (Exception e5) {
            com.bun.lib.a.a("IdentifierManager", "reflect exception!", e5);
        }
    }

    @Keep
    public static native String a(Context context);

    @Keep
    private static native String a(Context context, Method method);

    @Keep
    public static native boolean a();

    @Keep
    public static native String b(Context context);

    @Keep
    public static native String c(Context context);
}
