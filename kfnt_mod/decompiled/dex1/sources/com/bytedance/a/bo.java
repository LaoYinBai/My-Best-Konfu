package com.bytedance.a;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bo {

    /* renamed from: a, reason: collision with root package name */
    public static volatile bq f3417a;

    /* renamed from: b, reason: collision with root package name */
    public static String f3418b;

    /* renamed from: c, reason: collision with root package name */
    public static Account f3419c;
    public static bp d;

    @Nullable
    public static az e;

    public static bq a(Context context, az azVar) {
        if (f3417a == null) {
            synchronized (bo.class) {
                if (f3417a == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("context == null");
                    }
                    e = azVar;
                    if (d == null) {
                        d = new bp(context);
                    }
                    if (a(context)) {
                        if (h.a(context).f3528c) {
                            h.a(context).a();
                        }
                        try {
                            f3417a = (bq) Class.forName("com.bytedance.applog.manager.newuser.DeviceParamsProvider").getConstructor(Context.class, bp.class, az.class).newInstance(context, d, azVar);
                            de.a("DeviceRegisterParameterFactory create new user device param provider success", null);
                        } catch (Exception e2) {
                            de.a("", e2);
                            de.a("DeviceRegisterParameterFactoryclass com.bytedance.applog.manager.newuser.DeviceParamsProvider not found", e2);
                        }
                    }
                    if (f3417a == null) {
                        f3417a = new ah(context, azVar, d);
                        if (f3419c != null) {
                            ((ah) f3417a).a(f3419c);
                        }
                    }
                }
            }
        }
        return f3417a;
    }

    public static boolean a() {
        az azVar;
        if (TextUtils.isEmpty(f3418b) && (azVar = e) != null) {
            f3418b = azVar.a();
        }
        return "local_test".equals(f3418b);
    }

    public static boolean a(Context context) {
        if (context != null && a()) {
            return h.a(context).f3527b;
        }
        de.a("DeviceRegisterParameterFactory isNewUserMode false. context=" + context + " isDebugChannel()=" + a(), null);
        return false;
    }
}
