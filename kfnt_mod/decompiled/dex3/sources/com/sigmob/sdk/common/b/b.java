package com.sigmob.sdk.common.b;

import android.os.Handler;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b implements Runnable {
    private final Handler a;
    protected volatile long b;
    private volatile boolean c;

    protected b(Handler handler) {
        m.a.a(handler);
        this.a = handler;
    }

    protected abstract void a();

    public void a(long j) {
        m.a.a(j > 0, "intervalMillis must be greater than 0. Saw: " + j);
        this.b = j;
        if (this.c) {
            return;
        }
        this.c = true;
        this.a.post(this);
    }

    public void b() {
        this.c = false;
        this.a.removeCallbacksAndMessages(null);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c) {
            a();
            this.a.postDelayed(this, this.b);
        }
    }
}
