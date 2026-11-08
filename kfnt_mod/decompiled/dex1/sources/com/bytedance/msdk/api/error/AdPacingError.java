package com.bytedance.msdk.api.error;

import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdPacingError extends AdError {

    /* renamed from: a, reason: collision with root package name */
    private String f4317a;

    /* renamed from: b, reason: collision with root package name */
    private String f4318b;

    public AdPacingError(int i, String str, String str2, String str3) {
        super(i, str);
        this.f4317a = str2;
        this.f4318b = str3;
    }

    public String getBlockPacing() {
        return this.f4317a;
    }

    public String getRuleId() {
        return this.f4318b;
    }
}
