package com.bytedance.sdk.adtnc.sdk.a;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private int f5122a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, String> f5123b;

    public b(int i, Map<String, String> map) {
        this.f5122a = i;
        this.f5123b = map;
    }

    public int a() {
        return this.f5122a;
    }

    public String a(String str, String str2) {
        String str3;
        return (this.f5123b == null || (str3 = this.f5123b.get(str)) == null) ? str2 : str3;
    }

    public Map<String, String> b() {
        return this.f5123b;
    }

    public boolean c() {
        return this.f5122a >= 200 && this.f5122a < 400;
    }
}
