package com.ss.android.message;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.bytedance.common.push.utility.a.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements c.a {
    private static d a;
    private static HandlerThread c;
    private static boolean d;
    private final com.bytedance.common.push.utility.a.c b;

    public void handleMsg(Message message) {
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    private d() {
        if (c == null) {
            c = new HandlerThread("PushThreadHandler");
            c.start();
            d = true;
        }
        this.b = new com.bytedance.common.push.utility.a.c(c.getLooper(), this);
    }

    public Looper b() {
        return c.getLooper();
    }

    public com.bytedance.common.push.utility.a.c c() {
        return this.b;
    }

    public void a(Runnable runnable) {
        a(runnable, 0L);
    }

    public void a(Runnable runnable, long j) {
        if (j <= 0) {
            this.b.post(runnable);
        } else {
            this.b.postDelayed(runnable, j);
        }
    }
}
