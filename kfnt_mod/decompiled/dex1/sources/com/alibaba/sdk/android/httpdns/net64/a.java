package com.alibaba.sdk.android.httpdns.net64;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Net64Service {

    /* renamed from: a, reason: collision with root package name */
    private b f2382a;

    /* renamed from: a, reason: collision with other field name */
    private ConcurrentHashMap<String, List<String>> f31a;
    private boolean j;
    private volatile boolean k;

    /* renamed from: com.alibaba.sdk.android.httpdns.net64.a$a, reason: collision with other inner class name */
    private static final class C0074a {

        /* renamed from: a, reason: collision with root package name */
        private static final a f2383a = new a();
    }

    private a() {
        this.f2382a = new b();
        this.f31a = new ConcurrentHashMap<>();
    }

    public static a a() {
        return C0074a.f2383a;
    }

    public List<String> a(String str) {
        return this.f31a.get(str);
    }

    public void a(String str, List<String> list) {
        this.f31a.put(str, list);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m26a() {
        return this.j;
    }

    @Override // com.alibaba.sdk.android.httpdns.net64.Net64Service
    public void enableIPv6(boolean z) {
        this.j = z;
    }

    @Override // com.alibaba.sdk.android.httpdns.net64.Net64Service
    public String getIPv6ByHostAsync(String str) {
        List<String> list;
        if (this.j && (list = this.f31a.get(str)) != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public boolean h() {
        return this.k;
    }
}
