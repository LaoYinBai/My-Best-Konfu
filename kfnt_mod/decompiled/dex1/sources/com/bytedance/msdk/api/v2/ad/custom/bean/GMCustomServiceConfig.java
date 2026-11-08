package com.bytedance.msdk.api.v2.ad.custom.bean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class GMCustomServiceConfig {

    /* renamed from: a, reason: collision with root package name */
    private final String f4384a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4385b;

    /* renamed from: c, reason: collision with root package name */
    private final int f4386c;
    private final String d;

    public GMCustomServiceConfig(String str, String str2, int i, String str3) {
        this.f4384a = str;
        this.f4385b = str2;
        this.f4386c = i;
        this.d = str3;
    }

    public String getADNNetworkName() {
        return this.f4384a;
    }

    public String getADNNetworkSlotId() {
        return this.f4385b;
    }

    public int getAdStyleType() {
        return this.f4386c;
    }

    public String getCustomAdapterJson() {
        return this.d;
    }
}
