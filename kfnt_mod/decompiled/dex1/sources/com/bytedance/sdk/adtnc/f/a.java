package com.bytedance.sdk.adtnc.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    protected com.bytedance.sdk.adtnc.sdk.b.a f5108a;

    /* renamed from: b, reason: collision with root package name */
    protected com.bytedance.sdk.adtnc.b.a f5109b;

    public a(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        this.f5108a = aVar;
    }

    public a a(com.bytedance.sdk.adtnc.b.a aVar) {
        this.f5109b = aVar;
        return this;
    }

    protected void a(boolean z) {
        if (this.f5109b != null) {
            this.f5109b.a(z);
        }
    }
}
