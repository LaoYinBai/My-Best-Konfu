package com.cmic.sso.sdk.b.d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private int f5435a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, List<String>> f5436b;

    /* renamed from: c, reason: collision with root package name */
    private String f5437c;

    public b(int i, Map<String, List<String>> map, String str) {
        this.f5435a = i;
        this.f5436b = map;
        this.f5437c = str;
    }

    public int a() {
        return this.f5435a;
    }

    public Map<String, List<String>> b() {
        return this.f5436b == null ? new HashMap() : this.f5436b;
    }

    public String c() {
        return this.f5437c == null ? "" : this.f5437c;
    }

    public boolean d() {
        return this.f5435a == 302 || this.f5435a == 301;
    }
}
