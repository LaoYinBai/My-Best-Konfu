package com.xiaomi.push;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class as implements ar, InvocationHandler {
    private static final String[][] a = {new String[]{"com.bun.supplier.IIdentifierListener", "com.bun.supplier.IdSupplier"}, new String[]{"com.bun.miitmdid.core.IIdentifierListener", "com.bun.miitmdid.supplier.IdSupplier"}};

    /* renamed from: a, reason: collision with other field name */
    private Context f126a;

    /* renamed from: a, reason: collision with other field name */
    private Class f128a = null;
    private Class b = null;

    /* renamed from: a, reason: collision with other field name */
    private Method f130a = null;

    /* renamed from: b, reason: collision with other field name */
    private Method f131b = null;
    private Method c = null;
    private Method d = null;
    private Method e = null;
    private Method f = null;
    private Method g = null;

    /* renamed from: a, reason: collision with other field name */
    private final Object f129a = new Object();

    /* renamed from: a, reason: collision with other field name */
    private volatile int f124a = 0;

    /* renamed from: a, reason: collision with other field name */
    private volatile long f125a = 0;

    /* renamed from: a, reason: collision with other field name */
    private volatile a f127a = null;

    private class a {

        /* renamed from: a, reason: collision with other field name */
        Boolean f132a;

        /* renamed from: a, reason: collision with other field name */
        String f133a;
        String b;
        String c;
        String d;

        private a() {
            this.f132a = null;
            this.f133a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }

        boolean a() {
            if (!TextUtils.isEmpty(this.f133a) || !TextUtils.isEmpty(this.b) || !TextUtils.isEmpty(this.c) || !TextUtils.isEmpty(this.d)) {
                this.f132a = true;
            }
            return this.f132a != null;
        }
    }

    public as(Context context) {
        this.f126a = context.getApplicationContext();
        a(context);
        b(context);
    }

    private static Class<?> a(Context context, String str) {
        try {
            return t.a(context, str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static <T> T a(Method method, Object obj, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            T t = (T) method.invoke(obj, objArr);
            if (t != null) {
                return t;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod(str, clsArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    private void a() {
        synchronized (this.f129a) {
            try {
                this.f129a.notifyAll();
            } catch (Exception unused) {
            }
        }
    }

    private void a(Context context) {
        Class<?> clsA = a(context, "com.bun.miitmdid.core.MdidSdk");
        Class<?> cls = null;
        Class<?> cls2 = null;
        int i = 0;
        while (true) {
            if (i >= a.length) {
                break;
            }
            String[] strArr = a[i];
            Class<?> clsA2 = a(context, strArr[0]);
            Class<?> clsA3 = a(context, strArr[1]);
            if (clsA2 != null && clsA3 != null) {
                b("found class in index " + i);
                cls2 = clsA3;
                cls = clsA2;
                break;
            }
            i++;
            cls2 = clsA3;
            cls = clsA2;
        }
        this.f128a = clsA;
        this.f130a = a(clsA, "InitSdk", (Class<?>[]) new Class[]{Context.class, cls});
        this.b = cls;
        this.f131b = a(cls2, "getUDID", (Class<?>[]) new Class[0]);
        this.c = a(cls2, "getOAID", (Class<?>[]) new Class[0]);
        this.d = a(cls2, "getVAID", (Class<?>[]) new Class[0]);
        this.e = a(cls2, "getAAID", (Class<?>[]) new Class[0]);
        this.f = a(cls2, "isSupported", (Class<?>[]) new Class[0]);
        this.g = a(cls2, "shutDown", (Class<?>[]) new Class[0]);
    }

    private void a(String str) {
        if (this.f127a != null) {
            return;
        }
        long j = this.f125a;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j);
        int i = this.f124a;
        if (jElapsedRealtime > 3000 && i < 3) {
            synchronized (this.f129a) {
                if (this.f125a == j && this.f124a == i) {
                    b("retry, current count is " + i);
                    this.f124a = this.f124a + 1;
                    b(this.f126a);
                    j = this.f125a;
                    jElapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j);
                }
            }
        }
        if (this.f127a != null || j < 0 || jElapsedRealtime > 3000 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f129a) {
            if (this.f127a == null) {
                try {
                    b(str + " wait...");
                    this.f129a.wait(3000L);
                } catch (Exception unused) {
                }
            }
        }
    }

    private static boolean a(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double);
    }

    private void b(Context context) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = -jElapsedRealtime;
        if (this.b != null) {
            try {
                ClassLoader classLoader = this.b.getClassLoader();
                if (classLoader == null) {
                    classLoader = context.getClassLoader();
                }
                a(this.f130a, this.f128a.newInstance(), context, Proxy.newProxyInstance(classLoader, new Class[]{this.b}, this));
            } catch (Throwable th) {
                b("call init sdk error:" + th);
            }
        } else {
            jElapsedRealtime = j;
        }
        this.f125a = jElapsedRealtime;
    }

    private static void b(String str) {
        com.xiaomi.channel.commonutils.logger.b.m22a("mdid:" + str);
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public String mo100a() {
        a("getUDID");
        if (this.f127a == null) {
            return null;
        }
        return this.f127a.f133a;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public boolean mo101a() {
        a("isSupported");
        return this.f127a != null && Boolean.TRUE.equals(this.f127a.f132a);
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b */
    public String mo102b() {
        a("getOAID");
        if (this.f127a == null) {
            return null;
        }
        return this.f127a.b;
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        a("getVAID");
        if (this.f127a == null) {
            return null;
        }
        return this.f127a.c;
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        a("getAAID");
        if (this.f127a == null) {
            return null;
        }
        return this.f127a.d;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        this.f125a = SystemClock.elapsedRealtime();
        if (objArr != null) {
            a aVar = new a();
            int length = objArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Object obj2 = objArr[i];
                if (obj2 != null && !a(obj2)) {
                    aVar.f133a = (String) a(this.f131b, obj2, new Object[0]);
                    aVar.b = (String) a(this.c, obj2, new Object[0]);
                    aVar.c = (String) a(this.d, obj2, new Object[0]);
                    aVar.d = (String) a(this.e, obj2, new Object[0]);
                    aVar.f132a = (Boolean) a(this.f, obj2, new Object[0]);
                    a(this.g, obj2, new Object[0]);
                    if (aVar.a()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("has get succ, check duplicate:");
                        sb.append(this.f127a != null);
                        b(sb.toString());
                        synchronized (as.class) {
                            if (this.f127a == null) {
                                this.f127a = aVar;
                            }
                        }
                    }
                }
                i++;
            }
        }
        a();
        return null;
    }
}
