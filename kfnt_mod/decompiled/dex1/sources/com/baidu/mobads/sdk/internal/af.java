package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.baidu.mobads.sdk.internal.ae;
import com.baidu.mobads.sdk.internal.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class af {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3101a = "PluginLoader";

    /* renamed from: b, reason: collision with root package name */
    private static ClassLoader f3102b;

    public interface a {
        void a();
    }

    public static void a(double d, ae.b bVar, a aVar) {
        m mVar = (m) c.a().a(c.a.f3200a).a();
        if (mVar != null) {
            mVar.startLoadRemotePhp(d, bVar);
        } else if (aVar != null) {
            aVar.a();
        }
    }

    public static boolean a() {
        return bu.d.booleanValue();
    }

    public static ClassLoader a(String str, String str2, String str3, ClassLoader classLoader) {
        m mVar;
        if (!a()) {
            return classLoader;
        }
        if (f3102b == null && (mVar = (m) c.a().a(c.a.f3200a).a()) != null) {
            f3102b = mVar.getClassLoaderFromJar(str, str2, str3, classLoader);
        }
        return f3102b;
    }

    public static void a(Context context, String str, String str2) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.a(context, str, str2);
        }
    }

    public static void a(o oVar) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.a(oVar);
        }
    }

    public static void a(int i) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.a(i);
        }
    }

    public static void b(int i) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.b(i);
        }
    }

    public static void c(int i) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.c(i);
        }
    }

    public static void b() {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.a();
        }
    }

    public static View a(Context context) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            return nVar.a(context);
        }
        return null;
    }

    public static void a(Context context, String str) {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            nVar.a(context, str);
        }
    }

    public static Activity c() {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            return nVar.b();
        }
        return null;
    }

    public static boolean d() {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            return nVar.c();
        }
        return false;
    }

    public static boolean e() {
        n nVar = (n) c.a().a(c.a.f3201b).a();
        if (nVar != null) {
            return nVar.d();
        }
        return false;
    }
}
