package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.e;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.q;
import com.bytedance.msdk.adapter.util.Logger;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile Context f1764a;

    /* renamed from: b, reason: collision with root package name */
    private static volatile bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c f1765b;

    /* renamed from: c, reason: collision with root package name */
    private static volatile bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> f1766c;
    private static volatile bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> d;
    private static volatile bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> e;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a$a, reason: collision with other inner class name */
    static class C0056a implements e.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1767a;

        C0056a(Context context) {
            this.f1767a = context;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.e.a
        public boolean a() {
            Context contextD = this.f1767a;
            if (contextD == null) {
                contextD = a.d();
            }
            return q.a(contextD);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SuppressLint({"StaticFieldLeak"})
        private static volatile Application f1768a;

        static {
            try {
                Object objB = b();
                f1768a = (Application) objB.getClass().getMethod("getApplication", new Class[0]).invoke(objB, new Object[0]);
                Logger.e("MyApplication", "application get success");
            } catch (Throwable th) {
                Logger.e("MyApplication", "application get failed", th);
            }
        }

        public static Application a() {
            return f1768a;
        }

        private static Object b() {
            try {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]);
                method.setAccessible(true);
                return method.invoke(null, new Object[0]);
            } catch (Throwable th) {
                Logger.e("MyApplication", "ActivityThread get error, maybe api level <= 4.2.2", th);
                return null;
            }
        }
    }

    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<>("V1", new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.b(f1764a), e(), b(), a(f1764a));
                }
            }
        }
        return d;
    }

    public static synchronized void b(Context context) {
        if (f1764a == null) {
            synchronized (a.class) {
                if (b.a() != null) {
                    try {
                        f1764a = b.a();
                        if (f1764a != null) {
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (context != null) {
                    f1764a = context.getApplicationContext();
                }
            }
        }
    }

    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> c() {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.b<>("V3", new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.b(f1764a), e(), b(), a(f1764a));
                }
            }
        }
        return e;
    }

    public static Context d() {
        if (f1764a == null) {
            b(null);
        }
        return f1764a;
    }

    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a> e() {
        if (f1766c == null) {
            synchronized (a.class) {
                if (f1766c == null) {
                    f1766c = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.b(f1764a);
                }
            }
        }
        return f1766c;
    }

    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c f() {
        if (f1765b == null) {
            synchronized (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.class) {
                if (f1765b == null) {
                    f1765b = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c();
                }
            }
        }
        return f1765b;
    }

    private static e.a a(Context context) {
        return new C0056a(context);
    }

    private static e.b b() {
        return e.b.b();
    }
}
