package com.apm.insight.j;

import android.os.Handler;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private Handler f2885a;

    /* renamed from: b, reason: collision with root package name */
    private final long f2886b;

    /* renamed from: c, reason: collision with root package name */
    private final long f2887c;

    a(Handler handler, long j, long j2) {
        this.f2885a = handler;
        this.f2886b = j;
        this.f2887c = j2;
    }

    void a() {
        if (b() > 0) {
            this.f2885a.postDelayed(this, b());
        } else {
            this.f2885a.post(this);
        }
    }

    void a(long j) {
        if (j > 0) {
            this.f2885a.postDelayed(this, j);
        } else {
            this.f2885a.post(this);
        }
    }

    long b() {
        return this.f2886b;
    }

    long c() {
        return this.f2887c;
    }
}
