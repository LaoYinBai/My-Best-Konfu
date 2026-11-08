package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.ae;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bn implements ae.b {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ double f3171a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ bh f3172b;

    bn(bh bhVar, double d) {
        this.f3172b = bhVar;
        this.f3171a = d;
    }

    @Override // com.baidu.mobads.sdk.internal.ae.b
    public void a(String str, String str2) {
        this.f3172b.w = new bf(str);
        double dA = bu.a();
        float f = this.f3172b.m().getFloat(bh.f3162c, 0.0f);
        Boolean boolValueOf = Boolean.valueOf(((float) this.f3172b.w.b()) == f);
        Boolean boolValueOf2 = Boolean.valueOf(dA <= this.f3172b.w.b() && Math.floor(dA) == Math.floor(this.f3172b.w.b()));
        this.f3172b.z.a(bh.f3160a, "try to download apk badVer=" + f + ", isBad=" + boolValueOf + ", compatible=" + boolValueOf2);
        if (this.f3171a >= this.f3172b.w.b() || this.f3172b.w == null || !this.f3172b.w.a().booleanValue() || !boolValueOf2.booleanValue() || boolValueOf.booleanValue()) {
            if (this.f3172b.A) {
                this.f3172b.A = false;
                this.f3172b.a(false, "Refused to download remote for version...");
                return;
            }
            return;
        }
        this.f3172b.a(this.f3172b.w);
    }

    @Override // com.baidu.mobads.sdk.internal.ae.b
    public void a(String str, int i) {
        if (this.f3172b.A) {
            this.f3172b.A = false;
            this.f3172b.a(false, "remote update Network access failed");
        }
    }
}
