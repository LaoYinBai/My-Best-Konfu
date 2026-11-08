package com.sigmob.sdk.nativead;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class u extends Handler {
    long b;
    boolean c;

    public u(Looper looper) {
        super(looper);
        this.b = 100L;
        this.c = true;
    }

    public void a() {
        sendEmptyMessageDelayed(87108, this.b);
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean b() {
        return this.c;
    }
}
