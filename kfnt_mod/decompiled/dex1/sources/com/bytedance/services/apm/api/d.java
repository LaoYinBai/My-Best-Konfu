package com.bytedance.services.apm.api;

import androidx.annotation.Nullable;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private int f5324a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, String> f5325b;

    /* renamed from: c, reason: collision with root package name */
    private byte[] f5326c;

    public d(int i, byte[] bArr) {
        this.f5324a = i;
        this.f5326c = bArr;
    }

    public d(int i, Map<String, String> map, byte[] bArr) {
        this.f5324a = i;
        this.f5325b = map;
        this.f5326c = bArr;
    }

    public int a() {
        return this.f5324a;
    }

    public byte[] b() {
        return this.f5326c;
    }

    @Nullable
    public Map<String, String> c() {
        return this.f5325b;
    }
}
