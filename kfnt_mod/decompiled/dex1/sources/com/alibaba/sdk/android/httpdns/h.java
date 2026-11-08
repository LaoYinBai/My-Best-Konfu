package com.alibaba.sdk.android.httpdns;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends Exception {

    /* renamed from: b, reason: collision with root package name */
    private int f2374b;

    public h(int i, String str) {
        super(str);
        this.f2374b = i;
    }

    public int getErrorCode() {
        return this.f2374b;
    }
}
