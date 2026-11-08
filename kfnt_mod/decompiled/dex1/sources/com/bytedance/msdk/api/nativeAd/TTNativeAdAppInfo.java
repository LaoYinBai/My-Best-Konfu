package com.bytedance.msdk.api.nativeAd;

import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTNativeAdAppInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f4321a;

    /* renamed from: b, reason: collision with root package name */
    private String f4322b;

    /* renamed from: c, reason: collision with root package name */
    private long f4323c;
    private String d;
    private Map<String, String> e;
    private String f;
    private String g;
    private Map<String, Object> h;

    public Map<String, Object> getAppInfoExtra() {
        return this.h;
    }

    public String getAppName() {
        return this.f4321a;
    }

    public String getAuthorName() {
        return this.f4322b;
    }

    public long getPackageSizeBytes() {
        return this.f4323c;
    }

    public Map<String, String> getPermissionsMap() {
        return this.e;
    }

    public String getPermissionsUrl() {
        return this.d;
    }

    public String getPrivacyAgreement() {
        return this.f;
    }

    public String getVersionName() {
        return this.g;
    }

    public void setAppInfoExtra(Map<String, Object> map) {
        this.h = map;
    }

    public void setAppName(String str) {
        this.f4321a = str;
    }

    public void setAuthorName(String str) {
        this.f4322b = str;
    }

    public void setPackageSizeBytes(long j) {
        this.f4323c = j;
    }

    public void setPermissionsMap(Map<String, String> map) {
        this.e = map;
    }

    public void setPermissionsUrl(String str) {
        this.d = str;
    }

    public void setPrivacyAgreement(String str) {
        this.f = str;
    }

    public void setVersionName(String str) {
        this.g = str;
    }
}
