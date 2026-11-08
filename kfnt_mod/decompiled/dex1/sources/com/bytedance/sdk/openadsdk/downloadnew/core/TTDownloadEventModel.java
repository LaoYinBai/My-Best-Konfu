package com.bytedance.sdk.openadsdk.downloadnew.core;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTDownloadEventModel {

    /* renamed from: a, reason: collision with root package name */
    private String f5293a;

    /* renamed from: b, reason: collision with root package name */
    private String f5294b;

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f5295c;

    public static TTDownloadEventModel builder() {
        return new TTDownloadEventModel();
    }

    public TTDownloadEventModel setTag(String str) {
        this.f5293a = str;
        return this;
    }

    public TTDownloadEventModel setLabel(String str) {
        this.f5294b = str;
        return this;
    }

    public TTDownloadEventModel setExtJson(JSONObject jSONObject) {
        this.f5295c = jSONObject;
        return this;
    }

    public String getTag() {
        return this.f5293a;
    }

    public String getLabel() {
        return this.f5294b;
    }

    public JSONObject getExtJson() {
        return this.f5295c;
    }
}
