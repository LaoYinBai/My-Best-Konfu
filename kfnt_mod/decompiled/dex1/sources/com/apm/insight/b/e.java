package com.apm.insight.b;

import androidx.annotation.CallSuper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    public static long f2776a;

    /* renamed from: b, reason: collision with root package name */
    public static long f2777b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f2778c = false;

    @CallSuper
    public void a(String str) {
        this.f2778c = true;
    }

    public boolean a() {
        return false;
    }

    @CallSuper
    public void b(String str) {
        this.f2778c = false;
    }
}
