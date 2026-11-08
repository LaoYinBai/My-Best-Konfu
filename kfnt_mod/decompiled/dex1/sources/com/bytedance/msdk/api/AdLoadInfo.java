package com.bytedance.msdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdLoadInfo {
    public static final String AD_LOADED = "广告加载成功";
    public static final String AD_LOADING = "广告请求中";

    /* renamed from: a, reason: collision with root package name */
    private String f4255a;

    /* renamed from: b, reason: collision with root package name */
    private String f4256b;

    /* renamed from: c, reason: collision with root package name */
    private String f4257c;
    private int d;
    private String e;

    public String getAdType() {
        return this.f4257c;
    }

    public String getAdnName() {
        return this.f4256b;
    }

    public int getErrCode() {
        return this.d;
    }

    public String getErrMsg() {
        return this.e;
    }

    public String getMediationRit() {
        return this.f4255a;
    }

    public AdLoadInfo setAdType(String str) {
        this.f4257c = str;
        return this;
    }

    public AdLoadInfo setAdnName(String str) {
        this.f4256b = str;
        return this;
    }

    public AdLoadInfo setErrCode(int i) {
        this.d = i;
        return this;
    }

    public AdLoadInfo setErrMsg(String str) {
        this.e = str;
        return this;
    }

    public AdLoadInfo setMediationRit(String str) {
        this.f4255a = str;
        return this;
    }

    public String toString() {
        return "{mediationRit='" + this.f4255a + "', adnName='" + this.f4256b + "', adType='" + this.f4257c + "', errCode=" + this.d + ", errMsg=" + this.e + '}';
    }
}
