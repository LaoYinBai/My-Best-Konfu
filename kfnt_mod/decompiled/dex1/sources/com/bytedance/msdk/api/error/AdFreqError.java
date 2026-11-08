package com.bytedance.msdk.api.error;

import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdFreqError extends AdError {

    /* renamed from: a, reason: collision with root package name */
    private String f4315a;

    /* renamed from: b, reason: collision with root package name */
    private String f4316b;

    public AdFreqError(int i, String str, String str2, String str3) {
        super(i, str);
        this.f4315a = str2;
        this.f4316b = str3;
    }

    public String getBlockShowCount() {
        return this.f4315a;
    }

    public String getRuleId() {
        return this.f4316b;
    }
}
