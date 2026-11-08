package com.bytedance.b.a.b;

import android.content.Context;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    static final boolean f3658a = false;

    /* renamed from: b, reason: collision with root package name */
    static final String f3659b = "LogQueue";

    /* renamed from: c, reason: collision with root package name */
    private static d f3660c = null;
    private static final int i = 2000;
    private final Context d;
    private final f f;
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final LinkedList<b> h = new LinkedList<>();
    private final Map<String, a> e = new ConcurrentHashMap();

    static void a(String str) {
    }

    static void a(String str, String str2) {
    }

    public static d a(Context context) {
        if (f3660c == null) {
            synchronized (d.class) {
                if (f3660c == null) {
                    f3660c = new d(context);
                }
            }
        }
        return f3660c;
    }

    public static void a() {
        synchronized (d.class) {
            if (f3660c != null) {
                f3660c.d();
            }
        }
    }

    private d(Context context) {
        this.d = context.getApplicationContext();
        this.f = new f(this.d, this, this.h, this.g);
        this.f.start();
    }

    public void a(String str, a aVar) {
        if (c() || aVar == null) {
            return;
        }
        this.e.put(str, aVar);
    }

    public void a(a aVar) {
        if (c() || aVar == null) {
            return;
        }
        this.e.remove(aVar.c());
    }

    a b(String str) {
        return this.e.get(str);
    }

    Map<String, a> b() {
        return this.e;
    }

    boolean a(String str, byte[] bArr) {
        if (c() || bArr == null || bArr.length <= 0 || b(str) == null) {
            return false;
        }
        synchronized (this.h) {
            if (this.g.get()) {
                return false;
            }
            if (this.h.size() >= 2000) {
                this.h.poll();
            }
            boolean zAdd = this.h.add(new b(str, bArr));
            this.f.a();
            return zAdd;
        }
    }

    boolean c() {
        return this.g.get();
    }

    void d() {
        synchronized (this.h) {
            this.h.clear();
        }
        this.g.set(true);
        this.f.b();
    }
}
