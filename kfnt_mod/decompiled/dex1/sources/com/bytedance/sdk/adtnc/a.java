package com.bytedance.sdk.adtnc;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.adtnc.b.c;
import com.bytedance.sdk.adtnc.c.b;
import com.bytedance.sdk.adtnc.g.d;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f5073a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, c> f5074b = new HashMap();

    private a() {
    }

    public static a a() {
        if (f5073a == null) {
            synchronized (a.class) {
                if (f5073a == null) {
                    f5073a = new a();
                }
            }
        }
        return f5073a;
    }

    private c a(String str) {
        b(str);
        c(str);
        return this.f5074b.get(str);
    }

    private void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not null");
        }
    }

    private void b(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("depend must not null");
        }
        a(aVar.a());
        b(aVar.c());
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("id must not null");
        }
    }

    private void c(String str) {
        if (this.f5074b.get(str) == null) {
            throw new IllegalArgumentException("The manager instance to ID is not initialized");
        }
    }

    public synchronized a a(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        b(aVar);
        if (this.f5074b.containsKey(aVar.c())) {
            return this;
        }
        c aVar2 = d.a(aVar.a()) ? new com.bytedance.sdk.adtnc.c.a() : new b();
        aVar2.a(aVar);
        com.bytedance.sdk.adtnc.g.a.f5116a = aVar.h();
        this.f5074b.put(aVar.c(), aVar2);
        com.bytedance.sdk.adtnc.g.b.a("AdTNCSdk", "init", aVar.c(), "init success");
        return this;
    }

    public synchronized String a(String str, String str2) {
        com.bytedance.sdk.adtnc.g.b.a("AdTNCSdk", "filterUrl", str, "start filterUrl");
        try {
        } catch (Throwable unused) {
            return str2;
        }
        return a(str).a(str2);
    }

    public synchronized void a(String str, com.bytedance.sdk.adtnc.sdk.a.a aVar, com.bytedance.sdk.adtnc.sdk.a.b bVar) {
        com.bytedance.sdk.adtnc.g.b.a("AdTNCSdk", "onResponse", str, "onResponse start");
        try {
            a(str).a(aVar, bVar);
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(String str, com.bytedance.sdk.adtnc.sdk.a.a aVar, Throwable th) {
        com.bytedance.sdk.adtnc.g.b.a("AdTNCSdk", "onError", str, "onError start");
        try {
            a(str).a(aVar, th);
        } catch (Throwable unused) {
        }
    }

    public String b() {
        return "1.0.0";
    }

    public int c() {
        return 1;
    }
}
