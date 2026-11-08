package com.alibaba.sdk.android.httpdns;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g {

    /* renamed from: b, reason: collision with root package name */
    private int f2372b;

    /* renamed from: c, reason: collision with root package name */
    private String f2373c;

    g(int i, String str) {
        this.f2372b = i;
        this.f2373c = new JSONObject(str).getString("code");
    }

    public String a() {
        return this.f2373c;
    }

    public int getErrorCode() {
        return this.f2372b;
    }
}
