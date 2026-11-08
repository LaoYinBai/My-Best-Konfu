package com.bytedance.msdk.api.v2.ad.custom;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMCustomAdError {

    /* renamed from: a, reason: collision with root package name */
    private int f4358a;

    /* renamed from: b, reason: collision with root package name */
    private String f4359b;

    public GMCustomAdError(int i, String str) {
        this.f4358a = i;
        this.f4359b = str;
    }

    public int getCode() {
        return this.f4358a;
    }

    public String getMessage() {
        return this.f4359b;
    }
}
