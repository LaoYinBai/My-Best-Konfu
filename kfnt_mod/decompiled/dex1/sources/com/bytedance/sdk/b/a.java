package com.bytedance.sdk.b;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.b.b.b;
import com.bytedance.sdk.b.c.d;
import com.bytedance.sdk.b.f.c;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5125a = "AdDNSSdk";

    /* renamed from: b, reason: collision with root package name */
    private static volatile a f5126b;

    /* renamed from: c, reason: collision with root package name */
    private Map<String, b> f5127c = new HashMap();

    public String b() {
        return "1.0.0";
    }

    public int c() {
        return 100;
    }

    private a() {
    }

    public static a a() {
        if (f5126b == null) {
            synchronized (a.class) {
                if (f5126b == null) {
                    f5126b = new a();
                }
            }
        }
        return f5126b;
    }

    public synchronized a a(com.bytedance.sdk.b.e.a.a aVar) {
        b dVar;
        b(aVar);
        if (this.f5127c.containsKey(aVar.b())) {
            return this;
        }
        if (c.a(aVar.a())) {
            dVar = new com.bytedance.sdk.b.c.c();
        } else {
            dVar = new d();
        }
        dVar.a(aVar);
        com.bytedance.sdk.b.f.a.d = aVar.g();
        this.f5127c.put(aVar.b(), dVar);
        com.bytedance.sdk.b.f.b.a(f5125a, "init", aVar.b(), "init success");
        return this;
    }

    public void a(String str, String[] strArr) {
        com.bytedance.sdk.b.f.b.a(f5125a, "preLoadDomains", str, "start load");
        try {
            a(str).a(strArr);
        } catch (Throwable unused) {
        }
    }

    public synchronized List<InetAddress> a(String str, String str2) {
        com.bytedance.sdk.b.f.b.a(f5125a, "lookup", str, "start lookup");
        try {
            if (c.a(str2)) {
                com.bytedance.sdk.b.f.b.a(f5125a, "lookup", str, "param is real IP ", str2);
                ArrayList arrayList = new ArrayList();
                arrayList.add(InetAddress.getByName(str2));
                return arrayList;
            }
            return a(str).a(str2);
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    private b a(String str) {
        b(str);
        c(str);
        return this.f5127c.get(str);
    }

    private void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not null");
        }
    }

    private void b(com.bytedance.sdk.b.e.a.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("depend must not null");
        }
        a(aVar.a());
        b(aVar.b());
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("id must not null");
        }
    }

    private void c(String str) {
        if (this.f5127c.get(str) == null) {
            throw new IllegalArgumentException("The manager instance to ID is not initialized");
        }
    }
}
