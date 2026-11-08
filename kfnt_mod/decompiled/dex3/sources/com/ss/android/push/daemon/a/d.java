package com.ss.android.push.daemon.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d {
    private volatile long a = 0;

    d() {
    }

    boolean a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.a < 200) {
            return true;
        }
        synchronized (this) {
            if (jCurrentTimeMillis - this.a < 200) {
                return true;
            }
            this.a = jCurrentTimeMillis;
            return false;
        }
    }
}
