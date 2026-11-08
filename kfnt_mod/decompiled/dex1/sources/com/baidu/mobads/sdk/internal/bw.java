package com.baidu.mobads.sdk.internal;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bw extends h {

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ bv f3192b;

    bw(bv bvVar) {
        this.f3192b = bvVar;
    }

    @Override // com.baidu.mobads.sdk.internal.h
    protected Object i() {
        try {
            String strA = this.f3192b.a("key_crash_trace");
            String strA2 = this.f3192b.a("key_crash_ad");
            if (TextUtils.isEmpty(strA)) {
                return null;
            }
            bs bsVarA = bs.a();
            bsVarA.a(this.f3192b.h);
            bsVarA.a(this.f3192b.a("key_crash_source"), strA, strA2);
            this.f3192b.g();
            return null;
        } catch (Exception e) {
            bb.a().a(e);
            return null;
        }
    }
}
