package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.bh;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class w implements bh.c {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ u f3255a;

    w(u uVar) {
        this.f3255a = uVar;
    }

    @Override // com.baidu.mobads.sdk.internal.bh.c
    public void a(boolean z) {
        if (z) {
            try {
                if (f.f3227a != null) {
                    this.f3255a.f3250b = f.f3227a.i();
                    if (this.f3255a.f3250b != null) {
                        this.f3255a.k();
                        return;
                    }
                }
            } catch (Exception unused) {
                this.f3255a.a("加载dex异常");
                return;
            }
        }
        f.f3227a = null;
        this.f3255a.a("加载dex失败");
    }
}
